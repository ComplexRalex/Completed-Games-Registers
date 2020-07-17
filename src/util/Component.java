package util;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class Component{

    public final static Insets margin = new Insets(2,2,0,2);

    public final static int width = 560;

    public final static Dimension
        dim1LinePanel = new Dimension(width,44),
        dim2LinesPanel = new Dimension(width,80),
        dimTextField = new Dimension(width-24,25),
        dimSwitchButton = new Dimension(62,22),
        dimTitle = new Dimension(width,75);

    /**
     * Creates a title with predefined configurations and the string provided.
     * <p>
     * It is recommended to append it into the north border in the main panel
     * beacuse of the size.
     * @param title String that contains the title
     * @param bg Background color
     * @return JPanel that contains the title provided
     */
    public static JPanel createTitle(String title, Color bg){
        // Initializing new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.setBackground(bg);
        
        // Initializing JLabel into panel
        JLabel label = new JLabel(title);
        label.setFont(Typeface.labelTitle);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(dimTitle);
        label.setBackground(panel.getBackground());
        label.setForeground(Colour.getFontColor());
        panel.add(label);
        
        return panel;
    }

    /**
     * Creates a subtitle with predifined configurations and the string provided.
     * 
     * @param sub String that contains the subtitle
     * @param bg Background color
     * @return JPanel that contains the subtitle provided
     */
    public static JPanel createSubtitle(String sub, Color bg){
        // Initializing new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.setBackground(bg);

        // Initializing new label
        JLabel label = new JLabel(sub);
        label.setFont(Typeface.labelSubtitle);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(dim1LinePanel);
        label.setBackground(bg);
        label.setForeground(Colour.getFontColor());

        panel.add(label);

        return panel;
    }

    /**
     * Creates a simple paragraph with predefined configurations and the text
     * provided.
     * 
     * @param text String that contains the paragraph text
     * @param font Font the text will have
     * @param bg Background color
     * @return JPanel that contains the given paragraph
     */
    public static JPanel createPlainText(String text, Font font, Color bg){
        // Initializing new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,13,13));
        panel.setBackground(bg);

        // Initializing new label (using HTML format)
        JLabel label = new JLabel(String.format(
            "<html><body style='width: %dpx; background: #%06x;"+
            "font-family: %s; font-weight: normal; font-size: %d;"+
            " color: #%06x; text-align: justify;'>%s",
            width-149,
            Integer.valueOf(bg.getRGB() & 0x00FFFFFF),
            font.getName(),
            font.getSize(),
            Integer.valueOf(Colour.getFontColor().getRGB() & 0x00FFFFFF),
            text
        ));

        /*
         * There is a bug with the text width... Every 10 pixels, the width is
         * increased by 3 more pixels... so, if the original size is 560px, then
         * the result will be 560 + (56*3) = 728px
         * 
         * Because I don't know how's the problem being generated, I solved this
         * by the next equation:
         * 
         * result = width + (width / 10) * 3
         * 
         * Now, replacing "result" with 560 (the expected result) the solution is
         * width ~= 431
         * 
         * So, to make this works, it's necessary subtracting 129 to "width" to
         * get 431 pixels.
         * 
         * But, in order to generate a "gap" on the sides, I made a subtract 20
         * more pixels.
         */

        panel.add(label);

        return panel;
    }

    /**
     * Creates a JPanel with nothing inside it.
     * 
     * @param vgap Height of the vertical gap
     * @param bg Background color
     * @return empty JPanel with <i>vgap</i> pixels of height
     */
    public static JPanel createGap(int vgap, Color bg){
        // Initialize new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.setBackground(bg);
        panel.setPreferredSize(new Dimension(width,vgap));

        return panel;
    }

    /**
     * Creates a text field and a description text with predefined configurations.
     * Depending on the given flag, the JPanel will contain 1 or 2 lines of
     * components.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>,
     * <i>CaretColor</i> and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param field JTextField that will be added to this panel
     * @param flag boolean value which will determine the following things:
     * <p>
     * - If <b>true</b>: it will create the panel with the given description and
     * JTextField in the <i>same line</i>.
     * <p>
     * - If <b>false</b>: it will create the panel with the given description in
     * the <i>first line</i> and then the JTextField in the <i>second</i>.
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createTextField(String info, JTextField field, boolean flag, Color bg){
        return flag ? create1LineTextField(info, field, bg) : create2LineTextField(info, field, bg);
    }

    /**
     * Creates a text field and a description text with predefined configurations
     * in 1 line.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>,
     * <i>CaretColor</i> and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param field JTextField that will be added to this panel
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    private static JPanel create1LineTextField(String info, JTextField field, Color bg){
        // Initializing new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panel.setBackground(bg);
        panel.setPreferredSize(dim1LinePanel);

        // Initializing "info" label
        JLabel desc = new JLabel(info);
        desc.setFont(Typeface.labelPlain);
        desc.setForeground(Colour.getFontColor());
        panel.add(desc);
        
        // Initializing the text field specified
        field.setFont(Typeface.labelPlain);
        field.setBackground(bg == Colour.getBackgroundColor() ? Colour.getPrimaryColor() : Colour.getBackgroundColor());
        field.setForeground(Colour.getFontColor());
        field.setCaretColor(Colour.getFontColor());
        field.setPreferredSize(new Dimension(width-30-desc.getFontMetrics(Typeface.labelPlain).stringWidth(info),25));
        panel.add(field);

        return panel;
    }

    /**
     * Creates a text field and a description text with predefined configurations
     * in 2 lines.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>,
     * <i>CaretColor</i> and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param field JTextField that will be added to this panel
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    private static JPanel create2LineTextField(String info, JTextField field, Color bg){
        // Initializing new panel
        JPanel panel = new JPanel(new GridLayout(2,1,5,3));
        panel.setBackground(bg);
        panel.setPreferredSize(dim2LinesPanel);

        // Initializing "description" panel above the text field.
        JPanel description = new JPanel(new FlowLayout(FlowLayout.LEFT,15,10));
        description.setBackground(bg);

        // Initializing "info" label
        JLabel desc = new JLabel(info);
        desc.setFont(Typeface.labelPlain);
        desc.setForeground(Colour.getFontColor());
        description.add(desc);

        // Initializing "text field" panel which will contain the JTextField
        JPanel textfield = new JPanel(new FlowLayout());
        textfield.setBackground(bg);
        
        // Initializing the text field specified
        field.setFont(Typeface.labelPlain);
        field.setBackground(bg == Colour.getBackgroundColor() ? Colour.getPrimaryColor() : Colour.getBackgroundColor());
        field.setForeground(Colour.getFontColor());
        field.setCaretColor(Colour.getFontColor());
        field.setPreferredSize(dimTextField);
        textfield.add(field);

        // Adding those panles to the "TextField" panel
        panel.add(description);
        panel.add(textfield);

        return panel;
    }

    /**
     * Creates a text area and a description text with predefined configurations.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>area</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>,
     * <i>CaretColor</i> and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text area
     * @param area JTextArea that will be added to this panel
     * @param rows Number of rows that JTextArea will have
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createTextArea(String info, JTextArea area, int rows, Color bg){
        // Initializing new SimplePanel
        SimplePanel panel = new SimplePanel();
        panel.setBackground(bg);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, bg));

        // Initializing description panel which will contain the JLabel
        JPanel description = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        description.setPreferredSize(dim1LinePanel);
        description.setBackground(bg);

        // Initializing label info
        JLabel desc = new JLabel(info);
        desc.setFont(Typeface.labelPlain);
        desc.setForeground(Colour.getFontColor());
        description.add(desc);

        // Initializing text area field specified
        area.setRows(rows);
        area.setColumns(42);
        area.setFont(Typeface.textPlain);
        area.setBackground(bg == Colour.getBackgroundColor() ? Colour.getPrimaryColor() : Colour.getBackgroundColor());
        area.setForeground(Colour.getFontColor());
        area.setCaretColor(Colour.getFontColor());
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        // Initializing scroll pane for the text area field
        JScrollPane scroll = new JScrollPane(area);

        // Adding those panels into the new panel
        panel.add(description);
        panel.add(scroll);

        return panel;
    }

    /**
     * Creates a "switch button" and a description with predefined configurations.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>ON</b> and <b>OFF</b>: Change on <i>Font</i>, <i>Background</i>,
     * <i>Foreground</i> and <i>PreferredSize</i>.
     * <p>
     * <b>Note:</b> The "ON/OFF" switch effect will work once the function
     * <i>runSwitchButtonEffect</i> have been implemented into an
     * <i>actionPerformed</i> function.
     * 
     * @param info Brief explanation of what it's being activated or deactivated
     * @param ON Button that has the purpose to activate the option
     * @param OFF Button that has the purpose to deactivate the option
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createSwitchButton(String info, JButton ON, JButton OFF, Color bg){
        // Initializing new panel
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(bg);
        panel.setPreferredSize(dim1LinePanel);

        // Initializing "description" panel before the ON/OFF buttons
        JLabel desc = new JLabel(info);
        desc.setFont(Typeface.labelPlain);
        desc.setForeground(Colour.getFontColor());
        panel.add(desc);

        // Initializing ON button
        ON.setFont(Typeface.buttonBold);
        ON.setBackground(Colour.getButtonColor());
        ON.setForeground(Colour.getFontColor());
        ON.setPreferredSize(dimSwitchButton);
        panel.add(ON);

        // Initializing OFF button
        OFF.setFont(Typeface.buttonBold);
        OFF.setBackground(Colour.getButtonColor());
        OFF.setForeground(Colour.getFontColor());
        OFF.setPreferredSize(dimSwitchButton);
        panel.add(OFF);

        return panel;
    }

    /**
     * Creates a row filled with radio buttons and a description with predefined
     * configurations.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>buttons</b>: Change on <i>Font</i>, <i>Background</i> and
     * <i>Foreground</i>.
     * <p>
     * 
     * @param info Brief explanation of what is the purpose of the radio buttons
     * @param names String array containing the options
     * @param buttons JRadioButton array that will be added to this panel
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createRadioButtons(String info, String[] names, JRadioButton[] buttons, Color bg){
        // Initialize new panel
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(bg);

        // Initialize "info" label
        JLabel desc = new JLabel(info);
        desc.setFont(Typeface.labelPlain);
        desc.setForeground(Colour.getFontColor());
        panel.add(desc);

        // Initialize button group
        ButtonGroup group = new ButtonGroup();
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new JRadioButton(names[i]);
            buttons[i].setFont(Typeface.buttonPlain);
            buttons[i].setBackground(bg);
            buttons[i].setForeground(Colour.getFontColor());
            group.add(buttons[i]);
            panel.add(buttons[i]);
        }

        return panel;
    }

    /**
     * Creates a combo box and a description with predefined configurations.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>box</b>: Change on <i>Font</i>, <i>Background</i> and
     * <i>Foreground</i>.
     * <p>
     * <b>Note:</b> The JComboBox variable must already contain all the
     * available options that will be shown once created this panel.
     * 
     * @param info Brief explanation of what is the purpose of the combo box
     * @param box JComboBox containing the "String variable" options
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createComboBox(String info, JComboBox<String> box, Color bg){
        // Initialize new panel
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(bg);
        
        // Initialize "info" label
        JLabel desc = new JLabel(info);
        desc.setFont(Typeface.labelPlain);
        desc.setForeground(Colour.getFontColor());
        panel.add(desc);

        // Initialize combo box with the provided options
        box.setFont(Typeface.buttonPlain);
        box.setBackground(Colour.getButtonColor());
        box.setForeground(Colour.getFontColor());
        panel.add(box);

        return panel;
    }

    /**
     * Creates a panel with a single button with predefined configurations.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>button</b>: Change on <i>Font</i>, <i>Background</i>,
     * <i>Foreground</i> and <i>PreferredSize</i>.
     * 
     * @param button JButton that will be inside this JPanel
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createSingleButton(JButton button, Color bg){
        // Initialize new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,12,10));
        panel.setBackground(bg);

        // Initialize single button
        button.setFont(Typeface.buttonPlain);
        button.setBackground(Colour.getButtonColor());
        button.setForeground(Colour.getFontColor());
        button.setPreferredSize(dimTextField);
        panel.add(button);

        return panel;
    }

    /**
     * Creates a panel with the given "general options" and with predefined
     * configurations.
     * <p>
     * Note that his function will change the following properties to these
     * variables:
     * <p>
     * <b>Every element in <i>options</i> array</b>: Change on <i>Font</i>,
     * <i>Background</i>, <i>Foreground</i> and <i>PreferredSize</i>.
     * 
     * @param options JButton array containing every possible option.
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createGeneralOptions(JButton[] options, Color bg){
        // Initialize new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,8,8));
        panel.setBackground(bg);
        
        // Initialize every option
        for(JButton button: options){
            button.setFont(Typeface.buttonPlain);
            button.setBackground(Colour.getButtonColor());
            button.setForeground(Colour.getFontColor());
            panel.add(button);
        }
        
        return panel;
    }

    /**
     * Creates a panel with a given image. Note that the image will be
     * rescaled to a width = 560px.
     * 
     * @param img BufferedImage that will be inside this panel
     * @param bg Background color
     * @return JPanel containing a rescaled version of the given image
     */
    public static JPanel createImage(BufferedImage img, Color bg){
        // Initialize new panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.setBackground(bg);
        
        // Initialize label that will contain the image
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBackground(bg);
        panel.add(label);

        return panel;

    }

    // Missing add a function that will create just one long button 
    
    /**
     * Creates a JScrollPane with predefined configurations.
     * 
     * @param panel JPanel that will be contained into the JScrollPane
     * @return created JScrollPane
     */
    public static JScrollPane createScrollPane(JPanel panel){
        // Initializing new JScrollPane
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(BorderFactory.createLineBorder(panel.getBackground()));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setAlignmentY(JScrollPane.RIGHT_ALIGNMENT);
        
        return scroll;
    }

    /**
     * Depending on an action, toggles the "enabled state" of the selected switch
     * button. This means that if a button is pressed, then it will be disabled and
     * finally will enable its opposite option.
     * For example, if the ON button is pressed, then it won't be available unless
     * it is pressed the OFF button.
     * <p>
     * <b>Note:</b> This must only be executed into an <i>actionPerformed</i>
     * function. The purpose of this method is to add the "switch" effect to the
     * buttons provided. Also, this will add an activated or deactivated color
     * to the button that will be the following:
     * <p>
     * <b> - Green color</b> if it's pressed the ON button
     * <p>
     * <b> - Red color</b> if it's pressed the OFF button
     * This was created for visuals purposes. Nothing related to a "real"
     * functionality.
     * 
     * @param e ActionEvent to be evaluated
     * @param ON JButton to be compared as the ON button
     * @param OFF JButton to be compared as the OFF button
     * @return integers 1, 0 and -1 meaning the following:
     * <p>
     * * <b>1</b>, if the ON button was pressed
     * <p>
     * * <b>0</b>, if the OFF button was pressed
     * <p>
     * * <b>-1</b>, if neither was pressed
     */
    public static int runSwitchButtonEffect(ActionEvent e, JButton ON, JButton OFF){
        if(e.getSource() == ON){
            toggleEnabledButton(ON, false, Colour.colorON);
            toggleEnabledButton(OFF, true, Colour.getButtonColor());
            OFF.requestFocusInWindow();
            return 1;
        }else{
            if(e.getSource() == OFF){
                toggleEnabledButton(ON, true, Colour.getButtonColor());
                toggleEnabledButton(OFF, false, Colour.colorOFF);
                ON.requestFocusInWindow();
                return 0;
            }
        }
        return -1;
    }

    /**
	 * Toggles button state between "Enabled" and "Disabled" and changes its
	 * color.
	 *
	 * @param button component to change its state
	 * @param flag value being button's new state
	 * @param color the life! (button)
	 */
	public static void toggleEnabledButton(JButton button, boolean flag, Color color){
		button.setEnabled(flag);
		button.setBackground(color);
	}
}
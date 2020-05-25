package util;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class Component{

    public final static Insets margin = new Insets(2,2,0,2);

    private final static int width = 560;

    private final static Dimension
        dim1LinePanel = new Dimension(width,40),
        dim2LinesPanel = new Dimension(width,80),
        dimTextField = new Dimension(width-20,25),
        dimButton = new Dimension(62,22),
        dimTitle = new Dimension(0,75);

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
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bg);
        
        // Initializing JLabel into panel
        JLabel label = new JLabel(title);
        label.setFont(Typeface.labelTitle);
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
     * @return JLabel that contains the subtitle provided
     */
    public static JLabel createSubtitle(String sub, Color bg){
        // Initializing new label
        JLabel label = new JLabel(sub);
        label.setFont(Typeface.labelSubtitle);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(dim1LinePanel);
        label.setBackground(bg);
        label.setForeground(Colour.getFontColor());

        return label;
    }

    /**
     * Creates a simple paragraph with predefined configurations and the text
     * provided.
     * 
     * @param text String that contains the paragraph text
     * @param bg Background color
     * @return JLabel that contains the given paragraph
     */
    public static JLabel createPlainText(String text, Color bg){
        // Initializing new label (using HTML format)
        JLabel label = new JLabel(String.format(
            "<html><body style='width: %dpx; background: #%06x;"+
            "font-family: %s; font-weight: normal; font-size: %d;"+
            " color: #%06x; text-align: justify;'>%s",
            width-129,
            Integer.valueOf(bg.getRGB() & 0x00FFFFFF),
            Typeface.labelPlain.getName(),
            Typeface.labelPlain.getSize(),
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
         */

        return label;
    }

    /**
     * Creates a text field and a description text with predefined configurations.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>var</b>: Changes on <i>Font</i> and <i>Foreground</i>.
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>
     * and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param var JLabel that can be changed
     * @param field JTextField that will be added to this panel
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createTextField(String info, JLabel var, JTextField field, Color bg){
        // Initializing new panel
        JPanel panel = new JPanel(new GridLayout(2,1,5,3));
        panel.setBackground(bg);
        panel.setPreferredSize(dim2LinesPanel);

        // Initializing "description" panel above the text field.
        // Note that it will contain the "info" and "var" strings
        JPanel description = new JPanel(new FlowLayout());
        description.setBackground(bg);

        // Initializing "info" label
        JLabel desc1 = new JLabel(info);
        desc1.setFont(Typeface.labelPlain);
        desc1.setForeground(Colour.getFontColor());
        description.add(desc1);

        // Initializing "var" label in case it exists
        if(var != null){
            var.setFont(desc1.getFont());
            var.setForeground(desc1.getForeground());
            description.add(var);
        }

        // Initializing the text field specified
        field.setFont(Typeface.labelPlain);
        field.setBackground(bg);
        field.setForeground(Colour.getFontColor());
        field.setPreferredSize(dimTextField);

        // Adding those panles to the "TextField" panel
        panel.add(description);
        panel.add(field);

        return panel;
    }

    /**
     * Creates a text field and a description text with predefined configurations.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>
     * and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param field JTextField that will be added to this panel
     * @param bg Background color
     * @return JPanel containing the mentioned elements
     */
    public static JPanel createTextField(String info, JTextField field, Color bg){
        return createTextField(info, null, field, bg);
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
        ON.setPreferredSize(dimButton);
        panel.add(ON);

        // Initializing OFF button
        OFF.setFont(Typeface.buttonBold);
        OFF.setBackground(Colour.getButtonColor());
        OFF.setForeground(Colour.getFontColor());
        OFF.setPreferredSize(dimButton);
        panel.add(OFF);

        return panel;
    }
    
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
     * 
     * @param e ActionEvent to be evaluated
     * @param ON JButton to be compared as the ON button
     * @param OFF JButton to be compared as the OFF button
     * @return boolean which indicates if the operation was successful or not
     */
    public static boolean runSwitchButtonEffect(ActionEvent e, JButton ON, JButton OFF){
        if(e.getSource() == ON){
            toggleEnabledButton(ON, false, Colour.colorON);
            toggleEnabledButton(OFF, true, Colour.getButtonColor());
            OFF.requestFocusInWindow();
        }else{
            if(e.getSource() == OFF){
                toggleEnabledButton(ON, true, Colour.getButtonColor());
                toggleEnabledButton(OFF, false, Colour.colorOFF);
                ON.requestFocusInWindow();
            }else
                return false;
        }
        return true;
    }

    /**
	 * Toggles button state between "Enabled" and "Disabled" and changes its
	 * color.
	 *
	 * @param button component to change its state
	 * @param flag value being button's new state
	 * @param color the life! (button)
	 */
	private static void toggleEnabledButton(JButton button, boolean flag, Color color){
		button.setEnabled(flag);
		button.setBackground(color);
	}
}
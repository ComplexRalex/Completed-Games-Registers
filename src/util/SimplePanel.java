package util;

//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

/*
    NOTE: THIS HASN'T BE TESTED NEITHER FINISHED YET, SO DON'T GIVE IT TO MUCH ATTENTION.
*/

/**
 * Panel which contains every important methods and components to
 * create a JPanel with options without difficulty
 */
@SuppressWarnings("serial")
public abstract class SimplePanel extends JPanel{
    protected Color bgColor;
    protected GridBagConstraints location;
    protected final int width = 560;
    protected Dimension dim1LinePanel, dim2LinesPanel, dimTextField, dimButton, dimTitle;
    //protected JPanel optionsPanel;

    /**
     * Initializes a SimplePanel object
     * 
     * @param bg Background color
     */
    public SimplePanel(Color bg){
        // Initializing attributes
        bgColor = bg;
        dim1LinePanel = new Dimension(width,40);
        dim2LinesPanel = new Dimension(width,80);
        dimTextField = new Dimension(width-20,25);
        dimButton = new Dimension(62,22);
        dimTitle = new Dimension(0,75);

        // Initializing main JPanel
        setLayout(new BorderLayout());
        setBackground(bgColor);

        /*
        // Initializing "options" JPanel
        optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBackground(bgColor);

        JScrollPane central = new JScrollPane(optionsPanel);
        central.setBorder(BorderFactory.createLineBorder(optionsPanel.getBackground()));
        central.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        central.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        central.setAlignmentY(JScrollPane.RIGHT_ALIGNMENT);

        add(central,BorderLayout.CENTER);
        */
    }

    /**
     * Initializes a SimplePanel object with default configurations
     */
    public SimplePanel(){
        this(Colour.getBackgroundColor());
    }

    /**
     * Creates a title with the string provided.
     * <p>
     * It is recommended to append it into the north border in the main panel.
     * @param title String that contains the title
     * @return JPanel that contains the title provided
     */
    protected JPanel createTitle(String title){
        // Initializing new panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgColor);
        
        // Initializing JLabel into panel
        JLabel label = new JLabel(title);
        label.setFont(Typeface.labelTitle);
        label.setPreferredSize(dimTitle);
        label.setBackground(Colour.getPrimaryColor());
        label.setForeground(Colour.getFontColor());
        
        return panel;
    }

    /**
     * Creates a text field and a description text into the options pane.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>info, var</b>: Changes on <i>Font</i> and <i>Foreground</i>.
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>
     * and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param var JLabel that can be changed
     * @param field JTextField that will be added to this panel
     * @return JPanel containing the mentioned elements
     */
    protected JPanel createTextField(String info, JLabel var, JTextField field){
        // Initializing new panel
        JPanel panel = new JPanel(new GridLayout(2,1,5,3));
        panel.setBackground(bgColor);
        panel.setPreferredSize(dim2LinesPanel);

        // Initializing "description" panel above the text field.
        // Note that it will contain the "info" and "var" strings
        JPanel description = new JPanel(new FlowLayout());
        description.setBackground(bgColor);

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
        field.setBackground(bgColor);
        field.setForeground(Colour.getFontColor());
        field.setPreferredSize(dimTextField);

        // Adding those panles to the "TextField" panel
        panel.add(description);
        panel.add(field);

        return panel;
    }

    /**
     * Creates a text field and a description text into the options pane.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>info</b>: Changes on <i>Font</i> and <i>Foreground</i>.
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>
     * and <i>PreferredSize</i>.
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param field JTextField that will be added to this panel
     * @return JPanel containing the mentioned elements
     */
    protected JPanel createTextField(String info, JTextField field){
        return createTextField(info, null, field);
    }

    /**
     * Creates a "switch button" and a description into the options pane.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>info</b>: Changes on <i>Font</i> and <i>Foreground</i>.
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
     * @return JPanel containing the mentioned elements
     */
    protected JPanel createSwitchButton(String info, JButton ON, JButton OFF){
        // Initializing new panel
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(bgColor);
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
     * Depending on a condition, toggles the "enabled state" of the selected switch
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

    /*
     * Falta crear métodos para poder agregar las opciones o "componentes" a las
     * partes del panel (norte, sur, este y oeste), incluso al ScrollPane...
     */
}
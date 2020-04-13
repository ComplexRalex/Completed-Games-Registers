package util;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

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
    protected Dimension dim2LinesPanel, dimTextField;
    protected JPanel optionsPanel;

    /**
     * Initializes a SimplePanel object
     * 
     * @param bg Background color
     */
    public SimplePanel(Color bg){
        // Initializing attributes
        bgColor = bg;
        dim2LinesPanel = new Dimension(width,80);
        dimTextField = new Dimension(width-20,25);

        // Initializing main JPanel
        setLayout(new BorderLayout());
        setBackground(bgColor);

        // Initializing "options" JPanel
        optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBackground(bgColor);

        JScrollPane central = new JScrollPane(optionsPanel);
        central.setBorder(BorderFactory.createLineBorder(optionsPanel.getBackground()));
        central.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        central.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        central.setAlignmentY(JScrollPane.RIGHT_ALIGNMENT);

        add(central,BorderLayout.CENTER);
    }

    /**
     * Initializes a SimplePanel object with default configurations
     */
    public SimplePanel(){
        this(Colour.getBackgroundColor());
    }

    /**
     * Appends a text field and a description text into the options pane.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>info, var</b>: Changes on <i>Font</i> and <i>Foreground</i>.
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>
     * and <i>PreferredSize</i>
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param var String that can change if wanted. In such case it's recommended to
     * use a variable instead of using a "text in quotes"
     * @param field JTextField that will be added to this panel.
     * @return JPanel containing the mentioned elements but already implemented
     * in JComponents
     */
    protected JPanel addTextField(String info, String var, JTextField field){
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
            JLabel desc2 = new JLabel(var);
            desc2.setFont(desc1.getFont());
            desc2.setForeground(desc1.getForeground());
            description.add(desc2);
        }

        // Initializing the text field specified
        field.setFont(Typeface.labelPlain);
        field.setBackground(bgColor);
        field.setForeground(Colour.getFontColor());
        field.setPreferredSize(dimTextField);

        // Adding those panles to the "TextField" panel
        panel.add(description);
        panel.add(field);

        // Finally, adding this panel to the central panel
        optionsPanel.add(panel);

        return panel;
    }

    /**
     * Appends a text field and a description text into the options pane.
     * <p>
     * Note that this function will change the following properties to these
     * variables:
     * <p>
     * <b>info</b>: Changes on <i>Font</i> and <i>Foreground</i>.
     * <p>
     * <b>field</b>: Changes on <i>Font</i>, <i>Background</i>, <i>Foreground</i>
     * and <i>PreferredSize</i>
     * 
     * @param info Brief explanation of what should be filled into the text field
     * @param field JTextField that will be added to this panel.
     * @return JPanel containing the mentioned elements but already implemented
     * in JComponents
     */
    protected JPanel addTextField(String info, JTextField field){
        return addTextField(info, null, field);
    }
}
package util;

import javax.swing.JComponent;
//import javax.swing.BorderFactory;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * Panel which contains every important methods and components to
 * create a JPanel with options without difficulty
 */
@SuppressWarnings("serial")
public class SimplePanel extends JPanel{
    protected Color bgColor;
    protected GridBagConstraints location;
    
    //protected JPanel optionsPanel;

    /**
     * Initializes a SimplePanel object
     * 
     * @param bg Background color
     */
    public SimplePanel(Color bg){
        // Initializing attributes
        bgColor = bg;

        // Initializing main JPanel
        setLayout(new GridBagLayout());
        setBackground(bgColor);

        // Initializing "location" variable
        location = new GridBagConstraints();
        location.gridx = 0;
        location.gridy = 0;
        location.insets = Component.margin;
    }

    /**
     * Initializes a SimplePanel object with default configurations
     */
    public SimplePanel(){
        this(Colour.getBackgroundColor());
    }

    /**
     * Simply appends the given component into the center of the SimplePanel.
     * @param component JComponent (JPanel preferably) that will be entered
     * @return Position (index) of the given component appended into the
     * current SimplePanel.
     */
    public int add(JComponent component){
        // Adding component to SimplePanel
        add(component, location);

        // Returning the position of the current component and then increasing it
        return location.gridy++;
    }

    /**
     * Returns the number of components appended to the current SimplePanel.
     * @return Integer number of components appended. Also, it will be the
     * index of the next appended component.
     */
    public int amount(){
        return location.gridy;
    }
}
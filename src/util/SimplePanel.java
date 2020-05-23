package util;

import javax.swing.JComponent;
//import javax.swing.BorderFactory;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;

import java.awt.Color;
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
     * @return Total of components inside SimplePanel
     */
    public int add(JComponent component){
        // Adding component to SimplePanel
        add(component, location);

        // Returning and increasing the position of the next component
        return location.gridy++;
    }

    /*
     * Falta crear métodos para poder agregar las opciones o "componentes" a las
     * partes del panel (norte, sur, este y oeste), incluso al ScrollPane...
     */
}
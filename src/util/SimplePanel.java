/**
 * Completed-Games Registers, a software where you can record every
 * game you have beaten (completed) so far!
 * Copyright (C) 2020  Alejandro Batres
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * Contact by email: alejandro.batres37@gmail.com
 */

package util;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * <h3>SimplePanel utility class.</h3>
 * This class extends from {@link JPanel} and facilitates the way of
 * creating a panel without making extra steps.
 * 
 * @author Alejandro Batres
 */
@SuppressWarnings("serial")
public class SimplePanel extends JPanel{

    /**
     * Background color.
     */
    private Color bgColor;

    /**
     * Variable used to control the location of the entered
     * components.
     */
    private GridBagConstraints location;

    /**
     * Constructor of the SimplePanel class. It receives a
     * color that will be put in the background.
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
     * Constructor of the SimplePanel class. It will use
     * the return of {@link Colour#getBackgroundColor()}
     * as the background color.
     * 
     * @see Colour#getBackgroundColor()
     */
    public SimplePanel(){
        this(Colour.getBackgroundColor());
    }

    /**
     * Simply appends the given component into the center of the SimplePanel.
     * 
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
     * 
     * @return Integer number of components appended. Also, it will be the
     * index of the next appended component.
     */
    public int amount(){
        return location.gridy;
    }
}

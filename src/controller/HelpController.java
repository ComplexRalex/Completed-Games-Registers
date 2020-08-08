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

package controller;

import view.HelpPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import system.Software;
import util.Navigation;

/**
 * <h3>HelpController controller class.</h3>
 * This class implements the {@link ActionListener} interface
 * and is used to manage the actions of the visual components
 * of {@link HelpPanel}.
 * 
 * @author Alejandro Batres
 * @see HelpPanel
 */
public class HelpController implements ActionListener{

    /**
     * Parent controller.
     */
    private MainController parent;

    /**
     * Attached panel to the controller.
     */
    private HelpPanel view;

    /**
     * Constructor of the HelpController class.
     * 
     * @param v Set of visual components
     * @param p Parent controller
     */
    public HelpController(HelpPanel v, MainController p){
        view = v;
        parent = p;
    }

    /**
     * Sets listeners to all the visual "action" components
     * in the {@link #view}.
     */
    public void initialize(){
        view.btReportIssue.addActionListener(this);
        view.btReturn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btReturn)
            parent.frame.changePanel(parent.frame.pGeneral,null,0);
        else if(e.getSource() == view.btReportIssue){
            Navigation.goToPage(Software.ISSUES_PAGE, parent.frame);
        }
    }
}

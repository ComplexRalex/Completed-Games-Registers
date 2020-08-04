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

import java.awt.event.ActionListener;

import system.Software;
import util.Navigation;

import java.awt.event.ActionEvent;

import view.AboutPanel;

/**
 * <h3>AboutController controller class.</h3>
 * This class implements the {@link ActionListener} interface
 * and is used to manage the actions of the visual components
 * of {@link AboutPanel}.
 * 
 * @author Alejandro Batres
 * @see AboutPanel
 */
public class AboutController implements ActionListener{

    /**
     * Parent controller.
     */
    private MainController parent;

    /**
     * Attached panel to the controller.
     */
    private AboutPanel view;

    /**
     * Constructor of the AboutController class.
     * 
     * @param v Set of visual components
     * @param p Parent controller
     */
    public AboutController(AboutPanel v, MainController p){
        view = v;
        parent = p;
    }

    /**
     * Sets listeners to all the visual "action" components
     * in the {@link #view}.
     */
    public void initialize(){
        view.btTwitter.addActionListener(this);
        view.btSource.addActionListener(this);
        view.btJSONSource.addActionListener(this);
        view.btAPI.addActionListener(this);
        view.pLicense.btShowHide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.pLicense.show(!view.pLicense.visible());
            }
        });
        view.pRAWGTerms.btShowHide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.pRAWGTerms.show(!view.pRAWGTerms.visible());
            }
        });
        view.pJsonJavaLicense.btShowHide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.pJsonJavaLicense.show(!view.pJsonJavaLicense.visible());
            }
        });
        view.btReturn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btReturn)
            parent.frame.changePanel(parent.frame.pGeneral,null);
        else if(e.getSource() == view.btTwitter)
            Navigation.goToPage(Software.TWITTER_PROFILE, parent.frame);
        else if(e.getSource() == view.btSource)
            Navigation.goToPage(Software.SOURCE_CODE_PAGE, parent.frame);
        else if(e.getSource() == view.btJSONSource)
            Navigation.goToPage(Software.LIBRARY_DETAILS.get(Software.LIBRARY[0]).get("website_url"), parent.frame);
        else if(e.getSource() == view.btAPI)
            Navigation.goToPage(Software.API_DETAILS.get(Software.API[0]).get("website_url"), parent.frame);
    }
}

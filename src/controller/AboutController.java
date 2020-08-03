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

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import system.Software;
import util.Advice;
import util.Colour;
import util.Language;

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
            goToPage(Software.TWITTER_PROFILE);
        else if(e.getSource() == view.btSource)
            goToPage(Software.SOURCE_CODE_PAGE);
        else if(e.getSource() == view.btJSONSource)
            goToPage(Software.LIBRARY_DETAILS.get(Software.LIBRARY[0]).get("website_url"));
        else if(e.getSource() == view.btAPI)
            goToPage(Software.API_DETAILS.get(Software.API[0]).get("website_url"));
    }

    /**
     * Pops up a {@link Advice} dialog asking to open a
     * web page in the default configured browser.
     * 
     * @param url URL of the web page
     */
    private void goToPage(String url){
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            if(Advice.showOptionTextAreaAdvice(
                parent.frame,
                Language.loadMessage("g_message"),
                Language.loadMessage("g_will_browse"),
                url, 50, 2,
                new String[]{
                    Language.loadMessage("g_accept"),
                    Language.loadMessage("g_cancel")
                },
                Colour.getPrimaryColor()
            ) == 0){
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                    Advice.showTextAreaAdvice(
                        parent.frame,
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("g_wentworng")+": ",
                        Advice.getStackTrace(e), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                        Language.loadMessage("g_accept"),
                        Colour.getPrimaryColor()
                    );
                }
            }
        }else{
            Advice.showSimpleAdvice(
                parent.frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentworng"),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }
}

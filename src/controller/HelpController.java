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
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import system.Software;
import util.Advice;
import util.Colour;
import util.Language;

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
            parent.frame.changePanel(parent.frame.pGeneral,null);
        else if(e.getSource() == view.btReportIssue){
            if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                if(Advice.showOptionTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_message"),
                    Language.loadMessage("g_will_browse"),
                    Software.ISSUES_PAGE, 50, 2,
                    new String[]{
                        Language.loadMessage("g_accept"),
                        Language.loadMessage("g_cancel")
                    },
                    Colour.getPrimaryColor()
                ) == 0){
                    try {
                        Desktop.getDesktop().browse(new URI(Software.ISSUES_PAGE));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                        Advice.showTextAreaAdvice(
                            parent.frame,
                            Language.loadMessage("g_oops"),
                            Language.loadMessage("g_wentworng")+": ",
                            Advice.getStackTrace(e1), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
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
}

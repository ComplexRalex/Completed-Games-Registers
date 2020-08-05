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
import java.io.IOException;

import org.json.JSONException;

import model.GameData;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;
import util.Log;
import util.Navigation;
import view.ViewGamePanel;

import java.awt.event.ActionEvent;

/**
 * <h3>ViewGameController controller class.</h3>
 * This class implements the {@link ActionListener} interface
 * and is used to manage the actions of the visual components
 * of {@link ViewGamePanel}.
 * It uses a instance of {@link GameStat} to get its fields
 * and put them into the visual components.
 * <p>
 * If there is game information downloaded from RAWG's
 * database available, then a {@link GameData} instance will
 * be used to obtain such information.
 * 
 * @author Alejandro Batres
 * @see GameStat
 * @see GameData
 * @see ViewGamePanel
 */
public class ViewGameController implements ActionListener{
    
    /**
     * Parent controller.
     */
    private MainController parent;

    /**
     * Attached panel to the controller.
     */
    private ViewGamePanel view;

    /**
     * Game information provided by the user.
     */
    private GameStat actual;

    /**
     * Boolean which determines if there was
     * additional game information added.
     */
    private boolean loadedGameData;

    /**
     * Constructor of the ViewGameController class.
     * 
     * @param v Set of visual components
     * @param p Parent controller
     */
    public ViewGameController(ViewGamePanel v, MainController p){
        view = v;
        parent = p;
        loadedGameData = false;
    }

    /**
     * Sets listeners to all the visual "action" components
     * in the {@link #view}.
     */
    public void initialize(){
        view.btSpoiler.addActionListener(this);
        view.btReturn.addActionListener(this);
    }

    /**
     * Verifies the content of the fields to determine if the
     * components will be shown or will be hidden.
     * 
     * @param gs GameStat which contains game information
     * provided by the user
     */
    public void setInitialValues(GameStat gs){
        actual = gs;
        view.txtName.setText(actual.getGame());
        if(actual.getYear() > 0){
            view.pYear.setVisible(true);
            view.txtYear.setText(Integer.toString(actual.getYear()));
        }else{
            view.pYear.setVisible(false);
            view.txtYear.setText("");
        }
        view.pRate.setVisible(actual.getRate() != 0);
        view.txtRate.setText("\""+Language.loadMessage("ge_rate_"+actual.getRate())+"\"");
        if(!actual.getComment().trim().equals("")){
            view.pComment.setVisible(true);
            view.aComment.setText(actual.getComment());
        }else{
            view.pComment.setVisible(false);
            view.aComment.setText("");
        }
        if(!actual.getNote().trim().equals("")){
            view.pNote.setVisible(true);
            view.aNote.setText(actual.getNote());
        }else{
            view.pNote.setVisible(false);
            view.aNote.setText("");
        }
        if(!actual.getSpoiler().trim().equals("")){
            view.pSpoiler.setVisible(true);
            view.aSpoiler.setText(actual.getSpoiler());
        }else{
            view.pSpoiler.setVisible(false);
            view.aSpoiler.setText("");
        }
        view.viewSpoiler(false);

        // Creating a GameDataPanel and adding reference to RAWG's website.
        if(loadedGameData = actual.isInfoAvailable()){
            try{
                GameData gd = new GameData(actual.getGame());
                ViewGamePanel.GameDataPanel panel = view.addDatabaseInfo(gd);
                panel.btMoreDetails.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Navigation.goToPage("https://rawg.io/games/"+gd.getID(), parent.frame);
                    }
                });
			}catch(IOException | JSONException e){
                String error = Log.getDetails(e);
                Log.toFile(error, Log.ERROR);
				Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_went_wrong"),
                    error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
			}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btSpoiler){
            view.viewSpoiler(!view.spoilerVisible());
            if(view.spoilerVisible())
                view.btSpoiler.setText(Language.loadMessage("gv_hide_spoiler"));
            else
                view.btSpoiler.setText(Language.loadMessage("gv_show_spoiler"));
        }else{
            parent.frame.changePanel(parent.frame.pGeneral,null);
            if(loadedGameData) view.removeDatabaseInfo();
            loadedGameData = false;
            actual = null;
        }
    }
}

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

import model.GameData;
import model.GameRegister;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;
import util.Log;
import view.GeneralPanel;
import view.GeneralPanel.GameRegisterPanel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import java.awt.event.ActionEvent;

/**
 * <h3>GeneralController controller class.</h3>
 * This class implements the {@link ActionListener} interface
 * and is used to manage the actions of the visual components
 * of {@link GeneralPanel}.
 * <p>
 * It uses a instance of {@link GameRegister} which is used
 * to give logic functions to some of the visual components.
 * 
 * @author Alejandro Batres
 * @see GameRegister
 * @see GeneralPanel
 */
public class GeneralController implements ActionListener{

    /**
     * Parent controller.
     */
    private MainController parent;

    /**
     * Logic of the controller.
     */
    private GameRegister model;

    /**
     * Attached panel to the controller.
     */
    private GeneralPanel view;

    /**
     * HashMap which manages the created <b>completed-game
     * <i>registers</i></b> with its visual representation.
     * 
     * @see #add(GameStat, boolean)
     */
    private HashMap<GameStat,GameRegisterPanel> games;

    /**
     * Constructor of the GeneralController class.
     * 
     * @param m Logic of the controller
     * @param v Set of visual components
     * @param p Parent controller
     */
    public GeneralController(GameRegister m, GeneralPanel v, MainController p){
        model = m;
        view = v;
        parent = p;
        games = new HashMap<>();
    }

    /**
     * Sets listeners to all the visual "action" components
     * in the {@link #view}.
     * <p>
     * Also, it will call {@link #obtainInitialValues()}.
     * 
     * @see #obtainInitialValues()
     */
    public void initialize(){
        view.btAdd.addActionListener(this);
        view.btBackup.addActionListener(this);
        view.btExport.addActionListener(this);
        view.btHelp.addActionListener(this);
        view.btConfig.addActionListener(this);
        view.btAbout.addActionListener(this);

        obtainInitialValues();
    }

    /**
     * Initializes the visual components with the current
     * values retrieved from the {@link #model} object.
     * <p>
     * In this case, will set the username and will put
     * the stored register (if they exist). In case that
     * there are no registers entered, this will put a
     * "placeholder" panel instead.
     */
    public void obtainInitialValues(){
        view.lbUser.setText(parent.mConfig.getUsername());

        if(model.getGameStats().isEmpty())
            view.addPlaceHolder();

        for(GameStat gs: model.getGameStats())
            add(gs, false);
    }

    /**
     * Appends a new {@link GameRegisterPanel} to the
     * visual array of <b>completed-game <i>register</i></b>.
     * <p>
     * This method will put a {@link GameStat} object with
     * a new instance of {@link GameRegisterPanel} into
     * {@link #games}.
     * 
     * @param gs new {@link GameStat} object
     * @param recent Boolean which determines was created
     * recently ({@code true}) or not ({@code false})
     * @see #addActions(GameStat, boolean)
     */
    public void add(GameStat gs, boolean recent){
        if(games.isEmpty()) view.removePlaceHolder();
        model.addGameStat(gs);
        games.put(gs,view.new GameRegisterPanel(gs.getGame(), recent));
        view.addToCenter(games.get(gs));
        view.repaint();

        addActions(gs, recent);
    }

    /**
     * Sets the listeners to all the visual "action" components.
     * 
     * @param gs GameStat which will be referenced to these
     * components
     * @param recent Boolean which determines was created
     * recently ({@code true}) or not ({@code false})
     * @see #addActions(GameStat, boolean)
     */
    private void addActions(GameStat gs, boolean recent){
        GameRegisterPanel panel = games.get(gs);
        panel.btView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cViewGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pViewGame,parent.frame.pViewGame.scrollBar);
            }
        });
        panel.btEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cEditGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pEditGame,parent.frame.pEditGame.scrollBar);
            }
        });
        panel.btRemove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Advice.showOptionAdvice(
                    parent.frame,
                    Language.loadMessage("g_warning"),
                    Language.loadMessage("m_remove"),
                    new String[]{Language.loadMessage("g_accept"),Language.loadMessage("g_cancel")},
                    Colour.getPrimaryColor()
                ) == 0){
                    model.removeGameStat(gs);
                    GameData.deleteGameInfo(gs.getGame());
                    GameData.deleteGameImage(gs.getGame());

                    view.removeFromCenter(games.get(gs));
                    if(model.getGameStats().isEmpty())
                        view.addPlaceHolder();
                    view.validate();
                    view.repaint();

                    games.remove(gs);
                    parent.saveStats();
                }
            }
        });
        if(recent)
            panel.btRecent.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    Advice.showSimpleAdvice(
                        parent.frame,
                        Language.loadMessage("g_message"),
                        Language.loadMessage("m_text_recent"),
                        Language.loadMessage("g_accept"), 
                        Colour.getPrimaryColor()
                    );
                }
            });
    }

    /**
     * Updates the displayed title in the {@link GameRegisterPanel}
     * which is referenced to the {@link GameStat} object received.
     * 
     * @param gs GameStat which is referenced to some instance of
     * {@link GameRegisterPanel}
     */
    public void updateName(GameStat gs){
        games.get(gs).aName.setText(gs.getGame());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btAdd){
            parent.cEditGame.setInitialValues(null);
            parent.frame.changePanel(parent.frame.pEditGame,parent.frame.pEditGame.scrollBar);
        }else if(e.getSource() == view.btBackup){
            try {
                Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_success"),
                    Language.loadMessage("m_backedup"),
                    "Name of the backup file: "+parent.mGeneral.doBackup(), 40, 2,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
			} catch (IOException e1) {
                String error = Log.getDetails(e1);
                Log.toFile(error, Log.ERROR);
				Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_went_wrong")+": ",
                    error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }else if(e.getSource() == view.btExport){
            try {
                Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_success"),
                    Language.loadMessage("m_exported"),
                    "Name of the exported file: "+parent.mGeneral.exportStats(), 40, 2,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
			} catch (IOException e1) {
                String error = Log.getDetails(e1);
                Log.toFile(error, Log.ERROR);
				Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_went_wrong")+": ",
                    error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }else if(e.getSource() == view.btHelp){
            parent.frame.changePanel(parent.frame.pHelp,parent.frame.pHelp.scrollBar);
        }else if(e.getSource() == view.btConfig){
            parent.cConfig.obtainInitialConfig();
            parent.frame.changePanel(parent.frame.pConfig,parent.frame.pConfig.scrollBar);
        }else if(e.getSource() == view.btAbout){
            parent.frame.changePanel(parent.frame.pAbout,parent.frame.pAbout.scrollBar);
        }else{
            Advice.showSimpleAdvice(
                parent.frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_indev"),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }
}

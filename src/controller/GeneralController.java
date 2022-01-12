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
import java.util.ArrayList;
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
     * String that stores the last search (lol).
     */
    private String lastSearch;

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
        lastSearch = "";
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
        view.btSearch.addActionListener(this);
        view.btAdd.addActionListener(this);
        view.btBackup.addActionListener(this);
        view.btExport.addActionListener(this);
        view.btHelp.addActionListener(this);
        view.btConfig.addActionListener(this);
        view.btAbout.addActionListener(this);
        view.setSearchAvailable(true);
        view.showResultsCount(false);

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

        ArrayList<GameStat> gameStats = model.getGameStats();
        for(GameStat gs: gameStats)
            add(gs, false);

        showList();
    }

    /**
     * Adds a game register ({@link GameStat} object) to
     * the {@link #model}, and build the visual component
     * of that game register as well.
     * <p>
     * This panel is being added to the {@link #games}
     * HashMap.
     * 
     * @param gs Game register
     * @param recent Tells if the register has already been
     * added to the {@link #model} (<b>false</b>). If that's
     * the case, the game register won't be added.
     */
    public void add(GameStat gs, boolean recent){
        if(recent) model.addGameStat(gs);
        games.put(gs,view.new GameRegisterPanel(gs.getGame(), false));
        addActions(gs, false);
    }

    /**
     * Updates the visual list of the game registers. This
     * function is only called when either an element has
     * changed its name or one was added.
     * <p>
     * Note that this function removes all existing elements
     * from the view, found inside {@link #games} HashMap, and
     * re-enters them to the view, keeping the ordering of the
     * components.
     */
    public void updateList(){
        ArrayList<GameStat> gameStats = model.getGameStats();
        // view.setCount(model.getCount()); // Note that this is already done
        showStats(gameStats);
    }

    /**
     * Sets the title to look up into the list of registers.
     * This is used to save the last search in case the user
     * wants to open a register when searching, so the
     * external components can use it even if they don't know
     * what's going on.
     * 
     * @param search Title of the game to search
     */
    public void setSearch(String search){
        lastSearch = search;
    }

    /**
     * Updates the visual list of searched game registers.
     * This function is called in similar cases as
     * {@link #updateList()} and when the user request a search.
     * <p>
     * Note that this function removes all existing elements
     * from the view, found inside {@link #games} HashMap, and
     * re-enters them to the view, keeping the ordering of the
     * components.
     */
    public void showSearchResults(){
        ArrayList<GameStat> results = model.getGameStatsOccurrences(lastSearch);
        view.setSearchCount(results.size());
        showStats(results);
    }

    /**
     * Finally displays the list of {@link GameStat}s from
     * the given ArrayList.
     * 
     * @param stats Array list of game registers.
     */
    private void showStats(ArrayList<GameStat> stats){        
        view.removeAllFromCenter();
        if(!stats.isEmpty()){
            for(GameStat gs: stats)
                view.addToCenter(games.get(gs));
        }else{
            view.addPlaceHolder();
        }
        view.validate();
        view.repaint();
    }

    /**
     * Calls either {@link #updateList()} or
     * {@link #showSearchResults()}, depending on the current
     * search status of the search bar from {@link #view}.
     */
    public void showList(){
        view.setCount(model.getCount());
        if(view.isSearchAvailable())
            updateList();
        else
            showSearchResults();
    }

    /**
     * Sets the listeners to all the visual "action" components.
     * 
     * @param gs GameStat which will be referenced to these
     * components
     * @param recent Boolean which determines was created
     * recently ({@code true}) or not ({@code false})
     * (not in use anymore: use <b>false</b>)
     * @see #addActions(GameStat, boolean)
     */
    private void addActions(GameStat gs, boolean recent){
        GameRegisterPanel panel = games.get(gs);
        panel.btView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cViewGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pViewGame,parent.frame.pViewGame.scrollBar,0);
            }
        });
        panel.btEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.frame.setBusy(true);
                parent.cEditGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pEditGame,parent.frame.pEditGame.scrollBar,0);
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
                    remove(gs);
                    showList();
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
     * Remove {@link GameStat} instructions.
     * 
     * @param gs GameStat that will be deleted
     */
    public void remove(GameStat gs){
        model.removeGameStat(gs);
        GameData.deleteGameInfo(gs.getGame());
        GameData.deleteGameImage(gs.getGame());

        games.remove(gs);
        parent.saveStats();
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
        boolean yes;
        if(e.getSource() == view.btAdd){
            parent.frame.setBusy(true);
            parent.cEditGame.setInitialValues(null);
            parent.frame.changePanel(parent.frame.pEditGame,parent.frame.pEditGame.scrollBar,0);
        }else if(e.getSource() == view.btBackup){
            yes = true;
            if(parent.mConfig.getConfirmBackupDialog()){
                yes = Advice.showOptionAdvice(
                    parent.frame,
                    Language.loadMessage("g_message"),
                    Language.loadMessage("m_confirm_backup"),
                    new String[]{
                        Language.loadMessage("g_accept"),
                        Language.loadMessage("g_cancel")
                    },
                    Colour.getPrimaryColor()
                ) == 0;
            }
            if(yes){
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
            }
        }else if(e.getSource() == view.btExport){
            yes = true;
            if(parent.mConfig.getConfirmExportDialog()){
                yes = Advice.showOptionAdvice(
                    parent.frame,
                    Language.loadMessage("g_message"),
                    Language.loadMessage("m_confirm_export"),
                    new String[]{
                        Language.loadMessage("g_accept"),
                        Language.loadMessage("g_cancel")
                    },
                    Colour.getPrimaryColor()
                ) == 0;
            }
            if(yes){
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
            }
        }else if(e.getSource() == view.btHelp){
            parent.frame.changePanel(parent.frame.pHelp,parent.frame.pHelp.scrollBar,0);
        }else if(e.getSource() == view.btConfig){
            parent.frame.setBusy(true);
            parent.cConfig.obtainInitialConfig();
            parent.frame.changePanel(parent.frame.pConfig,parent.frame.pConfig.scrollBar,0);
        }else if(e.getSource() == view.btAbout){
            parent.frame.changePanel(parent.frame.pAbout,parent.frame.pAbout.scrollBar,0);
        }else if(e.getSource() == view.btSearch){
            if(view.isSearchAvailable()){
                String search = view.txtSearch.getText().trim();
                if(search.length() > 0){
                    setSearch(search);
                    view.setSearchAvailable(false);
                    view.showResultsCount(true);
                    showList();
                }else{
                    Advice.showSimpleAdvice(
                        parent.frame,
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("m_no_search"),
                        Language.loadMessage("g_accept"),
                        Colour.getPrimaryColor()
                    );
                }
            }else{
                view.setSearchAvailable(true);
                view.txtSearch.setText("");
                view.showResultsCount(false);
                showList();
            }
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

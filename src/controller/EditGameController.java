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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JTextField;

import org.json.JSONException;

import java.awt.event.ActionEvent;

import view.EditGamePanel;
import model.GameData;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;

/**
 * <h3>EditGameController controller class.</h3>
 * This class implements the {@link ActionListener} and
 * {@link KeyListener} interfaces and is used to manage
 * the actions of the visual components of {@link EditGamePanel}.
 * It uses a instance of {@link GameStat} to get its fields
 * and put them into the visual components.
 * <p>
 * If the user wants to download information about the game,
 * then the {@link GameData#downloadGameInfo(String)} method
 * will be used.
 * 
 * @author Alejandro Batres
 * @see GameStat
 * @see GameData
 * @see EditGamePanel
 */
public class EditGameController implements ActionListener, KeyListener{

    /**
     * Parent controller.
     */
    private MainController parent;

    /**
     * Attached panel to the controller.
     */
    private EditGamePanel view;

    /**
     * Game information provided by the user (if it exists).
     */
    private GameStat actual;

    /**
     * Last name of the recent game information downloaded.
     * 
     * @see #sameValues()
     */
    private String downloaded;

    /**
     * First value of the name of the game.
     * 
     * @see #sameValues()
     */
    private String oldName;

    /**
     * First name of the game information downloaded.
     * 
     * @see #sameValues()
     */
    private String oldInfoName;

    /**
     * First value of the year of completion.
     * 
     * @see #sameValues()
     */
    private int oldYear;

    /**
     * First value of the rate of the game.
     * 
     * @see #sameValues()
     */
    private int oldRate;

    /**
     * First value of the comments about the game.
     * 
     * @see #sameValues()
     */
    private String oldComment;

    /**
     * First value of the annotations of the games.
     * 
     * @see #sameValues()
     */
    private String oldNote;

    /**
     * First value of the spoilers about the game.
     * 
     * @see #sameValues()
     */
    private String oldSpoiler;

    /**
     * Constructor of the EditGameController class.
     * 
     * @param v Set of visual components
     * @param p Parent controller
     */
    public EditGameController(EditGamePanel v, MainController p){
        view = v;
        parent = p;
    }

    /**
     * Sets listeners to all the visual "action" components
     * in the {@link #view}.
     */
    public void initialize(){
        view.btDownload.addActionListener(this);
        view.btDelete.addActionListener(this);
        view.btCreate.addActionListener(this);
        view.btChange.addActionListener(this);
        view.btCancel.addActionListener(this);

        view.txtYear.addKeyListener(this);
    }

    /**
     * Verifies the content of the fields to determine if
     * there is information to put into the respective
     * components, or let them empty.
     * <p>
     * Also, note that if {@code initial == null}, then the
     * visual fields will be empty by default.
     * 
     * @param initial GameStat which contains game information
     * provided by the user
     */
    public void setInitialValues(GameStat initial){
        actual = initial;
        if(actual != null){
            view.txtName.setText(actual.getGame());
            if(actual.isInfoAvailable()){
                downloaded = actual.getGame();
                view.btDelete.setEnabled(true);
            }else{
                downloaded = null;
                view.btDelete.setEnabled(false);
            }
            view.txtYear.setText(actual.getYear() <= -1 ? "" : Integer.toString(actual.getYear()));
            view.btRate[actual.getRate()].setSelected(true);
            view.aComment.setText(actual.getComment());
            view.aNote.setText(actual.getNote());
            view.aSpoiler.setText(actual.getSpoiler());
            
            view.btCreate.setEnabled(false);
            view.btCreate.setVisible(false);
            view.btChange.setEnabled(true);
            view.btChange.setVisible(true);
        }else{
            view.txtName.setText("");
            downloaded = null;
            view.btDelete.setEnabled(false);
            view.txtYear.setText("");
            view.btRate[0].setSelected(true);
            view.aComment.setText("");
            view.aNote.setText("");
            view.aSpoiler.setText("");

            view.btCreate.setEnabled(true);
            view.btCreate.setVisible(true);
            view.btChange.setEnabled(false);
            view.btChange.setVisible(false);
        }
        oldName = view.txtName.getText().trim();
        oldInfoName = downloaded;
        oldYear = Integer.parseInt("".equals(view.txtYear.getText().trim()) ? "-1" : view.txtYear.getText().trim());
        for(int i = 0; i < GameStat.RATE_OPTIONS; i++)
            if(view.btRate[i].isSelected()){ oldRate = i; break; }
        oldComment = view.aComment.getText();
        oldNote = view.aNote.getText();
        oldSpoiler = view.aSpoiler.getText();
    }

    /**
     * Tells if there were not changes made in the
     * current session.
     * 
     * @see #oldName
     * @see #oldInfoName
     * @see #downloaded
     * @see #oldYear
     * @see #oldRate
     * @see #oldComment
     * @see #oldNote
     * @see #oldSpoiler
     * @return {@code true} if the values are the
     * same as at the beginning. {@code false} otherwise.
     */
    private boolean sameValues(){
        boolean flag = true;
        flag = (flag && oldName.equals(view.txtName.getText().trim()));
        try{
            flag = (flag && oldInfoName.equals(downloaded));
        }catch(NullPointerException e){
            flag = (flag && null == downloaded);
        }
        flag = (flag && (oldYear == Integer.parseInt("".equals(view.txtYear.getText().trim()) ? "-1" : view.txtYear.getText().trim())));
        for(int i = 0; i < GameStat.RATE_OPTIONS; i++)
            if(view.btRate[i].isSelected()){ flag = (flag && (oldRate == i)); break; }
        flag = (flag && oldComment.equals(view.aComment.getText()));
        flag = (flag && oldNote.equals(view.aNote.getText()));
        flag = (flag && oldSpoiler.equals(view.aSpoiler.getText()));

        return flag;
    }

    /**
     * Calls {@link GameData#downloadGameInfo(String)} and
     * {@link GameData#downloadGameImage(String)} methods to 
     * obtain information about the game described in
     * {@link EditGamePanel#txtName}.
     */
    public void downloadGameInfo(){
        try{
            if(GameData.downloadGameInfo(view.txtName.getText().trim())){
                Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_success"),
                    Language.loadMessage("ge_success_download"),
                    "The name of the downloaded game information was: "+(new GameData(view.txtName.getText().trim())).getName(), 40, 2,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
                view.btDelete.setEnabled(true);
                downloaded = view.txtName.getText().trim();
                try{
                    GameData.downloadGameImage(view.txtName.getText().trim());
                }catch(IOException | JSONException e){
                    Advice.showTextAreaAdvice(
                        parent.frame,
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("g_went_wrong")+": ",
                        Advice.getStackTrace(e), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                        Language.loadMessage("g_accept"),
                        Colour.getPrimaryColor()
                    );
                }
            }else{
                Advice.showSimpleAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("ge_fail_download"),
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }catch(IOException | JSONException | URISyntaxException e){
            Advice.showTextAreaAdvice(
                parent.frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_went_wrong")+": ",
                Advice.getStackTrace(e), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }

    /**
     * Calls {@link GameData#deleteGameInfo(String)} and
     * {@link GameData#deleteGameImage(String)} methods to
     * delete the lastest game information downloaded
     * ({@link #downloaded}'s value).
     */
    public void deleteGameInfo(){
        view.btDelete.setEnabled(!GameData.deleteGameInfo(downloaded));
        GameData.deleteGameImage(downloaded);
        downloaded = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btCancel){
            if(sameValues())
                parent.frame.changePanel(parent.frame.pGeneral,null);
            else if(Advice.showOptionAdvice(
                parent.frame,
                Language.loadMessage("g_warning"),
                Language.loadMessage("g_unsaved"),
                new String[]{
                    Language.loadMessage("g_accept"),
                    Language.loadMessage("g_cancel")
                },
                Colour.getPrimaryColor()
            ) == 0){
                if(oldInfoName != downloaded){
                    if(downloaded != null) deleteGameInfo();
                    if(oldInfoName != null){
                        view.txtName.setText(oldInfoName);
                        downloadGameInfo();
                    }
                }
                parent.frame.changePanel(parent.frame.pGeneral,null);
            }
        }else{
            String game = view.txtName.getText().trim();
            if(parent.mGeneral.getGameStat(game) == null || parent.mGeneral.getGameStat(game).equals(actual)){
                
                if(game.length() > 0){

                    if(e.getSource() == view.btDownload){
                        if(!game.equals(downloaded)){
                            if(downloaded != null)
                                deleteGameInfo();
                            downloadGameInfo();
                        }else{
                            Advice.showSimpleAdvice(
                                parent.frame,
                                Language.loadMessage("g_message"),
                                Language.loadMessage("ge_downloaded"),
                                Language.loadMessage("g_accept"),
                                Colour.getPrimaryColor()
                            );
                        }
                    }else if(e.getSource() == view.btDelete && downloaded != null)
                        deleteGameInfo();
                    else if(e.getSource() == view.btCreate || e.getSource() == view.btChange){
                        int year;
                        try{
                            year = Integer.parseInt(view.txtYear.getText().trim());
                        }catch(NumberFormatException e1){
                            year = -1;
                        }
                        int rate = 0;
                        for(int i = 0; i < GameStat.RATE_OPTIONS; i++)
                            if(view.btRate[i].isSelected()){ rate = i; break; }
                        String comment = view.aComment.getText();
                        String note = view.aNote.getText();
                        String spoiler = view.aSpoiler.getText();
                        
                        // This is in case the user has changed the name of the game (just) after downloading the game info
                        if(downloaded != null && !game.equals(downloaded)){
                            Advice.showSimpleAdvice(
                                parent.frame,
                                Language.loadMessage("g_message"),
                                Language.loadMessage("ge_update_data"),
                                Language.loadMessage("g_accept"),
                                Colour.getPrimaryColor()
                            );
                            deleteGameInfo();
                            downloadGameInfo();
                        }
                        
                        // Determines which button was pressed.
                        if(e.getSource() == view.btCreate){
                            parent.cGeneral.add(
                                new GameStat(game,year,rate,comment,note,spoiler),
                                true
                            );
                        }else if(e.getSource() == view.btChange){
                            actual.setGame(game);
                            actual.setYear(year);
                            actual.setRate(rate);
                            actual.setComment(comment);
                            actual.setNote(note);
                            actual.setSpoiler(spoiler);
                            parent.cGeneral.updateName(actual);
                        }

                        // Finally, saves the data (only registers) and changes panel
                        parent.saveStats();
                        parent.frame.changePanel(parent.frame.pGeneral,null);
                        actual = null;
                    }
                }else{
                    Advice.showSimpleAdvice(
                        parent.frame, 
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("ge_no_game"),
                        Language.loadMessage("g_return"),
                        Colour.getPrimaryColor()
                    );
                }
            }else{
                Advice.showSimpleAdvice(
                    parent.frame, 
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("ge_exists"),
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
        if((e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) || ((JTextField)e.getSource()).getText().length() >= 4)
            e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}

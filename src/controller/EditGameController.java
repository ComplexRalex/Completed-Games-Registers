package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JTextField;

import org.json.simple.parser.ParseException;

import java.awt.event.ActionEvent;

import view.EditGamePanel;
import model.GameData;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;
import util.Path;

public class EditGameController implements ActionListener, KeyListener{

    private MainController parent;
    private EditGamePanel view;
    private GameStat actual;
    private String downloaded;

    private String oldName;
    private String oldInfoName;
    private int oldYear;
    private int oldRate;
    private String oldComment;
    private String oldNote;
    private String oldSpoiler;

    public EditGameController(EditGamePanel v, MainController p){
        view = v;
        parent = p;
    }

    public void initialize(){
        view.btDownload.addActionListener(this);
        view.btDelete.addActionListener(this);
        view.btCreate.addActionListener(this);
        view.btChange.addActionListener(this);
        view.btCancel.addActionListener(this);

        view.txtYear.addKeyListener(this);
    }

    public void setInitialValues(GameStat initial){
        actual = initial;
        if(actual != null){
            view.txtName.setText(actual.getGame());
            if((new File(Path.gameInfo+Path.validFileName(actual.getGame(),"json"))).exists()){
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

    public void downloadGameInfo(){
        try{
            Path.resolve(Path.gameInfo);
            if(GameData.downloadGameInfo(view.txtName.getText().trim())){
                Advice.showTextAreaAdvice(
                    view,
                    Language.loadMessage("g_success"),
                    Language.loadMessage("ge_success_download"),
                    "The name of the downloaded game information was: "+(new GameData(view.txtName.getText().trim())).getName(),
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
                view.btDelete.setEnabled(true);
                downloaded = view.txtName.getText().trim();
                try{
                    Path.resolve(Path.gameImage);
                    GameData.downloadGameImage(view.txtName.getText().trim());
                }catch(IOException | ParseException e){
                    Advice.showTextAreaAdvice(
                        view,
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("g_wentwrong")+": ",
                        e.toString()+" (Not URL image provided).",
                        Language.loadMessage("g_accept"),
                        Colour.getPrimaryColor()
                    );
                }
            }else{
                Advice.showSimpleAdvice(
                    view,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("ge_fail_download"),
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }catch(IOException | ParseException | URISyntaxException e){
            Advice.showTextAreaAdvice(
                view,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong")+": ",
                Advice.getStackTrace(e), Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }

    public void deleteGameInfo(){
        Path.resolve(Path.gameInfo);
        view.btDelete.setEnabled(!GameData.deleteGameInfo(downloaded));
        Path.resolve(Path.gameImage);
        GameData.deleteGameImage(downloaded);
        downloaded = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btCancel){
            if(sameValues())
                parent.frame.changePanel(parent.frame.pGeneral);
            else if(Advice.showOptionAdvice(
                view,
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
                parent.frame.changePanel(parent.frame.pGeneral);
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
                                view,
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
                                view,
                                Language.loadMessage("g_message"),
                                Language.loadMessage("ge_update_data"),
                                Language.loadMessage("g_accept"),
                                Colour.getPrimaryColor()
                            );
                            deleteGameInfo();
                            downloadGameInfo();
                        }
                        
                        if(e.getSource() == view.btCreate){
                            parent.cGeneral.add(
                                new GameStat(game,year,rate,comment,note,spoiler)
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
                        parent.frame.changePanel(parent.frame.pGeneral);
                    }
                    
                }else{
                    Advice.showSimpleAdvice(
                        view, 
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("ge_no_game"),
                        Language.loadMessage("g_return"),
                        Colour.getPrimaryColor()
                    );
                }
            }else{
                Advice.showSimpleAdvice(
                    view, 
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
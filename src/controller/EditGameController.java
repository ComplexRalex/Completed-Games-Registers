package controller;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import java.awt.event.ActionEvent;

import view.EditGamePanel;
import model.GameData;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;
import util.Path;

public class EditGameController implements ActionListener{

    private MainController parent;
    private EditGamePanel view;
    private GameStat actual;
    private String downloaded;

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
    }

    public void setInitialValues(GameStat initial){
        if(initial != null){
            actual = initial;
            view.txtName.setText(initial.getGame());
            if((new File(Path.gameInfo+Path.validFileName(initial.getGame(),".json"))).exists()){
                downloaded = initial.getGame();
                view.btDelete.setEnabled(true);
            }
            view.txtYear.setText(Integer.toString(initial.getYear()));
            view.btRate[initial.getRate()].setSelected(true);
            view.aComment.setText(initial.getComment());
            view.aNote.setText(initial.getNote());
            view.aSpoiler.setText(initial.getSpoiler());
            
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
    }

    public void downloadGameInfo(){
        try{
            System.out.println("Downloaded info ("+view.txtName.getText().trim()+"): "+GameData.downloadGameInfo(view.txtName.getText().trim()));
            System.out.println("Downloaded image ("+view.txtName.getText().trim()+"): "+GameData.downloadGameImage(view.txtName.getText().trim()));
            view.btDelete.setEnabled(true);
            downloaded = view.txtName.getText().trim();
        }catch(IOException | org.json.simple.parser.ParseException | URISyntaxException e){
            Advice.showTextAreaAdvice(
                view,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong")+": ",
                e.toString(), Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }

    public void deleteGameInfo(){
        System.out.println("Deleted info: ("+downloaded+"): "+GameData.deleteGameInfo(downloaded));
        System.out.println("Deleted image: ("+downloaded+"): "+GameData.deleteGameImage(downloaded));
        view.btDelete.setEnabled(false);
        downloaded = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btCancel) parent.frame.changePanel(parent.frame.pGeneral);
        else{
            if(view.txtName.getText().trim().length() > 0){

                if(e.getSource() == view.btDownload){
                    if(!view.txtName.getText().trim().equals(downloaded)){
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

                    String game = view.txtName.getText().trim();
                    int year;
                    try{
                        year = Integer.parseInt(view.txtYear.getText());
                    }catch(NumberFormatException e1){
                        year = -1;
                    }
                    int rate = 0;
                    String comment = view.aComment.getText();
                    String note = view.aNote.getText();
                    String spoiler = view.aSpoiler.getText();

                    // This is in case the user has changed the name of the game (just) after downloading the game info
                    if(downloaded != null && !view.txtName.getText().trim().equals(downloaded)){
                        Advice.showSimpleAdvice(
                            view,
                            Language.loadMessage("g_message"),
                            Language.loadMessage("ge_update_data"),
                            Language.loadMessage("g_accept"),
                            Colour.getPrimaryColor()
                        );
                        downloadGameInfo();
                    }
                    
                    for(int i = 0; i < GameStat.RATE_OPTIONS; i++)
                        if(view.btRate[i].isSelected()){ rate = i; break; }
                    
                    if(e.getSource() == view.btCreate){
                        if(parent.mGeneral.getGameStat(game) == null){
                            parent.cGeneral.add(
                                new GameStat(game,year,rate,comment,note,spoiler)
                            );
                            parent.frame.changePanel(parent.frame.pGeneral);
                        }else{
                            Advice.showSimpleAdvice(
                                view, 
                                Language.loadMessage("g_oops"),
                                Language.loadMessage("ge_exists"),
                                Language.loadMessage("g_accept"),
                                Colour.getPrimaryColor()
                            );
                        }
                    }else if(e.getSource() == view.btChange){
                        actual.setGame(game);
                        actual.setYear(year);
                        actual.setRate(rate);
                        actual.setComment(comment);
                        actual.setNote(note);
                        actual.setSpoiler(spoiler);
                        parent.cGeneral.updateName(actual);
                        parent.frame.changePanel(parent.frame.pGeneral);
                    }
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
        }
    }
}
package controller;

import java.awt.event.ActionListener;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import model.GameData;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;
import view.ViewGamePanel;

import java.awt.event.ActionEvent;

public class ViewGameController implements ActionListener{
    
    private MainController parent;
    private ViewGamePanel view;
    private GameStat actual;

    public ViewGameController(ViewGamePanel v, MainController p){
        view = v;
        parent = p;
    }

    public void initialize(){
        view.btSpoiler.addActionListener(this);
        view.btReturn.addActionListener(this);
    }

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

        if(actual.isInfoAvailable()){
            try{
                view.addDatabaseInfo(new GameData(actual.getGame()));
			}catch(IOException | ParseException e){
				Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_went_wrong"),
                    Advice.getStackTrace(e), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
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
            parent.frame.changePanel(parent.frame.pGeneral);
            if(actual.isInfoAvailable()) view.removeDatabaseInfo();
        }

    }
}
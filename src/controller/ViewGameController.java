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
        view.txtYear.setText(actual.getYear() <= -1 ? Language.loadMessage("g_noinfo") : Integer.toString(actual.getYear()));
        view.txtRate.setText("\""+Language.loadMessage("ge_rate_"+actual.getRate())+"\"");
        view.aComment.setText(actual.getComment().trim().equals("") ? Language.loadMessage("g_noinfo") : actual.getComment());
        view.aNote.setText(actual.getNote().trim().equals("") ? Language.loadMessage("g_noinfo") : actual.getNote());
        view.aSpoiler.setText(actual.getSpoiler().trim().equals("") ? Language.loadMessage("g_noinfo") : actual.getSpoiler());
        view.viewSpoiler(false);

        if(actual.isInfoAvailable()){
            try{
                view.addDatabaseInfo(new GameData(actual.getGame()));
			}catch(IOException | ParseException e){
				Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong"), e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
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
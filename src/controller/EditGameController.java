package controller;

import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import view.EditGamePanel;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;

public class EditGameController implements ActionListener{

    private MainController parent;
    private EditGamePanel view;
    private GameStat actual;

    public EditGameController(EditGamePanel v, MainController p){
        view = v;
        parent = p;
    }

    public void initialize(){
        view.btDownload.addActionListener(this);
        view.btDelete.addActionListener(this);
        for(JRadioButton bt: view.btRate){
            bt.addActionListener(this);
        }
        view.btCreate.addActionListener(this);
        view.btChange.addActionListener(this);
        view.btCancel.addActionListener(this);
    }

    public void setInitialValues(GameStat initial){
        if(initial != null){
            actual = initial;
            view.txtName.setText(initial.getGame());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btCancel) parent.frame.changePanel(parent.frame.pConfig);
        else Advice.showSimpleAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_indev"), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
    }
}
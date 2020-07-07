package controller;

import model.Configuration;
import model.GameRegister;
import util.Advice;
import util.Colour;
import util.Language;
import view.GeneralPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneralController implements ActionListener{

    private MainController parent;
    private GameRegister model;
    private GeneralPanel view;

    public GeneralController(GameRegister m, GeneralPanel v, MainController p){
        model = m;
        view = v;
        parent = p;
    }

    public void initialize(){
        view.btAdd.addActionListener(this);
        view.btConfig.addActionListener(this);
        view.btAbout.addActionListener(this);

        obtainInitialValues();
    }

    public void obtainInitialValues(){
        view.lbUser.setText(Configuration.getUsername());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btAdd){
            parent.frame.changePanel(parent.frame.pEditGame);
            parent.cEditGame.setInitialValues(null);
        }else if(e.getSource() == view.btConfig){
            parent.frame.changePanel(parent.frame.pConfig);
        }else{
            Advice.showSimpleAdvice(null,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_indev"),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }
}

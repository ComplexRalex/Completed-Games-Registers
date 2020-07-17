package controller;

import view.HelpPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpController implements ActionListener{

    private MainController parent;
    private HelpPanel view;

    public HelpController(HelpPanel v, MainController p){
        view = v;
        parent = p;
    }

    public void initialize(){
        view.btReturn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btReturn)
            parent.frame.changePanel(parent.frame.pGeneral);
    }
}
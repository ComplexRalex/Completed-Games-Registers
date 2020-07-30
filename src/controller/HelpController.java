package controller;

import view.HelpPanel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import system.Software;
import util.Advice;
import util.Colour;
import util.Language;

public class HelpController implements ActionListener{

    private MainController parent;
    private HelpPanel view;

    public HelpController(HelpPanel v, MainController p){
        view = v;
        parent = p;
    }

    public void initialize(){
        view.btReportIssue.addActionListener(this);
        view.btReturn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btReturn)
            parent.frame.changePanel(parent.frame.pGeneral,null);
        else if(e.getSource() == view.btReportIssue){
            if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                if(Advice.showOptionTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_message"),
                    Language.loadMessage("g_will_browse"),
                    Software.ISSUES_PAGE, 50, 2,
                    new String[]{
                        Language.loadMessage("g_accept"),
                        Language.loadMessage("g_cancel")
                    },
                    Colour.getPrimaryColor()
                ) == 0){
                    try {
                        Desktop.getDesktop().browse(new URI(Software.ISSUES_PAGE));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                        Advice.showTextAreaAdvice(
                            parent.frame,
                            Language.loadMessage("g_oops"),
                            Language.loadMessage("g_wentworng")+": ",
                            Advice.getStackTrace(e1), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                            Language.loadMessage("g_accept"),
                            Colour.getPrimaryColor()
                        );
                    }
                }
            }else{
                Advice.showSimpleAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_wentworng"),
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }
    }
}
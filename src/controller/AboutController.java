package controller;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import system.Software;
import util.Advice;
import util.Colour;
import util.Language;

import java.awt.event.ActionEvent;

import view.AboutPanel;

public class AboutController implements ActionListener{

    private MainController parent;
    private AboutPanel view;

    public AboutController(AboutPanel v, MainController p){
        view = v;
        parent = p;
    }

    public void initialize(){
        view.btTwitter.addActionListener(this);
        view.btSource.addActionListener(this);
        view.btJSONSource.addActionListener(this);
        view.btAPI.addActionListener(this);
        view.pLicense.btShowHide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.pLicense.show(!view.pLicense.visible());
            }
        });
        view.pRAWGTerms.btShowHide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.pRAWGTerms.show(!view.pRAWGTerms.visible());
            }
        });
        view.pJsonJavaLicense.btShowHide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.pJsonJavaLicense.show(!view.pJsonJavaLicense.visible());
            }
        });
        view.btReturn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btReturn)
            parent.frame.changePanel(parent.frame.pGeneral,null);
        else if(e.getSource() == view.btTwitter)
            goToPage("https://twitter.com/"+Software.DEVELOPER_USERNAMES[1]);
        else if(e.getSource() == view.btSource)
            goToPage(Software.SOURCE_CODE_PAGE);
        else if(e.getSource() == view.btJSONSource)
            goToPage(Software.LIBRARY_DETAILS.get(Software.LIBRARY[0]).get("website_url"));
        else if(e.getSource() == view.btAPI)
            goToPage(Software.API_DETAILS.get(Software.API[0]).get("website_url"));
    }

    private void goToPage(String url){
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            if(Advice.showOptionTextAreaAdvice(
                parent.frame,
                Language.loadMessage("g_message"),
                Language.loadMessage("g_will_browse"),
                url, 50, 2,
                new String[]{
                    Language.loadMessage("g_accept"),
                    Language.loadMessage("g_cancel")
                },
                Colour.getPrimaryColor()
            ) == 0){
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                    Advice.showTextAreaAdvice(
                        parent.frame,
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("g_wentworng")+": ",
                        Advice.getStackTrace(e), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
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
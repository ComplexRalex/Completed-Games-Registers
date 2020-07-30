package controller;

import model.GameData;
import model.GameRegister;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;
import view.GeneralPanel;
import view.GeneralPanel.GameRegisterPanel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import java.awt.event.ActionEvent;

public class GeneralController implements ActionListener{

    private MainController parent;
    private GameRegister model;
    private GeneralPanel view;
    private HashMap<GameStat,GameRegisterPanel> games;

    public GeneralController(GameRegister m, GeneralPanel v, MainController p){
        model = m;
        view = v;
        parent = p;
        games = new HashMap<>();
    }

    public void initialize(){
        view.btAdd.addActionListener(this);
        view.btBackup.addActionListener(this);
        view.btExport.addActionListener(this);
        view.btHelp.addActionListener(this);
        view.btConfig.addActionListener(this);
        view.btAbout.addActionListener(this);

        obtainInitialValues();
    }

    public void obtainInitialValues(){
        view.lbUser.setText(parent.mConfig.getUsername());

        if(model.getGameStats().isEmpty())
            view.addPlaceHolder();

        for(GameStat gs: model.getGameStats())
            add(gs, false);
    }

    public void add(GameStat gs, boolean recent){
        if(games.isEmpty()) view.removePlaceHolder();
        model.addGameStat(gs);
        games.put(gs,view.new GameRegisterPanel(gs.getGame(), recent));
        view.addToCenter(games.get(gs));
        view.repaint();

        addActions(gs, recent);
    }

    private void addActions(GameStat gs, boolean recent){
        GameRegisterPanel panel = games.get(gs);
        panel.btView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cViewGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pViewGame,parent.frame.pViewGame.scrollBar);
            }
        });
        panel.btEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cEditGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pEditGame,parent.frame.pEditGame.scrollBar);
            }
        });
        panel.btRemove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Advice.showOptionAdvice(
                    parent.frame,
                    Language.loadMessage("g_warning"),
                    Language.loadMessage("m_remove"),
                    new String[]{Language.loadMessage("g_accept"),Language.loadMessage("g_cancel")},
                    Colour.getPrimaryColor()
                ) == 0){
                    model.removeGameStat(gs);
                    GameData.deleteGameInfo(gs.getGame());
                    GameData.deleteGameImage(gs.getGame());

                    view.removeFromCenter(games.get(gs));
                    if(model.getGameStats().isEmpty())
                        view.addPlaceHolder();
                    view.validate();
                    view.repaint();

                    games.remove(gs);
                    parent.saveStats();
                }
            }
        });
        if(recent)
            panel.btRecent.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    Advice.showSimpleAdvice(
                        parent.frame,
                        Language.loadMessage("g_message"),
                        Language.loadMessage("m_text_recent"),
                        Language.loadMessage("g_accept"), 
                        Colour.getPrimaryColor()
                    );
                }
            });
    }

    public void updateName(GameStat gs){
        games.get(gs).aName.setText(gs.getGame());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btAdd){
            parent.cEditGame.setInitialValues(null);
            parent.frame.changePanel(parent.frame.pEditGame,parent.frame.pEditGame.scrollBar);
        }else if(e.getSource() == view.btBackup){
            try {
                Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_success"),
                    Language.loadMessage("m_backedup"),
                    "Name of the backup file: "+parent.mGeneral.doBackup(), 40, 2,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
			} catch (IOException e1) {
				Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_went_wrong")+": ",
                    Advice.getStackTrace(e1), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }else if(e.getSource() == view.btExport){
            try {
                Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_success"),
                    Language.loadMessage("m_exported"),
                    "Name of the exported file: "+parent.mGeneral.exportStats(), 40, 2,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
			} catch (IOException e1) {
				Advice.showTextAreaAdvice(
                    parent.frame,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_went_wrong")+": ",
                    Advice.getStackTrace(e1), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }else if(e.getSource() == view.btHelp){
            parent.frame.changePanel(parent.frame.pHelp,parent.frame.pHelp.scrollBar);
        }else if(e.getSource() == view.btConfig){
            parent.cConfig.obtainInitialConfig();
            parent.frame.changePanel(parent.frame.pConfig,parent.frame.pConfig.scrollBar);
        }else if(e.getSource() == view.btAbout){
            parent.frame.changePanel(parent.frame.pAbout,parent.frame.pAbout.scrollBar);
        }else{
            Advice.showSimpleAdvice(
                parent.frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_indev"),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }
}

package controller;

import model.Configuration;
import model.GameData;
import model.GameRegister;
import model.GameStat;
import util.Advice;
import util.Colour;
import util.Language;
import view.GeneralPanel;
import view.GeneralPanel.GameRegisterPanel;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;

import exception.CouldNotBackupFileException;

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
        view.lbUser.setText(Configuration.getUsername());

        if(model.getGameStats().isEmpty())
            view.addPlaceHolder();

        for(GameStat gs: model.getGameStats())
            add(gs);
    }

    public void add(GameStat gs){
        if(games.isEmpty()) view.removePlaceHolder();
        model.addGameStat(gs);
        games.put(gs,view.new GameRegisterPanel(gs.getGame()));
        view.addToCenter(games.get(gs));
        view.repaint();

        addActions(gs);
    }

    private void addActions(GameStat gs){
        GameRegisterPanel panel = games.get(gs);
        panel.btView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cViewGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pViewGame);
            }
        });
        panel.btEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cEditGame.setInitialValues(gs);
                parent.frame.changePanel(parent.frame.pEditGame);
            }
        });
        panel.btRemove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Advice.showOptionAdvice(
                    view,
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
    }

    public void updateName(GameStat gs){
        games.get(gs).aName.setText(gs.getGame());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btAdd){
            parent.cEditGame.setInitialValues(null);
            parent.frame.changePanel(parent.frame.pEditGame);
        }else if(e.getSource() == view.btBackup){
            try {
                Advice.showTextAreaAdvice(
                    view,
                    Language.loadMessage("g_success"),
                    Language.loadMessage("m_backedup"),
                    "Name of the backup file: "+parent.mGeneral.doBackup(),
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
			} catch (FileNotFoundException | CouldNotBackupFileException e1) {
				Advice.showTextAreaAdvice(
                    view,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_wentwrong")+": ",
                    e1.toString(), Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
			}
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

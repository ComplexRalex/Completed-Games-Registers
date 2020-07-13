package controller;

import model.*;
import view.*;
import util.*;
import exception.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class MainController{

    public GameRegister mGeneral;
    public Configuration mConfig;

    public MainWindow frame;
    
    public GeneralController cGeneral;
    public EditGameController cEditGame;
    public ViewGameController cViewGame;
    public ConfigurationController cConfig;

    public MainController(){
        // Initializes Language
        Language.initialize();
        set();
    }

    private void set(){
        mGeneral = new GameRegister();
        mConfig = new Configuration();

        // Necesarry because of the custom settings
        loadData();

        frame = new MainWindow();
        frame.addWindowListener(createWindowAdapter());

        cGeneral = new GeneralController(mGeneral, frame.pGeneral, this);
        cGeneral.initialize();

        cEditGame = new EditGameController(frame.pEditGame, this);
        cEditGame.initialize();

        cViewGame = new ViewGameController(frame.pViewGame, this);
        cViewGame.initialize();

        cConfig = new ConfigurationController(mConfig, frame.pConfig, this);
        cConfig.initialize();

        setFrameSettings();
    }

    private void loadData(){
        try {
            mConfig.loadConfiguration();
        } catch (FileNotFoundException | ClassNotFoundException | CouldNotLoadFileException e) {
            Advice.showTextAreaAdvice(
                null,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong") + ": ",
                e.toString(),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        } try {
			mGeneral.loadGameStats();
		} catch (FileNotFoundException | ClassNotFoundException | CouldNotLoadFileException e) {
			Advice.showTextAreaAdvice(
                null,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong") + ": ",
                e.toString(),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }

    private void setFrameSettings(){
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void reset(){
        frame.setVisible(false);
        frame.dispose();
        frame = null;
        set();
    }

    public void saveStats(){
        try {
			mGeneral.saveGameStats();
		} catch (FileNotFoundException | CouldNotSaveFileException e) {
			Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong")+": ",
                e.toString(), Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }

    private void doBackup(){
        try {
            mGeneral.doBackup();
        } catch (FileNotFoundException | CouldNotBackupFileException e) {
            Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong")+": ",
                e.toString(), Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }

    private WindowAdapter createWindowAdapter(){
        return new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                if(Configuration.getExitDialog()){
                    if(Advice.showOptionAdvice(
                        frame,
                        Language.loadMessage("g_warning"),
                        Language.loadMessage("m_closing"),
                        new String[]{
                            Language.loadMessage("g_accept"),
                            Language.loadMessage("g_cancel")
                        },
                        Colour.getPrimaryColor()
                    ) == 0){
                        if(Configuration.getAutoBackup())
                            doBackup();
                        System.exit(0);
                    }
                }else{
                    if(Configuration.getAutoBackup())
                        doBackup();
                    System.exit(0);
                }
            }
        };
    }
}
package controller;

import model.*;
import view.*;
import util.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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
        Language.initialize();
        set();
    }

    private void set(){
        mConfig = new Configuration();
        mGeneral = new GameRegister();
        
        // Check existence of directories
        verifyDirectories();
        verifyConfigFile();
        
        // Necessary because of the custom settings and the saved files
        loadData();
        
        // Setup languages and themes
        
        Colour.setCurrentTheme(mConfig.currentTheme());
        Language.setCurrentLanguage(mConfig.currentLanguage());
        
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
    
    public void verifyDirectories(){
        // The "data" folder will be created with these if necessary
        if(!Path.exists(Path.backupPath)) Path.resolve(Path.backupPath);
        if(!Path.exists(Path.gameInfo)) Path.resolve(Path.gameInfo);
        if(!Path.exists(Path.gameImage)) Path.resolve(Path.gameImage);
    }

    // Probably require much validation...
    public void verifySaveFile(){
        if(!Path.exists(Path.saveFile)) saveStats();
    }

    // Probably require much validation...
    public void verifyConfigFile(){
        try {
            if(!Path.exists(Path.configFile)) mConfig.saveConfiguration();
		} catch (IOException e) {
			Advice.showTextAreaAdvice(
                null,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong") + ": ",
                Advice.getStackTrace(e),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }

    private void loadData(){
        try {
            mConfig.loadConfiguration();
        } catch (ClassNotFoundException | IOException e) {
            Advice.showTextAreaAdvice(
                null,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong") + ": ",
                Advice.getStackTrace(e),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
        try {
            mGeneral.loadGameStats();
		} catch (IOException | ClassNotFoundException e) {
			Advice.showTextAreaAdvice(
                null,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong") + ": ",
                Advice.getStackTrace(e),
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
            Path.resolve(Path.dataPath);
            mGeneral.saveGameStats();
		} catch (IOException e) {
			Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong")+": ",
                Advice.getStackTrace(e),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }

    private void doBackup(){
        try {
            Path.resolve(Path.backupPath);
            mGeneral.doBackup();
        } catch (IOException e) {
            Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_wentwrong")+": ",
                Advice.getStackTrace(e),
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }

    public void suddenClose(){
        System.exit(0);
    }

    private WindowAdapter createWindowAdapter(){
        return new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                if(mConfig.getExitDialog()){
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
                        if(mConfig.getAutoBackup() && mGeneral.changesMade())
                            doBackup();
                        frame.dispose();
                        verifyDirectories();
                        verifyConfigFile();
                    }
                }else{
                    if(mConfig.getAutoBackup() && mGeneral.changesMade())
                        doBackup();
                    frame.dispose();
                    verifyDirectories();
                    verifyConfigFile();
                }
            }
        };
    }
}
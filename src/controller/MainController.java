package controller;

import model.*;
import view.*;
import util.*;
import exception.*;

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
			Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong")+": ", e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		}
    }

    private void setFrameSettings(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }

    public void reset(){
        frame.setVisible(false);
        frame.dispose();
        frame = null;
        set();
    }
}
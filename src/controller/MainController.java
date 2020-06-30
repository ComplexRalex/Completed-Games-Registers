package controller;

import model.*;
import view.*;
import util.*;
import exception.*;

import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class MainController{

    public Configuration mConfig;

    public MainWindow frame;
    
    public EditGameController cEditGame;
    public ConfigurationController cConfig;

    public MainController(){
		// Initializes Language
        Language.initialize();
        set();
    }

    private void set(){
        mConfig = new Configuration();

        // Necesarry because of the custom settings
        loadData();

        frame = new MainWindow();

        cEditGame = new EditGameController(frame.pEditGame, this);
        cEditGame.initialize();

        cConfig = new ConfigurationController(mConfig, frame.pConfig, this);
        cConfig.initialize();

        setFrameSettings();
    }

    private void loadData(){
        try {
			mConfig.loadConfiguration();
		} catch (FileNotFoundException e) {
			Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong")+": ", e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		} catch (ClassNotFoundException e) {
			Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong")+": ", e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		} catch (CouldNotLoadFileException e) {
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
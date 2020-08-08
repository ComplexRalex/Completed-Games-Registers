/**
 * Completed-Games Registers, a software where you can record every
 * game you have beaten (completed) so far!
 * Copyright (C) 2020  Alejandro Batres
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * Contact by email: alejandro.batres37@gmail.com
 */

package controller;

import model.*;
import view.*;
import util.*;

import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * <h2>MainController controller class.</h2>
 * This class is used to manage <b>everything</b> about this program.
 * <p>
 * Its purpose is to connect all the important classes to each other.
 * Also, it manages important actions. For instance, verify the files
 * stored into the {@link Path#dataPath} folder, like the <b>save.dat</b>
 * and <b>config.dat</b> files.
 * <p>
 * Here, all the <b>controllers</b> and <b>models</b> are initialized
 * (all the class from the <b>view</b> package are initialized in the
 * {@link MainWindow} class).
 * <p>
 * The used models are:
 * <ul>
 * <li>{@link #mGeneral}: The logic behind the management of the
 * <b>completed-games <i>registers</i></b>.
 * <li>{@link #mConfig}: The logic behind the customization and
 * advanced options in the program.
 * </ul>
 * The used controllers are:
 * <ul>
 * <li>{@link #cGeneral}: Management of the current <b>completed-games
 * <i>registers</i></b> and navigation options.
 * <li>{@link #cEditGame}: Management of the editing/creation menu of
 * <b>completed-games <i>registers</i></b>.
 * <li>{@link #cViewGame}: Management of the visualization of
 * <b>completed-games <i>registers</i></b> with additional
 * information.
 * <li>{@link #cHelp}: Management of the "repport issue" and return
 * buttons.
 * <li>{@link #cConfig}: Management of every available setting in the
 * program.
 * <li>{@link #cAbout}: Management of the references and licenses/terms
 * of use options.
 * </ul>
 * 
 * @author Alejandro Batres
 */
public class MainController{

    /**
     * Logic about the management of the <b>completed-games
     * <i>registers</i></b>.
     */
    public GameRegister mGeneral;

    /**
     * Logic about the management of the settings in the
     * program.
     */
    public Configuration mConfig;

    /**
     * Window that will show up every visual component <i>and
     * life</i>.
     */
    public MainWindow frame;
    
    /**
     * Management of the <b>completed-games <i>registers</i></b>
     * and navigation options, including two more options related
     * to the creation of files.
     */
    public GeneralController cGeneral;

    /**
     * Management of the editing/creation menu of <b>completed-games
     * <i>registers</i></b> with some options that give the chance
     * to obtain additional information about that game.
     */
    public EditGameController cEditGame;

    /**
     * Management of the visualization menu of <b>completed-games
     * <i>registers</i></b> that can display additional information
     * about the game.
     */
    public ViewGameController cViewGame;

    /**
     * Management of the "repport issue" and return buttons.
     */
    public HelpController cHelp;

    /**
     * Management of every available setting in the program,
     * including advanced options and <i>delicate</i>
     * options.
     */
    public ConfigurationController cConfig;
    
    /**
     * Management of the references and licenses/terms of use
     * options.
     */
    public AboutController cAbout;

    /**
     * Constructor of the MainController class.
     * It just calls the {@link #set()} method.
     * 
     * @see #set
     */
    public MainController(){
        set();
    }

    /**
     * Initializes <b>verything</b>.
     * <p>
     * Here are the setps of the initialization.
     * <ol>
     * <li>Create {@link Configuration} and {@link GameRegister}
     * instances.
     * <li>Make additional GUI changes (like the color of the 
     * scrollbar).
     * <li>Verify external directories (like {@link Path#gameInfo}).
     * <li>Verify the existence of the <b>save.dat</b> and
     * <b>config.dat</b> files. In case of non-existence, they
     * will be created.
     * <li>Verify additional things, like the <i>installed fonts</i>.
     * <li>Load the <b>save.dat</b> and <b>config.dat</b> files.
     * <li>Initialize unmodifiable values (language and theme). Also
     * initialize some additional settings.
     * <li>Make additional GUI changes (again).
     * <li>Initialize the <b>frame</b> and add a WindowAdapter to it.
     * <li>Initialize all the <b>controllers</b>.
     * <li>Set additional frame settings.
     * </ol>
     * 
     * @see #makeUIchanges()
     * @see #verifyDirectories()
     * @see #verifyConfigFile()
     * @see #verifySaveFile()
     * @see #loadData()
     * @see Colour#setCurrentTheme(int)
     * @see Language#setCurrentLanguage(String)
     * @see GameData#setConnectionTimeout(int)
     * @see GameData#setReadTimeout(int)
     * @see #createWindowAdapter()
     */
    private void set(){
        
        // Initialize models (with default values)
        mConfig = new Configuration();
        mGeneral = new GameRegister();

        // Make additional changes in UI
        makeUIchanges();
        
        // Check existence of directories and files
        verifyDirectories();
        /**
         * In the case of the non-existence config and save files, these
         * will create them as "new files". Otherwise, just won't affect
         * the existing files.
         */
        verifyConfigFile();
        verifySaveFile();
        // Check additional stuff
        verifyAdditional();
        
        // Necessary because of the custom settings and the saved files
        loadData();
        
        // Setup languages and themes
        Colour.setCurrentTheme(mConfig.currentTheme());
        Language.setCurrentLanguage(mConfig.currentLanguage());
        GameData.setConnectionTimeout(mConfig.getConnectionTimeout());
        GameData.setReadTimeout(mConfig.getReadTimeout());
        // Update additional changes on UI
        makeUIchanges();
        
        frame = new MainWindow();
        frame.addWindowListener(createWindowAdapter());

        cGeneral = new GeneralController(mGeneral, frame.pGeneral, this);
        cGeneral.initialize();

        cEditGame = new EditGameController(frame.pEditGame, this);
        cEditGame.initialize();

        cViewGame = new ViewGameController(frame.pViewGame, this);
        cViewGame.initialize();

        cHelp = new HelpController(frame.pHelp, this);
        cHelp.initialize();

        cConfig = new ConfigurationController(mConfig, frame.pConfig, this);
        cConfig.initialize();

        cAbout = new AboutController(frame.pAbout, this);
        cAbout.initialize();

        setFrameSettings();
    }
    
    /**
     * Verifies if all the used directories in the program
     * are currently created. In case that they are not
     * created yet, then they will be.
     */
    public void verifyDirectories(){
        // The "data" folder will be created with these if necessary
        Path.resolve(Path.backupPath);
        Path.resolve(Path.exportPath);
        Path.resolve(Path.logPath);
        Path.resolve(Path.gameInfo);
        Path.resolve(Path.gameImage);
    }

    /**
     * It creates a new <b>save.dat</b> file if there is
     * none in {@link Path#saveFile}.
     * (NOTE: this must require more validation than this).
     * 
     * @see #saveStats()
     */
    public void verifySaveFile(){
        /**
         * Creates a save.dat file in case that doesn't already exist.
         * This is mostly because the first run of the program must create
         * every file and directory in order to work properly.
         */
        if(!Path.exists(Path.saveFile)) saveStats();
    }

    /**
     * It creates a new <b>config.dat</b> file if there is
     * none in {@link Path#configFile}.
     * (NOTE: this must require more validation than this).
     * 
     * @see #saveConfig()
     */
    public void verifyConfigFile(){
        /**
         * Creates a config.dat file in case that doesn't already exist.
         * This is mostly because the first run of the program must create
         * every file and directory in order to work properly.
         */
        if(!Path.exists(Path.configFile)) saveConfig();
    }

    /**
     * It checks additional things. In this case, it looks up for the
     * "Open Sans" font if it does exists in the system. If not, a
     * pop up will show up and tell to the user that it's recommended
     * to install it.
     * After this, it will create a new file in {@link Path#logPath}
     * named {@code fonts.log}. This will be used just to not to display
     * the same dialog again (unless the user deletes the file).
     */
    private void verifyAdditional(){
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String fonts[] = g.getAvailableFontFamilyNames();
        for(int i = 0; i < fonts.length; i++)
            if("Open Sans".equals(fonts[i]))
                return;
        Path.resolve(Path.logPath);
        if(!Path.exists(Path.logPath+Log.getValue(Log.MESSAGE)+"-font.log")){
            if(Advice.showOptionAdvice(
                frame,
                Language.loadMessage("g_message"),
                Language.loadMessage("m_needed_font"),
                new String[]{
                    Language.loadMessage("g_accept"),
                    Language.loadMessage("g_cancel")
                },
                Colour.getPrimaryColor()
            ) == 0)
                Navigation.goToPage("https://fonts.google.com/specimen/Open+Sans", frame);
            String content =
                "It's recommended to install the \"Open Sans\" font to get the best experience in this program!\n"+
                "\n"+
                "Go to the following link, and click \"Download family\":\n"+
                "https://fonts.google.com/specimen/Open+Sans";
            Log.toFile(content, "font", Log.MESSAGE);
        }
    }

    /**
     * Calls the {@link Configuration#loadConfiguration()} and
     * {@link GameRegister#loadGameStats()} methods, catching any
     * possible exception.
     * 
     * @see Configuration#loadConfiguration()
     * @see GameRegister#loadGameStats()
     */
    private void loadData(){
        try {
            mConfig.loadConfiguration();
        } catch (ClassNotFoundException | IOException | ClassCastException e) {
            String error = Log.getDetails(e);
            Log.toFile(error, Log.ERROR);
            Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_went_wrong") + ": ",
                error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
        try {
            mGeneral.loadGameStats();
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
            String error = Log.getDetails(e);
            Log.toFile(error, Log.ERROR);
			Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_went_wrong") + ": ",
                error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }

    /**
     * Sets the following {@link #frame} properties:
     * <ul>
     * <li>Default close operation
     * <li>Screen location
     * <li>Visibility
     * </ul>
     */
    private void setFrameSettings(){
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Make changes to some GUI elements that cannot be
     * changed directly.
     */
    private void makeUIchanges(){
        // This is because I want to color the ScrollBar and I don't see another way to do it...
		UIManager.put("ScrollBar.thumb",Colour.getPrimaryColor());
		UIManager.put("ScrollBar.thumbHighlight",Colour.getPrimaryColor());
		UIManager.put("ScrollBar.thumbShadow",Colour.getPrimaryColor());
		UIManager.put("ScrollBar.thumbDarkShadow",Colour.getButtonColor());
		UIManager.put("ScrollBar.track",Colour.getButtonColor());
    }

    /**
     * Closes the the current {@link #frame} and calls the
     * {@link #set()} method.
     * 
     * @see #set()
     */
    public void reset(){
        frame.setVisible(false);
        frame.dispose();
        frame = null;
        set();
    }

    /**
     * Calls the {@link GameRegister#saveGameStats()} method,
     * catching any possible exception.
     */
    public void saveStats(){
        try {
            mGeneral.saveGameStats();
		} catch (IOException e) {
            String error = Log.getDetails(e);
            Log.toFile(error, Log.ERROR);
			Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_went_wrong")+": ",
                error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }

    /**
     * Calls the {@link Configuration#saveConfiguration()} method,
     * catching any possible exception.
     */
    public void saveConfig(){
        try {
            mConfig.saveConfiguration();
		} catch (IOException e) {
            String error = Log.getDetails(e);
            Log.toFile(error, Log.ERROR);
			Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_went_wrong") + ": ",
                error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }

    /**
     * Calls the {@link GameRegister#doBackup()} method,
     * catching any possible exception.
     */
    private void doBackup(){
        try {
            mGeneral.doBackup();
        } catch (IOException e) {
            String error = Log.getDetails(e);
            Log.toFile(error, Log.ERROR);
            Advice.showTextAreaAdvice(
                frame,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_went_wrong")+": ",
                error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
        }
    }

    /**
     * Empties the array of {@link GameStat}s and
     * save to the file.
     * <p>
     * This means that the registers were removed and
     * then saved the file with no-registers. So,
     * there is no chance to recover the previous file.
     */
    public void resetStats(){
        mGeneral.getGameStats().clear();
        saveStats();
    }

    /**
     * Calls {@link Configuration#setDefaultValues()}
     * and save to the file.
     * <p>
     * This means that the current {@link Configuration}
     * instance were "reset" to its default values, and
     * then saved the file with those values. So, there
     * is no chance to recover the previous file.
     */
    public void resetConfig(){
        mConfig.setDefaultValues();
        saveConfig();
    }

    /**
     * Takes an {@link File} array list of the files inside
     * {@link Path#gameInfo} and {@link Path#gameImage} and
     * delete them.
     * <p>
     * WARNING: This will delete EVERYTHING inside those
     * folders. There is no validation of what files must
     * be deleted.
     */
    public void deleteDownloadedInfo(){
        Path.resolve(Path.gameInfo);
        File info[] = (new File(Path.gameInfo)).listFiles();
        for(File f: info)
            f.delete();

        Path.resolve(Path.gameImage);
        File image[] = (new File(Path.gameImage)).listFiles();
        for(File f: image)
            f.delete();
    }

    /**
     * Takes an {@link File} array list of the files inside
     * {@link Path#backupPath} and delete them.
     * <p>
     * WARNING: This will delete EVERYTHING inside that
     * folders. There is no validation of what files must
     * be deleted.
     */
    public void deleteBackups(){
        Path.resolve(Path.backupPath);
        File backup[] = (new File(Path.backupPath)).listFiles();
        for(File f: backup)
            f.delete();
    }

    /**
     * Takes an {@link File} array list of the files inside
     * {@link Path#exportPath} and delete them.
     * <p>
     * WARNING: This will delete EVERYTHING inside that
     * folders. There is no validation of what files must
     * be deleted.
     */
    public void deleteExports(){
        Path.resolve(Path.exportPath);
        File export[] = (new File(Path.exportPath)).listFiles();
        for(File f: export)
            f.delete();
    }

    /**
     * Takes an {@link File} array list of the files inside
     * {@link Path#logPath} and delete them.
     * <p>
     * WARNING: This will delete EVERYTHING inside that
     * folders. There is no validation of what files must
     * be deleted.
     */
    public void deleteLogs(){
        Path.resolve(Path.logPath);
        File log[] = (new File(Path.logPath)).listFiles();
        for(File f: log)
            f.delete();
    }

    /**
     * Default close instructions.
     */
    public void defaultClose(){
        if(mConfig.getAutoBackup() && mGeneral.changesMade())
            doBackup();
        frame.dispose();
        verifyDirectories();
        verifyConfigFile();
        verifySaveFile();
    }

    /**
     * Quick close operation.
     */
    public void suddenClose(){
        System.exit(0);
    }

    private WindowAdapter createWindowAdapter(){
        return new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                if(!frame.isBusy()){
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
                        ) == 0)
                            defaultClose();
                    }else
                        defaultClose();
                }else
                    Advice.showSimpleAdvice(
                        frame,
                        Language.loadMessage("g_message"),
                        Language.loadMessage("m_busy_frame"),
                        Language.loadMessage("g_accept"),
                        Colour.getPrimaryColor()
                    );
            }
        };
    }
}

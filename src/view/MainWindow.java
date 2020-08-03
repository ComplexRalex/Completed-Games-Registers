package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import system.Software;
import util.ImageResource;

/**
 * <h2>MainWindow view class.</h2>
 * This class extends from {@link JFrame} and is
 * used to display every menu created in this
 * software.
 * <p>
 * MainWindow initializes the following panels:
 * <ul>
 * <li>{@link #pGeneral}: The main "section" of this
 * program. Here the usar can access to the rest of
 * the sections.
 * <li>{@link #pEditGame}: The creator/editor menu of
 * a <b>completed-game <i>register</i></b>.
 * <li>{@link #pViewGame}: The <b>completed-game <i>
 * register</i></b> viewer. Also, can display additional
 * information about the game.
 * <li>{@link #pHelp}: The "Questions & Answers"
 * section of the program. It includes a "report issues"
 * button.
 * <li>{@link #pConfig}: The configuration menu where
 * the user can change settings like the theme or
 * language of the program, with some more parameters.
 * <li>{@link #pAbout}: The "About & Credits" section.
 * It shows the credits of the program and the
 * used software here.
 * </ul>
 * 
 * @see MainController
 * @see GeneralPanel
 * @see EditGamePanel
 * @see ViewGamePanel
 * @see HelpPanel
 * @see ConfigurationPanel
 * @see AboutPanel
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame{

	/**
	 * Instance of {@link GeneralPanel} that shows
	 * up the main menu of the program. In this panel,
	 * the user can access to every possible site.
	 * 
	 * @see GeneralPanel
	 */
	public GeneralPanel pGeneral;

	/**
	 * Instance of {@link EditGamePanel} that shows
	 * up the creation/editing menu of a <b>
	 * completed-game <i>register</i></b>. In this
	 * panel, the user can add/modify the fileds of
	 * a {@link GameStat} variable to save it in
	 * the current saved registers.
	 * 
	 * @see EditGamePanel
	 */
	public EditGamePanel pEditGame;

	/**
	 * Instance of {@link ViewGamePanel} that shows
	 * up the content of the selected {@link GameStat}
	 * object (<b>completed-game <i>register</i></b>).
	 * This panel might include additional game
	 * information if the user has downloaded it
	 * in the {@link #pEditGame} panel.
	 * 
	 * @see ViewGamePanel
	 */
	public ViewGamePanel pViewGame;

	/**
	 * Instance of {@link HelpPanel} that shows up
	 * the content related to (maybe) frequent
	 * questions that a new user can have the first
	 * time that has used this program.
	 * <p>
	 * This panel includes a "report issues" botton
	 * as well.
	 * 
	 * @see HelpPanel
	 */
	public HelpPanel pHelp;

	/**
	 * Instance of {@link ConfigurationPanel} that
	 * shows up settings that the user may want to
	 * modify any moment they want.
	 * <p>
	 * Some options may require restart the program
	 * to take effect (a dialog will show up in such
	 * case).
	 * 
	 * @see ConfigurationPanel
	 */
	public ConfigurationPanel pConfig;

	/**
	 * Instance of {@link AboutPanel} that shows
	 * up the credits of the program. This includes
	 * external software used.
	 * 
	 * @see AboutPanel
	 */
	public AboutPanel pAbout;
	
	/**
	 * Constructor of the MainWindow class. First,
	 * initializes some parameters to the frame.
	 * Second, initializes all the "sections" of
	 * the program (only its visual components).
	 * Finally, switches to the main panel ({@link #pGeneral}).
	 * 
	 * @see #setWindow()
	 * @see #changePanel(JPanel, JScrollBar)
	 */
	public MainWindow(){

		// Initialize the frame
		setWindow();
		
		// Initialize panels
		pGeneral = new GeneralPanel();
		pEditGame = new EditGamePanel();
		pViewGame = new ViewGamePanel();
		pHelp = new HelpPanel();
		pConfig = new ConfigurationPanel();
		pAbout = new AboutPanel();
		
		// Put the main panel at the beginning
		changePanel(pGeneral,null);
	}

	/**
	 * Defines some important parameters to the frame.
	 */
	private void setWindow(){
		setTitle(Software.NAME);
		try{
			setIconImage((new ImageResource()).resource(ImageResource.FRAME));
		}catch(IllegalArgumentException | NullPointerException e){/* In case of non-existence, it will be ignored.*/}
		setSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.70),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.70)));
		setMinimumSize(new Dimension(720,480));
		setResizable(true);
	}
	
	/**
	 * Switches the panels and set its scrollbar to the
	 * top of the "page".
	 * 
	 * @param pNuevo Destiny {@link JPanel} that will be
	 * put in the frame, closing the previous one
	 * @param scrollBar {@link JScrollBar} of such panel
	 * which will be set to <b>0</b> (this means that the
	 * panel will be scrolled at the top)
	 */
	public void changePanel(JPanel pNuevo, JScrollBar scrollBar){
		getContentPane().removeAll();
		add(pNuevo);
		pNuevo.setVisible(true);
		if(scrollBar != null) 
			scrollBar.setValue(0);
		getContentPane().validate();
		getContentPane().repaint();
		validate();
		repaint();
	}
}

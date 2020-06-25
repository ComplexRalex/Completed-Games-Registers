package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Path;
import util.Language;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	//public MainPanel pMain;
	public EditGamePanel pEditGame;
	//public GameInfoPanel pGameInfo;
	public ConfigurationPanel pConfig;
	
	public MainWindow(ConfigurationPanel config){
		// Initialize the frame
		setTitle(Language.loadMessage("p_title"));
		setIconImage((new ImageIcon(Path.images+"cartridge.png")).getImage());
		setSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.70),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.70)));
		setMinimumSize(new Dimension(720,480));
		setResizable(true);
		
		// Initialize the components
		//pMain = new MainPanel();
		pEditGame = new EditGamePanel();
		//pGameInfo = new GameInfoPanel();
		pConfig = config;
		
		changePanel(pEditGame);
	}
	
	public void changePanel(JPanel pNuevo){
		getContentPane().removeAll();
		add(pNuevo);
		pNuevo.setVisible(true);
		getContentPane().repaint();
		validate();
	}
}

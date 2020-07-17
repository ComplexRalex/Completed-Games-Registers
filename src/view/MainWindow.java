package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Image;
import util.Language;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	public GeneralPanel pGeneral;
	public EditGamePanel pEditGame;
	public ViewGamePanel pViewGame;
	public HelpPanel pHelp;
	public ConfigurationPanel pConfig;
	
	public MainWindow(){
		// Initialize the frame
		setWindow();
		
		// Initialize the components
		pGeneral = new GeneralPanel();
		pEditGame = new EditGamePanel();
		pViewGame = new ViewGamePanel();
		pHelp = new HelpPanel();
		pConfig = new ConfigurationPanel();
		
		changePanel(pGeneral);
	}

	private void setWindow(){
		setTitle(Language.loadMessage("p_title"));
		setIconImage(Image.getFrame());
		setSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.70),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.70)));
		setMinimumSize(new Dimension(720,480));
		setResizable(true);
	}
	
	public void changePanel(JPanel pNuevo){
		getContentPane().removeAll();
		add(pNuevo);
		pNuevo.setVisible(true);
		getContentPane().validate();
		getContentPane().repaint();
		validate();
		repaint();
	}
}

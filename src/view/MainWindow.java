package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	//public PrincipalPanel pMain;
	//public GameInfoPanel pGameInfo;
	public ConfigurationPanel pConfig;
	
	public MainWindow(ConfigurationPanel config){
		// Initialize the frame
		setTitle("Registro de Juegos Completados");
		setSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.70),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.70)));
		setMinimumSize(new Dimension(720,480));
		setResizable(true);
		
		// Initialize the components
		//pMain = new PrincipalPanel();
		//pGameInfo = new GameInfoPanel();
		pConfig = config;
		
		changePanel(pConfig);
	}
	
	public void changePanel(JPanel pNuevo){
		getContentPane().removeAll();
		add(pNuevo);
		pNuevo.setVisible(true);
		getContentPane().repaint();
		validate();
	}
}

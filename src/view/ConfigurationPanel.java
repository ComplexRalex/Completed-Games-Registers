package view;

import java.awt.*;
import javax.swing.*;

import util.Colour;
import util.Typeface;
import util.Language;
import util.Component;
import util.SimplePanel;

/**
 * Panel which contains every visual component about the configuration menu of the
 * Completed-GamesRegister program.
 * 
 * @author Alejandro Batres
 */
@SuppressWarnings("serial")
public class ConfigurationPanel extends JPanel{
	public JPanel pPass;
	public JButton btAutoSaveON, btAutoSaveOFF, btAutoBackupON, btAutoBackupOFF, btChange, btResetConfig, btWipeOut, btAccept, btReturn;
	public JComboBox<String> cbLang;
	public JRadioButton btTheme[];
	public JTextField txtUser;
	public JLabel lbConfigMessage;
	
	/*
	 * PASOS SIGUIENTES PARA NO PERDERSE XD:
	 * 4. Agregar siguientes opciones:
	 *  - Reset configurations to default
	 *  - Delete ALL data (it'll request password confirmation)
	 *  
	 * */
	public ConfigurationPanel(){
		this.setLayout(new BorderLayout());
		this.setBackground(Colour.getPrimaryColor());
		initComponents();
	}
	
	/**
	 * Initializes every visual component inside of the ConfigurationPanel panel.
	 */
	private void initComponents(){

		// Establishing the title (lol)
		
		JLabel lbTitle = new JLabel(Language.loadMessage("cf_title"));
		lbTitle.setHorizontalAlignment(JLabel.CENTER);
		lbTitle.setFont(Typeface.labelTitle);
		lbTitle.setPreferredSize(new Dimension(0,75));
		lbTitle.setForeground(Colour.getFontColor());

		add(lbTitle,BorderLayout.NORTH);
		
		// Establishing panel of all options
		
		SimplePanel pGeneralOptions = new SimplePanel();
		JScrollPane scrollOptions = new JScrollPane(pGeneralOptions);
		scrollOptions.setBorder(BorderFactory.createLineBorder(pGeneralOptions.getBackground()));
		scrollOptions.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollOptions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollOptions.setAlignmentY(JScrollPane.RIGHT_ALIGNMENT);
		
		// - Establishing the username option

		txtUser = new JTextField();
		pGeneralOptions.add(Component.createTextField(Language.loadMessage("cf_change_user"), txtUser, Colour.getPrimaryColor()));

		// - Establishing the auto-save options
		
		btAutoSaveON = new JButton("ON");
		btAutoSaveOFF = new JButton("OFF");
		pGeneralOptions.add(Component.createSwitchButton(Language.loadMessage("cf_autosave"), btAutoSaveON, btAutoSaveOFF, Colour.getBackgroundColor()));
		
		// - Establishing the auto-backup options

		btAutoBackupON = new JButton("ON");
		btAutoBackupOFF = new JButton("OFF");
		pGeneralOptions.add(Component.createSwitchButton(Language.loadMessage("cf_autobck"), btAutoBackupON, btAutoBackupOFF, Colour.getBackgroundColor()));
		
		// - Establishing the theme options
		
		btTheme = new JRadioButton[Colour.available.length];
		pGeneralOptions.add(Component.createRadioButtons(Language.loadMessage("cf_theme"), Colour.available, btTheme, Colour.getPrimaryColor()));
		
		// - Establishing language options

		cbLang = new JComboBox<String>(Language.available);
		pGeneralOptions.add(Component.createComboBox(Language.loadMessage("cf_lang"), cbLang, Colour.getBackgroundColor()));

		// - Establishing.... test stuff

		pGeneralOptions.add(Component.createPlainText(
			"This is just a text test.... or test text? I don't know! "+
			"Are you still reading this? I mean, it's okay but this is "+
			"just a sample text! Don't give it too much attention dude!"
			,
			Colour.getPrimaryColor())
		);

		add(scrollOptions,BorderLayout.CENTER);
				
		// Establishing panel which contains "Accept" and "Return" options
		
		JPanel pOptions = new JPanel(new FlowLayout());
		pOptions.setBackground(Colour.getPrimaryColor());
		btAccept = new JButton(Language.loadMessage("g_apply"));
		btAccept.setFont(Typeface.buttonPlain);
		btAccept.setBackground(Colour.getButtonColor());
		btAccept.setForeground(Colour.getFontColor());
		btAccept.setPreferredSize(new Dimension(200,30));
		pOptions.add(btAccept);
		
		btReturn = new JButton(Language.loadMessage("g_return"));
		btReturn.setFont(Typeface.buttonPlain);
		btReturn.setBackground(btAccept.getBackground());
		btReturn.setForeground(btAccept.getForeground());
		btReturn.setPreferredSize(btAccept.getPreferredSize());
		pOptions.add(btReturn);
		
		add(pOptions,BorderLayout.SOUTH);
	}
}

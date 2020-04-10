package view;

import java.awt.*;
import javax.swing.*;

import model.Configuration;
import model.Languages;

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
	public JLabel lbUser, lbConfigMessage;
	
	/*
	 * PASOS SIGUIENTES PARA NO PERDERSE XD:
	 * 4. Agregar siguientes opciones:
	 *  - Reset configurations to default
	 *  - Delete ALL data (it'll request password confirmation)
	 *  - Languages (NOT TOTALLY CONFIRMED)
	 *  
	 * */
	public ConfigurationPanel(){
		this.setLayout(new BorderLayout());
		this.setBackground(Configuration.getPrimaryColor());
		initComponents();
	}
	
	/**
	 * Initializes every visual component inside of the ConfigurationPanel panel.
	 */
	private void initComponents(){
		Dimension dimBt = new Dimension(62,22);
		Font fntBt = new Font("Open Sans",1,14);
		Font fntLbBold = new Font("Open Sans",1,16);
		Font fntLbPlain = new Font("Open Sans",0,16);
		Color fontColor = Configuration.getFontColor();
		Color buttonColor = Configuration.getButtonColor();
		
		// Establishing the title (lol)
		
		JLabel lbTitle = new JLabel(Languages.loadMessage("cf_title"));
		lbTitle.setHorizontalAlignment(JLabel.CENTER);
		lbTitle.setFont(new Font("Open Sans",1,24));
		lbTitle.setPreferredSize(new Dimension(0,75));
		lbTitle.setForeground(fontColor);
		
		// Establishing panel of all options
		
		JPanel pGeneralOptions = new JPanel(new GridBagLayout());
		pGeneralOptions.setBackground(Configuration.getBackgroundColor());
		JScrollPane scrollOptions = new JScrollPane(pGeneralOptions);
		scrollOptions.setViewportView(pGeneralOptions);
		scrollOptions.setBorder(BorderFactory.createLineBorder(pGeneralOptions.getBackground()));
		scrollOptions.setPreferredSize(new Dimension(20,800));
		scrollOptions.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollOptions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollOptions.setAlignmentY(JScrollPane.RIGHT_ALIGNMENT);
		
		// - Establishing the username panel
		
		JPanel pUsername = new JPanel(new GridLayout(2,1,5,3));
		pUsername.setBackground(Configuration.getPrimaryColor());
		pUsername.setPreferredSize(new Dimension(560,80));
		
		JPanel pCurrentUser = new JPanel(new FlowLayout());
		pCurrentUser.setBackground(Configuration.getPrimaryColor());
		JLabel lbCurrentUser = new JLabel(Languages.loadMessage("cf_current_user"));
		lbCurrentUser.setFont(fntLbPlain);
		lbCurrentUser.setForeground(fontColor);
		pCurrentUser.add(lbCurrentUser);
		lbUser = new JLabel(Configuration.getUsername());		
		lbUser.setFont(fntLbBold);
		lbUser.setForeground(fontColor);
		pCurrentUser.add(lbUser);
		pUsername.add(pCurrentUser);
		
		JPanel pChangeUser = new JPanel(new FlowLayout());
		pChangeUser.setBackground(Configuration.getPrimaryColor());
		JLabel lbChangeUser = new JLabel(Languages.loadMessage("cf_change_user"));
		lbChangeUser.setFont(fntLbPlain);
		lbChangeUser.setForeground(fontColor);
		pChangeUser.add(lbChangeUser);
		txtUser = new JTextField();
		txtUser.setFont(fntLbPlain);
		txtUser.setBackground(Configuration.getBackgroundColor());
		txtUser.setForeground(fontColor);
		txtUser.setPreferredSize(new Dimension(150,25));
		pChangeUser.add(txtUser);
		btChange = new JButton(Languages.loadMessage("g_change"));
		btChange.setFont(fntBt);
		btChange.setBackground(buttonColor);
		btChange.setForeground(fontColor);
		btChange.setPreferredSize(new Dimension(130,25));
		pChangeUser.add(btChange);
		pUsername.add(pChangeUser);
		
		// - Establishing the auto-save options
		
		JPanel pAutoSave = new JPanel(new FlowLayout());
		pAutoSave.setBackground(Configuration.transparent);
		JLabel lbAutoSave1 = new JLabel(Languages.loadMessage("cf_autosave_1"));
		lbAutoSave1.setFont(fntLbBold);
		lbAutoSave1.setForeground(fontColor);
		pAutoSave.add(lbAutoSave1);
		
		JLabel lbAutoSave2 = new JLabel(Languages.loadMessage("cf_autosave_2"));
		lbAutoSave2.setFont(fntLbPlain);
		lbAutoSave2.setForeground(fontColor);
		pAutoSave.add(lbAutoSave2);
		
		btAutoSaveON = new JButton("ON");
		btAutoSaveON.setFont(fntBt);
		btAutoSaveON.setBackground(buttonColor);
		btAutoSaveON.setForeground(fontColor);
		btAutoSaveON.setPreferredSize(dimBt);
		pAutoSave.add(btAutoSaveON);
		
		btAutoSaveOFF = new JButton("OFF");
		btAutoSaveOFF.setFont(fntBt);
		btAutoSaveOFF.setBackground(buttonColor);
		btAutoSaveOFF.setForeground(fontColor);
		btAutoSaveOFF.setPreferredSize(dimBt);
		pAutoSave.add(btAutoSaveOFF);
		
		// - Establishing the auto-backup options
		
		JPanel pAutoBackup = new JPanel(new FlowLayout());
		pAutoBackup.setBackground(Configuration.transparent);
		JLabel lbAutoBackup1 = new JLabel(Languages.loadMessage("cf_autobck_1"));
		lbAutoBackup1.setFont(fntLbBold);
		lbAutoBackup1.setForeground(fontColor);
		pAutoBackup.add(lbAutoBackup1);
		
		JLabel lbAutoBackup2 = new JLabel(Languages.loadMessage("cf_autobck_2"));
		lbAutoBackup2.setFont(fntLbPlain);
		lbAutoBackup2.setForeground(fontColor);
		pAutoBackup.add(lbAutoBackup2);
		
		btAutoBackupON = new JButton("ON");
		btAutoBackupON.setFont(fntBt);
		btAutoBackupON.setBackground(buttonColor);
		btAutoBackupON.setForeground(fontColor);
		btAutoBackupON.setPreferredSize(dimBt);
		pAutoBackup.add(btAutoBackupON);
		
		btAutoBackupOFF = new JButton("OFF");
		btAutoBackupOFF.setFont(fntBt);
		btAutoBackupOFF.setBackground(buttonColor);
		btAutoBackupOFF.setForeground(fontColor);
		btAutoBackupOFF.setPreferredSize(dimBt);
		pAutoBackup.add(btAutoBackupOFF);
		
		// - Establishing the theme options
		
		JPanel pThemes = new JPanel(new FlowLayout());
		pThemes.setBackground(Configuration.transparent);
		JLabel lbThemes = new JLabel(Languages.loadMessage("cf_theme"));
		lbThemes.setFont(fntLbPlain);
		lbThemes.setForeground(fontColor);
		pThemes.add(lbThemes);
		
		btTheme = new JRadioButton[3];
		btTheme[0] = new JRadioButton("Light");
		btTheme[1] = new JRadioButton("Dark");
		btTheme[2] = new JRadioButton("Night");
		ButtonGroup btsGroup = new ButtonGroup();
		for(JRadioButton bt: btTheme){
			bt.setFont(new Font("Open Sans",0,14));
			bt.setBackground(Configuration.getBackgroundColor());
			bt.setForeground(fontColor);
			btsGroup.add(bt);
			pThemes.add(bt);
		}
		
		// - Establishing language options (work in progress)
		
		JPanel pLang = new JPanel(new FlowLayout());
		pLang.setBackground(Configuration.getBackgroundColor());
		JLabel lbLang = new JLabel(Languages.loadMessage("cf_lang"));
		lbLang.setFont(fntLbPlain);
		lbLang.setForeground(fontColor);
		pLang.add(lbLang);
		
		cbLang = new JComboBox<String>(Languages.available);
		cbLang.setFont(fntBt);
		cbLang.setBackground(Configuration.getBackgroundColor());
		cbLang.setForeground(fontColor);
		pLang.add(cbLang);
				
		// Establishing panel which contains "Accept" and "Return" options
		
		JPanel pOptions = new JPanel(new FlowLayout());
		pOptions.setBackground(Configuration.getPrimaryColor());
		btAccept = new JButton(Languages.loadMessage("g_apply"));
		btAccept.setFont(fntBt);
		btAccept.setBackground(buttonColor);
		btAccept.setForeground(fontColor);
		btAccept.setPreferredSize(new Dimension(200,30));
		pOptions.add(btAccept);
		
		btReturn = new JButton(Languages.loadMessage("g_return"));
		btReturn.setFont(fntBt);
		btReturn.setBackground(btAccept.getBackground());
		btReturn.setForeground(btAccept.getForeground());
		btReturn.setPreferredSize(btAccept.getPreferredSize());
		pOptions.add(btReturn);

		add(lbTitle,BorderLayout.NORTH);
		
		GridBagConstraints support = new GridBagConstraints();
		support.gridx = 0;
		support.gridy = 0;
		support.insets = new Insets(2,2,0,2);
		support.gridwidth = 3;
		pGeneralOptions.add(pUsername,support);
		support.gridy++;
		pGeneralOptions.add(pAutoSave,support);
		support.gridy++;
		pGeneralOptions.add(pAutoBackup,support);
		support.gridy++;
		pGeneralOptions.add(pThemes,support);
		support.gridy++;
		pGeneralOptions.add(pLang,support);
		
		add(scrollOptions,BorderLayout.CENTER);
		add(pOptions,BorderLayout.SOUTH);
	}
	
	/**
	 * Toggle button state between "Enabled" and "Disabled" and changes its
	 * color.
	 *
	 * @param button component to change its state
	 * @param flag value being button's new state
	 * @param color the life! (button)
	 */
	public void toggleEnabledButton(JButton button, boolean flag, Color color){
		button.setEnabled(flag);
		button.setBackground(color);
	}
}

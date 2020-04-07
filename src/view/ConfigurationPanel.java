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
	public JButton btAutoSaveON, btAutoSaveOFF, btAutoBackupON, btAutoBackupOFF, btChange, btResetConfig, btWipeOut, btAccept, btReturn;
	public JComboBox cbLang;
	public JRadioButton btTheme[];
	public JPasswordField txtPass, txtNewPass, txtConfirmNewPass;
	public JLabel lbConfigMessage, lbPassMessage;
	
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
		
		//cbLang = new JComboBox(Languages.available);
		//cbLang.set
		
		// - Establishing the password options
		
		JPanel pPass = new JPanel(new GridLayout(5,1,5,3));
		pPass.setBackground(Configuration.getPrimaryColor());
		pPass.setPreferredSize(new Dimension(560,180));
		JPanel pPassInfo = new JPanel(new FlowLayout());
		pPassInfo.setBackground(pPass.getBackground());
		JLabel lbChangePass = new JLabel(Languages.loadMessage("cf_pass_tl"));
		lbChangePass.setHorizontalAlignment(JLabel.CENTER);
		lbChangePass.setFont(fntLbPlain);
		lbChangePass.setForeground(fontColor);
		lbPassMessage = new JLabel();
		lbPassMessage.setHorizontalAlignment(JLabel.CENTER);
		lbPassMessage.setFont(fntLbPlain);
		lbPassMessage.setVisible(false);
		pPassInfo.add(lbChangePass);
		pPassInfo.add(lbPassMessage);
		JPanel pSubPass = new JPanel(new FlowLayout());
		pSubPass.setBackground(pPass.getBackground());
		JLabel lbPass = new JLabel(Languages.loadMessage("cf_pass_curr"));
		lbPass.setFont(fntLbPlain);
		lbPass.setForeground(fontColor);
		txtPass = new JPasswordField();
		txtPass.setFont(fntLbPlain);
		txtPass.setBackground(Configuration.getBackgroundColor());
		txtPass.setForeground(fontColor);
		txtPass.setPreferredSize(new Dimension(150,20));
		pSubPass.add(lbPass);
		pSubPass.add(txtPass);
		JPanel pSubNewPass = new JPanel(new FlowLayout());
		pSubNewPass.setBackground(pPass.getBackground());
		JLabel lbNewPass = new JLabel(Languages.loadMessage("cf_pass_new"));
		lbNewPass.setFont(fntLbPlain);
		lbNewPass.setForeground(fontColor);
		txtNewPass = new JPasswordField();
		txtNewPass.setFont(fntLbPlain);
		txtNewPass.setBackground(txtPass.getBackground());
		txtNewPass.setForeground(txtPass.getForeground());
		txtNewPass.setPreferredSize(txtPass.getPreferredSize());
		pSubNewPass.add(lbNewPass);
		pSubNewPass.add(txtNewPass);
		JPanel pSubConfirmNewPass = new JPanel(new FlowLayout());
		pSubConfirmNewPass.setBackground(pPass.getBackground());
		JLabel lbConfirmNewPass = new JLabel(Languages.loadMessage("cf_pass_conf"));
		lbConfirmNewPass.setFont(fntLbPlain);
		lbConfirmNewPass.setForeground(fontColor);
		txtConfirmNewPass = new JPasswordField();
		txtConfirmNewPass.setFont(fntLbPlain);
		txtConfirmNewPass.setBackground(txtPass.getBackground());
		txtConfirmNewPass.setForeground(txtPass.getForeground());
		txtConfirmNewPass.setPreferredSize(txtPass.getPreferredSize());
		pSubConfirmNewPass.add(lbConfirmNewPass);
		pSubConfirmNewPass.add(txtConfirmNewPass);
		JPanel pButtonPass = new JPanel(new FlowLayout());
		pButtonPass.setBackground(pPass.getBackground());
		btChange = new JButton(Languages.loadMessage("cf_pass_change"));
		btChange.setFont(fntBt);
		btChange.setBackground(buttonColor);
		btChange.setForeground(fontColor);
		btChange.setPreferredSize(new Dimension(260,22));
		pButtonPass.add(btChange);
		pPass.add(pPassInfo);
		pPass.add(pSubPass);
		pPass.add(pSubNewPass);
		pPass.add(pSubConfirmNewPass);
		pPass.add(pButtonPass);
				
		// Establishing panel which contains "Accept" and "Return" options
		
		JPanel pOptions = new JPanel(new FlowLayout());
		pOptions.setBackground(Configuration.getPrimaryColor());
		btAccept = new JButton(Languages.loadMessage("g_apply"));
		btAccept.setFont(fntBt);
		btAccept.setBackground(buttonColor);
		btAccept.setForeground(fontColor);
		btAccept.setPreferredSize(new Dimension(200,30));
		btReturn = new JButton(Languages.loadMessage("g_return"));
		btReturn.setFont(fntBt);
		btReturn.setBackground(btAccept.getBackground());
		btReturn.setForeground(btAccept.getForeground());
		btReturn.setPreferredSize(btAccept.getPreferredSize());
		pOptions.add(btAccept);
		pOptions.add(btReturn);

		add(lbTitle,BorderLayout.NORTH);
		
		GridBagConstraints support = new GridBagConstraints();
		support.gridx = 0;
		support.gridy = 0;
		support.insets = new Insets(2,2,0,2);
		support.gridwidth = 3;
		pGeneralOptions.add(pAutoSave,support);
		support.gridy++;
		pGeneralOptions.add(pAutoBackup,support);
		support.gridy++;
		support.gridwidth = 3;
		pGeneralOptions.add(pThemes,support);
		support.gridy++;
		pGeneralOptions.add(pPass,support);
		
		add(scrollOptions,BorderLayout.CENTER);
		add(pOptions,BorderLayout.SOUTH);
	}
	
	/**
	 * Shows a message next to the lbChangePass label about password configurations.
	 * Actually, it runs {@code setVisible(true)} from lbPassMessage label.
	 * 
	 * @param message containing information about the password change success
	 * @param color of the message
	 */
	public void enablePassMessage(String message, Color color){
		lbPassMessage.setForeground(color);
		lbPassMessage.setText(message);
		lbPassMessage.setVisible(true);
	}
	/**
	 * Hides the message about password configurations. Actually, it runs {@code 
	 * setVisible(false)} from lbPassMessage label.
	 */
	public void disablePassMessage(){
		lbPassMessage.setVisible(false);
		lbPassMessage.setForeground(Color.white);
		lbPassMessage.setText("This is an easter egg XD");
	}
}

package view;

import java.awt.*;
import javax.swing.*;
import model.Configuration;

@SuppressWarnings("serial")
public class ConfigurationPanel extends JPanel{
	public JButton btAutoSaveON, btAutoSaveOFF, btAutoBackupON, btAutoBackupOFF, btChange, btAccept, btReturn;
	public JRadioButton btTheme[];
	public JPasswordField txtPass, txtNewPass, txtConfirmNewPass;
	public JLabel lbConfigMessage, lbPassMessage;
	
	/*
	 * PASOS SIGUIENTES PARA NO PERDERSE XD:
	 * 1. Fijar en la parte superior al titulo "Menu de configuracion"
	 * 2. Todas las opciones de configuracion dejarlas en un panel que tenga
	 * 	la posibilidad de desplazarse con una barra de desplazamiento lateral
	 * 3. Dejar las opciones de "Aplicar" y "Regresar" en la parte inferior
	 * */
	public ConfigurationPanel(){
		this.setLayout(new GridBagLayout());
		this.setBackground(Configuration.getBackgroundColor());
		initComponents();
	}
	
	private void initComponents(){
		Dimension dimBt = new Dimension(62,22);
		Font fntBt = new Font("Open Sans",1,14);
		Font fntLbBold = new Font("Open Sans",1,16);
		Font fntLbPlain = new Font("Open Sans",0,16);
		Color fontColor = Configuration.getFontColor();
		Color buttonColor = Configuration.getButtonColor();
		
		JLabel lbTitle = new JLabel("Menú de configuración");
		lbTitle.setHorizontalAlignment(JLabel.LEFT);
		lbTitle.setFont(new Font("Open Sans",1,22));
		lbTitle.setForeground(fontColor);
		
		JPanel pAutoSave = new JPanel(new FlowLayout());
		pAutoSave.setBackground(Configuration.transparent);
		JLabel lbAutoSave1 = new JLabel("Guardado automático");
		lbAutoSave1.setFont(fntLbBold);
		lbAutoSave1.setForeground(fontColor);
		pAutoSave.add(lbAutoSave1);
		JLabel lbAutoSave2 = new JLabel("cuando se realice un cambio:");
		lbAutoSave2.setFont(fntLbPlain);
		lbAutoSave2.setForeground(fontColor);
		pAutoSave.add(lbAutoSave2);
		btAutoSaveON = new JButton("ON");
		btAutoSaveON.setFont(fntBt);
		btAutoSaveON.setBackground(buttonColor);
		btAutoSaveON.setForeground(fontColor);
		btAutoSaveON.setPreferredSize(dimBt);
		btAutoSaveOFF = new JButton("OFF");
		btAutoSaveOFF.setFont(fntBt);
		btAutoSaveOFF.setBackground(buttonColor);
		btAutoSaveOFF.setForeground(fontColor);
		btAutoSaveOFF.setPreferredSize(dimBt);
		
		JPanel pAutoBackup = new JPanel(new FlowLayout());
		pAutoBackup.setBackground(Configuration.transparent);
		JLabel lbAutoBackup1 = new JLabel("Respaldo automático");
		lbAutoBackup1.setFont(fntLbBold);
		lbAutoBackup1.setForeground(fontColor);
		pAutoBackup.add(lbAutoBackup1);
		JLabel lbAutoBackup2 = new JLabel("antes de finalizar el programa:");
		lbAutoBackup2.setFont(fntLbPlain);
		lbAutoBackup2.setForeground(fontColor);
		pAutoBackup.add(lbAutoBackup2);
		btAutoBackupON = new JButton("ON");
		btAutoBackupON.setFont(fntBt);
		btAutoBackupON.setBackground(buttonColor);
		btAutoBackupON.setForeground(fontColor);
		btAutoBackupON.setPreferredSize(dimBt);
		btAutoBackupOFF = new JButton("OFF");
		btAutoBackupOFF.setFont(fntBt);
		btAutoBackupOFF.setBackground(buttonColor);
		btAutoBackupOFF.setForeground(fontColor);
		btAutoBackupOFF.setPreferredSize(dimBt);
		
		JPanel pThemes = new JPanel(new FlowLayout());
		pThemes.setBackground(Configuration.transparent);
		JLabel lbThemes = new JLabel("Tema del programa (requiere reinicio): ");
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
		
		JPanel pPass = new JPanel(new GridLayout(5,1,5,3));
		pPass.setBackground(Configuration.getPrimaryColor());
		pPass.setPreferredSize(new Dimension(560,180));
		JPanel pPassInfo = new JPanel(new FlowLayout());
		pPassInfo.setBackground(Configuration.getPrimaryColor());
		JLabel lbChangePass = new JLabel("Cambiar contraseña.");
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
		pSubPass.setBackground(Configuration.transparent);
		JLabel lbPass = new JLabel("Contraseña actual:");
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
		pSubNewPass.setBackground(Configuration.transparent);
		JLabel lbNewPass = new JLabel("Contraseña nueva:");
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
		pSubConfirmNewPass.setBackground(Configuration.transparent);
		JLabel lbConfirmNewPass = new JLabel("Confirmar contraseña nueva:");
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
		pButtonPass.setBackground(Configuration.transparent);
		btChange = new JButton("Cambiar");
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
		
		JPanel pOptions = new JPanel(new FlowLayout());
		pOptions.setBackground(Configuration.transparent);
		btAccept = new JButton("Aplicar");
		btAccept.setFont(fntBt);
		btAccept.setBackground(buttonColor);
		btAccept.setForeground(fontColor);
		btAccept.setPreferredSize(new Dimension(140,22));
		btReturn = new JButton("Regresar");
		btReturn.setFont(fntBt);
		btReturn.setBackground(btAccept.getBackground());
		btReturn.setForeground(btAccept.getForeground());
		btReturn.setPreferredSize(btAccept.getPreferredSize());
		pOptions.add(btAccept);
		pOptions.add(btReturn);
		
		GridBagConstraints support = new GridBagConstraints();
		support.insets = new Insets(0,0,14,0);
		support.gridx = 0;
		support.gridy = 0;
		support.gridwidth = 3;
		add(lbTitle,support);
		support.insets = new Insets(2,2,0,2);
		support.gridy++;
		support.gridwidth = 1;
		add(pAutoSave,support);
		support.gridx = 1;
		add(btAutoSaveON,support);
		support.gridx = 2;
		add(btAutoSaveOFF,support);
		support.gridy++;
		support.gridx = 0;
		add(pAutoBackup,support);
		support.gridx = 1;
		add(btAutoBackupON,support);
		support.gridx = 2;
		add(btAutoBackupOFF,support);
		support.gridy++;
		support.gridx = 0;
		support.gridwidth = 3;
		add(pThemes,support);
		support.gridy++;
		add(pPass,support);
		support.insets = new Insets(10,0,0,0);
		support.gridy++;
		add(pOptions,support);
	}
	
	public void enablePassMessage(String message, Color color){
		lbPassMessage.setForeground(color);
		lbPassMessage.setText(message);
		lbPassMessage.setVisible(true);
	}
	
	public void disablePassMessage(){
		lbPassMessage.setVisible(false);
		lbPassMessage.setForeground(Color.white);
		lbPassMessage.setText("This is an easter egg XD");
	}
}

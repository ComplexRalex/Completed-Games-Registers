package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import exception.CouldNotSaveFileException;
import model.Configuration;
import model.Languages;
import view.ConfigurationPanel;

public class ConfigurationController implements ActionListener, FocusListener, KeyListener{
	
	private ConfigurationPanel view;
	private Configuration model;
	private Object selectedButton;
	
	/*
	 * PASOS SIGUIENTES PARA NO PERDERSE:
	 * 1. Arreglar el main (para eso se tiene que crear el controlador principal)
	 * 2. Hacer una especie de "switches ON/FF" con los botones... ON/OFF
	 * 3. De principio deben estar seleccionadas las casillas si es que esas configuraciones
	 * 	estaban ya activadas (obetner de modelo)
	 * 4. Configurar acciones o eventos en el modelo segun los botones de la vista
	 * */
	public ConfigurationController(Configuration m, ConfigurationPanel v){
		model = m;
		view = v;
	}
	
	public void initialize(){
		view.btAutoSaveON.addActionListener(this);
		view.btAutoSaveON.addFocusListener(this);
		view.btAutoSaveON.addKeyListener(this);
		view.btAutoSaveOFF.addActionListener(this);
		view.btAutoSaveOFF.addFocusListener(this);
		view.btAutoSaveOFF.addKeyListener(this);
		
		view.btAutoBackupON.addActionListener(this);
		view.btAutoBackupON.addFocusListener(this);
		view.btAutoBackupON.addKeyListener(this);
		view.btAutoBackupOFF.addActionListener(this);
		view.btAutoBackupOFF.addFocusListener(this);
		view.btAutoBackupOFF.addKeyListener(this);
		
		for(JRadioButton bt: view.btTheme){
			bt.addActionListener(this);
			bt.addFocusListener(this);
			bt.addKeyListener(this);
		}
		
		view.cbLang.addActionListener(this);
		
		view.txtPass.addFocusListener(this);
		view.txtPass.addKeyListener(this);
		view.txtNewPass.addFocusListener(this);
		view.txtNewPass.addKeyListener(this);
		view.txtConfirmNewPass.addFocusListener(this);
		view.txtConfirmNewPass.addKeyListener(this);
		view.btChange.addActionListener(this);
		view.btChange.addFocusListener(this);
		view.btChange.addKeyListener(this);
		
		view.btAccept.addActionListener(this);
		view.btAccept.addFocusListener(this);
		view.btAccept.addKeyListener(this);
		view.btReturn.addActionListener(this);
		view.btReturn.addFocusListener(this);
		view.btReturn.addKeyListener(this);
		
		obtainInitialConfig();
		
		selectedButton = view.btReturn;
		view.btReturn.requestFocusInWindow();
	}
	
	public void obtainInitialConfig(){
		if(model.getAutoSave()) view.toggleEnabledButton(view.btAutoSaveON, false, Configuration.colorON);
		else view.toggleEnabledButton(view.btAutoSaveOFF, false, Configuration.colorOFF);
		
		if(model.getAutoBackup()) view.toggleEnabledButton(view.btAutoBackupON, false, Configuration.colorON);
		else view.toggleEnabledButton(view.btAutoBackupOFF, false, Configuration.colorOFF);
		
		view.btTheme[model.getTheme()].setSelected(true);
		
		view.cbLang.setSelectedItem(Configuration.currentLanguage());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == view.btAutoSaveON || source == view.btAutoSaveOFF)
			if(source == view.btAutoSaveON){
				view.toggleEnabledButton(view.btAutoSaveON, false, Configuration.colorON);
				view.toggleEnabledButton(view.btAutoSaveOFF, true, Configuration.getButtonColor());
				view.btAutoSaveOFF.requestFocusInWindow();
				model.enableAutoSave(true);
			}else{
				view.toggleEnabledButton(view.btAutoSaveON, true, Configuration.getButtonColor());
				view.toggleEnabledButton(view.btAutoSaveOFF, false, Configuration.colorOFF);
				view.btAutoSaveON.requestFocusInWindow();
				model.enableAutoSave(false);
			}
		if(source == view.btAutoBackupON || source == view.btAutoBackupOFF)
			if(source == view.btAutoBackupON){
				view.toggleEnabledButton(view.btAutoBackupON, false, Configuration.colorON);
				view.toggleEnabledButton(view.btAutoBackupOFF, true, Configuration.getButtonColor());
				view.btAutoBackupOFF.requestFocusInWindow();
				model.enableAutoBackup(true);
			}else{
				view.toggleEnabledButton(view.btAutoBackupON, true, Configuration.getButtonColor());
				view.toggleEnabledButton(view.btAutoBackupOFF, false, Configuration.colorOFF);
				view.btAutoBackupON.requestFocusInWindow();
				model.enableAutoBackup(false);
			}
		if(source instanceof JRadioButton)
			for(int i = 0; i < 3; i++)
				if(source == view.btTheme[i]){
					model.changeTheme(i);
					break;
				}
		if(source == view.cbLang)
			model.setLanguage((String)view.cbLang.getSelectedItem());
		if(source == view.btChange){
			if(model.checkPassword(view.txtPass.getPassword())){
				if(Configuration.checkPasswords(view.txtNewPass.getPassword(), view.txtConfirmNewPass.getPassword())){
					model.changePassword(view.txtNewPass.getPassword());
					view.enablePassMessage(Languages.loadMessage("cf_pass_ad_fy"),Color.green);
				}else
					view.enablePassMessage(Languages.loadMessage("cf_pass_ad_hn_1"),Color.red);
			}else
				view.enablePassMessage(Languages.loadMessage("cf_pass_ad_hn_2"),Color.red);
		}else
			view.disablePassMessage();
		if(source == view.btAccept){
			try {
				model.saveConfiguration();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CouldNotSaveFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(source == view.btReturn)
			JOptionPane.showMessageDialog(view, "Aun no se ha implementado todo :)");
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == view.txtPass || e.getSource() == view.txtNewPass || e.getSource() == view.txtConfirmNewPass)
			selectedButton = view.btChange;
		else
			selectedButton = e.getSource();
	}

	@Override
	public void focusLost(FocusEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
			((AbstractButton)selectedButton).doClick();
		if(e.getSource() != view.txtPass && e.getSource() != view.txtNewPass && e.getSource() != view.txtConfirmNewPass)
			e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}

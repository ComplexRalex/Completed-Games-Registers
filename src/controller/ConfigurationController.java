package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.AbstractButton;
import javax.swing.JRadioButton;

import exception.CouldNotSaveFileException;
import model.Configuration;
import view.ConfigurationPanel;

import util.Colour;
import util.Component;
import util.Language;
import util.Advice;

public class ConfigurationController implements ActionListener, FocusListener, KeyListener{
	
	private ConfigurationPanel view;
	private Configuration model;
	private Object selectedButton;
	private int autoSaveStatus;
	private int autoBackupStatus;
	
	/*
	 * PASOS SIGUIENTES PARA NO PERDERSE:
	 * 1. Arreglar el main (para eso se tiene que crear el controlador principal)
	 * */
	public ConfigurationController(Configuration m, ConfigurationPanel v){
		model = m;
		view = v;
	}
	
	public void initialize(){
		view.txtUser.addActionListener(this);
		view.txtUser.addFocusListener(this);
		view.txtUser.addKeyListener(this);
		
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

		view.btResetConfig.addActionListener(this);
		view.btResetConfig.addFocusListener(this);
		view.btResetConfig.addKeyListener(this);
		view.btWipeOut.addActionListener(this);
		view.btWipeOut.addFocusListener(this);
		view.btWipeOut.addKeyListener(this);
		
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
		view.txtUser.setText(Configuration.getUsername());
		
		if(Configuration.getAutoSave()){
			Component.toggleEnabledButton(view.btAutoSaveON, false, Colour.colorON);
			autoSaveStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btAutoSaveOFF, false, Colour.colorOFF);
			autoSaveStatus = 0;
		}

		if(Configuration.getAutoBackup()){
			Component.toggleEnabledButton(view.btAutoBackupON, false, Colour.colorON);
			autoBackupStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btAutoBackupOFF, false, Colour.colorOFF);
			autoBackupStatus = 0;
		}
		view.btTheme[Configuration.currentTheme()].setSelected(true);
		
		view.cbLang.setSelectedItem(Configuration.currentLanguage());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int value;

		autoSaveStatus = ((value = Component.runSwitchButtonEffect(e, view.btAutoSaveON, view.btAutoSaveOFF)) != -1) ? value : autoSaveStatus; 

		autoBackupStatus = ((value = Component.runSwitchButtonEffect(e, view.btAutoBackupON, view.btAutoBackupOFF)) != -1) ? value : autoBackupStatus;
		
		if(source == view.btResetConfig || source == view.btWipeOut){
			Advice.showSimpleAdvice(
				view,
				"What did just happen?!",
				"Not implemented yet.. but soon!... bye the way,"+
				" the index of the option you chose was: "+
					Advice.showOptionAdvice(
						view,
						Language.loadMessage("g_message"),
						Language.loadMessage("cf_yousure"),
						new String[]{Language.loadMessage("g_accept"),Language.loadMessage("g_cancel")},
						Colour.getPrimaryColor()
					),
				Colour.getPrimaryColor()
			);
		}

		if(source == view.btAccept){

			// Change username (if qualify)
			if(view.txtUser.getText().length() > 0){
				String cut = view.txtUser.getText().length() > 25 ? view.txtUser.getText().substring(0, 25) : view.txtUser.getText();
				view.txtUser.setText(cut);
				model.setUsername(cut);
			}

			// Toggle enable/disable autoSave option
			switch(autoSaveStatus){
				case 1: model.enableAutoSave(true); break;
				case 0: model.enableAutoSave(false);
			}

			// Toggle enable/disable autoBackup option
			switch(autoBackupStatus){
				case 1: model.enableAutoBackup(true); break;
				case 0: model.enableAutoBackup(false);
			}

			// Change to selected theme
			for(int i = 0; i < view.btTheme.length; i++){
				if(view.btTheme[i].isSelected()){
					model.changeTheme(i);
					break;
				}
			}

			// Change to selected language
			model.changeLanguage((String)view.cbLang.getSelectedItem());

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
		if(source == view.btReturn){
			Advice.showSimpleAdvice(view, Language.loadMessage("g_message"), Language.loadMessage("g_restricted"), Language.loadMessage("m_chale"), Colour.getPrimaryColor());
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == view.txtUser)
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
		if(e.getSource() != view.txtUser)
			e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JRadioButton;

import exception.CouldNotSaveFileException;
import model.Configuration;
import view.ConfigurationPanel;

import util.Colour;
import util.Component;
import util.Language;
import util.Advice;

public class ConfigurationController implements ActionListener{
	
	private MainController parent;
	private ConfigurationPanel view;
	private Configuration model;
	private int autoSaveStatus;
	private int autoBackupStatus;
	
	/*
	 * PASOS SIGUIENTES PARA NO PERDERSE:
	 * 1. Arreglar el main (para eso se tiene que crear el controlador principal)
	 * */
	public ConfigurationController(Configuration m, ConfigurationPanel v, MainController p){
		model = m;
		view = v;
		parent = p;
	}
	
	public void initialize(){
		view.btAutoSaveON.addActionListener(this);
		view.btAutoSaveOFF.addActionListener(this);
		
		view.btAutoBackupON.addActionListener(this);
		view.btAutoBackupOFF.addActionListener(this);
		
		for(JRadioButton bt: view.btTheme)
			bt.addActionListener(this);

		view.cbLang.addActionListener(this);

		view.btResetConfig.addActionListener(this);
		view.btWipeOut.addActionListener(this);
		
		view.btAccept.addActionListener(this);
		view.btReturn.addActionListener(this);
		
		obtainInitialConfig();
	}
	
	public void obtainInitialConfig(){
		view.txtUser.setText(Configuration.getUsername());
		
		if(Configuration.getAutoSave()){
			Component.toggleEnabledButton(view.btAutoSaveON, false, Colour.colorON);
			Component.toggleEnabledButton(view.btAutoSaveOFF, true, Colour.getButtonColor());
			autoSaveStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btAutoSaveON, true, Colour.getButtonColor());
			Component.toggleEnabledButton(view.btAutoSaveOFF, false, Colour.colorOFF);
			autoSaveStatus = 0;
		}

		if(Configuration.getAutoBackup()){
			Component.toggleEnabledButton(view.btAutoBackupON, false, Colour.colorON);
			Component.toggleEnabledButton(view.btAutoBackupOFF, true, Colour.getButtonColor());
			autoBackupStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btAutoBackupON, true, Colour.getButtonColor());
			Component.toggleEnabledButton(view.btAutoBackupOFF, false, Colour.colorOFF);
			autoBackupStatus = 0;
		}
		view.btTheme[Configuration.currentTheme()].setSelected(true);
		
		view.cbLang.setSelectedItem(Configuration.currentLanguage());
	}

	private boolean sameValues(){
		boolean flag = true;
		flag = (flag && Configuration.getUsername().equals(view.txtUser.getText().length() > 25 ? view.txtUser.getText().substring(0, 25) : view.txtUser.getText()));
		flag = (flag && (Configuration.getAutoSave() == (autoSaveStatus == 1)));
		flag = (flag && (Configuration.getAutoBackup() == (autoBackupStatus == 1)));
				
		return flag;
	}
	
	private boolean resetRequest(){
		boolean flag = true;
		for(int i = 0; i < view.btTheme.length; i++){
			if(view.btTheme[i].isSelected()){
				flag = (flag && (Configuration.currentTheme() == i));
				break;
			}
		}
		flag = (flag && Configuration.currentLanguage().equals((String)view.cbLang.getSelectedItem()));

		return !flag;
	}

	private void saveCurrentSettings(){
		// Change username (if qualify)
		if(view.txtUser.getText().length() > 0){
			String cut = view.txtUser.getText().length() > 25 ? view.txtUser.getText().substring(0, 25) : view.txtUser.getText();
			view.txtUser.setText(cut);
			model.setUsername(cut);
			parent.frame.pGeneral.lbUser.setText(cut);
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

		saveSettings();
	}

	private void saveSettings(){
		try {
			model.saveConfiguration();
		} catch (FileNotFoundException | CouldNotSaveFileException e1) {
			Advice.showTextAreaAdvice(
					null,
					Language.loadMessage("g_oops"),
					Language.loadMessage("g_wentwrong"),
					e1.toString(),
					Language.loadMessage("g_accept"),
					Colour.getPrimaryColor()
			);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int value;

		autoSaveStatus = ((value = Component.runSwitchButtonEffect(e, view.btAutoSaveON, view.btAutoSaveOFF)) != -1) ? value : autoSaveStatus; 

		autoBackupStatus = ((value = Component.runSwitchButtonEffect(e, view.btAutoBackupON, view.btAutoBackupOFF)) != -1) ? value : autoBackupStatus;
		
		if(source == view.btResetConfig){
			if(Advice.showOptionAdvice(
				null,
				Language.loadMessage("g_message"),
				Language.loadMessage("cf_yousure"),
				new String[]{Language.loadMessage("g_accept"),Language.loadMessage("g_cancel")},
				Colour.getPrimaryColor()
			) == 0){
				Advice.showSimpleAdvice(
					null,
					Language.loadMessage("g_message"),
					Language.loadMessage("cf_reset"),
					Language.loadMessage("g_accept"),
					Colour.getPrimaryColor()
				);
				model.setDefaultValues();
				saveSettings();
				parent.reset();
			}
		}

		if(source == view.btWipeOut) Advice.showSimpleAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_indev"), Language.loadMessage("g_accept"), Colour.getPrimaryColor());

		if(source == view.btAccept){
			if(resetRequest()){
				if(Advice.showOptionAdvice(
					null,
					Language.loadMessage("g_warning"),
					Language.loadMessage("cf_reset"),
					new String[]{
						Language.loadMessage("g_accept"),
						Language.loadMessage("g_cancel")
					},
					Colour.getPrimaryColor()
				) == 0){
					saveCurrentSettings();
					parent.reset();
				}
			} else if(!sameValues()){
				saveCurrentSettings();
				Advice.showSimpleAdvice(null, Language.loadMessage("g_success"), Language.loadMessage("cf_success"), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
			} else
				Advice.showSimpleAdvice(null, Language.loadMessage("g_message"), Language.loadMessage("cf_no_edit"), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		}
		if(source == view.btReturn){
			if(sameValues() && !resetRequest())
				parent.frame.changePanel(parent.frame.pGeneral);
			else
				if(Advice.showOptionAdvice(
					null,Language.loadMessage("g_warning"),
					Language.loadMessage("g_unsaved"),
					new String[]{
						Language.loadMessage("g_accept"),
						Language.loadMessage("g_cancel")
					},
					Colour.getPrimaryColor()
				) == 0){
					obtainInitialConfig();
					parent.frame.changePanel(parent.frame.pGeneral);
				}
		}
	}

}

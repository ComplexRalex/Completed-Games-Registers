package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JRadioButton;

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
	private int autoBackupStatus;
	private int exitDialogStatus;
	private int maxLength;
	
	public ConfigurationController(Configuration m, ConfigurationPanel v, MainController p){
		model = m;
		view = v;
		parent = p;
		maxLength = 35;
	}
	
	public void initialize(){
		view.btAutoBackupON.addActionListener(this);
		view.btAutoBackupOFF.addActionListener(this);
		
		view.btExitDialogON.addActionListener(this);
		view.btExitDialogOFF.addActionListener(this);
		
		for(JRadioButton bt: view.btTheme)
			bt.addActionListener(this);

		view.cbLang.addActionListener(this);

		view.btSuddenClose.addActionListener(this);
		view.btResetConfig.addActionListener(this);
		view.btResetSave.addActionListener(this);
		view.btDeleteGameInfo.addActionListener(this);
		view.btResetBackups.addActionListener(this);
		view.btWipeOut.addActionListener(this);
		
		view.btAccept.addActionListener(this);
		view.btReturn.addActionListener(this);
		
		obtainInitialConfig();
	}
	
	private void obtainInitialConfig(){
		view.txtUser.setText(parent.mConfig.getUsername());
		
		if(parent.mConfig.getAutoBackup()){
			Component.toggleEnabledButton(view.btAutoBackupON, false, Colour.colorON);
			Component.toggleEnabledButton(view.btAutoBackupOFF, true, Colour.getButtonColor());
			autoBackupStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btAutoBackupON, true, Colour.getButtonColor());
			Component.toggleEnabledButton(view.btAutoBackupOFF, false, Colour.colorOFF);
			autoBackupStatus = 0;
		}
		
		if(parent.mConfig.getExitDialog()){
			Component.toggleEnabledButton(view.btExitDialogON, false, Colour.colorON);
			Component.toggleEnabledButton(view.btExitDialogOFF, true, Colour.getButtonColor());
			exitDialogStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btExitDialogON, true, Colour.getButtonColor());
			Component.toggleEnabledButton(view.btExitDialogOFF, false, Colour.colorOFF);
			exitDialogStatus = 0;
		}
		view.btTheme[parent.mConfig.currentTheme()].setSelected(true);
		
		view.cbLang.setSelectedItem(parent.mConfig.currentLanguage());
	}

	private boolean sameValues(){
		boolean flag = true;
		flag = (flag && parent.mConfig.getUsername().equals(view.txtUser.getText().length() > maxLength ? view.txtUser.getText().substring(0, maxLength) : view.txtUser.getText()));
		flag = (flag && (parent.mConfig.getAutoBackup() == (autoBackupStatus == 1)));
		flag = (flag && (parent.mConfig.getExitDialog() == (exitDialogStatus == 1)));
				
		return flag;
	}
	
	private boolean resetRequest(){
		boolean flag = true;
		for(int i = 0; i < view.btTheme.length; i++){
			if(view.btTheme[i].isSelected()){
				flag = (flag && (parent.mConfig.currentTheme() == i));
				break;
			}
		}
		flag = (flag && parent.mConfig.currentLanguage().equals((String)view.cbLang.getSelectedItem()));

		return !flag;
	}

	private void saveCurrentSettings(){
		// Change username (if qualify)
		if(view.txtUser.getText().length() > 0){
			String cut = view.txtUser.getText().length() > maxLength ? view.txtUser.getText().substring(0, maxLength) : view.txtUser.getText();
			view.txtUser.setText(cut);
			model.setUsername(cut);
			parent.frame.pGeneral.lbUser.setText(cut);
		}
		
		// Toggle enable/disable autoBackup option
		switch(autoBackupStatus){
			case 1: model.enableAutoBackup(true); break;
			case 0: model.enableAutoBackup(false);
		}

		// Toggle enable/disable exitDialog option
		switch(exitDialogStatus){
			case 1: model.enableExitDialog(true); break;
			case 0: model.enableExitDialog(false);
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

	public void saveSettings(){
		try {
			model.saveConfiguration();
		} catch (IOException e) {
			Advice.showTextAreaAdvice(
				parent.frame,
				Language.loadMessage("g_oops"),
				Language.loadMessage("g_went_wrong"),
				e.toString(),
				Language.loadMessage("g_accept"),
				Colour.getPrimaryColor()
			);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int value;
		
		autoBackupStatus = ((value = Component.runSwitchButtonEffect(e, view.btAutoBackupON, view.btAutoBackupOFF)) != -1) ? value : autoBackupStatus;
		
		exitDialogStatus = ((value = Component.runSwitchButtonEffect(e, view.btExitDialogON, view.btExitDialogOFF)) != -1) ? value : exitDialogStatus; 
		
		if(source == view.btSuddenClose)
			parent.suddenClose();
		else if(source == view.btResetConfig || source == view.btResetSave || source == view.btWipeOut){
			if(Advice.showOptionAdvice(
				parent.frame,
				Language.loadMessage("g_message"),
				Language.loadMessage("cf_yousure"),
				new String[]{
					Language.loadMessage("g_accept"),
					Language.loadMessage("g_cancel")
				},
				Colour.getPrimaryColor()
			) == 0){
				Advice.showSimpleAdvice(
					parent.frame,
					Language.loadMessage("g_message"),
					Language.loadMessage("cf_reset"),
					Language.loadMessage("g_accept"),
					Colour.getPrimaryColor()
				);
				if(source == view.btResetConfig)
					parent.resetConfig();
				else if(source == view.btResetSave){
					parent.resetStats();
					parent.deleteDownloadedInfo();
				}else{
					parent.resetConfig();
					parent.resetStats();
					parent.deleteDownloadedInfo();
					parent.deleteBackups();
				}
				parent.reset();
			}
		}else if(source == view.btResetBackups || source == view.btDeleteGameInfo){
			if(source == view.btResetBackups)
				parent.deleteBackups();
			else
				parent.deleteDownloadedInfo();
			Advice.showSimpleAdvice(
				parent.frame,
				Language.loadMessage("g_success"),
				Language.loadMessage("g_done"),
				Language.loadMessage("g_accept"), 
				Colour.getPrimaryColor()
			);
		}else if(source == view.btAccept){
			if(resetRequest()){
				if(Advice.showOptionAdvice(
					parent.frame,
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
			}else if(!sameValues()){
				saveCurrentSettings();
				Advice.showSimpleAdvice(
					parent.frame,
					Language.loadMessage("g_success"),
					Language.loadMessage("cf_success"),
					Language.loadMessage("g_accept"),
					Colour.getPrimaryColor()
				);
			}else
				Advice.showSimpleAdvice(
					parent.frame,
					Language.loadMessage("g_message"), 
					Language.loadMessage("cf_no_edit"),
					Language.loadMessage("g_accept"),
					Colour.getPrimaryColor()
				);
		}else if(source == view.btReturn){
			if(sameValues() && !resetRequest())
				parent.frame.changePanel(parent.frame.pGeneral);
			else
				if(Advice.showOptionAdvice(
					parent.frame,
					Language.loadMessage("g_warning"),
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

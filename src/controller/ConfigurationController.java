/**
 * Completed-Games Registers, a software where you can record every
 * game you have beaten (completed) so far!
 * Copyright (C) 2020  Alejandro Batres
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * Contact by email: alejandro.batres37@gmail.com
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JRadioButton;

import model.Configuration;
import model.GameData;
import view.ConfigurationPanel;

import util.Colour;
import util.Component;
import util.Language;
import util.Advice;

/**
 * <h3>ConfigurationController controller class.</h3>
 * This class implements the {@link ActionListener} and
 * {@link KeyListener} interfaces and is used to manage
 * the actions of the visual components of {@link ConfigurationPanel}.
 * <p>
 * It uses a instance of {@link Configuration} which is used
 * to give logic functions to some of the visual components.
 * 
 * @author Alejandro Batres
 * @see Configuration
 * @see ConfigurationPanel
 */
public class ConfigurationController implements ActionListener, KeyListener{
	
	/**
     * Parent controller.
     */
	private MainController parent;

	/**
     * Logic of the controller.
     */
	private Configuration model;

	/**
     * Attached panel to the controller.
     */
	private ConfigurationPanel view;

	/**
	 * Temporal number which determines the current status
	 * of the "auto backup" switch option.
	 */
	private int autoBackupStatus;

	/**
	 * Temporal number which determines the current status
	 * of the "exit dialog" switch option.
	 */
	private int exitDialogStatus;

	/**
	 * Number of maximum characters in a String.
	 */
	private int maxLength;
	
	/**
     * Constructor of the ConfigurationController class.
     * 
     * @param m Logic of the controller
     * @param v Set of visual components
     * @param p Parent controller
     */
	public ConfigurationController(Configuration m, ConfigurationPanel v, MainController p){
		model = m;
		view = v;
		parent = p;
		maxLength = 45;
	}
	
	/**
     * Sets listeners to all the visual "action" components
     * in the {@link #view}.
     * <p>
     * Also, it will call {@link #obtainInitialConfig()}.
     * 
     * @see #obtainInitialConfig()
     */
	public void initialize(){
		view.txtUser.addKeyListener(this);

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
		view.btResetExports.addActionListener(this);
		view.btWipeOut.addActionListener(this);
		
		view.btAccept.addActionListener(this);
		view.btReturn.addActionListener(this);
		
		obtainInitialConfig();
	}
	
	/**
	 * Initializes the visual components with the current
     * values retrieved from the {@link #model} object.
     * <p>
	 * In this case, will fill the content of the visual
	 * components with their respective field in the
	 * {@link #model} object.
	 */
	public void obtainInitialConfig(){
		view.txtUser.setText(model.getUsername());
		
		if(model.getAutoBackup()){
			Component.toggleEnabledButton(view.btAutoBackupON, false, Colour.colorON);
			Component.toggleEnabledButton(view.btAutoBackupOFF, true, Colour.getButtonColor());
			autoBackupStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btAutoBackupON, true, Colour.getButtonColor());
			Component.toggleEnabledButton(view.btAutoBackupOFF, false, Colour.colorOFF);
			autoBackupStatus = 0;
		}
		
		if(model.getExitDialog()){
			Component.toggleEnabledButton(view.btExitDialogON, false, Colour.colorON);
			Component.toggleEnabledButton(view.btExitDialogOFF, true, Colour.getButtonColor());
			exitDialogStatus = 1;
		}else{
			Component.toggleEnabledButton(view.btExitDialogON, true, Colour.getButtonColor());
			Component.toggleEnabledButton(view.btExitDialogOFF, false, Colour.colorOFF);
			exitDialogStatus = 0;
		}
		view.btTheme[model.currentTheme()].setSelected(true);
		
		view.cbLang.setSelectedItem(model.currentLanguage());

		view.spConnect.setValue(model.getConnectionTimeout());
		view.spRead.setValue(model.getReadTimeout());
	}

	/**
     * Tells if there were not changes made in the
     * current session.
	 * This will compare the entered values with the
	 * saved data in the {@link #model} variable.
	 * 
     * @return {@code true} if the values are the
     * same as at the beginning. {@code false} otherwise.
     */
	private boolean sameValues(){
		boolean flag = true;
		flag = (flag && model.getUsername().equals(view.txtUser.getText().trim()));
		flag = (flag && (model.getAutoBackup() == (autoBackupStatus == 1)));
		flag = (flag && (model.getExitDialog() == (exitDialogStatus == 1)));
		flag = (flag && model.getConnectionTimeout() == (int)view.spConnect.getValue());
		flag = (flag && model.getReadTimeout() == (int)view.spRead.getValue());
				
		return flag;
	}
	
	/**
	 * Tells if it is necessary to reset the program
	 * to save the entered settings an to take effect.
	 * 
	 * @return {@code true} if there were changes made
     * in the settings which require a reset. {@code false}
	 * otherwise.
	 */
	private boolean resetRequest(){
		boolean flag = true;
		for(int i = 0; i < view.btTheme.length; i++){
			if(view.btTheme[i].isSelected()){
				flag = (flag && (model.currentTheme() == i));
				break;
			}
		}
		flag = (flag && model.currentLanguage().equals((String)view.cbLang.getSelectedItem()));

		return !flag;
	}

	/**
	 * Updates all the {@link #model} variables related with
	 * its specific fields in the visual components.
	 * At the end, it will call {@link #saveSettings()} to
	 * finally save the changes.
	 * 
	 * @see #saveSettings()
	 */
	private void saveCurrentSettings(){
		// Change username (if qualify)
		if(view.txtUser.getText().length() > 0){
			String cut = view.txtUser.getText().trim();
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

		model.setConnectionTimeout((int)view.spConnect.getValue());
		model.setReadTimeout((int)view.spRead.getValue());
		
		// External operations
		GameData.setConnectionTimeout((int)view.spConnect.getValue());
		GameData.setReadTimeout((int)view.spRead.getValue());

		saveSettings();
	}

	/**
	 * Calls {@link Configuration#saveConfiguration()} method.
	 */
	public void saveSettings(){
		try {
			model.saveConfiguration();
		} catch (IOException e) {
			Advice.showTextAreaAdvice(
				parent.frame,
				Language.loadMessage("g_oops"),
				Language.loadMessage("g_went_wrong"),
				Advice.getStackTrace(e), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
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
					parent.deleteExports();
				}
				parent.reset();
			}
		}else if(source == view.btResetBackups || source == view.btResetExports || source == view.btDeleteGameInfo){
			if(source == view.btResetBackups)
				parent.deleteBackups();
			else if(source == view.btResetExports)
				parent.deleteExports();
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
			}else{
				saveCurrentSettings();
				Advice.showSimpleAdvice(
					parent.frame,
					Language.loadMessage("g_success"),
					Language.loadMessage("cf_success"),
					Language.loadMessage("g_accept"),
					Colour.getPrimaryColor()
				);
			}
		}else if(source == view.btReturn){
			if(sameValues() && !resetRequest())
				parent.frame.changePanel(parent.frame.pGeneral,null);
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
					parent.frame.changePanel(parent.frame.pGeneral,null);
				}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_SPACE && view.txtUser.getCaretPosition() == 0)
			e.consume();
		if(view.txtUser.getText().length() >= maxLength &&
		view.txtUser.getText().length() - (view.txtUser.getSelectedText() == null ? 0 : view.txtUser.getSelectedText().length()) >= maxLength)
			e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}

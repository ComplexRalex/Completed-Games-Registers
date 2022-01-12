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

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import util.Colour;
import util.Language;
import util.Path;

/**
 * <h3>Configuration model class.</h3>
 * This class sets the settings of the current session of the
 * program. These are the available options:
 * <ul>
 * <li><b>{@link #username}</b>: String which contains the username
 * <li><b>{@link #autoBackup}</b>:  Boolean which determines if the
 * auto backup is enabled
 * <li><b>{@link #exitDialog}</b>: Boolean which determines if the
 * exit dialog is enabled
 * <li><b>{@link #theme}</b>: Number corresponding to the theme of
 * program
 * <li><b>{@link #lang}</b>: String which contains the name of the
 * current language
 * <li><b>{@link #connectionTimeout}</b>: The connect timeout limit
 * <li><b>{@link #readTimeout}</b>: The read timeout limit
 * </ul>
 * Some of the {@link Path} variables are used to save and load
 * the configuration file.
 * 
 * @author Alejandro Batres
 * @see Path
 * @see Colour
 * @see Language
 */
public class Configuration{

	/**
	 * String which determines the username.
	 * 
	 * @see #getUsername()
	 * @see #setUsername(String)
	 */
	private String username;

	/**
	 * Boolean which determines if the auto backup is enabled
	 * (true) or not (false).
	 * 
	 * @see #getAutoBackup()
	 * @see #enableAutoBackup(boolean)
	 */
	private boolean autoBackup;


	/**
	 * Boolean which determines if the confirm backup dialog
	 * is enabled (true) or not (false).
	 * 
	 * @see #getConfirmBackupDialog()
	 * @see #enableConfirmBackupDialog() 
	 */
	private boolean confirmBackup;


	/**
	 * Boolean which determines if the confirm export dialog
	 * is enabled (true) or not (false).
	 * 
	 * @see #getConfirmExportDialog()
	 * @see #enableConfirmExportDialog() 
	 */
	private boolean confirmExport;

	/**
	 * Boolean which determines if the exit dialog is enabled
	 * (true) or not (false).
	 * 
	 * @see #getExitDialog()
	 * @see #enableExitDialog(boolean)
	 */
	private boolean exitDialog;

	/**
	 * Integer (type <b>int</b>) which determines the theme of
	 * the visual components.
	 * 
	 * @see #currentTheme()
	 * @see #changeTheme(int)
	 */
	private int theme;

	/**
	 * String which determines the language displayed in the
	 * current session.
	 * 
	 * @see #currentLanguage()
	 * @see #changeLanguage(String)
	 */
	private String lang;

	/**
	 * Integer which determines the <i>connect</i> tiemout when a
	 * connection is being established.
	 * 
	 * @see #getConnectionTimeout()
	 * @see #setConnectionTimeout(int)
	 */
	private int connectionTimeout;

	/**
	 * Integer which determines the <i>read</i> tiemout when a
	 * connection is being established.
	 * 
	 * @see #getReadTimeout()
	 * @see #setReadTimeout(int)
	 */
	private int readTimeout;
	
	/**
	 * Constructor of the Configuration class. This function just
	 * runs the function <b>setDefaultValues()</b>
	 * 
	 * @see #setDefaultValues()
	 */
	public Configuration(){
		setDefaultValues();
	}

	/**
	 * Initializes all attributes of the Configuration object with
	 * the default values. These values are the following:
	 * <ul>
	 * <li>{@link #username} = "Username"
	 * <li>{@link #autoBackup} = false
	 * <li>{@link #confirmBackup} = true
	 * <li>{@link #confirmExport} = true
	 * <li>{@link #exitDialog} = true
	 * <li>{@link #theme} = {@link Colour#NIGHT_THEME}
	 * <li>{@link #lang} = {@link Language#available}[0] (English)
	 * <li>{@link #connectionTimeout} = 5000
	 * <li>{@link #readTimeout} = 5000
	 * </ul>
	 * 
	 * @see Colour
	 * @see Language
	 */
	public void setDefaultValues(){
		username = "Username";
		autoBackup = false;
		exitDialog = true;
		confirmBackup = true;
		confirmExport = true;
		theme = Colour.NIGHT_THEME;
		lang = Language.available[0];
		connectionTimeout = 5000;
		readTimeout = 5000;
	}
	
	/**
	 * Creates an instance of <i>{@literal HashMap<String,Object>}</i>
	 * where the attributes of the current object will be entered to such
	 * instance as a pair Key-Value.
	 * <p>
	 * This is being used to prevent compatibility errors in possible 
	 * future updates of the program (in case of adding more settings).
	 * 
	 * @see #saveConfiguration()
	 * @return Instance of <i>{@literal HashMap<String,Object>}</i>
	 * containing the current settings (attributes of the object).
	 */
	private HashMap<String,Object> createHashMap(){
		HashMap<String,Object> hashMap = new HashMap<>();

		hashMap.put("username",username);
		hashMap.put("autoBackup",autoBackup);
		hashMap.put("confirmBackup",confirmBackup);
		hashMap.put("confirmExport",confirmExport);
		hashMap.put("exitDialog",exitDialog);
		hashMap.put("theme",theme);
		hashMap.put("lang",lang);
		hashMap.put("connectionTimeout",connectionTimeout);
		hashMap.put("readTimeout",readTimeout);

		return hashMap;
	}

	/**
	 * Retrives the stored settings from an instance of <i>{@literal
	 * HashMap<String,Object>}</i> to the attributes of the current
	 * Configuration object.
	 * <p>
	 * This is being used to prevent compatibility errors in possible 
	 * future updates of the program (in case of adding more settings).
	 * 
	 * @param hashMap HashMap containing the stored settings from a
	 * previous session.
	 * @see #createHashMap()
	 */
	private void copyConfigData(HashMap<String,Object> hashMap){
		if(hashMap.containsKey("username") && hashMap.get("username") != null)
			username = (String)hashMap.get("username");
		if(hashMap.containsKey("autoBackup") && hashMap.get("autoBackup") != null)
			autoBackup = (boolean)hashMap.get("autoBackup");
		if(hashMap.containsKey("confirmBackup") && hashMap.get("confirmBackup") != null)
			confirmBackup = (boolean)hashMap.get("confirmBackup");
		if(hashMap.containsKey("confirmExport") && hashMap.get("confirmExport") != null)
			confirmExport = (boolean)hashMap.get("confirmExport");
		if(hashMap.containsKey("exitDialog") && hashMap.get("exitDialog") != null)
			exitDialog = (boolean)hashMap.get("exitDialog");
		if(hashMap.containsKey("theme") && hashMap.get("theme") != null)
			theme = (int)hashMap.get("theme");
		if(hashMap.containsKey("lang") && hashMap.get("lang") != null)
			lang = (String)hashMap.get("lang");
		if(hashMap.containsKey("connectionTimeout") && hashMap.get("connectionTimeout") != null)
			connectionTimeout = (int)hashMap.get("connectionTimeout");
		if(hashMap.containsKey("readTimeout") && hashMap.get("readTimeout") != null)
			readTimeout = (int)hashMap.get("readTimeout");
	}

	/**
	 * Creates (or overwrites) a file in {@link Path#configFile} with
	 * the current settings.
	 * <p>
	 * This method is saving a HashMap object to give more
	 * compatibility in future updates.
	 * 
	 * @see #createHashMap()
	 * @throws IOException
	 */
	public void saveConfiguration() throws IOException {
		Path.resolve(Path.dataPath);
		FileOutputStream f = new FileOutputStream(Path.configFile);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(createHashMap());
		o.close();
	}
	
	/**
	 * Reads a file in {@link Path#configFile} and uses its
	 * data into the current settings.
	 * <p>
	 * This method is loading a HashMap object to give more
	 * compatibility in future updates.
	 * 
	 * @see #copyConfigData(HashMap)
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadConfiguration() throws ClassNotFoundException, IOException {
		Path.resolve(Path.dataPath);
		FileInputStream f = new FileInputStream(Path.configFile);
		ObjectInputStream o = new ObjectInputStream(f);
		copyConfigData((HashMap<String,Object>)o.readObject());
		o.close();
	}
	
	/**
	 * Returns the value of the username.
	 * 
	 * @return {@link #username}'s value
	 */
	public String getUsername(){
		return username;
	}

	/**
	 * Sets the username to the new value.
	 * 
	 * @param user New {@link #username}'s value
	 */
	public void setUsername(String user){
		username = user;
	}
	
	/**
	 * Returns if the auto backup is enabled (true) or not (false).
	 * 
	 * @return {@link #autoBackup}'s value
	 */
	public boolean getAutoBackup(){
		return autoBackup;
	}
	
	/**
	 * Sets if the auto backup will be enabled.
	 * 
	 * @param flag New {@link #autoBackup}'s value
	 */
	public void enableAutoBackup(boolean flag){
		autoBackup = flag;
	}

	/**
	 * Returns if the confirm backup dialog is enabled (true) or
	 * not (false).
	 * 
	 * @return {@link #confirmBackup}'s value 
	 */
	public boolean getConfirmBackupDialog(){
		return confirmBackup;
	}

	/**
	 * Sets if the confirm backup dialog will be enabled.
	 * 
	 * @param flag New {@link #confirmBackup}'s value
	 */
	public void enableConfirmBackupDialog(boolean flag){
		confirmBackup = flag;
	}

	/**
	 * Returns if the confirm export dialog is enabled (true) or
	 * not (false).
	 * 
	 * @return {@link #confirmExport}'s value 
	 */
	public boolean getConfirmExportDialog(){
		return confirmExport;
	}

	
	/**
	 * Sets if the confirm export dialog will be enabled.
	 * 
	 * @param flag New {@link #confirmExport}'s value
	 */
	public void enableConfirmExportDialog(boolean flag){
		confirmExport = flag;
	}
	
	/**
	 * Returns if the exit dialog is enabled (true) or not (false).
	 * 
	 * @return {@link #exitDialog}'s value
	 */
	public boolean getExitDialog(){
		return exitDialog;
	}
	
	/**
	 * Sets if the exit dialog will be displayed.
	 * 
	 * @param flag New {@link #exitDialog}'s value
	 */
	public void enableExitDialog(boolean flag){
		exitDialog = flag;
	}
	
	/**
	 * Returns the value of the current theme.
	 * 
	 * @return {@link #theme}'s value
	 */
	public int currentTheme(){
		return theme;
	}
	
	/**
	 * Sets the new theme of the next session.
	 * 
	 * @param theme New {@link #theme}'s value
	 */
	public void changeTheme(int theme){
		this.theme = theme;
	}
	
	/**
	 * Returns the value of the current language.
	 * 
	 * @return {@link #lang}'s value
	 */
	public String currentLanguage(){
		return lang;
	}

	/**
	 * Sets the new language of the next session.
	 * 
	 * @param lang New {@link #lang}'s value
	 */
	public void changeLanguage(String lang){
		for(String l: Language.available)
			if(lang == l){
				this.lang = lang;
				return;
			}
		this.lang = "English";
	}

	/**
	 * Returns the value of the connect timeout.
	 * 
	 * @return {@link #connectionTimeout}'s value
	 */
	public int getConnectionTimeout(){
		return connectionTimeout;
	}

	/**
	 * Sets the value of the connect timeout.
	 * 
	 * @param timeout New {@link #connectionTimeout}'s value
	 */
	public void setConnectionTimeout(int timeout){
		connectionTimeout = timeout;
	}

	/**
	 * Returns the value of read timeout.
	 * 
	 * @return {@link #readTimeout}'s value
	 */
	public int getReadTimeout(){
		return readTimeout;
	}

	/**
	 * Sets the value of the read timeout.
	 * 
	 * @param timeout New {@link #readTimeout}'s value
	 */
	public void setReadTimeout(int timeout){
		readTimeout = timeout;
	}
}

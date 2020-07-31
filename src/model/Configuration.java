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
 * <li><b>{@link #username}</b> (String)
 * <li><b>{@link #autoBackup}</b> (boolean)
 * <li><b>{@link #exitDialog}</b> (boolean)
 * <li><b>{@link #theme}</b> (int)
 * <li><b>{@link #lang}</b> (String)
 * <li><b>{@link #connectionTimeout}</b> (int)
 * <li><b>{@link #readTimeout}</b> (int)
 * </ul>
 * Some of the {@link Path} variables are used to save and load
 * the configuration file.
 * 
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
		hashMap.put("exitDialog",exitDialog);
		hashMap.put("autoBackup",autoBackup);
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
		username = (String)hashMap.get("username");
		exitDialog = (boolean)hashMap.get("exitDialog");
		autoBackup = (boolean)hashMap.get("autoBackup");
		theme = (int)hashMap.get("theme");
		lang = (String)hashMap.get("lang");
		connectionTimeout = (int)hashMap.get("connectionTimeout");
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

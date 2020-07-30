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

public class Configuration{

	private String username;
	private boolean autoBackup;
	private boolean exitDialog;
	private int theme;
	private String lang;
	private int connectionTimeout;
	private int readTimeout;
	
	public Configuration(){
		setDefaultValues();
	}

	public void setDefaultValues(){
		username = "Username";
		autoBackup = false;
		exitDialog = true;
		theme = Colour.NIGHT_THEME;
		lang = Language.available[0];
		connectionTimeout = 5000;
		readTimeout = 5000;
	}
	
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

	private void copyConfigData(HashMap<String,Object> hashMap){
		username = (String)hashMap.get("username");
		exitDialog = (boolean)hashMap.get("exitDialog");
		autoBackup = (boolean)hashMap.get("autoBackup");
		theme = (int)hashMap.get("theme");
		lang = (String)hashMap.get("lang");
		connectionTimeout = (int)hashMap.get("connectionTimeout");
		readTimeout = (int)hashMap.get("readTimeout");
	}

	public void saveConfiguration() throws IOException {
		Path.resolve(Path.dataPath);
		FileOutputStream f = new FileOutputStream(Path.configFile);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(createHashMap());
		o.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadConfiguration() throws ClassNotFoundException, IOException {
		Path.resolve(Path.dataPath);
		FileInputStream f = new FileInputStream(Path.configFile);
		ObjectInputStream o = new ObjectInputStream(f);
		copyConfigData((HashMap<String,Object>)o.readObject());
		o.close();
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String user){
		username = user;
	}
	
	public boolean getAutoBackup(){
		return autoBackup;
	}
	
	public void enableAutoBackup(boolean flag){
		autoBackup = flag;
	}
	
	public boolean getExitDialog(){
		return exitDialog;
	}
	
	public void enableExitDialog(boolean flag){
		exitDialog = flag;
	}
	
	public int currentTheme(){
		return theme;
	}
	
	public void changeTheme(int theme){
		this.theme = theme;
	}
	
	public String currentLanguage(){
		return lang;
	}
	public void changeLanguage(String lang){
		for(String l: Language.available)
			if(lang == l){
				this.lang = lang;
				return;
			}
		this.lang = "English";
	}

	public int getConnectionTimeout(){
		return connectionTimeout;
	}

	public void setConnectionTimeout(int timeout){
		connectionTimeout = timeout;
	}

	public int getReadTimeout(){
		return readTimeout;
	}

	public void setReadTimeout(int timeout){
		readTimeout = timeout;
	}
}

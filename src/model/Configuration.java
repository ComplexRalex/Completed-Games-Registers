package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import util.Colour;
import util.Language;
import util.Path;

public class Configuration implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static String currentUsername;
	private static boolean autoBackupValue;
	private static boolean exitDialogValue;
	private static int currentTheme;
	private static String currentLanguage;
	
	private String username;
	private boolean autoBackup;
	private boolean exitDialog;
	private int theme;
	private String lang;
	
	public Configuration(){
		setDefaultValues();
	}

	public void setDefaultValues(){
		username = currentUsername = "Username";
		autoBackup = autoBackupValue = false;
		exitDialog = exitDialogValue = true;
		theme = currentTheme = Colour.DARK_THEME;
		lang = currentLanguage = Language.available[0];
	}
	
	public void saveConfiguration() throws IOException {
		FileOutputStream f = new FileOutputStream(Path.configFile);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
	}
	
	public void loadConfiguration() throws ClassNotFoundException, IOException {
		FileInputStream f = new FileInputStream(Path.configFile);
		ObjectInputStream o = new ObjectInputStream(f);
		Configuration file = (Configuration)o.readObject();
		o.close();
		this.username = currentUsername = file.username;
		this.exitDialog = exitDialogValue = file.exitDialog;
		this.autoBackup = autoBackupValue = file.autoBackup;
		this.theme = currentTheme = file.theme;
		this.lang = currentLanguage = file.lang;
	}
	
	public static String getUsername(){
		return currentUsername;
	}
	
	public void setUsername(String user){
		username = currentUsername = user;
	}
	
	public static boolean getAutoBackup(){
		return autoBackupValue;
	}
	
	public void enableAutoBackup(boolean flag){
		autoBackup = autoBackupValue = flag;
	}
	
	public static boolean getExitDialog(){
		return exitDialogValue;
	}
	
	public void enableExitDialog(boolean flag){
		exitDialog = exitDialogValue = flag;
	}
	
	public static int currentTheme(){
		return currentTheme;
	}
	
	public void changeTheme(int theme){
		this.theme = currentTheme = theme;
	}
	
	public static String currentLanguage(){
		return currentLanguage;
	}
	public void changeLanguage(String lang){
		for(String l: Language.available)
			if(lang == l){
				this.lang = currentLanguage = lang;
				return;
			}
		this.lang = currentLanguage = "English";
	}
}

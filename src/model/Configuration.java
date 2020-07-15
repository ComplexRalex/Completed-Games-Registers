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

	private String username;
	private boolean autoBackup;
	private boolean exitDialog;
	private int theme;
	private String lang;
	
	public Configuration(){
		setDefaultValues();
	}

	public void setDefaultValues(){
		username = "Username";
		autoBackup = false;
		exitDialog = true;
		theme = Colour.DARK_THEME;
		lang = Language.available[0];
	}
	
	public void saveConfiguration() throws IOException {
		Path.resolve(Path.dataPath);
		FileOutputStream f = new FileOutputStream(Path.configFile);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
	}
	
	public void loadConfiguration() throws ClassNotFoundException, IOException {
		Path.resolve(Path.dataPath);
		FileInputStream f = new FileInputStream(Path.configFile);
		ObjectInputStream o = new ObjectInputStream(f);
		Configuration file = (Configuration)o.readObject();
		o.close();
		this.username = file.username;
		this.exitDialog = file.exitDialog;
		this.autoBackup = file.autoBackup;
		this.theme = file.theme;
		this.lang = file.lang;
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
}

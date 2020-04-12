package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import exception.CouldNotLoadFileException;
import exception.CouldNotSaveFileException;

public class Configuration implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static String currentUsername;
	private static boolean autoSaveValue;
	private static boolean autoBackupValue;
	private static int currentTheme;
	private static String currentLanguage;
	
	private String username;
	private boolean autoSave;
	private boolean autoBackup;
	private int theme;
	private String lang;
	
	public Configuration(){
		username = currentUsername = "Username";
		autoSave = autoSaveValue = true;
		autoBackup = autoBackupValue = false;
		theme = currentTheme = Colour.LIGHT_THEME;
		lang = currentLanguage = Language.available[0];
		Language.initialize();
	}
	
	public boolean saveConfiguration() throws FileNotFoundException, CouldNotSaveFileException{
		FileOutputStream f = new FileOutputStream(Path.configPath);
		try{
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(this);
			o.close();
			return true;
		}
		catch(IOException e){
			throw new CouldNotSaveFileException();
		}
	}
	
	public boolean loadConfiguration() throws FileNotFoundException, ClassNotFoundException, CouldNotLoadFileException{
		FileInputStream f = new FileInputStream(Path.configPath);
		try{
			ObjectInputStream o = new ObjectInputStream(f);
			Configuration file = (Configuration)o.readObject();
			o.close();
			this.username = currentUsername = file.username;
			this.autoSave = autoSaveValue = file.autoSave;
			this.autoBackup = autoBackupValue = file.autoBackup;
			this.theme = currentTheme = file.theme;
			this.lang = currentLanguage = file.lang;
			return true;
		}
		catch(IOException e){
			throw new CouldNotLoadFileException();
		}
		//JOptionPane.showConfirmDialog(null, "No se encontro el archivo de guardado.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
		//JOptionPane.showMessageDialog(null, "El archivo de guardado no es valido.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
	}
	
	public static String getUsername(){
		return currentUsername;
	}
	
	public void setUsername(String user){
		username = currentUsername = user;
	}
	
	public static boolean getAutoSave(){
		return autoSaveValue;
	}
	
	public void enableAutoSave(boolean flag){
		autoSave = autoSaveValue = flag;
	}
	
	public static boolean getAutoBackup(){
		return autoBackupValue;
	}
	
	public void enableAutoBackup(boolean flag){
		autoBackup = autoBackupValue = flag;
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

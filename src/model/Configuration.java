package model;

import java.awt.Color;
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
	public static final int LIGHT_THEME = 0;
	public static final int DARK_THEME = 1;
	public static final int NIGHT_THEME = 2;
	
	private static String username;
	private static boolean autoSaveValue;
	private static boolean autoBackupValue;
	private static int universalTheme;
	private static String currentLanguage;
	public static final Color colorON = new Color(110,220,116);
	public static final Color colorOFF = new Color(220,90,90);
	public static final Color[][] THEME_COLORS = {
			{	// Light Colors
				new Color(10,10,10),	// Font Color
				new Color(230,230,230),	// (Maybe) Background Color
				new Color(240,240,240), // Button Color
				new Color(250,250,250)	// Background Color	
			},
			{	// Dark Colors
				new Color(230,230,230),
				new Color(87,87,87),
				new Color(50,50,50), 
				new Color(34,34,34)
			},
			{	// Night Colors
				new Color(225,226,238),
				new Color(60,62,78),
				new Color(50,51,65),
				new Color(35,34,40)
			}
	};
	public static final Color transparent = new Color(0f,0f,0f,0.0001f);
	
	private String user;
	private boolean autoSave;
	private boolean autoBackup;
	private int theme;
	private String lang;
	
	public Configuration(){
		user = username = "Username";
		autoSave = autoSaveValue = true;
		autoBackup = autoBackupValue = false;
		theme = universalTheme = LIGHT_THEME;
		lang = currentLanguage = "English";
		Languages.initialize();
	}
	
	public boolean saveConfiguration() throws FileNotFoundException, CouldNotSaveFileException{
		FileOutputStream f = new FileOutputStream(Paths.configPath);
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
		FileInputStream f = new FileInputStream(Paths.configPath);
		try{
			ObjectInputStream o = new ObjectInputStream(f);
			Configuration file = (Configuration)o.readObject();
			o.close();
			this.user = username = file.user;
			this.autoSave = autoSaveValue = file.autoSave;
			this.autoBackup = autoBackupValue = file.autoBackup;
			this.theme = universalTheme = file.theme;
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
		return username;
	}
	
	public void setUsername(String user){
		this.user = username = user;
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
	
	public static int getTheme(){
		return universalTheme;
	}
	
	public void changeTheme(int theme){
		this.theme = universalTheme = theme;
	}
	
	public static Color getFontColor(){
		try{
			return THEME_COLORS[universalTheme][0];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[0][0];
		}
	}
	public static Color getPrimaryColor(){
		try{
			return THEME_COLORS[universalTheme][1];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[0][1];
		}
	}
	public static Color getButtonColor(){
		try{
			return THEME_COLORS[universalTheme][2];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[0][2];
		}
	}
	public static Color getBackgroundColor(){
		try{
			return THEME_COLORS[universalTheme][3];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[0][3];
		}
	}
	public static String currentLanguage(){
		return currentLanguage;
	}
	public void setLanguage(String lang){
		for(String l: Languages.available)
			if(lang == l){
				this.lang = currentLanguage = lang;
				return;
			}
		this.lang = currentLanguage = "English";
	}
}

package model;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import exception.CouldNotLoadFileException;
import exception.CouldNotSaveFileException;

public class Configuration implements Serializable{
	private static final long serialVersionUID = 1L;
	public static int backupCount = 0;
	public static final int LIGHT_THEME = 0;
	public static final int DARK_THEME = 1;
	public static final int NIGHT_THEME = 2;

	private static int universalTheme;
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
	
	private char[] password;
	private boolean autoSave;
	private boolean autoBackup;
	private int theme;
	
	public Configuration(){
		password = "noespassword".toCharArray();
		autoSave = true;
		autoBackup = false;
		theme = universalTheme = LIGHT_THEME;
	}
	
	public boolean saveConfiguration() throws FileNotFoundException, CouldNotSaveFileException{
		FileOutputStream f = new FileOutputStream(ConfigPaths.configPath);
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
		FileInputStream f = new FileInputStream(ConfigPaths.configPath);
		try{
			ObjectInputStream o = new ObjectInputStream(f);
			Configuration file = (Configuration)o.readObject();
			o.close();
			this.password = file.password;
			this.autoSave = file.autoSave;
			this.autoBackup = file.autoBackup;
			Configuration.universalTheme = this.theme = file.theme;
			return true;
		}
		catch(IOException e){
			throw new CouldNotLoadFileException();
		}
		//JOptionPane.showConfirmDialog(null, "No se encontro el archivo de guardado.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
		//JOptionPane.showMessageDialog(null, "El archivo de guardado no es valido.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean checkPasswords(char[] pass1, char[] pass2){
		int i = 0;
		while(i != pass1.length && i != pass2.length){
			if(pass1[i] != pass2[i]) break;
			i++;
		}
		return i == pass1.length && i == pass2.length;
	}
	
	public boolean checkPassword(char[] password){
		return checkPasswords(this.password,password);
	}
	
	public void changePassword(char[] newPass){
		password = newPass.clone();
	}
	
	public boolean getAutoSave(){return autoSave;}
	
	public void enableAutoSave(boolean flag){
		autoSave = flag;
	}
	
	public boolean getAutoBackup(){return autoBackup;}
	
	public void enableAutoBackup(boolean flag){
		autoBackup = flag;
	}
	
	public int getTheme(){return theme;}
	
	public void changeTheme(int theme){Configuration.universalTheme = this.theme = theme;}
	
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
}

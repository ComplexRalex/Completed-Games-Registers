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

package util;

import java.io.File;

/**
 * <h3>Path utility class.</h3>
 * This class provides every path to folder and files used in
 * the other classes.
 * 
 * @author Alejandro Batres
 */
public class Path{
	
	/**
	 * Source code folder path.
	 */
	public static final String srcPath = "src/";		// Every important file goes here

	/** 
	 * Data files folder path.
	 */
	public static final String dataPath = "data/";		// Every user-mofified file goes here
	
	/**
	 * Save file path.
	 */
	public static final String saveFile = dataPath+"save.dat";

	/**
	 * Configuration file path.
	 */
	public static final String configFile = dataPath+"config.dat";

	/**
	 * Backup files folder path.
	 */
	public static final String backupPath = dataPath+"backup/";
	
	/**
	 * Export files folder path.
	 */
	public static final String exportPath = dataPath+"export/";

	/**
	 * Log files folder path.
	 */
	public static final String logPath = dataPath+"log/";

	/**
	 * Game Database API data folder.
	 */
	public static final String gamePath = dataPath+"game/";

	/**
	 * Game information folder path.
	 */
	public static final String gameInfo = gamePath+"json/";

	/**
	 * Game image folder path.
	 */
	public static final String gameImage = gamePath+"image/";

	/**
	 * Icon folder path.
	 */
	public static final String iconPath = "icon/";

	/**
	 * GUI folder path.
	 */
	public static final String guiPath = "gui/";

	/**
	 * Char array containing illegal characters.
	 */
	public static final char[] ILLEGAL = new char[]{
		'/','\n','\r','\t','\0','\f','`','´','?','*','\\','<','>','|','\"',':'
	};

	/**
	 * Creates a file name with the given {@code name} in lower case, replacing with
	 * the respective hexadecimal value any {@link #ILLEGAL} character found
	 * in the {@code name} String, and preceded by {@code %} character.
	 * <p>
	 * If {@code name} contains a {@code %} character, then the file name will
	 * include double it.
	 * <p>
	 * An example of a name could be the following:
	 * <blockquote><pre>
	 * String name = "Am I an example? %lol";
	 * String extension = "json";
	 * </pre></blockquote>
	 * The result will be:
	 * <p>
	 * {@code am i an example%003f %%lol.json}
	 * 
	 * @param name String which contains the name of the file
	 * @param extension String which contains the format of the file. Note
	 * that this must NOT include the "<b>.</b>" symbol
	 * @return String with the mentioned changes
	 */
	public static final String validFileName(String name, String extension){
		String validName = "";
		for(int i = 0; i < name.length(); i++){
			if(name.charAt(i) == '%')
				validName += "%%";
			else
				validName += (isValid(name.charAt(i)) ? name.charAt(i) : "%"+String.format("%04x", (int)name.charAt(i)));
		}
		return validName.toLowerCase()+"."+extension;
	}

	/**
	 * Verifies if the given char is valid to be
	 * put into a name of a file.
	 * 
	 * @param c Char to be compared
	 * @return {@code true} if the given char is
	 * not included into the {@link #ILLEGAL} char
	 * array. {@code false} otherwise.
	 */
	public static final boolean isValid(char c){
		for(int i = 0; i < ILLEGAL.length; i++)
			if(c == ILLEGAL[i]) return false;
		return true;
	}

	/**
	 * Verifies if the given path exists.
	 * 
	 * @param path String which contains the path name.
	 * @return {@code true} if the directory/file exists. {@code false}
	 * otherwise.
	 */
	public static final boolean exists(String path){
		return (new File(path)).exists();
	}

	/**
	 * Creates a directory with the specified path in case of the
	 * non-existence of such (including parent folders). Note that
	 * this will only work with directories and not files.
	 * 
	 * @param path String which contains the path name.
	 * @return {@code true} if the directory(s) was created successfully.
	 * {@code false} otherwise (also if the file already exists).
	 */
	public static final boolean resolve(String path){
		return (new File(path)).mkdirs();
	}
}

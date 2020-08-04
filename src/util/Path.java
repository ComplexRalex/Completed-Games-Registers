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
	 * Creates a file name with the given name in lower case, without any
	 * symbols different from:
	 * <ul>
	 * <li>"<b>a</b>" to "<b>z</b>" characters,<p>
	 * <li>"<b>()</b>" parenthesis,<p>
	 * <li>space character,<p>
	 * <li>"<b>0</b>" to "<b>9</b>" numbers,<p>
	 * <li>and "<b>+</b>", "<b>-</b>" symbols.<p>
	 * </ul>
	 * Also, the space characters will be replaced to "<b>_</b>" characters.
	 * 
	 * @param name String which contains the name of the file
	 * @param extension String which contains the format of the file. Note
	 * that this must NOT include the "<b>.</b>" symbol
	 * @return String with the mentioned changes
	 */
	public static final String validFileName(String name, String extension){
		return name.toLowerCase().replaceAll("[^ ()a-z0-9+-]","").replaceAll(" ","_")+"."+extension;
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

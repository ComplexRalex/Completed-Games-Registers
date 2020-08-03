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

import controller.MainController;

/**
 * <h1>Completed-Games Registers</h1>
 * A software where you can have a record of every game you
 * have beaten (completed) so far!
 * <p>
 * The <b>completed-game</b> <i>registers</i></b> are instances
 * of {@link GameStat} objects that contain the following fields:
 * <ul>
 * <li>Name of the game. This field is not optional.
 * <li>Year when the user completed the game.
 * <li>Rating provided by the user (a value in {@code [0,5]}).
 * <li>User thoughts about the game.
 * <li>User annotations. Thoughts not related to the game directly.
 * <li>Spoilers of the game provided by the user.
 * </ul>
 * <h2>MainLauncher class</h2>
 * This class simply sets a pair of useful properties
 * and creates an instance of {@link MainController}.
 * 
 * @author Alejandro Batres
 * @see MainController
 */
public class MainLauncher{
	
	/**
	 * Sets some important system properties.
	 */
	public static void initializer(){
		// Anti-aliasing properties
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext","true");
	}
	
	/**
	 * The <b>main</b>.
	 * 
	 * @param args
	 */
	public static void main(String[] args){

		// Initializing important stuff
		initializer();
		
		// Initialize MainController
		new MainController();
	}
}

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import util.Path;

/**
 * <h3>GameRegister model class.</h3>
 * This class manages everything around the completed-games
 * registers ({@link GameStat} objects). It uses two variables:
 * <ul>
 * <li>An <i>ArrayList</i> of #GameStat {@link #gameStats},
 * <li>and a <i>boolean</i> {@link #changeMade}.
 * </ul>
 * Also, this class uses a variable from {@link Path} class
 * which is {@link Path#saveFile}.
 * 
 * @see Path
 * @see GameStat
 */
public class GameRegister{

	/**
	 * Array list of GameStat objects (Completed-Game Registers).
	 * 
	 * @see #addGameStat(GameStat)
	 * @see #removeGameStat(GameStat)
	 * @see #doBackup()
	 * @see #exportStats()
	 * @see #getGameStats()
	 * @see #setGameStats()
	 */
	private ArrayList<GameStat> gameStats;

	/**
	 * Boolean variable which tells if a change was made.
	 * This is used to decide if it's convenient to do a backup
	 * (if {@link Configuration#getAutoBackup()} is {@code true}). 
	 * 
	 * @see Configuration
	 */
	private boolean changeMade;
	
	/**
	 * Constructor of the GameRegister class. This will
	 * initialize the variables. In the case of {@link #changeMade},
	 * it will set it as {@code false}.
	 */
	public GameRegister(){
		gameStats = new ArrayList<>();
		changeMade = false;
	}
	
	/**
	 * Creates an instance of <i>ArrayList</i> of {@link GameStat}
	 * where every object is "transformed" into a {@link HashMap} 
	 * with all of its attributes being a pair of Key-Value.
	 * <p>
	 * This is being used to prevent compatibility errors in possible 
	 * future updates of the program (in case of adding more fields).
	 * 
	 * @param stats ArrayList containing {@link GameStat} objects
	 * @see GameStat#exportStat()
	 * @see #saveGameStats()
	 * @return An ArrayList with received registers transformed
	 * into {@link HashMap} objects.
	 */
	private ArrayList<HashMap<String,Object>> toHashMapArray(ArrayList<GameStat> stats){
		ArrayList<HashMap<String,Object>> array = new ArrayList<>();

		for(GameStat gs: stats)
			array.add(new HashMap<String,Object>(gs.exportStat().toMap()));

		return array;
	}

	/**
	 * Retrives the stored {@link HashMap}s inside the given
	 * <i>ArrayList</i> and transform them into {@link GameStat}
	 * objects to save them in a new <i>ArrayList</i>.
	 * <p>
	 * This is being used to prevent compatibility errors in possible 
	 * future updates of the program (in case of adding more fields).
	 * 
	 * @param array ArrayList containing {@link HashMaps} objects
	 * @see #loadGameStats()
	 * @return An ArrayList with the HashMaps transformed
	 * into {@link GameStat} objects.
	 */
	private ArrayList<GameStat> fromHashMapArray(ArrayList<HashMap<String,Object>> array){
		ArrayList<GameStat> stats = new ArrayList<>();

		for(HashMap<String,Object> hashMap: array)
			stats.add(new GameStat(new JSONObject(hashMap)));

		return stats;
	}

	/**
	 * Creates (or overwrites) a file in {@link Path#saveFile} with
	 * the current registers.
	 * <p>
	 * This method is saving a HashMap object to give more
	 * compatibility in future updates.
	 * 
	 * @see #toHashMapArray(ArrayList)
	 * @throws IOException
	 */
	public void saveGameStats() throws IOException {
		Path.resolve(Path.dataPath);
		FileOutputStream f = new FileOutputStream(Path.saveFile);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(toHashMapArray(gameStats));
		o.close();
		f.close();

		changeMade = true;
	}
	
	/**
	 * Reads a file in {@link Path#saveFile} and retrives
	 * its stored registers to use them in the current
	 * <i>ArrayList</i>.
	 * <p>
	 * This method is loading a HashMap object to give more
	 * compatibility in future updates.
	 * 
	 * @see #fromHashMapArray(ArrayList)
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void loadGameStats() throws ClassNotFoundException, IOException {
		Path.resolve(Path.dataPath);
		FileInputStream f = new FileInputStream(Path.saveFile);
		ObjectInputStream o = new ObjectInputStream(f);
		gameStats = fromHashMapArray((ArrayList<HashMap<String,Object>>)o.readObject());
		o.close();
		f.close();
	}
	
	/**
	 * Creates a file in {@link Path#backupPath} with
	 * the current registers. This file will be named by
	 * "backup-" and the following {@link SimpleDateFormat}:
	 * {@code SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")}.
	 * <p>
	 * Example: <b>{@code backup-2020-07-31-12-09-58}</b>.
	 * <p>
	 * The values of each variable will be the same as the
	 * moment when the user creates this backup (i.e. the
	 * current dat and hour).
	 * <p>
	 * This method is saving a HashMap object to give more
	 * compatibility in future updates.
	 * 
	 * @see SimpleDateFormat
	 * @see #toHashMapArray(ArrayList)
	 * @throws IOException
	 * @return String containing the name of the backup file.
	 */
	public String doBackup() throws IOException {
		Path.resolve(Path.backupPath);
		String fileName = Path.backupPath+"backup-"+(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")).format(new Date())+".dat";
		FileOutputStream f = new FileOutputStream(fileName);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(toHashMapArray(gameStats));
		o.close();
		f.close();

		return fileName;
	}

	/**
	 * Creates a file in {@link Path#exportPath} with
	 * the current registers exported into a unique
	 * JSON file. This file will be named by "export-"
	 * and the following {@link SimpleDateFormat}:
	 * {@code SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")}.
	 * <p>
	 * Example: <b>{@code export-2020-07-31-12-09-58}</b>.
	 * <p>
	 * The values of each variable will be the same as the
	 * moment when the user creates this export (i.e. the
	 * current dat and hour).
	 * 
	 * @see SimpleDateFormat
	 * @throws IOException
	 * @return String containing the name of the export file.
	 */
	public String exportStats() throws IOException {
		Path.resolve(Path.exportPath);
		String filename = Path.exportPath+"export-"+(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")).format(new Date())+".json";

		JSONArray array = new JSONArray();
		for(GameStat gs: gameStats)
			array.put(gs.exportStat());
		JSONObject json = new JSONObject();
		json.put("registers",array);

		FileWriter f = new FileWriter(filename);
		f.append(json.toString());
		f.close();

		return filename;
	}
	
	/**
	 * Adds and sorts alphabetically the given {@link GameStat}
	 * to the current ArrayList.
	 * 
	 * @param gs New {@link GameStat} ready to be entered!
	 * @return {@code true} if the {@link GameStat} was added.
	 * {@code false} if the given {@link GameStat} was already
	 * added (this means that has the same <b>name</b> as an
	 * existing one).
	 */
	public boolean addGameStat(GameStat gs){
		int iterator = 0;
		for(iterator = 0; iterator < gameStats.size(); iterator++){ // Add elements sorted by their title
			if(gs.getGame().compareToIgnoreCase(gameStats.get(iterator).getGame()) > 0) continue;
			if(gs.getGame().compareToIgnoreCase(gameStats.get(iterator).getGame()) == 0) return false;
			break;
		}
		gameStats.add(iterator, gs);

		return true;
	}

	/**
	 * Returns a {@link GameStat} which corresponds to
	 * the entered name.
	 * 
	 * @param name String which contains the game name.
	 * @return an {@link GameStat} if it has the same name
	 * as the given one. {@code null} if it doesn't exist.
	 */
	public GameStat getGameStat(String name){
		for(GameStat gs: gameStats){
			if(gs.getGame().compareToIgnoreCase(name) == 0) return gs;
		}
		return null;
	}
	
	/**
	 * Removes the given {@link GameStat} from the
	 * current ArrayList.
	 * 
	 * @param gs {@link GameStat} ready to be removed!
	 * @return {@code true} if it was removed successfully.
	 * {@code false} otherwise.
	 */
	public boolean removeGameStat(GameStat gs){
		if(gs != null)
			return gameStats.remove(gs);

		return false;
	}
	
	/**
	 * Returns the value of {@link #changeMade}.
	 * This turns {@code true} if the {@link #saveGameStats()}
	 * method is called in the current session
	 * once at least.
	 * 
	 * @return {@code true} if a change was made
	 * (i.e. if a register were added, modified or
	 * deleted. This actually depend on the
	 * {@link #saveGameStats()} method).
	 */
	public boolean changesMade(){
		return changeMade;
	}
	
	/**
	 * Gets the current ArrayList of the current
	 * registers.
	 * 
	 * @return ArrayList of {@link GameStat}.
	 */
	public ArrayList<GameStat> getGameStats(){return gameStats;}

	/**
	 * Returns a new instance of ArrayList only
	 * containing {@link GameStat}s which their
	 * names contain part of the given name.
	 * <p>
	 * Currently, this method is not being used.
	 * 
	 * @param title Part of the name of the game
	 * @return ArrayList containing {@link GameStat}
	 * occurrences of the given title. 
	 */
	public ArrayList<GameStat> getGameStatsOccurrences(String title){
		ArrayList<GameStat> gss = new ArrayList<>();
		for(GameStat gs: gameStats)
			if(gs.getGame().toLowerCase().contains(title.toLowerCase())) gss.add(gs);
		return gss;
	}
	
	/**
	 * Sets a new value to the current ArrayList.
	 * 
	 * @param gs ArrayList of {@link GameStat} objects
	 */
	public void setGameStats(ArrayList<GameStat> gs){gameStats = gs;}
}

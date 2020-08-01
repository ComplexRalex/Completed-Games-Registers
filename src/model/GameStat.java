package model;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import util.Path;

/**
 * <h3>GameStat model class.</h3>
 * This class represents a <b><i>Completed-Game</i> Register</b>.
 * It contains the following fields:
 * <ul>
 * <li><b>{@link #game}</b>: Name of the game.
 * <li><b>{@link #year}</b>: Year when the user completed the game.
 * <li><b>{@link #rate}</b>: Rating provided by the user
 * (a value in {@code [0,5]}).
 * <li><b>{@link #comment}</b>: User thoughts about the game.
 * <li><b>{@link #note}</b>: User annotations. Thoughts not related
 * to the game directly.
 * <li><b>{@link #spoiler}</b>: Spoilers of the game provided by the
 * user.
 * </ul>
 * Note that the {@link #game} field is the only one which is
 * non-optional to create a new <i>Completed-Game Register</i>.
 * <p>
 * This class verifies if there is information downloaded about
 * this game in {@link Path#gameInfo}, downloaded through
 * {@link GameData#downloadGameInfo(String)} method.
 * 
 * @see Path
 * @see GameRegister
 * @see GameData#downloadGameInfo(String)
 */
public class GameStat{

	/**
	 * Number which represents a {@code non-rate} value.
	 */
	public static final int NO_RATE = 0;

	/**
	 * Number which represents a rating "<b>1 of 5</b>"".
	 */
	public static final int RATE_1 = 1;

	/**
	 * Number which represents a rating "<b>2 of 5</b>"".
	 */
	public static final int RATE_2 = 2;

	/**
	 * Number which represents a rating "<b>3 of 5</b>"".
	 */
	public static final int RATE_3 = 3;

	/**
	 * Number which represents a rating "<b>4 of 5</b>"".
	 */
	public static final int RATE_4 = 4;

	/**
	 * Number which represents a rating "<b>5 of 5</b>"".
	 */
	public static final int RATE_5 = 5;

	/**
	 * Number of possible ratings (counting the {@code non-rate}
	 * value).
	 */
	public static final int RATE_OPTIONS = 6;

	/**
	 * String which represents the name of the game.
	 * 
	 * @see #getGame()
	 * @see #setGame(String)
	 */
	private String game;

	/**
	 * Number which represents the year when the user completed
	 * (beated) the game.
	 * 
	 * @see #getYear()
	 * @see #setYear(int)
	 */
	private int year;

	/**
	 * Number which represent the rating of the game provided
	 * by the user. This must be between <b>0</b> and <b>5</b>.
	 * 
	 * @see #getRate()
	 * @see #setRate(int)
	 */
	private int rate;

	/**
	 * String which represents the thoughts about the game. 
	 * 
	 * @see #getComment()
	 * @see #setComment(String)
	 */
	private String comment;

	/**
	 * String which represents the annotations about the game.
	 * These are not related to the game directly.
	 * 
	 * @see #getNote()
	 * @see #setNote(String)
	 */
	private String note;

	/**
	 * String which represents spoilers about the game.
	 * 
	 * @see #getSpoiler()
	 * @see #setSpoiler(String)
	 */
	private String spoiler;

	/**
	 * Main constructor of the GameStat class. This will
	 * initialize the object with the provided values.
	 * 
	 * @param g String which contains the name of the game
	 * @param y Number which contains the year of completion
	 * @param r Number which contains the rating of the game
	 * @param c String which contains a comment about the game
	 * @param n String which contains notes about the game
	 * @param s String which contains spoilers bout the game
	 */
	public GameStat(String g, int y, int r, String c, String n, String s){
		game = validateGame(g);
		year = validateYear(y);
		rate = validateRate(r);
		comment = validateText(c);
		note = validateText(n);
		spoiler = validateText(s);
	}
	
	/**
	 * Constructor of the GameStat class. In this case,
	 * the object will be a "clone" of the provided object.
	 * However, this doesn't mean that the object will be
	 * the <i>same</i> as the given one. It just takes
	 * the values of every field and use them.
	 * 
	 * @param gs GameStat object different from null.
	 */
	public GameStat(GameStat gs){
		game = gs.getGame();
		year = gs.getYear();
		rate = gs.getRate();
		comment = gs.getComment();
		note = gs.getNote();
		spoiler = gs.getSpoiler();
	}

	/**
	 * Constructor of the GameStat class. This will
	 * initialize the object with the given JSON
	 * object.
	 * 
	 * @param json
	 * @see GameRegister#saveGameStats()
	 */
	public GameStat(JSONObject json){
		try{
			game = validateGame(json.getString("game"));
		}catch(JSONException e){game = validateGame(null);}
		try{
			year = validateYear(json.getInt("year"));
		}catch(JSONException e){year = -1;}
		try{
			rate = validateRate(json.getInt("rate"));
		}catch(JSONException e){rate = 0;}
		try{
			comment = validateText(json.getString("comment"));
		}catch(JSONException e){comment = validateText(null);}
		try{
			note = validateText(json.getString("note"));
		}catch(JSONException e){note = validateText(null);}
		try{
			spoiler = validateText(json.getString("spoiler"));
		}catch(JSONException e){spoiler = validateText(null);}
	}

	/**
	 * Validates the content of the name of the game.
	 * 
	 * @param game String which contains the name of the game
	 * @return String validated (different from {@code null}).
	 */
	private String validateGame(String game){if(game == null) game = ""; return game.trim();}

	/**
	 * Validates the content of the year of completion.
	 * 
	 * @param year Number which contains the year of completion
	 * @return Number validated.
	 */
	private int validateYear(int year){return (year < 0 ? -1 : year);}

	/**
	 * Validates the content of the rate of the game.
	 * 
	 * @param rate Number which contains the rate of the game
	 * @return Number validated.
	 */
	private int validateRate(int rate){return (rate > 5 || rate < 0 ? 0 : rate);}

	/**
	 * Validates the content of the text.
	 * 
	 * @param text String which contains a text.
	 * @return String validated (different from {@code null}).
	 */
	private String validateText(String text){if(text == null) text = ""; return ("".equals(text.trim()) ? "" : text);}
	
	/**
	 * Gets the name of the game.
	 * 
	 * @return {@link #game}'s value.
	 */
	public String getGame(){return game;}

	/**
	 * Gets the year of completion of the game.
	 * 
	 * @return {@link #year}'s value.
	 */
	public int getYear(){return year;}

	/**
	 * Gets the rate of the game.
	 * 
	 * @return {@link #rate}'s value.
	 */
	public int getRate(){return rate;}

	/**
	 * Gets the comments about the game.
	 * 
	 * @return {@link #comment}'s value.
	 */
	public String getComment(){return comment;}

	/**
	 * Gets the annotations of the game.
	 * 
	 * @return {@link #note}'s value.
	 */
	public String getNote(){return note;}
	
	/**
	 * Gets the spoilers about the game.
	 * 
	 * @return {@link #spoiler}'s value.
	 */
	public String getSpoiler(){return spoiler;}
	
	/**
	 * Sets the name of the game.
	 * 
	 * @param game New {@link #game}'s value
	 */
	public void setGame(String game){this.game = validateGame(game);}
	
	/**
	 * Sets the year of completion of the game.
	 * 
	 * @param year New {@link #year}'s value
	 */
	public void setYear(int year){this.year = validateYear(year);}

	/**
	 * Sets the rate of the game.
	 * 
	 * @param rate New {@link #rate}' value
	 */
	public void setRate(int rate){this.rate = validateRate(rate);}

	/**
	 * Sets the comments about the game.
	 * 
	 * @param comment New {@link #comment}'s value
	 */
	public void setComment(String comment){this.comment = validateText(comment);}
	
	/**
	 * Sets the annotations of the game.
	 * 
	 * @param note New {@link #note}'s value
	 */
	public void setNote(String note){this.note = validateText(note);}

	/**
	 * Sets the spoilers about the game.
	 * 
	 * @param spoiler New {@link #spoiler}'s value
	 */
	public void setSpoiler(String spoiler){this.spoiler = validateText(spoiler);}

	/**
	 * Creates an instance of {@link JSONObject} and stores in
	 * it every field of this object as a Kay-Value pair.
	 * <p>
	 * This method is used in {@link GameRegister#exportStats()}
	 * to store them in {@link Path#exportPath} as a JSON file.
	 * 
	 * @see GameRegister#exportStats()
	 * @see Path#exportPath
	 * @return {@link JSONObject} containing the fields of the
	 * current GameStat object.
	 */
	public JSONObject exportStat(){
		JSONObject json = new JSONObject();

		json.put("game", game);
		json.put("year", year);
		json.put("rate", rate);
		json.put("comment", comment);
		json.put("note", note);
		json.put("spoiler", spoiler);

		return json;
	}

	/**
	 * Tells if there is information available about this game.
	 * 
	 * @see Path#validFileName(String, String)
	 * 
	 * @return {@code true} if a file with {@link #game}'s
	 * value and extension ".json" exists in {@link Path#gameInfo}.
	 * Note that the name of the file it's not exactly the
	 * name of the game, because it is saved using
	 * {@link Path#validFileName(String, String)}. {@code false}
	 * otherwise.
	 */
	public boolean isInfoAvailable(){
		Path.resolve(Path.gameInfo);
		return (new File(Path.gameInfo+Path.validFileName(game, "json"))).exists();
	}
}

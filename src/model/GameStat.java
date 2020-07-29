package model;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import util.Path;

public class GameStat{

	public static final int NO_RATE = 0;
	public static final int RATE_1 = 1;
	public static final int RATE_2 = 2;
	public static final int RATE_3 = 3;
	public static final int RATE_4 = 4;
	public static final int RATE_5 = 5;

	public static final int RATE_OPTIONS = 6;

	private String game;
	private int year;
	private int rate;
	private String comment;
	private String note;
	private String spoiler;

	public GameStat(String g, int y, int r, String c, String n, String s){
		game = validateGame(g);
		year = validateYear(y);
		rate = validateRate(r);
		comment = validateText(c);
		note = validateText(n);
		spoiler = validateText(s);
	}
	
	public GameStat(GameStat gs){
		game = gs.getGame();
		year = gs.getYear();
		rate = gs.getRate();
		comment = gs.getComment();
		note = gs.getNote();
		spoiler = gs.getSpoiler();
	}

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

	private String validateGame(String game){if(game == null) game = ""; return game.trim();}
	private int validateYear(int year){return (year < 0 ? -1 : year);}
	private int validateRate(int rate){return (rate > 5 || rate < 0 ? 0 : rate);}
	private String validateText(String text){if(text == null) text = ""; return ("".equals(text.trim()) ? "" : text);}
	
	public String getGame(){return game;}
	public int getYear(){return year;}
	public int getRate(){return rate;}
	public String getComment(){return comment;}
	public String getNote(){return note;}
	public String getSpoiler(){return spoiler;}
	
	public void setGame(String game){this.game = validateGame(game);}
	public void setYear(int year){this.year = validateYear(year);}
	public void setRate(int rate){this.rate = validateRate(rate);}
	public void setComment(String comment){this.comment = validateText(comment);}
	public void setNote(String note){this.note = validateText(note);}
	public void setSpoiler(String spoiler){this.spoiler = validateText(spoiler);}

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

	public boolean isInfoAvailable(){
		Path.resolve(Path.gameInfo);
		return (new File(Path.gameInfo+Path.validFileName(game, "json"))).exists();
	}

}
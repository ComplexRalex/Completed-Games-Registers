package model;

import java.io.File;
import java.io.Serializable;

import org.json.simple.JSONObject;

import util.Path;

public class GameStat implements Serializable{

	public static final int NO_RATE = 0;
	public static final int RATE_1 = 1;
	public static final int RATE_2 = 2;
	public static final int RATE_3 = 3;
	public static final int RATE_4 = 4;
	public static final int RATE_5 = 5;

	public static final int RATE_OPTIONS = 6;

	private static final long serialVersionUID = 1L;
	private String game;
	private int year;
	private int rate;
	private String comment;
	private String note;
	private String spoiler;

	public GameStat(String g, int y, int r, String c, String n, String s){
		game = g;
		year = y;
		rate = r;
		comment = c;
		note = n;
		spoiler = s;
	}
	
	public GameStat(GameStat gs){
		game = gs.getGame();
		year = gs.getYear();
		rate = gs.getRate();
		comment = gs.getComment();
		note = gs.getNote();
		spoiler = gs.getSpoiler();
	}
	
	public String getGame(){return game;}
	public int getYear(){return year;}
	public int getRate(){return rate;}
	public String getComment(){return comment;}
	public String getNote(){return note;}
	public String getSpoiler(){return spoiler;}
	
	public void setGame(String game){this.game = game;}
	public void setYear(int year){this.year = year;}
	public void setRate(int rate){this.rate = rate;}
	public void setComment(String comment){this.comment = comment;}
	public void setNote(String note){this.note = note;}
	public void setSpoiler(String spoiler){this.spoiler = spoiler;}

	public JSONObject exportStat(){
		JSONObject json = new JSONObject();

		json.put("game", game);
		json.put("year", (year == -1 ? null : year));
		json.put("rate", rate);
		json.put("comment", ("".equals(comment) ? null : comment));
		json.put("note", ("".equals(note) ? null : note));
		json.put("spoiler", ("".equals(spoiler) ? null : spoiler));

		return json;
	}

	public boolean isInfoAvailable(){
		Path.resolve(Path.gameInfo);
		return (new File(Path.gameInfo+Path.validFileName(game, "json"))).exists();
	}

}
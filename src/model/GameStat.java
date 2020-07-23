package model;

import java.io.File;
import java.io.Serializable;

import org.json.JSONObject;

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
		game = g.trim();
		year = (y < 0 ? -1 : y);
		rate = (r > 5 || r < 0 ? 0 : r);
		comment = ("".equals(c.trim()) ? "" : c);
		note = ("".equals(n.trim()) ? "" : n);
		spoiler = ("".equals(s.trim()) ? "" : s);
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
	
	public void setGame(String game){this.game = game.trim();}
	public void setYear(int year){this.year = (year < 0 ? -1 : year);}
	public void setRate(int rate){this.rate = (rate > 5 || rate < 0 ? 0 : rate);}
	public void setComment(String comment){this.comment = ("".equals(comment.trim()) ? "" : comment);}
	public void setNote(String note){this.note = ("".equals(note.trim()) ? "" : note);}
	public void setSpoiler(String spoiler){this.spoiler = ("".equals(spoiler.trim()) ? "" : spoiler);}

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
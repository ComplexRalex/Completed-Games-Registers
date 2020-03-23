package model;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStat implements Serializable{
	private static final long serialVersionUID = 1L;
	private String game;
	private String image;
	private int year;
	private float percentage;
	private int rate;
	private String comment;
	private String note;
	private String spoiler;
	private ArrayList<String> platforms;

	public GameStat(String g, String i, int y, float p, int r, String c, String n, String s, ArrayList<String> gc){
		game = g;
		image = i;
		year = y;
		percentage = p;
		rate = r;
		comment = c;
		note = n;
		spoiler = s;
		platforms = gc;
	}
	
	public GameStat(GameStat gs){
		game = gs.getGame();
		image = gs.getImage();
		year = gs.getYear();
		percentage = gs.getPercentage();
		rate = gs.getRate();
		comment = gs.getComment();
		note = gs.getNote();
		spoiler = gs.getSpoiler();
		platforms = gs.getPlatforms();
	}
	
	public String getGame(){return game;}
	public String getImage(){return image;}
	public int getYear(){return year;}
	public float getPercentage(){return percentage;}
	public int getRate(){return rate;}
	public String getComment(){return comment;}
	public String getNote(){return note;}
	public String getSpoiler(){return spoiler;}
	public ArrayList<String> getPlatforms(){return platforms;}
	
	public void setGame(String game){this.game = game;}
	public void setImage(String image){this.image = image;}
	public void setYear(int year){this.year = year;}
	public void setPercentage(float percentage){this.percentage = percentage;}
	public void setRate(int rate){this.rate = rate;}
	public void setComment(String comment){this.comment = comment;}
	public void setNote(String note){this.note = note;}
	public void setSpoiler(String spoiler){this.spoiler = spoiler;}
	public void setPlatforms(ArrayList<String> platforms){this.platforms = platforms;}
}

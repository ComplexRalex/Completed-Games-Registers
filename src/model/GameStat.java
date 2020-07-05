package model;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.Component;
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
	private boolean availableInfo;

	public GameStat(String g, int y, int r, String c, String n, String s){
		game = g;
		year = y;
		rate = r;
		comment = c;
		note = n;
		spoiler = s;
		availableInfo = false;
	}
	
	public GameStat(GameStat gs){
		game = gs.getGame();
		year = gs.getYear();
		rate = gs.getRate();
		comment = gs.getComment();
		note = gs.getNote();
		spoiler = gs.getSpoiler();
		availableInfo = gs.getAvailableInfo();
	}
	
	public String getGame(){return game;}
	public int getYear(){return year;}
	public int getRate(){return rate;}
	public String getComment(){return comment;}
	public String getNote(){return note;}
	public String getSpoiler(){return spoiler;}
	public boolean getAvailableInfo(){return availableInfo;}
	
	public void setGame(String game){this.game = game;}
	public void setYear(int year){this.year = year;}
	public void setRate(int rate){this.rate = rate;}
	public void setComment(String comment){this.comment = comment;}
	public void setNote(String note){this.note = note;}
	public void setSpoiler(String spoiler){this.spoiler = spoiler;}

	public boolean downloadGameInfo() throws MalformedURLException, IOException, ParseException, URISyntaxException{

		URI uri = new URI("https","api.rawg.io","/api/games","search="+game,"page_size=1");
		URL url = new URL(uri.toASCIIString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		if(connection.getResponseCode() != 200) throw new IOException("Received not a good response from the server...");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		JSONObject json = (JSONObject)(new JSONParser()).parse(reader);
		connection.disconnect();
		reader.close();

		if("0".equals(String.valueOf(json.get("count")))) return false;

		StringBuffer response = new StringBuffer(((JSONObject)((JSONArray)json.get("results")).get(0)).toJSONString());

		FileWriter file = new FileWriter(Path.gameInfo+Path.validFileName(game, "json"));
		file.append(response);
		file.close();

		return (availableInfo = true);
	}

	public boolean downloadGameImage() throws FileNotFoundException, IOException, ParseException{
		if(availableInfo){
			JSONObject json = (JSONObject)(new JSONParser()).parse(new FileReader(Path.gameInfo+Path.validFileName(game, "json")));
			BufferedImage image = ImageIO.read(new URL((String)json.get("background_image")));

			if (image != null) {
				int
					n = 8, // Number of iterations that the image will be rescaled
					delta = Component.width-image.getWidth(),
					width, height,
					imgWidth = image.getWidth(),
					imgHeight = image.getHeight(); 
				BufferedImage scaled = image;
				Graphics2D g = null;
				/**
				 * In order to get the most "blockyless" image possible, it's necessary to
				 * rescale the image some more iterations to get a better result. Also,
				 * implementing antialiasing and bicubic interpolation will make a better
				 * effect on this.
				 * 
				 * Unfortunately, the rescaled image will look blurry to maintain some
				 * detail at that size.
				 */
				for(int i = 1; i <= n; i++){
					width = imgWidth+i*delta/n;
					height = width*imgHeight/imgWidth;
					scaled = new BufferedImage(width, height, image.getType());
					g = scaled.createGraphics();
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
					g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					g.drawImage(image, 0, 0, width, height, null);
					g.dispose();
					image = scaled;
				}
			}
			ImageIO.write(image,"jpg",new File(Path.gameImage+Path.validFileName(game, "jpg")));
			return true;
		}else
			return false;
	}

	public boolean deleteGameInfo(){

		File file = new File(Path.gameInfo+Path.validFileName(game, "json"));
		return !(availableInfo = !file.delete());
	}

	public boolean deleteGameImage(){
		
		File file = new File(Path.gameImage+Path.validFileName(game, "jpg"));
		return file.delete();
	}
}

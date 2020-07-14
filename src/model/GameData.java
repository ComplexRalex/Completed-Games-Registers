package model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.Component;
import util.Path;

public class GameData{

    private JSONObject data;
    private String game;

    public GameData(String name) throws FileNotFoundException, IOException, ParseException{
		FileReader file = new FileReader(Path.gameInfo+Path.validFileName(name, "json"));
		data = (JSONObject)(new JSONParser()).parse(file);
		file.close();
        game = name;
    }

    public String getName(){
        return (String)data.get("name");
    }

	public String getDescription(){
		return (String)data.get("description");
	}

    public BufferedImage getImage() throws IOException{
        return ImageIO.read(new File(Path.gameImage+Path.validFileName(game, "jpg")));
	}
	
	public String[] getDevelopers(){
		JSONArray developers = (JSONArray)data.get("developers");
        String[] array = new String[developers.size()];

        for(int i = 0; i < developers.size(); i++){
            array[i] = (String)((JSONObject)developers.get(i)).get("name");
        }

        return array;
	}

	public String[] getPublishers(){
		JSONArray publishers = (JSONArray)data.get("publishers");
        String[] array = new String[publishers.size()];

        for(int i = 0; i < publishers.size(); i++){
            array[i] = (String)((JSONObject)publishers.get(i)).get("name");
        }

        return array;
	}

    public String getReleaseDate(){
        return (String)data.get("released");
    }

    public String[] getPlatforms(){
        JSONArray platforms = (JSONArray)data.get("platforms");
        String[] array = new String[platforms.size()];

        for(int i = 0; i < platforms.size(); i++){
            array[i] = (String)((JSONObject)((JSONObject)platforms.get(i)).get("platform")).get("name");
        }

        return array;
    }

    public String[] getGenres(){
        JSONArray genres = (JSONArray)data.get("genres");
        String[] array = new String[genres.size()];

        for(int i = 0; i < genres.size(); i++){
            array[i] = (String)((JSONObject)genres.get(i)).get("name");
        }

        return array;
    }

    public String[] getTags(){
        JSONArray tags = (JSONArray)data.get("tags");
        Queue<String> engTags = new LinkedList<String>();

        JSONObject object;
        for(int i = 0; i < tags.size(); i++){
            if(((String)((object = (JSONObject)tags.get(i)).get("language"))).equals("eng"));
                engTags.add((String)object.get("name"));
        }

        int size;
        String[] finalTags = new String[size = engTags.size()];
        for(int i = 0; i < size; i++){
            finalTags[i] = engTags.poll();
        }
        
        return finalTags;
    }

    public float getRating(){
        return (float)(double)data.get("rating");
	}
	
	private static long searchGame(String game) throws MalformedURLException, IOException, ParseException, URISyntaxException{

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

		if("0".equals(String.valueOf(json.get("count")))) throw new IOException("There were no coincidences...");
		
		return (long)((JSONObject)((JSONArray)json.get("results")).get(0)).get("id");
	}

    public static boolean downloadGameInfo(String game) throws MalformedURLException, IOException, ParseException, URISyntaxException{

		URI uri = new URI("https","api.rawg.io","/api/games/"+searchGame(game),"");
		URL url = new URL(uri.toASCIIString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		if(connection.getResponseCode() != 200) throw new IOException("Received not a good response from the server...");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		FileWriter file = new FileWriter(Path.gameInfo+Path.validFileName(game, "json"));
		file.append(((JSONObject)(new JSONParser()).parse(reader)).toJSONString());
		file.close();
		connection.disconnect();
		reader.close();

		return true;
	}

	public static void downloadGameImage(String game) throws IOException, ParseException{

		FileReader reader = new FileReader(Path.gameInfo+Path.validFileName(game, "json"));
		JSONObject json = (JSONObject)(new JSONParser()).parse(reader);
		reader.close();

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
	}

	public static boolean deleteGameInfo(String game){
		
		File file = new File(Path.gameInfo+Path.validFileName(game, "json"));
		return file.delete();
	}

	public static void deleteGameImage(String game){
		
		File file = new File(Path.gameImage+Path.validFileName(game, "jpg"));
		file.delete();
	}
}

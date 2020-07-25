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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.Component;
import util.Path;

public class GameData{

    private JSONObject data;
    private String game;

    public GameData(String name) throws FileNotFoundException, IOException, JSONException{
		Path.resolve(Path.gameInfo);
		FileReader file = new FileReader(Path.gameInfo+Path.validFileName(name, "json"));
		BufferedReader reader = new BufferedReader(file);

		String jsonString = "", line;
		while((line = reader.readLine()) != null)
			jsonString += line+"\n";
		
		reader.close();
		file.close();

		data = new JSONObject(jsonString);
		game = name;
    }

    public String getName(){
        return data.getString("name");
    }

	public String getDescription(){
		return data.getString("description");
	}

    public BufferedImage getImage() throws IOException{
		Path.resolve(Path.gameImage);
        return ImageIO.read(new File(Path.gameImage+Path.validFileName(game, "jpg")));
	}
	
	public String[] getDevelopers(){
		JSONArray developers = data.getJSONArray("developers");
        String[] array = new String[developers.length()];

        for(int i = 0; i < developers.length(); i++){
            array[i] = developers.getJSONObject(i).getString("name");
        }

        return array;
	}

	public String[] getPublishers(){
		JSONArray publishers = data.getJSONArray("publishers");
        String[] array = new String[publishers.length()];

        for(int i = 0; i < publishers.length(); i++){
            array[i] = publishers.getJSONObject(i).getString("name");
        }

        return array;
	}

    public String getReleaseDate(){
        return data.getString("released");
    }

    public String[] getPlatforms(){
        JSONArray platforms = data.getJSONArray("platforms");
        String[] array = new String[platforms.length()];

        for(int i = 0; i < platforms.length(); i++){
            array[i] = platforms.getJSONObject(i).getJSONObject("platform").getString("name");
        }

        return array;
    }

    public String[] getGenres(){
        JSONArray genres = data.getJSONArray("genres");
        String[] array = new String[genres.length()];

        for(int i = 0; i < genres.length(); i++){
            array[i] = genres.getJSONObject(i).getString("name");
        }

        return array;
    }

    public String[] getTags(){
        JSONArray tags = (JSONArray)data.get("tags");
        Queue<String> engTags = new LinkedList<String>();

        JSONObject object;
        for(int i = 0; i < tags.length(); i++){
            if(((object = tags.getJSONObject(i)).getString("language")).equals("eng"));
                engTags.add(object.getString("name"));
        }

        int size;
        String[] finalTags = new String[size = engTags.size()];
        for(int i = 0; i < size; i++){
            finalTags[i] = engTags.poll();
        }
        
        return finalTags;
    }

    public float getRating(){
        return data.getFloat("rating");
	}

	public int getID(){
		return data.getInt("id");
	}
	
	private static int searchGame(String game) throws MalformedURLException, IOException, JSONException, URISyntaxException{

		URI uri = new URI("https","api.rawg.io","/api/games","search="+game,"page_size=1");
		URL url = new URL(uri.toASCIIString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		if(connection.getResponseCode() != 200) throw new IOException("Received not a good response from the server...");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String jsonString = "", line;
		while((line = reader.readLine()) != null)
			jsonString += line+"\n";

		connection.disconnect();
		reader.close();
		
		JSONObject json = new JSONObject(jsonString);

		if("0".equals(String.valueOf(json.getInt("count")))) throw new IOException("There were no coincidences...");
		
		return json.getJSONArray("results").getJSONObject(0).getInt("id");
	}

    public static boolean downloadGameInfo(String game) throws MalformedURLException, IOException, JSONException, URISyntaxException{

		URI uri = new URI("https","api.rawg.io","/api/games/"+searchGame(game),"");
		URL url = new URL(uri.toASCIIString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		if(connection.getResponseCode() != 200) throw new IOException("Received not a good response from the server...");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String jsonString = "", line;
		while((line = reader.readLine()) != null)
			jsonString += line+"\n";
		
		connection.disconnect();
		reader.close();

		Path.resolve(Path.gameInfo);
		FileWriter file = new FileWriter(Path.gameInfo+Path.validFileName(game, "json"));
		file.append(jsonString);
		file.close();

		return true;
	}

	public static void downloadGameImage(String game) throws IOException, JSONException{

		Path.resolve(Path.gameInfo);
		FileReader file = new FileReader(Path.gameInfo+Path.validFileName(game, "json"));
		BufferedReader reader = new BufferedReader(file);

		String jsonString = "", line;
		while((line = reader.readLine()) != null)
			jsonString += line+"\n";

		reader.close();
		file.close();

		JSONObject json = new JSONObject(jsonString);
			
		BufferedImage image = ImageIO.read(new URL(json.getString("background_image")));

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
		Path.resolve(Path.gameImage);
		ImageIO.write(image,"jpg",new File(Path.gameImage+Path.validFileName(game, "jpg")));
	}

	public static boolean deleteGameInfo(String game){
		
		Path.resolve(Path.gameInfo);
		File file = new File(Path.gameInfo+Path.validFileName(game, "json"));
		return file.delete();
	}

	public static void deleteGameImage(String game){
		
		Path.resolve(Path.gameImage);
		File file = new File(Path.gameImage+Path.validFileName(game, "jpg"));
		file.delete();
	}
}

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

/**
 * <h3>GameData model class.</h3>
 * This class access to the game information downloaded from
 * the RAWG Database API and stored into JSON files. These JSON
 * files are stored into {@link Path#gameInfo}.
 * <p>
 * The method {@link #downloadGameInfo(String)} downloads the
 * game information and stores it into {@link Path#gameInfo}.
 * This data complements the user-data provided by the
 * {@link GameStat} class.
 * 
 * @author Alejandro Batres
 * @see Path
 * @see GameStat
 */
public class GameData{

	/**
	 * JSONObject which will contain the loaded JSON file.
	 */
	private JSONObject data;

	/**
	 * String which contains the name of the game.
	 */
	private String game;
	
	/**
	 * Maximum timeout value.
	 * 
	 * @see #connectionTimeout
	 * @see #readTimeout
	 */
	public static int maxTimeout = 10000;

	/**
	 * Minimum timeout value.
	 * 
	 * @see #connectionTimeout
	 * @see #readTimeout
	 */
	public static int minTimeout = 1000;

	/**
	 * Timeout value for {@link HttpURLConnection#setConnectTimeout(int)}.
	 * 
	 * @see #searchGame(String)
	 * @see #downloadGameInfo(String)
	 */
	private static int connectionTimeout = 5000;

	/**
	 * Timeout value for {@link HttpURLConnection#setReadTimeout(int)}.
	 * 
	 * @see #searchGame(String)
	 * @see #downloadGameInfo(String)
	 */
	private static int readTimeout = 5000;

	/**
	 * Constructor of the GameData class. This function
	 * will read the JSON file downloaded containing
	 * information about the specified game.
	 * 
	 * @param name String containing the name of the game
	 * and the JSON file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JSONException
	 */
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

	/**
	 * Obtain name of the game.
	 * 
	 * @return String contianing the name of the game.
	 */
    public String getName(){
        return data.getString("name");
    }

	/**
	 * Obtain description of the game in HTML format.
	 * 
	 * @return String contianing the description of
	 * the game.
	 */
	public String getDescription(){
		return data.getString("description");
	}

	/**
	 * Obtain image of the game.
	 * 
	 * @return BufferedImage containing the background
	 * image of the game.
	 */
    public BufferedImage getImage() throws IOException{
		Path.resolve(Path.gameImage);
        return ImageIO.read(new File(Path.gameImage+Path.validFileName(game, "jpg")));
	}
	
	/**
	 * Obtain a list of developers of the game.
	 * 
	 * @return String array containing the names of the
	 * developers.
	 */
	public String[] getDevelopers(){
		JSONArray developers = data.getJSONArray("developers");
        String[] array = new String[developers.length()];

        for(int i = 0; i < developers.length(); i++){
            array[i] = developers.getJSONObject(i).getString("name");
        }

        return array;
	}

	/**
	 * Obtain a list of publishers of the game.
	 * 
	 * @return String array containing the names of the
	 * publishers.
	 */
	public String[] getPublishers(){
		JSONArray publishers = data.getJSONArray("publishers");
        String[] array = new String[publishers.length()];

        for(int i = 0; i < publishers.length(); i++){
            array[i] = publishers.getJSONObject(i).getString("name");
        }

        return array;
	}

	/**
	 * Obtain the release date of the game.
	 * 
	 * @return String containing the release date of
	 * the game.
	 */
    public String getReleaseDate(){
        return data.getString("released");
    }

	/**
	 * Obtain a list of platforms where the game
	 * is available.
	 * 
	 * @return String array of platforms that have
	 * this game available.
	 */
    public String[] getPlatforms(){
        JSONArray platforms = data.getJSONArray("platforms");
        String[] array = new String[platforms.length()];

        for(int i = 0; i < platforms.length(); i++){
            array[i] = platforms.getJSONObject(i).getJSONObject("platform").getString("name");
        }

        return array;
    }

	/**
	 * Obtain a list of genres of the game.
	 * 
	 * @return String array containing the genres
	 * of the game.
	 */
    public String[] getGenres(){
        JSONArray genres = data.getJSONArray("genres");
        String[] array = new String[genres.length()];

        for(int i = 0; i < genres.length(); i++){
            array[i] = genres.getJSONObject(i).getString("name");
        }

        return array;
    }

	/**
	 * Obtain a list of tags of the game. Note
	 * that these tags are given by the users
	 * on RAWG's website.
	 * 
	 * @return String array containing the tags
	 * of the game,
	 */
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

	/**
	 * Obtain the rating of the game. Note
	 * that this rating is meassured by all
	 * the rates of the users on RAWG's
	 * website.
	 * 
	 * @return Number of the rating of the
	 * game.
	 */
    public float getRating(){
        return data.getFloat("rating");
	}

	/**
	 * Obtain the (RAWG) ID of the game.
	 * 
	 * @return ID of the game.
	 */
	public int getID(){
		return data.getInt("id");
	}

	/**
	 * Sets timeout value for {@link HttpURLConnection#setConnectTimeout(int)}.
	 * 
	 * @param timeout Number of milliseconds
	 */
	public static void setConnectionTimeout(int timeout){
		connectionTimeout = timeout;
	}

	/**
	 * Gets timeout value for {@link HttpURLConnection#setConnectTimeout(int)}.
	 * 
	 * @return Number of milliseconds
	 */
	public static int getConnectionTimeout(){
		return connectionTimeout;
	}

	/**
	 * Sets timeout value for {@link HttpURLConnection#setReadTimeout(int)}.
	 * 
	 * @param timeout Number of milliseconds
	 */
	public static void setReadTimeout(int timeout){
		readTimeout = timeout;
	}

	/**
	 * Gets timeout value for {@link HttpURLConnection#setReadTimeout(int)}.
	 * 
	 * @return Number of milliseconds
	 */
	public static int getReadTimeout(){
		return readTimeout;
	}
	
	/**
	 * Makes a request to the {@code api.rawg.io}
	 * page with the given name as a search value.
	 * <p>
	 * The full GET request String is the following:
	 * {@code https://api.rawg.io/api/games?search=name&page_size=1}.
	 * <p>
	 * This function looks for the first occurrence
	 * (game) in the search and returns its ID.
	 * Also, the connect and read timeout is
	 * established by {@link #connectionTimeout}
	 * and {@link #readTimeout} respectively.
	 * 
	 * @param game String containing the name of the
	 * game that will be searched
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JSONException
	 * @throws URISyntaxException
	 * @see #connectionTimeout
	 * @see #readTimeout
	 * @return ID of the first occurrence in the
	 * search.
	 */
	private static int searchGame(String game) throws MalformedURLException, IOException, JSONException, URISyntaxException{

		URI uri = new URI("https","api.rawg.io","/api/games","search="+game,"page_size=1");
		URL url = new URL(uri.toASCIIString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(connectionTimeout);
		connection.setReadTimeout(readTimeout);

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

	/**
	 * Makes a request to the {@code api.rawg.io}
	 * with the given ID.
	 * <p>
	 * The full GET request String is the following:
	 * {@code https://api.rawg.io/api/games/id}.
	 * <p>
	 * This function will download the game information
	 * from the RAWG database. Also, the connect and read
	 * timeout is established by {@link #connectionTimeout}
	 * and {@link #readTimeout} respectively.
	 * <p>
	 * The downloaded game information will be stored
	 * into {@link Path#gameInfo} named with the String
	 * {@code game} provided <i>lowcased</i>, using the regex
	 * {@code [^ ()a-z0-9+-]} to replace every occurrence
	 * with a "" (nothing), and then
	 * replacing every <b>space</b> with "<b>_</b>" and adding
	 * the extension "<b>.json</b>". For example if the
	 * {@code game}'s value is "<b>So_me Game!!!!</b>":
	 * {@code some_game.json}. This is because of the 
	 * {@link Path#validFileName(String, String)}.
	 * 
	 * @param game String containing the name of the
	 * game which its ID will be used in the request.
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JSONException
	 * @throws URISyntaxException
	 * @see Path#gameInfo
	 * @see #connectionTimeout
	 * @see #readTimeout
	 * @return {@code ture} if the download was
	 * successful. {@code false} otherwise.
	 */
    public static boolean downloadGameInfo(String game) throws MalformedURLException, IOException, JSONException, URISyntaxException{

		URI uri = new URI("https","api.rawg.io","/api/games/"+searchGame(game),"");
		URL url = new URL(uri.toASCIIString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(connectionTimeout);
		connection.setReadTimeout(readTimeout);

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

	/**
	 * Downloads the image by the URL
	 * provided in the downloaded JSON file.
	 * <p>
	 * The downloaded image will be stored into
	 * {@link Path#gameImage} named with the String
	 * {@code game} provided <i>lowcased</i>, using the regex
	 * {@code [^ ()a-z0-9+-]} to replace every occurrence
	 * with a "" (nothing), and then
	 * replacing every <b>space</b> with "<b>_</b>" and adding
	 * the extension "<b>.img</b>". For example if the
	 * {@code game}'s value is "<b>So_me Game!!!!</b>":
	 * {@code some_game.img}. This is because of the 
	 * {@link Path#validFileName(String, String)}.
	 * 
	 * @param game String containing the name of the
	 * game which its {@code background_image} will
	 * be downloaded.
	 * @throws IOException
	 * @throws JSONException
	 * @see Path#gameImage
	 */
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

	/**
	 * Deletes the JSON file of the game.
	 * 
	 * @param game String containing the name
	 * of the game.
	 * @see Path#gameInfo
	 * @return {@code ture} if the delete was
	 * successful. {@code false} otherwise.
	 */
	public static boolean deleteGameInfo(String game){
		
		Path.resolve(Path.gameInfo);
		File file = new File(Path.gameInfo+Path.validFileName(game, "json"));
		return file.delete();
	}

	/**
	 * Deletes the image of the game.
	 * 
	 * @param game String containing the name
	 * of the game.
	 * @see Path#gameImage
	 */
	public static void deleteGameImage(String game){
		
		Path.resolve(Path.gameImage);
		File file = new File(Path.gameImage+Path.validFileName(game, "jpg"));
		file.delete();
	}
}

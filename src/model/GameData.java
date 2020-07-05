package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.*;

import util.Path;

public class GameData{

    private JSONObject data;
    private String game;

    public GameData(String name) throws FileNotFoundException, IOException, ParseException{
        data = (JSONObject)(new JSONParser()).parse(new FileReader(Path.gameInfo+Path.validFileName(name, "json")));
        game = name;
    }

    public String getName(){
        return (String)data.get("name");
    }

    public BufferedImage getImage() throws IOException{
        return ImageIO.read(new File(Path.gameImage+Path.validFileName(game, "jpg")));
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

}
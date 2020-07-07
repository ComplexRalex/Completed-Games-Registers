package util;

import java.awt.Color;
import model.Configuration;

/**
 * RALEX XD DON'T FORGET TO ADD THE POSSIBILITY TO ADD CUSTOM THEMES!
 * (later...) 
 */

public class Colour {

	public static final int LIGHT_THEME = 0;
	public static final int DARK_THEME = 1;
	public static final int NIGHT_THEME = 2;

	public static final Color colorON = new Color(110,220,116);
	public static final Color colorOFF = new Color(220,90,90);

	public static final String[] available = {"Light", "Dark", "Night"};
	
	private static final Color[][] THEME_COLORS = {
			{	// Light Colors
				new Color(10,10,10),	// Font Color
				new Color(230,230,230),	// (Maybe) Background Color
				new Color(240,240,240), // Button Color
				new Color(250,250,250)	// Background Color	
			},
			{	// Dark Colors
				new Color(230,230,230),
				new Color(87,87,87),
				new Color(50,50,50), 
				new Color(34,34,34)
			},
			{	// Night Colors
				new Color(225,226,238),
				new Color(60,62,78),
				new Color(50,51,65),
				new Color(35,34,40)
			}
	};
	
	public static final Color transparent = new Color(0f,0f,0f,0.0001f);
	
	public static Color getFontColor(){
		try{
			return THEME_COLORS[Configuration.currentTheme()][0];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[DARK_THEME][0];
		}
	}
	public static Color getPrimaryColor(){
		try{
			return THEME_COLORS[Configuration.currentTheme()][1];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[DARK_THEME][1];
		}
	}
	public static Color getButtonColor(){
		try{
			return THEME_COLORS[Configuration.currentTheme()][2];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[DARK_THEME][2];
		}
	}
	public static Color getBackgroundColor(){
		try{
			return THEME_COLORS[Configuration.currentTheme()][3];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[DARK_THEME][3];
		}
	}

	public static int getLuminance(Color color){
		return (color.getRed()+color.getGreen()+color.getBlue())/3;
	}
	
}

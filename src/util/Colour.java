package util;

import java.awt.Color;

/**
 * <h3>Colour utility class.</h3>
 * This class provides the theme colors of the visual components.
 * Also, it's used in the {@link Advice} and {@link Component}
 * classes.
 * 
 * @see Advice
 * @see Component
 */
public class Colour{

	/**
	 * Light theme value.
	 */
	public static final int LIGHT_THEME = 0;

	/**
	 * Dark theme value.
	 */
	public static final int DARK_THEME = 1;

	/**
	 * Night theme value.
	 */
	public static final int NIGHT_THEME = 2;

	/**
	 * Current theme value. The default is {@link #NIGHT_THEME}.
	 * 
	 * @see #getCurrentTheme()
	 * @see #setCurrentTheme()
	 */
	private static int currentTheme = NIGHT_THEME;

	/**
	 * Light-green color for the ON switch button.
	 * 
	 * @see Component#createSwitchButton(String, javax.swing.JButton, javax.swing.JButton, Color)
	 */
	public static final Color colorON = new Color(110,220,116);

	/**
	 * Light-red color for the OFF switch button.
	 * 
	 * @see Component#createSwitchButton(String, javax.swing.JButton, javax.swing.JButton, Color)
	 */
	public static final Color colorOFF = new Color(220,90,90);

	/**
	 * String array of available themes.
	 */
	public static final String[] available = {"Light", "Dark", "Night"};
	
	/**
	 * Color matrix. The first index represents the <b>theme</b>
	 * of the colors, and the second index can be the following
	 * values:
	 * <ul>
	 * <li>[0]: Font color.
	 * <li>[1]: Primary color (another background color).
	 * <li>[2]: Button color.
	 * <li>[3]: Background color.
	 * </ul>
	 * For example, the button color of the {@link #NIGHT_THEME}
	 * is: {@code THEME_COLORS[2][2]}.
	 * 
	 * @see #getFontColor()
	 * @see #getPrimaryColor()
	 * @see #getButtonColor()
	 * @see #getBackgroundColor()
	 */
	private static final Color[][] THEME_COLORS = {
			{	// Light Colors
				new Color(10,10,10),	// Font Color
				new Color(250,250,250),	// (Maybe) Background Color
				new Color(230,230,230), // Button Color
				new Color(220,220,220)	// Background Color	
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
				new Color(42,43,55),
				new Color(35,34,40)
			}
	};
	
	/**
	 * Black color with an alpha value of 0.0001.
	 */
	public static final Color transparent = new Color(0f,0f,0f,0.0001f);
	
	/**
	 * Returns the number of the current theme.
	 * 
	 * @return {@link #currentTheme}'s value.
	 */
	public static int getCurrentTheme(){
		return currentTheme;
	}

	/**
	 * Sets the current theme.
	 * 
	 * @param theme New {@link #currentTheme}'s value.
	 */
	public static void setCurrentTheme(int theme){
		currentTheme = theme;
	}

	/**
	 * Obtains the font color which corresponds to
	 * the current theme.
	 * 
	 * @return {@code THEME_COLORS[currentTheme][0]}
	 */
	public static Color getFontColor(){
		try{
			return THEME_COLORS[currentTheme][0];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[NIGHT_THEME][0];
		}
	}

	/**
	 * Obtains the (possible) background color
	 * which corresponds to the current theme.
	 * 
	 * @return {@code THEME_COLORS[currentTheme][1]}
	 */
	public static Color getPrimaryColor(){
		try{
			return THEME_COLORS[currentTheme][1];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[NIGHT_THEME][1];
		}
	}

	/**
	 * Obtains the button color which corresponds
	 * to the current theme.
	 * 
	 * @return {@code THEME_COLORS[currentTheme][2]}
	 */
	public static Color getButtonColor(){
		try{
			return THEME_COLORS[currentTheme][2];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[NIGHT_THEME][2];
		}
	}

	/**
	 * Obtains the background color which
	 * corresponds to the current theme.
	 * 
	 * @return {@code THEME_COLORS[currentTheme][3]}
	 */
	public static Color getBackgroundColor(){
		try{
			return THEME_COLORS[currentTheme][3];
		}
		catch(IndexOutOfBoundsException e){
			return THEME_COLORS[NIGHT_THEME][3];
		}
	}

	/**
	 * Returns the "<b>luminance <i>factor</i></b>"
	 * of the provided color.
	 * <p>
	 * This value is described by the following
	 * equation:
     * <p>
     * <b>{@code luminance}</b>{@code = (redValue + greenValue + blueValue) / 3}.
     * <p>
	 * Basically, it's the average of the color codes.
	 * 
	 * @param color which will be used to obtain the luminance factor
	 * @see ImageResource#colorAndShadow(java.awt.image.BufferedImage, Color, float)
	 * @return Luminance factor (between {@code [0,255]}).
	 */
	public static int getLuminance(Color color){
		return (color.getRed()+color.getGreen()+color.getBlue())/3;
	}	
}

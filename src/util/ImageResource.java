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

package util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

/**
 * <h3>ImageResource utility class.</h3>
 * This class provides a way to obtain images that are located
 * in the resource folder of the project.
 * <p>
 * Also, it includes the path of the resources currently
 * available.
 * 
 * @author Alejandro Batres
 * @see Path
 */
public class ImageResource{
    
    /**
     * ClassLoader which will be used to load the resources
	 */
    private ClassLoader loader;
	
    /**
     * Path to the image logotype.png in the resources folder.
     */
    public static final String LOGOTYPE = Path.guiPath+"logotype.png";

	/**
	 * Path to the image add.png in the resources folder.
	 */
    public static final String ADD = Path.guiPath+"add.png";

    /**
	 * Path to the image backup.png in the resources folder.
	 */
    public static final String BACKUP = Path.guiPath+"backup.png";

    /**
	 * Path to the image export.png in the resources folder.
	 */
    public static final String EXPORT = Path.guiPath+"export.png";

    /**
	 * Path to the image help.png in the resources folder.
	 */
    public static final String HELP = Path.guiPath+"help.png";

    /**
	 * Path to the image view.png in the resources folder.
	 */
    public static final String VIEW = Path.guiPath+"view.png";

    /**
	 * Path to the image edit.png in the resources folder.
	 */
    public static final String EDIT = Path.guiPath+"edit.png";

    /**
	 * Path to the image remove.png in the resources folder.
	 */
    public static final String REMOVE = Path.guiPath+"remove.png";

    /**
	 * Path to the image frame_icon.png in the resources folder.
	 */
    public static final String FRAME = Path.iconPath+"frame_icon.png";

    /**
	 * Path to the image advice_icon.png in the resources folder.
	 */
    public static final String ADVICE = Path.iconPath+"advice_icon.png";
    
    /**
     * Initializes a ClassLoader in order to obtain the resources later.
     */
    public ImageResource(){
    	loader = getClass().getClassLoader();
    }
    
    /**
     * Requests the given image. It's recommended to use one of the
     * static variables in this class.
     * 
     * @param image String with the given path to the image.
     * @return a BufferedImage
     */
	public BufferedImage resource(String image){
		try{
			URL link = loader.getResource(image);
			if(link == null) throw new IllegalArgumentException();
			return ImageIO.read(link);
		}catch(IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
    
    /**
     * Colors the given BufferedImage (with the given color) and also
     * redraw every pixel based on the "luminance" of such pixel,
     * replacing its alpha value with it.
     * <p>
     * This means that, depending on every pixel RGB value, it will
     * give a luminance level which is determined by the following
     * equation:
     * <p>
     * <b>{@code luminance}</b>{@code = (redValue + greenValue + blueValue) / 3}
     * <p>
     * Note that the given color will replace just brighter colors.
     * Thus, the darker colors aren't going to big change. Technically, 
     * the way to determine this change is by using the following 
     * equation:
     * <p>
     * <b>newColor</b> = oldColor*luminance/255
     * <p> The "brightness" parameter multiplies the previous 
     * magnitude.
     * 
     * @param image BufferedImage that will be colored
     * @param color Destiny color of the icon
     * @param brightness Float number which determines the level of
     * brightness that will be increased every color channel.
     * <p>
     * The default value (original brightness) is <b>1.0f<b>.
     * @see Colour#getLuminance(Color)
     * @return The modified BufferedImage
     */
    public static BufferedImage colorAndShadow(BufferedImage image, Color color, float brightness){
        BufferedImage img = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D gph = (Graphics2D)img.createGraphics();
        gph.setComposite(AlphaComposite.Src);
        gph.drawImage(image, 0, 0, null);
        gph.dispose();
        int r = color.getRed(), g = color.getGreen(), b = color.getBlue(),
            newR, newG, newB, luminance;
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                luminance = Colour.getLuminance(new Color(img.getRGB(i,j)));
                img.setRGB(i, j, ((new Color(
                    (newR = (int)(r*luminance/255*brightness)) <= 255 ? newR : 255,
                    (newG = (int)(g*luminance/255*brightness)) <= 255 ? newG : 255,
                    (newB = (int)(b*luminance/255*brightness)) <= 255 ? newB : 255
                )).getRGB() & 0x00ffffff) + (luminance << 24));
            }
        }
        return img;
    }
}

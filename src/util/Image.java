package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

public class Image{
    
    private static BufferedImage add;
    private static BufferedImage backup;
    private static BufferedImage export;
    private static BufferedImage help;
    private static BufferedImage view;
    private static BufferedImage edit;
    private static BufferedImage remove;

    private static BufferedImage frame;
    private static BufferedImage advice;
    
    // Theoricatelly, it shouldn't throw an IOException
    public static void initialize(){
        try{
            add = ImageIO.read(new File(Path.imagePath+"add.png"));
        }catch(IOException e){}
        try{
            backup = ImageIO.read(new File(Path.imagePath+"backup.png"));
        }catch(IOException e){}
        try{
            export = ImageIO.read(new File(Path.imagePath+"export.png"));
        }catch(IOException e){}
        try{
            help = ImageIO.read(new File(Path.imagePath+"help.png"));
        }catch(IOException e){}
        try{
            view = ImageIO.read(new File(Path.imagePath+"view.png"));
        }catch(IOException e){}
        try{
            edit = ImageIO.read(new File(Path.imagePath+"edit.png"));
        }catch(IOException e){}
        try{
            remove = ImageIO.read(new File(Path.imagePath+"remove.png"));
        }catch(IOException e){}
        try{
            frame = ImageIO.read(new File(Path.imagePath+"frame_icon.png"));
        }catch(IOException e){}
        try{
            advice = ImageIO.read(new File(Path.imagePath+"advice_icon.png"));
        }catch(IOException e){}
    }

	public static BufferedImage getAdd(){return add;}
	public static BufferedImage getBackup(){return backup;}
	public static BufferedImage getExport(){return export;}
	public static BufferedImage getHelp(){return help;}
	public static BufferedImage getView(){return view;}
	public static BufferedImage getEdit(){return edit;}
    public static BufferedImage getRemove(){return remove;}
    public static BufferedImage getFrame(){return frame;}
    public static BufferedImage getAdvice(){return advice;}
    
    /**
     * Colors the given BufferedImage (with the given color) and also
     * redraw every pixel based on the "lumminance" of such pixel,
     * replacing its alpha value with it.
     * <p>
     * This means that, depending on every pixel RGB value, it will
     * give a lumminance level which is determined by the following
     * equation:
     * <p>
     * <b>lumminance</b> = (redValue + greenValue + blueValue) / 3
     * <p>
     * Note that the given color will replace just brighter colors.
     * Thus, the darker colors aren't going to big change. Technically, 
     * the way to determine this change is by using the following 
     * equation:
     * <p>
     * <b>newColor</b> = oldColor*lumminance/255
     * <p> The "brightness" parameter multiplies the previous 
     * magnitude.
     * 
     * @param image BufferedImage that will be colored
     * @param color Destiny color of the icon
     * @param brightness Float number which determines the level of
     * brightness that will be increased every color channel.
     * <p>
     * The default value (original brightness) is <b>1.0f<b>.
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
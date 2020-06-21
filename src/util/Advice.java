package util;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

public class Advice{

    /**
     * Displays a frame with some simple advice message.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param bg Background color
     */
    public static void showSimpleAdvice(java.awt.Component parent, String title, String message, Color bg){
        
        JOptionPane pane = new JOptionPane();
        pane.setBackground(bg);
        pane.setMessage(message);

		styleComponents(pane, bg);
		JDialog dialog = pane.createDialog(parent, title);
		dialog.setVisible(true);
    }

    // public static void showConfirmAdvise(java.awt.Component parent,String message){ ... }
    
    /**
     * Styles every element inside of the container. To be clear, changes
     * the visual properties of the elements included inside the given
     * container.
     * 
     * @param c Container containing contents
     * @param background Background color
     */
    private static void styleComponents(Container c, Color background){
        
        java.awt.Component comp[] = c.getComponents();
        for(int i = 0; i < comp.length; i++){

            if(comp[i].getClass().getName() == "javax.swing.JLabel"){
                comp[i].setBackground(background);
                comp[i].setForeground(Colour.getFontColor());
                comp[i].setFont(Typeface.labelPlain);

            }else if(comp[i].getClass().getName() == "javax.swing.JButton"){
                comp[i].setBackground(Colour.getButtonColor());
                comp[i].setForeground(Colour.getFontColor());
                comp[i].setFont(Typeface.buttonPlain);
            
            }else if(comp[i].getClass().getName() == "javax.swing.JPanel"){
                comp[i].setBackground(background);
                styleComponents((Container)comp[i], background);
            }
        }
	}
}
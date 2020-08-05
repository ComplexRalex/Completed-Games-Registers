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

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * <h3>Advice utility class.</h3>
 * This class have a bunch of {@code static} functions which
 * are used to display a {@link JDialog}, showing information
 * about the operations that are happening in the moment.
 * <p>
 * These methods returns a {@code int} value that determine
 * which of the given options was selected. In fact, returns
 * the <b>index</b> of the String option (contained in the selected
 * {@link JButton}) in the provided String array. If it is
 * just a option, it will return <b>0</b>. However, if none of
 * the JButtons were clicked, the return value will be <b>-1</b>.
 * <p>
 * The simplest and useful method is the following:
 * {@link #showSimpleAdvice(Container, String, String, String, Color)}.
 * <p>
 * The method used to display {@link Throwable}s is the following:
 * {@link #showTextAreaAdvice(Container, String, String, String, int, int, String, Color)}
 * with {@link #EXCEPTION_WIDTH} and {@link #EXCEPTION_HEIGHT} dimensions.
 * 
 * @author Alejandro Batres
 * @see Colour
 */
public class Advice{

    /**
     * Selected option in the current JDialog.
     */
    private static String selected = "";

    /**
     * Default columns of JTextField in case of showing a stack trace of
     * an exception.
     */
    public final static int EXCEPTION_WIDTH = 60;

    /**
     * Default rows of JTextField in case of showing a stack trace of an
     * exception.
     */
    public final static int EXCEPTION_HEIGHT = 10;

    /**
     * Displays a frame with some simple advice message.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param okay_op Text inside the <i>okay</i> button
     * @param bg Background color
     */
    public static void showSimpleAdvice(Container parent, String title, String message, String okay_op, Color bg){
        showOptionComponentAdvice(parent, title, message, null, new String[]{okay_op}, bg);
    }

    /**
     * Displays a frame with some simple advice message with the provided
     * options.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param options String array containing every option
     * @param bg Background color
     * @return Array index of the selected option in the frame. 
     * For instance, if you click the first button, it will return
     * <b>0</b>. If it is not selected any button but the
     * close-button window, it will return <b>-1</b>.
     */
    public static int showOptionAdvice(Container parent, String title, String message, String options[], Color bg){
        return showOptionComponentAdvice(parent, title, message, null, options, bg);
    }

    /**
     * Displays a frame with some simple advice message with the provided
     * long message.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param longMessage Long text that will be show inside a
     * JTextArea. Note that this text area won't be editable.
     * @param width Number of columns of the JTextArea
     * @param height Number of rows of the JTextArea
     * @param okay_op Text inside the <i>okay</i> button
     * @param bg Background color
     */
    public static void showTextAreaAdvice(Container parent, String title, String message, String longMessage, int width, int height, String okay_op, Color bg){
        showOptionTextAreaAdvice(parent, title, message, longMessage, width, height, new String[]{okay_op}, bg);
    }

    /**
     * Displays a frame with some simple advice message with the provided
     * long message and options.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param longMessage Long text that will be show inside a
     * JTextArea. Note that this text area won't be editable.
     * @param width Number of columns of the JTextArea
     * @param height Number of rows of the JTextArea
     * @param options String array containing every option
     * @param bg Background color
     * @return Array index of the selected option in the frame. 
     * For instance, if you click the first button, it will return
     * <b>0</b>. If it is not selected any button but the
     * close-button window, it will return <b>-1</b>.
     */
    public static int showOptionTextAreaAdvice(Container parent, String title, String message, String longMessage, int width, int height, String options[], Color bg){  

        JTextArea area = new JTextArea(longMessage,height,width);
        area.setBackground(bg == Colour.getBackgroundColor() ? Colour.getPrimaryColor() : Colour.getBackgroundColor());
        area.setForeground(Colour.getFontColor());
        area.setCaretColor(Colour.getFontColor());
        area.setFont(Typeface.textPlain);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBackground(bg);
        scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        scroll.getVerticalScrollBar().setUnitIncrement(Component.SCROLLBAR_INCREMENT);

        return showOptionComponentAdvice(parent, title, message, scroll, options, bg);
    }

    /**
     * Displays a frame with some simple advice message with the provided
     * component.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param component Component (this won't be modified)
     * @param okay_op Text inside the <i>okay</i> button
     * @param bg Background color
     */
    public static void showComponentAdvice(Container parent, String title, String message, java.awt.Component component, String okay_op, Color bg){
        showOptionComponentAdvice(parent, title, message, component, new String[]{okay_op}, bg);
    }

    /**
     * Displays a frame with some simple advice message with the provided
     * component and options.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param component Component (this won't be modified)
     * @param options String array containing every option
     * @param bg Background color
     * @return Array index of the selected option in the frame. 
     * For instance, if you click the first button, it will return
     * <b>0</b>. If it is not selected any button but the
     * close-button window, it will return <b>-1</b>.
     */
    public static int showOptionComponentAdvice(Container parent, String title, String message, java.awt.Component component, String options[], Color bg){

        JDialog dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(bg);
        dialog.setResizable(false);
        dialog.setTitle(title);
        try{
        	dialog.setIconImage((new ImageResource()).resource(ImageResource.ADVICE));
        }catch(IllegalArgumentException | NullPointerException e){/* In case of non-existence, it will be ignored.*/}
        dialog.setModal(true);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = Component.margin;
        c.gridx = 0;

        JPanel text = new JPanel(new FlowLayout(FlowLayout.CENTER,8,8));
        text.setBackground(bg);

        JLabel msg = new JLabel();
        msg.setText(message);
        msg.setBackground(bg);
        msg.setForeground(Colour.getFontColor());
        msg.setFont(Typeface.labelPlain);

        text.add(msg);

        c.gridy = 0;
        dialog.add(text,c);

        if(component != null){
            c.gridy++;
            dialog.add(component,c);
        }

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER,8,8));
        buttons.setBackground(bg);

        JButton bt[] = new JButton[options.length];
        for(int i = 0; i < options.length; i++){
            bt[i] = new JButton(options[i]);
            bt[i].setBackground(Colour.getButtonColor());
            bt[i].setForeground(Colour.getFontColor());
            bt[i].setFont(Typeface.buttonPlain);

            buttons.add(bt[i]);
        }

        c.gridy++;
        dialog.add(buttons,c);

        dialog.pack();
        if(parent != null)
            dialog.setLocation(
                (int)parent.getLocationOnScreen().getX()+parent.getWidth()/2-dialog.getWidth()/2,
                (int)parent.getLocationOnScreen().getY()+parent.getHeight()/2-dialog.getHeight()/2
            );
        else
            dialog.setLocation(
                (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-dialog.getWidth()/2,
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-dialog.getHeight()/2
            );

        setAction(bt, dialog);

        dialog.setVisible(true);
        dialog.setVisible(false);
        dialog.dispose();

        return getResult(options);
    }

    /**
     * Adds a ActionListener to every given button in the array.
     * 
     * @param buttons JButton array
     * @param dialog JDialog which will contain every button
     */
    private static void setAction(JButton[] buttons, JDialog dialog){
        for(JButton bt: buttons){
            bt.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == bt) selected = bt.getText();
                    dialog.dispose();
                }
            });
        }
    }
    
    /**
     * Returns the index of the string of the given options which was selected
     * in the JDialog. It will return <b>-1</b> if there was no option selected.
     * 
     * @param options String array containing every option
     * @return Return the index of the selected options. If there was no
     * selection, it will return <b>-1</b>.
     */
    private static int getResult(String[] options){
        for(int i = 0; i < options.length; i++)
            if(options[i].equals(selected)){
                selected = "";
                return i;
            };
        return -1;
    }
}

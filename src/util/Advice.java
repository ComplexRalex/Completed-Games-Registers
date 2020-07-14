package util;

import java.io.StringWriter;
import java.io.PrintWriter;
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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Advice{

    /**
     * Selected option in the JDialog
     */
    private static String selected = "";

    /**
     * Displays a frame with some simple advice message.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param okay_op Text inside the <i>okay</i> button
     * @param bg Background color
     */
    public static void showSimpleAdvice(java.awt.Component parent, String title, String message, String okay_op, Color bg){
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
    public static int showOptionAdvice(java.awt.Component parent, String title, String message, String options[], Color bg){
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
     * @param okay_op Text inside the <i>okay</i> button
     * @param bg Background color
     */
    public static void showTextAreaAdvice(java.awt.Component parent, String title, String message, String longMessage, String okay_op, Color bg){
        showOptionTextAreaAdvice(parent, title, message, longMessage, new String[]{okay_op},bg);
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
     * @param options String array containing every option
     * @param bg Background color
     * @return Array index of the selected option in the frame. 
     * For instance, if you click the first button, it will return
     * <b>0</b>. If it is not selected any button but the
     * close-button window, it will return <b>-1</b>.
     */
    public static int showOptionTextAreaAdvice(java.awt.Component parent, String title, String message, String longMessage, String options[], Color bg){  

        JTextArea area = new JTextArea(longMessage,5,40);
        area.setBackground(bg == Colour.getBackgroundColor() ? Colour.getPrimaryColor() : Colour.getBackgroundColor());
        area.setForeground(Colour.getFontColor());
        area.setCaretColor(Colour.getFontColor());
        area.setFont(Typeface.textPlain);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBackground(bg);

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
    public static void showComponentAdvice(java.awt.Component parent, String title, String message, java.awt.Component component, String okay_op, Color bg){
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
    public static int showOptionComponentAdvice(java.awt.Component parent, String title, String message, java.awt.Component component, String options[], Color bg){

        JDialog dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(bg);
        dialog.setResizable(false);
        dialog.setTitle(title);
        dialog.setIconImage((new ImageIcon(Path.images+"advice_icon.png")).getImage());
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

    /**
     * Converts a stack trace (by a thrown Exception) into a string.
     * 
     * @param e Throwable
     * @return String containing the stack trace
     */
    public static String getStackTrace(Throwable e){
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }
}
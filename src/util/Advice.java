package util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Advice{

    // Selected option in the JDialog
    private static String selected = "";

	/**
     * Displays a frame with some simple advice message.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param bg Background color
     */
    public static void showSimpleAdvice(java.awt.Component parent, String title, String message, Color bg){
        showOptionAdvice(parent, title, message, new String[]{Language.loadMessage("g_accept")}, bg);
    }

    /**
     * Displays a frame with some simple advice message with a custom
     * "okay" mtext in the <i>okay</i> button.
     * 
     * @param parent Container where this frame will show up
     * @param title Text on the window bar of the frame
     * @param message Text that will show up inside the frame
     * @param okay_op Text inside the <i>okay</i> button
     * @param bg Background color
     */
    public static void showSimpleAdvice(java.awt.Component parent, String title, String message, String okay_op, Color bg){
        showOptionAdvice(parent, title, message, new String[]{okay_op}, bg);
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

        JDialog dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(bg);
        dialog.setResizable(false);
        dialog.setTitle(title);
        dialog.setModal(true);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = Component.margin;
        c.gridx = 0;

        JPanel text = new JPanel(new FlowLayout(FlowLayout.CENTER,8,8));
        text.setBackground(bg);

        JLabel msg = new JLabel(String.format(
            "<html><body style='max-width: %dpx; background: #%06x;"+
            "font-family: %s; font-weight: normal; font-size: %d;"+
            " color: #%06x; text-align: justify;'>%s",
            250,
            Integer.valueOf(bg.getRGB() & 0x00FFFFFF),
            Typeface.labelPlain.getName(),
            Typeface.labelPlain.getSize(),
            Integer.valueOf(Colour.getFontColor().getRGB() & 0x00FFFFFF),
            message
        ));
        msg.setBackground(bg);
        msg.setForeground(Colour.getFontColor());
        msg.setFont(Typeface.labelPlain);

        text.add(msg);

        c.gridy = 0;
        dialog.add(text,c);

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
        dialog.setLocation(
            (int)parent.getLocationOnScreen().getX()+parent.getWidth()/2-dialog.getWidth()/2,
            (int)parent.getLocationOnScreen().getY()+parent.getHeight()/2-dialog.getHeight()/2
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
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import util.Advice;
import util.Colour;
import util.Component;
import util.Language;
import util.Path;
import util.SimplePanel;
import util.Typeface;

@SuppressWarnings("serial")
public class GeneralPanel extends JPanel{
    private JPanel pCentral;
    private ImageIcon iconAdd, iconHelp, iconView, iconEdit, iconRemove;
    public JButton btAdd, btHelp, btConfig, btAbout;
    public JLabel lbUser;
    public FirstTimePanel pNothing;

    public GeneralPanel(){
        setLayout(new BorderLayout());
        setBackground(Colour.getBackgroundColor());

        pNothing = new FirstTimePanel();

        initIcons();
        initComponents();
    }

    public class GameRegisterPanel extends JPanel{
        public JButton btView, btEdit, btRemove;
        public JTextArea aName;

        public GameRegisterPanel(String game){
            setLayout(new BorderLayout());
            setBackground(Colour.getBackgroundColor());
            initComponents(game);
        }

        public void initComponents(String game){

            JPanel title = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
            title.setBackground(this.getBackground());

            aName = new JTextArea(game);
            aName.setBackground(this.getBackground());
            aName.setForeground(Colour.getFontColor());
            aName.setLineWrap(true);
            aName.setWrapStyleWord(true);
            aName.setEditable(false);
            aName.setFont(Typeface.labelBold);
            aName.setColumns(17);
            title.add(aName);

            add(title,BorderLayout.WEST);

            JPanel options = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
            options.setBackground(this.getBackground());

            btView = new JButton(iconView);
            btView.setBackground(Colour.getButtonColor());

            btEdit = new JButton(iconEdit);
            btEdit.setBackground(Colour.getButtonColor());

            btRemove = new JButton(iconRemove);
            btRemove.setBackground(Colour.getButtonColor());

            options.add(btView);
            options.add(btEdit);
            options.add(btRemove);

            add(options,BorderLayout.EAST);
        }
    }

    public class FirstTimePanel extends JPanel{
        public FirstTimePanel(){
            setLayout(new BorderLayout());
            setBackground(Colour.getBackgroundColor());
            
            JLabel message = new JLabel(Language.loadMessage("m_nothing"));
            message.setHorizontalAlignment(JLabel.CENTER);
            message.setForeground(Colour.getFontColor());
            message.setFont(Typeface.labelPlain);

            add(message,BorderLayout.CENTER);
        }
    }

    public void initIcons(){
        try {
            float brightness = 0.035f*Colour.getLuminance(Colour.getBackgroundColor());
            iconAdd = Component.colorAndShadowIcon(new ImageIcon(ImageIO.read(new File(Path.images+"add.png"))),Colour.getFontColor(),brightness);
            iconHelp = Component.colorAndShadowIcon(new ImageIcon(ImageIO.read(new File(Path.images+"help.png"))),Colour.getFontColor(),brightness);
            iconView = Component.colorAndShadowIcon(new ImageIcon(ImageIO.read(new File(Path.images+"view.png"))),Colour.getFontColor(),brightness);
            iconEdit = Component.colorAndShadowIcon(new ImageIcon(ImageIO.read(new File(Path.images+"edit.png"))),Colour.getFontColor(),brightness);
            iconRemove = Component.colorAndShadowIcon(new ImageIcon(ImageIO.read(new File(Path.images+"remove.png"))),Colour.getFontColor(),brightness);
		} catch (IOException e) {
			Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong"), e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		}
    }

    public void initComponents(){

        JPanel pWelcome = new JPanel(new FlowLayout(FlowLayout.CENTER,0,21));
        pWelcome.setBackground(Colour.getPrimaryColor());
        JLabel lbWelcome = new JLabel(Language.loadMessage("m_title"));
        lbWelcome.setFont(Typeface.labelTitle);
        lbWelcome.setBackground(this.getBackground());
        lbWelcome.setForeground(Colour.getFontColor());
        pWelcome.add(lbWelcome);
        lbUser = new JLabel("Username");
        lbUser.setFont(Typeface.labelTitle);
        lbUser.setBackground(this.getBackground());
        lbUser.setForeground(Colour.getFontColor());
        pWelcome.add(lbUser);
        add(pWelcome,BorderLayout.NORTH);

        // ----- TEST AREA

        pCentral = new JPanel(new GridLayout(0,1,10,6));
        pCentral.setBackground(Colour.getPrimaryColor());
        JScrollPane scroll = Component.createScrollPane(pCentral);

        add(scroll,BorderLayout.CENTER);

        // ----- END OF TEST AREA XD

        SimplePanel leftSide = new SimplePanel(Colour.getPrimaryColor());
        btAdd = new JButton();// Language.loadMessage("m_add")
        btAdd.setIcon(iconAdd);
        btAdd.setBackground(Colour.getButtonColor());
        btAdd.setForeground(Colour.getFontColor());
        btAdd.setFont(Typeface.labelPlain);
        leftSide.add(btAdd);
        btHelp = new JButton();// Language.loadMessage("m_help")
        btHelp.setIcon(iconHelp);
        btHelp.setBackground(Colour.getButtonColor());
        btHelp.setForeground(Colour.getFontColor());
        btHelp.setFont(Typeface.labelPlain);
        leftSide.add(btHelp);

        add(leftSide,BorderLayout.WEST);
        
        btConfig = new JButton(Language.loadMessage("m_config"));
        btAbout = new JButton(Language.loadMessage("m_about"));
        
        add(Component.createGeneralOptions(new JButton[]{btConfig,btAbout},Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }

    public void addToCenter(JComponent component){
        pCentral.add(component);
    }

    public void removeFromCenter(JComponent component){
        pCentral.remove(component);
    }

    public void addPlaceHolder(){
        pCentral.add(pNothing);
    }

    public void removePlaceHolder(){
        pCentral.remove(pNothing);
    }
}

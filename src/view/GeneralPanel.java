package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.Advice;
import util.Colour;
import util.Component;
import util.Language;
import util.Path;
import util.SimplePanel;
import util.Typeface;

@SuppressWarnings("serial")
public class GeneralPanel extends JPanel{
    public ImageIcon iconAdd, iconView, iconEdit, iconRemove;
    public JButton btConfig, btAdd, btAbout;
    public JLabel lbUser;
    public SimplePanel pGames;

    public GeneralPanel(){
        setLayout(new BorderLayout());
        setBackground(Colour.getBackgroundColor());
        initIcons();
        initComponents();
    }

    public class GameRegisterPanel{

    }

    public void initIcons(){
        try {
            float brightness = 0.035f*Colour.getLuminance(Colour.getBackgroundColor());
            iconAdd = Component.colorAndShadowIcon(new ImageIcon(ImageIO.read(new File(Path.images+"add.png"))),Colour.getFontColor(),brightness);
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

        pGames = new SimplePanel();
        JScrollPane scroll = Component.createScrollPane(pGames);

        // ----- TEST AREA

        pGames.add(Component.createPlainText("This is not finished yet... but hey! Look at these icons! They are cool, aren't they?", Colour.getPrimaryColor()));
        JLabel label1 = new JLabel(iconAdd);
        JLabel label2 = new JLabel(iconView);
        JLabel label3 = new JLabel(iconEdit);
        JLabel label4 = new JLabel(iconRemove);

        JPanel icons = new JPanel(new FlowLayout());
        icons.setBackground(this.getBackground());
        icons.add(label1);
        icons.add(label2);
        icons.add(label3);
        icons.add(label4);
        pGames.add(icons);

        // ----- END OF TEST AREA XD

        add(scroll,BorderLayout.CENTER);

        btAdd = new JButton(Language.loadMessage("m_add"));
        btConfig = new JButton(Language.loadMessage("m_config"));
        btAbout = new JButton(Language.loadMessage("m_about"));
        add(Component.createGeneralOptions(new JButton[]{btAdd,btConfig,btAbout},Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }

    public void addGameRegister(){

    }
}

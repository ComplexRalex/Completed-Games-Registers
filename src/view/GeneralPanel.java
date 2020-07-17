package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import util.Colour;
import util.Component;
import util.Image;
import util.Language;
import util.SimplePanel;
import util.Typeface;

@SuppressWarnings("serial")
public class GeneralPanel extends JPanel{
    private JPanel pCentral;
    private ImageIcon iconAdd, iconBackup, iconExport, iconHelp, iconView, iconEdit, iconRemove;
    public JButton btAdd, btBackup, btExport, btHelp, btConfig, btAbout;
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

            btView = new JButton();
            styleIconButton(btView, iconView);

            btEdit = new JButton(iconEdit);
            styleIconButton(btEdit, iconEdit);

            btRemove = new JButton(iconRemove);
            styleIconButton(btRemove, iconRemove);

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
        float brightness = 0.035f*Colour.getLuminance(Colour.getBackgroundColor());
        iconAdd = new ImageIcon(Image.colorAndShadow(Image.getAdd(),Colour.getFontColor(),brightness));
        iconBackup = new ImageIcon(Image.colorAndShadow(Image.getBackup(),Colour.getFontColor(),brightness));
        iconExport = new ImageIcon(Image.colorAndShadow(Image.getExport(),Colour.getFontColor(),brightness));
        iconHelp = new ImageIcon(Image.colorAndShadow(Image.getHelp(),Colour.getFontColor(),brightness));
        iconView = new ImageIcon(Image.colorAndShadow(Image.getView(),Colour.getFontColor(),brightness));
        iconEdit = new ImageIcon(Image.colorAndShadow(Image.getEdit(),Colour.getFontColor(),brightness));
        iconRemove = new ImageIcon(Image.colorAndShadow(Image.getRemove(),Colour.getFontColor(),brightness));
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

        pCentral = new JPanel(new GridLayout(0,1,10,6));
        pCentral.setBackground(Colour.getPrimaryColor());
        JScrollPane scroll = Component.createScrollPane(pCentral);

        add(scroll,BorderLayout.CENTER);

        SimplePanel leftSide = new SimplePanel(Colour.getPrimaryColor());
        btAdd = new JButton();
        styleIconButton(btAdd, iconAdd);
        leftSide.add(btAdd);

        btBackup = new JButton();
        styleIconButton(btBackup, iconBackup);
        leftSide.add(btBackup);

        btExport = new JButton();
        styleIconButton(btExport, iconExport);
        leftSide.add(btExport);

        btHelp = new JButton();
        styleIconButton(btHelp, iconHelp);
        leftSide.add(btHelp);

        add(leftSide,BorderLayout.WEST);
        
        btConfig = new JButton(Language.loadMessage("m_config"));
        btAbout = new JButton(Language.loadMessage("m_about"));
        
        add(Component.createGeneralOptions(new JButton[]{btConfig,btAbout},Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }

    private void styleIconButton(JButton button, ImageIcon icon){
        button.setIcon(icon);
        button.setBackground(Colour.getButtonColor());
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

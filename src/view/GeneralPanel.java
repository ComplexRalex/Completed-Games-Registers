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
import util.ImageResource;
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
    
    private boolean loadedIcons;

    public GeneralPanel(){
        setLayout(new BorderLayout());
        setBackground(Colour.getBackgroundColor());

        pNothing = new FirstTimePanel();

        initIcons();
        initComponents();
    }

    public class GameRegisterPanel extends JPanel{
        public JButton btView, btEdit, btRemove, btRecent;
        public JTextArea aName;

        public GameRegisterPanel(String game, boolean recent){
            setLayout(new BorderLayout());
            setBackground(Colour.getBackgroundColor());
            initComponents(game, recent);
        }

        public void initComponents(String game, boolean recent){

            JPanel title = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
            title.setBackground(this.getBackground());

            aName = new JTextArea(game);
            aName.setBackground(this.getBackground());
            aName.setForeground(Colour.getFontColor());
            aName.setLineWrap(true);
            aName.setWrapStyleWord(true);
            aName.setEditable(false);
            aName.setFont(Typeface.labelBold);
            aName.setColumns(18);
            title.add(aName);

            add(title,BorderLayout.WEST);

            JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
            rightSide.setBackground(this.getBackground());

            SimplePanel buttons = new SimplePanel(this.getBackground());

            JPanel options = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
            options.setBackground(this.getBackground());
            
            if(loadedIcons){
            	btView = new JButton(iconView);
            	btEdit = new JButton(iconEdit);
            	btRemove = new JButton(iconRemove);
            }else{
            	btView = new JButton(Language.loadMessage("m_option_view"));
            	btView.setForeground(Colour.getFontColor());
            	btView.setFont(Typeface.buttonBold);
        		btEdit = new JButton(Language.loadMessage("m_option_edit"));
            	btEdit.setForeground(Colour.getFontColor());
            	btEdit.setFont(Typeface.buttonBold);
        		btRemove = new JButton(Language.loadMessage("m_option_remove"));
            	btRemove.setForeground(Colour.getFontColor());
            	btRemove.setFont(Typeface.buttonBold);
        	}
            btView.setBackground(Colour.getButtonColor());
            btEdit.setBackground(Colour.getButtonColor());
            btRemove.setBackground(Colour.getButtonColor());

            options.add(btView);
            options.add(btEdit);
            options.add(btRemove);

            buttons.add(options);

            if(recent){
                JPanel pRecent = new JPanel(new FlowLayout(FlowLayout.CENTER,0,2));
                pRecent.setBackground(this.getBackground());
    
                btRecent = new JButton(Language.loadMessage("m_option_recent"));
                btRecent.setBackground(Colour.getButtonColor());
                btRecent.setForeground(Colour.getFontColor());
                btRecent.setFont(Typeface.buttonItalic);
    
                pRecent.add(btRecent);

                buttons.add(pRecent);
            }

            rightSide.add(buttons);

            add(rightSide,BorderLayout.EAST);
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
    	try{
	    	ImageResource res = new ImageResource();
	        float brightness = 0.035f*Colour.getLuminance(Colour.getBackgroundColor());
	        iconAdd = new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.ADD),Colour.getFontColor(),brightness));
	        iconBackup = new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.BACKUP),Colour.getFontColor(),brightness));
	        iconExport = new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.EXPORT),Colour.getFontColor(),brightness));
	        iconHelp = new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.HELP),Colour.getFontColor(),brightness));
	        iconView = new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.VIEW),Colour.getFontColor(),brightness));
	        iconEdit = new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.EDIT),Colour.getFontColor(),brightness));
	        iconRemove = new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.REMOVE),Colour.getFontColor(),brightness));
	        loadedIcons = true;
    	}catch(IllegalArgumentException | NullPointerException e){
            loadedIcons = false;
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

        pCentral = new JPanel(new GridLayout(0,1,10,6));
        pCentral.setBackground(Colour.getPrimaryColor());
        JScrollPane scroll = Component.createScrollPane(pCentral);

        add(scroll,BorderLayout.CENTER);

        SimplePanel leftSide = new SimplePanel(Colour.getPrimaryColor());
        
        if(loadedIcons){
        	btAdd = new JButton(iconAdd);
        	btBackup = new JButton(iconBackup);
        	btExport = new JButton(iconExport);
        	btHelp = new JButton(iconHelp);
        }else{
        	btAdd = new JButton(Language.loadMessage("m_option_add"));
        	btAdd.setForeground(Colour.getFontColor());
        	btAdd.setFont(Typeface.buttonBold);
        	btBackup = new JButton(Language.loadMessage("m_option_backup"));
        	btBackup.setForeground(Colour.getFontColor());
        	btBackup.setFont(Typeface.buttonBold);
        	btExport = new JButton(Language.loadMessage("m_option_export"));
        	btExport.setForeground(Colour.getFontColor());
        	btExport.setFont(Typeface.buttonBold);
        	btHelp = new JButton(Language.loadMessage("m_option_help"));
        	btHelp.setForeground(Colour.getFontColor());
        	btHelp.setFont(Typeface.buttonBold);
    	}
        btAdd.setBackground(Colour.getButtonColor());
        btBackup.setBackground(Colour.getButtonColor());
        btExport.setBackground(Colour.getButtonColor());
        btHelp.setBackground(Colour.getButtonColor());
        
        leftSide.add(btAdd);
        leftSide.add(btBackup);
        leftSide.add(btExport);
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

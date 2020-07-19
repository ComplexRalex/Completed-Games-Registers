package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import util.Colour;
import util.Component;
import util.ImageResource;
import util.Language;
import util.SimplePanel;
import util.Typeface;

@SuppressWarnings("serial")
public class HelpPanel extends JPanel{
    public JButton btReportIssue, btReturn;

    public class IconDescription extends JPanel{
        
        public IconDescription(ImageIcon icon, String description, Color bg){
            setBackground(bg);
            setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

            JLabel lbIcon = new JLabel(icon);
            lbIcon.setBorder(BorderFactory.createEmptyBorder(13,13,13,0));

            add(lbIcon);
            add(Component.createPlainText(description, Typeface.labelPlain, bg));

        }
        
        public IconDescription(String text, String description, Color bg){
        	setBackground(bg);
            setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

            JLabel lbIcon = new JLabel(text);
            lbIcon.setForeground(Colour.getFontColor());
            lbIcon.setFont(Typeface.buttonBold);
            lbIcon.setBorder(BorderFactory.createEmptyBorder(13,13,13,0));

            add(lbIcon);
            add(Component.createPlainText(description, Typeface.labelPlain, bg));
        }
    }

    public HelpPanel(){
        this.setLayout(new BorderLayout());
		this.setBackground(Colour.getBackgroundColor());
		initComponents();
    }

    public void initComponents(){
    	ImageResource res = new ImageResource();
        
        float brightness = 0.035f*Colour.getLuminance(Colour.getBackgroundColor());

        SimplePanel panel = new SimplePanel();
        JScrollPane scroll = Component.createScrollPane(panel);
        
        add(Component.createTitle(Language.loadMessage("h_title"), Colour.getPrimaryColor()),BorderLayout.NORTH);

        panel.add(Component.createSubtitle(Language.loadMessage("h_what_is"), Colour.getPrimaryColor()));
        panel.add(Component.createPlainText(Language.loadMessage("h_what_is_text"), Typeface.labelPlain, Colour.getBackgroundColor()));

        panel.add(Component.createSubtitle(Language.loadMessage("h_register"), Colour.getPrimaryColor()));
        panel.add(Component.createPlainText(Language.loadMessage("h_register_text"), Typeface.labelPlain, Colour.getBackgroundColor()));

        panel.add(Component.createSubtitle(Language.loadMessage("h_options"), Colour.getPrimaryColor()));
        try{
	        panel.add(new IconDescription(new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.ADD),Colour.getFontColor(),brightness)), Language.loadMessage("h_options_add"), Colour.getBackgroundColor()));
	        panel.add(new IconDescription(new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.BACKUP),Colour.getFontColor(),brightness)), Language.loadMessage("h_options_backup"), Colour.getBackgroundColor()));
	        panel.add(new IconDescription(new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.EXPORT),Colour.getFontColor(),brightness)), Language.loadMessage("h_options_export"), Colour.getBackgroundColor()));
	        panel.add(new IconDescription(new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.HELP),Colour.getFontColor(),brightness)), Language.loadMessage("h_options_help"), Colour.getBackgroundColor()));
	        panel.add(new IconDescription(new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.VIEW),Colour.getFontColor(),brightness)), Language.loadMessage("h_options_view"), Colour.getBackgroundColor()));
	        panel.add(new IconDescription(new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.EDIT),Colour.getFontColor(),brightness)), Language.loadMessage("h_options_edit"), Colour.getBackgroundColor()));
	        panel.add(new IconDescription(new ImageIcon(ImageResource.colorAndShadow(res.resource(ImageResource.REMOVE),Colour.getFontColor(),brightness)), Language.loadMessage("h_options_remove"), Colour.getBackgroundColor()));
        }catch(IllegalArgumentException | NullPointerException e){
        	 panel.add(new IconDescription(Language.loadMessage("m_option_add"), Language.loadMessage("h_options_add"), Colour.getBackgroundColor()));
        	 panel.add(new IconDescription(Language.loadMessage("m_option_backup"), Language.loadMessage("h_options_backup"), Colour.getBackgroundColor()));
        	 panel.add(new IconDescription(Language.loadMessage("m_option_export"), Language.loadMessage("h_options_export"), Colour.getBackgroundColor()));
        	 panel.add(new IconDescription(Language.loadMessage("m_option_help"), Language.loadMessage("h_options_help"), Colour.getBackgroundColor()));
        	 panel.add(new IconDescription(Language.loadMessage("m_option_view"), Language.loadMessage("h_options_view"), Colour.getBackgroundColor()));
        	 panel.add(new IconDescription(Language.loadMessage("m_option_edit"), Language.loadMessage("h_options_edit"), Colour.getBackgroundColor()));
        	 panel.add(new IconDescription(Language.loadMessage("m_option_remove"), Language.loadMessage("h_options_remove"), Colour.getBackgroundColor()));
        }

        panel.add(Component.createSubtitle(Language.loadMessage("h_report_issue"), Colour.getPrimaryColor()));
        btReportIssue = new JButton(Language.loadMessage("h_report_issue_text"));
        panel.add(Component.createSingleButton(btReportIssue, Colour.getBackgroundColor()));

        add(scroll,BorderLayout.CENTER);
        
        btReturn = new JButton(Language.loadMessage("g_return"));
        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()),BorderLayout.SOUTH);

    }

}
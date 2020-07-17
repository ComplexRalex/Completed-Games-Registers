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
import util.Image;
import util.Language;
import util.SimplePanel;
import util.Typeface;

@SuppressWarnings("serial")
public class HelpPanel extends JPanel{
    public JButton btReturn;

    public class IconDescription extends JPanel{
        
        public IconDescription(ImageIcon icon, String description, Color bg){
            setBackground(bg);
            setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

            JLabel lbIcon = new JLabel(icon);
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
        
        float brightness = 0.035f*Colour.getLuminance(Colour.getBackgroundColor());

        SimplePanel panel = new SimplePanel();
        JScrollPane scroll = Component.createScrollPane(panel);
        
        add(Component.createTitle(Language.loadMessage("h_title"), Colour.getPrimaryColor()),BorderLayout.NORTH);

        panel.add(Component.createSubtitle(Language.loadMessage("h_what_is"), Colour.getPrimaryColor()));
        panel.add(Component.createPlainText(Language.loadMessage("h_what_is_text"), Typeface.labelPlain, Colour.getBackgroundColor()));

        panel.add(Component.createSubtitle(Language.loadMessage("h_register"), Colour.getPrimaryColor()));
        panel.add(Component.createPlainText(Language.loadMessage("h_register_text"), Typeface.labelPlain, Colour.getBackgroundColor()));

        panel.add(Component.createSubtitle(Language.loadMessage("h_options"), Colour.getPrimaryColor()));
        panel.add(new IconDescription(new ImageIcon(Image.colorAndShadow(Image.getAdd(), Colour.getFontColor(), brightness)), Language.loadMessage("h_options_add"), Colour.getBackgroundColor()));
        panel.add(new IconDescription(new ImageIcon(Image.colorAndShadow(Image.getBackup(), Colour.getFontColor(), brightness)), Language.loadMessage("h_options_backup"), Colour.getBackgroundColor()));
        panel.add(new IconDescription(new ImageIcon(Image.colorAndShadow(Image.getExport(), Colour.getFontColor(), brightness)), Language.loadMessage("h_options_export"), Colour.getBackgroundColor()));
        panel.add(new IconDescription(new ImageIcon(Image.colorAndShadow(Image.getHelp(), Colour.getFontColor(), brightness)), Language.loadMessage("h_options_help"), Colour.getBackgroundColor()));
        panel.add(new IconDescription(new ImageIcon(Image.colorAndShadow(Image.getView(), Colour.getFontColor(), brightness)), Language.loadMessage("h_options_view"), Colour.getBackgroundColor()));
        panel.add(new IconDescription(new ImageIcon(Image.colorAndShadow(Image.getEdit(), Colour.getFontColor(), brightness)), Language.loadMessage("h_options_edit"), Colour.getBackgroundColor()));
        panel.add(new IconDescription(new ImageIcon(Image.colorAndShadow(Image.getRemove(), Colour.getFontColor(), brightness)), Language.loadMessage("h_options_remove"), Colour.getBackgroundColor()));
        
        add(scroll,BorderLayout.CENTER);
        
        btReturn = new JButton(Language.loadMessage("g_return"));
        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()),BorderLayout.SOUTH);

    }

}
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

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
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

/**
 * <h3>HelpPanel view class.</h3>
 * This class extends from JPanel and is used to show
 * questions and answers about something that is not
 * totally clear about this program. Also, it includes
 * a {@link #btReportIssue} if the user thinks that
 * there is a bug and want to report it.
 * 
 * @author Alejandro Batres
 * @see HelpController
 */
@SuppressWarnings("serial")
public class HelpPanel extends JPanel{

    /**
	 * Buttons used to different actions.
	 */
    public JButton btReportIssue, btReturn;

    /**
     * Scrollbar used in the {@link JScrollPane} of the central
     * pane.
     */
    public JScrollBar scrollBar;

    /**
     * <h3>IconDescription view class.</h3>
     * <h4>(from HelpPanel)</h4>
     * This class extends from JPanel and is used to create a
     * panel that displays an icon with a JLabel including
     * its description and usage.
     * 
     * @author Alejandro Batres
     * @see Component#createPlainText(String, java.awt.Font, boolean, Color)
     */
    public class IconDescription extends JPanel{
        
        /**
         * Constructor of the IconDescription class.
         * 
         * @param icon ImageIcon that will be included
         * @param description String which contains the description of
         * the given icon
         * @param bg Background color of the JLabel containing the
         * description
         */
        public IconDescription(ImageIcon icon, String description, Color bg){
            setBackground(bg);
            setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

            JLabel lbIcon = new JLabel(icon);
            lbIcon.setBorder(BorderFactory.createEmptyBorder(13,13,13,0));

            add(lbIcon);
            add(Component.createPlainText(description, Typeface.labelPlain, false, bg));
        }
        
        /**
         * Constructor of the IconDescription class. Ironically,
         * this constructor DOES NOT receive an icon. This
         * constructor needs a String instead.
         * 
         * @param text String describing the action of the icon.
         * @param description String which contains the description of
         * the given icon
         * @param bg Background color of the JLabel containing the
         * description
         */
        public IconDescription(String text, String description, Color bg){
        	setBackground(bg);
            setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

            JLabel lbIcon = new JLabel(text);
            lbIcon.setForeground(Colour.getFontColor());
            lbIcon.setFont(Typeface.buttonBold);
            lbIcon.setBorder(BorderFactory.createEmptyBorder(13,13,13,0));

            add(lbIcon);
            add(Component.createPlainText(description, Typeface.labelPlain, false, bg));
        }
    }

    /**
     * Constructor of the HelpPanel class. This will set
     * the layout manager of the current panel, its
     * background color and finally initialize all
	 * of its components.
     * 
     * @see #initComponents()
     */
    public HelpPanel(){
        this.setLayout(new BorderLayout());
		this.setBackground(Colour.getBackgroundColor());
		initComponents();
    }

    /**
	 * Initializes every visual component inside of this panel.
	 */
    public void initComponents(){

        // Establishing useful variables

    	ImageResource res = new ImageResource();
        float brightness = 0.035f*Colour.getLuminance(Colour.getBackgroundColor());

        // Establishing centered panel

        SimplePanel panel = new SimplePanel();
        JScrollPane scroll = Component.createScrollPane(panel);
        scrollBar = scroll.getVerticalScrollBar();

        // Establishing title
        
        add(Component.createTitle(Language.loadMessage("h_title"), Colour.getPrimaryColor()),BorderLayout.NORTH);

        // - Establishing "what is this?" stuff
        
        panel.add(Component.createSubtitle(Language.loadMessage("h_what_is"), Colour.getPrimaryColor()));
        panel.add(Component.createPlainText(Language.loadMessage("h_what_is_text"), Typeface.labelPlain, false, Colour.getBackgroundColor()));

        // - Establishing "what is a completed-game register?" stuff

        panel.add(Component.createSubtitle(Language.loadMessage("h_register"), Colour.getPrimaryColor()));
        panel.add(Component.createPlainText(Language.loadMessage("h_register_text"), Typeface.labelPlain, false, Colour.getBackgroundColor()));

        // - Establishing "what's that text field from above" stuff

        panel.add(Component.createSubtitle(Language.loadMessage("h_search_bar"), Colour.getPrimaryColor()));
        panel.add(Component.createPlainText(Language.loadMessage("h_search_bar_text"), Typeface.labelPlain, false, Colour.getBackgroundColor()));

        // - Establishing explanation of every icon

        panel.add(Component.createSubtitle(Language.loadMessage("h_options"), Colour.getPrimaryColor()));
        try{
	        panel.add(new IconDescription(
                new ImageIcon(ImageResource.colorAndShadow(
                    res.resource(ImageResource.ADD),
                    Colour.getFontColor(),
                    brightness
                )),
                Language.loadMessage("h_options_add"),
                Colour.getBackgroundColor()
            ));
	        panel.add(new IconDescription(
                new ImageIcon(ImageResource.colorAndShadow(
                    res.resource(ImageResource.BACKUP),
                    Colour.getFontColor(),
                    brightness
                )),
                Language.loadMessage("h_options_backup"),
                Colour.getBackgroundColor()
            ));
	        panel.add(new IconDescription(
                new ImageIcon(ImageResource.colorAndShadow(
                    res.resource(ImageResource.EXPORT),
                    Colour.getFontColor(),
                    brightness
                )),
                Language.loadMessage("h_options_export"),
                Colour.getBackgroundColor()
            ));
	        panel.add(new IconDescription(
                new ImageIcon(ImageResource.colorAndShadow(
                    res.resource(ImageResource.HELP),
                    Colour.getFontColor(),
                    brightness
                )),
                Language.loadMessage("h_options_help"),
                Colour.getBackgroundColor()
            ));
	        panel.add(new IconDescription(
                new ImageIcon(ImageResource.colorAndShadow(
                    res.resource(ImageResource.VIEW),
                    Colour.getFontColor(),
                    brightness
                )),
                Language.loadMessage("h_options_view"),
                Colour.getBackgroundColor()
            ));
	        panel.add(new IconDescription(
                new ImageIcon(ImageResource.colorAndShadow(
                    res.resource(ImageResource.EDIT),
                    Colour.getFontColor(),
                    brightness
                )),
                Language.loadMessage("h_options_edit"),
                Colour.getBackgroundColor()
            ));
	        panel.add(new IconDescription(
                new ImageIcon(ImageResource.colorAndShadow(
                    res.resource(ImageResource.REMOVE),
                    Colour.getFontColor(),brightness
                )),
                Language.loadMessage("h_options_remove"),
                Colour.getBackgroundColor()
            ));
        }catch(IllegalArgumentException | NullPointerException e){
            panel.add(new IconDescription(
                Language.loadMessage("m_option_add"),
                Language.loadMessage("h_options_add"),
                Colour.getBackgroundColor()
            ));
            panel.add(new IconDescription(
                Language.loadMessage("m_option_backup"),
                Language.loadMessage("h_options_backup"),
                Colour.getBackgroundColor()
            ));
            panel.add(new IconDescription(
                Language.loadMessage("m_option_export"),
                Language.loadMessage("h_options_export"),
                Colour.getBackgroundColor()
            ));
            panel.add(new IconDescription(
                Language.loadMessage("m_option_help"),
                Language.loadMessage("h_options_help"),
                Colour.getBackgroundColor()
            ));
            panel.add(new IconDescription(
                Language.loadMessage("m_option_view"),
                Language.loadMessage("h_options_view"),
                Colour.getBackgroundColor()
            ));
            panel.add(new IconDescription(
                Language.loadMessage("m_option_edit"),
                Language.loadMessage("h_options_edit"),
                Colour.getBackgroundColor()
            ));
            panel.add(new IconDescription(
                Language.loadMessage("m_option_remove"),
                Language.loadMessage("h_options_remove"),
                Colour.getBackgroundColor()
            ));
        }

        // - Establishing "report issue" stuff

        panel.add(Component.createSubtitle(Language.loadMessage("h_report_issue"), Colour.getPrimaryColor()));
        btReportIssue = new JButton(Language.loadMessage("h_report_issue_text"));
        panel.add(Component.createSingleButton(btReportIssue, Colour.getBackgroundColor()));

        add(scroll,BorderLayout.CENTER);
        
        // Establishing general options

        btReturn = new JButton(Language.loadMessage("g_return"));
        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }
}

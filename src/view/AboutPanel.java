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

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import system.Software;
import util.Colour;
import util.Component;
import util.ImageResource;
import util.Language;
import util.SimplePanel;
import util.Typeface;

import javax.swing.JButton;

/**
 * <h3>AboutPanel view class.</h3>
 * This class extends from JPanel and is used to display
 * everything related to the developers, used libraries,
 * used APIs, contributors, etc. in the program.
 * 
 * @author Alejandro Batres
 * @see AboutController
 */
@SuppressWarnings("serial")
public class AboutPanel extends JPanel{

    /**
     * Panels that display licenses/terms of use.
     */
    public LicenseTermsPanel pLicense, pRAWGTerms, pJsonJavaLicense;

    /**
     * Buttons used to different actions.
     */
    public JButton btTwitter, btSource, btAPI, btJSONSource, btReturn;

    /**
     * Scrollbar used in the {@link JScrollPane} of the central
     * pane.
     */
    public JScrollBar scrollBar;

    /**
     * <h3>LicenseTermsPanel view class.</h3>
     * <h4>(from AboutPanel)</h4>
     * This class extends from {@link SimplePanel} and is used to
     * create a panel that needs to display a license or the
     * terms of use.
     * <p>
     * Summarizing, this panel will include the following
     * components:
     * <ul>
     * <li>A <b>JButton</b> that will allow the user to show/hide
     * the license/terms of use,
     * <li>and a <b>JTextArea</b> that will contain such
     * license/terms of use.
     * 
     * @author Alejandro Batres
     */
    public class LicenseTermsPanel extends SimplePanel{

        /**
         * Panel containing the {@link #aLicenseTerms} component.
         * 
         * @see #aLicenseTerms
         */
        private JPanel pArea;

        /**
         * Button used to show/hide the {@link #pArea} panel.
         */
        public JButton btShowHide;

        /**
         * Text area that contains the license/terms of use.
         */
        public JTextArea aLicenseTerms;

        /**
         * Name of the product, library, API, etc.
         */
        public String name;

        /**
         * Boolean which determines if the content of
         * {@link #aLicenseTerms} is a license ({@code true})
         * or not ({@code false}). This variable is used to
         * customize the text of the {@link #btShowHide} button.
         */
        public boolean license;

        /**
         * Constructor of the LicenseTermsPanel class.
         * 
         * @param name String that will be displayed on the button.
         * @param text String which contains the license/terms of use
         * @param rows Number of rows of {@link #aLicenseTerms}
         * @param license {@code true} if it's a license (so, the
         * {@link #btShowHide} will display "show/hide <b>license</b>").
         * Otherwise {@code false} (so, the {@link #btShowHide} will
         * display "show/hide <b>terms of use</b>")
         */
        public LicenseTermsPanel(String name, String text, int rows, boolean license){
            this.name = name;
            this.license = license;

            btShowHide = new JButton();
            add(Component.createSingleButton(btShowHide, this.getBackground()));

            aLicenseTerms = new JTextArea(text);
            pArea = Component.createTextArea(null, aLicenseTerms, rows, Colour.getBackgroundColor());
            add(pArea);

            show(false);
        }

        /**
         * Determines if the component {@link #aLicenseTerms}
         * will be displayed.
         * 
         * @param flag Boolean which determines if {@link #aLicenseTerms}
         * will be displayed ({@code true}) or not ({@code false})
         */
        public void show(boolean flag){
            pArea.setVisible(flag);
            if(license)
                btShowHide.setText(name+" ("+(flag ? Language.loadMessage("ac_license_hide"): Language.loadMessage("ac_license_show"))+")");
            else
                btShowHide.setText(name+" ("+(flag ? Language.loadMessage("ac_terms_hide"): Language.loadMessage("ac_terms_show"))+")");
        }

        /**
         * Returns if {@link #aLicenseTerms} is being shown.
         * 
         * @return Returns {@code true} if the license/terms
         * of use is being shown or {@code false} otherwise.
         */
        public boolean visible(){
            return pArea.isVisible();
        }
    }

    /**
     * Constructor of the AboutPanel class. This will set the
     * layout manager of the current panel, its background
     * color and finally initialize all of its components.
     * 
     * @see #initComponents()
     */
    public AboutPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Colour.getBackgroundColor());
        initComponents();
    }

    /**
	 * Initializes every visual component inside of this panel.
	 */
    public void initComponents(){

        // Establishing title

        add(Component.createTitle(Language.loadMessage("ac_title"), Colour.getPrimaryColor()), BorderLayout.NORTH);

        // Establishing centered panel

        SimplePanel centeredPanel = new SimplePanel();
        JScrollPane scroll = Component.createScrollPane(centeredPanel);
        scrollBar = scroll.getVerticalScrollBar();

        // - Establishing logo

        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        try{
            centeredPanel.add(Component.createImage((new ImageResource()).resource(ImageResource.LOGOTYPE), Colour.getBackgroundColor()));
		}catch(IllegalArgumentException | NullPointerException e){
            centeredPanel.add(Component.createTitle(Software.NAME, Colour.getBackgroundColor()));
        }
        
        // - Establishing brief description
        
        centeredPanel.add(Component.createPlainText(Language.loadMessage("ac_brief"), Typeface.labelPlain, true, Colour.getBackgroundColor()));
        
        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // - Establishing "details" subtitle

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_details"), Colour.getPrimaryColor()));

        // - Establishing "details" stuff

        centeredPanel.add(createField(Language.loadMessage("ac_version"),Software.VERSION));
        centeredPanel.add(createField(Language.loadMessage("ac_written_in"),"Java"));
        centeredPanel.add(createField(Language.loadMessage("ac_java_version"),Software.JAVA));

        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // - Establishing "credits" subtitle

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_credits"), Colour.getPrimaryColor()));

        // - Establishing "credits" stuff

        centeredPanel.add(createField(Language.loadMessage("ac_developed"),Software.DEVELOPER+" / "+Software.DEVELOPER_USERNAMES[0]+" / "+Software.DEVELOPER_USERNAMES[1]));
        centeredPanel.add(createField(Language.loadMessage("ac_library"),Software.LIBRARY[0]+" (ver. "+Software.LIBRARY_DETAILS.get(Software.LIBRARY[0]).get("version")+")"));
        centeredPanel.add(createField(Language.loadMessage("ac_api"),Software.API[0]));
        
        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // - Establishing "links" subtitle

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_links"), Colour.getPrimaryColor()));

        // - Establishing "links" stuff

        btTwitter = new JButton(Software.DEVELOPER_USERNAMES[1]+" ("+Language.loadMessage("ac_twitter")+")");
        centeredPanel.add(Component.createSingleButton(btTwitter, Colour.getBackgroundColor()));

        btSource = new JButton(Software.NAME+" ("+Language.loadMessage("ac_source_code")+" (GitHub))");
        centeredPanel.add(Component.createSingleButton(btSource, Colour.getBackgroundColor()));

        btJSONSource = new JButton(Software.LIBRARY[0]+" ("+Language.loadMessage("ac_source_code")+" (GitHub))");
        centeredPanel.add(Component.createSingleButton(btJSONSource, Colour.getBackgroundColor()));

        btAPI = new JButton(Software.API[0]+" ("+Language.loadMessage("ac_website")+")");
        centeredPanel.add(Component.createSingleButton(btAPI, Colour.getBackgroundColor()));
        
        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // - Establlishing "licenses and terms of use" subtitle 

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_licenses_terms"), Colour.getPrimaryColor()));

        // - Establlishing "licenses and terms of use" stuff

        pLicense = new LicenseTermsPanel(Software.NAME, Software.LICENSE, 19, true);
        centeredPanel.add(pLicense);

        pRAWGTerms = new LicenseTermsPanel(Software.API[0], Software.API_DETAILS.get(Software.API[0]).get("terms_of_use"), 14, false);
        centeredPanel.add(pRAWGTerms);

        pJsonJavaLicense = new LicenseTermsPanel(Software.LIBRARY[0], Software.LIBRARY_DETAILS.get(Software.LIBRARY[0]).get("license"), 23, true);
        centeredPanel.add(pJsonJavaLicense);

        centeredPanel.add(Component.createGap(35, Colour.getBackgroundColor()));

        // - Establishing ...

        centeredPanel.add(createField("En memoria de", Software.MEMORY_OF_LIFE));

        centeredPanel.add(Component.createGap(35, Colour.getBackgroundColor()));

        add(scroll, BorderLayout.CENTER);

        // Establishing return button

        btReturn = new JButton(Language.loadMessage("g_return"));

        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()), BorderLayout.SOUTH);
    }

    /**
     * Creates an area where will be displayed one of the
     * credits of the panel.
     * <p>
     * At the top, the panel contains a <b>JLabel</b>
     * with the given <i>title</i>, and below there
     * is a <b>JLabel</b> displaying the respective
     * <i>name</i> of the contributor, related to the
     * <i>title</i>.
     * 
     * @param title String which contains the contribution
     * @param name String which contains the author of such
     * contribution
     * @return <b>JPanel</b> containing a credit.
     */
    public JPanel createField(String title, String name){
        return createField(title, new String[]{name});
    }

    /**
     * Creates an area where will be displayed one of the
     * credits of the panel.
     * <p>
     * At the top, the panel contains a <b>JLabel</b>
     * with the given <i>title</i>, and below there
     * is a <b>JLabel</b> displaying the respective
     * <i>names</i> of all the contributors, related
     * to the <i>title</i>.
     * 
     * @param title String which contains the contribution
     * @param name String which contains the authors of such
     * contribution
     * @return <b>JPanel</b> containing a credit.
     */
    public JPanel createField(String title, String[] names){

        SimplePanel panel = new SimplePanel();

        String textFormated = "<span style=\"font-weight: bold; font-size: "+Typeface.labelBold.getSize()+"\">"+title+"</span><br>";
        for(int i = 0; i < names.length; i++)
            textFormated += names[i]+(names.length - 1 == i ? "" : "<br>");
        panel.add(Component.createPlainText(textFormated, Typeface.textPlain, true, Colour.getBackgroundColor()));

        return panel;
    }
}

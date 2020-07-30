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

@SuppressWarnings("serial")
public class AboutPanel extends JPanel{
    public LicenseTermsPanel pLicense, pRAWGTerms, pJsonJavaLicense;
    public JButton btTwitter, btSource, btAPI, btJSONSource, btReturn;
    public JScrollBar scrollBar;

    public class LicenseTermsPanel extends SimplePanel{
        private JPanel pArea;
        public JButton btShowHide;
        public JTextArea aLicenseTerms;
        public String name;
        public boolean license;

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

        public void show(boolean flag){
            pArea.setVisible(flag);
            if(license)
                btShowHide.setText(name+" ("+(flag ? Language.loadMessage("ac_license_hide"): Language.loadMessage("ac_license_show"))+")");
            else
                btShowHide.setText(name+" ("+(flag ? Language.loadMessage("ac_terms_hide"): Language.loadMessage("ac_terms_show"))+")");

        }

        public boolean visible(){
            return pArea.isVisible();
        }

    }

    public AboutPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Colour.getBackgroundColor());
        initComponents();
    }

    public void initComponents(){

        // Establishing title

        add(Component.createTitle(Language.loadMessage("ac_title"), Colour.getPrimaryColor()), BorderLayout.NORTH);

        // Establishing centered panel

        SimplePanel centeredPanel = new SimplePanel();
        JScrollPane scroll = Component.createScrollPane(centeredPanel);
        scrollBar = scroll.getVerticalScrollBar();

        // Establishing logo
        try{
            centeredPanel.add(Component.createImage((new ImageResource()).resource(ImageResource.LOGOTYPE), Colour.getBackgroundColor()));
		}catch(IllegalArgumentException | NullPointerException e){
            centeredPanel.add(Component.createTitle(Software.NAME, Colour.getBackgroundColor()));
        }
        
        // Establishing brief description
        
        centeredPanel.add(Component.createPlainText(Language.loadMessage("ac_brief"), Typeface.labelPlain, true, Colour.getBackgroundColor()));
        
        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // Establishing "details" subtitle

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_details"), Colour.getPrimaryColor()));

        // Establishing "details" stuff

        centeredPanel.add(createField(Language.loadMessage("ac_version"),Software.VERSION));
        centeredPanel.add(createField(Language.loadMessage("ac_written_in"),"Java"));
        centeredPanel.add(createField(Language.loadMessage("ac_java_version"),Software.JAVA));

        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // Establishing "credits" subtitle

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_credits"), Colour.getPrimaryColor()));

        // Establishing "credits" stuff

        centeredPanel.add(createField(Language.loadMessage("ac_developed"),Software.DEVELOPER+" / "+Software.DEVELOPER_USERNAMES[0]+" / "+Software.DEVELOPER_USERNAMES[1]));
        centeredPanel.add(createField(Language.loadMessage("ac_library"),Software.LIBRARY[0]+" (ver. "+Software.LIBRARY_DETAILS.get(Software.LIBRARY[0]).get("version")+")"));
        centeredPanel.add(createField(Language.loadMessage("ac_api"),Software.API[0]));
        
        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // Establishing "links" subtitle

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_links"), Colour.getPrimaryColor()));

        // Establishing "links" stuff

        btTwitter = new JButton(Software.DEVELOPER_USERNAMES[1]+" ("+Language.loadMessage("ac_twitter")+")");
        centeredPanel.add(Component.createSingleButton(btTwitter, Colour.getBackgroundColor()));

        btSource = new JButton(Software.NAME+" ("+Language.loadMessage("ac_source_code")+" (GitHub))");
        centeredPanel.add(Component.createSingleButton(btSource, Colour.getBackgroundColor()));

        btJSONSource = new JButton(Software.LIBRARY[0]+" ("+Language.loadMessage("ac_source_code")+" (GitHub))");
        centeredPanel.add(Component.createSingleButton(btJSONSource, Colour.getBackgroundColor()));

        btAPI = new JButton(Software.API[0]+" ("+Language.loadMessage("ac_website")+")");
        centeredPanel.add(Component.createSingleButton(btAPI, Colour.getBackgroundColor()));
        
        centeredPanel.add(Component.createGap(25, Colour.getBackgroundColor()));

        // Establlishing "licenses and terms of use" subtitle 

        centeredPanel.add(Component.createSubtitle(Language.loadMessage("ac_licenses_terms"), Colour.getPrimaryColor()));

        // Establlishing "licenses and terms of use" stuff

        pLicense = new LicenseTermsPanel(Software.NAME, Software.LICENSE, 19, true);
        centeredPanel.add(pLicense);

        pRAWGTerms = new LicenseTermsPanel(Software.API[0], Software.API_DETAILS.get(Software.API[0]).get("terms_of_use"), 14, false);
        centeredPanel.add(pRAWGTerms);

        pJsonJavaLicense = new LicenseTermsPanel(Software.LIBRARY[0], Software.LIBRARY_DETAILS.get(Software.LIBRARY[0]).get("license"), 23, true);
        centeredPanel.add(pJsonJavaLicense);

        add(scroll, BorderLayout.CENTER);

        // Establishing return button

        btReturn = new JButton(Language.loadMessage("g_return"));

        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()), BorderLayout.SOUTH);
    }

    public JPanel createField(String title, String name){
        return createField(title, new String[]{name});
    }

    public JPanel createField(String title, String[] names){

        SimplePanel panel = new SimplePanel();

        String textFormated = "<span style=\"font-weight: bold; font-size: "+Typeface.labelBold.getSize()+"\">"+title+"</span><br>";
        for(int i = 0; i < names.length; i++)
            textFormated += names[i]+(names.length - 1 == i ? "" : "<br>");
        panel.add(Component.createPlainText(textFormated, Typeface.textPlain, true, Colour.getBackgroundColor()));

        return panel;
    }

}
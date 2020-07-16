package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.GameData;
import util.Colour;
import util.Component;
import util.Language;
import util.SimplePanel;
import util.Typeface;

@SuppressWarnings("serial")
public class ViewGamePanel extends JPanel{
    private JPanel pGameFields, pAreaSpoiler;
    public JPanel pYear, pComment, pNote;
    public SimplePanel pSpoiler;
    private GridBagConstraints c;
    public JTextField txtName, txtYear, txtRate;
    public JTextArea aComment, aNote, aSpoiler;
    public JButton btSpoiler, btReturn;
    public GameDataPanel pGameData;

    public class GameDataPanel extends SimplePanel{
        public JTextField txtName, txtDevelopers, txtPublishers, txtRelease, txtRating;
        public JTextArea aPlatforms, aGenres, aTags;
        public BufferedImage image;

        public GameDataPanel(GameData gd){
            initComponents(gd);
        }

        public void initComponents(GameData gd){

            // Establishing subtitle

            add(Component.createSubtitle(Language.loadMessage("gv_db_info"), Colour.getPrimaryColor()));

            // Establishing an image

            try{
                image = gd.getImage();
                add(Component.createImage(image, Colour.getBackgroundColor()));
            }catch(IOException e){}
        
            // Establishing game name

            txtName = new JTextField(gd.getName());
            txtName.setEditable(false);
            add(Component.createTextField(Language.loadMessage("gv_name"), txtName, true, Colour.getPrimaryColor()));
            
            // Establishing game description

            add(Component.createPlainText(gd.getDescription(), Typeface.textPlain, Colour.getPrimaryColor()));

            // Establishing game developers

            try{
                txtDevelopers = new JTextField(oneLineList(gd.getDevelopers()));
                txtDevelopers.setEditable(false);
                add(Component.createTextField(Language.loadMessage("gv_developers"), txtDevelopers, false, Colour.getBackgroundColor()));
            } catch(NullPointerException e) {}

            // Establishing game publishers

            try{
                txtPublishers = new JTextField(oneLineList(gd.getPublishers()));
                txtPublishers.setEditable(false);
                add(Component.createTextField(Language.loadMessage("gv_publishers"), txtPublishers, false, Colour.getBackgroundColor()));
            } catch(NullPointerException e) {}

            // Establishing game release date

            try{
                try {
                    SimpleDateFormat format = new SimpleDateFormat("MMMM dd',' yyyy");
                    txtRelease = new JTextField((format.format((new SimpleDateFormat("yyyy-MM-dd")).parse(gd.getReleaseDate()))));
                } catch (ParseException e) {
                    txtRelease = new JTextField(gd.getReleaseDate());
                }
                txtRelease.setEditable(false);
                add(Component.createTextField(Language.loadMessage("gv_release"), txtRelease, true, Colour.getBackgroundColor()));
            } catch(NullPointerException e) {}

            // Establishing game platforms

            try{
                aPlatforms = new JTextArea(oneLineList(gd.getPlatforms()));
                aPlatforms.setEditable(false);
                add(Component.createTextArea(Language.loadMessage("gv_platforms"), aPlatforms, 2, Colour.getBackgroundColor()));
            } catch(NullPointerException e) {}

            // Establishing game genres

            try{
                aGenres = new JTextArea(oneLineList(gd.getGenres()));
                aGenres.setEditable(false);
                add(Component.createTextArea(Language.loadMessage("gv_genres"), aGenres, 2, Colour.getBackgroundColor()));
            } catch(NullPointerException e) {}

            // Establishing game tags

            try{
                aTags = new JTextArea(oneLineList(gd.getTags()));
                aTags.setEditable(false);
                add(Component.createTextArea(Language.loadMessage("gv_tags"), aTags, 3, Colour.getBackgroundColor()));
            } catch(NullPointerException e) {}

            // Establishing game rating

            try{
                txtRating = new JTextField(Float.toString(gd.getRating()));
                txtRating.setEditable(false);
                add(Component.createTextField(Language.loadMessage("gv_rating"), txtRating, true, Colour.getBackgroundColor()));
            } catch(NullPointerException e) {}

            // Establishing gap

            add(Component.createGap(35,Colour.getBackgroundColor()));
        }

        private String oneLineList(String[] elements){
            if(elements.length == 0) throw new NullPointerException();
            String string = "";
            String[] array = elements;

            for(int i = 0; i < array.length; i++){
                if(i < array.length-1) string+=array[i]+", ";
                else string+=array[i]+".";
            }

            return string;
        }
    }

    public ViewGamePanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Colour.getBackgroundColor());
        initComponents();
    }

    public void initComponents(){

        // Establishing title

        add(Component.createTitle(Language.loadMessage("gv_title"), Colour.getPrimaryColor()),BorderLayout.NORTH);

        // Establishing panel of all options

        pGameFields = new JPanel();
        pGameFields.setLayout(new GridBagLayout());
        pGameFields.setBackground(Colour.getBackgroundColor());

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1; // is 1 on purpose!
        c.insets = Component.margin;

        JScrollPane scroll = Component.createScrollPane(pGameFields);

        // Establishing subtitle

        pGameFields.add(Component.createSubtitle(Language.loadMessage("ge_user_info"), Colour.getPrimaryColor()),c);
        c.gridy++;

        // Establishing game name

        txtName = new JTextField();
        txtName.setEditable(false);
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_name"), txtName, true, Colour.getBackgroundColor()),c);
        c.gridy++;

        // Establishing year of completion

        txtYear = new JTextField();
        txtYear.setEditable(false);
        pYear = Component.createTextField(Language.loadMessage("ge_year"), txtYear, true, Colour.getBackgroundColor());
        pGameFields.add(pYear,c);
        c.gridy++;

        // Establishing local user rating

        txtRate = new JTextField();
        txtRate.setEditable(false);
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_rating"), txtRate, true, Colour.getBackgroundColor()),c);
        c.gridy++;

        // Establishing comments

        aComment = new JTextArea();
        aComment.setEditable(false);
        pComment = Component.createTextArea(Language.loadMessage("ge_comment"), aComment, 3, Colour.getBackgroundColor());
        pGameFields.add(pComment,c);
        c.gridy++;

        // Establishing notes

        aNote = new JTextArea();
        aNote.setEditable(false);
        pNote = Component.createTextArea(Language.loadMessage("ge_note"), aNote, 2, Colour.getBackgroundColor());
        pGameFields.add(pNote,c);
        c.gridy++;

        // Establishing panel of spoiler text and spoiler button

        pSpoiler = new SimplePanel(Colour.getPrimaryColor());
        pGameFields.add(pSpoiler,c);
        c.gridy = 0;

        // Establishing show/hide spoiler button

        btSpoiler = new JButton(Language.loadMessage("gv_show_spoiler"));
        pSpoiler.add(Component.createSingleButton(btSpoiler, Colour.getPrimaryColor()));

        // Establishing spoiler

        aSpoiler = new JTextArea();
        aSpoiler.setEditable(false);
        pAreaSpoiler = Component.createTextArea(Language.loadMessage("ge_spoiler"), aSpoiler, 2, Colour.getPrimaryColor());
        pSpoiler.add(pAreaSpoiler);

        add(scroll,BorderLayout.CENTER);

        // Establishing general options
        btReturn = new JButton(Language.loadMessage("g_return"));
        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }

    public void viewSpoiler(boolean flag){
        btSpoiler.setText(flag ? Language.loadMessage("gv_hide_spoiler") : Language.loadMessage("gv_show_spoiler"));
        pAreaSpoiler.setVisible(flag);
    }

    public boolean spoilerVisible(){
        return pAreaSpoiler.isVisible();
    }

    public void addDatabaseInfo(GameData gd){
        c.gridy = 0;
        pGameData = new GameDataPanel(gd);
        pGameFields.add(pGameData,c);
        validate();
        repaint();
    }

    public void removeDatabaseInfo(){
        pGameFields.remove(pGameData);
        pGameData.image = null;
        pGameData = null;
        validate();
        repaint();
    }

}
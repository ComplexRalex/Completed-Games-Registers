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
import util.Advice;
import util.Colour;
import util.Component;
import util.Language;
import util.SimplePanel;

@SuppressWarnings("serial")
public class ViewGamePanel extends JPanel{
    private JPanel pGameFields, pSpoiler;
    private GridBagConstraints c;
    public JTextField txtName, txtYear, txtRate;
    public JTextArea aComment, aNote, aSpoiler;
    public JButton btSpoiler, btReturn;
    public GameDataPanel pGameData;

    public class GameDataPanel extends SimplePanel{
        public JTextField txtName, txtRelease, txtRating;
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
            }catch(IOException e){Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong"), e.toString()+"The won't be shown!", Language.loadMessage("g_accept"), Colour.getPrimaryColor());}
        
            // Establishing game name

            txtName = new JTextField(gd.getName());
            txtName.setEditable(false);
            add(Component.createTextField(Language.loadMessage("gv_name"), txtName, true, Colour.getBackgroundColor()));
            
            // Establishing game release date

            try {
				txtRelease = new JTextField((new SimpleDateFormat("yyyy MM dd")).parse(gd.getReleaseDate()).toString());
			} catch (ParseException e) {
				txtRelease = new JTextField(gd.getReleaseDate());
			}
            txtRelease.setEditable(false);
            add(Component.createTextField(Language.loadMessage("gv_release"), txtRelease, true, Colour.getBackgroundColor()));

            // Establishing game platforms

            aPlatforms = new JTextArea(oneLineList(gd.getPlatforms()));
            aPlatforms.setEditable(false);
            add(Component.createTextArea(Language.loadMessage("gv_platforms"), aPlatforms, 2, Colour.getBackgroundColor()));

            // Establishing game genres

            aGenres = new JTextArea(oneLineList(gd.getGenres()));
            aGenres.setEditable(false);
            add(Component.createTextArea(Language.loadMessage("gv_genres"), aGenres, 2, Colour.getBackgroundColor()));
            
            // Establishing game tags

            aTags = new JTextArea(oneLineList(gd.getTags()));
            aTags.setEditable(false);
            add(Component.createTextArea(Language.loadMessage("gv_tags"), aTags, 3, Colour.getBackgroundColor()));

            // Establishing game rating

            txtRating = new JTextField(Float.toString(gd.getRating()));
            txtRating.setEditable(false);
            add(Component.createTextField(Language.loadMessage("gv_rating"), txtRating, true, Colour.getBackgroundColor()));
        
            // Establishing gap

            add(Component.createGap(35,Colour.getBackgroundColor()));
        }

        private String oneLineList(String[] elements){
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
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_year"), txtYear, true, Colour.getBackgroundColor()),c);
        c.gridy++;

        // Establishing local user rating

        txtRate = new JTextField();
        txtRate.setEditable(false);
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_rating"), txtRate, true, Colour.getBackgroundColor()),c);
        c.gridy++;

        // Establishing comments

        aComment = new JTextArea();
        aComment.setEditable(false);
        pGameFields.add(Component.createTextArea(Language.loadMessage("ge_comment"), aComment, 3, Colour.getBackgroundColor()),c);
        c.gridy++;

        // Establishing notes

        aNote = new JTextArea();
        aNote.setEditable(false);
        pGameFields.add(Component.createTextArea(Language.loadMessage("ge_note"), aNote, 2, Colour.getBackgroundColor()),c);
        c.gridy++;

        // Establishing show/hide spoiler button

        btSpoiler = new JButton(Language.loadMessage("gv_show_spoiler"));
        pGameFields.add(Component.createSingleButton(btSpoiler, Colour.getBackgroundColor()),c);
        c.gridy++;

        // Establishing spoiler

        aSpoiler = new JTextArea();
        aSpoiler.setEditable(false);
        pSpoiler = Component.createTextArea(Language.loadMessage("ge_spoiler"), aSpoiler, 2, Colour.getPrimaryColor());
        pGameFields.add(pSpoiler,c);
        c.gridy = 0;

        add(scroll,BorderLayout.CENTER);

        // Establishing general options
        btReturn = new JButton(Language.loadMessage("g_return"));
        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }

    public void viewSpoiler(boolean flag){
        if(flag)
            btSpoiler.setText(Language.loadMessage("gv_hide_spoiler"));
        else
            btSpoiler.setText(Language.loadMessage("gv_show_spoiler"));
        pSpoiler.setVisible(flag);
    }

    public boolean spoilerVisible(){
        return pSpoiler.isVisible();
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
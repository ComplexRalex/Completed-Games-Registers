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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;

import model.GameData;
import util.Colour;
import util.Component;
import util.Language;
import util.SimplePanel;
import util.Typeface;

/**
 * <h3>ViewGamePanel view class.</h3>
 * This class extends from JPanel and is used to display
 * an already created <b>completed-game <i>register</i></b>.
 * <p>
 * Also, it includes information related to that game
 * such as their developers, publishers, user tags, genres,
 * description, etcetera.
 * 
 * @author Alejandro Batres
 * @see ViewGameController
 * @see GameStat
 * @see GameData
 * @see GameRegister
 */
@SuppressWarnings("serial")
public class ViewGamePanel extends JPanel{

    /**
     * Panels with different components.
     */
    private JPanel pGameFields, pAreaSpoiler;

    /**
     * Panels used to be show/hide depending of the
     * content of such field in a {@link GameStat}
     * field.
     * <p>
     * If a field is "empty" it will simply hide
     * the panel.
     */
    public JPanel pRate, pYear, pComment, pNote;

    /**
     * Panel containing the {@link #btSpoiler} and
     * {@link #aSpoiler} components.
     * 
     * @see #btSpoiler
     * @see #aSpoiler
     */
    public SimplePanel pSpoiler;

    /**
     * Constraints of the {@link GridBagLayout} class
     * to center the components.
     */
    private GridBagConstraints c;

    /**
     * Text fields used to show different values.
     */
    public JTextField txtName, txtYear, txtRate;

    /**
     * Text areas used to show a large amount of text.
     */
    public JTextArea aComment, aNote, aSpoiler;

    /**
     * Buttons used to different actions.
     */
    public JButton btSpoiler, btReturn;

    /**
     * Panel containing most of the game information
     * downloaded using the RAWG's API through the
     * {@link GameData#downloadGameInfo(String)} method.
     * 
     * @see #addDatabaseInfo(GameData)
     * @see #removeDatabaseInfo()
     */
    public GameDataPanel pGameData;

    /**
     * Scrollbar used in the {@link JScrollPane} of the central
     * pane.
     */
    public JScrollBar scrollBar;

    /**
     * <h3>GameDataPanel view class.</h3>
     * <h4>(from ViewGamePanel)</h4>
     * This class extends from {@link SimplePanel} and is used to 
     * create a panel that displays some of the fields of a
     * JSON file downloaded through {@link GameData#downloadGameInfo(String)}.
     * <p>
     * All the information is obtained by the <i>getter</i>
     * methods of the {@link GameData} class.
     * 
     * @author Alejandro Batres
     * @see GameData
     */
    public class GameDataPanel extends SimplePanel{

        /**
         * Button that is used to link to a RAWG's website page
         * related to the game.
         */
        public JButton btMoreDetails;

        /**
         * Text fields used to show different values.
         */
        public JTextField txtName, txtDevelopers, txtPublishers, txtRelease, txtRating;
        
        /**
         * Text areas used to show different values.
         */
        public JTextArea aPlatforms, aGenres, aTags;

        /**
         * Image related to the game.
         */
        public BufferedImage image;

        /**
         * Constructor of the GameDataPanel class.
         * 
         * @param gd GameData where all the information
         * is going to be collected.
         * @see #initComponents(GameData)
         */
        public GameDataPanel(GameData gd){
            initComponents(gd);
        }

        /**
         * Initializes every visual component inside of this panel.
         */
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

            add(Component.createPlainText(gd.getDescription(), Typeface.textPlain, false, Colour.getPrimaryColor()));

            // Establishing game developers

            try{
                txtDevelopers = new JTextField(oneLineList(gd.getDevelopers()));
                txtDevelopers.setEditable(false);
                add(Component.createTextField(Language.loadMessage("gv_developers"), txtDevelopers, false, Colour.getBackgroundColor()));
            } catch(NullPointerException | JSONException e) {}

            // Establishing game publishers

            try{
                txtPublishers = new JTextField(oneLineList(gd.getPublishers()));
                txtPublishers.setEditable(false);
                add(Component.createTextField(Language.loadMessage("gv_publishers"), txtPublishers, false, Colour.getBackgroundColor()));
            } catch(NullPointerException | JSONException e) {}

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
            } catch(NullPointerException | JSONException e) {}

            // Establishing game platforms

            try{
                aPlatforms = new JTextArea(oneLineList(gd.getPlatforms()));
                aPlatforms.setEditable(false);
                add(Component.createTextArea(Language.loadMessage("gv_platforms"), aPlatforms, 2, Colour.getBackgroundColor()));
            } catch(NullPointerException | JSONException e) {}

            // Establishing game genres

            try{
                aGenres = new JTextArea(oneLineList(gd.getGenres()));
                aGenres.setEditable(false);
                add(Component.createTextArea(Language.loadMessage("gv_genres"), aGenres, 2, Colour.getBackgroundColor()));
            } catch(NullPointerException | JSONException e) {}

            // Establishing game tags

            try{
                aTags = new JTextArea(oneLineList(gd.getTags()));
                aTags.setEditable(false);
                add(Component.createTextArea(Language.loadMessage("gv_tags"), aTags, 3, Colour.getBackgroundColor()));
            } catch(NullPointerException | JSONException e) {}

            // Establishing game rating

            try{
                txtRating = new JTextField(Float.toString(gd.getRating()));
                txtRating.setEditable(false);
                add(Component.createTextField(Language.loadMessage("gv_rating"), txtRating, true, Colour.getBackgroundColor()));
            } catch(NullPointerException | JSONException e) {}

            btMoreDetails = new JButton(Language.loadMessage("gv_more_details"));
            add(Component.createSingleButton(btMoreDetails, Colour.getPrimaryColor()));
            
            // Establishing gap

            add(Component.createGap(35,Colour.getBackgroundColor()));
        }

        /**
         * Appends to a single String all array of elements.
         * If the current evaluated String is NOT the last
         * of the array, will append it a "{@code , }" at
         * the end of the string. Otherwise, will be appended
         * a "{@code .}" at the end.
         * <p>
         * For instance, if the array is the following:
         * <blockquote><pre>
         *     String names[] = {"Alejandro","José","Antonio","Laura","Marco"};
         * </pre></blockquote>
         * then the result will be: "<b>Alejandro, José, Antonio,
         * Laura, Marco.</b>"
         * 
         * @param elements String array containing the elements
         * @return String variable containing all the elements
         * separated by "<b>,</b>" an a "<b>.</b>" at the end.
         */
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

    /**
     * Constructor of the ViewGamePanel class. This will set
     * the layout manager of the current panel, its background
     * color and finally initialize all of its components.
     * 
     * @see #initComponents()
     */
    public ViewGamePanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Colour.getBackgroundColor());
        initComponents();
    }

    /**
	 * Initializes every visual component inside of this panel.
	 */
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
        scrollBar = scroll.getVerticalScrollBar();

        // - Establishing subtitle

        pGameFields.add(Component.createSubtitle(Language.loadMessage("ge_user_info"), Colour.getPrimaryColor()),c);
        c.gridy++;

        // - Establishing game name

        txtName = new JTextField();
        txtName.setEditable(false);
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_name"), txtName, true, Colour.getBackgroundColor()),c);
        c.gridy++;

        // - Establishing year of completion

        txtYear = new JTextField();
        txtYear.setEditable(false);
        pYear = Component.createTextField(Language.loadMessage("ge_year"), txtYear, true, Colour.getBackgroundColor());
        pGameFields.add(pYear,c);
        c.gridy++;

        // - Establishing local user rating

        txtRate = new JTextField();
        txtRate.setEditable(false);
        pRate = Component.createTextField(Language.loadMessage("ge_rating"), txtRate, true, Colour.getBackgroundColor());
        pGameFields.add(pRate,c);
        c.gridy++;

        // - Establishing comments

        aComment = new JTextArea();
        aComment.setEditable(false);
        pComment = Component.createTextArea(Language.loadMessage("ge_comment"), aComment, 3, Colour.getBackgroundColor());
        pGameFields.add(pComment,c);
        c.gridy++;

        // - Establishing notes

        aNote = new JTextArea();
        aNote.setEditable(false);
        pNote = Component.createTextArea(Language.loadMessage("ge_note"), aNote, 2, Colour.getBackgroundColor());
        pGameFields.add(pNote,c);
        c.gridy++;

        // - Establishing panel of spoiler text and spoiler button

        pSpoiler = new SimplePanel(Colour.getPrimaryColor());
        pGameFields.add(pSpoiler,c);
        c.gridy = 0;

        // - Establishing show/hide spoiler button

        btSpoiler = new JButton(Language.loadMessage("gv_show_spoiler"));
        pSpoiler.add(Component.createSingleButton(btSpoiler, Colour.getPrimaryColor()));

        // - Establishing spoiler

        aSpoiler = new JTextArea();
        aSpoiler.setEditable(false);
        pAreaSpoiler = Component.createTextArea(Language.loadMessage("ge_spoiler"), aSpoiler, 2, Colour.getPrimaryColor());
        pSpoiler.add(pAreaSpoiler);

        add(scroll,BorderLayout.CENTER);

        // Establishing general options
        btReturn = new JButton(Language.loadMessage("g_return"));
        add(Component.createGeneralOptions(new JButton[]{btReturn}, Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }

    /**
     * Displays (or hides) the spoiler text area.
     * 
     * @param flag Boolean which determines if {@link #pAreaSpoiler}
     * will be shown ({@code true}) or not ({@code false})
     */
    public void viewSpoiler(boolean flag){
        btSpoiler.setText(flag ? Language.loadMessage("gv_hide_spoiler") : Language.loadMessage("gv_show_spoiler"));
        pAreaSpoiler.setVisible(flag);
    }

    /**
     * Tells if the spoiler is currently visible.
     * 
     * @return {@code true} if {@link #pAreaSpoiler} is
     * visible. {@code false} otherwise.
     */
    public boolean spoilerVisible(){
        return pAreaSpoiler.isVisible();
    }

    /**
     * Appends a {@link GameDataPanel} to the current panel.
     * 
     * @param gd {@link GameData} variable which will be
     * used to obtain the game information
     * @return an instance of {@link GameDataPanel}.
     */
    public GameDataPanel addDatabaseInfo(GameData gd){
        c.gridy = 0;
        pGameData = new GameDataPanel(gd);
        pGameFields.add(pGameData,c);
        validate();
        repaint();

        return pGameData;
    }

    /**
     * Removes the current {@link GameDataPanel} appended
     * in the panel.
     */
    public void removeDatabaseInfo(){
        pGameFields.remove(pGameData);
        pGameData.image = null;
        pGameData = null;
        validate();
        repaint();
    }
}

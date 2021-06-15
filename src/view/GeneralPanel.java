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
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.Colour;
import util.Component;
import util.ImageResource;
import util.Language;
import util.SimplePanel;
import util.Typeface;

/**
 * <h3>GeneralPanel view class.</h3>
 * This class extends from JPanel and is used to display
 * the most "general" options possible. What this means is
 * that this panel will allow the user to access any site
 * they want.
 * 
 * @author Alejandro Batres
 * @see GeneralController
 */
@SuppressWarnings("serial")
public class GeneralPanel extends JPanel{

    /**
     * Panel used to display all the entered <b>completed-game
     * <i>registers</i></b>.
     */
    private JPanel pCentral;

    /**
     * Icons used to be put in different buttons.
     */
    private ImageIcon iconAdd, iconBackup, iconExport, iconHelp, iconView, iconEdit, iconRemove;

    /**
     * TextField that works as part of the searchbar.
     */
    private JTextField txtSearch;

    /**
	 * Buttons used to different actions.
	 */
    public JButton btAdd, btBackup, btExport, btHelp, btConfig, btAbout, btSearch;

    /**
     * Label that displays the username at the top of the
     * program.
     */
    public JLabel lbUser, lbCount;

    /**
     * Panel that works as a "placeholder" in case of
     * no registers added.
     */
    public FirstTimePanel pNothing;

    /**
     * Scrollbar used in the {@link JScrollPane} of the central
     * pane.
     */
    public JScrollBar scrollBar;
    
    /**
     * Boolean that is used to tell if the icons have
     * loaded successfully.
     */
    private boolean loadedIcons;

    /**
     * Boolean that is used to tell if the place holder
     * is added at the moment.
     */
    private boolean placeHolder;

    /**
     * Constructor of the GeneralPanel class. This
     * will set the layout manager of the current 
     * panel, its background color and finally 
     * initialize all of its components.
     * Also, will initialize the icons of some
     * buttons and the {@link #pNothing} panel.
     * 
     * @see #initIcons()
     * @see #initComponents()
     */
    public GeneralPanel(){
        setLayout(new BorderLayout());
        setBackground(Colour.getBackgroundColor());

        pNothing = new FirstTimePanel();

        initIcons();
        initComponents();
    }

    /**
     * <h3>GameRegisterPanel view class.</h3>
     * <h4>(from GeneralPanel)</h4>
     * This class extends from JPanel and is used to display,
     * clearly, a <b>completed-game <i>register</i></b>.
     * 
     * @author Alejandro Batres
     */
    public class GameRegisterPanel extends JPanel{

        /**
         * Buttons used to different actions.
         */
        public JButton btView, btEdit, btRemove, btRecent;

        /**
         * Text area that displays the name of the game.
         */
        public JTextArea aName;

        /**
         * Constructor of the GameRegisterPanel class.
         * 
         * @param game String which contains the name of the
         * game
         * @param recent Boolean that determines if the new
         * register was added recently ({@code true}) or not
         * ({@code false}) (not in use anymore: use <b>false</b>)
         * @see #initComponents(String, boolean)
         */
        public GameRegisterPanel(String game, boolean recent){
            setLayout(new BorderLayout());
            setBackground(Colour.getBackgroundColor());
            initComponents(game, recent);
        }

        /**
         * Initializes every visual component inside of this panel.
         * 
         * @param game String which contains the name of the
         * game
         * @param recent Boolean that determines if the new
         * register was added recently ({@code true}) or not
         * ({@code false})
         */
        public void initComponents(String game, boolean recent){

            // Establishing the title of the register

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

            // Establishing panel that will be at the right

            JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
            rightSide.setBackground(this.getBackground());

            // - Establishing buttons

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

            // - Establishing "recently added" button (if it's true)

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

    /**
     * <h3>FirstTimePanel view class.</h3>
     * <h4>(from GeneralPanel)</h4>
     * This class extends from JPanel and is used to display
     * a "placeholder" text, in case of non-existence of
     * registers.
     * 
     * @author Alejandro Batres
     */
    public class FirstTimePanel extends JPanel{

        /**
         * Constructor of the FirsTimePanel class.
         */
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

    /**
     * Initializes every icon that will be used in this panel.
     */
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

    /**
     * Initializes every visual component inside of this panel.
     */
    public void initComponents(){

        // Establishing title

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

        // Establishing centered panel

        JPanel centeredPanel = new JPanel(new BorderLayout());
        centeredPanel.setBackground(Colour.getBackgroundColor());
        
        // - Establishing central-central panel

        pCentral = new JPanel(new GridLayout(0,1,10,6));
        pCentral.setBackground(Colour.getPrimaryColor());
        JScrollPane scroll = Component.createScrollPane(pCentral);
        scrollBar = scroll.getVerticalScrollBar();
        centeredPanel.add(scroll,BorderLayout.CENTER);

        // - Establishing central-upper panel
        
        txtSearch = new JTextField();
        btSearch = new JButton();
        centeredPanel.add(Component.createTexFieldAndButton(Language.loadMessage("g_search"), txtSearch, btSearch, Colour.getBackgroundColor()),BorderLayout.NORTH);
        
        // - Establishing central-bottom panel
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        bottomPanel.setBackground(Colour.getBackgroundColor());
        JLabel lbCountMSG = new JLabel(Language.loadMessage("m_count"));
        lbCountMSG.setFont(Typeface.labelPlain);
        lbCountMSG.setBackground(this.getBackground());
        lbCountMSG.setForeground(Colour.getFontColor());
        bottomPanel.add(lbCountMSG);
        lbCount = new JLabel("0");
        lbCount.setFont(Typeface.labelBold);
        lbCount.setBackground(this.getBackground());
        lbCount.setForeground(Colour.getFontColor());
        bottomPanel.add(lbCount);
        centeredPanel.add(bottomPanel,BorderLayout.SOUTH);
        
        add(centeredPanel,BorderLayout.CENTER);

        // Establishing left-side panel

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
        
        // Establishing general options

        btConfig = new JButton(Language.loadMessage("m_config"));
        btAbout = new JButton(Language.loadMessage("m_about"));
        
        add(Component.createGeneralOptions(new JButton[]{btConfig,btAbout},Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }

    /**
     * Appends the given component in the center of this panel.
     * 
     * @param component Component that will be appended to
     * {@link #pCentral}
     */
    public void addToCenter(JComponent component){
        pCentral.add(component);
    }

    /**
     * Sets the count of the {@link #lbCount} label, referred to
     * the actual count of the registers entered.
     * 
     * @param count of registers
     */
    public void setCount(int count){
        lbCount.setText(String.valueOf(count));
    }

    /**
     * Removes the given component from the center of this
     * panel.
     * 
     * @param component Component that will be removed
     * from {@link #pCentral}
     */
    public void removeFromCenter(JComponent component){
        pCentral.remove(component);
    }

    /**
     * Removes all components from the center of this panel.
     */
    public void removeAllFromCenter(){
        pCentral.removeAll();
    }

    /**
     * Tells if the placeholder is currntly added on the
     * central panel.
     * 
     * @return The truth (about the mentioned above).
     */
    public boolean isPlaceHolderPut(){
        return placeHolder;
    }

    /**
     * Appends the {@link #pNothing} panel to the
     * center of this panel.
     */
    public void addPlaceHolder(){
        if(!placeHolder){
            pCentral.add(pNothing);
            placeHolder = true;
        }
    }

    /**
     * Removes the {@link #pNothing} panel from
     * the center of this panel.
     */
    public void removePlaceHolder(){
        if(placeHolder){
            pCentral.remove(pNothing);
            placeHolder = false;
        }
    }
}

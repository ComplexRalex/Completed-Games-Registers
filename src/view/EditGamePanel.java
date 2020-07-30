package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import util.Colour;
import util.Component;
import util.Language;
import util.SimplePanel;

import model.GameStat;

/**
 * Panel which contains every visual component about the game register
 * creation (or modification) of the program.
 * 
 * @author Alejandro Batres
 */
@SuppressWarnings("serial")
public class EditGamePanel extends JPanel{
    public JTextField txtName, txtYear;
    public JTextArea aComment, aNote, aSpoiler;
    public JButton btDownload, btDelete, btCreate, btChange, btCancel;
    public JRadioButton btRate[];
    public JScrollBar scrollBar;

    public EditGamePanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Colour.getBackgroundColor());
        initComponents();
    }

    public void initComponents(){
        
        // Establishing the title

        add(Component.createTitle(Language.loadMessage("ge_title"), Colour.getPrimaryColor()), BorderLayout.NORTH);

        // Establishing panel of all options

        SimplePanel pGameFields = new SimplePanel();
        JScrollPane scrollFields = Component.createScrollPane(pGameFields);
        scrollBar = scrollFields.getVerticalScrollBar();
        
        // Establishing a "main game info" subtitle

        pGameFields.add(Component.createSubtitle(Language.loadMessage("ge_main_info"), Colour.getPrimaryColor()));

        // Establishing game name field

        txtName = new JTextField();
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_name"), txtName, true, Colour.getBackgroundColor()));

        // Establishing "download game info" button
        
        btDownload = new JButton(Language.loadMessage("ge_download"));
        pGameFields.add(Component.createSingleButton(btDownload, Colour.getBackgroundColor()));

        // Establishing "delete game info" button

        btDelete = new JButton(Language.loadMessage("ge_delete"));
        pGameFields.add(Component.createSingleButton(btDelete, Colour.getBackgroundColor()));

        // - Establishing gap XD

		pGameFields.add(Component.createGap(35,Colour.getBackgroundColor()));
        
        // Establishing a "user game info" subtitle

        pGameFields.add(Component.createSubtitle(Language.loadMessage("ge_user_info"), Colour.getPrimaryColor()));

        // Establishing year of completion field

        txtYear = new JTextField();
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_year"), txtYear, true, Colour.getBackgroundColor()));

        // Establishing rating options

        btRate = new JRadioButton[GameStat.RATE_OPTIONS];
        pGameFields.add(Component.createRadioButtons(
            Language.loadMessage("ge_rating"), 
            new String[]{
                Language.loadMessage("ge_rate_0"),
                Language.loadMessage("ge_rate_1"),
                Language.loadMessage("ge_rate_2"),
                Language.loadMessage("ge_rate_3"),
                Language.loadMessage("ge_rate_4"),
                Language.loadMessage("ge_rate_5")
            },
            btRate,
            Colour.getBackgroundColor()
        ));
        btRate[0].setSelected(true);

        // Establishing comment field

        aComment = new JTextArea();
        pGameFields.add(Component.createTextArea(Language.loadMessage("ge_comment"), aComment, 3, Colour.getBackgroundColor()));

        // Establishing note field

        aNote = new JTextArea();
        pGameFields.add(Component.createTextArea(Language.loadMessage("ge_note"), aNote, 2, Colour.getBackgroundColor()));

        // Establishing spoiler field

        aSpoiler = new JTextArea();
        pGameFields.add(Component.createTextArea(Language.loadMessage("ge_spoiler"), aSpoiler, 2, Colour.getBackgroundColor()));

        add(scrollFields, BorderLayout.CENTER);

        // Establishing general options
        
        btCreate = new JButton(Language.loadMessage("g_accept"));
        btChange = new JButton(Language.loadMessage("g_change"));
        btCancel = new JButton(Language.loadMessage("g_cancel"));
        add(Component.createGeneralOptions(new JButton[]{btCreate, btChange, btCancel}, Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }
}
package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import util.Colour;
import util.Component;
import util.Language;
import util.SimplePanel;

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
    public JButton btCreate, btChange, btCancel;

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
        JScrollPane scrollFields = new JScrollPane(pGameFields);
        scrollFields.setBorder(BorderFactory.createLineBorder(pGameFields.getBackground()));
		scrollFields.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFields.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollFields.setAlignmentY(JScrollPane.RIGHT_ALIGNMENT);
        
        // Establishing a "main game info" subtitle

        pGameFields.add(Component.createSubtitle(Language.loadMessage("ge_main_info"), Colour.getPrimaryColor()));
        
        // Establishing game name field

        txtName = new JTextField();
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_name"), txtName, true, Colour.getBackgroundColor()));

        // - Establishing gap XD

		pGameFields.add(Component.createGap(35,Colour.getBackgroundColor()));
        
        // Establishing a "user game info" subtitle

        pGameFields.add(Component.createSubtitle(Language.loadMessage("ge_user_info"), Colour.getPrimaryColor()));

        // Establishing year of completion field

        txtYear = new JTextField();
        pGameFields.add(Component.createTextField(Language.loadMessage("ge_year"), txtYear, true, Colour.getBackgroundColor()));

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
        btChange.setEnabled(false);
        btCancel = new JButton(Language.loadMessage("g_cancel"));
        add(Component.createGeneralOptions(new JButton[]{btCreate, btChange, btCancel}, Colour.getPrimaryColor()),BorderLayout.SOUTH);
    }
}
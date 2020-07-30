package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;

import model.GameData;
import util.Colour;
import util.Language;
import util.Component;
import util.SimplePanel;
import util.Typeface;

/**
 * Panel which contains every visual component about the configuration menu of the
 * Completed-GamesRegister program.
 * 
 * @author Alejandro Batres
 */
@SuppressWarnings("serial")
public class ConfigurationPanel extends JPanel{
	public JButton
		btAutoBackupON, btAutoBackupOFF,
		btExitDialogON, btExitDialogOFF,
		btResetConfig, btResetSave, btResetBackups,
		btResetExports, btSuddenClose,
		btDeleteGameInfo, btWipeOut,
		btAccept, btReturn;
	public JComboBox<String> cbLang;
	public JRadioButton btTheme[];
	public JFormattedTextField txtConnect, txtRead;
	public JSpinner spConnect, spRead;
	public JTextField txtUser;
	
	public ConfigurationPanel(){
		this.setLayout(new BorderLayout());
		this.setBackground(Colour.getPrimaryColor());
		initComponents();
	}
	
	/**
	 * Initializes every visual component inside of the ConfigurationPanel panel.
	 */
	private void initComponents(){

		// Establishing the title (lol)
		
		add(Component.createTitle(Language.loadMessage("cf_title"), Colour.getPrimaryColor()),BorderLayout.NORTH);
		
		// Establishing panel of all options
		
		SimplePanel pGeneralOptions = new SimplePanel();
		JScrollPane scrollOptions = Component.createScrollPane(pGeneralOptions);
		
		// - Establishing "general settings" subtitle

		pGeneralOptions.add(Component.createSubtitle(Language.loadMessage("cf_general"),Colour.getPrimaryColor()));

		// - Establishing the username option

		txtUser = new JTextField();
		pGeneralOptions.add(Component.createTextField(Language.loadMessage("cf_change_user"), txtUser, true, Colour.getBackgroundColor()));
		
		// - Establishing a note about the auto-backup option

		pGeneralOptions.add(Component.createPlainText(Language.loadMessage("cf_auto_backup_note"), Typeface.textPlain, false, Colour.getPrimaryColor()));

		// - Establishing the auto-backup options
		
		btAutoBackupON = new JButton("ON");
		btAutoBackupOFF = new JButton("OFF");
		pGeneralOptions.add(Component.createSwitchButton(Language.loadMessage("cf_auto_backup"), btAutoBackupON, btAutoBackupOFF, Colour.getBackgroundColor()));

		// - Establishing "show dialog on close" options
		
		btExitDialogON = new JButton("ON");
		btExitDialogOFF = new JButton("OFF");
		pGeneralOptions.add(Component.createSwitchButton(Language.loadMessage("cf_ask_on_close"), btExitDialogON, btExitDialogOFF, Colour.getBackgroundColor()));
		
		// - Establishing the theme options
		
		btTheme = new JRadioButton[Colour.available.length];
		pGeneralOptions.add(Component.createRadioButtons(Language.loadMessage("cf_theme"), Colour.available, btTheme, Colour.getBackgroundColor()));
		
		// - Establishing language options

		cbLang = new JComboBox<String>(Language.available);
		pGeneralOptions.add(Component.createComboBox(Language.loadMessage("cf_lang"), cbLang, Colour.getBackgroundColor()));

		// - Establishing gap XD

		pGeneralOptions.add(Component.createGap(35,Colour.getBackgroundColor()));

		// - Establishing "advanced" subtitle

		pGeneralOptions.add(Component.createSubtitle(Language.loadMessage("cf_advanced"), Colour.getPrimaryColor()));

		// - Establishing "advanced" message

		pGeneralOptions.add(Component.createPlainText(Language.loadMessage("cf_advanced_text"), Typeface.labelPlain, false, Colour.getPrimaryColor()));

		// - Establishing connection timeout spinner

		spConnect = new JSpinner(new SpinnerNumberModel(5000, GameData.minTimeout, GameData.maxTimeout, 100));
		txtConnect = ((JFormattedTextField)((JPanel)spConnect.getComponent(2)).getComponent(0));
		txtConnect.setEditable(false);
		pGeneralOptions.add(Component.createSpinner(Language.loadMessage("cf_connection_timeout"), spConnect, Colour.getBackgroundColor()));

		// - Establishing connection timeout spinner

		spRead = new JSpinner(new SpinnerNumberModel(5000, GameData.minTimeout, GameData.maxTimeout, 100));
		txtRead = ((JFormattedTextField)((JPanel)spRead.getComponent(2)).getComponent(0));
		txtRead.setEditable(false);
		pGeneralOptions.add(Component.createSpinner(Language.loadMessage("cf_read_timeout"), spRead, Colour.getBackgroundColor()));

		// - Establishing gap XD

		pGeneralOptions.add(Component.createGap(35,Colour.getBackgroundColor()));

		// - Establishing "delicated stuff" subtitle

		pGeneralOptions.add(Component.createSubtitle(Language.loadMessage("cf_delicate"), Colour.getPrimaryColor()));

		// - Establishing "warning" message

		pGeneralOptions.add(Component.createPlainText(Language.loadMessage("cf_warn"), Typeface.labelPlain, false, Colour.getPrimaryColor()));

		// - Establishing sudden close button

		btSuddenClose = new JButton(Language.loadMessage("cf_sudden_close"));
		pGeneralOptions.add(Component.createSingleButton(btSuddenClose, Colour.getBackgroundColor()));
		btSuddenClose.setBackground(Colour.colorOFF);

		// - Establishing reset configuration button

		btResetConfig = new JButton(Language.loadMessage("cf_reset_cf"));
		pGeneralOptions.add(Component.createSingleButton(btResetConfig, Colour.getBackgroundColor()));

		// - Establishing reset save file button

		btResetSave = new JButton(Language.loadMessage("cf_reset_save"));
		pGeneralOptions.add(Component.createSingleButton(btResetSave, Colour.getBackgroundColor()));

		// - Establishing delete downloaded game database info button

		btDeleteGameInfo = new JButton(Language.loadMessage("cf_reset_db_info"));
		pGeneralOptions.add(Component.createSingleButton(btDeleteGameInfo, Colour.getBackgroundColor()));

		// - Establishing reset backup files button

		btResetBackups = new JButton(Language.loadMessage("cf_reset_backups"));
		pGeneralOptions.add(Component.createSingleButton(btResetBackups, Colour.getBackgroundColor()));

		// - Establishing reset export files button

		btResetExports = new JButton(Language.loadMessage("cf_reset_exports"));
		pGeneralOptions.add(Component.createSingleButton(btResetExports, Colour.getBackgroundColor()));

		// - Establishing wipe out all data button

		btWipeOut = new JButton(Language.loadMessage("cf_wipeout"));
		pGeneralOptions.add(Component.createSingleButton(btWipeOut, Colour.getBackgroundColor()));
		btWipeOut.setBackground(Colour.colorOFF);

		// Adding scroll pane into the config panel

		add(scrollOptions,BorderLayout.CENTER);
				
		// Establishing panel which contains "Accept" and "Return" options
		
		btAccept = new JButton(Language.loadMessage("g_apply"));
		btReturn = new JButton(Language.loadMessage("g_return"));
		
		add(Component.createGeneralOptions(new JButton[]{btAccept,btReturn}, Colour.getPrimaryColor()),BorderLayout.SOUTH);
	}
}

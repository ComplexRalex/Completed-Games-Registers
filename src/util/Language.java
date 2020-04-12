package util;

import java.util.HashMap;
import model.Configuration;

public class Language{
	
	public static final String defaultCase = "English";
	public static final String[] available = {"English", "Spanish"};
	
	private final static HashMap<String,HashMap<String,String>> languages = new HashMap<>();
	
	public static void initialize(){
		setEnglish();
		setSpanish();
	}
	
	public static String loadMessage(String code){
		return languages.get(Configuration.currentLanguage()).get(code);
	}
	
	private static void setEnglish(){
		languages.put("English",new HashMap<>());
		
		languages.get("English").put("g_accept",			"Accept");
		languages.get("English").put("g_apply",				"Apply");
		languages.get("English").put("g_cancel",			"Cancel");
		languages.get("English").put("g_return",			"Return");
		languages.get("English").put("g_change", 			"Change");
		
		languages.get("English").put("cf_title",		 	"Configuration menu");
		languages.get("English").put("cf_current_user",		"Current username:");
		languages.get("English").put("cf_change_user",		"Change username:");
		languages.get("English").put("cf_autosave_1", 		"Auto-save data");
		languages.get("English").put("cf_autosave_2",	 	"when a change is made:");
		languages.get("English").put("cf_autobck_1", 		"Auto-backup data");
		languages.get("English").put("cf_autobck_2", 		"before closing the program:");
		languages.get("English").put("cf_theme", 			"Program theme (requires restart): ");
		languages.get("English").put("cf_lang", 			"Program language:");
		languages.get("English").put("cf_reset_cf", 		"Reset configuration to default:");
		languages.get("English").put("cf_wipeout", 			"Wipe out all data:");
	}
	
	private static void setSpanish(){
		languages.put("Spanish",new HashMap<>());
		
		languages.get("Spanish").put("g_accept",			"Aceptar");
		languages.get("Spanish").put("g_apply",				"Aplicar");
		languages.get("Spanish").put("g_cancel",			"Cancelar");
		languages.get("Spanish").put("g_return",			"Regresar");
		languages.get("Spanish").put("g_change", 			"Cambiar");
		
		languages.get("Spanish").put("cf_title", 			"Menú de configuración");
		languages.get("Spanish").put("cf_current_user",		"Username actual:");
		languages.get("Spanish").put("cf_change_user",		"Cambiar username:");
		languages.get("Spanish").put("cf_autosave_1",		"Guardado automático");
		languages.get("Spanish").put("cf_autosave_2", 		"cuando se realice un cambio:");
		languages.get("Spanish").put("cf_autobck_1", 		"Respaldo automático");
		languages.get("Spanish").put("cf_autobck_2", 		"antes de finalizar el programa:");
		languages.get("Spanish").put("cf_theme", 			"Tema del programa (requiere reinicio): ");
		languages.get("Spanish").put("cf_lang",				"Idioma del programa:");
		languages.get("Spanish").put("cf_reset_cf",			"Restaurar configuración por defecto:");
		languages.get("Spanish").put("cf_wipeout",			"Pulverizar todo los datos:");
	}
}

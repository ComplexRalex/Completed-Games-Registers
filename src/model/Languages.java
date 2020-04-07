package model;

import java.util.HashMap;
import model.Configuration;

public class Languages{
	
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
		
		languages.get("English").put("g_accept","Accept");
		languages.get("English").put("g_apply","Apply");
		languages.get("English").put("g_cancel","Cancel");
		languages.get("English").put("g_return","Return");
		
		languages.get("English").put("cf_title", "Configuration menu");
		languages.get("English").put("cf_autosave_1", "Auto-save");
		languages.get("English").put("cf_autosave_2", "data when a change is made:");
		languages.get("English").put("cf_autobck_1", "Auto-backup");
		languages.get("English").put("cf_autobck_2", "data before closing the program:");
		languages.get("English").put("cf_theme", "Program theme (requires restart): ");
		languages.get("English").put("cf_pass_tl", "Change password.");
		languages.get("English").put("cf_pass_curr", "Current password:");
		languages.get("English").put("cf_pass_new", "New password:");
		languages.get("English").put("cf_pass_conf", "Confirm new password:");
		languages.get("English").put("cf_pass_change", "Change");
		languages.get("English").put("cf_pass_ad_fy", "The password has been updated.");
		languages.get("English").put("cf_pass_ad_hn_1", "Wrong confirmation.");
		languages.get("English").put("cf_pass_ad_hn_2", "Wrong password.");
		languages.get("English").put("cf_lang", "Languages:");
		languages.get("English").put("cf_reset_cf", "Reset configuration to default:");
		languages.get("English").put("cf_wipeout", "Wipe out all data:");
	}
	
	private static void setSpanish(){
		languages.put("Spanish",new HashMap<>());
		
		languages.get("Spanish").put("g_accept","Aceptar");
		languages.get("Spanish").put("g_apply","Aplicar");
		languages.get("Spanish").put("g_cancel","Cancelar");
		languages.get("Spanish").put("g_return","Regresar");
		
		languages.get("Spanish").put("cf_title", "Menú de configuración");
		languages.get("Spanish").put("cf_autosave_1", "Guardado automático");
		languages.get("Spanish").put("cf_autosave_2", "cuando se realice un cambio:");
		languages.get("Spanish").put("cf_autobck_1", "Respaldo automático");
		languages.get("Spanish").put("cf_autobck_2", "antes de finalizar el programa:");
		languages.get("Spanish").put("cf_theme", "Tema del programa (requiere reinicio): ");
		languages.get("Spanish").put("cf_pass_tl", "Cambiar contraseña.");
		languages.get("Spanish").put("cf_pass_curr", "Contraseña actual:");
		languages.get("Spanish").put("cf_pass_new", "Contraseña nueva:");
		languages.get("Spanish").put("cf_pass_conf", "Confirmar contraseña nueva:");
		languages.get("Spanish").put("cf_pass_change", "Cambiar");
		languages.get("Spanish").put("cf_pass_ad_fy", "Se ha actualizado la contraseña.");
		languages.get("Spanish").put("cf_pass_ad_hn_1", "La confirmacion es incorrecta.");
		languages.get("Spanish").put("cf_pass_ad_hn_2", "La cotraseña ingresada es incorrecta.");
		languages.get("Spanish").put("cf_lang", "Idiomas:");
		languages.get("Spanish").put("cf_reset_cf", "Restaurar configuración por defecto:");
		languages.get("Spanish").put("cf_wipeout", "Pulverizar todo los datos:");
	}
}

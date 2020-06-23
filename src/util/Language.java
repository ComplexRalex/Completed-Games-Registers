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
		if(Configuration.currentLanguage() == null)
			return languages.get(defaultCase).get(code);
		return languages.get(Configuration.currentLanguage()).get(code);
	}
	
	private static void setEnglish(){
		languages.put("English",new HashMap<>());
		
		languages.get("English").put("p_title", 			"Completed-Game Register");

		languages.get("English").put("g_accept",			"Accept");
		languages.get("English").put("g_apply",				"Apply");
		languages.get("English").put("g_cancel",			"Cancel");
		languages.get("English").put("g_return",			"Return");
		languages.get("English").put("g_change", 			"Change");
		languages.get("English").put("g_message",			"Message");
		languages.get("English").put("g_oops",				"Oops!");
		languages.get("English").put("g_wentwrong",			"Something went wrong!");
		languages.get("English").put("g_indev",				"This is not implemented yet!");
		languages.get("English").put("g_restricted",		"You shall not pass here!");
		
		languages.get("English").put("cf_title",		 	"Configuration menu");
		languages.get("English").put("cf_general",			"General settings");
		languages.get("English").put("cf_change_user",		"Change username:");
		languages.get("English").put("cf_autosave", 		"Auto-save data when a change is made:");
		languages.get("English").put("cf_autobck", 			"Auto-backup data before closing the program:");
		languages.get("English").put("cf_theme", 			"Program theme (requires restart): ");
		languages.get("English").put("cf_lang", 			"Program language (requires restart):");
		languages.get("English").put("cf_delicate",			"Delicate options");
		languages.get("English").put("cf_warn",				"Warning! think twice before clicking some of these options!");
		languages.get("English").put("cf_reset_cf", 		"Reset configuration to default");
		languages.get("English").put("cf_wipeout", 			"Wipe out all data");
		languages.get("English").put("cf_yousure",			"Do you really want to do it?");

		languages.get("English").put("m_chale",				"Duuude, that's not fair!");
	}
	
	private static void setSpanish(){
		languages.put("Spanish",new HashMap<>());

		languages.get("Spanish").put("p_title", 			"Registro de Juegos Completados");
		
		languages.get("Spanish").put("g_accept",			"Aceptar");
		languages.get("Spanish").put("g_apply",				"Aplicar");
		languages.get("Spanish").put("g_cancel",			"Cancelar");
		languages.get("Spanish").put("g_return",			"Regresar");
		languages.get("Spanish").put("g_change", 			"Cambiar");
		languages.get("Spanish").put("g_message",			"Mensaje");
		languages.get("Spanish").put("g_oops",				"¡Ups!");
		languages.get("Spanish").put("g_wentwrong",			"¡Algo salió mal!");
		languages.get("Spanish").put("g_indev",				"¡Aún no se ha implementado!");
		languages.get("Spanish").put("g_restricted",		"No puede pasar, ¡váyase pa' otro lado!");
		
		languages.get("Spanish").put("cf_title", 			"Menú de configuración");
		languages.get("Spanish").put("cf_general",			"Ajustes generales");
		languages.get("Spanish").put("cf_change_user",		"Cambiar nombre de usuario:");
		languages.get("Spanish").put("cf_autosave",			"Guardado automático cuando se realice un cambio:");
		languages.get("Spanish").put("cf_autobck", 			"Respaldo automático antes de finalizar el programa:");
		languages.get("Spanish").put("cf_theme", 			"Tema del programa (requiere reinicio): ");
		languages.get("Spanish").put("cf_lang",				"Idioma del programa (requiere reinicio):");
		languages.get("Spanish").put("cf_delicate",			"Opciones delicadas");
		languages.get("Spanish").put("cf_warn",				"¡Cuidado! ¡piénsalo dos veces antes de presionar alguna de las opciones!");
		languages.get("Spanish").put("cf_reset_cf",			"Restaurar configuración por defecto");
		languages.get("Spanish").put("cf_wipeout",			"Pulverizar todo los datos");
		languages.get("Spanish").put("cf_yousure",			"¿De verdad quieres hacerlo?");

		languages.get("Spanish").put("m_chale",				"Chaaale... quería ver qué onda");
	}
}

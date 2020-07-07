package util;

import java.util.HashMap;
import model.Configuration;

/**
 * RALEX, DON'T FORGET TO ADD THE POSSIBILITY TO INCLUDE CUSTOM LANGUAGES FROM JSON FILES
 * (I mean, if the language you are looking for isn't included in the system, you can add
 * it just by including it in a JSON file in a specified folder location)
 */

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
		languages.get("English").put("g_warning",			"Warning!");
		languages.get("English").put("g_success",			"Success!");
		languages.get("English").put("g_oops",				"Oops!");
		languages.get("English").put("g_unsaved",			"There are some unsaved changes, are you sure?");
		languages.get("English").put("g_wentwrong",			"Something went wrong!");
		languages.get("English").put("g_indev",				"This is not implemented yet!");
		languages.get("English").put("g_restricted",		"You shall not pass here!");
		languages.get("English").put("g_noinfo",			"[No info provided]");
		
		languages.get("English").put("m_title",				"Welcome, ");
		languages.get("English").put("m_add",				"Add game register");
		languages.get("English").put("m_config",			"Configuration");
		languages.get("English").put("m_about",				"About this program");
		// languages.get("English").put("","");

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
		languages.get("English").put("cf_success",			"The settings have been saved!");
		languages.get("English").put("cf_no_edit",			"There were no changes...");
		languages.get("English").put("cf_reset",			"A reset is requiered!");

		languages.get("English").put("ge_title",			"Game register");
		languages.get("English").put("ge_main_info", 		"Game main info");
		languages.get("English").put("ge_name",				"Game name:");
		languages.get("English").put("ge_download",			"Download game info from RAWG database");
		languages.get("English").put("ge_delete",			"Delete game info from RAWG database");
		languages.get("English").put("ge_user_info", 		"Game user info");
		languages.get("English").put("ge_year",				"Year of completion:");
		languages.get("English").put("ge_rating",			"Rating:");
		languages.get("English").put("ge_rate_0",			"I don't know!");
		languages.get("English").put("ge_rate_1",			"NOPE");
		languages.get("English").put("ge_rate_2",			"Meh");
		languages.get("English").put("ge_rate_3",			"Neutral");
		languages.get("English").put("ge_rate_4",			"Nice");
		languages.get("English").put("ge_rate_5",			"HELL YEAH!");
		languages.get("English").put("ge_comment",			"Comments about the game:");
		languages.get("English").put("ge_note",				"Some notes before playing or something...?:");
		languages.get("English").put("ge_spoiler",			"Wanna give a spoiler? (this won't be visible at first):");

		languages.get("English").put("gv_title",			"Game info viewer");
		languages.get("English").put("gv_db_info",			"Game database info");
		languages.get("English").put("gv_name",				"Game name:");
		languages.get("English").put("gv_release",			"Release date:");
		languages.get("English").put("gv_platforms",		"Platforms where you can find this game:");
		languages.get("English").put("gv_genres",			"Genres of the game:");
		languages.get("English").put("gv_tags",				"User tags:");
		languages.get("English").put("gv_rating",			"User score:");
		languages.get("English").put("gv_show_spoiler",		"Show spoiler");
		languages.get("English").put("gv_hide_spoiler",		"Hide spoiler");

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
		languages.get("Spanish").put("g_warning",			"¡Advertencia!");
		languages.get("Spanish").put("g_success",			"¡Éxito!");
		languages.get("Spanish").put("g_oops",				"¡Ups!");
		languages.get("Spanish").put("g_unsaved",			"No se han guardado algunos cambios, ¿quieres continuar?");
		languages.get("Spanish").put("g_wentwrong",			"¡Algo salió mal!");
		languages.get("Spanish").put("g_indev",				"¡Aún no se ha implementado!");
		languages.get("Spanish").put("g_restricted",		"No puede pasar, ¡váyase pa' otro lado!");
		languages.get("English").put("g_noinfo",			"[No se obtuvo información]");

		languages.get("Spanish").put("m_title",				"Hola, ");
		languages.get("Spanish").put("m_add",				"Agregar registro de juego");
		languages.get("Spanish").put("m_config",			"Configuración");
		languages.get("Spanish").put("m_about",				"Acerca del programa");
		// languages.get("Spanish").put("","");

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
		languages.get("Spanish").put("cf_success",			"¡Se han guardado los cambios!");
		languages.get("Spanish").put("cf_no_edit",			"No hubo cambios...");
		languages.get("Spanish").put("cf_reset",			"¡Se requiere reiniciar!");

		languages.get("Spanish").put("ge_title",			"Registro de juego");
		languages.get("Spanish").put("ge_main_info", 		"Información del juego");
		languages.get("Spanish").put("ge_name",				"Nombre del juego:");
		languages.get("Spanish").put("ge_download",			"Descargar información del juego de la RAWG database");
		languages.get("Spanish").put("ge_delete",			"Borrar información del juego de la RAWG database");
		languages.get("Spanish").put("ge_user_info", 		"Información del usuario");
		languages.get("Spanish").put("ge_year",				"Año de completación:");
		languages.get("Spanish").put("ge_rating",			"Puntaje:");
		languages.get("Spanish").put("ge_rate_0",			"¡Ni idea!");
		languages.get("Spanish").put("ge_rate_1",			"NOPE");
		languages.get("Spanish").put("ge_rate_2",			"Meh");
		languages.get("Spanish").put("ge_rate_3",			"Normal");
		languages.get("Spanish").put("ge_rate_4",			"Chido");
		languages.get("Spanish").put("ge_rate_5",			"¡PERFECTO!");
		languages.get("Spanish").put("ge_comment",			"Comentarios sobre el juego:");
		languages.get("Spanish").put("ge_note",				"¿Algunas notas antes de jugar o algo...?:");
		languages.get("Spanish").put("ge_spoiler",			"¿Quieres dar un spoiler? (no será visible al principio):");

		languages.get("Spanish").put("gv_title",			"Datos del juego");
		languages.get("Spanish").put("gv_db_info",			"Información de la database");
		languages.get("Spanish").put("gv_name",				"Nombre del juego:");
		languages.get("Spanish").put("gv_release",			"Fecha de lanzamiento:");
		languages.get("Spanish").put("gv_platforms",		"Plataformas en donde lo puedes encontrar:");
		languages.get("Spanish").put("gv_genres",			"Géneros del juego:");
		languages.get("Spanish").put("gv_tags",				"Tags de usuarios:");
		languages.get("Spanish").put("gv_rating",			"Puntaje de usuarios:");
		languages.get("Spanish").put("gv_show_spoiler",		"Mostrar spoiler");
		languages.get("Spanish").put("gv_hide_spoiler",		"Ocultar spoiler");

		languages.get("Spanish").put("m_chale",				"Chaaale... quería ver qué onda");
	}
}

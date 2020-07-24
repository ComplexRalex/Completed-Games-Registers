package util;

import java.util.HashMap;

/**
 * RALEX, DON'T FORGET TO ADD THE POSSIBILITY TO INCLUDE CUSTOM LANGUAGES FROM JSON FILES
 * (I mean, if the language you are looking for isn't included in the system, you can add
 * it just by including it in a JSON file in a specified folder location)
 */

public class Language{
	
	public static final String defaultCase = "English";
	public static final String[] available = {"English", "Spanish"};

	private static String currentLang;
	
	private final static HashMap<String,HashMap<String,String>> languages = new HashMap<>();
	
	public static void setCurrentLanguage(String lang){
		currentLang = lang;
	}

	public static void initialize(){
		setEnglish();
		setSpanish();
	}
	
	public static String loadMessage(String code){
		if(currentLang == null)
			return languages.get(defaultCase).get(code);
		return languages.get(currentLang).get(code);
	}
	
	private static void setEnglish(){
		languages.put("English",new HashMap<>());
		
		languages.get("English").put("p_title", 			"Completed-Games Registers");

		languages.get("English").put("g_accept",			"Accept");
		languages.get("English").put("g_apply",				"Apply");
		languages.get("English").put("g_cancel",			"Cancel");
		languages.get("English").put("g_return",			"Return");
		languages.get("English").put("g_change", 			"Change");
		languages.get("English").put("g_message",			"Message");
		languages.get("English").put("g_warning",			"Warning!");
		languages.get("English").put("g_success",			"Success!");
		languages.get("English").put("g_oops",				"Oops!");
		languages.get("English").put("g_done",				"The operation was successful");
		languages.get("English").put("g_unsaved",			"There are some unsaved changes, are you sure?");
		languages.get("English").put("g_went_wrong",		"Something went wrong!");
		languages.get("English").put("g_indev",				"This is not implemented yet!");
		languages.get("English").put("g_restricted",		"You shall not pass here!");
		languages.get("English").put("g_no_info",			"[No info provided]");
		languages.get("English").put("g_will_browse",		"The following page will open in your web browser:");
		languages.get("English").put("g_cant_browse",		"It was not possible to open the web browser...");
		
		languages.get("English").put("m_title",				"Welcome, ");
		languages.get("English").put("m_option_add",		"Add");
		languages.get("English").put("m_option_backup",		"Backup");
		languages.get("English").put("m_option_export",		"Export");
		languages.get("English").put("m_option_help",		"Help!");
		languages.get("English").put("m_option_view",		"View");
		languages.get("English").put("m_option_edit",		"Edit");
		languages.get("English").put("m_option_remove",		"Remove");
		languages.get("English").put("m_option_recent",		"Recently added");
		languages.get("English").put("m_text_recent",		"This register will be ordered in the next reset");
		languages.get("English").put("m_backedup",			"The registers were successfully backed up");
		languages.get("English").put("m_exported",			"The registers were successfully exported");
		languages.get("English").put("m_remove",			"Do you want to remove this register?");
		languages.get("English").put("m_config",			"Configuration");
		languages.get("English").put("m_about",				"About this program");
		languages.get("English").put("m_help",				"How to use it?");
		languages.get("English").put("m_nothing",			"...there's nothing here!");
		languages.get("English").put("m_closing",			"Do you really want to exit?");

		languages.get("English").put("cf_title",		 	"Configuration menu");
		languages.get("English").put("cf_general",			"General settings");
		languages.get("English").put("cf_change_user",		"Change username:");
		languages.get("English").put("cf_auto_backup_note",	"Note: Automatic backup only works when changes have been made in the current session (avoiding duplicate files).");
		languages.get("English").put("cf_auto_backup", 		"Auto-backup data before closing the program:");
		languages.get("English").put("cf_ask_on_close",		"Show a dialog before exiting the program:");
		languages.get("English").put("cf_theme", 			"Program theme (requires restart): ");
		languages.get("English").put("cf_lang", 			"Program language (requires restart):");
		languages.get("English").put("cf_delicate",			"Delicate options");
		languages.get("English").put("cf_warn",				"Warning! think twice before clicking some of these options! These don't require to be applied to take effect.");
		languages.get("English").put("cf_reset_cf", 		"Reset configuration to default");
		languages.get("English").put("cf_reset_save", 		"Reset registers and save.dat to default");
		languages.get("English").put("cf_reset_db_info", 	"Delete game info downloaded from RAWG database");
		languages.get("English").put("cf_reset_backups", 	"Delete currently stored backups");
		languages.get("English").put("cf_reset_exports", 	"Delete currently stored exports");
		languages.get("English").put("cf_wipeout", 			"Wipe out all data");
		languages.get("English").put("cf_sudden_close",		"Quit program suddenly, without validations (Not recommended)");
		languages.get("English").put("cf_yousure",			"Do you really want to do it?");
		languages.get("English").put("cf_success",			"The settings have been saved!");
		languages.get("English").put("cf_no_edit",			"There were no changes...");
		languages.get("English").put("cf_reset",			"A reset is requiered!");

		languages.get("English").put("ge_title",			"Game register");
		languages.get("English").put("ge_main_info", 		"Game main info");
		languages.get("English").put("ge_name",				"Game name:");
		languages.get("English").put("ge_download",			"Download game info from RAWG database");
		languages.get("English").put("ge_delete",			"Delete game info downloaded from RAWG database");
		languages.get("English").put("ge_user_info", 		"Game user info");
		languages.get("English").put("ge_year",				"Year of completion:");
		languages.get("English").put("ge_rating",			"Rating:");
		languages.get("English").put("ge_rate_0",			"I don't know!");
		languages.get("English").put("ge_rate_1",			"NOPE");
		languages.get("English").put("ge_rate_2",			"Neh");
		languages.get("English").put("ge_rate_3",			"Neutral");
		languages.get("English").put("ge_rate_4",			"Nice");
		languages.get("English").put("ge_rate_5",			"HELL YEAH!");
		languages.get("English").put("ge_comment",			"Comments about the game:");
		languages.get("English").put("ge_note",				"Some notes before playing or something...?:");
		languages.get("English").put("ge_spoiler",			"Wanna give a spoiler? (this won't be visible at first):");
		languages.get("English").put("ge_no_game",			"Mm... Which game are you registering?");
		languages.get("English").put("ge_exists",			"This game is already added!");
		languages.get("English").put("ge_success_download",	"The game info has been downloaded!");
		languages.get("English").put("ge_fail_download",	"Couldn't find coincidences...");
		languages.get("English").put("ge_success_delete",	"The game info has been deleted!");
		languages.get("English").put("ge_downloaded",		"You've already downloaded the game info");
		languages.get("English").put("ge_update_data",		"The game info will be re-downloaded");

		languages.get("English").put("gv_title",			"Game info viewer");
		languages.get("English").put("gv_db_info",			"Game database info");
		languages.get("English").put("gv_name",				"Game name:");
		languages.get("English").put("gv_developers",		"Game developers:");
		languages.get("English").put("gv_publishers",		"Game publishers:");
		languages.get("English").put("gv_release",			"Release date:");
		languages.get("English").put("gv_platforms",		"Platforms where you can find this game:");
		languages.get("English").put("gv_genres",			"Genres of the game:");
		languages.get("English").put("gv_tags",				"User tags:");
		languages.get("English").put("gv_rating",			"User score:");
		languages.get("English").put("gv_show_spoiler",		"Show spoiler");
		languages.get("English").put("gv_hide_spoiler",		"Hide spoiler");

		languages.get("English").put("h_title",				"Quick help");
		languages.get("English").put("h_what_is",			"What is this?");
		languages.get("English").put("h_what_is_text",		"This program will allow you to have a record of all the games you have completed so far so you can check them out or even add more the next time!");
		languages.get("English").put("h_register",			"What is a completed-game register?");
		languages.get("English").put("h_register_text",		"It's an object that contains the following fields:"+
															"<ul>"+
																"<li>Name of the game (non-optional field).</li>"+
																"<li>The year which you've beaten (completed) the game.</li>"+
																"<li>The rating measured by 6 different expressions such as \"Nice\" or \"Neh\".</li>"+
																"<li>A comment about the game.</li>"+
																"<li>A note about the game, for example, something you have to consider before playing it.</li>"+
																"<li>A spoiler about the game. This won't be visible at first when you view the register.</li>"+
															"</ul>"+
															"Besides, you can download game information such as an image, its description, its developers, and even it score! (Thanks, RAWG database).<br>"+
															"You can add a completed-game register clicking the \"<i>plus</i> button\" (explained below).");
		languages.get("English").put("h_options",			"What does each option do?");
		languages.get("English").put("h_options_add",		"If you want to add a completed-game register, click this button. It will display a panel where you can add the game name and more information you'd like to save.");
		languages.get("English").put("h_options_backup",	"You don't feel comfortable just by knowing that the program saves automatically the registers? Hey, it's okay! you can make a backup with this option!");
		languages.get("English").put("h_options_export",	"This option will allow you to export the current registers into a JSON file which will contain everything you entered! Except, of course, the game info downloaded from the database).");
		languages.get("English").put("h_options_help",		"Well... as you can see, this option will show you a panel explaining stuff shown in this program!");
		languages.get("English").put("h_options_view",		"This kind-of eye will show you the information you entered about that game and will include its online information (in case you downloaded it).");
		languages.get("English").put("h_options_edit",		"If you click this, you can modify anything about that register you created before. Displays the same panel as the one where you entered a new register, but now with the given information.");
		languages.get("English").put("h_options_remove",	"I present to you: the trash can!. If you think that was not a good choice adding that game register, you can remove it just by clicking this!");
		languages.get("English").put("h_report_issue",		"Did you see any bug?");
		languages.get("English").put("h_report_issue_text",	"Report an issue (GitHub page)");

		languages.get("English").put("m_chale",				"Duuude, that's not fair!");
	}
	
	private static void setSpanish(){
		languages.put("Spanish",new HashMap<>());

		languages.get("Spanish").put("p_title", 			"Registros de Juegos Completados");
		
		languages.get("Spanish").put("g_accept",			"Aceptar");
		languages.get("Spanish").put("g_apply",				"Aplicar");
		languages.get("Spanish").put("g_cancel",			"Cancelar");
		languages.get("Spanish").put("g_return",			"Regresar");
		languages.get("Spanish").put("g_change", 			"Cambiar");
		languages.get("Spanish").put("g_message",			"Mensaje");
		languages.get("Spanish").put("g_warning",			"¡Advertencia!");
		languages.get("Spanish").put("g_success",			"¡Éxito!");
		languages.get("Spanish").put("g_oops",				"¡Ups!");
		languages.get("Spanish").put("g_done",				"Se ha realizado la operación correctamente");
		languages.get("Spanish").put("g_unsaved",			"No se han guardado algunos cambios, ¿quieres continuar?");
		languages.get("Spanish").put("g_went_wrong",		"¡Algo salió mal!");
		languages.get("Spanish").put("g_indev",				"¡Aún no se ha implementado!");
		languages.get("Spanish").put("g_restricted",		"No puede pasar, ¡váyase pa' otro lado!");
		languages.get("Spanish").put("g_no_info",			"[No se obtuvo información]");
		languages.get("Spanish").put("g_will_browse",		"La siguiente página se abrirá en tu navegador web:");
		languages.get("Spanish").put("g_cant_browse",		"No fue posible abrir el navegador web...");

		languages.get("Spanish").put("m_title",				"Hola, ");
		languages.get("Spanish").put("m_option_add",		"Agregar");
		languages.get("Spanish").put("m_option_backup",		"Respaldar");
		languages.get("Spanish").put("m_option_export",		"Exportar");
		languages.get("Spanish").put("m_option_help",		"¡Ayuda!");
		languages.get("Spanish").put("m_option_view",		"Ver");
		languages.get("Spanish").put("m_option_edit",		"Editar");
		languages.get("Spanish").put("m_option_remove",		"Borrar");
		languages.get("Spanish").put("m_option_recent",		"Agregado recientemente");
		languages.get("Spanish").put("m_text_recent",		"Este registro se ordenará en el próximo reinicio.");
		languages.get("Spanish").put("m_backedup",			"Se han respaldado los registros correctamente");
		languages.get("Spanish").put("m_exported",			"Se han exportado los registros correctamente");
		languages.get("Spanish").put("m_remove",			"¿Quieres quitar este registro?");
		languages.get("Spanish").put("m_config",			"Configuración");
		languages.get("Spanish").put("m_about",				"Acerca del programa");
		languages.get("Spanish").put("m_help",				"¿Cómo se usa?");
		languages.get("Spanish").put("m_nothing",			"...esto se ve muy vacío!");
		languages.get("Spanish").put("m_closing",			"¿De verdad quieres salir?");

		languages.get("Spanish").put("cf_title", 			"Menú de configuración");
		languages.get("Spanish").put("cf_general",			"Ajustes generales");
		languages.get("Spanish").put("cf_change_user",		"Cambiar nombre de usuario:");
		languages.get("Spanish").put("cf_auto_backup_note",	"Nota: El respaldo automático solamente funciona cuando se ha realizado algún cambio en la sesión actual (con el fin de evitar archivos duplicados).");
		languages.get("Spanish").put("cf_auto_backup", 		"Respaldo automático antes de finalizar el programa:");
		languages.get("Spanish").put("cf_ask_on_close",		"Mostrar un diálogo antes de salir del programa:");
		languages.get("Spanish").put("cf_theme", 			"Tema del programa (requiere reinicio): ");
		languages.get("Spanish").put("cf_lang",				"Idioma del programa (requiere reinicio):");
		languages.get("Spanish").put("cf_delicate",			"Opciones delicadas");
		languages.get("Spanish").put("cf_warn",				"¡Cuidado! ¡piénsalo dos veces antes de presionar alguna de las opciones! No requieren ser aplicadas para hacer efecto.");
		languages.get("Spanish").put("cf_reset_cf",			"Restaurar configuración por defecto");
		languages.get("Spanish").put("cf_reset_save",		"Restaurar registros y save.dat por defecto");
		languages.get("Spanish").put("cf_reset_db_info", 	"Borrar información de juegos descargada de la base de datos de RAWG");
		languages.get("Spanish").put("cf_reset_backups", 	"Borrar backups creados hasta el momento");
		languages.get("Spanish").put("cf_reset_exports", 	"Borrar exportaciones creadas hasta el momento");
		languages.get("Spanish").put("cf_wipeout",			"Pulverizar todo los datos");
		languages.get("Spanish").put("cf_sudden_close",		"Cerrar el programa repentinamente, sin validaciones (No recomendado)");
		languages.get("Spanish").put("cf_yousure",			"¿De verdad quieres hacerlo?");
		languages.get("Spanish").put("cf_success",			"¡Se han guardado los cambios!");
		languages.get("Spanish").put("cf_no_edit",			"No hubo cambios...");
		languages.get("Spanish").put("cf_reset",			"¡Se requiere reiniciar!");

		languages.get("Spanish").put("ge_title",			"Registro de juego");
		languages.get("Spanish").put("ge_main_info", 		"Información del juego");
		languages.get("Spanish").put("ge_name",				"Nombre del juego:");
		languages.get("Spanish").put("ge_download",			"Descargar información del juego de la base de datos de RAWG");
		languages.get("Spanish").put("ge_delete",			"Borrar información del juego descargada de la base de datos de RAWG");
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
		languages.get("Spanish").put("ge_no_game",			"Mm... ¿Cuál juego estás registrando?");
		languages.get("Spanish").put("ge_exists",			"¡Este juego ya fue agregado!");
		languages.get("Spanish").put("ge_success_download",	"Se descargó la información del juego correctamente");
		languages.get("Spanish").put("ge_fail_download",	"No se encontraron coincidencias...");
		languages.get("Spanish").put("ge_success_delete",	"Se borró la información del juego correctamente");
		languages.get("Spanish").put("ge_downloaded",		"Ya haz descargado la información del juego");
		languages.get("Spanish").put("ge_update_data",		"La información del juego se volverá a descargar");

		languages.get("Spanish").put("gv_title",			"Datos del juego");
		languages.get("Spanish").put("gv_db_info",			"Información de la base de datos");
		languages.get("Spanish").put("gv_name",				"Nombre del juego:");
		languages.get("Spanish").put("gv_developers",		"Desarrolladores:");
		languages.get("Spanish").put("gv_publishers",		"Distribuidores:");
		languages.get("Spanish").put("gv_release",			"Fecha de lanzamiento:");
		languages.get("Spanish").put("gv_platforms",		"Plataformas en donde lo puedes encontrar:");
		languages.get("Spanish").put("gv_genres",			"Géneros del juego:");
		languages.get("Spanish").put("gv_tags",				"Tags de usuarios:");
		languages.get("Spanish").put("gv_rating",			"Puntaje de usuarios:");
		languages.get("Spanish").put("gv_show_spoiler",		"Mostrar spoiler");
		languages.get("Spanish").put("gv_hide_spoiler",		"Ocultar spoiler");

		languages.get("Spanish").put("h_title",				"Ayuda rápida");
		languages.get("Spanish").put("h_what_is",			"¿Qué es esto?");
		languages.get("Spanish").put("h_what_is_text",		"Este programa te permitirá tener un récord de todos los juegos que has completado hasta el momento para posteriormente poder visualizarlos o incluso ¡agregar más!");
		languages.get("Spanish").put("h_register",			"¿Qué es un registro de juego completado?");
		languages.get("Spanish").put("h_register_text",		"Es un objeto que contiene los siguientes campos:"+
															"<ul>"+
																"<li>Nombre del juego (campo no opcional).</li>"+
																"<li>El año en el que has vencido (o completado) el juego.</li>"+
																"<li>El puntaje medido por 6 diferentes expresiones, tales como \"Chido\" o \"Meh\".</li>"+
																"<li>Un comentario acerca del juego.</li>"+
																"<li>Una nota sobre el juego, por ejemplo, algo que debes considerar antes de jugar.</li>"+
																"<li>Un spoiler sobre el juego. Este no será visible al principio cuando veas el registro.</li>"+
															"</ul>"+
															"¡Además, podrás descargar información del juego como una imágen, su descripción, sus desarrolladores e incluso su puntaje! (Gracias a la base de datos de RAWG).<br>"+
															"Puedes agregar un registro de juego completado presionando el \"botón <i>más</i>\" (explicado abajo).");
		languages.get("Spanish").put("h_options",			"¿Qué hace cada opción?");
		languages.get("Spanish").put("h_options_add",		"Si quieres agregar un registro de juego completado, presiona este botón. Te mostrará un panel en donde podrás agregar el nombre del juego y más información que quisieras guardar.");
		languages.get("Spanish").put("h_options_backup",	"¿No es suficiente para tí saber que el programa guarda los registros automáticamente? Si gustas, ¡puedes hacer una copia de seguridad con esta opción!");
		languages.get("Spanish").put("h_options_export",	"Esta opción te permitirá exportar los registros actuales a un archivo JSON que contendrá toda la información que ingresaste, excepto la que descargaste de la base de datos.");
		languages.get("Spanish").put("h_options_help",		"Bueno... como puedes ver, ¡esta opción te mostrará un panel explicando las cosas mostradas en este programa!");
		languages.get("Spanish").put("h_options_view",		"Esta especie de... ojo, te mostrará la información que ingresaste sobre dicho juego e incluirá su información online (en caso de haberla descargado).");
		languages.get("Spanish").put("h_options_edit",		"Si haces click aquí, podrás modificar cualquier cosa sobre ese registro que agregaste. Muestra el mismo panel en donde sueles agregar registros, pero esta vez con la información previa.");
		languages.get("Spanish").put("h_options_remove",	"Te presento: ¡el bote de basura!. Si crees que no fue una buena idea agregar ese registro, ¡lo puedes borrar con solo dar click a esto!");
		languages.get("Spanish").put("h_report_issue",		"¿Viste algún error?");
		languages.get("Spanish").put("h_report_issue_text",	"Reportar problema (página GitHub)");

		languages.get("Spanish").put("m_chale",				"Chaaale... quería ver qué onda");
	}
}

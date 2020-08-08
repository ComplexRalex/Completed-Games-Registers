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

package util;

import java.util.HashMap;

// RALEX, DON'T FORGET TO ADD THE POSSIBILITY TO INCLUDE CUSTOM LANGUAGES FROM JSON FILES
// (I mean, if the language you are looking for isn't included in the system, you can add
// it just by including it in a JSON file in a specified folder location)

/**
 * <h3>Language utility class.</h3>
 * This class is used to obtain the text that will be displayed
 * in the GUI of the software corresponding to the selected
 * language ({@link #currentLang}).
 * <p>
 * Currently, the software has to languages:
 * <ul>
 * <li><b>English</b> (default)
 * <li><b>Spanish (MX)</b>
 * </ul>
 * In future updates will be added the possibility to create
 * and use <i>custom languages</i>.
 * 
 * @author Alejandro Batres
 */
public class Language{
	
	/**
	 * Default language.
	 */
	public static final String defaultCase = "English";

	/**
	 * String array of available languages.
	 */
	public static final String[] available = {"English", "Spanish"};

	/**
	 * Variable which controls the current language in the
	 * session.
	 */
	private static String currentLang;
	
	/**
	 * <b>HashMap</b> that contains a pair of <b>(String,HashMap)</b>,
	 * which its HashMap has a pair of <b>(String,String)</b>.
	 * <p>
	 * In other words, it's an instance of:
	 * {@code HashMap<String,HashMap<String,String>>}.
	 * <p>
	 * The first pair is used as (Language,Dialogs). So, the Dialogs
	 * has pairs of (Context,Value).
	 * For example, if the user wants to get the "Accept" text in
	 * Spanish, then the variable can be used like this:
	 * {@code languages.get("Spanish").get("g_accept")}.
	 */
	private final static HashMap<String,HashMap<String,String>> languages = createHashMap();
	
	/**
	 * Returns the name of the current language.
	 * 
	 * @return {@link #currentLang}'s value.
	 */
	public final static String getCurrentLanguage(){
		return currentLang;
	}
	
	/**
	 * Sets the current language.
	 * 
	 * @param lang New {@link #currentLang}'s value.
	 */
	public final static void setCurrentLanguage(String lang){
		currentLang = lang;
	}

	/**
	 * Initializes the {@link #languages} variable. To do this, the
	 * {@link #initialize(HashMap)} method will set the available
	 * languages in a new HashMap and then will return it.
	 * 
	 * @see #initialize(HashMap)
	 * @return HashMap with set languages.
	 */
	private static final HashMap<String,HashMap<String,String>> createHashMap(){
		HashMap<String,HashMap<String,String>> map = new HashMap<>();
		initialize(map);

		return map;
	}

	/**
	 * Call the setters of every available language in the
	 * software.
	 * 
	 * @param map HashMap that will contain the available
	 * languages
	 * @see #setEnglish(HashMap)
	 * @see #setSpanish(HashMap)
	 */
	private static void initialize(HashMap<String,HashMap<String,String>> map){
		setEnglish(map);
		setSpanish(map);
	}
	
	/**
	 * Retrives the String value of the provided key/code. To
	 * achieve this, this method is using the {@link #currentLang}
	 * value.
	 * <p>
	 * If the value of {@link #currentLang} is {@code null}, it
	 * will use {@link #defaultCase} instead.
	 * 
	 * @param code String key
	 * @return The String value of the received key.
	 */
	public static String loadMessage(String code){
		if(currentLang == null)
			return languages.get(defaultCase).get(code);
		return languages.get(currentLang).get(code);
	}
	
	/**
	 * Initializes every key/code with its respective dialog
	 * into the English language.
	 * 
	 * @param map HashMap that will contain this language.
	 */
	private static void setEnglish(HashMap<String,HashMap<String,String>> map){
		map.put("English",new HashMap<>());
		
		map.get("English").put("g_accept",				"Accept");
		map.get("English").put("g_apply",				"Apply");
		map.get("English").put("g_cancel",				"Cancel");
		map.get("English").put("g_return",				"Return");
		map.get("English").put("g_change", 				"Modify");
		map.get("English").put("g_message",				"Message");
		map.get("English").put("g_warning",				"Warning!");
		map.get("English").put("g_success",				"Success!");
		map.get("English").put("g_oops",				"Oops!");
		map.get("English").put("g_done",				"The operation was successful");
		map.get("English").put("g_unsaved",				"There are some unsaved changes, are you sure?");
		map.get("English").put("g_went_wrong",			"Something went wrong!");
		map.get("English").put("g_indev",				"This is not implemented yet!");
		map.get("English").put("g_restricted",			"You shall not pass here!");
		map.get("English").put("g_no_info",				"[No info provided]");
		map.get("English").put("g_will_browse",			"The following page will open in your web browser:");
		map.get("English").put("g_cant_browse",			"It was not possible to open the web browser...");
		
		map.get("English").put("m_title",				"Welcome, ");
		map.get("English").put("m_option_add",			"Add");
		map.get("English").put("m_option_backup",		"Backup");
		map.get("English").put("m_option_export",		"Export");
		map.get("English").put("m_option_help",			"Help!");
		map.get("English").put("m_option_view",			"View");
		map.get("English").put("m_option_edit",			"Edit");
		map.get("English").put("m_option_remove",		"Remove");
		map.get("English").put("m_option_recent",		"Recently added");
		map.get("English").put("m_text_recent",			"This register will be ordered in the next reset");
		map.get("English").put("m_backedup",			"The registers were successfully backed up");
		map.get("English").put("m_exported",			"The registers were successfully exported");
		map.get("English").put("m_needed_font",			"To get a better experience, download the \"Open Sans\" font!");
		map.get("English").put("m_remove",				"Do you want to remove this register?");
		map.get("English").put("m_config",				"Configuration");
		map.get("English").put("m_about",				"About this program");
		map.get("English").put("m_help",				"How to use it?");
		map.get("English").put("m_nothing",				"...there's nothing here!");
		map.get("English").put("m_busy_frame",			"You can't close the window right now");
		map.get("English").put("m_closing",				"Do you really want to exit?");

		map.get("English").put("cf_title",		 		"Configuration menu");
		map.get("English").put("cf_general",			"General settings");
		map.get("English").put("cf_change_user",		"Change username:");
		map.get("English").put("cf_auto_backup_note",	"Note: Automatic backup only works when changes have been made in the current session (avoiding duplicate files).");
		map.get("English").put("cf_auto_backup", 		"Auto-backup data before closing the program:");
		map.get("English").put("cf_ask_on_close",		"Show a dialog before exiting the program:");
		map.get("English").put("cf_theme", 				"Program theme (requires restart): ");
		map.get("English").put("cf_lang", 				"Program language (requires restart):");
		map.get("English").put("cf_advanced",			"Advanced options");
		map.get("English").put("cf_advanced_text",		"Note that these timeout values will be applied in two cases:"+
														"<ul>"+
															"<li>When a game is being searched by the given name</li>"+
															"<li>When downloading the information after getting its ID</li>"+
														"</ul>");
		map.get("English").put("cf_connection_timeout",	"Connection timeout: ");
		map.get("English").put("cf_read_timeout",		"Read timeout: ");
		map.get("English").put("cf_delicate",			"Delicate options");
		map.get("English").put("cf_warn",				"Warning! think twice before clicking some of these options! These don't require to be applied to take effect.");
		map.get("English").put("cf_reset_cf", 			"Reset configuration to default");
		map.get("English").put("cf_reset_save", 		"Reset registers and save.dat to default");
		map.get("English").put("cf_reset_db_info", 		"Delete game info downloaded from RAWG database");
		map.get("English").put("cf_reset_backups", 		"Delete currently stored backups");
		map.get("English").put("cf_reset_exports", 		"Delete currently stored exports");
		map.get("English").put("cf_reset_logs", 		"Delete currently stored logs");
		map.get("English").put("cf_wipeout", 			"Wipe out all data");
		map.get("English").put("cf_sudden_close",		"Quit program suddenly, without validations (Not recommended)");
		map.get("English").put("cf_yousure",			"Do you really want to do it?");
		map.get("English").put("cf_success",			"The settings have been saved!");
		map.get("English").put("cf_no_edit",			"There were no changes...");
		map.get("English").put("cf_reset",				"A reset is requiered!");

		map.get("English").put("ge_title",				"Game register");
		map.get("English").put("ge_main_info", 			"Game main info");
		map.get("English").put("ge_name",				"Game name:");
		map.get("English").put("ge_download",			"Download game info from RAWG database");
		map.get("English").put("ge_delete",				"Delete game info downloaded from RAWG database");
		map.get("English").put("ge_user_info", 			"Game user info");
		map.get("English").put("ge_year",				"Year of completion:");
		map.get("English").put("ge_rating",				"Rating:");
		map.get("English").put("ge_rate_0",				"I don't know!");
		map.get("English").put("ge_rate_1",				"NOPE");
		map.get("English").put("ge_rate_2",				"Neh");
		map.get("English").put("ge_rate_3",				"Neutral");
		map.get("English").put("ge_rate_4",				"Nice");
		map.get("English").put("ge_rate_5",				"HELL YEAH!");
		map.get("English").put("ge_comment",			"Comments about the game:");
		map.get("English").put("ge_note",				"Some notes before playing or something...?:");
		map.get("English").put("ge_spoiler",			"Wanna give a spoiler? (this won't be visible at first):");
		map.get("English").put("ge_no_game",			"Mm... Which game are you registering?");
		map.get("English").put("ge_exists",				"This game is already added!");
		map.get("English").put("ge_success_download",	"The game info has been downloaded!");
		map.get("English").put("ge_fail_download",		"Couldn't find coincidences...");
		map.get("English").put("ge_success_delete",		"The game info has been deleted!");
		map.get("English").put("ge_downloaded",			"You've already downloaded the game info");
		map.get("English").put("ge_downloading",		"Downloading the game information will take a few seconds");
		map.get("English").put("ge_update_data",		"The game info will be re-downloaded");

		map.get("English").put("gv_title",				"Game info viewer");
		map.get("English").put("gv_db_info",			"Game database info");
		map.get("English").put("gv_name",				"Game name:");
		map.get("English").put("gv_developers",			"Game developers:");
		map.get("English").put("gv_publishers",			"Game publishers:");
		map.get("English").put("gv_release",			"Release date:");
		map.get("English").put("gv_platforms",			"Platforms where you can find this game:");
		map.get("English").put("gv_genres",				"Genres of the game:");
		map.get("English").put("gv_tags",				"User tags:");
		map.get("English").put("gv_rating",				"User score:");
		map.get("English").put("gv_more_details",		"Go to RAWG's website to see more details");
		map.get("English").put("gv_show_spoiler",		"Show spoiler");
		map.get("English").put("gv_hide_spoiler",		"Hide spoiler");

		map.get("English").put("h_title",				"Quick help");
		map.get("English").put("h_what_is",				"What is this?");
		map.get("English").put("h_what_is_text",		"This program will allow you to have a record of all the games you have completed so far so you can check them out or even add more the next time!");
		map.get("English").put("h_register",			"What is a completed-game register?");
		map.get("English").put("h_register_text",		"It's an object that contains the following fields:"+
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
		map.get("English").put("h_options",				"What does each option do?");
		map.get("English").put("h_options_add",			"If you want to add a completed-game register, click this button. It will display a panel where you can add the game name and more information you'd like to save.");
		map.get("English").put("h_options_backup",		"You don't feel comfortable just by knowing that the program saves automatically the registers? Hey, it's okay! you can make a backup with this option!");
		map.get("English").put("h_options_export",		"This option will allow you to export the current registers into a JSON file which will contain everything you entered! Except, of course, the game info downloaded from the database).");
		map.get("English").put("h_options_help",		"Well... as you can see, this option will show you a panel explaining stuff shown in this program!");
		map.get("English").put("h_options_view",		"This kind-of eye will show you the information you entered about that game and will include its online information (in case you downloaded it).");
		map.get("English").put("h_options_edit",		"If you click this, you can modify anything about that register you created before. Displays the same panel as the one where you entered a new register, but now with the given information.");
		map.get("English").put("h_options_remove",		"I present to you: the trash can!. If you think that was not a good choice adding that game register, you can remove it just by clicking this!");
		map.get("English").put("h_report_issue",		"Did you see any bug?");
		map.get("English").put("h_report_issue_text",	"Report an issue (GitHub page)");

		map.get("English").put("ac_brief",				"Keep a record of all the games you have completed so far!");
		map.get("English").put("ac_title",				"About & Credits");
		map.get("English").put("ac_details",			"Details");
		map.get("English").put("ac_version",			"Version");
		map.get("English").put("ac_written_in",			"Written in");
		map.get("English").put("ac_java_version",		"Required Java version");
		map.get("English").put("ac_credits",			"Credits");
		map.get("English").put("ac_developed",			"Created, developed and designed by");
		map.get("English").put("ac_library",			"Used libraries");
		map.get("English").put("ac_api",				"Used APIs");
		map.get("English").put("ac_licenses_terms",		"Licenses and terms of use");
		map.get("English").put("ac_license_show",		"Show license");
		map.get("English").put("ac_license_hide",		"Hide license");
		map.get("English").put("ac_terms_show",			"Show terms of use");
		map.get("English").put("ac_terms_hide",			"Hide terms of use");
		map.get("English").put("ac_links",				"Links");
		map.get("English").put("ac_twitter",			"Twitter profile");
		map.get("English").put("ac_source_code",		"Source code");
		map.get("English").put("ac_website",			"Website");

		map.get("English").put("m_chale",				"Duuude, that's not fair!");
	}
	
	/**
	 * Initializes every key/code with its respective dialog
	 * into the Spanish language.
	 * 
	 * @param map HashMap that will contain this language.
	 */
	private static void setSpanish(HashMap<String,HashMap<String,String>> map){
		map.put("Spanish",new HashMap<>());

		map.get("Spanish").put("g_accept",				"Aceptar");
		map.get("Spanish").put("g_apply",				"Aplicar");
		map.get("Spanish").put("g_cancel",				"Cancelar");
		map.get("Spanish").put("g_return",				"Regresar");
		map.get("Spanish").put("g_change", 				"Modificar");
		map.get("Spanish").put("g_message",				"Mensaje");
		map.get("Spanish").put("g_warning",				"¡Advertencia!");
		map.get("Spanish").put("g_success",				"¡Éxito!");
		map.get("Spanish").put("g_oops",				"¡Ups!");
		map.get("Spanish").put("g_done",				"Se ha realizado la operación correctamente");
		map.get("Spanish").put("g_unsaved",				"No se han guardado algunos cambios, ¿quieres continuar?");
		map.get("Spanish").put("g_went_wrong",			"¡Algo salió mal!");
		map.get("Spanish").put("g_indev",				"¡Aún no se ha implementado!");
		map.get("Spanish").put("g_restricted",			"No puede pasar, ¡váyase pa' otro lado!");
		map.get("Spanish").put("g_no_info",				"[No se obtuvo información]");
		map.get("Spanish").put("g_will_browse",			"La siguiente página se abrirá en tu navegador web:");
		map.get("Spanish").put("g_cant_browse",			"No fue posible abrir el navegador web...");

		map.get("Spanish").put("m_title",				"Hola, ");
		map.get("Spanish").put("m_option_add",			"Agregar");
		map.get("Spanish").put("m_option_backup",		"Respaldar");
		map.get("Spanish").put("m_option_export",		"Exportar");
		map.get("Spanish").put("m_option_help",			"¡Ayuda!");
		map.get("Spanish").put("m_option_view",			"Ver");
		map.get("Spanish").put("m_option_edit",			"Editar");
		map.get("Spanish").put("m_option_remove",		"Borrar");
		map.get("Spanish").put("m_option_recent",		"Agregado recientemente");
		map.get("Spanish").put("m_text_recent",			"Este registro se ordenará en el próximo reinicio.");
		map.get("Spanish").put("m_backedup",			"Se han respaldado los registros correctamente");
		map.get("Spanish").put("m_exported",			"Se han exportado los registros correctamente");
		map.get("Spanish").put("m_needed_font",			"¡Para tener una mejor experiencia, descarga la fuente \"Open Sans\"!");
		map.get("Spanish").put("m_remove",				"¿Quieres quitar este registro?");
		map.get("Spanish").put("m_config",				"Configuración");
		map.get("Spanish").put("m_about",				"Acerca del programa");
		map.get("Spanish").put("m_help",				"¿Cómo se usa?");
		map.get("Spanish").put("m_nothing",				"...esto se ve muy vacío!");
		map.get("Spanish").put("m_busy_frame",			"No puedes cerrar la ventana en este momento");
		map.get("Spanish").put("m_closing",				"¿De verdad quieres salir?");

		map.get("Spanish").put("cf_title", 				"Menú de configuración");
		map.get("Spanish").put("cf_general",			"Ajustes generales");
		map.get("Spanish").put("cf_change_user",		"Cambiar nombre de usuario:");
		map.get("Spanish").put("cf_auto_backup_note",	"Nota: El respaldo automático solamente funciona cuando se ha realizado algún cambio en la sesión actual (con el fin de evitar archivos duplicados).");
		map.get("Spanish").put("cf_auto_backup", 		"Respaldo automático antes de finalizar el programa:");
		map.get("Spanish").put("cf_ask_on_close",		"Mostrar un diálogo antes de salir del programa:");
		map.get("Spanish").put("cf_theme", 				"Tema del programa (requiere reinicio): ");
		map.get("Spanish").put("cf_lang",				"Idioma del programa (requiere reinicio):");
		map.get("Spanish").put("cf_advanced",			"Opciones avanzadas");
		map.get("Spanish").put("cf_advanced_text",		"Toma en cuenta que los tiempos de espera serán aplicados en dos casos:"+
														"<ul>"+
															"<li>Cuando un juego está siendo buscado por su nombre</li>"+
															"<li>Al descargar la información después de obtener su ID</li>"+
														"</ul>");
		map.get("Spanish").put("cf_connection_timeout",	"Tiempo de espera de conexión: ");
		map.get("Spanish").put("cf_read_timeout",		"Tiempo de espera de lectura: ");
		map.get("Spanish").put("cf_delicate",			"Opciones delicadas");
		map.get("Spanish").put("cf_warn",				"¡Cuidado! ¡piénsalo dos veces antes de presionar alguna de las opciones! No requieren ser aplicadas para hacer efecto.");
		map.get("Spanish").put("cf_reset_cf",			"Restaurar configuración por defecto");
		map.get("Spanish").put("cf_reset_save",			"Restaurar registros y save.dat por defecto");
		map.get("Spanish").put("cf_reset_db_info", 		"Borrar información de juegos descargada de la base de datos de RAWG");
		map.get("Spanish").put("cf_reset_backups", 		"Borrar backups creados hasta el momento");
		map.get("Spanish").put("cf_reset_exports", 		"Borrar exportaciones creadas hasta el momento");
		map.get("Spanish").put("cf_reset_logs", 		"Borrar logs creados hasta el momento");
		map.get("Spanish").put("cf_wipeout",			"Pulverizar todo los datos");
		map.get("Spanish").put("cf_sudden_close",		"Cerrar el programa repentinamente, sin validaciones (No recomendado)");
		map.get("Spanish").put("cf_yousure",			"¿De verdad quieres hacerlo?");
		map.get("Spanish").put("cf_success",			"¡Se han guardado los cambios!");
		map.get("Spanish").put("cf_no_edit",			"No hubo cambios...");
		map.get("Spanish").put("cf_reset",				"¡Se requiere reiniciar!");

		map.get("Spanish").put("ge_title",				"Registro de juego");
		map.get("Spanish").put("ge_main_info", 			"Información del juego");
		map.get("Spanish").put("ge_name",				"Nombre del juego:");
		map.get("Spanish").put("ge_download",			"Descargar información del juego de la base de datos de RAWG");
		map.get("Spanish").put("ge_delete",				"Borrar información del juego descargada de la base de datos de RAWG");
		map.get("Spanish").put("ge_user_info", 			"Información del usuario");
		map.get("Spanish").put("ge_year",				"Año de completación:");
		map.get("Spanish").put("ge_rating",				"Puntaje:");
		map.get("Spanish").put("ge_rate_0",				"¡Ni idea!");
		map.get("Spanish").put("ge_rate_1",				"NOPE");
		map.get("Spanish").put("ge_rate_2",				"Meh");
		map.get("Spanish").put("ge_rate_3",				"Normal");
		map.get("Spanish").put("ge_rate_4",				"Chido");
		map.get("Spanish").put("ge_rate_5",				"¡PERFECTO!");
		map.get("Spanish").put("ge_comment",			"Comentarios sobre el juego:");
		map.get("Spanish").put("ge_note",				"¿Algunas notas antes de jugar o algo...?:");
		map.get("Spanish").put("ge_spoiler",			"¿Quieres dar un spoiler? (no será visible al principio):");
		map.get("Spanish").put("ge_no_game",			"Mm... ¿Cuál juego estás registrando?");
		map.get("Spanish").put("ge_exists",				"¡Este juego ya fue agregado!");
		map.get("Spanish").put("ge_success_download",	"Se descargó la información del juego correctamente");
		map.get("Spanish").put("ge_fail_download",		"No se encontraron coincidencias...");
		map.get("Spanish").put("ge_success_delete",		"Se borró la información del juego correctamente");
		map.get("Spanish").put("ge_downloaded",			"Ya haz descargado la información del juego");
		map.get("Spanish").put("ge_downloading",		"Descargar información sobre el juego tomará unos segundos");
		map.get("Spanish").put("ge_update_data",		"La información del juego se volverá a descargar");

		map.get("Spanish").put("gv_title",				"Datos del juego");
		map.get("Spanish").put("gv_db_info",			"Información de la base de datos");
		map.get("Spanish").put("gv_name",				"Nombre del juego:");
		map.get("Spanish").put("gv_developers",			"Desarrolladores:");
		map.get("Spanish").put("gv_publishers",			"Distribuidores:");
		map.get("Spanish").put("gv_release",			"Fecha de lanzamiento:");
		map.get("Spanish").put("gv_platforms",			"Plataformas en donde lo puedes encontrar:");
		map.get("Spanish").put("gv_genres",				"Géneros del juego:");
		map.get("Spanish").put("gv_tags",				"Tags de usuarios:");
		map.get("Spanish").put("gv_rating",				"Puntaje de usuarios:");
		map.get("Spanish").put("gv_more_details",		"Ir al sitio web de RAWG para ver más información");
		map.get("Spanish").put("gv_show_spoiler",		"Mostrar spoiler");
		map.get("Spanish").put("gv_hide_spoiler",		"Ocultar spoiler");

		map.get("Spanish").put("h_title",				"Ayuda rápida");
		map.get("Spanish").put("h_what_is",				"¿Qué es esto?");
		map.get("Spanish").put("h_what_is_text",		"Este programa te permitirá tener un récord de todos los juegos que has completado hasta el momento para posteriormente poder visualizarlos o incluso ¡agregar más!");
		map.get("Spanish").put("h_register",			"¿Qué es un registro de juego completado?");
		map.get("Spanish").put("h_register_text",		"Es un objeto que contiene los siguientes campos:"+
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
		map.get("Spanish").put("h_options",				"¿Qué hace cada opción?");
		map.get("Spanish").put("h_options_add",			"Si quieres agregar un registro de juego completado, presiona este botón. Te mostrará un panel en donde podrás agregar el nombre del juego y más información que quisieras guardar.");
		map.get("Spanish").put("h_options_backup",		"¿No es suficiente para tí saber que el programa guarda los registros automáticamente? Si gustas, ¡puedes hacer una copia de seguridad con esta opción!");
		map.get("Spanish").put("h_options_export",		"Esta opción te permitirá exportar los registros actuales a un archivo JSON que contendrá toda la información que ingresaste, excepto la que descargaste de la base de datos.");
		map.get("Spanish").put("h_options_help",		"Bueno... como puedes ver, ¡esta opción te mostrará un panel explicando las cosas mostradas en este programa!");
		map.get("Spanish").put("h_options_view",		"Esta especie de... ojo, te mostrará la información que ingresaste sobre dicho juego e incluirá su información online (en caso de haberla descargado).");
		map.get("Spanish").put("h_options_edit",		"Si haces click aquí, podrás modificar cualquier cosa sobre ese registro que agregaste. Muestra el mismo panel en donde sueles agregar registros, pero esta vez con la información previa.");
		map.get("Spanish").put("h_options_remove",		"Te presento: ¡el bote de basura!. Si crees que no fue una buena idea agregar ese registro, ¡lo puedes borrar con solo dar click a esto!");
		map.get("Spanish").put("h_report_issue",		"¿Viste algún error?");
		map.get("Spanish").put("h_report_issue_text",	"Reportar problema (página GitHub)");

		map.get("Spanish").put("ac_brief",				"¡Crea un récord de todos los juegos que has completado hasta el momento!");
		map.get("Spanish").put("ac_title",				"Acerca del programa y créditos");
		map.get("Spanish").put("ac_details",			"Detalles");
		map.get("Spanish").put("ac_version",			"Versión");
		map.get("Spanish").put("ac_written_in",			"Escrito en");
		map.get("Spanish").put("ac_java_version",		"Versión de java requerida");
		map.get("Spanish").put("ac_credits",			"Créditos");
		map.get("Spanish").put("ac_developed",			"Creado, desarrollado y diseñado por");
		map.get("Spanish").put("ac_library",			"Librerías utilizadas");
		map.get("Spanish").put("ac_api",				"APIs utilizadas");
		map.get("Spanish").put("ac_licenses_terms",		"Licencias y términos de uso");
		map.get("Spanish").put("ac_license_show",		"Mostrar licencia");
		map.get("Spanish").put("ac_license_hide",		"Ocultar licencia");
		map.get("Spanish").put("ac_terms_show",			"Mostrar términos de uso");
		map.get("Spanish").put("ac_terms_hide",			"Ocultar términos de uso");
		map.get("Spanish").put("ac_links",				"Enlaces");
		map.get("Spanish").put("ac_twitter",			"Perfil en Twitter");
		map.get("Spanish").put("ac_source_code",		"Código fuente");
		map.get("Spanish").put("ac_website",			"Sitio web");

		map.get("Spanish").put("m_chale",				"Chaaale... quería ver qué onda");
	}
}

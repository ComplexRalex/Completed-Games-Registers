package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import exception.*;

/**
 * THIS IS NOT FUNCTIONAL YET, MUST CHANGE SOME LINES
 */
public class HTMLGameRegister {
	private static PrintWriter fileOutput;
	private static boolean isOpen = false;
	
	// First step: opens the file that will contain the HTML page
	public static void openFileOutput(String path) throws CouldNotCreateHTMLFileException{ //HTMLNotFoundException
		if(!isOpen) {
			try{
				fileOutput = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			}
			catch(IOException e){
				throw new CouldNotCreateHTMLFileException();
			}
			isOpen = true;
		}
	}
	
	// Second step: writes the "first part" of the site which contains the CSS styles and some explanations
	public static void writeTemplatePage(String path) throws HTMLTemplateNotFoundException{ 
		if(isOpen){
			try{
				BufferedReader in = new BufferedReader(new FileReader(path));
				String tempString;
				
				while((tempString = in.readLine()) != null){ fileOutput.println(tempString); }
				
				in.close(); 
				fileOutput.println("");
			}
			catch(FileNotFoundException e){
				throw new HTMLTemplateNotFoundException();
			}
			catch(IOException e){
				throw new HTMLTemplateNotFoundException();
			}
		}
	}
	
	// Third step: writes the index of the games (lol)
	public static void writeGameIndexPage(ArrayList<GameStat> gameStats){
		if(isOpen){
			String trimmedTitle;
			boolean firstElement = true;
			char cLast = '\0', cActual;
			// Start container of the index
			fileOutput.println("<div class=\"index\" style=\"text-align: justify;\">");
			// Title of index
			fileOutput.println("<p style=\"text-align: center; margin: 3px;\">&Iacute;ndice</p>");
			// Adding elements
			for(GameStat gs: gameStats){
				trimmedTitle = gs.getGame().toLowerCase().trim();	// Just for practical and "mamalón" uses
				cActual = gs.getGame().charAt(0);
				
				if(cActual >= '0' && cActual <= '9'){				// Case which the title starts with a number
					if(firstElement){
						fileOutput.print("<b>#</b>: ");
						firstElement = false;
					}
					else fileOutput.print(" &#8212; ");
					fileOutput.print("<a href=\"#"+trimmedTitle+"\"><i>"+gs.getGame()+"</i></a>");
				}
				else{												// Case which the title starts with a char (I hope)
					if(cActual != cLast){
						if(firstElement) firstElement = false;
						else fileOutput.println(".<br>");			// Shows an "enter" between the elements
						fileOutput.print("<b>"+String.valueOf(cActual).toUpperCase()+"</b>: ");
						fileOutput.print("<a href=\"#"+trimmedTitle+"\"><i>"+gs.getGame()+"</i></a>");
					}
					else{
						fileOutput.print(" &#8212; ");
						fileOutput.print("<a href=\"#"+trimmedTitle+"\"><i>"+gs.getGame()+"</i></a>");
					}
				}
				cLast = cActual;
			}
			fileOutput.println(".<br>");
		}
	}
	
	// Fourth step: finally writes the content of each GameStat in the array
	public static void writeGameListPage(ArrayList<GameStat> gameStats){
		if(isOpen){	
			String trimmedTitle;
			for(GameStat gs: gameStats){
				trimmedTitle = gs.getGame().toLowerCase().trim(); // Just for practical and "mamalón" uses, again
				// Start of the table
				fileOutput.println("<table class=\"estiloco\" id=\""+trimmedTitle+"\">");
				// Title of the game
				fileOutput.println("<tr><th colspan=\"2\" class=\"title\">"+gs.getGame()+"</th></tr>");
				// Comment by the user
				fileOutput.println("<tr><td colspan=\"2\"class=\"review_spoiler\">"+gs.getComment()+"</td></tr>");
				// Spoiler about the game (hide by default) by the user, if wanted
				if(gs.getSpoiler() == "*")
					fileOutput.println("<tr><td colspan=\"2\" class=\"review_spoiler\"><center><button onclick=\"toggle_visibility(\'spoiler_"+trimmedTitle+"\')\">Mostrar/Ocultar Spoiler</button></center><span id=\"spoiler_"+trimmedTitle+"\" style=\"display:none; margin-top:5px;\">"+gs.getSpoiler()+"</span></td></tr>"); 
				// Note by the user, if wanted
				if(gs.getNote() == "*")
					fileOutput.println("<tr><td colspan=\"2\" class=\"note\">"+gs.getNote()+"</td></tr>");
				// Year of completion
				fileOutput.println("<tr><td class=\"mini\">Completado en el año: <b>"+gs.getYear()+"</b></td>");
				// End of the table
				fileOutput.println("</table></br>\n");
			}
		}
	}
	
	// Fifth and last step: closes the output
	public static void closeFileOutput(){
		if(isOpen){
			fileOutput.close();
			isOpen = false;
		}
	}
}

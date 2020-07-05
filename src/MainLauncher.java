import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.parser.ParseException;

import controller.MainController;
import model.GameStat;

/**
 * <h1>Registro de juegos completados en Java</h1>
 * 
 * La idea principal con este proyecto era la de registrar juegos
 * y descargar sus datos automaticamente (incluyendo el uso de
 * imagenes dentro del programa y etc). Sin embargo, hasta el
 * momento no tengo los conocimientos suficientes para cumplir con
 * mis excpectativas, asi que lo hare de otra forma!
 * <p>
 * Esta es la primera version, la cual solamente es una "mejora
 * grafica" respecto a la version hecha en C
 * 
 * @author Alejandro Batres
 **/

public class MainLauncher{
	
	public static void initializer(){
		// Anti-aliasing properties
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext","true");
	}
	public static void main(String[] args){

		// Initializing important stuff
		initializer();
		
		// Initialize MainController
		new MainController();

		// test();
	}

	// Testing the image and info download
	public static void test(){
		GameStat gs = new GameStat(
			"Terraria",
			2019,
			5,
			"Esta muy bueno, la verdad.",
			null,
			null
		);

		try {
			gs.downloadGameInfo();
			System.out.println(gs.downloadGameImage());
		} catch (IOException | ParseException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

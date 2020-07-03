import controller.MainController;

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
	}
	
}

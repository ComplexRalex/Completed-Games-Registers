import controller.MainController;

/**
 * <h1>Completed-Games Registers</h1>
 * 
 * // add description
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

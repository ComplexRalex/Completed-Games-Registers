import model.*;
import util.Language;
import util.Advice;
import util.Colour;
import view.*;
import controller.*;
import exception.CouldNotLoadFileException;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

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
		Language.initialize();
	}
	public static void main(String[] args){

		// Initializing important stuff
		initializer();
		
		//Anti-aliasing properties
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		
		Configuration configModel = new Configuration();
		try {
			configModel.loadConfiguration();
		} catch (FileNotFoundException e) {
			Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong")+": ", e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		} catch (ClassNotFoundException e) {
			Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong")+": ", e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		} catch (CouldNotLoadFileException e) {
			Advice.showTextAreaAdvice(null, Language.loadMessage("g_oops"), Language.loadMessage("g_wentwrong")+": ", e.toString(), Language.loadMessage("g_accept"), Colour.getPrimaryColor());
		}

		ConfigurationPanel configView = new ConfigurationPanel();
		MainWindow view = new MainWindow(configView);
		ConfigurationController configController = new ConfigurationController(configModel,configView);
		configController.initialize();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
}

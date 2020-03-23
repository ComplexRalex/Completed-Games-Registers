import model.*;
import view.*;
import controller.*;
import exception.CouldNotLoadFileException;
import exception.CouldNotSaveFileException;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

/*
 * Registro de juegos completados en Java
 * 
 * @author Alejandro Batres Pedroza
 * 
 * La idea principal con este proyecto era la de registrar juegos
 * y descargar sus datos automaticamente (incluyendo el uso de
 * imagenes dentro del programa y etc). Sin embargo, hasta el
 * momento no tengo los conocimientos suficientes para cumplir con
 * mis excpectativas, asi que lo hare de otra forma!
 * 
 * Esta es la primera version, la cual solamente es una "mejora
 * grafica" respecto a la version hecha en C
 * */
public class MainLauncher{
	
	public static void main(String[] args){
		
		//Anti-aliasing properties
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		
		Configuration configModel = new Configuration();
		try {
			configModel.loadConfiguration();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CouldNotLoadFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

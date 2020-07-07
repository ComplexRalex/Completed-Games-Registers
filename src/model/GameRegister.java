package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exception.CouldNotBackupFileException;
import exception.CouldNotCreateHTMLFileException;
import exception.CouldNotLoadFileException;
import exception.CouldNotSaveFileException;
import exception.HTMLTemplateNotFoundException;

import util.Path;

/*
 * NOTA: Los JOptionPane y algunas las clausulas "try" con excepciones cachadas estaran en el controlador
 * Por ahora los dejare aqui (comentados) hasta que finalmente los mueva
 * */


public class GameRegister{
	private ArrayList<GameStat> gameStats;
	
	/* Obviously it's necessary, but most importantly because if there wasn't a "savefile" to load, it will generate 
	 * a new an fresh-oh-yezz array list.
	 * */
	public GameRegister(){
		gameStats = new ArrayList<>();
	}
	
	public void saveGameStats() throws FileNotFoundException, CouldNotSaveFileException{
		FileOutputStream f = new FileOutputStream(Path.saveFile);
		try{
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(gameStats);
			o.close();
		}
		catch(IOException e){
			throw new CouldNotSaveFileException();
		}
		//JOptionPane.showConfirmDialog(null, "No se pudo escribir el archivo de guardado.", "Error al guardar datos", JOptionPane.ERROR_MESSAGE);
	}
	
	@SuppressWarnings("unchecked")
	public void loadGameStats() throws FileNotFoundException, ClassNotFoundException, CouldNotLoadFileException{
		FileInputStream f = new FileInputStream(Path.saveFile);
		try{
			ObjectInputStream o = new ObjectInputStream(f);
			gameStats = (ArrayList<GameStat>)o.readObject();
			o.close();
		}
		catch(IOException e){
			throw new CouldNotLoadFileException();
		}
		//JOptionPane.showConfirmDialog(null, "No se encontro el archivo de guardado.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
		//JOptionPane.showMessageDialog(null, "El archivo de guardado no es valido.", "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
	}
	
	public void doBackup() throws FileNotFoundException, CouldNotBackupFileException{
		FileOutputStream f = new FileOutputStream(Path.backups+"backup-"+(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")).format(new Date())+".dat");
		try{
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(gameStats);
			o.close();
		}
		catch(IOException e){
			throw new CouldNotBackupFileException();
		}
		//JOptionPane.showMessageDialog(null, "No se pudo crear el backup"+ConfigModel.backupCount, "Error al crear backup"+ConfigModel.backupCount, JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean addGameStat(GameStat gs){
		int iterator = 0;
		for(iterator = 0; iterator < gameStats.size(); iterator++){ // Add elements sorted by their title
			if(gs.getGame().compareToIgnoreCase(gameStats.get(iterator).getGame()) > 0) continue;
			if(gs.getGame().compareToIgnoreCase(gameStats.get(iterator).getGame()) == 0) return false;
			break;
		}
		gameStats.add(iterator, gs);

		return true;
	}
	
	public boolean removeGameStat(GameStat gs){ // Maybe useless because of the view
		if(gs != null)
			return gameStats.remove(gs);

		return false;
	}
	
	
	public void generateHTMLPage() throws CouldNotCreateHTMLFileException, HTMLTemplateNotFoundException{
		HTMLGameRegister.openFileOutput(Path.html);
		HTMLGameRegister.writeTemplatePage(Path.htmlTemplateFile);
		HTMLGameRegister.writeGameIndexPage(gameStats);
		HTMLGameRegister.writeGameListPage(gameStats);
		HTMLGameRegister.closeFileOutput();
		//JOptionPane.showMessageDialog(null, "Hubo un error al generar la pagina HTML.","Error al generar archivo HTML",JOptionPane.ERROR_MESSAGE);
		// Faltan mas juasjuas, hacer los que faltan cuando empiece el controlador
	}
	
	public ArrayList<GameStat> getGameStats(){return gameStats;}
	public ArrayList<GameStat> getGameStatsOccurrences(String title){
		ArrayList<GameStat> gss = new ArrayList<>();
		for(GameStat gs: gameStats)
			if(gs.getGame().toLowerCase().contains(title.toLowerCase())) gss.add(gs);
		return gss;
	}
	
	public void setGameStats(ArrayList<GameStat> gs){gameStats = gs;}
}

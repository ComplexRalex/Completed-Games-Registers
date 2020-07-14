package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.Path;

/*
 * NOTA: Los JOptionPane y algunas las clausulas "try" con excepciones cachadas estaran en el controlador
 * Por ahora los dejare aqui (comentados) hasta que finalmente los mueva
 * */


public class GameRegister{
	private ArrayList<GameStat> gameStats;
	private boolean changeMade;
	
	/* Obviously it's necessary, but most importantly because if there wasn't a "savefile" to load, it will generate 
	 * a new an fresh-oh-yezz array list.
	 * */
	public GameRegister(){
		gameStats = new ArrayList<>();
		changeMade = false;
	}
	
	public void saveGameStats() throws IOException {
		FileOutputStream f = new FileOutputStream(Path.saveFile);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(gameStats);
		o.close();
		f.close();

		changeMade = true;
	}
	
	@SuppressWarnings("unchecked")
	public void loadGameStats() throws ClassNotFoundException, IOException {
		FileInputStream f = new FileInputStream(Path.saveFile);
		ObjectInputStream o = new ObjectInputStream(f);
		gameStats = (ArrayList<GameStat>)o.readObject();
		o.close();
		f.close();
	}
	
	public String doBackup() throws IOException {
		String fileName = Path.backups+"backup-"+(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")).format(new Date())+".dat";
		FileOutputStream f = new FileOutputStream(fileName);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(gameStats);
		o.close();
		f.close();

		return fileName;
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

	public GameStat getGameStat(String name){
		for(GameStat gs: gameStats){
			if(gs.getGame().compareToIgnoreCase(name) == 0) return gs;
		}
		return null;
	}
	
	public boolean removeGameStat(GameStat gs){ // Maybe useless because of the view
		if(gs != null)
			return gameStats.remove(gs);

		return false;
	}
	
	public boolean changesMade(){
		return changeMade;
	}
	
	// public void generateHTMLPage() throws CouldNotCreateHTMLFileException, HTMLTemplateNotFoundException{
	// 	HTMLGameRegister.openFileOutput(Path.html);
	// 	HTMLGameRegister.writeTemplatePage(Path.htmlTemplateFile);
	// 	HTMLGameRegister.writeGameIndexPage(gameStats);
	// 	HTMLGameRegister.writeGameListPage(gameStats);
	// 	HTMLGameRegister.closeFileOutput();
	// 	//JOptionPane.showMessageDialog(null, "Hubo un error al generar la pagina HTML.","Error al generar archivo HTML",JOptionPane.ERROR_MESSAGE);
	// 	// Faltan mas juasjuas, hacer los que faltan cuando empiece el controlador
	// }
	
	public ArrayList<GameStat> getGameStats(){return gameStats;}
	public ArrayList<GameStat> getGameStatsOccurrences(String title){
		ArrayList<GameStat> gss = new ArrayList<>();
		for(GameStat gs: gameStats)
			if(gs.getGame().toLowerCase().contains(title.toLowerCase())) gss.add(gs);
		return gss;
	}
	
	public void setGameStats(ArrayList<GameStat> gs){gameStats = gs;}
}

package util;

public class Path{
	private static final String srcPath = "src/";			// Every important file goes here
	private static final String dataPath = "data/";			// Every user-mofified file goes here
	
	public static final String saveFile = dataPath+"save.dat";
	public static final String backups = dataPath+"backup/";
	public static final String configFile = dataPath+"config.dat";
	public static final String gameInfo = dataPath+"game/json/";
	public static final String gameImage = dataPath+"game/image/";
	public static final String html = dataPath+"html/";

	public static final String images = srcPath+"image/";
	public static final String htmlTemplateFile = srcPath+"html/template.html";

	public static final String validFileName(String name, String extension){
		return name.toLowerCase().replaceAll("[^ ()a-z0-9+-]","").replaceAll(" ","_")+"."+extension;
	}
}

package util;

public class Path{
	private static final String srcPath = "src/";			// Every important file goes here
	private static final String dataPath = "data/";			// Every user-mofified file goes here
	
	public static final String saveFile = dataPath+"save.dat";
	public static final String backups = dataPath+"backup/";
	public static final String configFile = dataPath+"config.dat";
	public static final String html = dataPath+"html/";

	public static final String images = srcPath+"image/";
	public static final String consoleInfo = srcPath+"console/info/";
	public static final String htmlTemplateFile = srcPath+"html/template.html";
}

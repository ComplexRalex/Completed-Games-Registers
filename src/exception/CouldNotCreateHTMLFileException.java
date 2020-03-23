package exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class CouldNotCreateHTMLFileException extends IOException{
	public CouldNotCreateHTMLFileException(){ super("Could not create the HTML file."); }
	public CouldNotCreateHTMLFileException(String str){ super(str); }
}

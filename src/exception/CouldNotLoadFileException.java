package exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class CouldNotLoadFileException extends IOException{
	public CouldNotLoadFileException(){ super("Could not load the file."); }
	public CouldNotLoadFileException(String str){ super(str); }
}

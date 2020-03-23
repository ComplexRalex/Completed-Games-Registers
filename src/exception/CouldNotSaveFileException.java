package exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class CouldNotSaveFileException extends IOException{
	public CouldNotSaveFileException(){ super("Could not save the file."); }
	public CouldNotSaveFileException(String str){ super(str); }
}

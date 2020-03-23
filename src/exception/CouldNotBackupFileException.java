package exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class CouldNotBackupFileException extends IOException{
	public CouldNotBackupFileException(){ super("Could not create a backup of the file."); }
	public CouldNotBackupFileException(String str){ super(str); }
}

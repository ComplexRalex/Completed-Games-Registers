package exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class HTMLTemplateNotFoundException extends IOException{
	public HTMLTemplateNotFoundException(){ super("The HTML template was not found."); }
	public HTMLTemplateNotFoundException(String str){ super(str); }
}

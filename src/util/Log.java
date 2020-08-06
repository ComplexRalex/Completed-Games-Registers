/**
 * Completed-Games Registers, a software where you can record every
 * game you have beaten (completed) so far!
 * Copyright (C) 2020  Alejandro Batres
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * Contact by email: alejandro.batres37@gmail.com
 */

package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import system.Software;

/**
 * <h3>Log util class.</h3>
 * This class provides some useful functions to create
 * "debug" messages or related stuff.
 * <p>
 * Every created files goes directly to {@link Path#logPath}.
 * </ul>
 * 
 * @see Path
 */
public class Log{

    /**
     * Code of a simple log message, its "string" value is 
     * {@code message}.
     */
    public static final int MESSAGE = 0;

    /**
     * Code of a debug message, its "string" value is 
     * {@code debug}.
     */
    public static final int DEBUG = 1;

    /**
     * Code of an error/exception message, its "string" value is 
     * {@code error}.
     */
    public static final int ERROR = 2;

    /**
     * Converts a stack trace (by a thrown Exception) into a string
     * and includes a little more information.
     * 
     * @param e Throwable
     * @return String containing all the text.
     */
    public static String getDetails(Throwable e){
        StringWriter writer = new StringWriter();
        Calendar today = Calendar.getInstance();
        
        writer.append(" > Version: "+Software.VERSION+"\n\n");

        writer.append(" | --- --- --- --- --- --- --- |"+"\n\n");

        writer.append(" > Time: "+
            (today.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "")+today.get(Calendar.HOUR_OF_DAY)+":"+
            (today.get(Calendar.MINUTE) < 10 ? "0" : "")+today.get(Calendar.MINUTE)+":"+
            (today.get(Calendar.SECOND) < 10 ? "0" : "")+today.get(Calendar.SECOND)+"\n");
        writer.append(" > Day: "+today.get(Calendar.DAY_OF_MONTH)+"\n");
        writer.append(" > Month: "+today.get(Calendar.MONTH)+"\n");
        writer.append(" > Year: "+today.get(Calendar.YEAR)+"\n\n");
        
        writer.append(" | --- --- --- --- --- --- --- |"+"\n\n");
        
        writer.append(" > Exception.toString(): "+e.toString()+"\n\n");
        
        writer.append(" | --- --- --- --- --- --- --- |"+"\n\n");

        writer.append(" > Class: "+e.getClass()+"\n");
        writer.append(" > Message: "+e.getMessage()+"\n");
        writer.append(" > Localized message: "+e.getLocalizedMessage()+"\n");
        writer.append(" > Cause: "+e.getCause()+"\n\n");
        
        writer.append(" | --- --- --- --- --- --- --- |"+"\n\n");

        writer.append(" > Stack trace:\n");
        e.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }

    /**
     * Obtains the {@code value} of the given {@code code}. 
     * 
     * @param code Number which represents a value
     * @return {@code value} of the given {@code code}.
     */
    public static String getValue(int code){
        switch(code){
            case 0: return "message";
            case 1: return "debug";
            case 2: return "error";
            default: return "undefined";
        }
    }

    /**
     * Saves to a file the given text with a name determined
     * by:
     * <ul>
     * <li>The {@code value}, which is determined by the
     * given {@code code}.
     * <li>The current date formatted with
     * {@code SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")}.
     * <li>The extension {@code .log}.
     * </ul>
     * An example is the following:
     * <p>
     * <b>{@code value-2020-08-05-16-18-30.log}</b>.
     * 
     * @param content Text that will be inside this file
     * @param code Number which determines the type of
     * log message. If it does not exists, the value will
     * be {@code undefined}.
     * @see Path#validFileName(String, String)
     */
    public static void toFile(String content, int code){
        toFile(content, (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")).format(new Date()), code);
    }

    /**
     * Saves to a file the given text with a name determined
     * by:
     * <ul>
     * <li>The {@code value}, which is determined by the
     * given {@code code}.
     * <li>The given name.
     * <li>The extension {@code .log}.
     * <ul>
     * An example is the following:
     * <p>
     * <b>{@code value-name.log}</b>.
     * <b>WARNING</b>: This will overwrite any file which
     * have the same name as the given one.
     * 
     * @param content Text that will be inside this file
     * @param name Name of the file (after the value)
     * @param code Number which determines the type of
     * log message. If it does not exists, the value will
     * @see Path#validFileName(String, String)
     * be {@code undefined}.
     */
    public static void toFile(String content, String name, int code){
        Path.resolve(Path.logPath);
        String value = getValue(code)+"-";
        try {
            FileWriter writer = new FileWriter(Path.logPath+value+Path.validFileName(name, "log"));
            writer.append(content);
            writer.close();
		} catch (IOException e) {
			Advice.showTextAreaAdvice(
                null,
                Language.loadMessage("g_oops"),
                Language.loadMessage("g_went_wrong") + ": ",
                getDetails(e), Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                Language.loadMessage("g_accept"),
                Colour.getPrimaryColor()
            );
		}
    }
}

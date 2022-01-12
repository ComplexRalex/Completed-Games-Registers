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

import java.awt.Container;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <h3>Navigation util class.</h3>
 * This class provides a function that will show up
 * an {@link Advice} dialog asking to go to the specified
 * web page.
 */
public class Navigation{

    /**
     * Pops up an {@link Advice} dialog asking to open a
     * web page in the default configured browser.
     * 
     * @param url URL of the web page
     */
    public static void goToPage(String url, Container container){
        String command, os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();

        if(Advice.showOptionTextAreaAdvice(
            container,
            Language.loadMessage("g_message"),
            Language.loadMessage("g_will_browse"),
            url, 50, 2,
            new String[]{
                Language.loadMessage("g_accept"),
                Language.loadMessage("g_cancel")
            },
            Colour.getPrimaryColor()
        ) == 0){
            try {
                Log.toConsole("System: "+os, "Navigation.goToPage", Log.DEBUG);
                if(os.indexOf("win") >= 0)
                    command = "rundll32 url.dll,FileProtocolHandler "+url;
                else if(os.indexOf("mac") >= 0)
                    command = "open "+url;
                else if(os.indexOf("nix") >=0 || os.indexOf("nux") >=0)
                    command = "xdg-open "+url;
                else{
                    Advice.showTextAreaAdvice(
                        container,
                        Language.loadMessage("g_oops"),
                        Language.loadMessage("g_went_wrong")+": ",
                        "Opening browser is not supported at the moment...", 40, 2,
                        Language.loadMessage("g_accept"),
                        Colour.getPrimaryColor()
                    );
                    return;
                }
                rt.exec(command);
            } catch (IOException e) {
                String error = Log.getDetails(e);
                Log.toFile(error, Log.ERROR);
                Advice.showTextAreaAdvice(
                    container,
                    Language.loadMessage("g_oops"),
                    Language.loadMessage("g_went_wrong")+": ",
                    error, Advice.EXCEPTION_WIDTH, Advice.EXCEPTION_HEIGHT,
                    Language.loadMessage("g_accept"),
                    Colour.getPrimaryColor()
                );
            }
        }
    }
}

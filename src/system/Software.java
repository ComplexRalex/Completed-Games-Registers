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

package system;

import java.util.HashMap;

/**
 * <h3>Software system class.</h3>
 * This class is used to obtain important data about this program.
 * It contains variables like the current version, developer,
 * licenses, etcetera.
 * 
 * @author Alejandro Batres
 */
public class Software{

    /**
     * Name of the software project.
     */
    public static final String NAME = "Completed-Games Registers";

    /**
     * Current version of the project.
     */
    public static final String VERSION = "1.1.020";

    /**
     * RAWG API key.
     */
    public static final String API_KEY = "INSERT-YOUR-RAWG-API-KEY-HERE";

    /**
     * Necessary java version to run the project.
     */
    public static final String JAVA = "Java 1.8";

    /**
     * Java JDK version.
     */
    public static final String JDK = "OpenJDK 1.8.0_262";

    /**
     * Name of the developer of the project.
     */
    public static final String DEVELOPER = "Alejandro Batres";

    /**
     * Usernames of the developer of the project.
     */
    public static final String[] DEVELOPER_USERNAMES = new String[]{
        "MrAlexbross",
        "ComplexRalex"
    };

    /**
     * URL of the report issues page of the project (in GitHub).
     */
    public static final String ISSUES_PAGE = "https://github.com/ComplexRalex/Completed-Games-Registers/issues/new/choose";

    /**
     * URL of the source code page of the project (in GitHub).
     */
    public static final String SOURCE_CODE_PAGE = "https://github.com/ComplexRalex/Completed-Games-Registers";

    /**
     * URL of the Twitter profile of the developer.
     */
    public static final String TWITTER_PROFILE = "https://twitter.com/ComplexRalex";

    /**
     * License of the program.
     */
    public static final String LICENSE = 
        "    Completed-Games Registers, a software where you can record every\n"+
        "    game you have beaten (completed) so far!\n"+
        "    Copyright (C) 2020  Alejandro Batres\n"+
        "\n"+
        "    This program is free software: you can redistribute it and/or modify\n"+
        "    it under the terms of the GNU General Public License as published by\n"+
        "    the Free Software Foundation, either version 3 of the License, or\n"+
        "    (at your option) any later version.\n"+
        "\n"+
        "    This program is distributed in the hope that it will be useful,\n"+
        "    but WITHOUT ANY WARRANTY; without even the implied warranty of\n"+
        "    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"+
        "    GNU General Public License for more details.\n"+
        "\n"+
        "    You should have received a copy of the GNU General Public License\n"+
        "    along with this program.  If not, see <https://www.gnu.org/licenses/>.\n"+
        "\n"+
        "    Contact by email: alejandro.batres37@gmail.com";

    /**
     * Names of the used libraries.
     */
    public static final String[] LIBRARY = new String[]{
        "JSON-java"
    };

    /**
     * HashMap of used libraries.
     */
    public static final HashMap<String,HashMap<String,String>> LIBRARY_DETAILS = createLibraryMap();
    
    /**
     * Initializes the {@link #LIBRARY_DETAILS} variable.
     * 
     * @return HashMap with information about the used libraries.
     */
    private static final HashMap<String,HashMap<String,String>> createLibraryMap(){
        HashMap<String,HashMap<String,String>> map = new HashMap<>();
        for(int i = 0; i < LIBRARY.length; i++)
            map.put(LIBRARY[i], new HashMap<>());
        
        // Init library named "JSON-java"
        map.get(LIBRARY[0]).put("version", "20200518");
        map.get(LIBRARY[0]).put("website_url", "https://github.com/stleary/JSON-java");
        map.get(LIBRARY[0]).put("license",
            "Copyright (c) 2002 JSON.org\n"+
            "\n"+
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n"+
            "of this software and associated documentation files (the \"Software\"), to deal "+
            "in the Software without restriction, including without limitation the rights "+
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell "+
            "copies of the Software, and to permit persons to whom the Software is "+
            "furnished to do so, subject to the following conditions:\n"+
            "\n"+
            "The above copyright notice and this permission notice shall be included in all "+
            "copies or substantial portions of the Software.\n"+
            "\n"+
            "The Software shall be used for Good, not Evil.\n"+
            "\n"+
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR "+
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, "+
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE "+
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER "+
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, "+
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE "+
            "SOFTWARE."
        );

        return map;
    }

    /**
     * Names of used APIs.
     */
    public static final String[] API = new String[]{
        "RAWG Video Games Database API"
    };

    /**
     * HashMap of used libraries.
     */
    public static final HashMap<String,HashMap<String,String>> API_DETAILS = createAPIMap();
    
    /**
     * Initializes the {@link #API_DETAILS} variable.
     * 
     * @return HashMap with information about the used APIs. 
     */
    private static final HashMap<String,HashMap<String,String>> createAPIMap(){
        HashMap<String,HashMap<String,String>> map = new HashMap<>();
        for(int i = 0; i < API.length; i++)
            map.put(API[i], new HashMap<>());
        
        // Init library named "RAWG Viedeo Games Database"
        map.get(API[0]).put("website_url", "https://rawg.io/apidocs");
        map.get(API[0]).put("terms_of_use",
            "Terms of Use\n"+
            "\n"+
            " * Free for personal use as long as you attribute RAWG as the source of the data "+
            "and/or images and add an active hyperlink from every page where the data of "+
            "RAWG is used.\n"+
            " * Free for commercial use for startups and hobby projects with not more than "+
            "100,000 monthly active users or 500,000 page views per month. If your project is "+
            "larger than that, email us at api@rawg.io for commercial terms.\n"+
            " * No cloning. It would not be cool if you used our API to launch a clone of RAWG. "+
            "We know it is not always easy to say what is a duplicate and what isn't. Drop us a "+
            "line at api@rawg.io if you are in doubt, and we will talk it through.\n"+
            " * Every API request should have a User-Agent header with your app name. If you don’t "+
            "provide it, we may ban your requests."
        );

        return map;
    }

    /**
     * Recordando a conocidos y seres queridos. <3
     */
    public static final String[] MEMORY_OF_LIFE = new String[]{
        "Bichi (Mi hermanito perruno)",
        "Wicho Luna (YipeSkull)",
        "Alejandro Miguel Taboada Sanchez (Programación ATS)"
    };
}

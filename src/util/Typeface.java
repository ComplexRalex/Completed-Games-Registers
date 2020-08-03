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

import java.awt.Font;

/**
 * <h3>Typeface utility class.</h3>
 * This class provides a bunch of {@code static} {@link Font}s.
 * <p>
 * Note that this software is using the "<b>Open Sans</b>" font. So,
 * if the user doesn't have installed it, the software might use a
 * default system font (depending on the OS and Java).
 * 
 * @author Alejandro Batres
 */
public class Typeface{
    
    /**
     * Font used to show lots of paragraphs.
     * <ul>
     * <li>Style: Normal.
     * <li>Size: 14px.
     * </ul>
     */
    public static final Font textPlain = new Font("Open Sans",0,14);

    /**
     * Font used in {@link JLabel}s that display a title.
     * <ul>
     * <li>Style: Bold.
     * <li>Size: 24px.
     * </ul>
     */
    public static final Font labelTitle = new Font("Open Sans",1,24);

    /**
     * Font used in {@link JLabel}s that display a subtitle.
     * <ul>
     * <li>Style: Bold and Italic.
     * <li>Size: 20px.
     * </ul>
     */
    public static final Font labelSubtitle = new Font("Open Sans",3,20);

    /**
     * Font used in {@link JLabel}s that display a simple text.
     * <ul>
     * <li>Style: Bold.
     * <li>Size: 16px.
     * </ul>
     */
    public static final Font labelBold = new Font("Open Sans",1,16);

    /**
     * Font used in {@link JLabel}s that display a simple text.
     * <ul>
     * <li>Style: Normal.
     * <li>Size: 16px.
     * </ul>
     */
    public static final Font labelPlain = new Font("Open Sans",0,16);

    /**
     * Font used in {@link JButton}s.
     * <ul>
     * <li>Style: Bold.
     * <li>Size: 14px.
     * </ul>
     */
    public static final Font buttonBold = new Font("Open Sans",1,14);

    /**
     * Font used in {@link JButton}s.
     * <ul>
     * <li>Style: Italic.
     * <li>Size: 14px.
     * </ul>
     */
    public static final Font buttonItalic = new Font("Open Sans",2,14);

    /**
     * Font used in {@link JButton}s.
     * <ul>
     * <li>Style: Normal.
     * <li>Size: 14px.
     * </ul>
     */
    public static final Font buttonPlain = new Font("Open Sans",0,14);
}

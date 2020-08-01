package util;

import java.awt.Font;

/**
 * <h3>Typeface utility class.</h3>
 * This class provides a bunch of {@code static} {@link Font}s.
 * <p>
 * Note that this software is using the "<b>Open Sans</b>" font. So,
 * if the user doesn't have installed it, the software might use a
 * default system font (depending on the OS and Java).
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

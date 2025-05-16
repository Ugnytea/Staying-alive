package Frontend;

import javax.swing.*;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ------------------------------------------------------------------------
 * StartButton is a custom JButton that represents the entry point
 * to begin the main game. It displays the "Start Game" label.
 */
public class StartButton extends JButton {
    /**
     * Constructs a StartButton with predefined text and layout configuration.
     * The text is positioned below and centered relative to any icon.
     */
    public StartButton() {

        super("Start Game");

        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }
}

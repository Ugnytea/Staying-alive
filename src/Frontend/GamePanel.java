package Frontend;

import javax.swing.*;
import java.awt.*;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ------------------------------------------------------------------------
 * GamePanel is the central panel of the frontend GUI, containing
 * all the interactive buttons that allow the user to track various
 * aspects of their pet's well-being and their own daily habits.
 */
public class GamePanel extends JPanel {
    /**
     * Constructs the GamePanel and adds all functional buttons
     * (Sleep, Nutrition, Hydration, Exercise, Task and Check-Up)
     */
    public GamePanel() {
        setLayout(new GridLayout(3, 2, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // every button is here
        add(new SleepButton());
        add(new NutritionButton());
        add(new HydrationButton());
        add(new ExerciseButton());
        add(new TaskButton());
        add(new CheckUpButton());
    }
}

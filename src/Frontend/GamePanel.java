package Frontend;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setLayout(new GridLayout(3, 2, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // every button is here
        add(new SleepButton());
        add(new NutritionButton());
        add(new HydrationButton());
        add(new ExerciseButton());
        add(new TasksButton());
        add(new CheckUpButton());
    }
}

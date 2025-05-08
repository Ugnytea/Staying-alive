package Frontend;

import Backend.ButtonAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * ExerciseButton is a custom JButton that prompts the user
 * to confirm whether they exercised. If the user answers "yes",
 * the current time is recorded and passed to the backend for logging.
 *
 */


public class ExerciseButton extends JButton {
    /**
     * Constructs an ExerciseButton labeled "Exercise".
     * Adds a dialog prompt on click, and if the user confirms exercise,
     * logs the time via the backend.
     */
    public ExerciseButton() {
        super("Exercise");  // Set the button's text

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, " Did You exercise?");

                if (input != null && !input.trim().isEmpty()) {
                    String answer = input.trim().toLowerCase();
                    if (answer.equals("yes")) {
                        try {
                            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

                            ButtonAction.updateExercise(time);
                            JOptionPane.showMessageDialog(null, "Exercise logged at " + time + "!");
                            JOptionPane.showMessageDialog(null, "Information updated!\n");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no'.");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        }
                    } else if (answer.equals("no")) {
                        JOptionPane.showMessageDialog(null, "No exercise logged.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No input provided.");
                    }
                }
            }
        });
    }
}
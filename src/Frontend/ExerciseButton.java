package Frontend;

import Backend.ButtonAction;
import Exceptions.DataNotSavedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ------------------------------------------------------------------------
 * ExerciseButton is a custom JButton that prompts the user
 * to confirm whether they exercised. If the user answers "yes",
 * the current time is recorded and passed to the backend for logging.
 */
public class ExerciseButton extends JButton {

    private final Color baseColor = new Color(204, 255, 204);      // pastel green
    private final Color hoverColor = new Color(184, 245, 184);     // lighter green
    private final Color pressColor = new Color(164, 235, 164);     // deeper green

    /**
     * Constructs an ExerciseButton labeled "Exercise".
     * Adds a dialog prompt on click, and if the user confirms exercise,
     * logs the time via the backend.
     */
    public ExerciseButton() {
        super("Exercise");
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setBackground(baseColor);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(1));
        setFocusPainted(false);

        // Pumpiness visual effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setBorder(BorderFactory.createBevelBorder(1, Color.WHITE, Color.GRAY));
                setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(baseColor);
                setBorder(BorderFactory.createBevelBorder(1));
                setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressColor);
                setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
                setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2);
            }
        });

        // Logic for recording exercise
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Did you exercise? Type 'yes' or 'no'.");

                if (input != null) {
                    input = input.trim();
                    if (!input.isEmpty()) {
                        String answer = input.toLowerCase();
                        if (answer.equals("yes")) {
                            try {
                                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                                ButtonAction.updateExercise(time);
                                JOptionPane.showMessageDialog(null, "Exercise logged at " + time + "!");
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Please enter 'yes' or 'no'.");
                            } catch (DataNotSavedException ex) {
                                JOptionPane.showMessageDialog(null, "Error: saving pet's data.");
                            }
                        } else if (answer.equals("no")) {
                            JOptionPane.showMessageDialog(null, "No exercise logged.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Input is not correct, it has to be 'yes' or 'no'.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No input provided.");
                    }
                }
            }
        });
    }
}

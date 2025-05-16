package Frontend;

import Backend.ButtonAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ----------------------------------------------------------------------------
 * NutritionButton is a custom JButton that prompts the user to input
 * the time they last ate and updates this information using the backend logic.
 */
public class NutritionButton extends JButton {
    /**
     * Constructs a NutritionButton with labeled text and registers an action listener.
     * When clicked, it prompts the user to input the time of their last meal and
     * sends the data to the backend.
     */
    public NutritionButton() {
        super("Nutrition");  // Set the button's text

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask for ml of water
                String input = JOptionPane.showInputDialog(null, "When was the last time You ate (HH:mm)?");

                if (input != null) {
                    input = input.trim();


                    if (input != null && !input.trim().isEmpty()) {
                        try {
                            String time = input.trim();

                            if (!time.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                                JOptionPane.showMessageDialog(null, "Please enter time in format - HH:mm");
                                return;
                            }

                            // Call backend
                            ButtonAction.updateNutrition(time);

                            JOptionPane.showMessageDialog(null, "Information updated!\n");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter a number.");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No input provided.");
                    }
                }
            }

        });
    }
}
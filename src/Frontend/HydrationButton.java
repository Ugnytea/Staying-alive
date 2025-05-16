package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Backend.ButtonAction;
import Exceptions.DataNotSavedException;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ------------------------------------------------------------------------
 * HydrationButton represents a button in the GUI that allows the user
 * to log their water intake. The user inputs the amount of water in ml
 * and the time of intake (in HH:mm format).
 */
public class HydrationButton extends JButton {
    /**
     * Constructs the HydrationButton and attaches an action listener
     * that prompts the user for water intake data and logs it.
     */
    public HydrationButton() {
        super("Hydration");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask for ml of water
                String input = JOptionPane.showInputDialog(null, "How much water did You drink (ml) and when (HH:mm)?\n (e. g., 250 14:30)");
                if (input != null) {
                    input = input.trim();

                    if (input != null && !input.trim().isEmpty()) {
                        try {
                            String[] parts = input.trim().split("\\s+");


                            if (parts.length != 2) {
                                throw new IllegalArgumentException("Please enter both ml and time (e.g., 250 14:30).");
                            }

                            int ml = Integer.parseInt(parts[0]);
                            if (ml < 0 || ml > 4000) {
                                throw new IllegalArgumentException("Please enter water ml between 0 and 4000 ml.");
                            }

                            String time = parts[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                            LocalTime parsedTime = LocalTime.parse(time, formatter); // will throw if invalid

                            ButtonAction.updateHydration(ml, time);
                            JOptionPane.showMessageDialog(null, "Hydration updated!\n" +
                                    "You logged " + ml + " ml at " + time + ".");

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number for milliliters (e.g., 250).");
                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid time format.\nUse 24 hour format HH:mm (e.g., 14:30).\nHours: 00–23, Minutes: 00–59.");
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (DataNotSavedException ex) {
                            JOptionPane.showMessageDialog(null, "Error: could not save hydration data.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No input provided.");
                    }
                }
            }
        });
    }
}
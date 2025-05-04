package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Backend.ButtonAction;

public class HydrationButton extends JButton {

    public HydrationButton() {
        super("Hydration");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask for ml of water
                String input = JOptionPane.showInputDialog(null, "How much water did You drink (ml) and when (HH:mm)?\nExample: 250 14:30");

                if (input != null && !input.trim().isEmpty()) {
                    try {
                        String[] parts = input.trim().split("\\s+");


                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Please enter both ml and time (e.g., 250 14:30).");
                        }

                        int ml = Integer.parseInt(parts[0]);
                        String time = parts[1];

                        ButtonAction.updateHydration(ml, time);

                        JOptionPane.showMessageDialog(null, "Hydration updated!\n" +
                                "You logged " + ml + " ml at " + time + ".");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a numbers without.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No input provided.");
                }
            }
        });
    }
}

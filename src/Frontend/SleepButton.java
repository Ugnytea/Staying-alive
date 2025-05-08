package Frontend;

import Backend.ButtonAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * SleepButton is a custom JButton used to collect and log the user's wake-up time.
 * When clicked, it prompts the user to input the time they woke up (in HH:mm format).
 */
public class SleepButton extends JButton {
    /**
     * Constructs the SleepButton with appropriate label and action listener.
     * It handles input validation and updates the backend with the user's wake-up time.
     */
    public SleepButton() {
        super("Sleep");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "When did You wake up? (HH:mm)");

                if (input != null) {
                    input = input.trim();

                    if (!input.isEmpty()) {
                        try {
                        String time = input.trim();

                        // check if time is proper
                            if (!input.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                                JOptionPane.showMessageDialog(null, "Please enter time in format - HH:mm");
                                return;
                            }

                        // call backend with entered time
                        ButtonAction.updateSleep(time);

                        JOptionPane.showMessageDialog(null, "Wake-up time saved successfully!");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
                    }
                     } else {
                    JOptionPane.showMessageDialog(null, "No input provided.");
                    }
                }
            }
        });
    }
}

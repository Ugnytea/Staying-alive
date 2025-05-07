package Frontend;

import Backend.ButtonAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SleepButton extends JButton {

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

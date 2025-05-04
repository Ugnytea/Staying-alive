package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SleepButton extends JButton {

    public SleepButton() {
        super("Sleep");  // Set the button's text
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define the action for the Sleep button
                JOptionPane.showMessageDialog(null, "Sleep button pressed!");
            }
        });
    }
}

package Frontend;

import Backend.ButtonAction;
import Exceptions.DataNotSavedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * -----------------------------------------------------------------------------------
 * SleepButton is a custom JButton used to collect and log the user's wake-up time.
 * When clicked, it prompts the user to input the time they woke up (in HH:mm format).
 */
public class SleepButton extends JButton {

    private final Color baseColor = new Color(255, 253, 208);
    private final Color hoverColor = new Color(255, 240, 180);
    private final Color pressColor = new Color(230, 220, 150);

    /**
     * Constructs the SleepButton with appropriate label, styling, and action listener.
     * It handles input validation and updates the backend with the user's wake-up time.
     */
    public SleepButton() {
        super("Sleep");

        // Basic styling
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setBackground(baseColor);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(1));
        setFocusPainted(false);

        // Add "pumpy" mouse effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setBorder(BorderFactory.createBevelBorder(1, Color.WHITE, Color.GRAY));
                setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2); // "pop out"
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(baseColor);
                setBorder(BorderFactory.createBevelBorder(1));
                setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2); // reset size
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressColor);
                setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2); // "press in"
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
                setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2); // back to "pop"
            }
        });

        // Action logic
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
                                JOptionPane.showMessageDialog(null, "Please enter the time in 24-hour format (HH:mm)");
                                return;
                            }

                            // call backend with entered time
                            ButtonAction.updateSleep(time);

                            JOptionPane.showMessageDialog(null, "Wake-up time saved successfully!");
                        } catch (DataNotSavedException ex) {
                            JOptionPane.showMessageDialog(null, "Error: saving pet's data.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No input provided.");
                    }
                }
            }
        });
    }
}

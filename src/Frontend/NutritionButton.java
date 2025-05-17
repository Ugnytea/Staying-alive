package Frontend;

import Backend.ButtonAction;
import Exceptions.DataNotSavedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ----------------------------------------------------------------------------
 * NutritionButton is a custom JButton that prompts the user to input
 * the time they last ate and updates this information using the backend logic.
 */
public class NutritionButton extends JButton {

    private final Color baseColor = new Color(255, 223, 186);      // pastel yellow
    private final Color hoverColor = new Color(255, 213, 166);     // lighter on hover
    private final Color pressColor = new Color(245, 200, 150);     // darker on press

    /**
     * Constructs a NutritionButton with labeled text and registers an action listener.
     * When clicked, it prompts the user to input the time of their last meal and
     * sends the data to the backend.
     */
    public NutritionButton() {
        super("Nutrition");
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setBackground(baseColor);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(1));
        setFocusPainted(false);

        // Add mouse listener for pumpy effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setBorder(BorderFactory.createBevelBorder(1, Color.WHITE, Color.GRAY));
                setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2); // pop out
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
                setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2); // press in
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
                setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2); // back to hover
            }
        });

        // Action logic
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "When was the last time You ate (HH:mm)?");

                if (input != null) {
                    input = input.trim();

                    if (!input.isEmpty()) {
                        try {
                            if (!input.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                                JOptionPane.showMessageDialog(null, "Please enter the time in 24-hour format (HH:mm)");
                                return;
                            }

                            ButtonAction.updateNutrition(input);
                            JOptionPane.showMessageDialog(null, "Information updated!\n");

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter a number.");
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

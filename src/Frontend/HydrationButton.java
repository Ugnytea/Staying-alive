package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private final Color baseColor = new Color(173, 216, 230);      // pastel blue
    private final Color hoverColor = new Color(153, 206, 220);     // lighter hover
    private final Color pressColor = new Color(133, 196, 210);     // darker on press

    /**
     * Constructs the HydrationButton and attaches an action listener
     * that prompts the user for water intake data and logs it.
     */
    public HydrationButton() {
        super("Hydration");
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setBackground(baseColor);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(1));
        setFocusPainted(false);

        // Pumpy mouse effects
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

        // Input and backend logic
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null,
                        "How much water did You drink (ml) and when (HH:mm)?\n(e.g., 250 14:30)");
                if (input != null) {
                    input = input.trim();

                    if (!input.isEmpty()) {
                        try {
                            String[] parts = input.split("\\s+");

                            if (parts.length != 2) {
                                throw new IllegalArgumentException("Please enter both ml and time (e.g., 250 14:30).");
                            }

                            int ml = Integer.parseInt(parts[0]);
                            if (ml < 0 || ml > 4000) {
                                throw new IllegalArgumentException("Please enter water ml between 0 and 4000 ml.");
                            }

                            String time = parts[1];
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                            LocalTime parsedTime = LocalTime.parse(time, formatter); // throws if invalid

                            ButtonAction.updateHydration(ml, time);
                            JOptionPane.showMessageDialog(null,
                                    "Hydration updated!\nYou logged " + ml + " ml at " + time + ".");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number for milliliters (e.g., 250).");
                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid time format.\nUse 24-hour format HH:mm (e.g., 14:30).");
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

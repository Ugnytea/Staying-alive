package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import Backend.ButtonAction;
import Exceptions.DataNotSavedException;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ----------------------------------------------------------------------------------
 * CheckUpButton is a custom JButton that, when clicked,
 * displays the current health and wellbeing status of the user's virtual pet.
 * It retrieves pet status data from the backend (via ButtonAction)
 * and shows a message dialog summarizing the pet's condition (e.g., tired, hungry).
 */
public class CheckUpButton extends JButton {

    private final Color baseColor = new Color(255, 204, 203); // light pink
    private final Color hoverColor = new Color(255, 180, 180); // slightly darker pink
    private final Color pressColor = new Color(240, 160, 160); // even darker pink

    public CheckUpButton() {
        super("How is Your pet doing?");
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setBackground(baseColor);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(1));
        setFocusPainted(false);

        // Pumpy visual effect (hover/press/resize)
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
                if (contains(e.getPoint())) {
                    setBackground(hoverColor);
                    setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2); // return to pop out
                } else {
                    setBackground(baseColor);
                    setBorder(BorderFactory.createBevelBorder(1));
                    setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2); // reset
                }
            }
        });

        // Action: check pet status
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Map<String, Boolean> stats = ButtonAction.checkOnPet();

                    StringBuilder status = new StringBuilder("Status:\n\n");

                    status.append(stats.getOrDefault("tired", false) ? "Pet is tired!\n" : "Pet is rested!\n");
                    status.append(stats.getOrDefault("hungry", false) ? "Pet is hungry!\n" : "Pet is well fed!\n");
                    status.append(stats.getOrDefault("thirsty", false) ? "Pet is thirsty!\n" : "Pet is hydrated!\n");
                    status.append(stats.getOrDefault("restless", false) ? "Pet is restless!\n" : "Pet got enough exercise!\n");

                    JOptionPane.showMessageDialog(null, status.toString());

                } catch (DataNotSavedException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving pet's data.");
                }
            }
        });
    }
}

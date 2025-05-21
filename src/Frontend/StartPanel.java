package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ------------------------------------------------------------------------
 * StartPanel is the opening screen of the application.
 * It introduces the game to the user with a message, a description
 * and a button to proceed to the main game panel.
 */
public class StartPanel extends JPanel {
    /**
     * Constructs the start screen with a title, informational text
     * and a start button. Clicking the button transitions to the GamePanel.
     *
     * @param frame the main application frame to allow switching panels
     */
    public StartPanel(MainFrame frame) {
        setLayout(null);

        //Question
        JLabel nameLabel = new JLabel("Are You really staying alive?");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBounds(50, 80, 500, 50);
        add(nameLabel);

        //Instructions
        JLabel info = new JLabel("Your pet’s stats are a reflection of Your own!");
        info.setFont(new Font("Monospaced", Font.BOLD, 12));
        nameLabel.setForeground(Color.RED);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setBounds(100, 150, 400, 50);
        add(info);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        startButton.setBounds(200, 230, 200, 50);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createBevelBorder(1)); // 3D bevel border

        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(255, 100, 100)); // lighter on hover
                startButton.setBounds(198, 228, 204, 54); // slightly larger (pop effect)
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(255, 80, 80)); // original color
                startButton.setBounds(200, 230, 200, 50); // original size
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                startButton.setBounds(202, 232, 196, 46); // mimic press-in effect
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                startButton.setBounds(198, 228, 204, 54); // back to hover-pump
            }
        });

        add(startButton);



        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToGamePanel();
            }
        });
    }
}

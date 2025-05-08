package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
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

        // Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Monospaced", Font.PLAIN, 18));
        startButton.setBounds(200, 230, 200, 50);
        startButton.setForeground(Color.RED);
        add(startButton);



        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToGamePanel();
            }
        });
    }
}

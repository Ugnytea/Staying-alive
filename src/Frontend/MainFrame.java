package Frontend;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        initialize();
    }

    public void initialize() {
        setTitle("Staying Alive");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        StartPanel startPanel = new StartPanel(this);
        GamePanel gamePanel = new GamePanel();

        mainPanel.add(startPanel, "Start");
        mainPanel.add(gamePanel, "Game");

        add(mainPanel);
        cardLayout.show(mainPanel, "Start");

        setVisible(true);
    }

    public void switchToGamePanel() {
        cardLayout.show(mainPanel, "Game");
    }
}

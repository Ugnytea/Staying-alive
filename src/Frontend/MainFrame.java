package Frontend;

import javax.swing.*;
import java.awt.*;
/**
 * This class sets up the window, initializes the layout,
 * and provides a method to switch from the start panel to the game panel.
 */
public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    /**
     * Constructs the main frame and initializes its components.
     */
    public MainFrame() {
        initialize();
    }
    /**
     * Initializes the frame settings and adds the main panels.
     */
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

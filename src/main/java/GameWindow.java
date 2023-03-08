import javax.swing.*;

public class GameWindow extends JFrame {
    private JPanel gamePanel;

    public GameWindow(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(gamePanel);
    }

}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {

    private JPanel mainPanel;
    private JButton easyGameButton;
    private JButton mediumGameButton;
    private JButton hardGameButton;
    private JButton exitButton;

    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();

        exitButton.addActionListener(this::exitBtnActionPerformed);
        easyGameButton.addActionListener(this::easyGameBtnActionPerformed);
        mediumGameButton.addActionListener(this::mediumGameBtnActionPerformed);
        hardGameButton.addActionListener(this::hardGameBtnActionPerformed);
    }
    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }

    private void exitBtnActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void easyGameBtnActionPerformed(ActionEvent e) {
        new GameWindow("Easy Game").setVisible(true);
    }

    private void mediumGameBtnActionPerformed(ActionEvent e) {
        new GameWindow("Medium Game").setVisible(true);
    }

    private void hardGameBtnActionPerformed(ActionEvent e) {
        new GameWindow("Hard Game").setVisible(true);
    }
}
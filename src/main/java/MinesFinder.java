import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        var window = new GameWindow("Easy Game", new MineField(9,9,10));
        window.setVisible(true);
    }

    private void mediumGameBtnActionPerformed(ActionEvent e) {
        var window = new GameWindow("Medium Game", new MineField(16,16,40));
        window.setVisible(true);
    }

    private void hardGameBtnActionPerformed(ActionEvent e) {
        var window = new GameWindow("Hard Game", new MineField(16,30,90));
        window.setVisible(true);
    }
}
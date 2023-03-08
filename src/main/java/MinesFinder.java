import javax.swing.*;

public class MinesFinder extends JFrame {

    private JPanel mainPanel;

    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

    }
    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
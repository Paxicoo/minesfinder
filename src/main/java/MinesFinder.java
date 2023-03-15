import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinesFinder extends JFrame {

    private JPanel mainPanel;
    private JButton easyGameButton;
    private JButton mediumGameButton;
    private JButton hardGameButton;
    private JButton exitButton;
    private JLabel mediumPlayerNameLabel;
    private JLabel hardPlayerNameLabel;
    private JLabel easyPlayerNameLabel;

    private HighScoreTable easyHighScores;
    private HighScoreTable mediumHighScores;
    private HighScoreTable hardHighScores;

    private JLabel easyHighScoresLabel;
    private JLabel mediumHighScoresLabel;
    private JLabel hardHighScoresLabel;

    public MinesFinder(String title) {
        super(title);
        this.easyHighScores = new HighScoreTable();
        this.mediumHighScores = new HighScoreTable();
        this.hardHighScores = new HighScoreTable();

        readHighScoresOnDisk();

        easyPlayerNameLabel.setText(easyHighScores.getName());
        mediumPlayerNameLabel.setText(mediumHighScores.getName());
        hardPlayerNameLabel.setText(hardHighScores.getName());
        easyHighScoresLabel.setText(Long.toString(easyHighScores.getTime()/1000));
        mediumHighScoresLabel.setText(Long.toString(mediumHighScores.getTime()/1000));
        hardHighScoresLabel.setText(Long.toString(hardHighScores.getTime()/1000));

        easyHighScores.addHighScoreListener(new HighScoreListener() {
            @Override
            public void highScoresUpdated(HighScoreTable highScores) {
                easyHighScoresUpdated(highScores);
                storeHighScoresOnDisk();
                System.out.println("Easy game high scores updated");
            }
        });

        mediumHighScores.addHighScoreListener(new HighScoreListener() {
            @Override
            public void highScoresUpdated(HighScoreTable highScores) {
                mediumHighScoresUpdated(highScores);
                storeHighScoresOnDisk();
                System.out.println("Medium game high scores updated");
            }
        });

        hardHighScores.addHighScoreListener(new HighScoreListener() {
            @Override
            public void highScoresUpdated(HighScoreTable highScores) {
                hardHighScoresUpdated(highScores);
                storeHighScoresOnDisk();
                System.out.println("Hard game high scores updated");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();

        exitButton.addActionListener(this::exitBtnActionPerformed);
        easyGameButton.addActionListener(this::easyGameBtnActionPerformed);
        mediumGameButton.addActionListener(this::mediumGameBtnActionPerformed);
        hardGameButton.addActionListener(this::hardGameBtnActionPerformed);

    }

    private void storeHighScoresOnDisk() {
        ObjectOutputStream oos = null;
        try {
            File f = new File(System.getProperty("user.home") + File.separator + "minesfinder.recordes");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(easyHighScores);
            oos.writeObject(mediumHighScores);
            oos.writeObject(hardHighScores);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void readHighScoresOnDisk() {
        ObjectInputStream ois = null;
        File f = new File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                easyHighScores=(HighScoreTable) ois.readObject();
                mediumHighScores=(HighScoreTable) ois.readObject();
                hardHighScores=(HighScoreTable) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
    }

    private void easyHighScoresUpdated(HighScoreTable highScores) {
        easyPlayerNameLabel.setText(highScores.getName() + " : ");
        easyHighScoresLabel.setText(Long.toString(highScores.getTime() / 1000) + " seconds");
    }

    private void mediumHighScoresUpdated(HighScoreTable highScores) {
        mediumPlayerNameLabel.setText(highScores.getName() + " : ");
        mediumHighScoresLabel.setText(Long.toString(highScores.getTime() / 1000) + " seconds");
    }

    private void hardHighScoresUpdated(HighScoreTable highScores) {
        hardPlayerNameLabel.setText(highScores.getName() + " : ");
        hardHighScoresLabel.setText(Long.toString(highScores.getTime() / 1000) + " seconds");
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }

    private void exitBtnActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void easyGameBtnActionPerformed(ActionEvent e) {
        var window = new GameWindow("Easy Game", new MineField(9, 9, 10), easyHighScores);
        window.setVisible(true);
    }

    private void mediumGameBtnActionPerformed(ActionEvent e) {
        var window = new GameWindow("Medium Game", new MineField(16, 16, 40), mediumHighScores);
        window.setVisible(true);
    }

    private void hardGameBtnActionPerformed(ActionEvent e) {
        var window = new GameWindow("Hard Game", new MineField(16, 30, 90), hardHighScores);
        window.setVisible(true);
    }
}
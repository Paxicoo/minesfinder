import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private JPanel gamePanel;
    private MineFieldButton[][] buttons;
    private MineField mineField;

    public GameWindow(String title, MineField mineField) {
        super(title);
        this.mineField = mineField;

        var rowsNumber = mineField.getRowsNumber();
        var columnsNumber = mineField.getColumnsNumber();

        this.buttons = new MineFieldButton[rowsNumber][columnsNumber];

        gamePanel.setLayout(new GridLayout(rowsNumber, columnsNumber));

        //create and add buttons to the window
        for (int row = 0; row < rowsNumber; ++row) {
            for (int column = 0; column < columnsNumber; ++column) {
                buttons[row][column] = new MineFieldButton();
                gamePanel.add(buttons[row][column]);
            }
        }

        //set the default close operation for this window to close the window but not the application
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Sets the content pane of this window to the specified container.
        setContentPane(gamePanel);

        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();

        // Makes the window visible.
        setVisible(true);
    }

}

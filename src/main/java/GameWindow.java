import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {
    private JPanel gamePanel;
    private MineFieldButton[][] buttons;
    private MineField mineField;

    private int rowsNumber;
    private int columnsNumber;

    public GameWindow(String title, MineField mineField) {
        super(title);
        this.mineField = mineField;

        rowsNumber = mineField.getRowsNumber();
        columnsNumber = mineField.getColumnsNumber();

        this.buttons = new MineFieldButton[rowsNumber][columnsNumber];

        gamePanel.setLayout(new GridLayout(rowsNumber, columnsNumber));

        //create and add buttons to the window
        for (int row = 0; row < rowsNumber; ++row) {
            for (int column = 0; column < columnsNumber; ++column) {
                buttons[row][column] = new MineFieldButton(row, column);
                buttons[row][column].addActionListener(this::btnMineFieldActionPerformed);
                buttons[row][column].addMouseListener(mouseListener);
                buttons[row][column].addKeyListener(keyListener);
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

    //create a btnMineFieldActionPerformed method
    private void btnMineFieldActionPerformed(ActionEvent e) {
        //get the button that was clicked
        var button = (MineFieldButton) e.getSource();

        //get the row and column of the button
        var row = button.getRow();
        var column = button.getColumn();

        // mineField reveal cell
        mineField.revealCell(row, column);

        //update the buttons states
        updateButtons();

        //check if the game is over
        if (mineField.isGameOver()) {
            //if the game is not won
            if (!mineField.isGameWon()) {
                //show a message dialog
                JOptionPane.showMessageDialog(this, "Oops! Mine Exploded...","Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //show a message dialog
                JOptionPane.showMessageDialog(this, "Congratulations! You managed to find all the mines in "
                        + (mineField.getGameDuration()/1000)+" seconds.","Victory", JOptionPane.INFORMATION_MESSAGE);
            }
            // set visible false
            setVisible(false);
        }


    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() != MouseEvent.BUTTON3){
                return;
            }

            var button = (MineFieldButton) e.getSource();

            var row = button.getRow();
            var column = button.getColumn();

            markCell(row, column);
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    //add keylistener to the window that allows user to navigate through the cells using the arrow keys and
    //mark cells as having mines using the 'M' key
    KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            var button = (MineFieldButton) e.getSource();

            var row = button.getRow();
            var column = button.getColumn();

            switch(e.getKeyCode()){
                case KeyEvent.VK_UP -> buttons[--row < 0 ? rowsNumber - 1 : row][column].requestFocus();
                case KeyEvent.VK_DOWN -> buttons[(row + 1) % rowsNumber][column].requestFocus();
                case KeyEvent.VK_LEFT -> buttons[row][--column < 0 ? columnsNumber - 1 : column].requestFocus();
                case KeyEvent.VK_RIGHT -> buttons[row][(column + 1) % columnsNumber].requestFocus();
                case KeyEvent.VK_M -> {
                    markCell(row, column);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    //create a method markCell
    private void markCell(int row, int column) {
        var cellState = mineField.getState(row, column);

        switch(cellState){
            case MineField.COVERED -> mineField.markAsHavingMine(row, column);
            case MineField.MARKED -> mineField.markAsSuspect(row, column);
            case MineField.DOUBT -> mineField.markOff(row, column);
        }

        updateButtons();
    }

    //create a method to update the buttons states
    public void updateButtons() {
        for (int row = 0; row < mineField.getRowsNumber(); row++) {
            for (int column = 0; column < mineField.getColumnsNumber(); column++) {
                buttons[row][column].setState(mineField.getState(row, column));
            }
        }
    }

}

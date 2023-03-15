import javax.swing.*;
import java.awt.*;

public class MineFieldButton extends JButton {
    private int state;

    private int row;
    private int column;

    public MineFieldButton(int row, int column) {
        this.row = row;
        this.column = column;
        this.state = MineField.COVERED;
    }

    //set state of cell
    public void setState(int state) {
        this.state = state;
        switch (state) {
            case MineField.COVERED:
                setText("     ");
                setBackground(null);
                break;
            case MineField.DOUBT:
                setText("?");
                setBackground(Color.YELLOW);
                break;
            case MineField.MARKED:
                setText("!");
                setBackground(Color.RED);
                break;
            case MineField.EXPLODED:
                setText("*");
                setBackground(Color.ORANGE);
                break;
            case MineField.EMPTY:
                setText("");
                setBackground(Color.LIGHT_GRAY);
                break;
            default:
                //text is 1 through 8 depending on the number of mines in the surrounding cells
                setText(Integer.toString(state));
                setBackground(Color.LIGHT_GRAY);
        }

    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}

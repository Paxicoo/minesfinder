public class MineField { // class for the minefield
    private boolean[][] mines; // true if there is a mine in the cell

    public static final int EMPTY = 0; // empty cell
    /* from 1 through 8 are the number of mines in the surrounding cells */
    public static final int COVERED = 9; // covered cell
    public static final int DOUBT = 10; // doubtful cell
    public static final int MARKED = 11; // marked cell
    public static final int EXPLODED = 12; // exploded mine

    private int[][] state; // the state of each cell

    private int rowsNumber; // number of rows in the minefield
    private int columnsNumber; // number of columns in the minefield
    private int minesNumber; // number of mines in the minefield

    public MineField(int rowsNumber, int columnsNumber, int minesNumber) { // constructor
        this.rowsNumber = rowsNumber; // rowsNumber is the number of rows in the minefield
        this.columnsNumber = columnsNumber; // columnsNumber is the number of columns in the minefield
        this.minesNumber = minesNumber; // minesNumber is the number of mines in the minefield

        this.mines = new boolean[rowsNumber][columnsNumber]; // false by default
        this.state = new int[rowsNumber][columnsNumber]; // 0 by default

    }

    //get state of cell
    public int getState(int row, int column) {
        return state[row][column]; // returns the state of the cell
    }

    //get mines
    public boolean hasMine(int row, int column) {
        return mines[row][column]; // returns true if there is a mine in the cell
    }

    //get rowsNumber
    public int getRowsNumber() {
        return rowsNumber; // returns the number of rows in the minefield
    }

    //get columnsNumber
    public int getColumnsNumber() {
        return columnsNumber; // returns the number of columns in the minefield
    }


}

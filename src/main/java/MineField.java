import java.util.Random;

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

    private boolean firstClick = true; // true if it is the first click

    private boolean gameOver;
    private boolean gameWon;

    public MineField(int rowsNumber, int columnsNumber, int minesNumber) { // constructor
        this.rowsNumber = rowsNumber; // rowsNumber is the number of rows in the minefield
        this.columnsNumber = columnsNumber; // columnsNumber is the number of columns in the minefield
        this.minesNumber = minesNumber; // minesNumber is the number of mines in the minefield

        this.mines = new boolean[rowsNumber][columnsNumber]; // false by default
        this.state = new int[rowsNumber][columnsNumber]; // 0 by default

        this.gameOver = false;
        this.gameWon = false;

        //set the state of each cell to COVERED
        for (int row = 0; row < rowsNumber; ++row) {
            for (int column = 0; column < columnsNumber; ++column) {
                state[row][column] = COVERED;
            }
        }


    }

    private void placeMines(int firstRow, int firstColumn) { // places mines in the minefield
        var random = new Random(); // random number generator
        var row = 0; // row of the mine
        var column = 0; // column of the mine

        //place mines
        for(var i = 0; i < minesNumber; ++i) {
            do {
                row = random.nextInt(rowsNumber); // random row
                column = random.nextInt(columnsNumber); // random column
            } while (mines[row][column] || (row == firstRow && column == firstColumn)); // if there is already a mine in the cell or if it is the first click

            mines[row][column] = true; // place a mine in the cell
        }

    }

    //reveal cell
    public void revealCell(int row, int column){
        if (state[row][column] < COVERED || gameOver) {
            return;
        }

        if(firstClick) {
            firstClick = false;
            placeMines(row, column);
        }

        //if cell has mine, change state to EXPLODED, set gameOver to true and inform the user that he lost
        if (mines[row][column]) {
            state[row][column] = EXPLODED;
            gameOver = true;
            gameWon = false;
            return;
        }

        //if cell is empty, change state to EMPTY
        if (countSurroundingMines(row, column) == 0) {
            state[row][column] = EMPTY;
            revealSurroundingCells(row, column);
        } else {
            //if cell is not empty, change state to the number of mines in the surrounding cells
            state[row][column] = countSurroundingMines(row, column);
        }

    }

    //create private method to reveal surrounding cells
    private void revealSurroundingCells(int row, int column) {
        //reveal surrounding cells
        for (var i = Math.max(0, row - 1); i < Math.min(rowsNumber, row + 2); ++i) {
            for (var j = Math.max(0, column - 1); j < Math.min(columnsNumber, column + 2); ++j)
            {
                revealCell(i, j);
            }
        }
    }

    //create private method isWon
    //TODO page 21 worksheet 2
    private boolean isWon() {
        //check if all cells are revealed
        for (var row = 0; row < rowsNumber; ++row) {
            for (var column = 0; column < columnsNumber; ++column) {
                if (state[row][column] == COVERED && !mines[row][column]) {
                    return false;
                }
            }
        }

        return true;
    }

    //create private method to count the number of mines in the surrounding cells
    private int countSurroundingMines(int row, int column) {
        var count = 0; // number of mines in the surrounding cells

        //count the number of mines in the surrounding cells
        for (var i = Math.max(0, row - 1); i < Math.min(rowsNumber, row + 2); ++i) {
            for (var j = Math.max(0, column - 1); j < Math.min(columnsNumber, column + 2); ++j)
            {
                if (mines[i][j]) {
                    ++count;
                }
            }
        }

        return count;
    }

    //create isGameOver method
    public boolean isGameOver() {
        return gameOver; // returns true if the game is over
    }

    //create isGameWon method
    public boolean isGameWon() {
        return gameWon; // returns true if the game is won
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

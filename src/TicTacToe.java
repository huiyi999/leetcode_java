/**
 * 348. Design Tic-Tac-Toe
 * <p>
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 * <p>
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 * <p>
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
 */
public class TicTacToe {

    int[] rows;  // rows[i] stores the number of times a player has marked a cell on the ith row.
    int[] cols;
    int diagonal; // store how many times a cell has been marked on each of the diagonals.
    int antiDiagonal;

    public TicTacToe(int n) {
        // initialize arrays
        this.rows = new int[n];
        this.cols = new int[n];

    }

    // a move is always valid and placed on an empty cell
    public int move(int row, int col, int player) {

        int currentPlayer = (player == 1) ? 1 : -1;  //  only 2 players, use 1 and -1 mark different player
        // increment the count when player 1 marks a cell and decrement the count when player 2 marks a cell.

        // 1. determine whether a player has marked all of the cells in a row or column.
        // update currentPlayer in rows and cols arrays
        rows[row] += currentPlayer;
        cols[col] += currentPlayer;

        // 2. determine whether a player has marked all of the cells on the main diagonal or anti-diagonal.
        // update diagonal
        if (row == col)
            diagonal += currentPlayer;

        //update anti diagonal
        if (col == (cols.length - row - 1))   // n-1 -row == col
            antiDiagonal += currentPlayer;

        int n = rows.length;

        // check if the current player wins, check the absolute values.
        // 1. if the value of rows[i] is equal to n, player 1 has marked ith row n times
        // 2. if one player mark entire diagonal, or antidiagonal cells, the result will be n or -n
        // check if the value of variable diagonal for that player is equal to n
        if (Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||  //
                Math.abs(antiDiagonal) == n)
            return player;

        return 0;  // no one wins
    }
}

// time O(1)
// space O(n)

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

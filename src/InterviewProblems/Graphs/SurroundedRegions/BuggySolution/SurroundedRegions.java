package InterviewProblems.Graphs.SurroundedRegions.BuggySolution;

import java.util.Arrays;

public class SurroundedRegions {

    // Let m = number of rows in the board, n = number of columns in the board
    //
    // Time: O(m * n)
    // --> O(m * n) for 1st pass
    // --> O(m * n) for 2nd pass
    // --> O(m * n) for 3rd pass
    // Space: O(m * n)
    // --> O(m * n) max cells for the call stack space??

    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();

        // Example 1: 1 x 1 board
        // Input: board = [["X"]]
        // Output: [["X"]]
        char[][] board1 = {{'X'}};
        surroundedRegions.solve(board1);
        System.out.println(Arrays.deepToString(board1));
        // [["X"]]

        // Example 2: 1 O-cell is 4-adjacent to X's.
        // Input: board = [["X", "X", "X"], ["X", "O", "X"], ["X", "X", "X"]]
        // Output: [["X", "X", "X"], ["X", "X", "X"], ["X", "X", "X"]]
        char[][] board2 = {
            {'X', 'X', 'X'},
            {'X', 'O', 'X'},
            {'X', 'X', 'X'}
        };
        surroundedRegions.solve(board2);
        System.out.println(Arrays.deepToString(board2));
        // [["X", "X", "X"], ["X", "X", "X"], ["X", "X", "X"]]

        // Example 3: O-cell on a border
        // Input: board = [["O", "O", "O"], ["O", "X", "O"], ["O", "O", "O"]]
        // Output: [["O", "O", "O"], ["O", "X", "O"], ["O", "O", "O"]]
        char[][] board3 = {
            {'O', 'O', 'O'},
            {'O', 'X', 'O'},
            {'O', 'O', 'O'}
        };
        surroundedRegions.solve(board3);
        System.out.println(Arrays.deepToString(board3));
        // [["O", "O", "O"], ["O", "X", "O"], ["O", "O", "O"]]

        // Example 4:
        // Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
        // Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
        // Explanation: Notice that an 'O' should not be flipped if:
        // - It is on the border, or
        // - It is adjacent to an 'O' that should not be flipped.
        // The bottom 'O' is on the border, so it is not flipped.
        // The other three 'O' form a surrounded region, so they are flipped.
        char[][] board4 = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };
        surroundedRegions.solve(board4);
        System.out.println(Arrays.deepToString(board4));
        // [["X","X","X","X"], ["X","X","X","X"], ["X","X","X","X"], ["X","O","X","X"]]
    }

    /**
     * Given an m x n matrix board containing 'X' and 'O', capture all regions that
     * are 4-directionally surrounded by 'X'.
     *
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     *
     * Preconditions:
     * - m == board.length
     * - n == board[i].length
     * - 1 <= m, n <= 200
     * - board[i][j] is 'X' or 'O'.
     */
    public void solve(char[][] board) {
        int numRows = board.length;
        int numColumns = board[0].length;
        boolean[][] cellsVisited = new boolean[numRows][numColumns];

        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                exploreCell(board, rowNum, columnNum, cellsVisited);
                cellsVisited[rowNum][columnNum] = true;
            }
        }
    }

    private void exploreCell(char[][] board, int rowNum, int columnNum, boolean[][] cellsVisited) {
        if (!isInBounds(board, rowNum, columnNum)) {
            return;
        }

        // Notice that an 'O' should not be flipped if:
        // - It is on the border, or...
        if (isOnBorder(board, rowNum, columnNum)) {
            return;
        }

        // - It is adjacent to an 'O' that should not be flipped.

        // ensure current cell is 'X'???
        if (board[rowNum][columnNum] == 'X') {
            return;
        } else if (cellsVisited[rowNum][columnNum]) {
            return;
        }
        cellsVisited[rowNum][columnNum] = true;

        // check adjacent cells. if it's an O, recurse into that cell?
        if (board[rowNum - 1][columnNum] == 'O') {
            exploreCell(board, rowNum - 1, columnNum, cellsVisited);
        }

        if (board[rowNum][columnNum + 1] == 'O') {
            exploreCell(board, rowNum, columnNum + 1, cellsVisited);
        }

        if (board[rowNum + 1][columnNum] == 'O') {
            exploreCell(board, rowNum + 1, columnNum, cellsVisited);
        }

        if (board[rowNum][columnNum - 1] == 'O') {
            exploreCell(board, rowNum, columnNum - 1, cellsVisited);
        }

        if (board[rowNum - 1][columnNum] == 'X' && board[rowNum + 1][columnNum] == 'X'
                && board[rowNum + 1][columnNum] == 'X' && board[rowNum][columnNum - 1] == 'X') {
            board[rowNum][columnNum] = 'X';
        }
    }

    private boolean isInBounds(char[][] board, int rowNum, int columnNum) {
        int numRows = board.length;
        int numColumns = board[0].length;

        return  0 <= rowNum && rowNum < numRows &&
                0 <= columnNum && columnNum < numColumns;
    }

    private boolean isOnBorder(char[][] board, int rowNum, int columnNum) {
        int numRows = board.length;
        int numColumns = board[0].length;

        return  (rowNum == 0 || rowNum == numRows - 1) ||  // top and bottom border
                (columnNum == 0 || columnNum == numColumns - 1);  // left and right border
    }
}

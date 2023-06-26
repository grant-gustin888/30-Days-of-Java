package InterviewProblems.Graphs.SurroundedRegions.OriginalSolution;

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

        // 1. (DFS) capture all unsurrounded regions by marking unsurrounded regions of
        // O's with "T"
        // TODO: I can technically just iterate through the border cells here.
        //  But we'll iterate through all of the cells for simplicity.
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (board[rowNum][columnNum] == 'O' && cellIsOnBorder(board, rowNum, columnNum)) {
                    captureCell(board, rowNum, columnNum);
                }
            }
        }

        // 2. Capture surrounded regions by marking surrounded regions of
        // O's with X's.
        // - ignore X's, dfs on O's, and ignore all O's marked "T"??
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (board[rowNum][columnNum] == 'O') {
                    board[rowNum][columnNum] = 'X';
                }
            }
        }

        // 3. Uncapture surrounded regions by marking unsurrounded regions of
        // O's with "T"'s back to O's.
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (board[rowNum][columnNum] == 'T') {
                    board[rowNum][columnNum] = 'O';
                }
            }
        }
    }

    private void captureCell(char[][] board, int rowNum, int columnNum) {
        if (!isInBounds(board, rowNum, columnNum)) {
            return;
        }

        if (board[rowNum][columnNum] != 'O') {
            return;
        }

        board[rowNum][columnNum] = 'T';
        captureCell(board, rowNum + 1, columnNum);
        captureCell(board, rowNum - 1, columnNum);
        captureCell(board, rowNum, columnNum + 1);
        captureCell(board, rowNum, columnNum - 1);
    }

    private boolean isInBounds(char[][] board, int rowNum, int columnNum) {
        int numRows = board.length;
        int numColumns = board[0].length;
        return  0 <= rowNum && rowNum < numRows &&
                0 <= columnNum && columnNum < numColumns;
    }

    private boolean cellIsOnBorder(char[][] board, int rowNum, int columnNum) {
        int numRows = board.length;
        int numColumns = board[0].length;

        return  (rowNum == 0 || rowNum == numRows - 1) ||  // top and bottom border
                (columnNum == 0 || columnNum == numColumns - 1);  // left and right border
    }
}

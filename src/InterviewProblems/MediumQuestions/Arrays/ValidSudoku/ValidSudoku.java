package InterviewProblems.MediumQuestions.CurrentlyDoingTheseQuestions.ValidSudoku;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    // Time: O(1)
    // --> O(1) to determine if all row constraints hold for all 9 rows
    // --> O(1) to determine if all column constraints hold for all 9 columns
    // --> O(1) to determine if all 3x3 subsquare constraints hold for all 9 subsquares
    // Space: O(1)
    // --> We only need to store up to 9 numbers in our hashset of numbers seen before
    // in any row, column, or subsquare.

    public static void main(String[] args) {
        ValidSudoku validSudoku = new ValidSudoku();

        // satisfies all constraints
        // Input: board =
        // [["5", "3", ".", ".", "7", ".", ".", ".", "."],
        //  ["6", ".", ".", "1", "9", "5", ".", ".", "."],
        //  [".", "9", "8", ".", ".", ".", ".", "6", "."],
        //  ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
        //  ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
        //  ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
        //  [".", "6", ".", ".", ".", ".", "2", "8", "."],
        //  [".", ".", ".", "4", "1", "9", ".", ".", "5"],
        //  [".", ".", ".", ".", "8", ".", ".", "7", "9"]]
        // Output: true
        char[][] board1 = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValidSudoku1 = validSudoku.isValidSudoku(board1);
        System.out.println(isValidSudoku1);  // true

        // fails subsquare constraint
        // Input: board =
        // [["8", "3", ".", ".", "7", ".", ".", ".", "."],
        //  ["6", ".", ".", "1", "9", "5", ".", ".", "."],
        //  [".", "9", "8", ".", ".", ".", ".", "6", "."],
        //  ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
        //  ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
        //  ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
        //  [".", "6", ".", ".", ".", ".", "2", "8", "."],
        //  [".", ".", ".", "4", "1", "9", ".", ".", "5"],
        //  [".", ".", ".", ".", "8", ".", ".", "7", "9"]]
        // Output: false
        // Explanation: Same as Example 1, except with the 5 in the top left corner
        // being modified to 8.
        // Since there are two 8's in the top left 3x3 sub-box, it is invalid.
        char[][] board2 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValidSudoku2 = validSudoku.isValidSudoku(board2);
        System.out.println(isValidSudoku2);  // false

        // fails row constraint
        // Input: board =
        // [["5", ".", ".", "5", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."]]
        // Output: false
        char[][] board3 = {
            {'5', '.', '.', '5', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        boolean isValidSudoku3 = validSudoku.isValidSudoku(board3);
        System.out.println(isValidSudoku3);  // false

        // fails column constraint
        // Input: board =
        // [["8", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  ["8", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."],
        //  [".", ".", ".", ".", ".", ".", ".", ".", "."]]
        // Output: false
        char[][] board4 = {
            {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        boolean isValidSudoku4 = validSudoku.isValidSudoku(board4);
        System.out.println(isValidSudoku4);  // false
    }

    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to
     * be validated according to the following rules:<br>
     * 1. Each row must contain the digits 1-9 without repetition.<br>
     * 2. Each column must contain the digits 1-9 without repetition.<br>
     * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits
     * 1 - 9 without repetition.<br><br>
     *
     * Note:<br>
     * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.<br>
     * - Only the filled cells need to be validated according to the mentioned rules.<br><br>
     *
     * Preconditions:<br>
     * - board.length == 9.<br>
     * - board[i].length == 9.<br>
     * - board[i][j] is a digit 1-9 or '.'.
     */
    public boolean isValidSudoku(char[][] board) {
        return allRowConstraintsHold(board) && allColumnConstraintsHold(board) && allSubsquareConstraintsHold(board);
    }

    private boolean allRowConstraintsHold(char[][] board) {
        for (int rowNum = 0; rowNum < board.length; rowNum++) {
            Set<Character> numbersSeenInCurrentRow = new HashSet<>();
            for (int columnNum = 0; columnNum < board[0].length; columnNum++) {
                char currentCell = board[rowNum][columnNum];
                if (currentCell == '.') {
                    continue;
                }

                if (numbersSeenInCurrentRow.contains(currentCell)) {
                    return false;
                } else {
                    numbersSeenInCurrentRow.add(currentCell);
                }
            }
        }
        return true;
    }

    private boolean allColumnConstraintsHold(char[][] board) {
        for (int columnNum = 0; columnNum < board[0].length; columnNum++) {
            Set<Character> numbersSeenInCurrentColumn = new HashSet<>();
            for (int rowNum = 0; rowNum < board.length; rowNum++) {
                char currentCell = board[rowNum][columnNum];
                if (currentCell == '.') {
                    continue;
                }

                if (numbersSeenInCurrentColumn.contains(currentCell)) {
                    return false;
                } else {
                    numbersSeenInCurrentColumn.add(currentCell);
                }
            }
        }
        return true;
    }

    private boolean allSubsquareConstraintsHold(char[][] board) {
        int[][] subsquareTopLeftCorners = {
            {0, 0}, {0, 3}, {0, 6},
            {3, 0}, {3, 3}, {3, 6},
            {6, 0}, {6, 3}, {6, 6}
        };
        for (int[] subsquareTopLeftCorner : subsquareTopLeftCorners) {
            if (!subsquareConstraintHolds(board, subsquareTopLeftCorner)) {
                return false;
            }
        }
        return true;
    }

    private boolean subsquareConstraintHolds(char[][] board, int[] subsquareStart) {
        int rowStart = subsquareStart[0];
        int columnStart = subsquareStart[1];
        Set<Character> numbersSeenInCurrentSubsquare = new HashSet<>();
        for (int rowNum = rowStart; rowNum < rowStart + 3; rowNum++) {
            for (int columnNum = columnStart; columnNum < columnStart + 3; columnNum++) {
                char currentCell = board[rowNum][columnNum];
                if (currentCell == '.') {
                    continue;
                }

                if (numbersSeenInCurrentSubsquare.contains(currentCell)) {
                    return false;
                } else {
                    numbersSeenInCurrentSubsquare.add(currentCell);
                }
            }
        }
        return true;
    }
}

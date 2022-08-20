package InterviewProblems.Matrix.LargestLocalValuesInAMatrix;

import java.util.Arrays;

public class LargestLocalValues {

    // Let n = the number of rows and columns in the matrix.
    //
    // Time: O(n ^ 2)
    // --> We must traverse (n - 2) * (n - 2) cells from the grid matrix to
    // extract the largest local values from a 3 x 3 matrix starting from the
    // top left corner of the current cell.
    // Space: O(n ^ 2)
    // --> We must store an (n - 2) * (n - 2) matrix of largest local values.

    public static void main(String[] args) {
        LargestLocalValues largestLocalValues = new LargestLocalValues();

        // Input: grid = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        // Output: [[9]]
        int[][] grid1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] largestLocalValues1 = largestLocalValues.largestLocal(grid1);
        System.out.println(Arrays.deepToString(largestLocalValues1));  // [[9]]

        // Input: grid = [[9, 9, 8, 1], [5, 6, 2, 6], [8, 2, 6, 4], [6, 2, 2, 2]]
        // Output: [[9, 9], [8, 6]]
        // Explanation: The diagram above shows the original matrix and the generated matrix.
        // Notice that each value in the generated matrix corresponds to the largest value of
        // a contiguous 3 x 3 matrix in grid.
        int[][] grid2 = {
            {9, 9, 8, 1},
            {5, 6, 2, 6},
            {8, 2, 6, 4},
            {6, 2, 2, 2}
        };
        int[][] largestLocalValues2 = largestLocalValues.largestLocal(grid2);
        System.out.println(Arrays.deepToString(largestLocalValues2));  // [[9, 9], [8, 6]]

        // Input: grid = [[1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 2, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1]]
        // Output: [[2, 2, 2], [2, 2, 2], [2, 2, 2]]
        // Explanation: Notice that the 2 is contained within every contiguous 3 x 3 matrix in grid.
        int[][] grid3 = {
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 2, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };
        int[][] largestLocalValues3 = largestLocalValues.largestLocal(grid3);
        System.out.println(Arrays.deepToString(largestLocalValues3));  // [[2, 2, 2], [2, 2, 2], [2, 2, 2]]
    }

    /**
     * You are given an n x n integer matrix grid.<br><br>
     *
     * Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:<br>
     * - maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid centered
     * around row i + 1 and column j + 1.<br><br>
     *
     * In other words, we want to find the largest value in every contiguous 3 x 3 matrix in grid.<br><br>
     *
     * Return the generated matrix.<br><br>
     *
     * Preconditions:<br>
     * - n == grid.length == grid[i].length.<br>
     * - 3 <= n <= 100.<br>
     * - 1 <= grid[i][j] <= 100.
     */
    public int[][] largestLocal(int[][] grid) {
        int[][] localValuesMatrix = new int[grid.length - 2][grid.length - 2];
        for (int rowNum = 0; rowNum < grid.length - 2; rowNum++) {
            for (int columnNum = 0; columnNum < grid[0].length - 2; columnNum++) {
                localValuesMatrix[rowNum][columnNum] = getLargestLocalValueAtCell(grid, rowNum, columnNum);
            }
        }
        return localValuesMatrix;
    }

    /**
     * Return the largest local value starting at cell (startingRowNum, startingColumnNum).
     */
    private int getLargestLocalValueAtCell(int[][] grid, int startingRowNum, int startingColumnNum) {
        int largestLocalValue = Integer.MIN_VALUE;
        for (int rowNum = startingRowNum; rowNum < startingRowNum + 3; rowNum++) {
            for (int columnNum = startingColumnNum; columnNum < startingColumnNum + 3; columnNum++) {
                if (grid[rowNum][columnNum] > largestLocalValue) {
                    largestLocalValue = grid[rowNum][columnNum];
                }
            }
        }
        return largestLocalValue;
    }
}

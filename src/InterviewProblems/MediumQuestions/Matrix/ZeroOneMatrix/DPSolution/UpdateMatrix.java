package InterviewProblems.MediumQuestions.Matrix.ZeroOneMatrix.DPSolution;

import java.util.Arrays;

public class UpdateMatrix {

    // inspiration, DP solution: https://leetcode.com/problems/01-matrix/solution/

    // Let r = number of rows, c = number of columns.
    //
    // Time: O(rc)
    // --> O(rc) to populate distance matrix with Integer.MAX_VALUE.
    // --> O(rc) for first pass: traversing left and top
    // --> O(rc) for second pass: traversing right and bottom
    // Space: O(rc)
    // --> We must store the distance matrix.

    public static void main(String[] args) {
        UpdateMatrix updateMatrix = new UpdateMatrix();

        // Input: matrix = [[0]]
        // Output: [[0]]
        int[][] matrix1 = {{0}};
        int[][] matrixDistances1 = updateMatrix.updateMatrix(matrix1);
        System.out.println(Arrays.deepToString(matrixDistances1));
        // [[0]]

        // TODO: This matrix violates the precondition -- matrix2 must contain at least 1 zero.
        // TODO: It returns [[2147483647]] instead
        // Input: matrix = [[1]]
        // Output: [[1]]
        // int[][] matrix2 = {{1}};
        // int[][] matrixDistances2 = updateMatrix.updateMatrix(matrix2);
        // System.out.println(Arrays.deepToString(matrixDistances2));
        // [[1]]

        // Input: matrix = [[0, 1], [1, 0]]
        // Output: [[0, 1], [1, 0]]
        int[][] matrix3 = {{0, 1}, {1, 0}};
        int[][] matrixDistances3 = updateMatrix.updateMatrix(matrix3);
        System.out.println(Arrays.deepToString(matrixDistances3));
        // [[0, 1],
        //  [1, 0]]

        // Input: mat = [[0, 0, 0], [0, 1, 0], [0, 0, 0]]
        // Output: [[0, 0, 0], [0, 1, 0], [0, 0, 0]]
        int[][] matrix4 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] matrixDistances4 = updateMatrix.updateMatrix(matrix4);
        System.out.println(Arrays.deepToString(matrixDistances4));
        // [[0, 0, 0],
        //  [0, 1, 0],
        //  [0, 0, 0]]

        // Input: mat = [[0, 0, 0], [0, 1, 0], [1, 1, 1]]
        // Output: [[0, 0, 0], [0, 1, 0], [1, 2, 1]]
        int[][] matrix5 = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };
        int[][] matrixDistances5 = updateMatrix.updateMatrix(matrix5);
        System.out.println(Arrays.deepToString(matrixDistances5));
        // [[0, 0, 0],
        //  [0, 1, 0],
        //  [1, 2, 1]]

        // TODO: This solution is wrong!
        // TODO: counterexample: matrix = [[0, 1, 0], [0, 1, 0], [0, 1, 0]]
    }

    /**
     * Given an m x n binary matrix mat, return the distance of the
     * nearest 0 for each cell.<br><br>
     *
     * The distance between two adjacent cells is 1.<br><br>
     *
     * Preconditions:<br>
     * - m == mat.length<br>
     * - n == mat[i].length<br>
     * - 1 <= m, n <= 10 ^ 4.<br>
     * - 1 <= m * n <= 10 ^ 4.<br>
     * - mat[i][j] is either 0 or 1.<br>
     * - There is at least one 0 in mat.<br>
     */
    public int[][] updateMatrix(int[][] mat) {
        int numRows = mat.length;
        int numColumns = mat[0].length;

        // Fill distance matrix with Integer.MAX_VALUE.
        int[][] distances = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        // first pass: check for top and left
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (mat[i][j] == 0) {
                    distances[i][j] = 0;
                } else if (i > 0) {  // and mat[i][j] != 0
                    distances[i][j] = Math.min(distances[i][j], distances[i - 1][j] + 1);
                } else if (j > 0) {  // and mat[i][j] != 0
                    distances[i][j] = Math.min(distances[i][j], distances[i][j - 1] + 1);
                }
            }
        }

        // second pass: check for bottom and right
        for (int i = numRows - 1; i >= 0; i--) {
            for (int j = numColumns - 1; j >= 0; j--) {
                if (i < numRows - 1) {
                    distances[i][j] = Math.min(distances[i][j], distances[i + 1][j] + 1);
                } else if (j < numColumns - 1) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][j + 1] + 1);
                }
            }
        }
        return distances;
    }
}

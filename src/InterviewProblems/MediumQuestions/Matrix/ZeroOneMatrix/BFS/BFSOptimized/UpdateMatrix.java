package InterviewProblems.MediumQuestions.Matrix.ZeroOneMatrix.BFS.BFSOptimized;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UpdateMatrix {

    // inspiration, see BFS solution: https://leetcode.com/problems/01-matrix/

    // Let r = number of rows, c = number of columns.
    //
    // Time: O(rc)
    // --> O(rc) to populate distance matrix with Integer.MAX_VALUE.
    // --> O(rc) to enqueue all 0-cells.
    // --> O(rc) to iterate all rc cells, since we visit and enqueue each cell at most once.
    // Space: O(rc)
    // --> We must store all the visited cells in the matrix.

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

        // Add all 0's to queue
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (mat[rowNum][columnNum] == 0) {
                    distances[rowNum][columnNum] = 0;
                    List<Integer> newPoint = getPoint(rowNum, columnNum);
                    queue.add(newPoint);
                }
            }
        }

        // BFS on all 0's and neighbouring 1's that are closer to 0s.
        while (!queue.isEmpty()) {
            List<Integer> currentPoint = queue.remove();

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] direction : directions) {
                int currentRow = currentPoint.get(0);
                int currentColumn = currentPoint.get(1);
                int newRow = currentRow + direction[0];
                int newColumn = currentColumn + direction[1];
                if (isInBounds(newRow, newColumn, numRows, numColumns) &&
                        distances[newRow][newColumn] > distances[currentRow][currentColumn] + 1) {
                    distances[newRow][newColumn] = distances[currentRow][currentColumn] + 1;
                    List<Integer> newPoint = getPoint(newRow, newColumn);
                    queue.add(newPoint);
                }
            }
        }
        return distances;
    }

    private boolean isInBounds(int newRowNum, int newColumnNum, int numRows, int numColumns) {
        return  0 <= newRowNum && newRowNum < numRows &&
                0 <= newColumnNum && newColumnNum < numColumns;
    }

    private static List<Integer> getPoint(int newRow, int newColumn) {
        List<Integer> newPoint = new LinkedList<>();
        newPoint.add(newRow);
        newPoint.add(newColumn);
        return newPoint;
    }
}

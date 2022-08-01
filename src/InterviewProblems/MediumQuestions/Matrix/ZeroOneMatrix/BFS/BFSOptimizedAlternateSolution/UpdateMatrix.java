package InterviewProblems.MediumQuestions.Matrix.ZeroOneMatrix.BFS.BFSOptimizedAlternateSolution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class UpdateMatrix {

    // inspiration, see BFS solution:
    // https://leetcode.com/problems/01-matrix/discuss/1901791/Python-DFS-with-explanation-easy-to-read

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
                    queue.add(getPoint(rowNum, columnNum, 0));
                    distances[rowNum][columnNum] = 0;
                }
            }
        }

        // BFS on all 0's and neighbouring 1's that are closer to 0s.
        Set<List<Integer>> pointsVisited = new HashSet<>();
        while (!queue.isEmpty()) {
            List<Integer> currentPoint = queue.remove();
            int rowNum = currentPoint.get(0);
            int columnNum = currentPoint.get(1);
            int distance = currentPoint.get(2);

            List<Integer> point = Arrays.asList(rowNum, columnNum);
            if (pointsVisited.contains(point)) {  // or if mat[rowNum][columnNum] == Integer.MAX_VALUE
                continue;
            }
            pointsVisited.add(point);
            if (distances[rowNum][columnNum] == Integer.MAX_VALUE) {
                distances[rowNum][columnNum] = distance;
            }

            // if in bounds, and no result from another 0 yet
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] direction : directions) {
                int currentRow = currentPoint.get(0);
                int currentColumn = currentPoint.get(1);
                int newRow = currentRow + direction[0];
                int newColumn = currentColumn + direction[1];
                // List<Integer> newPoint = Arrays.asList(newRow, newColumn);  TODO: This is optional
                if (isInBounds(newRow, newColumn, numRows, numColumns) &&
                        // !equals(currentPoint, newPoint) &&
                        distances[newRow][newColumn] == Integer.MAX_VALUE) {
                    queue.add(getPoint(newRow, newColumn, distance + 1));
                }
            }
        }
        return distances;
    }

    private boolean isInBounds(int newRow, int newColumn, int numRows, int numColumns) {
        return  0 <= newRow && newRow < numRows &&
                0 <= newColumn && newColumn < numColumns;
    }

    private boolean equals(List<Integer> point1, List<Integer> point2) {
        return point1.get(0) == point2.get(0) && point1.get(1) == point2.get(1);
    }

    private static List<Integer> getPoint(int newRow, int newColumn, int distance) {
        List<Integer> newPoint = new LinkedList<>();
        newPoint.add(newRow);
        newPoint.add(newColumn);
        newPoint.add(distance);
        return newPoint;
    }
}

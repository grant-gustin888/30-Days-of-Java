package InterviewProblems.MediumQuestions.Matrix.ZeroOneMatrix.BFS.BFSUnoptimized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class UpdateMatrix {

    // Let r = number of rows, c = number of columns.
    //
    // Time: O((rc) ^ 2)
    // --> O(rc) to determine 0-cells and 1-cells.
    // --> O((rc) ^ 2) to perform BFS on all rc cells, since we may have to traverse the entire
    // matrix for each 1-cell we encounter.
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
        // fill in 0 cells with 0 distance, since 0 cells are 0 units away
        // from the nearest 0 cell.
        int[][] distancesMatrix = new int[mat.length][mat[0].length];
        List<List<Integer>> coordinatesOfOnes = new ArrayList<>();
        for (int rowNum = 0; rowNum < mat.length; rowNum++) {
            for (int colNum = 0; colNum < mat[0].length; colNum++) {
                if (mat[rowNum][colNum] == 0) {
                    distancesMatrix[rowNum][colNum] = 0;
                } else {
                    distancesMatrix[rowNum][colNum] = Integer.MAX_VALUE;
                    // Record all coordinates of 1 cells, so we can use BFS to each point.
                    List<Integer> coordinate = new ArrayList<>();
                    coordinate.add(rowNum);
                    coordinate.add(colNum);
                    coordinatesOfOnes.add(coordinate);
                }
            }
        }

        // For each 1 cell, find its distance from the nearest 0 using BFS,
        // and (optionally) populate the dp matrix?
        for (List<Integer> point : coordinatesOfOnes) {
            int shortestDistance = breadthFirstSearch(mat, point);
            int rowNum = point.get(0);
            int columnNum = point.get(1);
            distancesMatrix[rowNum][columnNum] = shortestDistance;
        }
        return distancesMatrix;
    }

    /**
     * Return the shortest distance from the given point to the nearest 0 cell.<br><br>
     *
     * Preconditions:<br>
     * - startingPoint is in bounds.<br>
     * - startingPoint is not a 0 cell, but rather a 1 cell.
     */
    private int breadthFirstSearch(int[][] mat, List<Integer> startingPoint) {
        // start from source vertex
        Set<List<Integer>> pointsAlreadyVisited = new HashSet<>();
        int shortestDistance = 0;
        Queue<List<Integer>> pointsToVisit = new LinkedList<>();
        pointsToVisit.add(startingPoint);

        // process each point in the queue...
        while (!pointsToVisit.isEmpty()) {
            // Take a snapshot of current queue to find out how many times we need to loop
            // before incrementing level.
            int cellsAtCurrentLevel = pointsToVisit.size();
            for (int i = 0; i < cellsAtCurrentLevel; i++) {
                List<Integer> currentPoint = pointsToVisit.remove();

                // skip point if already visited
                if (pointsAlreadyVisited.contains(currentPoint)) {
                    continue;
                }

                // if current cell is 0, report the distance from the startingPoint.
                int rowNum = currentPoint.get(0);
                int columnNum = currentPoint.get(1);
                if (mat[rowNum][columnNum] == 0) {  // this is guaranteed to happen at some point.
                    // this condition won't be true for startingPoint, but it could be true
                    // for all other points.
                    return shortestDistance;
                }

                // Populate a list of all neighbouring points that you haven't visited yet.
                List<List<Integer>> neighbouringPointsToVisit = getNeighbours(mat, pointsAlreadyVisited, currentPoint);
                pointsToVisit.addAll(neighbouringPointsToVisit);

                // mark point as visited
                pointsAlreadyVisited.add(currentPoint);
            }
            shortestDistance++;
        }
        return shortestDistance;
    }

    private boolean isInBounds(int[][] mat, List<Integer> point) {
        int rowNum = point.get(0);
        int columnNum = point.get(1);
        return  0 <= rowNum && rowNum < mat.length &&
                0 <= columnNum && columnNum < mat[0].length;
    }

    private List<List<Integer>> getNeighbours(int[][] mat, Set<List<Integer>> pointsAlreadyVisited, List<Integer> currentPoint) {
        // Check if point above, right, below, and left of currentPoint is in bounds.
        List<List<Integer>> neighbouringPointsToVisit = new ArrayList<>();
        List<List<Integer>> neighbouringPoints = getNeighbouringPoints(currentPoint);
        for (List<Integer> neighbouringPoint : neighbouringPoints) {
            if (isInBounds(mat, neighbouringPoint) && !pointsAlreadyVisited.contains(neighbouringPoint)) {
                neighbouringPointsToVisit.add(neighbouringPoint);
            }
        }
        return neighbouringPointsToVisit;
    }

    private static List<List<Integer>> getNeighbouringPoints(List<Integer> currentPoint) {
        int rowNum = currentPoint.get(0);
        int columnNum = currentPoint.get(1);

        List<Integer> topPoint = new ArrayList<>();
        topPoint.add(rowNum - 1);
        topPoint.add(columnNum);

        List<Integer> bottomPoint = new ArrayList<>();
        bottomPoint.add(rowNum + 1);
        bottomPoint.add(columnNum);

        List<Integer> leftPoint = new ArrayList<>();
        leftPoint.add(rowNum);
        leftPoint.add(columnNum - 1);

        List<Integer> rightPoint = new ArrayList<>();
        rightPoint.add(rowNum);
        rightPoint.add(columnNum + 1);

        return Arrays.asList(topPoint, bottomPoint, leftPoint, rightPoint);
    }
}

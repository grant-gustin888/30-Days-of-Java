package InterviewProblems.Matrix.ReshapeMatrix;

import java.util.Arrays;

public class ReshapeMatrix {

    // Let m = the number of rows in the original matrix
    // Let n = the number of columns in the original matrix
    // Let r = the desired number of rows in the reshaped matrix
    // Let c = the desired number of columns in the reshaped matrix
    //
    // Time: O(m * n)
    // --> O(1) to check if the matrix dimensions are compatible
    // --> O(m * n) to flatten the matrix into a 1D array
    // --> O(r * c) = O(m * n) to reshape the 1D array into an r * c array
    //
    // Space: O(m * n)
    // --> O(r * c) = O(m * n) for the reshaped array

    public static void main(String[] args) {
        ReshapeMatrix reshapeMatrix = new ReshapeMatrix();

        // Input: mat = [[1, 2], [3, 4]], r = 1, c = 4
        // Output: [[1, 2, 3, 4]]
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] reshapedMatrix1 = reshapeMatrix.matrixReshape(matrix1, 1, 4);
        System.out.println(Arrays.deepToString(reshapedMatrix1));  // [[1, 2, 3, 4]]

        // Input: mat = [[1, 2], [3, 4]], r = 2, c = 4
        // Output: [[1, 2], [3, 4]]
        int[][] matrix2 = {{1, 2}, {3, 4}};
        int[][] reshapedMatrix2 = reshapeMatrix.matrixReshape(matrix2, 2, 4);
        System.out.println(Arrays.deepToString(reshapedMatrix2));  // [[1, 2], [3, 4]]

        // Input: mat = [[1, 2], [3, 4]], r = 2, c = 2
        // Output: [[1, 2], [3, 4]]
        int[][] matrix3 = {{1, 2}, {3, 4}};
        int[][] reshapedMatrix3 = reshapeMatrix.matrixReshape(matrix3, 2, 2);
        System.out.println(Arrays.deepToString(reshapedMatrix3));  // [[1, 2], [3, 4]]

        // Input: mat = [[1, 2]], r = 1, c = 1
        int[][] matrix4 = {{1, 2}};
        int[][] reshapedMatrix4 = reshapeMatrix.matrixReshape(matrix4, 1, 1);
        System.out.println(Arrays.deepToString(reshapedMatrix4));  // [[1, 2]]
    }

    /**
     * In MATLAB, there is a handy function called reshape which can reshape an m x n
     * matrix into a new one with a different size r x c keeping its original data.
     *
     * You are given an m x n matrix mat and two integers r and c representing the
     * number of rows and the number of columns of the wanted reshaped matrix.
     *
     * The reshaped matrix should be filled with all the elements of the original
     * matrix in the same row-traversing order as they were.
     *
     * If the reshape operation with given parameters is possible and legal, output
     * the new reshaped matrix; Otherwise, output the original matrix.
     *
     * Preconditions:
     * - m == mat.length
     * - n == mat[i].length
     * - 1 <= m, n <= 100
     * - -1000 <= mat[i][j] <= 1000
     * - 1 <= r, c <= 300
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int numRows = mat.length;
        int numColumns = mat[0].length;

        if (numRows == r && numColumns == c) {
            return mat;
        } else if (numRows * numColumns != r * c) {
            return mat;
        }

        int[] flattenedMatrix = flattenMatrix(mat);
        int[][] reshapedMatrix = new int[r][c];
        int i = 0;
        for (int rowNum = 0; rowNum < r; rowNum++) {
            for (int columnNum = 0; columnNum < c; columnNum++) {
                reshapedMatrix[rowNum][columnNum] = flattenedMatrix[i];
                i++;
            }
        }
        return reshapedMatrix;
    }

    private int[] flattenMatrix(int[][] matrix) {
        int numRows = matrix.length;
        int numColumns = matrix[0].length;

        int[] flattenedMatrix = new int[numRows * numColumns];
        int i = 0;
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                flattenedMatrix[i] = matrix[rowNum][columnNum];
                i++;
            }
        }
        return flattenedMatrix;
    }
}

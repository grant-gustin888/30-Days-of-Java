package InterviewProblems.MediumQuestions.BinarySearch.SearchA2DMatrixRemastered.OriginalSolution;

public class SearchA2DMatrix {

    // inspiration: https://www.youtube.com/embed/Ber2pi2C0j0

    // Let m = the number of rows in the matrix, and n = the number of columns in the matrix.
    //
    // Time: O(log m + log n) = O(log mn)
    // --> O(log m) We use modified binary search, where we compare target against matrixRow[startColumn] and
    // matrixRow[endColumn], to find the target row to perform binary search on.
    // --> O(log n) We use binary search on the target row to find the target column.
    // Space: O(log m)
    // --> O(log m) We may need to store the target row for the second step.
    // --> O(1) We use 4 variables: startRow, endRow, startColumn, and endColumn.

    public static void main(String[] args) {
        SearchA2DMatrix searchA2DMatrix = new SearchA2DMatrix();

        // Input: matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 3
        // Output: true
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        boolean itemFound1 = searchA2DMatrix.searchMatrix(matrix1, 3);
        System.out.println(itemFound1);  // true

        // Input: matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 13
        // Output: false
        int[][] matrix2 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        boolean itemFound2 = searchA2DMatrix.searchMatrix(matrix2, 13);
        System.out.println(itemFound2);  // false

        // Input: matrix = [[1, 3]], target = 3
        // Output: true
        int[][] matrix3 = {{1, 3}};
        boolean itemFound3 = searchA2DMatrix.searchMatrix(matrix3, 3);
        System.out.println(itemFound3);  // true
    }

    /**
     * Write an efficient algorithm that searches for a value target in an m x n
     * integer matrix matrix. This matrix has the following properties:
     * - Integers in each row are sorted from left to right.
     * - The first integer of each row is greater than the last integer of the previous row.
     *
     * Preconditions:
     * - m == matrix.length.
     * - n == matrix[i].length.
     * - 1 <= m, n <= 100.
     * - -10 ^ 4 <= matrix[i][j], target <= 10 ^ 4.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int targetRow = findMatrixRow(matrix, target);
        if (targetRow == -1) {
            return false;
        } else {
            return binarySearch(matrix[targetRow], target);
        }
    }

    private int findMatrixRow(int[][] matrix, int target) {
        int startRow = 0;
        int endRow = matrix.length - 1;
        int startColumn = 0;
        int endColumn = matrix[0].length - 1;
        while (startRow <= endRow) {
            int middleRow = (startRow + endRow) / 2;
            int[] matrixRow = matrix[middleRow];
            if (matrixRow[startColumn] <= target && target <= matrixRow[endColumn]) {
                return middleRow;
            } else if (target < matrixRow[startColumn]) {
                // go left
                endRow = middleRow - 1;
            } else {  // target > matrixRow[endColumn]
                // go right
                startRow = middleRow + 1;
            }
        }
        return -1;
    }

    private boolean binarySearch(int[] array, int target) {
        int startIndex = 0;
        int endIndex = array.length - 1;
        while (startIndex <= endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            if (array[middleIndex] == target) {
                return true;
            } else if (target < array[middleIndex]) {
                // go left
                endIndex = middleIndex - 1;
            } else {  // target > array[middleIndex]
                // go right
                startIndex = middleIndex + 1;
            }
        }
        return false;
    }

    // original solution
    //
    // int startRow = 0;
    // int endRow = matrix.length - 1;
    //
    // int startColumn = 0;
    // int endColumn = matrix[0].length - 1;
    //
    // while (startRow <= endRow && startColumn <= endColumn) {
    //     int middleRow = (startRow + endRow) / 2;
    //     int middleColumn = (startColumn + endColumn) / 2;
    //
    //     if (matrix[middleRow][middleColumn] == target) {
    //         return true;
    //     } else if (matrix[middleRow][middleColumn] < target) {
    //         endRow = middleRow - 1;
    //         endColumn = middleColumn - 1;
    //     } else {  // matrix[middleRow][middleColumn] > target
    //         startRow = middleRow + 1;
    //         startColumn = middleColumn + 1;
    //     }
    // }
    // return false;
}

package InterviewProblems.MediumQuestions.BinarySearch.SearchA2DMatrixRemastered.AlternativeSolution;

public class SearchA2DMatrix {

    // from NeetCode > Binary Search > Search a 2D Matrix solutions:
    // https://neetcode.io/practice

    // Let m = the number of rows in the matrix, and n = the number of columns in the matrix.
    //
    // Time: O(log mn)
    // --> We use modified binary search on the number of cells in the matrix (there are mn cells)
    // to find the target cell.
    // Space: O(1)
    // --> We only use 4 variables: startRow, endRow, startColumn, and endColumn.

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
        if (matrix.length == 0) {
            return false;
        }

        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        int startCellNum = 0;
        int endCellNum = numRows * numColumns;
        while (startCellNum <= endCellNum) {
            int middleCellNum = (startCellNum + endCellNum) / 2;
            // middleIndex / columnIndex-th row, middleIndex % columnIndex-th column
            if (matrix[middleCellNum / numColumns][middleCellNum % numColumns] == target) {
                return true;
            } else if (target < matrix[middleCellNum / numColumns][middleCellNum % numColumns]) {
                // go left
                endCellNum = middleCellNum - 1;
            } else {  // target > matrix[middleCellNum / numColumns][middleCellNum % numColumns]
                // go right
                startCellNum = middleCellNum + 1;
            }
        }
        return false;
    }

    // another accepted solution:
    //
    // public boolean searchMatrix(int[][] matrix, int target) {
    //    int rowIndex = matrix.length;
    //    int columnIndex = matrix[0].length;
    //    int startIndex = 0;
    //    int endIndex = rowIndex * columnIndex - 1;
    //    while (startIndex <= endIndex) {
    //        int middleIndex = startIndex + (endIndex - startIndex) / 2;
    //        // middleIndex / columnIndex-th row, middleIndex % columnIndex-th column
    //        int value = matrix[middleIndex / columnIndex][middleIndex % columnIndex];
    //        if (value == target) {
    //            return true;
    //        } else if (value < target) {
    //            startIndex = middleIndex + 1;
    //        } else {  // value > target
    //            endIndex = middleIndex - 1;
    //        }
    //    }
    //    return false;
    // }
}

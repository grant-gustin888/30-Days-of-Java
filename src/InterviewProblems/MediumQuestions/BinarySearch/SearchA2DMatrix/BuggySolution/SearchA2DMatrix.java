package InterviewProblems.MediumQuestions.BinarySearch.SearchA2DMatrix.BuggySolution;

public class SearchA2DMatrix {

    public static void main(String[] args) {
        SearchA2DMatrix searchA2DMatrix = new SearchA2DMatrix();

        // Input: matrix = [[1]], target = 1
        // Output: true
        int[][] matrix1 = {{1}};
        boolean search1 = searchA2DMatrix.searchMatrix(matrix1, 1);
        System.out.println(search1);  // true

        // Input: matrix = [[1]], target = 0
        // Output: false
        int[][] matrix2 = {{1}};
        boolean search2 = searchA2DMatrix.searchMatrix(matrix2, 0);
        System.out.println(search2);  // false

        // Input: matrix = [[1]], target = 2
        // Output: false
        int[][] matrix3 = {{1}};
        boolean search3 = searchA2DMatrix.searchMatrix(matrix3, 3);
        System.out.println(search3);  // false

        // Input: matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 3
        // Output: true
        int[][] matrix4 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        boolean search4 = searchA2DMatrix.searchMatrix(matrix4, 3);
        System.out.println(search4);  // true

        // Input: matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 16
        // Output: true
        int[][] matrix5 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        boolean search5 = searchA2DMatrix.searchMatrix(matrix5, 16);
        System.out.println(search5);  // true

        // Input: matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 34
        // Output: true
        int[][] matrix6 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        boolean search6 = searchA2DMatrix.searchMatrix(matrix5, 34);
        System.out.println(search5);  // true
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int rowToSearch = findRowClosestToTarget(matrix, target);
        return binarySearch(matrix[rowToSearch], target);
    }

    /**
     * Use binary search to find the rowest closest to target to start searching for the target value.
     */
    private int findRowClosestToTarget(int[][] matrix, int target) {
        int startRow = 0;
        int endRow = matrix.length;
        int lastColumnIndex = matrix[0].length - 1;
        while (startRow < endRow) {
            int middleRow = (startRow + endRow) / 2;  // may produce integer overflow
            if (matrix[middleRow][0] > target) {
                endRow = middleRow - 1;  // search to the left of row matrix[middleRow]
            } else if (matrix[middleRow][lastColumnIndex] < target) {
                startRow = middleRow + 1;  // search to the right of row matrix[middleRow]
            } else {  // matrix[middleRow][0] <= target && target <= matrix[middleRow][lastColumnIndex]
                return middleRow;  // stop searching...
            }
        }
        // assert startRow >= endRow
        if (endRow < 0) {
            return startRow;
        } else {  // startRow > matrix.length - 1
            return endRow;
        }

        //        else if (startRow > matrix.length - 1) {
        //            return endRow - 1;
        //        } else if (endRow > matrix.length - 1) {
        //            return startRow - 1;
        //        } else {
        //            return -1;
        //        }
        // return startRow;  // TODO: What should this row be?  if endIndex < 0 then startRow, else if (startIndex > number of rows) endRow - 1 else (endIndex > number of rows) startIndex - 1.
    }

    private boolean binarySearch(int[] array, int target) {
        int startIndex = 0;
        int endIndex = array.length;
        while (startIndex < endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;  // may produce integer overflow
            if (array[middleIndex] == target) {
                return true;
            } else if (array[middleIndex] < target) {
                endIndex = middleIndex - 1;
            } else {  // array[middleIndex] > target
                startIndex = middleIndex + 1;
            }
        }
        return false;
    }
}

package InterviewProblems.MediumQuestions.BinarySearch.SearchA2DMatrix.OriginalSolution;

public class SearchA2DMatrix {

    // inspired by 0ms sample LeetCode solution: https://leetcode.com/submissions/detail/797449005/

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
        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        int startIndex = 0;
        int endIndex = numRows * numColumns - 1;
        while (startIndex <= endIndex) {
            int middleIndex = startIndex + (endIndex - startIndex) / 2;
            // middleIndex / numColumns-th row, middleIndex % numColumns-th column
            int value = matrix[middleIndex / numColumns][middleIndex % numColumns];
            if (value == target) {
                return true;
            } else if (value < target) {    
                startIndex = middleIndex + 1;
            } else {  // value > target
                endIndex = middleIndex - 1;
            }
        }
        return false;
    }
}

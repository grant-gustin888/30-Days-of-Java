package InterviewProblems.MediumQuestions.DataStructureDesign.SubrectangleQueries;

public class SubrectangleQueries {

    private final int[][] rectangle;

    /**
     * Representation Invariants:
     * - There will be at most 500 operations considering both methods:
     * updateSubrectangle and getValue.
     * - 1 <= rows, cols <= 100.
     * - rows == rectangle.length.
     * - cols == rectangle[i].length.
     * - 0 <= row1 <= row2 < rows.
     * - 0 <= col1 <= col2 < cols.
     * - 1 <= newValue, rectangle[i][j] <= 10 ^ 9.
     * - 0 <= row < rows.
     * - 0 <= col < cols.
     */

    // Let r = the number of rows in the rectangle.
    // Let c = the number of columns in the rectangle.
    //
    // updateSubrectangle(row1, col1, row2, col2, newValue)
    // - Time: O(r * c)
    // --> We may have to update the entire rectangle.
    // - Space: O(1)
    // --> We modify the rectangle's values in place.
    //
    // getValue(row, col)
    // - Time: O(1)
    // --> We index into the rectangle.
    // - Space: O(1)
    // --> We do not store any additional data.

    // Implement the class SubrectangleQueries which receives a rows x cols rectangle as
    // a matrix of integers in the constructor and supports two methods:
    public static void main(String[] args) {
        int[][] rectangle1 = {
            {1, 2, 1},
            {4, 3, 4},
            {3, 2, 1},
            {1, 1, 1}
        };
        SubrectangleQueries subrectangleQueries = new SubrectangleQueries(rectangle1);
        // The initial rectangle (4 x 3) looks like:
        // 1 2 1
        // 4 3 4
        // 3 2 1
        // 1 1 1
        System.out.println(subrectangleQueries.getValue(0, 2));  // returns 1
        subrectangleQueries.updateSubrectangle(0, 0, 3, 2, 5);
        // After this update the rectangle looks like:
        // 5 5 5
        // 5 5 5
        // 5 5 5
        // 5 5 5
        System.out.println(subrectangleQueries.getValue(3, 2));  // returns 5
        subrectangleQueries.updateSubrectangle(3, 0, 3, 2, 10);
        // After this update the rectangle looks like:
        // 5   5   5
        // 5   5   5
        // 5   5   5
        // 10  10  10
        System.out.println(subrectangleQueries.getValue(3, 1));  // returns 10
        System.out.println(subrectangleQueries.getValue(0, 2));  // returns 5

        int[][] rectangle2 = {
            {1, 1, 1},
            {2, 2, 2},
            {3, 3, 3}
        };
        SubrectangleQueries subrectangleQueries2 = new SubrectangleQueries(rectangle2);
        System.out.println(subrectangleQueries2.getValue(0, 0));  // returns 1

        subrectangleQueries2.updateSubrectangle(0, 0, 2, 2, 100);
        // After this update the rectangle looks like:
        // 100   100   100
        // 100   100   100
        // 100   100   100
        System.out.println(subrectangleQueries2.getValue(0, 0));  // returns 100
        System.out.println(subrectangleQueries2.getValue(2, 2));  // returns 100

        subrectangleQueries2.updateSubrectangle(1, 1, 2, 2, 20);
        // After this update the rectangle looks like:
        // 100   100   100
        // 100    20    20
        // 100    20    20
        System.out.println(subrectangleQueries2.getValue(2, 2));  // returns 20
    }

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }

    // 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
    // Updates all values with newValue in the subrectangle whose upper left coordinate
    // is (row1, col1) and bottom right coordinate is (row2, col2).
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int rowNum = row1; rowNum <= row2; rowNum++) {
            for (int columnNum = col1; columnNum <= col2; columnNum++) {
                rectangle[rowNum][columnNum] = newValue;
            }
        }
    }

    // 2. getValue(int row, int col)
    // Returns the current value of the coordinate (row, col) from the rectangle.
    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}

package InterviewProblems.MediumQuestions.DynamicProgramming.UniquePaths.DPSolution;

import java.util.Arrays;

public class UniquePaths {

    // TODO: Add time and space complexity analysis here.

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();

        // Input: m = 1, n = 1
        // Output: 1
        int uniquePaths1 = uniquePaths.uniquePaths(1, 1);
        System.out.println(uniquePaths1);  // 1

        // Input: m = 3, n = 2
        // Output: 3
        // Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
        // 1. Right -> Down -> Down
        // 2. Down -> Down -> Right
        // 3. Down -> Right -> Down
        int uniquePaths2 = uniquePaths.uniquePaths(3, 2);
        System.out.println(uniquePaths2);  // 3

        // Input: m = 3, n = 7
        // Output: 28
        int uniquePaths3 = uniquePaths.uniquePaths(3, 7);
        System.out.println(uniquePaths3);  // 28

        // Input: m = 19, n = 13
        // Output: 86493225
        int uniquePaths4 = uniquePaths.uniquePaths(19, 13);
        System.out.println(uniquePaths4);  // 86493225
    }

    /**
     * There is a robot on an m x n grid. The robot is initially
     * located at the top-left corner (i.e., grid[0][0]). The robot
     * tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
     * The robot can only move either down or right at any point in time.
     * <p>
     * Given the two integers m and n, return the number of possible
     * unique paths that the robot can take to reach the bottom-right corner.
     * <p>
     * The test cases are generated so that the answer will be less
     * than or equal to 2 * 10 ^ 9.
     * <p>
     * Preconditions:
     * 1 <= m, n <= 100
     */
    public int uniquePaths(int m, int n) {
        int[][] numWaysGrid = new int[m][n];

        // fill first row with 1s
        Arrays.fill(numWaysGrid[0], 1);

        // fill first column with 1s
        for (int rowNum = 0; rowNum < m; rowNum++) {
            numWaysGrid[rowNum][0] = 1;
        }

        // populate grid with remaining values
        for (int rowNum = 1; rowNum < m; rowNum++) {
            for (int columnNum = 1; columnNum < n; columnNum++) {
                numWaysGrid[rowNum][columnNum] =
                    numWaysGrid[rowNum - 1][columnNum] +
                    numWaysGrid[rowNum][columnNum - 1];
            }
        }
        return numWaysGrid[m - 1][n - 1];
    }
}

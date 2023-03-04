package InterviewProblems.Graphs.NumberOfIslands;

public class NumberOfIslands {

    // inspiration from neetcode solutions: https://neetcode.io/practice

    // TODO: Add time and space complexity analysis here.

    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();

        // Input: grid = [
        //     ["1", "1", "1", "1", "0"],
        //     ["1", "1", "0", "1", "0"],
        //     ["1", "1", "0", "0", "0"],
        //     ["0", "0", "0", "0", "0"]
        // ]
        // Output: 1
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        int numIslands1 = numberOfIslands.numIslands(grid1);
        System.out.println(numIslands1);  // 1

        // Input: grid = [
        //     ["1", "1", "0", "0", "0"],
        //     ["1", "1", "0", "0", "0"],
        //     ["0", "0", "1", "0", "0"],
        //     ["0", "0", "0", "1", "1"]
        // ]
        // Output: 3
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        int numIslands2 = numberOfIslands.numIslands(grid2);
        System.out.println(numIslands2);  // 3
    }

    public final char WATER = '0';
    public final char LAND = '1';
    public final char VISITED = '2';

    public int numIslands(char[][] grid) {
        int numIslands = 0;
        int numRows = grid.length;
        int numColumns = grid[0].length;

        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (grid[rowNum][columnNum] == LAND) {
                    exploreIsland(grid, rowNum, columnNum);
                    numIslands++;
                }
            }
        }
        return numIslands;
    }

    /**
     * Return the number of new islands encountered from cell (rowNum, columnNum).
     *
     * Preconditions:
     * - m == grid.length
     * - n == grid[i].length
     * - 1 <= m, n <= 300
     * - grid[i][j] is '0' or '1'.
     */
    private void exploreIsland(char[][] grid, int rowNum, int columnNum) {
        // is it in bounds?
        if (!isInBounds(grid, rowNum, columnNum)) {
            return;
        }

        // is cell already visited?
        if (grid[rowNum][columnNum] == WATER) {
            return;
        }

        if (grid[rowNum][columnNum] == VISITED) {
            return;
        }

        // visit this cell, and others
        grid[rowNum][columnNum] = VISITED;  // count this cell as visited
        exploreIsland(grid, rowNum - 1, columnNum);  // up
        exploreIsland(grid, rowNum, columnNum + 1);  // right
        exploreIsland(grid, rowNum + 1, columnNum);  // down
        exploreIsland(grid, rowNum, columnNum - 1);  // left
    }

    private boolean isInBounds(char[][] grid, int rowNum, int columnNum) {
        return  0 <= rowNum && rowNum < grid.length &&
                0 <= columnNum && columnNum < grid[0].length;
    }
}

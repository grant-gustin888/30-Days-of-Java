package InterviewProblems.Graphs.MaxAreaOfIsland.AlternativeSolution4DisjointSet2D;

public class MaxAreaOfIsland {

    // TODO: Add time and space complexity analysis here.

    public static void main(String[] args) {
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();

        // Input: grid = [[0, 0, 0, 0, 0, 0, 0, 0]]
        // Output: 0
        int[][] grid1 = {{0, 0, 0, 0, 0, 0, 0, 0}};
        int maxArea1 = maxAreaOfIsland.maxAreaOfIsland(grid1);
        System.out.println(maxArea1);  // 0

        // Input: grid =
        // [[0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0],
        //  [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0],
        //  [0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
        //  [0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0],
        //  [0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0],
        //  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0],
        //  [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0],
        //  [0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0]]
        // Output: 6
        // Explanation: The answer is not 11, because the island must be connected 4-directionally.
        int[][] grid2 = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        int maxArea2 = maxAreaOfIsland.maxAreaOfIsland(grid2);
        System.out.println(maxArea2);  // 6

        // Input: grid =
        // [[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //  [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //  [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //  [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //  [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //  [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //  [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        //  [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]
        // Output: 104
        int[][] grid3 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        int maxArea3 = maxAreaOfIsland.maxAreaOfIsland(grid3);
        System.out.println(maxArea3);  // 104
    }

    private final int WATER = 0;
    private final int LAND = 1;

    public int maxAreaOfIsland(int[][] grid) {
        int numRows = grid.length;
        int numColumns = grid[0].length;

        // union adjacent cells into islands of connected components
        DisjointSet disjointSet = new DisjointSet(numRows, numColumns);
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                if (grid[row][column] == WATER) {
                    continue;
                }

                // join current cell with right adjacent cell
                if (column < numColumns - 1 && grid[row][column + 1] == LAND) {
                    disjointSet.union(row, column, row, column + 1);
                }

                // join current cell with bottom adjacent cell
                if (row < numRows - 1 && grid[row + 1][column] == LAND) {
                    disjointSet.union(row, column, row + 1, column);
                }
            }
        }

        // the area of each island is the size of each connected component
        int maxAreaOfIsland = 0;
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                if (grid[row][column] == LAND) {
                    maxAreaOfIsland = Math.max(maxAreaOfIsland, disjointSet.componentSize(row, column));
                }
            }
        }
        return maxAreaOfIsland;
    }
}

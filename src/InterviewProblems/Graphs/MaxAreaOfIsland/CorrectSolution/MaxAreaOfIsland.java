package InterviewProblems.Graphs.MaxAreaOfIsland.CorrectSolution;

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

    private final int ALREADY_VISITED = -1;
    private final int WATER = 0;
    private final int NOT_VISITED = 1;

    public int maxAreaOfIsland(int[][] grid) {
        int maxAreaOfIsland = 0;
        int numRows = grid.length;
        int numColumns = grid[0].length;

        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (grid[rowNum][columnNum] == NOT_VISITED) {
                    int numNewPointsVisited = numNewCellsFrom(grid, rowNum, columnNum);
                    maxAreaOfIsland = Math.max(maxAreaOfIsland, numNewPointsVisited);
                }
            }
        }
        return maxAreaOfIsland;
    }

    /**
     * Return the number of new cells encountered starting from cell (startRowNum, startColumnNum).
     */
    private int numNewCellsFrom(int[][] grid, int startRowNum, int startColumnNum) {
        // if point is out of bounds?
        if (!inBounds(grid, startRowNum, startColumnNum)) {
            return 0;
        }

        // if point is already 0? vs. if it's a 1. (add 1 to total)
        if (grid[startRowNum][startColumnNum] == ALREADY_VISITED || grid[startRowNum][startColumnNum] == WATER) {
            return 0;
        }

        // grid[startRowNum][startColumnNum] == NOT_VISITED
        grid[startRowNum][startColumnNum] = ALREADY_VISITED;
        int newCells = 1;  // this point counts as a newly visited cell

        // visit cells in clockwise order: top, right, bottom, left
        // backtrack from paths that lead to dead ends
        newCells += numNewCellsFrom(grid, startRowNum - 1, startColumnNum);
        newCells += numNewCellsFrom(grid, startRowNum, startColumnNum + 1);
        newCells += numNewCellsFrom(grid, startRowNum + 1, startColumnNum);
        newCells += numNewCellsFrom(grid, startRowNum, startColumnNum - 1);
        return newCells;
    }

    private boolean inBounds(int[][] grid, int rowNum, int columnNum) {
        return  0 <= rowNum && rowNum < grid.length &&
                0 <= columnNum && columnNum < grid[0].length;
    }
}
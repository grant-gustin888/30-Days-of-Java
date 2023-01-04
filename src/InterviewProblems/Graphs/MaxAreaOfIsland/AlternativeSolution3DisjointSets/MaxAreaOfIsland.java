package InterviewProblems.Graphs.MaxAreaOfIsland.AlternativeSolution3DisjointSets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    Map<Integer, Set<Integer>> pointsVisited;
    private final int ALREADY_VISITED = -1;
    private final int WATER = 0;
    private final int NOT_VISITED = 1;

    public int maxAreaOfIsland(int[][] grid) {
        int maxAreaOfIsland = 0;
        pointsVisited = new HashMap<>();
        int numRows = grid.length;
        int numColumns = grid[0].length;

        DisjointSet disjointSet = new DisjointSet(numRows * numColumns);
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                if (grid[row][column] == WATER) {
                    continue;
                }

                if (column < numColumns - 1 && grid[row][column + 1] == NOT_VISITED) {
                    disjointSet.union(row * numColumns + column, row * numColumns + (column + 1));
                }

                if (row < numRows - 1 && grid[row + 1][column] == NOT_VISITED) {
                    disjointSet.union(row * numColumns + column, (row + 1) * numColumns + column);
                }
            }
        }

        // find the max area of island
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                if (grid[row][column] == NOT_VISITED) {
                    maxAreaOfIsland = Math.max(maxAreaOfIsland, disjointSet.componentSize(row * numColumns + column));
                    // grid[row][column] = ALREADY_VISITED;
                }
            }
        }
        return maxAreaOfIsland;
    }
}

package InterviewProblems.Graphs.PacificAtlanticWaterFlow.BuggySolution;

import java.util.ArrayList;
import java.util.List;

public class AtlanticWaterFlow {

    public static void main(String[] args) {
        AtlanticWaterFlow atlanticWaterFlow = new AtlanticWaterFlow();

        // Input: heights = [[1, 2, 2, 3, 5], [3, 2, 3, 4, 4], [2, 4, 5, 3, 1], [6, 7, 1, 4, 5], [5, 1, 1, 2, 4]]
        // Output: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
        // Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
        // [0, 4]: [0, 4] -> Pacific Ocean
        //         [0, 4] -> Atlantic Ocean
        // [1, 3]: [1, 3] -> [0, 3] -> Pacific Ocean
        //         [1, 3] -> [1, 4] -> Atlantic Ocean
        // [1, 4]: [1, 4] -> [1, 3] -> [0, 3] -> Pacific Ocean
        //         [1, 4] -> Atlantic Ocean
        // [2, 2]: [2, 2] -> [1, 2] -> [0, 2] -> Pacific Ocean
        //         [2, 2] -> [2, 3] -> [2, 4] -> Atlantic Ocean
        // [3, 0]: [3, 0] -> Pacific Ocean
        //         [3, 0] -> [4, 0] -> Atlantic Ocean
        // [3, 1]: [3, 1] -> [3, 0] -> Pacific Ocean
        //         [3, 1] -> [4, 1] -> Atlantic Ocean
        // [4, 0]: [4, 0] -> Pacific Ocean
        //         [4, 0] -> Atlantic Ocean
        // Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
        int[][] heights1 = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {0, 0, 0, 0, 0}
        };
        List<List<Integer>> pacificAtlanticFlow1 = atlanticWaterFlow.pacificAtlantic(heights1);
        System.out.println(pacificAtlanticFlow1);
        // [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1]]

        // Input: heights = [[1]]
        // Output: [[0, 0]]
        // Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
        int[][] heights2 = {{1}};
        List<List<Integer>> pacificAtlanticFlow2 = atlanticWaterFlow.pacificAtlantic(heights2);
        System.out.println(pacificAtlanticFlow2);
        // [[0, 0]]

        // Input: heights = [[1, 1], [1, 1]]
        // Output: [[0, 0], [0, 1], [1, 0], [1, 1]]
        // Explanation: The water can flow from all four cells to the Pacific and Atlantic oceans.
        int[][] heights3 = {{1, 1}, {1, 1}};
        List<List<Integer>> pacificAtlanticFlow3 = atlanticWaterFlow.pacificAtlantic(heights3);
        System.out.println(pacificAtlanticFlow3);
        // [[0, 0], [0, 1], [1, 0], [1, 1]]

        int[][] heights4 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        List<List<Integer>> pacificAtlanticFlow4 = atlanticWaterFlow.pacificAtlantic(heights4);
        System.out.println(pacificAtlanticFlow4);
        // [[0, 0], [0, 1], [0, 2], [1, 0], [1, 2], [2, 0], [2, 1], [2, 2]]
    }

    // keep track of cells already visited
    // TODO: for memoization purposes, should this same matrix also tell me whether
    // TODO: the current cell can reach both the pacific and atlantic ocean?
    int[][] visited;

    final int NOT_VISITED = 0;
    final int VISITED_NO_GOAL_YET = 1;
    final int VISITED_REACHED_GOAL = 2;

    /**
     * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
     * The Pacific Ocean touches the island's left and top edges, and the Atlantic
     * Ocean touches the island's right and bottom edges.
     *
     * The island is partitioned into a grid of square cells.
     * You are given an m x n integer matrix heights, where heights[r][c] represents
     * the heights above sea level of the cell at coordinate (r, c).
     *
     * The island receives a lot of rain, and the rain water can flow to neighbouring
     * cells directly north, south, east, and west if the neighbouring cell's height
     * is less than or equal to the current cell's height. Water can flow from any cell
     * adjacent to an ocean into the ocean.
     *
     * Return a 2D list of grid coordinates result where result[i] = [r_i, c_i] denotes
     * that rain water can flow from cell (r_i, c_i) to both the Pacific and Atlantic oceans.
     *
     * Preconditions:
     * - m == heights.length
     * - n == heights[r].length
     * - 1 <= m, n <= 200
     * - 0 <= heights[r][c] <= 10 ^ 5
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int numRows = heights.length;
        int numColumns = heights[0].length;
        visited = new int[numRows][numColumns];
        List<List<Integer>> cellsFlowToBothOceans = new ArrayList<>();
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (hasPath(heights, rowNum, columnNum)) {
                    List<Integer> currentCell = new ArrayList<>();
                    currentCell.add(rowNum);
                    currentCell.add(columnNum);
                    cellsFlowToBothOceans.add(currentCell);
                }
            }
        }
        return cellsFlowToBothOceans;
    }

    // TODO:
    // a (recursive) helper function that determines if there's a path from current cell
    // to both Pacific Ocean and Atlantic Ocean
    private boolean hasPath(int[][] grid, int rowNum, int columnNum) {
        // if cell out of bounds
        // return false
        if (!isInBounds(grid, rowNum, columnNum)) {
            return false;
        }

        // if it already touches Pacific Ocean and Atlantic Ocean
        // return true
        if (touchesPacificOcean(grid, rowNum, columnNum) &&
            touchesAtlanticOcean(grid, rowNum, columnNum)) {
            return true;
        }

        // if cell already visited... return true
        int currentCell = visited[rowNum][columnNum];
        if (currentCell == VISITED_REACHED_GOAL) {
            visited[rowNum][columnNum] = VISITED_REACHED_GOAL;
            return true;
        }

        // mark current cell as visited
        visited[rowNum][columnNum] = VISITED_NO_GOAL_YET;

        // visit 4-adjacent cells:
        // - top
        // - right
        // - bottom
        // - left
        // return whether any of these are true or not...
        boolean goUp = hasPath(grid, rowNum - 1, columnNum);
        boolean goRight = hasPath(grid, rowNum, columnNum + 1);
        boolean goBottom = hasPath(grid, rowNum + 1, columnNum);
        boolean goLeft = hasPath(grid, rowNum, columnNum - 1);
        if (goUp || goRight || goBottom || goLeft) {
            visited[rowNum][columnNum] = VISITED_REACHED_GOAL;
            return true;
        } else {
            return false;
        }
    }

    // TODO:
    // return true iff cell is in bounds...
    private boolean isInBounds(int[][] grid, int rowNum, int columnNum) {
        int numRows = grid.length;
        int numColumns = grid[0].length;
        return  0 <= rowNum && rowNum < numRows &&
                0 <= columnNum && columnNum < numColumns;
    }

    // TODO:
    // a helper function that determines whether a cell touches the Pacific Ocean.
    private boolean touchesPacificOcean(int[][] grid, int rowNum, int columnNum) {
        return rowNum == 0 || columnNum == 0;
    }

    // TODO:
    // a helper function that determines whether a cell touches the Atlantic Ocean.
    private boolean touchesAtlanticOcean(int[][] grid, int rowNum, int columnNum) {
        int numRows = grid.length;
        int numColumns = grid[0].length;
        return rowNum == numRows - 1 && columnNum == numColumns - 1;
    }
}

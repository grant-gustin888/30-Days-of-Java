package InterviewProblems.Graphs.PacificAtlanticWaterFlow.CorrectSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AtlanticWaterFlow {

    // Let m = the number of rows in the matrix
    // Let n = the number of columns in the matrix
    //
    // Time: O(m * n)
    // --> O(m * n) from our DFS, because we visit every cell in our grid <= 4 times
    // (visit each cell from all 4 directions)
    // --> O(m * n) to check each cell to see if it can flow to both oceans
    // Space: O(m * n)
    // --> O(m * n) for our DFS call stack: at most O(m * n) cells exist on the call stack
    // at once
    // --> O(m * n) for our two boolean matrices, pacificWaterFlow and atlanticWaterFlow.
    // one stores the cells that we visit starting at the pacific ocean and the other
    // stores the cells that we visit starting at the atlantic ocean

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
            {5, 1, 1, 2, 4}
        };
        List<List<Integer>> pacificAtlanticFlow1 = atlanticWaterFlow.pacificAtlantic(heights1);
        System.out.println(pacificAtlanticFlow1);
        // [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]

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
        boolean[][] pacificWaterFlow = new boolean[numRows][numColumns];
        boolean[][] atlanticWaterFlow = new boolean[numRows][numColumns];

        // key idea: start from a cell that touches either the pacific or atlantic ocean,
        // and allow water to flow across (all 4 directions) or upwards, rather than across or downwards.
        for (int columnNum = 0; columnNum < numColumns; columnNum++) {
            // from the first row, go from the pacific to the atlantic ocean
            dfs(heights, 0, columnNum, Integer.MIN_VALUE, pacificWaterFlow);
            // from the last row, go from the atlantic to the pacific ocean
            dfs(heights, numRows - 1, columnNum, Integer.MIN_VALUE, atlanticWaterFlow);
        }

        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            // from the first column, go from the pacific to the atlantic ocean
            dfs(heights, rowNum, 0, Integer.MIN_VALUE, pacificWaterFlow);
            // from the last column, go from the atlantic to the pacific ocean
            dfs(heights, rowNum, numColumns - 1, Integer.MIN_VALUE, atlanticWaterFlow);
        }

        List<List<Integer>> cellsReachBothOceans = new ArrayList<>();
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            for (int columnNum = 0; columnNum < numColumns; columnNum++) {
                if (pacificWaterFlow[rowNum][columnNum] && atlanticWaterFlow[rowNum][columnNum]) {
                    List<Integer> newCell = Arrays.asList(rowNum, columnNum);
                    cellsReachBothOceans.add(newCell);
                }
            }
        }
        return cellsReachBothOceans;
    }

    private void dfs(int[][] heights, int rowNum, int columnNum,
                     int previousHeight, boolean[][] oceanCellsVisited) {
        if (!isInBounds(heights, rowNum, columnNum)) {
            return;
        }

        // cell is not reachable
        if (heights[rowNum][columnNum] < previousHeight) {
            return;
        }

        if (oceanCellsVisited[rowNum][columnNum]) {
            return;
        }

        // mark current cell as visited
        oceanCellsVisited[rowNum][columnNum] = true;

        // go from atlantic to pacific (or vice versa)
        int currentHeight = heights[rowNum][columnNum];
        dfs(heights, rowNum - 1, columnNum, currentHeight, oceanCellsVisited);  // up
        dfs(heights, rowNum, columnNum + 1, currentHeight, oceanCellsVisited);  // right
        dfs(heights, rowNum + 1, columnNum, currentHeight, oceanCellsVisited);  // down
        dfs(heights, rowNum, columnNum - 1, currentHeight, oceanCellsVisited);  // left
    }

    private boolean isInBounds(int[][] heights, int rowNum, int columnNum) {
        int numRows = heights.length;
        int numColumns = heights[0].length;
        return  0 <= rowNum && rowNum < numRows &&
                0 <= columnNum && columnNum < numColumns;
    }
}

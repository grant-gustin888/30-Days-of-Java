package InterviewProblems.Graphs.MaxAreaOfIsland.AlternativeSolution4DisjointSet2D;


class DisjointSet {
    // similar to the Disjoint Set from solution 3, except using int[][] instead of just int[]
    // to represent coordinates (x, y) instead of a flattened index x + num of cols * y

    private final int numElements;

    // size of cell (i, j)
    private final int[][] sizes;

    // id[i][j] points to the coordinate of the parent of (i, j), if id[i][j] = {i, j} then i is a root node
    private final int[][][] parents;

    // Tracks the number of components in the union find
    private int numComponents;

    public DisjointSet(int numRows, int numColumns) {
        if (numRows <= 0) {
            throw new IllegalArgumentException("Row <= 0 is not allowed");
        }

        if (numColumns <= 0) {
            throw new IllegalArgumentException("Column <= 0 is not allowed");
        }

        this.numElements = numComponents = numRows * numColumns;
        this.sizes = new int[numRows][numColumns];
        parents = new int[numRows][numColumns][];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                parents[i][j] = new int[] {i, j};  // Link to itself (self root)
                this.sizes[i][j] = 1;  // Each component is originally of size one
            }
        }
    }

    public int[] findSet(int row, int column) {
        // Find the root of the component/set
        int[] root = {row, column};
        while (root != parents[root[0]][root[1]]) {
            root = parents[root[0]][root[1]];
        }

        // Path compression: Compress the path leading back to the root.
        // Doing this operation is called "path compression"
        // and is what gives us amortized time complexity.
        int[] currentPoint = {row, column};
        while (currentPoint != root) {
            int[] next = parents[currentPoint[0]][currentPoint[1]];
            parents[currentPoint[0]][currentPoint[1]] = root;
            currentPoint = next;
        }
        return root;
    }

    public boolean isConnected(int row1, int column1, int row2, int column2) {
        return findSet(row1, column1) == findSet(row2, column2);
    }

    public void union(int row1, int column1, int row2, int column2) {
        // These elements are already in the same group!
        if (isConnected(row1, column1, row2, column2)) {
            return;
        }

        int[] root1 = findSet(row1, column1);
        int[] root2 = findSet(row2, column2);

        // Union by rank: Merge smaller component/set into the larger one.
        if (sizes[root1[0]][root1[1]] < sizes[root2[0]][root2[1]]) {
            parents[root1[0]][root1[1]] = root2;
            sizes[root2[0]][root2[1]] += sizes[root1[0]][root1[1]];
        } else {  // sizes[root1[0]][root1[1]] >= sizes[root2[0]][root2[1]]
            parents[root2[0]][root2[1]] = root1;
            sizes[root1[0]][root1[1]] += sizes[root2[0]][root2[1]];
        }

        // Since the roots found are different we know that the
        // number of components/sets has decreased by one
        numComponents--;
    }

    // Return the size of the components/set 'p' belongs to
    public int componentSize(int row, int column) {
        int[] parent = findSet(row, column);
        return sizes[parent[0]][parent[1]];
    }

    // Return the number of elements in this UnionFind/Disjoint set
    public int getNumElements() {
        return numElements;
    }

    // Returns the number of remaining components/sets
    public int getNumComponents() {
        return numComponents;
    }
}

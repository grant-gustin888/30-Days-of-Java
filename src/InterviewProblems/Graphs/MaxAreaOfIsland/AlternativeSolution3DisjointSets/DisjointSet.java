package InterviewProblems.Graphs.MaxAreaOfIsland.AlternativeSolution3DisjointSets;

// credits to William Fiset:
// https://www.youtube.com/watch?v=KbFlZYCpONw&list=PLDV1Zeh2NRsBI1C-mR6ZhHTyfoEJWlxvq&index=5

public class DisjointSet {

    // The number of elements in this union find
    private final int numElements;

    // Used to track the size of each of the component
    private final int[] sizes;

    // id[i] points to the parent of i, if id[i] = i then i is a root node
    private final int[] parents;

    // Tracks the number of components in the union find
    private int numComponents;

    public DisjointSet(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size <= 0 is not allowed");
        }

        this.numElements = numComponents = size;
        this.sizes = new int[size];
        parents = new int[size];

        for (int i = 0; i < size; i++) {
            parents[i] = i;  // Link to itself (self root)
            this.sizes[i] = 1;  // Each component is originally of size one
        }
    }

    // Find which component/set 'p' belongs to, takes amortized constant time.
    public int findSet(int p) {
        // Find the root of the component/set
        int root = p;
        while (root != parents[root]) {
            root = parents[root];
        }

        // Compress the path leading back to the root.
        // Doing this operation is called "path compression"
        // and is what gives us amortized time complexity.
        while (p != root) {
            int next = parents[p];
            parents[p] = root;
            p = next;
        }
        return root;
    }

    // Return whether or not the elements 'p' and
    // 'q' are in the same components/set.
    public boolean isConnected(int p, int q) {
        return findSet(p) == findSet(q);
    }

    // Unify the components/sets containing elements 'p' and 'q'
    public void union(int p, int q) {
        // These elements are already in the same group!
        if (isConnected(p, q)) {
            return;
        }

        int root1 = findSet(p);
        int root2 = findSet(q);

        // Merge smaller component/set into the larger one.
        if (sizes[root1] < sizes[root2]) {
            sizes[root2] += sizes[root1];
            parents[root1] = root2;
            sizes[root1] = 0;
        } else {
            sizes[root1] += sizes[root2];
            parents[root2] = root1;
            sizes[root2] = 0;
        }

        // Since the roots found are different we know that the
        // number of components/sets has decreased by one
        numComponents--;
    }

    // Return the size of the components/set 'p' belongs to
    public int componentSize(int p) {
        return sizes[findSet(p)];
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

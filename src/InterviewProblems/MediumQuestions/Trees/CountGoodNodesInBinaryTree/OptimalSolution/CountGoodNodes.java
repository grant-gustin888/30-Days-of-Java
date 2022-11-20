package InterviewProblems.MediumQuestions.Trees.CountGoodNodesInBinaryTree.OptimalSolution;

import DataStructures.CustomDataStructures.BinaryTree.BinaryTree;

public class CountGoodNodes {

    // Let n = the number of nodes in the binary tree.
    // Let h = the height (the longest path from root to the furthest leaf)
    // of the binary tree.
    //
    // Time: O(n)
    // --> We must traverse every node in the binary tree to
    // determine if it is a good node.
    // Space: O(h)
    // --> When we reach the furthest leaf, our call stack will contain
    // at most O(h) recursive calls from the root to the furthest leaf.

    public static void main(String[] args) {
        CountGoodNodes countGoodNodes = new CountGoodNodes();

        // Input: root = [1]
        // Output: 1
        // Explanation: Root is considered as good.
        BinaryTree tree1 = new BinaryTree(1);
        int numGoodNodes1 = countGoodNodes.goodNodes(tree1);
        System.out.println(numGoodNodes1);  // 1

        // Input: root = [2, 1, 3]
        // Output: 2
        // Explanation: Root is considered as good.
        // and node 3 is considered as good.
        BinaryTree tree2 = new BinaryTree(2);
        tree2.leftChild = new BinaryTree(1);
        tree2.rightChild = new BinaryTree(3);
        int numGoodNodes2 = countGoodNodes.goodNodes(tree2);
        System.out.println(numGoodNodes2);  // 2

        // Input: root = [3, 1, 4, 3, null, 1, 5]
        // Output: 4
        // Explanation: Nodes in blue are good.
        // Root Node (3) is always a good node.
        // Node 4 -> (3, 4) is the maximum value in the path starting from the root.
        // Node 5 -> (3, 4, 5) is the maximum value in the path
        // Node 3 -> (3, 1, 3) is the maximum value in the path.
        BinaryTree tree3 = new BinaryTree(3);
        tree3.leftChild = new BinaryTree(1);
        tree3.leftChild.leftChild = new BinaryTree(3);
        tree3.rightChild = new BinaryTree(4);
        tree3.rightChild.leftChild = new BinaryTree(1);
        tree3.rightChild.rightChild = new BinaryTree(5);
        int numGoodNodes3 = countGoodNodes.goodNodes(tree3);
        System.out.println(numGoodNodes3);  // 4

        // Input: root = [3, 3, null, 4, 2]
        // Output: 3
        // Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
        BinaryTree tree4 = new BinaryTree(3);
        tree4.leftChild = new BinaryTree(3);
        tree4.leftChild.leftChild = new BinaryTree(4);
        tree4.leftChild.rightChild = new BinaryTree(2);
        int numGoodNodes4 = countGoodNodes.goodNodes(tree4);
        System.out.println(numGoodNodes4);  // 3
    }

    /**
     * Given a binary tree root, a node X in the tree is named good
     * if in the path from root to X there are no nodes with a value
     * greater than X.
     *
     * Return the number of good nodes in the binary tree.
     *
     * Preconditions:
     * - The number of nodes in the binary tree is in the range [1, 10 ^ 5].
     * - Each node's value is between [-10 ^ 4, 10 ^ 4].
     */
    public int goodNodes(BinaryTree root) {
        // we'll use pre-order traversal to traverse the binary tree
        return dfs(root, root.value);
    }

    private int dfs(BinaryTree currentNode, int maxValueSoFar) {
        if (currentNode == null) {
            return 0;
        }

        int numGoodNodes = (currentNode.value >= maxValueSoFar) ? 1 : 0;
        maxValueSoFar = Math.max(maxValueSoFar, currentNode.value);

        numGoodNodes += dfs(currentNode.leftChild, maxValueSoFar);
        numGoodNodes += dfs(currentNode.rightChild, maxValueSoFar);
        return numGoodNodes;
    }
}

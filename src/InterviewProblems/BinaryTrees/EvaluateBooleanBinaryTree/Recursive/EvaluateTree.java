package InterviewProblems.BinaryTrees.EvaluateBooleanBinaryTree.Recursive;

import DataStructures.CustomDataStructures.BinaryTree.BinaryTree;

public class EvaluateTree {

    // Let n = the number of nodes in the binary tree.
    //
    // Time: O(n)
    // --> We must visit all nodes to evaluate the binary tree.
    // Space: O(n)
    // --> If the tree is unbalanced, then we may have to store all n nodes in the call stack.

    public static void main(String[] args) {
        EvaluateTree evaluateTree = new EvaluateTree();

        // Base cases. root nodes.

        // Input: root = [1]
        // Output: true
        // Explanation: The root node is a leaf node, and it evaluates to true, so we return true.
        BinaryTree binaryTree1 = new BinaryTree(1);
        boolean value1 = evaluateTree.evaluateTree(binaryTree1);
        System.out.println(value1);  // true

        // Input: root = [0]
        // Output: false
        // Explanation: The root node is a leaf node, and it evaluates to false, so we return false.
        BinaryTree binaryTree2 = new BinaryTree(0);
        boolean value2 = evaluateTree.evaluateTree(binaryTree2);
        System.out.println(value2);  // false

        // Recursive cases. internal nodes.

        // Input: root = [2, 0, 1, null, null, null, null]
        // Output: true
        BinaryTree binaryTree3 = new BinaryTree(2);
        binaryTree3.leftChild = new BinaryTree(0);
        binaryTree3.rightChild = new BinaryTree(1);
        boolean value3 = evaluateTree.evaluateTree(binaryTree3);
        System.out.println(value3);  // true

        // Input: root = [3, 0, 1, null, null, null, null]
        // Ouptut: false
        BinaryTree binaryTree4 = new BinaryTree(3);
        binaryTree4.leftChild = new BinaryTree(0);
        binaryTree4.rightChild = new BinaryTree(1);
        boolean value4 = evaluateTree.evaluateTree(binaryTree4);
        System.out.println(value4);  // false

        // Input: root = [2, 1, 3, null, null, 0, 1]
        // Output: true
        // Explanation: The above diagram illustrates the evaluation process.
        // The AND node evaluates to False AND True = False.
        // The OR node evaluates to True OR False = True.
        // The root node evaluates to True, so we return true.
        BinaryTree binaryTree5 = new BinaryTree(2);
        binaryTree5.leftChild = new BinaryTree(1);
        binaryTree5.rightChild = new BinaryTree(3);
        binaryTree5.rightChild.leftChild = new BinaryTree(0);
        binaryTree5.rightChild.rightChild = new BinaryTree(1);
        boolean value5 = evaluateTree.evaluateTree(binaryTree5);
        System.out.println(value5);  // true

        // TODO: Try solving this problem iteratively.
    }

    /**
     * You are given the root of a full binary tree with the following properties:
     * - Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
     * - Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and 3 represents the boolean AND.
     *
     * The evaluation of a node is as follows:
     * - If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
     * - Otherwise, evaluate the node's two children and apply the boolean operation of its value with the children's evaluations.
     *
     * Return the boolean result of evaluating the root node.
     * A full binary tree is a binary tree where each node has either 0 or 2 children.
     *
     * Preconditions:
     * - The number of nodes in the tree is in the range [1, 1000].
     * - 0 <= Node.val <= 3.
     * - Every node has either 0 or 2 children.
     * - Leaf nodes have a value of 0 or 1.
     *  -Non-leaf nodes have a value of 2 or 3.
     */
    public boolean evaluateTree(BinaryTree root) {
        if (root.value == 0) {   // base cases begin
            return false;
        } else if (root.value == 1) {
            return true;
        } else if (root.value == 2) {  // OR  // recursive cases begin
            return evaluateTree(root.leftChild) || evaluateTree(root.rightChild);
        } else {  // root.value == 3, AND
            return evaluateTree(root.leftChild) && evaluateTree(root.rightChild);
        }
    }
}
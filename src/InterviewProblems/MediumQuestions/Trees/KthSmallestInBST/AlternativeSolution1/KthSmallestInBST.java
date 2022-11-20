package InterviewProblems.MediumQuestions.Trees.KthSmallestInBST.AlternativeSolution1;

import DataStructures.CustomDataStructures.BinaryTree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestInBST {

    // inspired by NeetCode sample solutions: https://neetcode.io/practice
    // trees > Kth Smallest Element in a BST

    // Let n = the number of nodes in the BST
    // Let k = the value of k, where k <= n.
    // Let h = the height (the longest path from root to the furthest leaf)
    // of the binary tree.
    //
    // Time: O(n).
    // --> We must visit all n nodes to find the kth smallest element.
    // Space: O(h)
    // --> When we reach the furthest leaf, our call stack will contain
    // at most O(h) recursive calls from the root to the furthest leaf.

    public static void main(String[] args) {
        KthSmallestInBST kthSmallestInBST = new KthSmallestInBST();

        // Input: root = [3, 1, 4, null, 2], k = 1
        // Output: 1
        BinaryTree tree1 = new BinaryTree(3);
        tree1.leftChild = new BinaryTree(1);
        tree1.leftChild.rightChild = new BinaryTree(2);
        tree1.rightChild = new BinaryTree(4);
        int kthSmallest1 = kthSmallestInBST.kthSmallest(tree1, 1);
        System.out.println(kthSmallest1);  // 1

        // Input: root = [5, 3, 6, 2, 4, null, null, 1], k = 3
        // Output: 3
        BinaryTree tree2 = new BinaryTree(5);
        tree2.leftChild = new BinaryTree(3);
        tree2.leftChild.leftChild = new BinaryTree(2);
        tree2.leftChild.leftChild.leftChild = new BinaryTree(1);
        tree2.leftChild.rightChild = new BinaryTree(4);
        tree2.rightChild = new BinaryTree(6);
        int kthSmallest2 = kthSmallestInBST.kthSmallest(tree2, 3);
        System.out.println(kthSmallest2);  // 3

        // Input: root = [2, 1, 3], k = 3
        // Output: 3
        BinaryTree tree3 = new BinaryTree(2);
        tree3.leftChild = new BinaryTree(1);
        tree3.rightChild = new BinaryTree(3);
        int kthSmallest3 = kthSmallestInBST.kthSmallest(tree3, 3);
        System.out.println(kthSmallest3);  // 3
    }

    /**
     * Given the root of a binary search tree, and an integer k,
     * return the kth smallest value (1-indexed) of all the values
     * of the nodes in the tree.
     *
     * Preconditions:
     * - The number of nodes in the tree is n.
     * - 1 <= k <= n <= 10 ^ 4.
     * - 0 <= Node.val <= 10 ^ 4.
     */
    public int kthSmallest(BinaryTree root, int k) {
        List<Integer> elementsInBST = new ArrayList<>();
        inorder(root, elementsInBST);
        return elementsInBST.get(k - 1);
    }

    private void inorder(BinaryTree root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.leftChild, list);
        list.add(root.value);
        inorder(root.rightChild, list);
    }
}

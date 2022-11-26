package InterviewProblems.MediumQuestions.Trees.ConstructBinaryTreeFromPreorderTraversal.AlternativeSolution;

import DataStructures.CustomDataStructures.BinaryTree.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstructBinaryTree {

    // TODO: Add time and space complexity analysis here.

    // inspired by NeetCode solutions: https://neetcode.io/practice
    // Trees > Construct Binary Tree from Preorder and Inorder Traversal

    public static void main(String[] args) {
        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();

        // Input: preorder = [-1], inorder = [-1]
        // Output: [-1]
        int[] preorder1 = {-1};
        int[] inorder1 = {-1};
        BinaryTree root1 = constructBinaryTree.buildTree(preorder1, inorder1);
        System.out.println(constructBinaryTree.inorderTraversal(root1));  // [-1]

        // Input: preorder = [3, 1, 2], inorder = [1, 3, 2]
        // Output: [3, 1, null, null, 2, null, null]
        int[] preorder2 = {3, 1, 2};
        int[] inorder2 = {1, 3, 2};
        BinaryTree root2 = constructBinaryTree.buildTree(preorder2, inorder2);
        System.out.println(constructBinaryTree.inorderTraversal(root2));  // [1, 3, 2]

        // Input: preorder = [3, 9, 20, 15, 7], inorder = [9, 3, 15, 20, 7]
        // Output: [3, 9, 20, null, null, 15, 7]
        int[] preorder3 = {3, 9, 20, 15, 7};
        int[] inorder3 = {9, 3, 15, 20, 7};
        BinaryTree root3 = constructBinaryTree.buildTree(preorder3, inorder3);
        System.out.println(constructBinaryTree.inorderTraversal(root3));  // [9, 3, 15, 20, 7]

        // Input: preorder = [1, 2], inorder = [2, 1]
        // Output [1, null, 2]
        int[] preorder4 = {1, 2};
        int[] inorder4 = {2, 1};
        BinaryTree root4 = constructBinaryTree.buildTree(preorder4, inorder4);
        System.out.println(constructBinaryTree.inorderTraversal(root4));  // [1, 2]
    }


    /**
     * Given two integer arrays preorder and inorder where preorder is the preorder
     * traversal of a binary tree and inorder is the inorder traversal of the same
     * tree, construct and return the binary tree.
     *
     * Preconditions:
     * - 1 <= preorder.length <= 3000
     * - inorder.length == preorder.length
     * - -3000 <= preorder[i], inorder[i] <= 3000
     * - preorder and inorder consist of unique values.
     * - Each value of inorder also appears in preorder.
     * - preorder is guaranteed to be the preorder traversal of the tree.
     * - inorder is guaranteed to be the inorder traversal of the tree.
     */
    public BinaryTree buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        BinaryTree root = new BinaryTree(preorder[0]);
        int middle = linearSearch(inorder, preorder[0]);
        root.leftChild = buildTree(
            Arrays.copyOfRange(preorder, 1, middle + 1),
            Arrays.copyOfRange(inorder, 0, middle)
        );
        root.rightChild = buildTree(
            Arrays.copyOfRange(preorder, middle + 1, preorder.length),
            Arrays.copyOfRange(inorder, middle + 1, inorder.length)
        );
        return root;
    }

    private int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;  // this will never happen
    }

    private List<Integer> inorderTraversal(BinaryTree root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> items = new ArrayList<>();
        List<Integer> leftTraversal = inorderTraversal(root.leftChild);
        List<Integer> rightTraversal = inorderTraversal(root.rightChild);

        items.addAll(leftTraversal);
        items.add(root.value);
        items.addAll(rightTraversal);
        return items;
    }
}

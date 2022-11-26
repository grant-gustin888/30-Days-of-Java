package InterviewProblems.MediumQuestions.Trees.ConstructBinaryTreeFromPreorderTraversal.NaiveSolution;

import DataStructures.CustomDataStructures.BinaryTree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class ConstructBinaryTree {

    // TODO: Add time and space complexity analysis here.

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
        List<Integer> preorderList = convertArrayToList(preorder);
        List<Integer> inorderList = convertArrayToList(inorder);
        return buildTree(preorderList, inorderList);
    }

    private BinaryTree buildTree(List<Integer> preorderList, List<Integer> inorderList) {
        if (preorderList.size() == 0) {
            return null;
        } else if (preorderList.size() == 1) {
            return new BinaryTree(preorderList.get(0));
        } else {
            // find root node
            // System.out.println(preorderList);
            // System.out.println(inorderList);
            BinaryTree root = new BinaryTree(preorderList.get(0));

            // split up the preorder and inorder arrays into the left and right subtrees....
            int middleIndex = linearSearch(inorderList, root.value);
            List<Integer> leftInorderSubarray = getSubarray(inorderList, 0, middleIndex);
            List<Integer> rightInorderSubarray = getSubarray(inorderList, middleIndex + 1, preorderList.size());

            List<Integer> leftPreorderSubarray = getSubarray(preorderList, 1, 1 + leftInorderSubarray.size());
            List<Integer> rightPreorderSubarray = getSubarray(preorderList, 1 + leftInorderSubarray.size(), preorderList.size());

            root.leftChild = buildTree(leftPreorderSubarray, leftInorderSubarray);
            root.rightChild = buildTree(rightPreorderSubarray, rightInorderSubarray);

            return root;
        }
    }

    private List<Integer> convertArrayToList(int[] array) {
        List<Integer> newList = new ArrayList<>();
        for (int n : array) {
            newList.add(n);
        }
        return newList;
    }

    private List<Integer> getSubarray(List<Integer> array, int startIndex, int endIndex) {
        List<Integer> newList = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            newList.add(array.get(i));
        }
        return newList;
    }

    private int linearSearch(List<Integer> array, int targetValue) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == targetValue) {
                return i;
            }
        }
        return -1;  // this will never happen....
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

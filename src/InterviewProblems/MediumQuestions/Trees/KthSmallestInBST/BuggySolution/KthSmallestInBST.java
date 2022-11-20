package InterviewProblems.MediumQuestions.Trees.KthSmallestInBST.BuggySolution;

import DataStructures.CustomDataStructures.BinaryTree.BinaryTree;

public class KthSmallestInBST {

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

    // 1st try: incorrect.
    public int kthSmallest(BinaryTree root, int k) {
        return inOrderTraversal(root, 0, k);
    }

    // 1st try: incorrect solution.
    private int inOrderTraversal(BinaryTree root, int currentK, int targetK) {
        if (root.leftChild != null) {
            return inOrderTraversal(root.leftChild, currentK + 1, targetK);
        }

        // do stuff in root. -- root must not be empty...
        if (currentK == targetK) {
            return root.value;
        }

        if (root.rightChild != null) {
            return inOrderTraversal(root.rightChild, currentK + 1, targetK);
        }

        return -1;
    }
}

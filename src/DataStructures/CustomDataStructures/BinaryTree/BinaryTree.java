package DataStructures.CustomDataStructures.BinaryTree;

import java.util.Stack;

public class BinaryTree {

    public int value;
    public BinaryTree leftChild;
    public BinaryTree rightChild;

    public BinaryTree(int newValue) {
        this.value = newValue;
        this.leftChild = null;
        this.rightChild = null;
    }

    public BinaryTree(int newValue, BinaryTree leftChild, BinaryTree rightChild) {
        this.value = newValue;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void inorderTraversalRecursive(BinaryTree root) {
        inorderTraversalRecursive(root, 0);
        System.out.println();
    }

    private void inorderTraversalRecursive(BinaryTree root, int depth) {
        if (root == null) {
            return;
        }

        String spaces = getIndentation(depth);
        inorderTraversalRecursive(root.leftChild, depth + 1);
        System.out.println(spaces + root.value);
        inorderTraversalRecursive(root.rightChild, depth + 1);
    }

    public void inorderTraversalIterative(BinaryTree root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree current = root;
        while (current != null || !stack.isEmpty()) {
            // Go as far left as possible... before going to the right child
            if (current != null) {
                stack.push(current);
                current = current.leftChild;
            } else {
                current = stack.pop();
                System.out.print(current.value + " ");
                current = current.rightChild;
            }

            // alternative solution:
            //
            // while (current != null) {
            //     stack.push(current);
            //     current = current.leftChild;
            // }
            //
            // current = stack.pop();
            // System.out.print(current.value + " ");
            // current = current.rightChild;
        }
    }

    public void levelOrderTraversal(BinaryTree root) {
        levelOrderTraversal(root, 0);
        System.out.println();
    }

    private void levelOrderTraversal(BinaryTree root, int depth) {
        if (root == null) {
            return;
        }

        String spaces = getIndentation(depth);
        System.out.println(spaces + root.value);
        levelOrderTraversal(root.leftChild, depth + 1);
        levelOrderTraversal(root.rightChild, depth + 1);
    }

    private String getIndentation(int depth) {
        String spaces = "";
        for (int i = 0; i < depth; i++) {
            spaces += "\t";
        }
        return spaces;
    }
}

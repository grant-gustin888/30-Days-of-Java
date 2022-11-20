package InterviewProblems.MediumQuestions.Trees.BinaryTreeRightSideView.OriginalSolution;

import DataStructures.CustomDataStructures.BinaryTree.BinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BinaryTreeRightSideView {

    // Let n = the number of nodes in the binary tree.
    //
    // Time: O(n)
    // --> We must traverse all binary tree nodes to find the right side nodes.
    // Space: O(n)
    // --> We may need to store all binary tree nodes in the queue and the hashmap

    public static void main(String[] args) {
        BinaryTreeRightSideView binaryTreeRightSideView = new BinaryTreeRightSideView();

        // Input: root = []
        // Output: []
        BinaryTree binaryTree1 = null;
        List<Integer> rightSideNodes1 = binaryTreeRightSideView.rightSideView(binaryTree1);
        System.out.println(rightSideNodes1);  // []

        // Input: root = [1]
        // Output: [1]
        BinaryTree binaryTree2 = new BinaryTree(1);
        List<Integer> rightSideNodes2 = binaryTreeRightSideView.rightSideView(binaryTree2);
        System.out.println(rightSideNodes2);  // [1]

        // Input: root = [1, 2, 3, null, 5, null, 4]
        // Output: [1, 3, 4]
        BinaryTree binaryTree3 = new BinaryTree(1);
        binaryTree3.leftChild = new BinaryTree(2);
        binaryTree3.rightChild = new BinaryTree(3);
        binaryTree3.leftChild.rightChild = new BinaryTree(5);
        binaryTree3.rightChild.rightChild = new BinaryTree(4);
        List<Integer> rightSideNodes3 = binaryTreeRightSideView.rightSideView(binaryTree3);
        System.out.println(rightSideNodes3);  // [1, 3, 4]

        // Input: root = [1, null, 3]
        // Output: [1, 3]
        BinaryTree binaryTree4 = new BinaryTree(1);
        binaryTree4.rightChild = new BinaryTree(3);
        List<Integer> rightSideNodes4 = binaryTreeRightSideView.rightSideView(binaryTree4);
        System.out.println(rightSideNodes4);  // [1, 3]

        // Input: root = [4, 2, null, 1, 3, null, null]
        // Output: [4, 2, 3]
        BinaryTree binaryTree5 = new BinaryTree(4);
        binaryTree5.leftChild = new BinaryTree(2);
        binaryTree5.leftChild.leftChild = new BinaryTree(1);
        binaryTree5.leftChild.rightChild = new BinaryTree(3);
        List<Integer> rightSideNodes5 = binaryTreeRightSideView.rightSideView(binaryTree5);
        System.out.println(rightSideNodes5);  // [4, 2, 3]

        // Input: root = [4, 2, 5, null, 3, null, null]
        // Output: [4, 5, 3]
        BinaryTree binaryTree6 = new BinaryTree(4);
        binaryTree6.leftChild = new BinaryTree(2);
        binaryTree6.rightChild = new BinaryTree(5);
        binaryTree6.leftChild.rightChild = new BinaryTree(3);
        List<Integer> rightSideNodes6 = binaryTreeRightSideView.rightSideView(binaryTree6);
        System.out.println(rightSideNodes6);  // [4, 5, 3]
    }

    /**
     * Given the root of a binary tree, imagine yourself standing on the right
     * side of it, return the values of the nodes you can see ordered from top to bottom.
     *
     * Preconditions:
     * - The number of nodes in the tree is in the range [0, 100].
     * - -100 <= Node.val <= 100.
     */
    public List<Integer> rightSideView(BinaryTree root) {
        // do bfs on binary tree
        // only the last node on each level is visible from the right side.
        Map<Integer, List<BinaryTree>> levelsToNodes = new HashMap<>();
        int currentLevel = 0;
        levelsToNodes.put(currentLevel, new ArrayList<>());
        Queue<BinaryTree> queueOfNodes = new LinkedList<>();
        List<Integer> rightSideNodes = new ArrayList<>();

        // visit root
        if (root != null) {
            levelsToNodes.get(currentLevel).add(root);
            queueOfNodes.add(root);
        }

        // visit all other children in the binary tree
        while (!queueOfNodes.isEmpty()) {
            // take a snapshot of all elements currently in queue at the current level.
            // add all items at each level to the levelsToNodes hashmap.
            // and add them to queue
            // if it's the last element of the queue, add them to rightSideNodes.
            levelsToNodes.put(currentLevel + 1, new ArrayList<>());
            for (int i = 0; i < levelsToNodes.get(currentLevel).size(); i++) {
                // we won't try to pop from an empty queue!
                assert !queueOfNodes.isEmpty();
                BinaryTree currentNode = queueOfNodes.poll();

                // add last node of each level to list of nodes visible from the right side.
                if (i == levelsToNodes.get(currentLevel).size() - 1) {
                    rightSideNodes.add(currentNode.value);
                }

                // add children to queue
                if (currentNode.leftChild != null) {
                    levelsToNodes.get(currentLevel + 1).add(currentNode.leftChild);
                    queueOfNodes.add(currentNode.leftChild);
                }

                if (currentNode.rightChild != null) {
                    levelsToNodes.get(currentLevel + 1).add(currentNode.rightChild);
                    queueOfNodes.add(currentNode.rightChild);
                }
            }

            currentLevel++;
        }
        return rightSideNodes;
    }
}

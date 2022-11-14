package InterviewProblems.MediumQuestions.LinkedLists.CopyListWithRandomPointer;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    // Let n = the length of the linked list.
    //
    // Time: O(n)
    // --> O(n) for the first pass to create the new nodes
    // --> O(n) for the second pass to set the next and random pointers of the new nodes
    // Space: O(n)
    // --> O(n) for the map of the old nodes and the new nodes.

    public static void main(String[] args) {
        CopyListWithRandomPointer copyListWithRandomPointer = new CopyListWithRandomPointer();

        // Input: head = [[7, null], [13, 0], [11, 4], [10, 2], [1, 0]]
        // Output: [[7, null], [13, 0], [11, 4], [10, 2], [1, 0]]
        Node node1 = new Node(7, null, null);
        Node node2 = new Node(13, null, null);
        Node node3 = new Node(11, null, null);
        Node node4 = new Node(10, null, null);
        Node node5 = new Node(1, null, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;
        Node deepCopy1 = copyListWithRandomPointer.copyRandomList(node1);
        System.out.println(deepCopy1);
        // 7 -> 13 -> 11 -> 10 -> 1
        // RANDOM NULL -> RANDOM 7 -> RANDOM 1 -> RANDOM 11 -> RANDOM 7

        // Input: head = [[1, 1], [2, 1]]
        // Output: [[1, 1], [2, 1]]
        Node node6 = new Node(1, null, null);
        Node node7 = new Node(2, null, null);
        node6.next = node7;
        node7.next = null;
        node6.random = node7;
        node7.random = node7;
        Node deepCopy2 = copyListWithRandomPointer.copyRandomList(node6);
        System.out.println(deepCopy2);
        // 1 -> 2
        // RANDOM 2 -> RANDOM 2

        // Input: head = [[3, null], [3, 0], [3, null]]
        // Output: [[3, null], [3, 0], [3, null]]
        Node node8 = new Node(3, null, null);
        Node node9 = new Node(3, null, null);
        Node node10 = new Node(3, null, null);
        node8.next = node9;
        node9.next = node10;
        node10.next = null;
        node8.random = null;
        node9.random = node8;
        node10.random = null;
        Node deepCopy3 = copyListWithRandomPointer.copyRandomList(node8);
        System.out.println(deepCopy3);
        // 3 -> 3 -> 3
        // RANDOM NULL -> RANDOM 3 -> RANDOM NULL
    }

    /**
     * A linked list of length n is given such that each node contains an additional
     * random pointer, which could point to any node in the list, or null.
     *
     * Construct a deep copy of the list. The deep copy should consist of exactly n
     * brand new nodes, where each new node has its value set to the value of its
     * corresponding original node. Both the next and random pointer of the new nodes
     * should point to new nodes in the copied list such that the pointers in the
     * original list and copied list represent the same list state.
     *
     * None of the pointers in the new list should point to nodes in the original list.
     *
     * For example, if there are two nodes X and Y in the original list, where
     * X.random --> Y, then for the corresponding two nodes x and y in the copied list,
     * x.random --> y.
     *
     * Return the head of the copied linked list.
     *
     * The linked list is represented in the input/output as a list of n nodes.
     * Each node is represented as a pair of [val, random_index] where:
     *
     * val: an integer representing Node.val
     * random_index: the index of the node (range from 0 to n-1) that the random
     * pointer points to, or null if it does not point to any node.
     *
     * Your code will only be given the head of the original linked list.
     *
     * Preconditions:
     * - 0 <= n <= 1000.
     * - -10 ^ 4 <= Node.val <= 10 ^ 4.
     * - Node.random is null or is pointing to some node in the linked list.
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // mapping between old linked list nodes and new linked list nodes
        Map<Node, Node> oldNodesToNewNodes = new HashMap<>();

        // 1st pass: create a deep copy of all nodes only
        Node oldCurr = head;
        while (oldCurr != null) {
            Node newNode = new Node(oldCurr.value);
            oldNodesToNewNodes.put(oldCurr, newNode);
            oldCurr = oldCurr.next;
        }

        // 2nd pass: establish pointers in new nodes using old nodes
        // and the dictionary.
        Node newCurr = null;
        oldCurr = head;  // go back to the beginning of the old linked list
        while (oldCurr != null) {
            Node newLinkedListNode = oldNodesToNewNodes.get(oldCurr);
            newLinkedListNode.next = oldNodesToNewNodes.get(oldCurr.next);
            newLinkedListNode.random = oldNodesToNewNodes.get(oldCurr.random);
            oldCurr = oldCurr.next;
        }

        return oldNodesToNewNodes.get(head);
    }

    // My 1st attempt:
    //
    // public Node copyRandomList(Node head) {
    //     Map<Node, Node> oldNodesToNewNodes = new HashMap<>();
    //     Node newLinkedListCurrentNode = null;
    //     Node originalLinkedListCurrentNode = head;
    //     Node headOfNewLinkedList = null;
    //
    //     while (originalLinkedListCurrentNode != null) {
    //         // create new linked list node based on original linked list node.
    //         Node newLinkedListNode = new Node(originalLinkedListCurrentNode.val);
    //
    //         // set next and random attributes appropriately
    //         if (newLinkedListCurrentNode == null) {
    //             headOfNewLinkedList = newLinkedListNode;
    //             newLinkedListCurrentNode = newLinkedListNode;
    //         } else {
    //             newLinkedListCurrentNode.next = newLinkedListNode;
    //         }
    //
    //         oldNodesToNewNodes.put(originalLinkedListCurrentNode, newLinkedListCurrentNode);
    //
    //         newLinkedListNode.next = originalLinkedListCurrentNode.next;
    //         newLinkedListNode.random = originalLinkedListCurrentNode.random;
    //
    //         originalLinkedListCurrentNode = originalLinkedListCurrentNode.next;
    //     }
    //     return headOfNewLinkedList;
    // }
}

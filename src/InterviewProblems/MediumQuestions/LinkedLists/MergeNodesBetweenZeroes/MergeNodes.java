package InterviewProblems.MediumQuestions.LinkedLists.MergeNodesBetweenZeroes;

import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.Node;
import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.SinglyLinkedList;

public class MergeNodes {

    // Let n = the number of nodes in the linked list.
    //
    // Time: O(n)
    // --> We must traverse all elements in the linked list to produce the linked list
    // with merged nodes.
    // Space: O(1)
    // --> We only store 5 variables: sumBetweenZeroNodes, currentZeroNode, previousNode,
    // currentNode, and nextZeroNode -- and we modify the linked list head in place.

    public static void main(String[] args) {
        MergeNodes mergeNodes = new MergeNodes();

        // Input: head = [0, 1, 0, 3, 0, 2, 2, 0]
        // Output: [1, 3, 4]
        // Explanation:
        // The above figure represents the given linked list. The modified list contains
        // - The sum of the nodes marked in green: 1 = 1.
        // - The sum of the nodes marked in red: 3 = 3.
        // - The sum of the nodes marked in yellow: 2 + 2 = 4.
        SinglyLinkedList<Integer> linkedList1 = new SinglyLinkedList<>();
        linkedList1.insert(0, 0);
        linkedList1.insert(1, 1);
        linkedList1.insert(0, 2);
        linkedList1.insert(3, 3);
        linkedList1.insert(0, 4);
        linkedList1.insert(2, 5);
        linkedList1.insert(2, 6);
        linkedList1.insert(0, 7);
        Node<Integer> headOfMergedNodes1 = mergeNodes.mergeNodes(linkedList1.head);
        mergeNodes.print(headOfMergedNodes1);  // [1, 3, 4]

        // Input: head = [0, 3, 1, 0, 4, 5, 2, 0]
        // Output: [4, 11]
        // Explanation:
        // The above figure represents the given linked list. The modified list contains
        // - The sum of the nodes marked in green: 3 + 1 = 4.
        // - The sum of the nodes marked in red: 4 + 5 + 2 = 11.
        SinglyLinkedList<Integer> linkedList2 = new SinglyLinkedList<>();
        linkedList2.insert(0, 0);
        linkedList2.insert(3, 1);
        linkedList2.insert(1, 2);
        linkedList2.insert(0, 3);
        linkedList2.insert(4, 4);
        linkedList2.insert(5, 5);
        linkedList2.insert(2, 6);
        linkedList2.insert(0, 7);
        Node<Integer> headOfMergedNodes2 = mergeNodes.mergeNodes(linkedList2.head);
        mergeNodes.print(headOfMergedNodes2);  // [4, 11]
    }

    /**
     * You are given the head of a linked list, which contains a series of integers separated by 0's.<br>
     * The beginning and end of the linked list will have Node.val == 0.<br><br>
     *
     * For every two consecutive 0's, merge all the nodes lying in between them into a single node<br>
     * whose value is the sum of all the merged nodes. The modified list should not contain any 0's.<br><br>
     *
     * Return the head of the modified linked list.<br><br>
     *
     * Preconditions:<br>
     * - The number of nodes in the list is in the range [3, 2 * 10 ^ 5].<br>
     * - 0 <= Node.val <= 1000.<br>
     * - There are no two consecutive nodes with Node.val == 0.<br>
     * - The beginning and end of the linked list have Node.val == 0.
     */
    public Node<Integer> mergeNodes(Node<Integer> head) {
        int sumBetweenZeroNodes = 0;
        Node<Integer> currentZeroNode = head;
        Node<Integer> previousNode = currentZeroNode;
        Node<Integer> currentNode = currentZeroNode.next;
        Node<Integer> nextZeroNode = getNextZeroNode(currentNode);

        while (currentNode != null) {  // or nextZeroNode != null
            if (currentNode.value != 0) {
                // add to sumBetweenZeroNodes's value
                sumBetweenZeroNodes += currentNode.value;

                // remove currentNode from linked list
                previousNode.next = null;
                currentZeroNode.next = currentNode.next;
            } else {  // currentNode.value == 0
                // save the value of sumBetweenZeroNodes in a zero node
                currentNode.value = sumBetweenZeroNodes;

                // reset sumBetweenZeroNodes's value
                sumBetweenZeroNodes = 0;

                // move currentZeroNode and nextZeroNode pointers.
                currentZeroNode = nextZeroNode;
                nextZeroNode = getNextZeroNode(currentNode);
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        // remove first 0 node
        head = head.next;

        // assert currentNode == null;
        // assert currentZeroNode != null;
        // assert nextZeroNode == null;
        // assert sumBetweenZeroNodes == 0;
        return head;
    }

    /**
     * Return the next zero node in this linked list starting from currentNode,
     * or null if there are no more zero nodes in this linked list.
     *
     * Precondition:
     * - zero nodes are always at least 1 node apart from one another.
     */
    private Node<Integer> getNextZeroNode(Node<Integer> currentNode) {
        while (currentNode != null) {
            if (currentNode.value == 0) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;  // if there are no more zero nodes left.
    }

    private void print(Node<Integer> head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        Node<Integer> currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.value);
            if (currentNode.next != null) {
                System.out.print(" -> ");
            }
            currentNode = currentNode.next;
        }
        System.out.println();
    }
}

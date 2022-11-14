package InterviewProblems.MediumQuestions.LinkedLists.RemoveNthNodeFromEndOfList;

import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.Node;
import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.SinglyLinkedList;

public class RemoveNthNode<T> {

    // Let m = the length of the linked list, and n = the value of n, where n <= m.
    //
    // Time: O(m)
    // --> O(n) to find the linked list node n nodes ahead of the target node
    // --> O(m - n) to move the target node to the nth node from the end of the list,
    // and the current node to the end of the linked list
    // --> O(1) to delete the target node from the linked list
    // Space: O(1)
    // --> We don't need any additional data structures, apart from a fixed number
    // of linked list nodes (e.g., currentNode, targetNode, previousTargetNode).

    public static void main(String[] args) {
        RemoveNthNode<Integer> removeNthNode = new RemoveNthNode<>();

        // Input: head = [1], n = 1
        // Output: []
        SinglyLinkedList<Integer> singlyLinkedList1 = new SinglyLinkedList<>();
        singlyLinkedList1.insert(1, 0);
        Node<Integer> nthNode1 = removeNthNode.removeNthFromEnd(singlyLinkedList1.head, 1);
        removeNthNode.print(nthNode1);  // null

        // Input: head = [1, 2], n = 1
        // Output: [1]
        SinglyLinkedList<Integer> singlyLinkedList2 = new SinglyLinkedList<>();
        singlyLinkedList2.insert(1, 0);
        singlyLinkedList2.insert(2, 1);
        Node<Integer> nthNode2 = removeNthNode.removeNthFromEnd(singlyLinkedList2.head, 1);
        removeNthNode.print(nthNode2);  // 1

        // Input: head = [1, 2, 3, 4, 5], n = 2
        // Output: [1, 2, 3, 5]
        SinglyLinkedList<Integer> singlyLinkedList3 = new SinglyLinkedList<>();
        singlyLinkedList3.insert(1, 0);
        singlyLinkedList3.insert(2, 1);
        singlyLinkedList3.insert(3, 2);
        singlyLinkedList3.insert(4, 3);
        singlyLinkedList3.insert(5, 4);
        Node<Integer> nthNode3 = removeNthNode.removeNthFromEnd(singlyLinkedList3.head, 2);
        removeNthNode.print(nthNode3);  // 1 -> 2 -> 3 -> 5

        // Input: head = [1, 2, 3, 4, 5], n = 1
        // Output: [1, 2, 3, 4]
        SinglyLinkedList<Integer> singlyLinkedList4 = new SinglyLinkedList<>();
        singlyLinkedList4.insert(1, 0);
        singlyLinkedList4.insert(2, 1);
        singlyLinkedList4.insert(3, 2);
        singlyLinkedList4.insert(4, 3);
        singlyLinkedList4.insert(5, 4);
        Node<Integer> nthNode4 = removeNthNode.removeNthFromEnd(singlyLinkedList4.head, 1);
        removeNthNode.print(nthNode4);  // 1 -> 2 -> 3 -> 4
    }

    /**
     * Given the head of a linked list, remove the nth node from the
     * end of the list and return its head.
     *
     * Preconditions:
     * - The number of nodes in the list is sz.
     * - 1 <= sz <= 30.
     * - 0 <= Node.val <= 100.
     * - 1 <= n <= sz.
     */
    public Node<T> removeNthFromEnd(Node<T> head, int n) {
        // Position currentNode n nodes ahead of targetNode.
        Node<T> currentNode = getCurrentNode(head, n);

        // Move targetNode to the nth node from the right, and
        // move currentNode to the end of the linked list.
        Node<T> previousTargetNode = null;
        Node<T> targetNode = head;
        while (currentNode != null) {
            previousTargetNode = targetNode;
            targetNode = targetNode.next;
            currentNode = currentNode.next;
        }

        // Delete the nth node from the linked list.
        if (previousTargetNode == null) {
            head = head.next;
        } else {  // previousTargetNode == null
            previousTargetNode.next = targetNode.next;
        }

        // Return a new linked list without the nth node.
        return head;
    }

    private Node<T> getCurrentNode(Node<T> head, int n) {
        Node<T> currentNode = head;
        int offset = 0;
        while (currentNode != null && offset < n) {
            currentNode = currentNode.next;
            offset++;
        }
        return currentNode;
    }

    public void print(Node<T> head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node<T> currentNode = head;
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

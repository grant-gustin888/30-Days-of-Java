package InterviewProblems.MediumQuestions.LinkedLists.DeleteTheMiddleNodeFromALinkedList;

import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.Node;
import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.SinglyLinkedList;

public class DeleteTheMiddleNode {

    // Let n = the length of the linked list
    //
    // Time: O(n)
    // --> O(n) to move the fast pointer to the end of the linked list
    // --> O(1) to delete the middle node
    // Space: O(1)
    // --> We only store 3 variables, previousNode, slow, and fast.

    public static void main(String[] args) {
        DeleteTheMiddleNode deleteTheMiddleNode = new DeleteTheMiddleNode();

        // Input: head = [1, 3, 4, 7, 1, 2, 6]
        // Output: [1, 3, 4, 1, 2, 6]
        // Explanation:
        // The above figure represents the given linked list. The indices of the nodes are written below.
        // Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
        // We return the new list after removing this node.
        SinglyLinkedList<Integer> linkedList1 = new SinglyLinkedList<>();
        linkedList1.insert(1, 0);
        linkedList1.insert(3, 1);
        linkedList1.insert(4, 2);
        linkedList1.insert(7, 3);
        linkedList1.insert(1, 4);
        linkedList1.insert(2, 5);
        linkedList1.insert(6, 6);
        Node<Integer> headOfDeletedNode1 = deleteTheMiddleNode.deleteMiddle(linkedList1.head);
        deleteTheMiddleNode.print(headOfDeletedNode1);  // [1, 3, 4, 1, 2, 6]

        // Input: head = [1, 2, 3, 4]
        // Output: [1, 2, 4]
        // Explanation:
        // The above figure represents the given linked list.
        // For n = 4, node 2 with value 3 is the middle node, which is marked in red.
        SinglyLinkedList<Integer> linkedList2 = new SinglyLinkedList<>();
        linkedList2.insert(1, 0);
        linkedList2.insert(2, 1);
        linkedList2.insert(3, 2);
        linkedList2.insert(4, 3);
        Node<Integer> headOfDeletedNode2 = deleteTheMiddleNode.deleteMiddle(linkedList2.head);
        deleteTheMiddleNode.print(headOfDeletedNode2);  // [1, 2, 4]

        // Input: head = [2, 1]
        // Output: [2]
        // Explanation:
        // The above figure represents the given linked list.
        // For n = 2, node 1 with value 1 is the middle node, which is marked in red.
        // Node 0 with value 2 is the only node remaining after removing node 1.
        SinglyLinkedList<Integer> linkedList3 = new SinglyLinkedList<>();
        linkedList3.insert(2, 0);
        linkedList3.insert(1, 1);
        Node<Integer> headOfDeletedNode3 = deleteTheMiddleNode.deleteMiddle(linkedList3.head);
        deleteTheMiddleNode.print(headOfDeletedNode3);  // [2]

        // Input: head = [1]
        // Output: []
        // Explanation:
        // The above figure represents the given linked list.
        SinglyLinkedList<Integer> linkedList4 = new SinglyLinkedList<>();
        linkedList4.insert(1, 0);
        Node<Integer> headOfDeletedNode4 = deleteTheMiddleNode.deleteMiddle(linkedList4.head);
        deleteTheMiddleNode.print(headOfDeletedNode4);  // []
    }

    /**
     * You are given the head of a linked list. Delete the middle node, and
     * return the head of the modified linked list.
     *
     * The middle node of a linked list of size n is the ⌊n / 2⌋th node from
     * the start using 0-based indexing, where ⌊x⌋ denotes the largest integer
     * less than or equal to x.
     *
     * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2,
     * respectively.
     *
     * Preconditions:
     * - The number of nodes in the list is in the range [1, 10 ^ 5].
     * - 1 <= Node.val <= 10 ^ 5.
     */
    public Node<Integer> deleteMiddle(Node<Integer> head) {
        // find the middle node
        Node<Integer> previousNode = null;
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        while (fast != null && fast.next != null) {
            previousNode = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // delete the middle node
        if (previousNode != null) {
            previousNode.next = slow.next;
        } else if (slow != null) {
            head = slow.next;
        }

        // return the head of the modified linked list
        return head;
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

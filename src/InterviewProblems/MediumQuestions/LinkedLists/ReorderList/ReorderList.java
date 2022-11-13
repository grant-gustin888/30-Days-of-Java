package InterviewProblems.MediumQuestions.LinkedLists.ReorderList;

import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.Node;
import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.SinglyLinkedList;

public class ReorderList<T> {

    // Let n = the length of the linked list.
    //
    // Time: O(n)
    // --> O(n) to find the middle linked list node.
    // --> O(n) to reverse the second half of the linked list.
    // --> O(n) to interweave the 2 halves of the linked list.
    // Space: O(1)
    // --> O(1) for middleNode, reversedSecondHalf, firstHalf, and more.
    // --> We also only swap nodes in place, and the reverse the second half of
    // the linked list in place, so we don't need any extra space.

    public static void main(String[] args) {
        ReorderList<Integer> reorderList = new ReorderList<>();

        // Input: head = [1]
        // Output: [1]
        SinglyLinkedList<Integer> singlyLinkedList1 = new SinglyLinkedList<>();
        singlyLinkedList1.insert(1, 0);
        reorderList.reorderList(singlyLinkedList1.head);
        reorderList.print(singlyLinkedList1.head);  // 1

        // Input: head = [1, 2]
        // Output: [1, 2]
        SinglyLinkedList<Integer> singlyLinkedList2 = new SinglyLinkedList<>();
        singlyLinkedList2.insert(1, 0);
        singlyLinkedList2.insert(2, 1);
        reorderList.reorderList(singlyLinkedList2.head);
        reorderList.print(singlyLinkedList2.head);  // 1 -> 2

        // Input: head = [1, 2, 3, 4]
        // Output: [1, 4, 2, 3]
        SinglyLinkedList<Integer> singlyLinkedList3 = new SinglyLinkedList<>();
        singlyLinkedList3.insert(1, 0);
        singlyLinkedList3.insert(2, 1);
        singlyLinkedList3.insert(3, 2);
        singlyLinkedList3.insert(4, 3);
        reorderList.reorderList(singlyLinkedList3.head);
        reorderList.print(singlyLinkedList3.head);  // 1 -> 4 -> 2 -> 3

        // Input: head = [1, 2, 3, 4, 5]
        // Output: [1, 5, 2, 4, 3]
        SinglyLinkedList<Integer> singlyLinkedList4 = new SinglyLinkedList<>();
        singlyLinkedList4.insert(1, 0);
        singlyLinkedList4.insert(2, 1);
        singlyLinkedList4.insert(3, 2);
        singlyLinkedList4.insert(4, 3);
        singlyLinkedList4.insert(5, 4);
        reorderList.reorderList(singlyLinkedList4.head);
        reorderList.print(singlyLinkedList4.head);  // 1 -> 5 -> 2 -> 4 -> 3
    }

    /**
     * You are given the head of a singly linked-list. The list can be represented as:
     * L0 → L1 → … → Ln - 1 → Ln
     *
     * Reorder the list to be on the following form:
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     *
     * You may not modify the values in the list's nodes.
     * Only nodes themselves may be changed.
     *
     * Preconditions:
     * - The number of nodes in the list is in the range [1, 5 * 10 ^ 4].
     * - 1 <= Node.val <= 1000.
     */
    public void reorderList(Node<T> head) {
        if (head == null || head.next == null) {
            return;
        }

        // find the middle of the linked list
        Node<T> middleNode = getMiddleOfLinkedList(head);

        // reverse the second half of the linked list
        Node<T> reversedSecondHalf = reverse(middleNode.next);
        middleNode.next = null;

        // interleave the first and second halves of the linked list together
        Node<T> firstHalf = head;
        while (firstHalf != null && reversedSecondHalf != null) {
            Node<T> oldFirstHalfNextNode = firstHalf.next;
            firstHalf.next = reversedSecondHalf;
            firstHalf = oldFirstHalfNextNode;

            Node<T> oldSecondHalfNextNode = reversedSecondHalf.next;
            reversedSecondHalf.next = firstHalf;
            reversedSecondHalf = oldSecondHalfNextNode;
        }
    }

    private Node<T> getMiddleOfLinkedList(Node<T> head) {
        Node<T> slowNode = head;
        Node<T> fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return slowNode;
    }

    private Node<T> reverse(Node<T> head) {
        Node<T> previousNode = null;
        Node<T> currentNode = head;
        while (currentNode != null) {
            Node<T> oldNextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = oldNextNode;
        }
        return previousNode;
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

package InterviewProblems.MediumQuestions.LinkedLists.LinkedListComponents;

import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.Node;
import DataStructures.CustomDataStructures.LinkedList.SinglyLinkedListImplementation.SinglyLinkedList;

import java.util.HashSet;
import java.util.Set;

public class ConnectedComponents {

    // Let n = the number of nodes in the linked list.
    //
    // Time: O(n)
    // --> O(n) to convert the nums array into a set
    // --> O(n) to iterate through all the nodes in the linked list to determine if they
    // belong to the same connected component
    // Space: O(n)
    // --> We store up to k <= n nodes in the numbersSeen set.

    public static void main(String[] args) {
        ConnectedComponents connectedComponents = new ConnectedComponents();

        // Input: head = [0, 1, 2, 3], nums = [0, 1, 3]
        // Output: 2
        // Explanation: 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
        SinglyLinkedList<Integer> linkedList1 = new SinglyLinkedList<>();
        linkedList1.insert(0, 0);
        linkedList1.insert(1, 1);
        linkedList1.insert(2, 2);
        linkedList1.insert(3, 3);
        int[] nums1 = {0, 1, 3};
        int numConnectedComponents1 = connectedComponents.numComponents(linkedList1.head, nums1);
        System.out.println(numConnectedComponents1);  // 2

        // Input: head = [0, 1, 2, 3, 4], nums = [0, 3, 1, 4]
        // Output: 2
        // Explanation: 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
        SinglyLinkedList<Integer> linkedList2 = new SinglyLinkedList<>();
        linkedList2.insert(0, 0);
        linkedList2.insert(1, 1);
        linkedList2.insert(2, 2);
        linkedList2.insert(3, 3);
        linkedList2.insert(4, 4);
        int[] nums2 = {0, 3, 1, 4};
        int numConnectedComponents2 = connectedComponents.numComponents(linkedList2.head, nums2);
        System.out.println(numConnectedComponents2);  // 2

        // Input: head = [0, 1, 2, 3, 4], nums = [0, 2, 4]
        // Output: 3
        SinglyLinkedList<Integer> linkedList3 = new SinglyLinkedList<>();
        linkedList3.insert(0, 0);
        linkedList3.insert(1, 1);
        linkedList3.insert(2, 2);
        linkedList3.insert(3, 3);
        linkedList3.insert(4, 4);
        int[] nums3 = {0, 2, 4};
        int numConnectedComponents3 = connectedComponents.numComponents(linkedList3.head, nums3);
        System.out.println(numConnectedComponents3);  // 3
    }

    /**
     * You are given the head of a linked list containing unique integer values and an
     * integer array nums that is a subset of the linked list values.
     *
     * Return the number of connected components in nums where two values are connected
     * if they appear consecutively in the linked list.
     *
     * Preconditions:
     * - The number of nodes in the linked list is n.
     * - 1 <= n <= 10 ^ 4.
     * - 0 <= Node.val < n.
     * - All the values Node.val are unique.
     * - 1 <= nums.length <= n.
     * - 0 <= nums[i] < n.
     * - All the values of nums are unique.
     */
    public int numComponents(Node<Integer> head, int[] nums) {
        Set<Integer> numsSeen = convertToSet(nums);
        int numConnectedComponents = 0;
        Node<Integer> currentNode = head;
        boolean isConnectedComponent = false;
        while (currentNode != null) {
            if (isConnectedComponent) {
                if (!numsSeen.contains(currentNode.value)) {
                    isConnectedComponent = false;
                }
            } else {  // isConnectedComponent == false
                if (numsSeen.contains(currentNode.value)) {
                    isConnectedComponent = true;
                    numConnectedComponents++;
                }
            }
            currentNode = currentNode.next;
        }
        return numConnectedComponents;
    }

    private Set<Integer> convertToSet(int[] nums) {
        Set<Integer> numsAsSet = new HashSet<>();
        for (int num : nums) {
            numsAsSet.add(num);
        }
        return numsAsSet;
    }
}

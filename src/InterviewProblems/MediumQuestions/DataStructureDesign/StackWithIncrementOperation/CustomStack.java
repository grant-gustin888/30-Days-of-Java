package InterviewProblems.MediumQuestions.DataStructureDesign.StackWithIncrementOperation;

import java.util.ArrayList;
import java.util.List;

public class CustomStack {

    // Let n = the number of elements in the stack.
    // Let k = the number of elements to increment in the stack.
    //
    // CustomStack(int k):
    // - Time: O(1)
    // --> We allocate space for the custom stack.
    // - Space: O(n)
    // --> We allocate space for k elements in the custom stack.
    //
    // push(int x):
    // - Time: O(1)
    // --> We either push x to the stack if there's space, or don't because it's full.
    // - Space: O(1)
    // --> Same as above.
    //
    // pop():
    // - Time: O(1)
    // --> We either pop the top element from the stack if there's nonempty, or don't because it's empty.
    // - Space: O(1)
    // --> We store the top value of the stack in a temporary variable to return at the end.
    //
    // increment(k, val):
    // - Time: O(min(k, n))
    // --> We either increment all n elements in the stack if k < n, or the bottom k elements if k >= n.
    // - Space: O(1)
    // --> We don't create any extra space in our custom stack data structure.

    private final List<Integer> stackElements;
    private final int capacity;

    /**
     * Representation Invariants:
     * - 1 <= maxSize <= 1000.
     * - 1 <= x <= 1000.
     * - 1 <= k <= 1000.
     * - 0 <= val <= 100.
     * - At most 1000 calls will be made to each method of increment,
     * push and pop each separately.
     */

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3); // Stack is Empty []
        System.out.println(customStack.stackElements);  // []

        customStack.push(1);  // stack becomes [1]
        System.out.println(customStack.stackElements);  // [1]
        customStack.push(2);  // stack becomes [1, 2]
        System.out.println(customStack.stackElements);  // [1, 2]
        System.out.println(customStack.pop());  // 2  --> Return top of the stack 2, stack becomes [1]

        customStack.push(2);  // stack becomes [1, 2]
        System.out.println(customStack.stackElements);  // [1, 2]
        customStack.push(3);  // stack becomes [1, 2, 3]
        System.out.println(customStack.stackElements);  // [1, 2, 3]
        customStack.push(4);  // stack still [1, 2, 3], Don't add another elements as size is 4
        System.out.println(customStack.stackElements);  // [1, 2, 3]

        customStack.increment(5, 100);  // stack becomes [101, 102, 103]
        System.out.println(customStack.stackElements);  // [101, 102, 103]
        customStack.increment(2, 100);  // stack becomes [201, 202, 103]
        System.out.println(customStack.stackElements);  // [201, 202, 103]
        System.out.println(customStack.pop());  // 103  --> Return top of the stack 103, stack becomes [201, 202]
        System.out.println(customStack.pop());  // 202  --> Return top of the stack 202, stack becomes [201]
        System.out.println(customStack.pop());  // 201  --> Return top of the stack 201, stack becomes []
        System.out.println(customStack.pop());  // -1  --> Stack is empty return -1.
    }

    /**
     * Initializes the object with maxSize which is the maximum number of elements in the
     * stack or do nothing if the stack reached the maxSize.
     */
    public CustomStack(int maxSize) {
        stackElements = new ArrayList<>();
        capacity = maxSize;
    }

    /**
     * Adds x to the top of the stack if the stack hasn't reached the maxSize.
     */
    public void push(int x) {
        if (stackElements.size() == capacity) {
            return;
        }

        stackElements.add(x);
    }

    /**
     * Pops and returns the top of stack or -1 if the stack is empty.
     */
    public int pop() {
        if (stackElements.isEmpty()) {
            return -1;
        }

        int topValue = stackElements.get(stackElements.size() - 1);
        stackElements.remove(stackElements.size() - 1);
        return topValue;
    }

    /**
     * Increments the bottom k elements of the stack by val. If there are less than k elements in
     * the stack, just increment all the elements in the stack.
     */
    public void increment(int k, int val) {
        int numElementsToIncrements = Math.min(stackElements.size(), k);
        for (int i = 0; i < numElementsToIncrements; i++) {
            stackElements.set(i, stackElements.get(i) + val);
        }
    }
}

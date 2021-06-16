package InterviewProblems.StackProblems.QueueWithTwoStacks;

import java.util.Stack;

class MyQueue<T> {

    private Stack<T> stackNewestOnTop;
    private Stack<T> stackOldestOnTop;

    MyQueue() {
        stackNewestOnTop = new Stack<>();
        stackOldestOnTop = new Stack<>();
    }

    // Add an item
    void enqueue(T newValue) {
        stackNewestOnTop.push(newValue);
    }

    // Get oldest item
    T peek() {
        shiftStacks();
        return stackOldestOnTop.peek();
        // - we don't have to move elements back from stackOldest to stackNewest
        // - we only need to move elements from stackNewest to stackOldest when stackOldest runs out of elements.
    }

    // Get oldest item and remove it
    T dequeue() {
        shiftStacks();
        return stackOldestOnTop.pop();
        // - we don't have to move elements back from stackOldest to stackNewest
        // - we only need to move elements from stackNewest to stackOldest when stackOldest runs out of elements.
    }

    // move elements from stackNewest to stackOldest
    private void shiftStacks() {
        if (!stackOldestOnTop.isEmpty()) {
            return;
        }

        while (!stackNewestOnTop.isEmpty()) {
            stackOldestOnTop.push(stackNewestOnTop.pop());
        }
    }
}
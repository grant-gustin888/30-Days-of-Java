package InterviewProblems.MediumQuestions.LinkedLists.LRUCache.MySolution;

public class Node {

    final int key;
    final int value;
    Node previous;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.previous = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return key + ", " + value;
    }
}

package InterviewProblems.MediumQuestions.LinkedLists.LRUCache.MySolution;

class DoublyCircularLinkedList {

    public Node head;
    public Node tail;
    public int size;

    public DoublyCircularLinkedList() {
        // head = least recently used key
        // tail = most recently used key
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        this.tail.previous = head;
        this.size = 0;
    }

    public void insert(Node newNode) {
        Node previousNode = tail.previous;
        Node nextNode = tail;

        previousNode.next = newNode;
        nextNode.previous = newNode;

        newNode.next = nextNode;
        newNode.previous = previousNode;
    }

    public void remove(Node oldNode) {
        Node previousNode = oldNode.previous;
        Node nextNode = oldNode.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;
    }

    /**
     * Return the string representation of this doubly linked list.
     */
    public String toString() {
        StringBuilder doublyLinkedListString = new StringBuilder();
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.previous != null) {
                doublyLinkedListString.append("PREVIOUS ");
            }

            doublyLinkedListString.append(currentNode.key);

            if (currentNode.next != null) {
                doublyLinkedListString.append(" NEXT ");
            }

            currentNode = currentNode.next;
        }

        return doublyLinkedListString.toString();
    }
}

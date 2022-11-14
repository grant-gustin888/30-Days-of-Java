package InterviewProblems.MediumQuestions.LinkedLists.CopyListWithRandomPointer;

public class Node {

    int value;
    Node next;
    Node random;

    public Node(int newValue, Node newNextNode, Node newRandomNode) {
        this.value = newValue;
        this.next = newNextNode;
        this.random = newRandomNode;
    }

    public Node(int newValue) {
        this.value = newValue;
        this.next = null;
        this.random = null;
    }

    public String toString() {
        StringBuilder nodeString = new StringBuilder();
        Node currentNode = this;
        while (currentNode != null) {
            nodeString.append(currentNode.value);
            if (currentNode.next != null) {
                nodeString.append(" NEXT ");
            }
            currentNode = currentNode.next;
        }
        nodeString.append("\n");

        currentNode = this;
        while (currentNode != null) {
            if (currentNode.random != null) {
                nodeString.append("RANDOM ").append(currentNode.random.value).append(" ");
            } else {
                nodeString.append("RANDOM NULL ");
            }
            currentNode = currentNode.next;
        }
        nodeString.append("\n");
        return nodeString.toString();
    }
}

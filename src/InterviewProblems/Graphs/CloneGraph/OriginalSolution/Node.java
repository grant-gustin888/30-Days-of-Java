package InterviewProblems.Graphs.CloneGraph.OriginalSolution;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int newValue) {
        val = newValue;
        neighbors = new ArrayList<>();
    }

    public Node(int newValue, ArrayList<Node> newNeighbours) {
        val = newValue;
        neighbors = newNeighbours;
    }

    public void addNeighbour(Node neighbour) {
        neighbors.add(neighbour);
    }
}

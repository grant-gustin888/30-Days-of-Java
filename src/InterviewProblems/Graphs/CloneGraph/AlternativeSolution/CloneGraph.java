package InterviewProblems.Graphs.CloneGraph.AlternativeSolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {

    // inspiration from neetcode solutions: graphs > "Clone Graph"
    // https://neetcode.io/practice

    // Let |V| = the number of vertices in the graph.
    // Let |E| = the number of edges in the graph.
    //
    // Time: O(|V| + |E|)
    // --> O(|V|) for the first pass to create the new nodes
    // --> O(|E|) for the second pass to set the neighbors of the new nodes
    // Space: O(|V| + |E|)
    // --> O(|V| + |E|) for the map of the old nodes and the new nodes,
    // plus their vertices.

    public static void main(String[] args) {
        CloneGraph cloneGraph = new CloneGraph();

        // Input: adjList = [[2, 4], [1, 3], [2, 4], [1, 3]]
        // Output: [[2, 4], [1, 3], [2, 4], [1, 3]]
        // Explanation: There are 4 nodes in the graph.
        // 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
        // 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
        // 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
        // 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.addNeighbour(node2);
        node1.addNeighbour(node4);
        node2.addNeighbour(node1);
        node2.addNeighbour(node3);
        node3.addNeighbour(node2);
        node3.addNeighbour(node4);
        node4.addNeighbour(node1);
        node4.addNeighbour(node3);
        Node clonedGraph1 = cloneGraph.cloneGraph(node1);
        cloneGraph.map = new HashMap<>();
        cloneGraph.bfs(clonedGraph1);

        // Input: adjList = [[]]
        // Output: [[]]
        // Explanation: Note that the input contains one empty list.
        // The graph consists of only one node with val = 1 and it does not have
        // any neighbors.
        Node node5 = new Node(1);
        Node clonedGraph2 = cloneGraph.cloneGraph(node5);
        cloneGraph.map = new HashMap<>();
        cloneGraph.bfs(clonedGraph2);

        // Input: adjList = []
        // Output: []
        // Explanation: This an empty graph, it does not have any nodes.
        Node clonedGraph3 = cloneGraph.cloneGraph(null);
        cloneGraph.map = new HashMap<>();
        cloneGraph.bfs(clonedGraph3);
    }

    public HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }

        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(node.val, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }

    public void bfs(Node node) {
        if (node == null) {
            System.out.println("EMPTY GRAPH");
            return;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(node);
        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (visited.contains(currentNode)) {
                continue;
            }

            visited.add(currentNode);
            System.out.println(currentNode.val);
            for (Node neighbour : currentNode.neighbors) {
                frontier.add(neighbour);
            }
        }

        System.out.println("\n");
    }
}

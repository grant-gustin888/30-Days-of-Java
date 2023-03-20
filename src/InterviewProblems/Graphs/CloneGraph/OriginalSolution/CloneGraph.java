package InterviewProblems.Graphs.CloneGraph.OriginalSolution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {

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
        cloneGraph.bfs(clonedGraph1);

        // Input: adjList = [[]]
        // Output: [[]]
        // Explanation: Note that the input contains one empty list.
        // The graph consists of only one node with val = 1 and it does not have
        // any neighbors.
        Node node5 = new Node(1);
        Node clonedGraph2 = cloneGraph.cloneGraph(node5);
        cloneGraph.bfs(clonedGraph2);

        // Input: adjList = []
        // Output: []
        // Explanation: This an empty graph, it does not have any nodes.
        Node clonedGraph3 = cloneGraph.cloneGraph(null);
        cloneGraph.bfs(clonedGraph3);
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // map old nodes to new graph nodes
        Map<Node, Node> oldNodesToNewNodes = new HashMap<>();

        // 1st pass: create a deep copy of all nodes only
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(node);
        while (!frontier.isEmpty()) {
            Node oldNode = frontier.poll();
            if (oldNodesToNewNodes.containsKey(oldNode)) {
                continue;
            }

            Node newNode = new Node(oldNode.val);
            oldNodesToNewNodes.put(oldNode, newNode);

            for (Node neighbour : oldNode.neighbors) {
                frontier.add(neighbour);
            }
        }

        // 2nd pass: establish pointers in new nodes using old nodes
        // and the dictionary.
        for (Node oldNode : oldNodesToNewNodes.keySet()) {
            Node newNode = oldNodesToNewNodes.get(oldNode);
            for (Node neighbour : oldNode.neighbors) {
                Node neighbourInNewGraph = oldNodesToNewNodes.get(neighbour);
                newNode.neighbors.add(neighbourInNewGraph);
            }
        }

        return oldNodesToNewNodes.get(node);
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

package DataStructures.CustomDataStructures.Graph.AdjacencyListImplementation;

import java.util.LinkedList;
import java.util.List;

public class Graph {

    private List<Vertex> vertices;
    public static int EDGE_DISTANCE = 1;

    /**
     * Create a new graph.
     */
    public Graph() {
        vertices = new LinkedList<>();
    }

    /**
     * Add the given vertex to this graph.
     *
     * Precondition: newVertex is not an existing vertex in this graph.
     */
    public void addVertex(Vertex newVertex) {
        vertices.add(newVertex);
    }

    /**
     * Remove the given vertex from this graph.
     *
     * Precondition: oldVertex is an existing vertex in this graph.
     */
    public void removeVertex(Vertex oldVertex) {
        vertices.remove(oldVertex);
    }

    /**
     * Add an edge between the given vertices.
     *
     * Precondition: vertex1 and vertex2 are existing vertices in this graph.
     */
    public void addEdge(Vertex vertex1, Vertex vertex2) {
        vertex1.addNeighbour(vertex2);
        vertex2.addNeighbour(vertex1);
    }

    /**
     * Remove an edge between the given vertices.
     *
     * Precondition: vertex1 and vertex2 are existing vertices in this graph.
     */
    public void removeEdge(Vertex vertex1, Vertex vertex2) {
        vertex1.removeNeighbour(vertex2);
        vertex2.removeNeighbour(vertex1);
    }

    /**
     * Return a list of all vertices of this graph.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Return a list of all edges of this graph.
     */
    public List<List<Vertex>> getEdges() {
        List<List<Vertex>> edgeList = new LinkedList<>();
        for (Vertex startVertex : getVertices()) {
            for (Vertex endVertex : startVertex.getNeighbors()) {
                List<Vertex> edge = new LinkedList<>();
                edge.add(startVertex);
                edge.add(endVertex);
                edgeList.add(edge);
            }
        }
        return edgeList;
    }

    /**
     * Return a string representation of this graph.
     */
    public String toString() {
        StringBuilder graphString = new StringBuilder();
        for (Vertex vertex : vertices) {
            graphString.append("Vertex: ").append(vertex).append(" ");
            graphString.append("Neighbours: ").append(vertex.getNeighbors());
            graphString.append("\n");
        }
        return graphString.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);
        Vertex vertex6 = new Vertex(6);
        Vertex vertex7 = new Vertex(7);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addVertex(vertex6);
        graph.addVertex(vertex7);

        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex1, vertex3);
        graph.addEdge(vertex2, vertex4);
        graph.addEdge(vertex2, vertex5);
        graph.addEdge(vertex3, vertex6);
        graph.addEdge(vertex3, vertex7);

        System.out.println(graph.getEdges());  // [[1, 2], [1, 3], [2, 1], [2, 4], [2, 5], [3, 1], [3, 6], [3, 7], [4, 2], [5, 2], [6, 3], [7, 3]]
        System.out.println(graph.getVertices());  // [1, 2, 3, 4, 5, 6, 7]
        System.out.println(graph);
        // Vertex: 1 Neighbours: [2, 3]
        // Vertex: 2 Neighbours: [1, 4, 5]
        // Vertex: 3 Neighbours: [1, 6, 7]
        // Vertex: 4 Neighbours: [2]
        // Vertex: 5 Neighbours: [2]
        // Vertex: 6 Neighbours: [3]
        // Vertex: 7 Neighbours: [3]

        Graph graph2 = new Graph();
        Vertex vertex8 = new Vertex(8);
        Vertex vertex9 = new Vertex(9);

        graph2.addVertex(vertex8);
        graph2.addVertex(vertex9);

        graph2.addEdge(vertex8, vertex9);
        graph2.removeEdge(vertex8, vertex9);

        System.out.println(graph2);
        // Vertex: 8 Neighbours: []
        // Vertex: 9 Neighbours: []

        graph2.removeVertex(vertex8);
        System.out.println(graph2);
        // Vertex: 9 Neighbours: []

        graph2.removeVertex(vertex9);
        System.out.println(graph2);
        // empty string
    }
}
package InterviewProblems.GraphProblems.BreadthFirstSearch;

import DataStructures.CustomDataStructures.Graph.AdjacencyListImplementation.Graph;
import DataStructures.CustomDataStructures.Graph.AdjacencyListImplementation.Vertex;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BreadthFirstSearchTest {

    private BreadthFirstSearch bfs;

    private Graph tree = new Graph();

    private Vertex vertex1 = new Vertex(1);
    private Vertex vertex2 = new Vertex(2);
    private Vertex vertex3 = new Vertex(3);
    private Vertex vertex4 = new Vertex(4);
    private Vertex vertex5 = new Vertex(5);
    private Vertex vertex6 = new Vertex(6);
    private Vertex vertex7 = new Vertex(7);

    @BeforeEach
    void setUp() {
        bfs = new BreadthFirstSearch();
        setUpTree();
    }

    @AfterEach
    void tearDown() {

    }

    private void setUpTree() {
        tree.addVertex(vertex1);
        tree.addVertex(vertex2);
        tree.addVertex(vertex3);
        tree.addVertex(vertex4);
        tree.addVertex(vertex5);
        tree.addVertex(vertex6);
        tree.addVertex(vertex7);

        tree.addEdge(vertex1, vertex2);
        tree.addEdge(vertex1, vertex3);
        tree.addEdge(vertex2, vertex4);
        tree.addEdge(vertex2, vertex5);
        tree.addEdge(vertex3, vertex6);
        tree.addEdge(vertex3, vertex7);
    }

    @Test
    void testOnTree() {
        List<Vertex> expectedValues = Arrays.asList(vertex1, vertex2, vertex3, vertex4, vertex5, vertex6, vertex7);
        Assert.assertEquals(expectedValues, bfs.breadthFirstSearch(tree, vertex1));
    }
}
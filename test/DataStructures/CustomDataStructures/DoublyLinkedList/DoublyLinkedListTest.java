package DataStructures.CustomDataStructures.DoublyLinkedList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> emptyDoublyLinkedList;
    private DoublyLinkedList<Integer> simpleDoublyLinkedList;

    @BeforeEach
    void setUp() {
        simpleDoublyLinkedList = new DoublyLinkedList<>();
        simpleDoublyLinkedList.insert(1, 0);
        simpleDoublyLinkedList.insert(2, 1);
        simpleDoublyLinkedList.insert(3, 2);
        simpleDoublyLinkedList.insert(4, 3);
        simpleDoublyLinkedList.insert(5, 4);
        Assert.assertEquals("1 Next Previous 2 Next Previous 3 Next Previous 4 Next Previous 5",
                            simpleDoublyLinkedList.toString());

        emptyDoublyLinkedList = new DoublyLinkedList<>();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testInsertAtBeginningOfDoublyLinkedList() {
        simpleDoublyLinkedList.insert(6, 0);
        Assert.assertEquals("6 Next Previous 1 Next Previous 2 Next Previous 3 Next Previous 4 Next Previous 5",
                            simpleDoublyLinkedList.toString());
    }

    @Test
    void testInsertInMiddleOfDoublyLinkedList() {
        simpleDoublyLinkedList.insert(7,3);
        Assert.assertEquals("1 Next Previous 2 Next Previous 3 Next Previous 7 Next Previous 4 Next Previous 5",
                            simpleDoublyLinkedList.toString());
    }

    @Test
    void testInsertAtEndOfDoublyLinkedList() {
        simpleDoublyLinkedList.insert(8, 5);
        Assert.assertEquals("1 Next Previous 2 Next Previous 3 Next Previous 4 Next Previous 5 Next Previous 8",
                            simpleDoublyLinkedList.toString());
    }

    @Test
    void testDeleteFromBeginningOfDoublyLinkedList() {
        simpleDoublyLinkedList.delete(1);
        Assert.assertEquals("2 Next Previous 3 Next Previous 4 Next Previous 5",
                            simpleDoublyLinkedList.toString());
    }

    @Test
    void testDeleteFromMiddleOfDoublyLinkedList() {
        simpleDoublyLinkedList.delete(3);
        Assert.assertEquals("1 Next Previous 2 Next Previous 4 Next Previous 5",
                            simpleDoublyLinkedList.toString());
    }

    @Test
    void testDeleteFromEndOfDoublyLinkedList() {
        simpleDoublyLinkedList.delete(5);
        Assert.assertEquals("1 Next Previous 2 Next Previous 3 Next Previous 4",
                            simpleDoublyLinkedList.toString());
    }

    @Test
    void testDeleteEverythingFromDoublyLinkedList() {
        simpleDoublyLinkedList.delete(1);
        simpleDoublyLinkedList.delete(2);
        simpleDoublyLinkedList.delete(3);
        simpleDoublyLinkedList.delete(4);
        simpleDoublyLinkedList.delete(5);
        Assert.assertEquals("", simpleDoublyLinkedList.toString());
    }

    @Test
    void testDeleteFromEmptyDoublyLinkedList() {
        Assertions.assertThrows(IllegalStateException.class, () -> emptyDoublyLinkedList.delete(0));
    }

    @Test
    void testPopFromBeginningOfDoublyLinkedList() {
        int poppedValue = simpleDoublyLinkedList.pop(0);
        Assert.assertEquals(1, poppedValue);
    }

    @Test
    void testPopFromMiddleOfDoublyLinkedList() {
        int poppedValue = simpleDoublyLinkedList.pop(2);
        Assert.assertEquals(3, poppedValue);
    }

    @Test
    void testPopFromEndOfDoublyLinkedList() {
        int poppedValue = simpleDoublyLinkedList.pop(4);
        Assert.assertEquals(5, poppedValue);
    }

    @Test
    void testPopEverythingFromDoublyLinkedList() {
        simpleDoublyLinkedList.pop(0);
        simpleDoublyLinkedList.pop(0);
        simpleDoublyLinkedList.pop(0);
        simpleDoublyLinkedList.pop(0);
        simpleDoublyLinkedList.pop(0);
        Assert.assertEquals("", simpleDoublyLinkedList.toString());
    }

    @Test
    void testPopFromEmptyDoublyLinkedList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> emptyDoublyLinkedList.pop(0));
    }

    @Test
    void testIsEmptyOnEmptyDoublyLinkedList() {
        Assert.assertFalse(simpleDoublyLinkedList.isEmpty());
    }

    @Test
    void testIsEmptyOnNonEmptyDoublyLinkedList() {
        Assert.assertTrue(emptyDoublyLinkedList.isEmpty());
    }

    @Test
    void testSizeOnEmptyDoublyLinkedList() {
        Assert.assertEquals(0, emptyDoublyLinkedList.getSize());
    }

    @Test
    void testSizeOnNonEmptyDoublyLinkedList() {
        Assert.assertEquals(5, simpleDoublyLinkedList.getSize());
    }
}

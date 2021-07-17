package Algorithms.SortingAlgorithms.MergeSort;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OutOfPlaceMergeSortTest {

    private OutOfPlaceMergeSort mergeSort;

    private int[] emptyArray = new int[0];
    private int[] oneElementArray = new int[] {1};
    private int[] sortedArray = new int[] {1, 2, 3, 4, 5};
    private int[] almostSortedArray = new int[] {1, 2, 3, 5, 4};
    private int[] unsortedArray = new int[] {3, 2, 5, 4, 1};
    private int[] reversedArray = new int[] {5, 4, 3, 2, 1};
    private int[] duplicatesArray = new int[] {2, 2, 1, 1, 3, 3};

    @BeforeEach
    void setUp() {
        mergeSort = new OutOfPlaceMergeSort();
    }

    @AfterEach
    void tearDown() {

    }

    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    void testIsSortedOnEmptyArray() {
        mergeSort.outOfPlaceMergeSort(emptyArray);
        assertTrue(isSorted(emptyArray));
    }

    @Test
    void testIsSortedOnOneElementArray() {
        mergeSort.outOfPlaceMergeSort(oneElementArray);
        assertTrue(isSorted(oneElementArray));
    }

    @Test
    void testIsSortedOnAlreadySortedArray() {
        mergeSort.outOfPlaceMergeSort(sortedArray);
        assertTrue(isSorted(sortedArray));
    }

    @Test
    void testIsSortedOnAlmostSortedArray() {
        mergeSort.outOfPlaceMergeSort(almostSortedArray);
        assertTrue(isSorted(almostSortedArray));
    }

    @Test
    void testIsSortedOnUnsortedArray() {
        mergeSort.outOfPlaceMergeSort(unsortedArray);
        assertTrue(isSorted(unsortedArray));
    }

    @Test
    void testIsSortedOnArrayWithDuplicates() {
        mergeSort.outOfPlaceMergeSort(duplicatesArray);
        assertTrue(isSorted(duplicatesArray));
    }

    @Test
    void testIsSortedOnReversedArray() {
        mergeSort.outOfPlaceMergeSort(reversedArray);
        assertTrue(isSorted(reversedArray));
    }
}
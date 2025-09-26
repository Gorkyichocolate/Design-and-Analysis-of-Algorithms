package test;

import main.algorithms.InsertionSortBinary;
import main.metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionSortBinaryTest {

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSortBinary.sort(arr, tracker);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {42};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSortBinary.sort(arr, tracker);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSortBinary.sort(arr, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testReverseArray() {
        int[] arr = {5, 4, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSortBinary.sort(arr, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {3, 1, 2, 1, 3};
        PerformanceTracker tracker = new PerformanceTracker();
        InsertionSortBinary.sort(arr, tracker);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, arr);
    }
}

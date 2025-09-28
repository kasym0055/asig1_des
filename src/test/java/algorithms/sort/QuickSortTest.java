package algorithms.sort;

import algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {3, 1, 4, 1, 5, 9};
        int[] copy = arr.clone();
        Arrays.sort(copy);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(copy, arr);
        assertTrue(m.getMaxDepth() > 0);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copy = arr.clone();

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(copy, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] copy = arr.clone();
        Arrays.sort(copy);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(copy, arr);
    }

    @Test
    void testRandomArrays() {
        Random rnd = new Random(42);
        for (int n = 1; n <= 2000; n += 157) {
            int[] arr = rnd.ints(n, -10000, 10000).toArray();
            int[] copy = arr.clone();
            Arrays.sort(copy);

            Metrics m = new Metrics();
            QuickSort.sort(arr, m);

            assertArrayEquals(copy, arr);
        }
    }

    @Test
    void testRecursionDepthBounded() {
        int n = 20_000;
        int[] arr = new Random(123).ints(n, -10000, 10000).toArray();
        Metrics m = new Metrics();

        QuickSort.sort(arr, m);

        // глубина должна быть O(log n), но с запасом
        int log2n = (int) (Math.log(n) / Math.log(2));
        assertTrue(m.getMaxDepth() <= 2 * log2n + 10,
                "Max recursion depth too large: " + m.getMaxDepth());
    }
}
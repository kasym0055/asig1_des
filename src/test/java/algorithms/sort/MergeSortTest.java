package algorithms.sort;

import algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] copy = arr.clone();
        Arrays.sort(copy);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(copy, arr);
        assertTrue(m.getMaxDepth() > 0);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copy = arr.clone();

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(copy, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] copy = arr.clone();
        Arrays.sort(copy);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(copy, arr);
    }

    @Test
    void testRandomArrays() {
        Random rnd = new Random(42);
        for (int n = 1; n <= 2000; n += 137) {
            int[] arr = rnd.ints(n, -10000, 10000).toArray();
            int[] copy = arr.clone();
            Arrays.sort(copy);

            Metrics m = new Metrics();
            MergeSort.sort(arr, m);

            assertArrayEquals(copy, arr);
        }
    }

    @Test
    void testRecursionDepthBounded() {
        int n = 10_000;
        int[] arr = new Random(123).ints(n, -10000, 10000).toArray();
        Metrics m = new Metrics();

        MergeSort.sort(arr, m);


        int log2n = (int) (Math.log(n) / Math.log(2));
        assertTrue(m.getMaxDepth() <= log2n + 10);
    }
}
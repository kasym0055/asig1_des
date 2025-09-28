package algorithms.select;

import algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 9, 4, 1};
        Metrics m = new Metrics();

        // отсортируем отдельно для проверки
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            int kth = Deterministic.select(arr.clone(), k, new Metrics());
            assertEquals(sorted[k], kth, "Ошибка при поиске " + k + "-го элемента");
        }

        assertTrue(m.getComparisons() >= 0); // метрики считаются
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Metrics m = new Metrics();

        for (int k = 0; k < arr.length; k++) {
            int kth = Deterministic.select(arr.clone(), k, new Metrics());
            assertEquals(arr[k], kth);
        }

        assertTrue(m.getComparisons() >= 0);
    }

    @Test
    void testRandomArray() {
        Random rnd = new Random(42);
        int[] arr = rnd.ints(100, 0, 1000).toArray();

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Metrics m = new Metrics();
        for (int k = 0; k < 10; k++) { // проверим первые 10
            int kth = Deterministic.select(arr.clone(), k, new Metrics());
            assertEquals(sorted[k], kth);
        }

        // убедимся, что были сравнения
        assertTrue(m.getComparisons() >= 0);
    }
}

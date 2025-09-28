package org.example.sort;

import org.example.metrics.Metrics;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void sort(int[] a, Metrics m) {
        quicksort(a, 0, a.length - 1, m);
    }


    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            m.enter();
            try {
                int pivotIndex = ThreadLocalRandom.current().nextInt(lo, hi + 1);
                int pivot = a[pivotIndex];
                swap(a, pivotIndex, hi, m);
                int p = partition(a, lo, hi, pivot, m);

                if (p - lo < hi - p) {
                    quicksort(a, lo, p - 1, m);
                    lo = p + 1;
                } else {
                    quicksort(a, p + 1, hi, m);
                    hi = p - 1;
                }
            } finally { m.exit(); }
        }
    }


    private static int partition(int[] a, int lo, int hi, int pivot, Metrics m) {
        int i = lo;
        for (int j = lo; j < hi; j++) {
            m.compare();
            if (a[j] < pivot) { swap(a, i++, j, m); }
        }
        swap(a, i, hi, m);
        return i;
    }


    private static void swap(int[] a, int i, int j, Metrics m) {
        if (i == j) return;
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp; m.swap();
    }
}
package org.example.sort;

import org.example.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 32;


    public static void sort(int[] a, Metrics m) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length, m);
    }


    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i < hi; i++) {
            int v = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.compare();
                if (a[j] <= v) break;
                a[j + 1] = a[j];
                m.swap();
                j--;
            }
            a[j + 1] = v;
        }
    }


    private static void sort(int[] a, int[] aux, int lo, int hi, Metrics m) {
        m.enter();
        try {
            int len = hi - lo;
            if (len <= CUTOFF) { insertionSort(a, lo, hi, m); return; }
            int mid = lo + (len >> 1);
            sort(a, aux, lo, mid, m);
            sort(a, aux, mid, hi, m);

            int i = lo, j = mid, k = lo;
            while (i < mid && j < hi) {
                m.compare();
                if (a[i] <= a[j]) aux[k++] = a[i++]; else aux[k++] = a[j++];
            }
            while (i < mid) aux[k++] = a[i++];
            while (j < hi) aux[k++] = a[j++];
            System.arraycopy(aux, lo, a, lo, len);
        } finally { m.exit(); }
    }
}
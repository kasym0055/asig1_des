package algorithms.select;

import algorithms.metrics.Metrics;

public class Deterministic {
    public static int select(int[] a, int k, Metrics m) {
        return select(a, 0, a.length - 1, k, m);
    }


    private static int select(int[] a, int lo, int hi, int k, Metrics m) {
        while (true) {
            if (lo == hi) return a[lo];
            int pivot = medianOfMedians(a, lo, hi, m);
            int p = partition(a, lo, hi, pivot, m);
            int leftSize = p - lo;
            if (k == leftSize) return a[p];
            else if (k < leftSize) hi = p - 1; else { k = k - leftSize - 1; lo = p + 1; }
        }
    }


    private static int partition(int[] a, int lo, int hi, int pivotValue, Metrics m) {
        int i = lo;
        for (int j = lo; j <= hi; j++) {
            m.compare();
            if (a[j] < pivotValue) { swap(a, i++, j, m); }
        }

        for (int j = i; j <= hi; j++) if (a[j] == pivotValue) { swap(a, i, j, m); break; }
        return i;
    }


    private static int medianOfMedians(int[] a, int lo, int hi, Metrics m) {
        int n = hi - lo + 1;
        if (n <= 5) {
            java.util.Arrays.sort(a, lo, hi + 1);
            return a[lo + n/2];
        }
        int numMedians = 0;
        for (int i = lo; i <= hi; i += 5) {
            int subHi = Math.min(i + 4, hi);
            java.util.Arrays.sort(a, i, subHi + 1);
            int median = a[i + (subHi - i)/2];
            swap(a, lo + numMedians, i + (subHi - i)/2, m);
            a[lo + numMedians] = median; // already in place
            numMedians++;
        }

        return select(a, lo, lo + numMedians - 1, numMedians/2, m);
    }


    private static void swap(int[] a, int i, int j, Metrics m) {
        if (i==j) return; int t=a[i]; a[i]=a[j]; a[j]=t; m.swap();
    }
}

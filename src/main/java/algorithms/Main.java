package algorithms;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import algorithms.geom.ClosestPair;
import algorithms.metrics.Metrics;
import algorithms.select.Deterministic;
import algorithms.sort.MergeSort;
import algorithms.sort.QuickSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 1000; // размер массива
        String algo = args.length > 1 ? args[1] : "quicksort";     // выбор алгоритма

        int[] arr = randomArray(n);

        Metrics m = new Metrics();
        long start = System.nanoTime();

        switch (algo.toLowerCase()) {
            case "mergesort":
                MergeSort.sort(arr, m);
                break;
            case "quicksort":
                QuickSort.sort(arr, m);
                break;
            case "select":
                int k = n / 2;
                int kth = Deterministic.select(arr.clone(), k, m);
                System.out.println("k-th element = " + kth);
                break;
            case "closest":
                ClosestPair.Point[] pts = randomPoints(n);
                double d = ClosestPair.closest(pts, m);
                System.out.println("Closest distance = " + d);
                break;
            default:
                System.err.println("Unknown algorithm: " + algo);
                return;
        }

        long end = System.nanoTime();
        long timeMs = (end - start) / 1_000_000;

        System.out.printf("Algo=%s, n=%d, time=%dms, depth=%d%n",
                algo, n, timeMs, m.getMaxDepth());

        // сохранить в CSV
        try (FileWriter fw = new FileWriter("results.csv", true)) {
            fw.write(algo + "," + n + "," + timeMs + "," + m.getMaxDepth() + "\n");
        }
    }

    private static int[] randomArray(int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(1_000_000);
        return arr;
    }

    private static ClosestPair.Point[] randomPoints(int n) {
        Random rnd = new Random();
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new ClosestPair.Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
        }
        return pts;
    }
}
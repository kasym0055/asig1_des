package algorithms;

import algorithms.metrics.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x;
        public final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double closest(Point[] points, Metrics m) {
        Point[] pts = points.clone();
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[pts.length];
        return closestRec(pts, aux, 0, pts.length, m);
    }

    private static double closestRec(Point[] pts, Point[] aux, int lo, int hi, Metrics m) {
        m.enter();
        int n = hi - lo;
        if (n <= 3) {
            double d = bruteForce(pts, lo, hi);
            Arrays.sort(pts, lo, hi, Comparator.comparingDouble(p -> p.y));
            m.exit();
            return d;
        }

        int mid = lo + n / 2;
        double midX = pts[mid].x;

        double d1 = closestRec(pts, aux, lo, mid, m);
        double d2 = closestRec(pts, aux, mid, hi, m);
        double d = Math.min(d1, d2);

        mergeByY(pts, aux, lo, mid, hi);

        int stripSize = 0;
        for (int i = lo; i < hi; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                aux[stripSize++] = pts[i];
            }
        }

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (aux[j].y - aux[i].y) < d; j++) {
                d = Math.min(d, dist(aux[i], aux[j]));
            }
        }

        m.exit();
        return d;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
            if (pts[i].y <= pts[j].y) aux[k++] = pts[i++];
            else aux[k++] = pts[j++];
        }
        while (i < mid) aux[k++] = pts[i++];
        while (j < hi) aux[k++] = pts[j++];
        System.arraycopy(aux, lo, pts, lo, hi - lo);
    }

    private static double bruteForce(Point[] pts, int lo, int hi) {
        double d = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
            for (int j = i + 1; j < hi; j++) {
                d = Math.min(d, dist(pts[i], pts[j]));
            }
        }
        return d;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

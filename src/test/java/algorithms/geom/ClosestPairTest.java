package algorithms.geom;

import algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testSmallSet() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 7),
                new ClosestPair.Point(1, 1)
        };
        Metrics m = new Metrics();
        double dist = ClosestPair.closest(points, m);

        // минимальная пара: (0,0) и (1,1) → √2
        assertEquals(Math.sqrt(2), dist, 1e-9);
        assertTrue(m.getMaxDepth() > 0);
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(5, 7)
        };
        Metrics m = new Metrics();
        double dist = ClosestPair.closest(points, m);

        assertEquals(5.0, dist, 1e-9); // √((3)^2 + (4)^2) = 5
    }

    @Test
    void testDuplicatePoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(5, 5)
        };
        Metrics m = new Metrics();
        double dist = ClosestPair.closest(points, m);

        assertEquals(0.0, dist, 1e-9); // одинаковые точки → расстояние 0
    }

    @Test
    void testLinePoints() {
        ClosestPair.Point[] points = new ClosestPair.Point[5];
        for (int i = 0; i < 5; i++) {
            points[i] = new ClosestPair.Point(i * 2.0, 0);
        }
        Metrics m = new Metrics();
        double dist = ClosestPair.closest(points, m);

        assertEquals(2.0, dist, 1e-9); // соседние точки на прямой
    }
}

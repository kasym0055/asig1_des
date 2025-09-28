package algorithms.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicLong;


public class Metrics {
    private final ThreadLocal<Integer> depth = ThreadLocal.withInitial(() -> 0);
    private int maxDepth = 0;
    private final AtomicLong comparisons = new AtomicLong(0);
    private final AtomicLong swaps = new AtomicLong(0);


    public void enter() {
        int d = depth.get() + 1;
        depth.set(d);
        synchronized (this) { if (d > maxDepth) maxDepth = d; }
    }
    public void exit() { depth.set(depth.get() - 1); }


    public void compare() { comparisons.incrementAndGet(); }
    public void swap() { swaps.incrementAndGet(); }


    public int getMaxDepth() { return maxDepth; }
    public long getComparisons() { return comparisons.get(); }
    public long getSwaps() { return swaps.get(); }


    public void writeCsvHeader(PrintWriter pw) {
        pw.println("algorithm,n,elapsedMillis,maxDepth,comparisons,swaps,seed");
    }
    public void writeCsvRow(PrintWriter pw, String algo, int n, long elapsedMillis, long seed) {
        pw.printf("%s,%d,%d,%d,%d,%d,%d\n", algo, n, elapsedMillis, getMaxDepth(), getComparisons(), getSwaps(), seed);
    }
}

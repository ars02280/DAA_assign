package com.daa.metrics;

public class Metrics {
    private long comparisons = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;

    public void incrementComparisons() {
        this.comparisons++;
    }

    public void enterRecursion() {
        this.currentDepth++;
        if (this.currentDepth > this.maxDepth) {
            this.maxDepth = this.currentDepth;
        }
    }

    public void exitRecursion() {
        this.currentDepth--;
    }

    public void reset() {
        this.comparisons = 0;
        this.currentDepth = 0;
        this.maxDepth = 0;
    }

    public long getComparisons() { return comparisons; }
    public int getMaxDepth() { return maxDepth; }
}
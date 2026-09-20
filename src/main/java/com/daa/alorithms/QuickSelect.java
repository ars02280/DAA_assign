package com.daa.algorithms;

import com.daa.metrics.Metrics;
import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid array or k index out of bounds.");
        }
        return select(a, 0, a.length - 1, k, metrics);
    }

    private static int select(int[] a, int low, int high, int k, Metrics metrics) {
        metrics.enterRecursion();
        try {
            if (low == high) return a[low];

            int pivotIdx = low + random.nextInt(high - low + 1);
            swap(a, low, pivotIdx);

            int pivot = a[low];
            int lt = low, i = low + 1, gt = high;

            while (i <= gt) {
                metrics.incrementComparisons();
                if (a[i] < pivot) {
                    swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    metrics.incrementComparisons();
                    swap(a, i, gt--);
                } else {
                    metrics.incrementComparisons();
                    i++;
                }
            }

            if (k < lt) {
                return select(a, low, lt - 1, k, metrics);
            } else if (k > gt) {
                return select(a, gt + 1, high, k, metrics);
            } else {
                return a[k]; // Выпал в равные элементы
            }
        } finally {
            metrics.exitRecursion();
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
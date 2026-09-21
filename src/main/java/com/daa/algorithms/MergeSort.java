package com.daa.algorithms;

import com.daa.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        int[] temp = new int[a.length]; // Единый буфер
        sort(a, temp, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int[] temp, int left, int right, Metrics metrics) {
        metrics.enterRecursion();
        try {
            if (right - left + 1 <= CUTOFF) {
                insertionSort(a, left, right, metrics);
                return;
            }

            int mid = left + (right - left) / 2;
            sort(a, temp, left, mid, metrics);
            sort(a, temp, mid + 1, right, metrics);
            merge(a, temp, left, mid, right, metrics);
        } finally {
            metrics.exitRecursion();
        }
    }

    private static void merge(int[] a, int[] temp, int left, int mid, int right, Metrics metrics) {
        System.arraycopy(a, left, temp, left, right - left + 1);

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            metrics.incrementComparisons();
            if (i > mid) {
                a[k] = temp[j++];
            } else if (j > right) {
                a[k] = temp[i++];
            } else {
                metrics.incrementComparisons();
                if (temp[j] < temp[i]) {
                    a[k] = temp[j++];
                } else {
                    a[k] = temp[i++];
                }
            }
        }
    }

    private static void insertionSort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incrementComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}
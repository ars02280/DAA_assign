package com.daa;

import com.daa.algorithms.QuickSort;
import com.daa.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testRandomArrays() {
        Metrics metrics = new Metrics();
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int[] actual = rand.ints(1000, 0, 10000).toArray();
            int[] expected = actual.clone();

            Arrays.sort(expected);
            QuickSort.sort(actual, metrics);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    void testMaxDepthOnSorted() {
        Metrics metrics = new Metrics();
        int n = 100_000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;

        QuickSort.sort(arr, metrics);

        double maxAllowedDepth = 2 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth);
    }
}
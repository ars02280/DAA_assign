package com.daa;

import com.daa.algorithms.MergeSort;
import com.daa.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testRandomArrays() {
        Metrics metrics = new Metrics();
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int[] actual = rand.ints(1000, 0, 10000).toArray();
            int[] expected = actual.clone();

            Arrays.sort(expected);
            MergeSort.sort(actual, metrics);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    void testEdgeCases() {
        Metrics metrics = new Metrics();

        int[] empty = {};
        MergeSort.sort(empty, metrics);
        assertArrayEquals(new int[]{}, empty);

        int[] single = {42};
        MergeSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        int[] equal = {7, 7, 7, 7};
        MergeSort.sort(equal, metrics);
        assertArrayEquals(new int[]{7, 7, 7, 7}, equal);
    }
}
package com.daa;

import com.daa.algorithms.MergeSort;
import com.daa.algorithms.QuickSort;
import com.daa.algorithms.QuickSelect;
import com.daa.metrics.Metrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortsAndSelectTest {

    private Metrics metrics;
    private Random random;

    @BeforeEach
    void setUp() {
        metrics = new Metrics();
        random = new Random();
    }

    @Test
    @DisplayName("MergeSort & QuickSort: Compare with Arrays.sort on 100 random arrays")
    void testCorrectnessOnRandomArrays() {
        for (int i = 0; i < 100; i++) {
            int size = 100 + random.nextInt(900);
            int[] original = random.ints(size, -10000, 10000).toArray();

            int[] expected = original.clone();
            Arrays.sort(expected);

            int[] forMerge = original.clone();
            metrics.reset();
            MergeSort.sort(forMerge, metrics);
            assertArrayEquals(expected, forMerge);

            int[] forQuick = original.clone();
            metrics.reset();
            QuickSort.sort(forQuick, metrics);
            assertArrayEquals(expected, forQuick);
        }
    }

    @Test
    @DisplayName("Edge Case: Empty array")
    void testEmptyArray() {
        int[] emptyMerge = {};
        MergeSort.sort(emptyMerge, metrics);
        assertArrayEquals(new int[]{}, emptyMerge);

        int[] emptyQuick = {};
        QuickSort.sort(emptyQuick, metrics);
        assertArrayEquals(new int[]{}, emptyQuick);
    }

    @Test
    @DisplayName("Edge Case: One element array")
    void testSingleElementArray() {
        int[] singleMerge = {42};
        MergeSort.sort(singleMerge, metrics);
        assertArrayEquals(new int[]{42}, singleMerge);

        int[] singleQuick = {42};
        QuickSort.sort(singleQuick, metrics);
        assertArrayEquals(new int[]{42}, singleQuick);
    }

    @Test
    @DisplayName("Edge Case: All elements equal (Duplicates)")
    void testAllElementsEqual() {
        int[] duplicates = new int[1000];
        Arrays.fill(duplicates, 7);

        int[] expected = duplicates.clone();

        int[] forMerge = duplicates.clone();
        MergeSort.sort(forMerge, metrics);
        assertArrayEquals(expected, forMerge);

        int[] forQuick = duplicates.clone();
        metrics.reset();
        QuickSort.sort(forQuick, metrics);
        assertArrayEquals(expected, forQuick);
        assertTrue(metrics.getMaxDepth() <= 5);
    }

    @Test
    @DisplayName("Edge Case: Already sorted array")
    void testAlreadySortedArray() {
        int[] sorted = new int[1000];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;

        int[] expected = sorted.clone();

        int[] forMerge = sorted.clone();
        MergeSort.sort(forMerge, metrics);
        assertArrayEquals(expected, forMerge);

        int[] forQuick = sorted.clone();
        QuickSort.sort(forQuick, metrics);
        assertArrayEquals(expected, forQuick);
    }

    @Test
    @DisplayName("Depth Check: QuickSort maxDepth <= 2 * log2(n) on 100,000 sorted array")
    void testQuickSortRecursionDepthOnSortedArray() {
        int n = 100_000;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) sorted[i] = i;

        metrics.reset();
        QuickSort.sort(sorted, metrics);

        double maxAllowedDepth = 2.0 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth);
    }

    @Test
    @DisplayName("QuickSelect: Compare result with sorted[k] on 100 random arrays")
    void testQuickSelectOnRandomArrays() {
        for (int i = 0; i < 100; i++) {
            int size = 100 + random.nextInt(900);
            int[] array = random.ints(size, -5000, 5000).toArray();
            int k = random.nextInt(size);

            int[] sortedCopy = array.clone();
            Arrays.sort(sortedCopy);

            metrics.reset();
            int actualKthElement = QuickSelect.select(array.clone(), k, metrics);
            assertEquals(sortedCopy[k], actualKthElement);
        }
    }
}
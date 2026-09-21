package com.daa;

import com.daa.algorithms.QuickSelect;
import com.daa.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Test
    void testRandomArrays() {
        Metrics metrics = new Metrics();
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int[] arr = rand.ints(100, 0, 1000).toArray();
            int k = rand.nextInt(arr.length);

            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int actual = QuickSelect.select(arr, k, metrics);
            assertEquals(sorted[k], actual);
        }
    }

    @Test
    void testInvalidInput() {
        Metrics metrics = new Metrics();
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{}, 0, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2}, -1, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2}, 2, metrics));
    }
}
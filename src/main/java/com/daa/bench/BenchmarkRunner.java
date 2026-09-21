package com.daa.bench;

import com.daa.algorithms.MergeSort;
import com.daa.algorithms.QuickSort;
import com.daa.algorithms.QuickSelect;
import com.daa.metrics.Metrics;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {
    private static final int[] SIZES = {1000, 10000, 100000, 1000000};
    private static final String[] TYPES = {"random", "sorted", "duplicates"};
    private static final int RUNS = 5;

    public static void runAllBenchmarks() {
        Metrics metrics = new Metrics();

        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");

            for (String type : TYPES) {
                for (String alg : new String[]{"MergeSort", "QuickSort", "QuickSelect"}) {
                    for (int n : SIZES) {
                        executeBenchmarkRun(alg, n, type, metrics, writer);
                    }
                }
            }
            System.out.println("Benchmark finished. Results saved to results.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeBenchmarkRun(String alg, int n, String type, Metrics metrics, PrintWriter writer) {
        long[] times = new long[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        for (int r = 0; r < RUNS; r++) {
            int[] arr = generateData(n, type);
            metrics.reset();

            long start = System.nanoTime();
            if (alg.equals("MergeSort")) {
                MergeSort.sort(arr, metrics);
            } else if (alg.equals("QuickSort")) {
                QuickSort.sort(arr, metrics);
            } else if (alg.equals("QuickSelect")) {
                QuickSelect.select(arr, n / 2, metrics);
            }
            long elapsed = (System.nanoTime() - start) / 1_000_000;

            times[r] = elapsed;
            comparisons[r] = metrics.getComparisons();
            depths[r] = metrics.getMaxDepth();
        }

        Arrays.sort(times);
        Arrays.sort(comparisons);
        Arrays.sort(depths);

        int median = RUNS / 2;
        writer.printf("%s,%s,%d,%d,%d,%d%n", alg, type, n, times[median], comparisons[median], depths[median]);
    }

    private static int[] generateData(int n, String type) {
        Random rand = new Random();
        int[] arr = new int[n];

        if (type.equals("random")) {
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt();
        } else if (type.equals("sorted")) {
            for (int i = 0; i < n; i++) arr[i] = i;
        } else if (type.equals("duplicates")) {
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(10);
        }
        return arr;
    }
}
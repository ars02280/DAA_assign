package com.daa.bench;

public class ConsolePlotter {

    public static void printRatioTable(String algorithm, String inputType, int[] ns, long[] comparisons) {
        System.out.println("\n=== RATIO TABLE: " + algorithm + " (" + inputType + ") ===");
        System.out.printf("%-10s | %-15s | %-20s | %-10s%n", "N", "Comparisons", "Theoretical Growth", "Ratio");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < ns.length; i++) {
            int n = ns[i];
            long comp = comparisons[i];
            double expected;

            if (algorithm.equalsIgnoreCase("QuickSelect")) {
                expected = n;
            } else {
                expected = n * (Math.log(n) / Math.log(2));
            }

            double ratio = comp / expected;
            System.out.printf("%-10d | %-15d | %-20.2f | %-10.4f%n", n, comp, expected, ratio);
        }
    }
}
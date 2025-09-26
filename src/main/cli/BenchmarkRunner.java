package main.cli;


import main.algorithms.InsertionSortBinary;
import main.algorithms.SelectionSort;
import main.metrics.PerformanceTracker;

import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000, 10000};

        for (int n : sizes) {
            int[] random = generateRandomArray(n);
            int[] sorted = generateSortedArray(n);
            int[] reverse = generateReverseArray(n);

            System.out.println("=== n = " + n + " ===");

            runBenchmark("InsertionSortBinary - random", random, true);
            runBenchmark("InsertionSortBinary - sorted", sorted, true);
            runBenchmark("InsertionSortBinary - reverse", reverse, true);

            runBenchmark("SelectionSort - random", random, false);
            runBenchmark("SelectionSort - sorted", sorted, false);
            runBenchmark("SelectionSort - reverse", reverse, false);
        }
    }

    private static void runBenchmark(String label, int[] arr, boolean insertion) {
        int[] copy = arr.clone(); // чтобы не сортировать один и тот же массив
        PerformanceTracker tracker = new PerformanceTracker();

        long start = System.nanoTime();
        if (insertion) {
            InsertionSortBinary.sort(copy, tracker);
        } else {
            SelectionSort.sort(copy, tracker);
        }
        long end = System.nanoTime();

        System.out.printf("%-35s time = %.3f ms, comparisons = %d, swaps = %d%n",
                label, (end - start) / 1_000_000.0,
                tracker.getComparisons(), tracker.getSwaps());
    }

    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) arr[i] = r.nextInt();
        return arr;
    }

    private static int[] generateSortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }

    private static int[] generateReverseArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }
}

import main.algorithms.*;
import main.cli.BenchmarkRunner;
import main.metrics.PerformanceTracker;
import test.SelectionSortTest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000, 10000};
        String fileName = "performance.csv";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.append("n,TimeMs,Comparisons,Swaps\n");

            for (int n : sizes) {
                int[] arr = generateRandomArray(n);
                PerformanceTracker tracker = new PerformanceTracker();

                long start = System.nanoTime();
                main.algorithms.SelectionSort.sort(arr, tracker);
                long end = System.nanoTime();

                double timeMs = (end - start) / 1_000_000.0;

                // Write results to CSV
                writer.append(n + "," + timeMs + "," + tracker.getComparisons() + "," + tracker.getSwaps() + "\n");

                // Print results in console
                System.out.println("n=" + n +
                        " | Time=" + timeMs + "ms" +
                        " | Comparisons=" + tracker.getComparisons() +
                        " | Swaps=" + tracker.getSwaps());

                // Verify array is sorted
                if (!isSorted(arr)) {
                    System.out.println("Error: array is not sorted!");
                }
            }

            System.out.println("Results saved to " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" ");
        System.out.println("Absolute path: " + new java.io.File(fileName).getAbsolutePath());

    }

    private static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
}
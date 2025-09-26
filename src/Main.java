import main.algorithms.InsertionSortBinary;
import main.algorithms.SelectionSort;
import main.metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000, 10000};
        String fileName = "performance.csv";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.append("Algorithm,n,TimeMs,Comparisons,Swaps\n");

            for (int n : sizes) {
                int[] arrRandom = generateRandomArray(n);

                //SelectionSort
                runAndWrite("SelectionSort", arrRandom, writer, n);

                //InsertionSortBinary
                runAndWrite("InsertionSortBinary", arrRandom, writer, n);
            }

            System.out.println("Results saved to " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Absolute path: " + new java.io.File(fileName).getAbsolutePath());
    }

    private static void runAndWrite(String algorithmName, int[] arr, FileWriter writer, int n) throws IOException {
        int[] copy = arr.clone(); // чтобы оба алгоритма сортировали один и тот же массив
        PerformanceTracker tracker = new PerformanceTracker();

        long start = System.nanoTime();

        if (algorithmName.equals("SelectionSort")) {
            SelectionSort.sort(copy, tracker);
        } else if (algorithmName.equals("InsertionSortBinary")) {
            InsertionSortBinary.sort(copy, tracker);
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;

        // Запись в CSV
        writer.append(algorithmName + "," + n + "," + timeMs + "," +
                tracker.getComparisons() + "," + tracker.getSwaps() + "\n");

        // Вывод в консоль
        System.out.println(algorithmName + " | n=" + n +
                " | Time=" + timeMs + " ms" +
                " | Comparisons=" + tracker.getComparisons() +
                " | Swaps=" + tracker.getSwaps());

        if (!isSorted(copy)) {
            System.out.println("Error: " + algorithmName + " did not sort correctly!");
        }
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

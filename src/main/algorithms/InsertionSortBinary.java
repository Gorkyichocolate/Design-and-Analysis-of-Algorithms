package main.algorithms;

import main.metrics.PerformanceTracker;

public class InsertionSortBinary {

    public static void sort(int[] arr, PerformanceTracker tracker) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];

            int insertPos = binarySearch(arr, key, 0, i - 1, tracker);


            int j = i - 1;
            while (j >= insertPos) {
                arr[j + 1] = arr[j];
                tracker.incrementSwaps();
                j--;
            }

            arr[insertPos] = key;
        }
    }


    private static int binarySearch(int[] arr, int key, int low, int high, PerformanceTracker tracker) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            tracker.incrementComparisons();
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}



public class SelectionSort {
    public static void sort(int[] array, Metrics tracker) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                tracker.incrementComparisons();
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                tracker.incrementSwaps();
            }
        }
    } // best way O(n)   -- O(n²)


    public static void sort(int[] array) {
        sort(array, new Metrics());
    }
}

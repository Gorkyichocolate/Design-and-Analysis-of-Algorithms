package Sort;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        while (true) {
            int n = right - left + 1;
            if (n <= 5) {
                insertionSort(arr, left, right);
                return arr[k];
            }

            int numGroups = 0;
            for (int i = left; i <= right; i += 5) {
                int groupRight = Math.min(i + 4, right);
                insertionSort(arr, i, groupRight);
                int medianIndex = i + (groupRight - i) / 2;
                swap(arr, left + numGroups, medianIndex);
                numGroups++;
            }

            int medOfMedIndex = left + (numGroups - 1) / 2;
            int pivotValue = select(arr, left, left + numGroups - 1, medOfMedIndex);

            int pivotIndex = -1;
            for (int i = left; i <= right; i++) {
                if (arr[i] == pivotValue) {
                    pivotIndex = i;
                    break;
                }
            }
            if (pivotIndex == -1) pivotIndex = left + (right - left) / 2;
            swap(arr, pivotIndex, right);

            int store = left;
            for (int i = left; i < right; i++) {
                if (arr[i] < pivotValue) {
                    swap(arr, store, i);
                    store++;
                }
            }
            swap(arr, store, right);
            int p = store;

            if (k == p) return arr[p];
            int leftSize = p - left;
            int rightSize = right - p;

            if (k < p) {
                if (leftSize <= rightSize) return select(arr, left, p - 1, k);
                right = p - 1;
            } else {
                if (rightSize < leftSize) return select(arr, p + 1, right, k);
                left = p + 1;
            }
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

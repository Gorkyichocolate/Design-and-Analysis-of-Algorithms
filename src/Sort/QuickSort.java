package Sort;
import java.util.Random;

public class QuickSort {
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, new Random());
    }

    private static void quickSort(int[] arr, int left, int right, Random rand) {
        while (left < right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            swap(arr, pivotIndex, right);

            int i = left;
            for (int j = left; j < right; j++) {
                if (arr[j] <= pivot) {
                    swap(arr, i, j);
                    i++;
                }
            }
            swap(arr, i, right);
            int mid = i;

            if (mid - left < right - mid) {
                quickSort(arr, left, mid - 1, rand);
                left = mid + 1;
            } else {
                quickSort(arr, mid + 1, right, rand);
                right = mid - 1;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

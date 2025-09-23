
import static mergeSort.betterMergeSort.mergeSort;


public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 9, 5, 4, 8, 1, 6};
        mergeSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
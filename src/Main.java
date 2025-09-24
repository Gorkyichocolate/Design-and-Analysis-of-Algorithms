
import Sort.DeterministicSelect;
import static Sort.DeterministicSelect.select;
import static Sort.QuickSort.quickSort;
import static Sort.betterMergeSort.mergeSort;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {2, 9, 5, 4, 8, 1, 6};
        int[] arr2 = arr1.clone();
        int median = select(arr1, arr1.length / 2);



        mergeSort(arr1);
        quickSort(arr2);


        System.out.print("MergeSort: ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        System.out.print("QuickSort: ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }

        System.out.println("Median (DeterministicSelect): " + median);
    }

}

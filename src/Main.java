import Sort.ClosestPair;

import java.awt.*;

import static Sort.DeterministicSelect.select;
import static Sort.QuickSort.quickSort;
import static Sort.betterMergeSort.mergeSort;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {
                45, 3, 88, 12, 7, 99, 56, 4, 33, 78,
                10, 65, 23, 41, 1, 90, 34, 8, 73, 19,
                57, 29, 60, 13, 5, 97, 25, 44, 32, 2,
                17, 71, 46, 80, 6, 93, 39, 21, 70, 27,
                15, 31, 98, 20, 35, 9, 68, 18, 64, 50
        };

        int[] arr2 = {
                100, 2, 76, 45, 3, 81, 59, 21, 87, 10,
                37, 8, 93, 41, 12, 5, 73, 25, 99, 18,
                50, 30, 68, 7, 16, 85, 34, 92, 13, 48,
                63, 1, 97, 28, 40, 14, 79, 33, 26, 9,
                36, 19, 64, 53, 11, 24, 90, 35, 17, 60
        };

        int[] arr3 = {
                15, 94, 6, 37, 85, 22, 4, 66, 9, 73,
                42, 98, 18, 59, 27, 10, 78, 33, 64, 19,
                8, 45, 30, 57, 2, 92, 13, 53, 23, 99,
                36, 5, 47, 80, 1, 88, 29, 14, 95, 34,
                3, 50, 12, 41, 26, 90, 32, 7, 72, 17
        };

        int median = select(arr1, arr3.length / 2);

        mergeSort(arr1);
        quickSort(arr2);

        ClosestPair.Point[] pts = {
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50),
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10),
                new ClosestPair.Point(3, 4)
        };

        double minDist = ClosestPair.closestPair(pts);

        System.out.print("MergeSort: ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        System.out.print("QuickSort: ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

        System.out.println("Median (DeterministicSelect): " + median);
        System.out.println("Closest Pair distance: " + minDist);
    }
}

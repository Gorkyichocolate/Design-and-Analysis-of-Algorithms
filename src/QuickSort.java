// QuickSort.java
import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random rnd = new Random(123);

    public QuickSort(Metrics metrics){
        this.metrics = metrics;
    }

    public void sort(int[] a){
        if(a == null || a.length < 2) return;
        metrics.enter();
        try {
            IntUtils.shuffle(a);
            metrics.addAllocations(a.length);
            quicksort(a, 0, a.length - 1);
        } finally {
            metrics.exit();
        }
    }

    private void quicksort(int[] a, int lo, int hi){
        while(lo < hi){
            int p = partition(a, lo, hi);
            // recurse on smaller side
            if(p - lo < hi - p){
                metrics.enter();
                quicksort(a, lo, p - 1);
                metrics.exit();
                lo = p + 1;
            } else {
                metrics.enter();
                quicksort(a, p + 1, hi);
                metrics.exit();
                hi = p - 1;
            }
        }
    }

    private int partition(int[] a, int lo, int hi){
        int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
        int pivot = a[pivotIndex];
        IntUtils.swap(a, pivotIndex, hi);
        metrics.incSwaps();
        int store = lo;
        for(int i = lo; i < hi; i++){
            metrics.incComparisons();
            if(a[i] < pivot){
                IntUtils.swap(a, store, i);
                metrics.incSwaps();
                store++;
            }
        }
        IntUtils.swap(a, store, hi);
        metrics.incSwaps();
        return store;
    }
}

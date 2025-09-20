// DeterministicSelect.java
public class DeterministicSelect {
    private final Metrics metrics;

    public DeterministicSelect(Metrics metrics){
        this.metrics = metrics;
    }

    // returns k-th smallest (0-based)
    public int select(int[] a, int k){
        if(a == null || k < 0 || k >= a.length) throw new IllegalArgumentException();
        metrics.enter();
        try {
            return selectRec(a, 0, a.length - 1, k);
        } finally {
            metrics.exit();
        }
    }

    private int selectRec(int[] a, int l, int r, int k){
        if(l == r) return a[l];
        if(r - l + 1 <= 10){
            java.util.Arrays.sort(a, l, r + 1);
            return a[l + k];
        }

        int numMeds = 0;
        for(int i = l; i <= r; i += 5){
            int rr = Math.min(i + 4, r);
            insertionSort(a, i, rr);
            int median = (i + rr) / 2;
            swap(a, median, l + numMeds);
            numMeds++;
        }

        int pivot = selectRec(a, l, l + numMeds - 1, numMeds / 2);
        int pivotIndex = partitionAround(a, l, r, pivot);
        int leftSize = pivotIndex - l;
        if(k == leftSize) return a[pivotIndex];
        else if(k < leftSize) return selectRec(a, l, pivotIndex - 1, k);
        else return selectRec(a, pivotIndex + 1, r, k - leftSize - 1);
    }

    private void insertionSort(int[] a, int l, int r){
        for(int i = l + 1; i <= r; i++){
            int key = a[i];
            int j = i - 1;
            while(j >= l){
                metrics.incComparisons();
                if(a[j] <= key) break;
                a[j + 1] = a[j--];
                metrics.incSwaps();
            }
            a[j + 1] = key;
        }
    }

    private int partitionAround(int[] a, int l, int r, int pivotVal){
        int pivotPos = l;
        // move pivot value to end (find an occurrence)
        for(int i = l; i <= r; i++){
            metrics.incComparisons();
            if(a[i] == pivotVal){
                swap(a, i, r);
                metrics.incSwaps();
                break;
            }
        }
        int store = l;
        for(int i = l; i < r; i++){
            metrics.incComparisons();
            if(a[i] < pivotVal){
                swap(a, store, i);
                metrics.incSwaps();
                store++;
            }
        }
        swap(a, store, r);
        metrics.incSwaps();
        return store;
    }

    private void swap(int[] a, int i, int j){
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}

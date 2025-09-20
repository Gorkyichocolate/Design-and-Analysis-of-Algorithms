// MergeSort.java
public class MergeSort {
    private final Metrics metrics;
    private final int INSERTION_CUTOFF = 32;
//
    public MergeSort(Metrics metrics){
        this.metrics = metrics;
    }

    public void sort(int[] a){
        if(a == null || a.length < 2) return;
        metrics.enter();
        try {
            int[] buf = new int[a.length];
            metrics.addAllocations(a.length);
            mergesort(a, buf, 0, a.length);
        } finally {
            metrics.exit();
        }
    }

    private void mergesort(int[] a, int[] buf, int l, int r){
        if(r - l <= 1) return;
        if(r - l <= INSERTION_CUTOFF){
            insertion(a, l, r);
            return;
        }
        int m = (l + r) >>> 1;
        metrics.enter();
        mergesort(a, buf, l, m);
        metrics.exit();
        metrics.enter();
        mergesort(a, buf, m, r);
        metrics.exit();
        merge(a, buf, l, m, r);
    }

    private void insertion(int[] a, int l, int r){
        for(int i = l + 1; i < r; i++){
            int key = a[i];
            int j = i - 1;
            while(j >= l){
                metrics.incComparisons();
                if(a[j] <= key) break;
                a[j+1] = a[j--];
                metrics.incSwaps(); // count shifts as swaps-ish
            }
            a[j+1] = key;
        }
    }

    private void merge(int[] a, int[] buf, int l, int m, int r){
        int i = l, j = m, k = l;
        while(i < m && j < r){
            metrics.incComparisons();
            if(a[i] <= a[j]) buf[k++] = a[i++];
            else buf[k++] = a[j++];
        }
        while(i < m) buf[k++] = a[i++];
        while(j < r) buf[k++] = a[j++];
        System.arraycopy(buf, l, a, l, r - l);
    }
}

import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        Random rnd = new Random(42);

        // test arrays
        int[] a1 = {5,2,9,1,5,6,3,8,7,4};
        System.out.println("Original: " + Arrays.toString(a1));

        // MergeSort
        int[] m1 = IntUtils.copy(a1);
        MergeSort ms = new MergeSort(metrics);
        metrics.reset();
        long t0 = System.nanoTime();
        ms.sort(m1);
        long t1 = System.nanoTime();
        System.out.println("MergeSort -> sorted: " + Arrays.toString(m1));
        System.out.printf("MergeSort time=%.3f ms, %s%n", (t1 - t0)/1e6, metrics);

        // QuickSort demo
        int[] q1 = IntUtils.copy(a1);
        QuickSort qs = new QuickSort(metrics);
        metrics.reset();
        t0 = System.nanoTime();
        qs.sort(q1);
        t1 = System.nanoTime();
        System.out.println("QuickSort -> sorted: " + Arrays.toString(q1));
        System.out.printf("QuickSort time=%.3f ms, %s%n", (t1 - t0)/1e6, metrics);

        // DeterministicSelect
        int[] s1 = new int[101];
        for(int i=0;i<s1.length;i++) s1[i] = rnd.nextInt(1000);
        int[] s1copy = IntUtils.copy(s1);
        DeterministicSelect ds = new DeterministicSelect(metrics);
        metrics.reset();
        t0 = System.nanoTime();
        int median = ds.select(s1, s1.length / 2);
        t1 = System.nanoTime();
        Arrays.sort(s1copy);
        System.out.println("Select median (MoM) -> " + median + ", actual median -> " + s1copy[s1copy.length/2]);
        System.out.printf("Select time=%.3f ms, %s%n", (t1 - t0)/1e6, metrics);

        // Closest pair test
        int n = 200;
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for(int i=0;i<n;i++) pts[i] = new ClosestPair.Point(rnd.nextDouble()*1000, rnd.nextDouble()*1000);
        t0 = System.nanoTime();
        ClosestPair.Result res = ClosestPair.closest(pts);
        t1 = System.nanoTime();
        System.out.printf("Closest pair dist=%.6f, time=%.3f ms%n", res.dist, (t1 - t0)/1e6);

        // Quick benchmark sort large
        int N = 200_000;
        int[] big = new int[N];
        for(int i=0;i<N;i++) big[i] = rnd.nextInt(N);
        int[] bigCopy = IntUtils.copy(big);

        metrics.reset();
        MergeSort msBig = new MergeSort(metrics);
        t0 = System.nanoTime();
        msBig.sort(big);
        t1 = System.nanoTime();
        System.out.printf("MergeSort on %d time=%.3f ms, sorted=%b%n", N, (t1 - t0)/1e6, IntUtils.isSorted(big));

        metrics.reset();
        QuickSort qsBig = new QuickSort(metrics);
        t0 = System.nanoTime();
        qsBig.sort(bigCopy);
        t1 = System.nanoTime();
        System.out.printf("QuickSort on %d time=%.3f ms, sorted=%b%n", N, (t1 - t0)/1e6, IntUtils.isSorted(bigCopy));
    }
}

// Metrics.java
public class Metrics {
    private long comparisons = 0;
    private long swaps = 0;
    private long allocations = 0;
    private int depth = 0;
    private int maxDepth = 0;

    public void incComparisons(){ comparisons++; }
    public void incSwaps(){ swaps++; }
    public void addAllocations(long n){ allocations += n; }

    public void enter(){
        depth++;
        if(depth > maxDepth) maxDepth = depth;
    }
    public void exit(){ depth = Math.max(0, depth-1); }

    public long getComparisons(){ return comparisons; }
    public long getSwaps(){ return swaps; }
    public long getAllocations(){ return allocations; }
    public int getMaxDepth(){ return maxDepth; }

    public void reset(){
        comparisons = 0; swaps = 0; allocations = 0; depth = 0; maxDepth = 0;
    }

    @Override
    public String toString(){
        return String.format("comparisons=%d swaps=%d allocations=%d maxDepth=%d",
                comparisons, swaps, allocations, maxDepth);
    }
}

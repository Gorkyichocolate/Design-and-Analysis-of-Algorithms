// ClosestPair.java (исправленный)
import java.util.*;

public class ClosestPair {
    public static class Point {
        public final double x, y;
        public Point(double x, double y){ this.x = x; this.y = y; }
    }

    public static class Result {
        public final Point a, b;
        public final double dist;
        public Result(Point a, Point b, double d){ this.a = a; this.b = b; this.dist = d; }
    }

    public static Result closest(Point[] pts){
        if(pts == null || pts.length < 2) return null;
        Point[] px = Arrays.copyOf(pts, pts.length);
        Point[] py = Arrays.copyOf(pts, pts.length);
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return recur(px, py, 0, px.length);
    }

    private static Result recur(Point[] px, Point[] py, int l, int r){
        int n = r - l;
        if(n <= 3) return brute(px, l, r);
        int mid = l + n/2;
        double midx = px[mid].x;

        // вместо статических массивов — списки
        List<Point> pylList = new ArrayList<>();
        List<Point> pyrList = new ArrayList<>();
        for(Point p : py){
            if(p.x < midx || (p.x == midx && pylList.size() < mid - l)) pylList.add(p);
            else pyrList.add(p);
        }

        Result dl = recur(px, pylList.toArray(new Point[0]), l, mid);
        Result dr = recur(px, pyrList.toArray(new Point[0]), mid, r);
        Result best = dl.dist < dr.dist ? dl : dr;
        double d = best.dist;

        Point[] strip = new Point[py.length];
        int m = 0;
        for(Point p: py){
            if(Math.abs(p.x - midx) < d) strip[m++] = p;
        }
        for(int i = 0; i < m; i++){
            for(int j = i + 1; j < m && (strip[j].y - strip[i].y) < d; j++){
                double dd = dist(strip[i], strip[j]);
                if(dd < d){
                    d = dd;
                    best = new Result(strip[i], strip[j], d);
                }
            }
        }
        return best;
    }

    private static Result brute(Point[] a, int l, int r){
        double best = Double.POSITIVE_INFINITY;
        Point pa = null, pb = null;
        for(int i = l; i < r; i++) for(int j = i + 1; j < r; j++){
            double d = dist(a[i], a[j]);
            if(d < best){
                best = d; pa = a[i]; pb = a[j];
            }
        }
        return new Result(pa, pb, best);
    }

    private static double dist(Point p, Point q){
        double dx = p.x - q.x, dy = p.y - q.y;
        return Math.hypot(dx, dy);
    }
}

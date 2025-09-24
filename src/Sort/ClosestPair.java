package Sort;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static class Point {
        public double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double closestPair(Point[] points) {
        Point[] px = points.clone();
        Arrays.sort(px, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                return Double.compare(a.x, b.x);
            }
        });
        Point[] aux = new Point[px.length];
        return closestPairRec(px, aux, 0, px.length - 1);
    }

    private static double closestPairRec(Point[] pts, Point[] aux, int left, int right) {
        if (right - left <= 3) {
            double min = Double.MAX_VALUE;
             for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    double d = dist(pts[i], pts[j]);
                    if (d < min) min = d;
                }
            }
            Arrays.sort(pts, left, right + 1, new Comparator<Point>() {
                @Override
                public int compare(Point a, Point b) {
                    return Double.compare(a.y, b.y);
                }
            });
            return min;
        }

        int mid = (left + right) / 2;
        double midX = pts[mid].x;
        double dLeft = closestPairRec(pts, aux, left, mid);
        double dRight = closestPairRec(pts, aux, mid + 1, right);
        double d = dLeft < dRight ? dLeft : dRight;

        mergeByY(pts, aux, left, mid, right);

        int m = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                aux[m++] = pts[i];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && (aux[j].y - aux[i].y) < d; j++) {
                double dist = dist(aux[i], aux[j]);
                if (dist < d) d = dist;
            }
        }

        return d;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (pts[i].y <= pts[j].y) {
                aux[k++] = pts[i++];
            } else {
                aux[k++] = pts[j++];
            }
        }
        while (i <= mid) aux[k++] = pts[i++];
        while (j <= right) aux[k++] = pts[j++];
        for (int t = left; t <= right; t++) pts[t] = aux[t];
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

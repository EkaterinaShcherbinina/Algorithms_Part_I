package MergeAndQuickSort.CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private static final int SEGMENT_LENGTH = 4;
    private final ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] pointsForSort = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            pointsForSort[i] = points[i];
        }
        for (Point invokingPoint: points) {
            if (invokingPoint == null) throw new IllegalArgumentException();
            sort(pointsForSort, invokingPoint.slopeOrder());
            int count = 0;
            for (int i = 0; i < pointsForSort.length; i++) {
                if (i < pointsForSort.length - 1 && pointsForSort[i].compareTo(pointsForSort[i + 1]) == 0) throw new IllegalArgumentException();
                if (i < pointsForSort.length - 1 &&
                        invokingPoint.slopeOrder().compare(pointsForSort[i], pointsForSort[i + 1]) == 0) count++;
                    else {
                    if (count >= SEGMENT_LENGTH - 2) {
                        Point[] aux = new Point[count + 2];
                        aux[0] = invokingPoint;
                        System.arraycopy(pointsForSort, i - count, aux, 1, count + 1);
                        Arrays.sort(aux);
                        LineSegment seg = new LineSegment(aux[0], aux[count + 1]);
                        if (invokingPoint.compareTo(aux[0]) == 0) {
                            segments.add(seg);
                        }
                    }
                    count = 0;
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    private static void sort(Point[] a, Point[] aux, Comparator<Point> comparator, int low, int high) {
        if (high <= low) return;

        int mid = low + (high - low) / 2;
        sort(a, aux, comparator, low, mid);
        sort(a, aux, comparator, mid + 1, high);
        merge(a, aux, comparator, low, high, mid);
    }

    private static void sort(Point[] a, Comparator<Point> comparator) {
        Point[] aux = new Point[a.length];
        sort(a, aux, comparator, 0, a.length - 1);
    }

    private static void merge(Point[] a, Point[] aux, Comparator<Point> comparator, int lo, int hi, int mid) {
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (comparator.compare(aux[i], aux[j]) < 0) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }
}

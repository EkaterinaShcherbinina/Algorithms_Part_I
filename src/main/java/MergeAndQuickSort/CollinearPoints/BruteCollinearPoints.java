package MergeAndQuickSort.CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> lineSegment = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        int inf;
        for (Point invokingPoint : points) {
            if (invokingPoint == null) throw new IllegalArgumentException();
            inf = 0;
            for (Point point : points) {
                if (point == null) throw new IllegalArgumentException();
                if (invokingPoint.compareTo(point) == 0) inf++;
                if (inf > 1) throw new IllegalArgumentException();

                double slop = invokingPoint.slopeTo(point);
                int SEGMENT_LENGTH = 4;
                Point[] aux = new Point[SEGMENT_LENGTH];

                int n = 0;
                for (Point value : points) {
                    if (value == null) throw new IllegalArgumentException();
                    if (n >= aux.length) break;
                    if (Double.compare(invokingPoint.slopeTo(value), slop) != 0) {
                        continue;
                    }
                    aux[n++] = value;
                }
                if (aux[aux.length - 2] != null) {
                    aux[aux.length - 1] = invokingPoint;
                    Arrays.sort(aux);
                    LineSegment newSegment = new LineSegment(aux[0], aux[SEGMENT_LENGTH - 1]);
                    if (invokingPoint.compareTo(aux[0]) == 0
                            && point.compareTo(aux[1]) == 0) {
                        lineSegment.add(newSegment);
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegment.size();
    }

    public LineSegment[] segments() {
            return lineSegment.toArray(new LineSegment[0]);
        }
   }
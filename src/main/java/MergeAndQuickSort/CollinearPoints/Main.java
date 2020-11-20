package MergeAndQuickSort.CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {

        Point[] points = new Point[15];
        points[0] = new Point(6, 3);
        points[1] = new Point(4, 2);
        points[2] = new Point(8, 2);
        points[3] = new Point(2, 9);
        points[4] = new Point(7, 6);
        points[5] = new Point(5, 4);
        points[6] = new Point(9, 4);
        points[7] = new Point(3, 6);
        points[8] = new Point(6, 6);
        points[9] = new Point(6, 4);
        points[10] = new Point(7, 8);
        points[11] = new Point(4, 5);
        points[12] = new Point(6, 5);
        points[13] = new Point(1, 1);
        points[14] = null;

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);

        LineSegment[] segments = fastCollinearPoints.segments();
        int j = fastCollinearPoints.numberOfSegments();
        for (int i = 0; i < fastCollinearPoints.numberOfSegments(); i++) {
            System.out.println(segments[i].toString());
        }
        System.out.println();

        LineSegment[] segmentsS = fastCollinearPoints.segments();
        for (int i = 0; i < fastCollinearPoints.numberOfSegments(); i++) {
            System.out.println(segmentsS[i].toString());
        }

        /*BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] segmentsB = bruteCollinearPoints.segments();
        for(int i = 0; i < bruteCollinearPoints.numberOfSegments(); i++) {
            System.out.println(segmentsB[i].toString());
        }
        System.out.println(bruteCollinearPoints.numberOfSegments());
        LineSegment[] segmentsC = bruteCollinearPoints.segments();
        for(int i = 0; i < bruteCollinearPoints.numberOfSegments(); i++) {
            System.out.println(segmentsC[i].toString());
        }*/

   /*     In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();*/
    }
}

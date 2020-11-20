package MergeAndQuickSort.CollinearPoints;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    private final Comparator<Point> pointComparator = new PointComparator(this);
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private static class PointComparator implements Comparator<Point> {
        private final Point thisPoint;

        public PointComparator(Point thisPoint) {
            this.thisPoint = thisPoint;
        }
        @Override
        public int compare(Point point1, Point point2) {
            double value1 = thisPoint.slopeTo(point1);
            double value2 = thisPoint.slopeTo(point2);
            if (value1 < value2) return -1;
            else if (value1 > value2) return 1;
            else return 0;
        }
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        else if (this.y > that.y || this.x > that.x) return 1;
        else return 0;
    }

    public double slopeTo(Point that) {
        if (that.y == this.y && that.x == this.x) return Double.NEGATIVE_INFINITY;
        else if (that.x == this.x) return Double.POSITIVE_INFINITY;
        else if (that.y == this.y) return 0;
        else {
            return (double) (that.y - this.y) / (that.x - this.x);
        }
    }

    public Comparator<Point> slopeOrder() {
        return pointComparator;
    }
}

package MergeAndQuickSort.CollinearPoints;

public class LineSegment {
    public Point p;
    public Point q;
    public LineSegment(Point p, Point q) {
        this.p = p;
        this.q = q;
    }


    public void draw() {}
    public String toString() {
        return "p: " + p.toString() +  " q: " + q.toString();
    }
}
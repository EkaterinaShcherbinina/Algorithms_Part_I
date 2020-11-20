package PriorityQueues.KDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {
    private final TreeSet<Point2D> treeSet;

    public PointSET() {
        treeSet = new TreeSet<>();
    }

    public boolean isEmpty() {
        return treeSet.isEmpty();
    }

    public int size() {
        return treeSet.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        treeSet.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        return treeSet.contains(p);
    }

    public void draw() {
        treeSet.forEach(Point2D::draw);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        return treeSet.stream().filter(rect::contains)
            .collect(Collectors.toList());
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;

        Point2D res = null;

        double distance = -1;
        for (Point2D point: treeSet) {
            double pointDistance = p.distanceSquaredTo(point);
            if (distance == -1 || Double.compare(pointDistance, distance) < 0) {
                res = point;
                distance = pointDistance;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.3, 0.6));
        pointSET.insert(new Point2D(0.4, 0.2));
        pointSET.insert(new Point2D(0.9, 0.1));
        pointSET.insert(new Point2D(0.5, 0.2));
        pointSET.insert(new Point2D(0.8, 0.1));
        pointSET.insert(new Point2D(0.6, 0.2));
        pointSET.insert(new Point2D(1.2, 0.6));
        pointSET.insert(new Point2D(0.7, 0.9));
        pointSET.insert(new Point2D(1.3, 1.0));

        System.out.println("size: " + pointSET.size());
        System.out.println("contains 0.8, 0.1: " + pointSET.contains(new Point2D(0.8, 0.1)));
        System.out.println("contains 1.8, 1.1: " + pointSET.contains(new Point2D(1.8, 1.1)));
        System.out.println("range: ");

        for (Point2D p: pointSET.range(new RectHV(0.2, 0.3, 0.5, 0.4))) {
            p.draw();
        }

        System.out.println("nearest: " + pointSET.nearest(new Point2D(0.8, 0.1)));
    }
}

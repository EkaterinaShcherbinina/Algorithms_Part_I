package PriorityQueues.KDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private static final double RADIUS_POINT = 0.02;
    private static final double RADIUS_LINE = 0.01;
    private Node root;
    private class Node {
        private final Point2D point;
        private Node left, right;
        private final boolean direction;
        private int count;

        public Node(Point2D point, boolean direction, int size) {
            this.point = point;
            this.direction = direction;
            this.count = size;
        }
    }

    private boolean isVertical(Node node) {
        if (node == null) return false;
        return node.direction == VERTICAL;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.count;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = put(root, p, VERTICAL);
    }

    private Node put(Node node, Point2D p, boolean direction) {
       if (node == null) return new Node(p, direction, 1);

       if (node.direction == VERTICAL) {
           int cmp = Double.compare(p.x(), node.point.x());
           if (cmp < 0) node.left = put(node.left, p, HORIZONTAL);
           else node.right = put(node.right, p, HORIZONTAL);
       } else {
           int cmp = Double.compare(p.y(), node.point.y());
           if (cmp < 0) node.left = put(node.left, p, VERTICAL);
           else node.right = put(node.right, p, VERTICAL);
       }

       node.count = 1 + size(node.left) + size(node.right);
       return node;
    }

    public boolean contains(Point2D p)      {
        if (p == null) throw new IllegalArgumentException();
        Node node = root;

        while (node != null) {
            if (node.point.compareTo(p) == 0) return true;
            int cmp;
            if (node.direction == VERTICAL) {
                cmp = Double.compare(p.x(), node.point.x());
            }
            else {
                cmp = Double.compare(p.y(), node.point.y());
            }
            if (cmp < 0) node = node.left;
            else node = node.right;
        }

        return false;
    }
    public void draw() {
        draw(root, new Point2D(root.point.x(), 0), new Point2D(root.point.x(), 1));
    }
    private void draw(Node node, Point2D p1, Point2D p2) {
        if (node == null) return;
        drawPoint(node.point);

        if (isVertical(node)) drawVertical(p1, p2);
        else drawHorizontal(p1, p2);

        if (node.left != null) {
            Point2D leftPoint1;
            Point2D leftPoint2;
            if (!isVertical(node.left)) {
                leftPoint1 = new Point2D(0, node.left.point.y());
                leftPoint2 = new Point2D(node.point.x(), node.left.point.y());
            } else {
                leftPoint1 = new Point2D(node.left.point.x(), 0);
                leftPoint2 = new Point2D(node.left.point.x(), node.point.y());
            }
            draw(node.left, leftPoint1, leftPoint2);
        }

        if (node.right != null) {
            Point2D rightPoint1;
            Point2D rightPoint2;
            if (!isVertical(node.right)) {
                rightPoint1 = new Point2D(node.point.x(), node.right.point.y());
                rightPoint2 = new Point2D(1, node.right.point.y());
            } else {
                rightPoint1 = new Point2D(node.right.point.x(), node.point.y());
                rightPoint2 = new Point2D(node.right.point.x(), 1);
            }
            draw(node.right, rightPoint1, rightPoint2);
        }
    }

    private void drawVertical(Point2D point1, Point2D point2) {
        StdDraw.setPenRadius(RADIUS_LINE);
        StdDraw.setPenColor(StdDraw.RED);
        point1.drawTo(point2);
    }

    private void drawHorizontal(Point2D point1, Point2D point2) {
        StdDraw.setPenRadius(RADIUS_LINE);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        point1.drawTo(point2);
    }

    private void drawPoint(Point2D point) {
        StdDraw.setPenRadius(RADIUS_POINT);
        StdDraw.setPenColor(StdDraw.BLACK);
        point.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        if (root == null) return null;
        ArrayList<Point2D> list = new ArrayList<>();

        range(root, rect, list);
        return list;
    }

    private void range(Node node, RectHV rect, ArrayList<Point2D> list) {
        if (node == null) return;

        if (isVertical(node)) {
            if (Double.compare(rect.xmax(), node.point.x()) < 0) range(node.left, rect, list);
            else if (Double.compare(node.point.x(), rect.xmin()) < 0) range(node.right, rect, list);
            else {
                addPointToListIfNeeded(node, rect, list);
                range(node.left, rect, list);
                range(node.right, rect, list);
            }
        } else {
            if (Double.compare(rect.ymax(), node.point.y()) < 0) range(node.left, rect, list);
            else if (Double.compare(node.point.y(), rect.ymin()) < 0) range(node.right, rect, list);
            else {
                addPointToListIfNeeded(node, rect, list);
                range(node.left, rect, list);
                range(node.right, rect, list);
            }
        }
    }

    private void addPointToListIfNeeded(Node node, RectHV rect, ArrayList<Point2D> list) {
        int cmpMinX = Double.compare(rect.xmin(), node.point.x());
        int cmpMaxX = Double.compare(rect.xmax(), node.point.x());
        int cmpMinY = Double.compare(rect.ymin(), node.point.y());
        int cmpMaxY = Double.compare(rect.ymax(), node.point.y());

        if (cmpMinX <= 0 && cmpMaxX >= 0
            && cmpMinY <= 0 && cmpMaxY >= 0) list.add(node.point);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Point2D closest = root.point;
        closest = nearest(root, p, closest);
        return closest;
    }

    private Point2D nearest(Node node, Point2D query, Point2D closest) {
        if (node == null) return closest;

        closest = Double.compare(query.distanceSquaredTo(closest), query.distanceSquaredTo(node.point)) < 0 ? closest : node.point;

        if (isLessQueryPoint(node.direction, query, node.point)) {
            closest = nearest(node.left, query, closest);

            Point2D rectNode = createRectNode(node.direction, query, node.point);
            if (Double.compare(query.distanceSquaredTo(closest), query.distanceSquaredTo(rectNode)) > 0) {
                closest = nearest(node.right, query, closest);
            }
        } else {
            closest = nearest(node.right, query, closest);

            Point2D rectNode = createRectNode(node.direction, query, node.point);
            if (Double.compare(query.distanceSquaredTo(closest), query.distanceSquaredTo(rectNode)) > 0) {
                closest = nearest(node.left, query, closest);
            }
        }
        return closest;
    }

    private Point2D createRectNode(boolean isVertical, Point2D query, Point2D nodePoint) {
         return isVertical ? new Point2D(nodePoint.x(), query.y()) : new Point2D(query.x(), nodePoint.y());
    }

    private boolean isLessQueryPoint(boolean isVertical, Point2D query, Point2D point) {
        if (isVertical) {
            return Double.compare(query.x(), point.x()) < 0;
        }
        else {
            return Double.compare(query.y(), point.y()) < 0;
        }
    }
   public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.6, 0.5));
        kdTree.insert(new Point2D(0.3, 0.3));
        kdTree.insert(new Point2D(0.9, 0.8));
        kdTree.insert(new Point2D(0.5, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.8));
        kdTree.insert(new Point2D(0.1, 0.5));
        kdTree.insert(new Point2D(0.3, 0.7));
        kdTree.insert(new Point2D(0.9, 0.4));
        kdTree.insert(new Point2D(0.7, 0.8));

        System.out.println("isEmpty: " + kdTree.isEmpty());
        System.out.println("size: " + kdTree.size());

       Iterable<Point2D> list = kdTree.range(new RectHV(0, 0.3, 0.3, 0.8));
       for (Point2D point: list) {
           System.out.println(point.toString());
       }

       System.out.println("nearest: 0.8, 0.3");
       Point2D p = kdTree.nearest(new Point2D(0.8, 0.3));
       System.out.println(p);

       System.out.println("nearest: 0.5, 0.3");
       Point2D p1 = kdTree.nearest(new Point2D(0.5, 0.2));
       System.out.println(p1);

       System.out.println("nearest: 0.6, 0.5");
       Point2D p2 = kdTree.nearest(new Point2D(0.6, 0.5));
       System.out.println(p2);

       System.out.println("nearest: 0.9, 0.8");
       Point2D p3 = kdTree.nearest(new Point2D(0.9, 0.8));
       System.out.println(p3);

       System.out.println("nearest: 0.3, 0.4");
       Point2D p4 = kdTree.nearest(new Point2D(0.3, 0.4));
       System.out.println(p4);

       System.out.println("nearest: 0.6, 0.1");
       Point2D p5 = kdTree.nearest(new Point2D(0.6, 0.1));
       System.out.println(p5);
   }
}

package bearmaps;
import java.util.List;

public class KDTree implements PointSet {
    private Node root;
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;

    private class Node {
        private Point p;
        private boolean orientation;
        private Node leftChild = null;  //down child
        private Node rightChild = null; //up child

        Node(Point p, boolean orientation) {
            this.p = p;
            this.orientation = orientation;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p: points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    private Node add(Point p, Node n, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        }
        if (p.equals(n.p)) {
            return n;
        }
        if (comparePoints(p, n.p, orientation) < 0) {
            n.leftChild = add(p, n.leftChild, !orientation);
        } else if (comparePoints(p, n.p, orientation) >= 0) {
            n.rightChild = add(p, n.rightChild, !orientation);
        }
        return n;
    }

    private double comparePoints(Point a, Point b, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    private Node nearestHelper(Node n, Point goal, Node best) {
        Node goodSide;
        Node badSide;
        if (n == null) {
            return best;
        }
        if (Point.distance(n.p, goal) < Point.distance(best.p, goal)) {
            best = n;
        }
        if (comparePoints(goal, n.p, n.orientation) < 0) {
            goodSide = n.leftChild;
            badSide = n.rightChild;
        } else {
            goodSide = n.rightChild;
            badSide = n.leftChild;
        }
        best = nearestHelper(goodSide, goal, best);
        if (n.orientation) {
            if (Math.pow(Math.abs(n.p.getX() - goal.getX()), 2)
                    - Point.distance(best.p, goal) < 0) {
                best = nearestHelper(badSide, goal, best);
            }
        } else {
            if (Math.pow(Math.abs(n.p.getY() - goal.getY()), 2)
                    - Point.distance(best.p, goal) < 0) {
                best = nearestHelper(badSide, goal, best);
            }
        }
        return best;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = nearestHelper(root, goal, root);
        return new Point(best.p.getX(), best.p.getY());
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        System.out.println(kd.nearest(1, 1));
    }
}

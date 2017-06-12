import java.util.Comparator;

public class Point {
    private double x;
    private double y;
    public static Comparator<Point> COMPARATOR = (pointA, pointB) -> {
        int result = Double.compare(pointA.x, pointB.x);
        if (result == 0)
            return Double.compare(pointA.y, pointB.y);
        else
            return result;
    };

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

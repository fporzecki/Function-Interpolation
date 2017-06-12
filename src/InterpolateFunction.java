import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public abstract class InterpolateFunction {
    protected Point[] points;

    public InterpolateFunction(List<Point> points) {
        this.points = new Point[points.size()];
        addPoints(points);
    }

    private void addPoints(List<Point> points) {
        this.points= points.toArray(new Point[points.size()]);
        sort();
    }

    private void sort() {
        Arrays.sort(points, Point.COMPARATOR);
    }

    public abstract double giveResult(double x);
}

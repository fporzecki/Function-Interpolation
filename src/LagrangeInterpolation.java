import java.util.List;

public class LagrangeInterpolation extends InterpolateFunction {

    public LagrangeInterpolation(List<Point> points) {
        super(points);
    }

    @Override
    public double giveResult(double x) {
        double result = 0;
        for (int i = 0; i < points.length; i++)
            result += points[i].getY() * getL(i, x);
        return result;
    }

    private double getL(int i, double x) {
        double li = 1;
        Point pointI = points[i];
        double xi = pointI.getX();
        for (Point pointJ : points) {
            if (pointI == pointJ)
                continue;
            double xj = pointJ.getX();
            li *= (x - xj) / (xi - xj);
        }
        return li;
    }
}
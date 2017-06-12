import java.util.List;

public class CubicSplineInterpolation extends InterpolateFunction {

    public double[] z;
    public double[] h;


    public CubicSplineInterpolation(List<Point> points) {
        super(points);
        z = new double[points.size()];
        h = new double[points.size()];
        calculateDerivative();
    }

    private void calculateDerivative() {
        double[] b = new double[points.length];
        double[] u = new double[points.length];
        double[] v = new double[points.length];

        for (int i = 0; i < points.length - 1; i++) {
            double xA = points[i].getX();
            double yA = points[i].getY();
            double xB = points[i + 1].getX();
            double yB = points[i + 1].getY();
            h[i] = xB - xA;
            b[i] = 6 * (yB - yA) / h[i];
        }

        u[0] = 2 * (h[0] + h[1]);
        v[0] = b[1] - b[0];

        for (int i = 1; i < points.length; i++) {
            u[i] = 2 * (h[i - 1] * h[i]) - h[i - 1] * h[i - 1] / u[i - 1];
            v[i] = b[i] - b[i - 1] - h[i - 1] * v[i - 1] / u[i - 1];
        }
        for (int i = points.length - 2; i > 0; i--)
            z[i] = (v[i] - h[i] * z[i + 1]) / u[i];
    }

    private double getA(int i) {
        return (z[i + 1] - z[i]) / (6 * h[i]);
    }

    private double getB(int i) {
        return z[i] / 2;
    }

    private double getC(int i) {
        return (z[i + 1] + 2 * z[i]) / -(6 * h[i]) +
                (points[i + 1].getY() - points[i].getY()) / h[i];
    }

    private int i(double x) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].getX() < x && x < points[i + 1].getX())
                return i;
        }
        return 0;
    }

    @Override
    public double giveResult(double x) {
        int i = i(x);
        double temp = x - points[i].getX();
        return points[i].getY() + temp * (getC(i) + temp * (getB(i) + temp * getA(i)));
    }
}

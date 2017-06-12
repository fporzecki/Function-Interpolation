import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static String FILE_NOT_FOUND = "Nie znaleziono pliku ";
    public static String SPLIT_STRING = ",";

    public static void saveResults(String inputData, String filename, int multiplier)
            throws FileNotFoundException {
        List<Point> points = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(Paths.get(inputData))) {
            try(Stream<String> stream = br.lines()) {
                stream.skip(1).forEach(line -> {
                    String[] elements = line.split(SPLIT_STRING);
                    double x = Double.parseDouble(elements[0]);
                    double y = Double.parseDouble(elements[1]);
                    points.add(new Point(x, y));
                });
                stream.close();
            }
        } catch (IOException e) {
            System.err.println(FILE_NOT_FOUND+e.getMessage());
            return;
        }

        LagrangeInterpolation lgPoly = new LagrangeInterpolation(points);
        CubicSplineInterpolation splineFunction=new CubicSplineInterpolation(points);

        PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();
        sb.append("distance");
        sb.append(",");
        sb.append("height");
        sb.append(",");
        sb.append("lagrange");
        sb.append(",");
        sb.append("spline");
        sb.append("\n");

        int it = 0;

        for(double i = 0;
            i < points.get(points.size() - 1).getX() && it < points.size();
            i += (points.get(points.size() - 1).getX() / points.size()) * multiplier, it++)
        {
            sb.append(points.get(it*multiplier).getX());
            sb.append(",");
            sb.append(points.get(it*multiplier).getY());
            sb.append(",");
            sb.append(lgPoly.giveResult(i));
            sb.append(",");
            sb.append(splineFunction.giveResult(i));
            sb.append("\n");
        }

        pw.write(sb.toString());
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        saveResults("data.csv", "akademiki.csv", 1);
        saveResults("data.csv", "akademiki2.csv", 10);
        saveResults("data.csv", "akademiki3.csv", 20);
        saveResults("data.csv", "akademiki4.csv", 50);
        saveResults("data.csv", "akademiki5.csv", 250);

        saveResults("greenwich.csv", "greenwich.csv", 1);
        saveResults("greenwich.csv", "greenwich2.csv", 10);
        saveResults("greenwich.csv", "greenwich3.csv", 20);
        saveResults("greenwich.csv", "greenwich4.csv", 50);
        saveResults("greenwich.csv", "greenwich5.csv", 250);

        saveResults("targanice.csv", "targanice.csv", 1);
        saveResults("targanice.csv", "targanice2.csv", 10);
        saveResults("targanice.csv", "targanice3.csv", 20);
        saveResults("targanice.csv", "targanice4.csv", 50);
        saveResults("targanice.csv", "targanice5.csv", 250);
    }
}
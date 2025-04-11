package se.ifmo.plot;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import se.ifmo.CsvWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlotFromCsv {
    public static void main(String[] args) throws PythonExecutionException, IOException {
//        plot("Sin.csv");
//        plot("Cos.csv");
//        plot("Tan.csv");
//        plot("Cot.csv");
//        plot("Csc.csv");
//        plot("Sec.csv");
//        plot("Log3.0.csv");
//        plot("Log3.0.csv");
//        plot("Log5.0.csv");
//        plot("Log10.0.csv");
        plot("Function.csv");
    }

    private static void plot(String csvFileName) throws PythonExecutionException, IOException {
        List<Double> xs = new ArrayList<>();
        List<Double> ys = new ArrayList<>();
        List<List<String>> xys = CsvWorker.readCsvFile("src/main/resources/" + csvFileName);
        for (List<String> xy : xys) {
            if (!xy.get(1).equals("NaN")) {
                xs.add(Double.parseDouble(xy.get(0)));
                ys.add(Double.parseDouble(xy.get(1)));
            }
        }

        Plot plt = Plot.create();

        // Настройки графика
        double ymin = -3; // Минимальное значение по оси Y
        double ymax = 3;  // Максимальное значение по оси Y

        // Построение графика с маленькими точками (markersize=2)
        plt.plot()
                .add(xs, ys, ".")
                .label(csvFileName);

        plt.legend().loc("upper right");
        plt.title("scatter");

        // Установка пределов по оси Y
        plt.ylim(ymin, ymax);

        plt.show();
//        plt.savefig("src/main/resources/plots/" + csvFileName + ".png").dpi(200);
    }
}

package operations;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.Point;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.TabulatedFunctionFactory;

import static operations.TabulatedFunctionOperationService.asPoints;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    TabulatedFunctionFactory factory;

    TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i].x;
        }
        for (int i = 0; i < points.length - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (xValues[i + 1] - xValues[i]);
        }

        xValues[xValues.length - 1] = points[xValues.length - 1].x;
        yValues[yValues.length - 1] = yValues[yValues.length - 2];

        return factory.create(xValues, yValues);
    }
}
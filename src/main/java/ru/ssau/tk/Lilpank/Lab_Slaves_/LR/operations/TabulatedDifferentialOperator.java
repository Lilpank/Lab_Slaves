package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.Point;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.TabulatedFunctionFactory;

import static ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations.TabulatedFunctionOperationService.asPoints;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    public TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
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

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        Object object = new Object();

        if (function instanceof SynchronizedTabulatedFunction) {
            return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
        }
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function, object);
        return synchronizedTabulatedFunction.doSynchronously(this::derive);
    }
}
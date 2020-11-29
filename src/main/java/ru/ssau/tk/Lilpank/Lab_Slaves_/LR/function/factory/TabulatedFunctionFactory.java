package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.StrictTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.UnmodifiableTabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createStrict(double[] xValues, double[]
            yValues) {
        return new StrictTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createUnmodifiable(double[] xValues, double[]
            yValues) {
        return new UnmodifiableTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues) {
        return new StrictTabulatedFunction(createUnmodifiable(xValues, yValues));
    }

}

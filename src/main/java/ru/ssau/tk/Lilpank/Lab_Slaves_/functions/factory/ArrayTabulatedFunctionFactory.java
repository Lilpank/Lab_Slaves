package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}

package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}

package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}

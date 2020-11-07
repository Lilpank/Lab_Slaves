package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}

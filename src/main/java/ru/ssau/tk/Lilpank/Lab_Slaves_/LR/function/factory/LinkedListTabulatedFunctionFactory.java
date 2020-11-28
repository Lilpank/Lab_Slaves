package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory;


import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.StrictTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}

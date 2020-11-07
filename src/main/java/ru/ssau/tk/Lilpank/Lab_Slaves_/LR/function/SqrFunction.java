package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

public class SqrFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return Math.pow(x,2);
    }
}

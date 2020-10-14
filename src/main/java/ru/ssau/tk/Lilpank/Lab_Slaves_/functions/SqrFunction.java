package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

public class SqrFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return Math.pow(x,2);
    }
}

package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

public class LgFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.log10(x);
    }

}

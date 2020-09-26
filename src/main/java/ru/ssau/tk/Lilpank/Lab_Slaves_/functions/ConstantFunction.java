package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

public class ConstantFunction implements MathFunction {

    private final double num;

    public ConstantFunction(double num) {
        this.num = num;
    }

    public double getNum() {
        return num;
    }

    @Override
    public double apply(double x) {
        return x;
    }
}

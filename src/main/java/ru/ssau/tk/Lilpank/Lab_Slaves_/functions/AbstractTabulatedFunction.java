package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    private int count;

    @Override
    public int getCount() {
        return count;
    }

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

}

package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    private int count;

    public int getCount() {
        return count;
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1) {
            return getY(getCount());
        }
        else interpolate(x, floorIndexOfX(x));
    }

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

}

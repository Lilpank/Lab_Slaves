package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length <= 2) {
            throw new IllegalArgumentException("Длина меньше минимальной.");
        } else {
            count = xValues.length;
            this.xValues = Arrays.copyOf(xValues, count);
            this.yValues = Arrays.copyOf(yValues, count);
        }
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Длина меньше минимальной.");
        } else {
            this.count = count;
            xValues = new double[count];
            yValues = new double[count];

            double step = (xTo - xFrom) / (count - 1);
            double xMomentValue = xFrom;
            for (int i = 0; i < count; i++) {
                xValues[i] = xMomentValue;
                yValues[i] = source.apply(xMomentValue);
                xMomentValue += step;
            }
        }
    }

    @Override
    public int getCount() {
        return count;
    }


    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 1; i < count; i++) {
            if (xValues[i] > x) {
                return i - 1;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (floorIndex == count) {
            return extrapolateRight(x);
        } else {
            return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
        }
    }


    @Override
    public double getX(int index) {
        if (index <= -0 | index >= xValues.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return xValues[index];
        }
    }

    @Override
    public double getY(int index) {
        if (index < 0 | index >= yValues.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return yValues[index];
        }
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }
}
package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import java.util.Iterator;

public class MockTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction {
    final double x0 = 3.;
    final double x1 = 5.;
    final double y0 = 6.;
    final double y1 = 12.;


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        return (index == 1 ? x1 : x0);
    }

    @Override
    public double getY(int index) {
        return (index == 1 ? y1 : y0);
    }

    @Override
    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setY(int index, double value) {

    }

    @Override
    public int indexOfX(double x) {
        if (x == x0) {
            return 0;
        } else if (x == x1) {
            return 1;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        if (y == y0) {
            return 0;
        } else if (y == y1) {
            return 1;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return 0;
    }

    @Override
    public double rightBound() {
        return 20;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x - x0 < 0.0005) {
            return 0;
        } else if (x - x1 > 0.0005) {
            return getCount();
        }
        return -1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (floorIndex == 1) {
            return extrapolateRight(x);
        }
        return x;
    }


    @Override
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX));
    }
}

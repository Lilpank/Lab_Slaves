package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StrictTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction function;

    public <T> StrictTabulatedFunction(T function) {
        this.function = (TabulatedFunction) function;
    }

    @Override
    public int getCount() {
        return function.getCount();
    }

    @Override
    public double getX(int index) {
        return function.getX(index);
    }

    @Override
    public double getY(int index) {
        return function.getY(index);
    }

    @Override
    public Iterator<Point> iterator() {
        return function.iterator();
    }

    @Override
    public void setY(int index, double value) {
        function.setY(index, value);
    }

    @Override
    public int indexOfX(double x) {
        return function.indexOfX(x);
    }

    @Override
    public int indexOfY(double y) {
        return function.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return function.leftBound();
    }

    @Override
    public double rightBound() {
        return function.rightBound();
    }

    @Override
    public double apply(double x) {
        if (function.indexOfX(x) == -1) {
            throw new UnsupportedOperationException();
        } else {
            return function.getY(indexOfX(x));
        }
    }
}

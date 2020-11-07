package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import java.util.Iterator;

public interface TabulatedFunction extends MathFunction, Iterable<Point> {
    int getCount();

    double getX(int index);

    double getY(int index);

    Iterator<Point> iterator();

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();
}

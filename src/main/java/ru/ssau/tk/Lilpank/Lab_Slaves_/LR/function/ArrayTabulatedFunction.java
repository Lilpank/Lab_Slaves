package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable, Insertable, Removable {
    private static final long serialVersionUID = 1545272046013992252L;
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private double[] xValues;
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private double[] yValues;
    private int count = 0;

    @JsonCreator
    public ArrayTabulatedFunction(
            @JsonProperty(value = "xValues") double[] xValues,
            @JsonProperty(value = "yValues")double[] yValues
    ) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

        if (xValues.length < 2) {
            throw new IllegalArgumentException("Длина меньше минимальной.");
        } else {
            count = xValues.length;
            this.xValues = Arrays.copyOf(xValues, count);
            this.yValues = Arrays.copyOf(yValues, count);
        }
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Неправильные значения параметров.");
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
        if (x < xValues[floorIndex] || x > xValues[floorIndex + 1]) {
            throw new InterpolationException("X не находится внутри интервала интерполирования.");
        }
        if (floorIndex == count) {
            return extrapolateRight(x);
        } else {
            return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
        }
    }


    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
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

    @Override
    public void insert(double x, double y) {
        if (indexOfX(x) != -1) {
            setY(indexOfX(x), y);
        } else {
            double[] xValuesNew = new double[count + 1];
            double[] yValuesNew = new double[count + 1];
            if (x < leftBound()) {
                xValuesNew[0] = x;
                yValuesNew[0] = y;
                System.arraycopy(xValues, 0, xValuesNew, 1, count);
                System.arraycopy(yValues, 0, yValuesNew, 1, count);
            } else if (x > rightBound()) {
                System.arraycopy(xValues, 0, xValuesNew, 0, count);
                System.arraycopy(yValues, 0, yValuesNew, 0, count);
                xValuesNew[count] = x;
                yValuesNew[count] = y;
            } else {
                int i = floorIndexOfX(x);
                System.arraycopy(xValues, 0, xValuesNew, 0, i + 1);
                System.arraycopy(yValues, 0, yValuesNew, 0, i + 1);
                xValuesNew[i + 1] = x;
                yValuesNew[i + 1] = y;
                System.arraycopy(xValues, i + 1, xValuesNew, i + 2, count - i - 1);
                System.arraycopy(yValues, i + 1, yValuesNew, i + 2, count - i - 1);
            }
            this.xValues = xValuesNew;
            this.yValues = yValuesNew;
            count++;
        }
    }

    @Override
    public void remove(int index) {
        if (count <= 2) {
            throw new UnsupportedOperationException("Массивы xValues и yValues должны иметь размер больше 2.");
        }
        double[] xValuesNew = new double[count + 1];
        double[] yValuesNew = new double[count + 1];
        System.arraycopy(xValues, 0, xValuesNew, 0, index);
        System.arraycopy(yValues, 0, yValuesNew, 0, index);
        System.arraycopy(xValues, index + 1, xValuesNew, index, count - index - 1);
        System.arraycopy(yValues, index + 1, yValuesNew, index, count - index - 1);
        this.xValues = xValuesNew;
        this.yValues = yValuesNew;
        count--;
    }
}

package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions.InterpolationException;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.000001;
    private final double[] valuesX = new double[]{-15, -3, -1, 0, 1, 3, 15};
    private final double[] valuesY = new double[]{-5, -2, -1, -0, 1, 2, 9};
    private final MathFunction sqrFunc = new SqrFunction();

    private AbstractTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }


    private AbstractTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(sqrFunc, 0, 10, 20);
    }

    private AbstractTabulatedFunction getUnitArray() { return new ArrayTabulatedFunction(sqrFunc, 6, 6, 1); }


    @Test
    public void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().floorIndexOfX(0));
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().extrapolateLeft(1));
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().extrapolateLeft(5));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{1},new double[]{1}));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{1,3},new double[]{1,2}));
    }

    @Test
    public void testIteratorWhile() {
        AbstractTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        Iterator<Point> iterator = testDefinedThroughArrays.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(testDefinedThroughArrays.getX(i), point.x, DELTA);
            assertEquals(testDefinedThroughArrays.getY(i++), point.y, DELTA);
        }
        assertEquals(testDefinedThroughArrays.getCount(), i);

        assertThrows(NoSuchElementException.class, iterator::next);

        AbstractTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();
        iterator = testDefinedThroughMathFunction.iterator();
        i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(testDefinedThroughMathFunction.getX(i), point.x, DELTA);
            assertEquals(testDefinedThroughMathFunction.getY(i++), point.y, DELTA);
        }
        assertEquals(testDefinedThroughMathFunction.getCount(), i);

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorForEach() {
        AbstractTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        int i = 0;
        for (Point point : testDefinedThroughArrays) {
            assertEquals(point.x, testDefinedThroughArrays.getX(i), DELTA);
            assertEquals(point.y, testDefinedThroughArrays.getY(i++), DELTA);
        }
        assertEquals(testDefinedThroughArrays.getCount(), i);

        AbstractTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();
        i = 0;
        for (Point point : testDefinedThroughMathFunction) {
            assertEquals(point.x, testDefinedThroughMathFunction.getX(i), DELTA);
            assertEquals(point.y, testDefinedThroughMathFunction.getY(i++), DELTA);
        }
        assertEquals(testDefinedThroughMathFunction.getCount(), i);
    }

    @Test
    public void testApply() {
        assertEquals(getDefinedThroughArrays().apply(-35), -10.0, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(-1.9), -0.9999999999999998, DELTA);
        assertEquals(getDefinedThroughArrays().apply(35), 20.666666666666668, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(10), 100, DELTA);
        assertEquals(getDefinedThroughArrays().apply(0.5), 0.5, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(0.125), 0.06578947368421052, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(8), 64.04432132963989, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getDefinedThroughArrays().floorIndexOfX(0), 3, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(-1), 2, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(-20), 0, DELTA);

        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(-1), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(20), 20, DELTA);

        assertThrows(IllegalArgumentException.class, () -> getUnitArray().floorIndexOfX(0));
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().floorIndexOfX(6));
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().floorIndexOfX(10000));
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getDefinedThroughArrays().extrapolateLeft(-10), -3.75, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateLeft(9), 4.7368421052631575, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().extrapolateLeft(1));
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getDefinedThroughArrays().extrapolateRight(10.5), 6.375, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateRight(10), 100, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().extrapolateLeft(5));
    }

    @Test
    public void testInterpolate() {
        assertEquals(getDefinedThroughArrays().interpolate(0.5, getDefinedThroughArrays().floorIndexOfX(0.5)), 0.5, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(10, getDefinedThroughMathFunction().floorIndexOfX(10)), 100, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().interpolate(10, getUnitArray().floorIndexOfX(10)));
        assertThrows(InterpolationException.class, () -> getDefinedThroughArrays().interpolate(0, 1));
        assertThrows(InterpolationException.class, () -> getDefinedThroughMathFunction().interpolate(10, 19));
    }


    @Test
    public void testGetCount() {
        assertEquals(getDefinedThroughArrays().getCount(), 7);
        assertEquals(getDefinedThroughMathFunction().getCount(), 20);
    }

    @Test
    public void testGetX() {
        assertEquals(getDefinedThroughArrays().getX(0), -15);
        assertEquals(getDefinedThroughArrays().getX(4), 1);
        assertEquals(getDefinedThroughArrays().getX(5), 3);

        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(19), 10, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(18), 9.473684210526317, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getDefinedThroughArrays().getY(0), -5, DELTA);
        assertEquals(getDefinedThroughArrays().getY(4), 1, DELTA);
        assertEquals(getDefinedThroughArrays().getY(5), 2, DELTA);

        assertEquals(getDefinedThroughMathFunction().getY(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(6), 9.972299168975066, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(19), 100.00000000000004, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().getY(0));
    }

    @Test
    public void testSetY() {
        AbstractTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        AbstractTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();

        testDefinedThroughArrays.setY(5, 250);
        testDefinedThroughMathFunction.setY(0, 150);
        assertEquals(testDefinedThroughArrays.getY(5), 250);
        assertEquals(testDefinedThroughMathFunction.getY(0), 150);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getDefinedThroughArrays().indexOfX(1), 4, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfX(0.1), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(8), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(0.1), -1, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().indexOfX(8));
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().indexOfX(2));
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getDefinedThroughArrays().indexOfY(2), 5, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfY(0.1), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(1), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(0.1), -1, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().indexOfY(1));
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().indexOfY(0));
    }

    @Test
    public void testLeftBound() {
        assertEquals(getDefinedThroughArrays().leftBound(), -15, DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().leftBound());
    }

    @Test
    public void testRightBound() {
        assertEquals(getDefinedThroughArrays().rightBound(), 15, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 10, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().rightBound());
    }
}
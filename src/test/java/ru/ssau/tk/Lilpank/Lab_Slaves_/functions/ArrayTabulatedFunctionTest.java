package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.000001;
    private final double[] valuesX = new double[]{-15, -3, -1, 0, 1, 3, 15};
    private final double[] valuesY = new double[]{-5, -2, -1, -0, 1, 2, 9};
    private final MathFunction sqrFunc = new SqrFunction();

    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private ArrayTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(sqrFunc, 0, 10, 20);
    }

    private ArrayTabulatedFunction getUnitArray() {
        return new ArrayTabulatedFunction(sqrFunc, 6, 6, 1);
    }

    @Test
    public void testApply() {
        assertEquals(getDefinedThroughArrays().apply(-35), -10.0, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(-1.9), -0.9999999999999998, DELTA);
        assertEquals(getDefinedThroughArrays().apply(35), 20.666666666666668, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(10), 100, DELTA);
        assertEquals(getDefinedThroughArrays().apply(0.5), 0.5, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(0.125), 0.06578947368421052, DELTA);
        assertEquals(getUnitArray().apply(10), 10, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(8), 64.04432132963989, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getDefinedThroughArrays().floorIndexOfX(0), 3, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(-1), 2, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(-20), 0, DELTA);

        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(-1), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(20), 20, DELTA);

        assertEquals(getUnitArray().floorIndexOfX(0), 0, DELTA);
        assertEquals(getUnitArray().floorIndexOfX(6), 1, DELTA);
        assertEquals(getUnitArray().floorIndexOfX(10000), 1, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getDefinedThroughArrays().extrapolateLeft(-10), -3.75, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateLeft(9), 4.7368421052631575, DELTA);
        assertEquals(getUnitArray().extrapolateLeft(1), 1, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getDefinedThroughArrays().extrapolateRight(10.5), 6.375, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateRight(10), 100, DELTA);
        assertEquals(getUnitArray().extrapolateLeft(5), 5, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(getDefinedThroughArrays().interpolate(0.5, getDefinedThroughArrays().floorIndexOfX(0.5)), 0.5, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(10, getDefinedThroughMathFunction().floorIndexOfX(10)), 100, DELTA);
        assertEquals(getUnitArray().interpolate(10, getUnitArray().floorIndexOfX(10)), 10, DELTA);
    }


    @Test
    public void testGetCount() {
        assertEquals(getDefinedThroughArrays().getCount(), 7);
        assertEquals(getDefinedThroughMathFunction().getCount(), 20);
        assertEquals(getUnitArray().getCount(), 1);
    }

    @Test
    public void testGetX() {
        assertEquals(getDefinedThroughArrays().getX(0), -15);
        assertEquals(getDefinedThroughArrays().getX(4), 1);
        assertEquals(getDefinedThroughArrays().getX(5), 3);

        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(19), 10, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(18), 9.473684210526317, DELTA);

        assertEquals(getUnitArray().getX(0), 6, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getDefinedThroughArrays().getY(0), -5, DELTA);
        assertEquals(getDefinedThroughArrays().getY(4), 1, DELTA);
        assertEquals(getDefinedThroughArrays().getY(5), 2, DELTA);

        assertEquals(getDefinedThroughMathFunction().getY(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(6), 9.972299168975066, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(19), 100.00000000000004, DELTA);
        assertEquals(getUnitArray().getY(0), 36, DELTA);
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        ArrayTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();
        ArrayTabulatedFunction testUnitArray = getUnitArray();

        testDefinedThroughArrays.setY(5, 250);
        testDefinedThroughMathFunction.setY(0, 150);
        testUnitArray.setY(0, 9);
        assertEquals(testDefinedThroughArrays.getY(5), 250);
        assertEquals(testDefinedThroughMathFunction.getY(0), 150);
        assertEquals(testUnitArray.getY(0), 9, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getDefinedThroughArrays().indexOfX(1), 4, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfX(0.1), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(8), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(0.1), -1, DELTA);
        assertEquals(getUnitArray().indexOfX(8), -1, DELTA);
        assertEquals(getUnitArray().indexOfX(2), -1, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getDefinedThroughArrays().indexOfY(2), 5, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfY(0.1), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(1), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(0.1), -1, DELTA);
        assertEquals(getUnitArray().indexOfY(1), -1, DELTA);
        assertEquals(getUnitArray().indexOfY(0), -1, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getDefinedThroughArrays().leftBound(), -15, DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0, DELTA);
        assertEquals(getUnitArray().leftBound(), 6, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getDefinedThroughArrays().rightBound(), 15, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 10, DELTA);
        assertEquals(getUnitArray().rightBound(), 6, DELTA);
    }
}
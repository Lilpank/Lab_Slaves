package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {

    double[] valuesX = new double[]{-3., -2., -1, -0., 1., 2., 3., 4., 5.};
    double[] valuesY = new double[]{9., 4., 1., 0., 1., 4., 9., 16., 25.};
    private MathFunction sqrFunc = new SqrFunction();
    private ArrayTabulatedFunction definedThroughArrays = new ArrayTabulatedFunction(valuesX, valuesY);
    private ArrayTabulatedFunction definedThroughMathFunction = new ArrayTabulatedFunction(sqrFunc, 20, 0, 1000);
    private ArrayTabulatedFunction unitArray = new ArrayTabulatedFunction(sqrFunc, 10, 10, 1);

    @Test
    public void testFloorIndexOfX() {
        assertEquals(definedThroughArrays.floorIndexOfX(30.), 9);
        assertEquals(definedThroughArrays.floorIndexOfX(-4.), 0);
        for (int i = -2; i < 5; i++) {
            assertEquals(definedThroughArrays.floorIndexOfX(i - 0.5), i + 2);
        }
        assertEquals(definedThroughMathFunction.floorIndexOfX(-1.), 0);
        assertEquals(definedThroughMathFunction.floorIndexOfX(20.1), 1000);
        for (int i = 0; i < 999; i++) {
            assertEquals(definedThroughMathFunction.floorIndexOfX(20. * i / 999), i);
        }
        assertEquals(unitArray.floorIndexOfX(9.), 0);
        assertEquals(unitArray.floorIndexOfX(11.), 1);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(definedThroughArrays.extrapolateLeft(-5.), 19., 1E-6);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-1.), -0.0200200200200200200, 1E-6);
        assertEquals(unitArray.extrapolateLeft(5.), 5., 1E-6);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(definedThroughArrays.extrapolateRight(6.), 34., 1E-6);
        assertEquals(definedThroughMathFunction.extrapolateRight(20.), 400., 1E-6);
        assertEquals(unitArray.extrapolateRight(11.), 11., 1E-6);
    }

    @Test
    public void testInterpolate() {
        assertEquals(definedThroughArrays.interpolate(0.5, definedThroughArrays.floorIndexOfX(0.5)), 0.5, 1E-6);
        assertEquals(definedThroughMathFunction.interpolate(0.041, definedThroughMathFunction.floorIndexOfX(0.041)), 0.0016992968944920899, 1E-6);
        assertEquals(unitArray.interpolate(10., unitArray.floorIndexOfX(10.)), 10., 1E-6);
    }

    @Test
    public void testGetCount() {
        assertEquals(definedThroughArrays.getCount(), 9);
        assertEquals(definedThroughMathFunction.getCount(), 1000);
        assertEquals(unitArray.getCount(), 1);
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughArrays.getX(i), i - 3., 1E-6);
        }
        for (int i = 0; i < 1000; i++) {
        assertEquals(definedThroughMathFunction.getX(i), i * 20. / 999, 1E-6);
        }
        assertEquals(unitArray.getX(0), 10., 1E-6);
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughArrays.getY(i), Math.pow(i - 3., 2), 1E-6);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.pow(i * 20. / 999, 2), 1E-6);
        }
        assertEquals(unitArray.getY(0), 100., 1E-6);
    }

    @Test
    public void testSetY() {
        definedThroughArrays.setY(5, 100500.);
        definedThroughMathFunction.setY(0, 1009.);
        unitArray.setY(0, 9.);
        assertEquals(definedThroughArrays.getY(5), 100500., 1E-6);
        assertEquals(definedThroughMathFunction.getY(0), 1009., 1E-6);
        assertEquals(unitArray.getY(0), 9., 1E-6);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(definedThroughArrays.indexOfX(1.), 4);
        assertEquals(definedThroughArrays.indexOfX(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfX(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfX(0.1), -1);
        assertEquals(unitArray.indexOfX(10), 0);
        assertEquals(unitArray.indexOfX(11), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(definedThroughArrays.indexOfY(1.), 2);
        assertEquals(definedThroughArrays.indexOfY(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfY(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfY(0.1), -1);
        assertEquals(unitArray.indexOfY(100), 0);
        assertEquals(unitArray.indexOfY(101), -1);
    }

    @Test
    public void testLeftBound() {
        assertEquals(definedThroughArrays.leftBound(), -3., 1E-6);
        assertEquals(definedThroughMathFunction.leftBound(), 0., 1E-6);
        assertEquals(unitArray.leftBound(), 10., 1E-6);
    }

    @Test
    public void testRightBound() {
        assertEquals(definedThroughArrays.rightBound(), 5., 1E-6);
        assertEquals(definedThroughMathFunction.rightBound(), 20., 1E-6);
        assertEquals(unitArray.rightBound(), 10., 1E-6);
    }
}

package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{10, 20, 30, 40, 50};
    private final MathFunction sqrTestFunction = new SqrFunction();
    private final double DELTA = 0.00001;

    private LinkedListTabulatedFunction getListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListOfMathFunction() {
        return new LinkedListTabulatedFunction(sqrTestFunction, 5, 10, 20);
    }

    @Test
    public void testApply() {
        assertEquals(getListOfArray().apply(3), 30, DELTA);
        assertEquals(getListOfMathFunction().apply(5), 25, DELTA);

        assertEquals(getListOfArray().apply(7), 70, DELTA);
        assertEquals(getListOfMathFunction().apply(10), 100, DELTA);

        assertEquals(getListOfArray().apply(0.5), 5, DELTA);
        assertEquals(getListOfMathFunction().apply(8), 64.01662049861496, DELTA);
    }

    @Test
    public void testGetNode() {
        assertEquals(getListOfArray().getX(0), 1, DELTA);
        assertEquals(getListOfArray().getX(1), 2, DELTA);
        assertEquals(getListOfArray().getX(2), 3, DELTA);
        assertEquals(getListOfArray().getX(3), 4, DELTA);
        assertEquals(getListOfArray().getX(4), 5, DELTA);

    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction testList = getListOfArray();
        testList.addNode(6, 60);
        assertEquals(testList.rightBound(), 6, DELTA);
    }

    @Test
    public void testGetCount() {
        assertEquals(getListOfArray().getCount(), 5, DELTA);
        assertEquals(getListOfMathFunction().getCount(), 20, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getListOfArray().leftBound(), 1, DELTA);
        assertEquals(getListOfMathFunction().leftBound(), 5, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getListOfArray().rightBound(), 5, DELTA);
        assertEquals(getListOfMathFunction().rightBound(), 10, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(getListOfArray().getX(0), 1, DELTA);
        assertEquals(getListOfMathFunction().getX(0), 5, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getListOfArray().getY(0), 10, DELTA);
        assertEquals(getListOfMathFunction().getY(0), 25, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        testListArray.setY(4, 60);
        assertEquals(testListArray.getY(4), 60, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getListOfArray().indexOfX(5), 4);
        assertEquals(getListOfMathFunction().indexOfX(5), 0);
        assertEquals(getListOfArray().indexOfX(100), -1);

    }

    @Test
    public void testIndexOfY() {

        assertEquals(getListOfArray().indexOfY(10), 0);
        assertEquals(getListOfMathFunction().indexOfY(5), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getListOfArray().floorIndexOfX(3.7), 2);
        assertEquals(getListOfMathFunction().floorIndexOfX(-10), 0);
        assertEquals(getListOfMathFunction().floorIndexOfX(100), 20);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListOfArray().extrapolateLeft(1.5), 15, DELTA);
        assertEquals(getListOfMathFunction().extrapolateLeft(5), 25, DELTA);
    }

    @Test
    public void testExtrapolateRight() {

        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.extrapolateRight(8), 80, DELTA);
        assertEquals(testListMath.extrapolateRight(10), 100, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.interpolate(2.5, 2), 25);
        assertEquals(testListMath.interpolate(5.07018, 3), 25.00005401662049, DELTA);
    }
}

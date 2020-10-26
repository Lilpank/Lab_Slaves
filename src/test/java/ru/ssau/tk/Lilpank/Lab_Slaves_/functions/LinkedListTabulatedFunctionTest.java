package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import exceptions.InterpolationException;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{10, 20, 30, 40, 50};
    private final MathFunction sqrTestFunction = new SqrFunction();
    private final double DELTA = 0.00001;

    private AbstractTabulatedFunction getListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private AbstractTabulatedFunction getListOfMathFunction() {
        return new LinkedListTabulatedFunction(sqrTestFunction, 5, 10, 20);
    }

    @Test
    public void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().getX(-1));
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().getX(-6));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new double[]{1, 2}, new double[]{1, 2}));
    }

    @Test
    public void testIteratorWhile() {
        AbstractTabulatedFunction tempArrayList = getListOfArray();
        Iterator<Point> tempIterator = tempArrayList.iterator();

        int i = 0;
        while (tempIterator.hasNext()) {
            Point myPoint = tempIterator.next();
            assertEquals(tempArrayList.getX(i), myPoint.x);
            assertEquals(tempArrayList.getY(i++), myPoint.y);

        }
        assertEquals(tempArrayList.getCount(), i);

        assertThrows(NoSuchElementException.class, tempIterator::next);
    }

    @Test
    public void testIteratorFor_each() {
        AbstractTabulatedFunction tempArrayList = getListOfArray();
        int i = 0;
        for (Point point : tempArrayList) {
            assertEquals(point.x, tempArrayList.getX(i), DELTA);
            assertEquals(point.y, tempArrayList.getY(i++), DELTA);
        }
        assertEquals(tempArrayList.getCount(), i);
    }

    @Test
    public void testApply() {
        assertEquals(getListOfArray().apply(3), 30, DELTA);
        assertEquals(getListOfMathFunction().apply(5), 25, DELTA);

        assertEquals(getListOfArray().apply(7), 70, DELTA);
        assertEquals(getListOfMathFunction().apply(10), 100, DELTA);

        assertEquals(getListOfArray().apply(0.5), 5, DELTA);
        assertEquals(getListOfMathFunction().apply(8), 64.01662049861496, DELTA);

        assertEquals(getListOfArray().apply(-1), -10, DELTA);
        assertEquals(getListOfMathFunction().apply(-7), -98.15789473684218, DELTA);

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
        LinkedListTabulatedFunction testList = (LinkedListTabulatedFunction) getListOfArray();
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
        LinkedListTabulatedFunction testListArray = (LinkedListTabulatedFunction) getListOfArray();
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
        assertEquals(getListOfMathFunction().floorIndexOfX(100), 20);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListOfArray().extrapolateLeft(1.5), 15, DELTA);
        assertEquals(getListOfMathFunction().extrapolateLeft(5), 25, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction testListArray = (LinkedListTabulatedFunction) getListOfArray();
        LinkedListTabulatedFunction testListMath = (LinkedListTabulatedFunction) getListOfMathFunction();
        assertEquals(testListArray.extrapolateRight(8), 80, DELTA);
        assertEquals(testListMath.extrapolateRight(10), 100, DELTA);
    }

    @Test
    public void testInterpolationException() {
        LinkedListTabulatedFunction testListArray = (LinkedListTabulatedFunction) getListOfArray();
        LinkedListTabulatedFunction testListMath = (LinkedListTabulatedFunction) getListOfMathFunction();

        assertThrows(InterpolationException.class, () -> testListArray.interpolate(2.5, 2));
        assertThrows(InterpolationException.class, () -> testListMath.interpolate(2.1, 3));
        assertThrows(InterpolationException.class, () -> testListArray.interpolate(-2, 0));
        assertThrows(InterpolationException.class, () -> testListMath.interpolate(1., 0));
    }
}

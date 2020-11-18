package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.Point;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double[] yValues = new double[]{10, 20, 30, 40, 50, 60, 70};
    private final Object object = new Object();
    private final double DELTA = 0.00001;

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), object);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), object);
    }

    @Test
    public void testGetCount() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getCount(), 7, DELTA);
        assertEquals(synchronizedArr.getCount(), 7, DELTA);
    }

    @Test
    public void testGetX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getX(0) , 1, DELTA);
        assertEquals(synchronizedTabulatedFunction.getX(2), 3, DELTA);
        assertEquals(synchronizedTabulatedFunction.getX(5), 6, DELTA);

        assertEquals(synchronizedArr.getX(1), 2, DELTA);
        assertEquals(synchronizedArr.getX(3), 4, DELTA);
        assertEquals(synchronizedArr.getX(6), 7, DELTA);
    }

    @Test
    public void testGetY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getY(0) , 10, DELTA);
        assertEquals(synchronizedTabulatedFunction.getY(2), 30, DELTA);
        assertEquals(synchronizedTabulatedFunction.getY(5), 60, DELTA);

        assertEquals(synchronizedArr.getY(1), 20, DELTA);
        assertEquals(synchronizedArr.getY(3), 40, DELTA);
        assertEquals(synchronizedArr.getY(6), 70, DELTA);
    }

    @Test
    public void testIteratorWhile() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        Iterator<Point> testIterator = synchronizedTabulatedFunction.iterator();

        int i = 0;
        while (testIterator.hasNext()) {
            Point myPoint = testIterator.next();
            assertEquals(synchronizedTabulatedFunction.getX(i), myPoint.x);
            assertEquals(synchronizedTabulatedFunction.getY(i++), myPoint.y);
        }
        assertEquals(synchronizedTabulatedFunction.getCount(), i);

        assertThrows(NoSuchElementException.class, testIterator::next);
    }

    @Test
    public void testIteratorForEach() {
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        int i = 0;
        for (Point point : synchronizedArr) {
            assertEquals(point.x, synchronizedArr.getX(i), DELTA);
            assertEquals(point.y, synchronizedArr.getY(i++), DELTA);
        }
        assertEquals(synchronizedArr.getCount(), i);
    }

    @Test
    public void testSetY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        synchronizedTabulatedFunction.setY(0, 11);
        assertEquals(synchronizedTabulatedFunction.getY(0) , 11, DELTA);
        synchronizedTabulatedFunction.setY(3, 44);
        assertEquals(synchronizedTabulatedFunction.getY(3) , 44, DELTA);
        synchronizedTabulatedFunction.setY(6, 77);
        assertEquals(synchronizedTabulatedFunction.getY(6) , 77, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        synchronizedArr.setY(2, 33);
        assertEquals(synchronizedArr.getY(2) , 33, DELTA);
        synchronizedArr.setY(4, 55);
        assertEquals(synchronizedArr.getY(4) , 55, DELTA);
        synchronizedArr.setY(5, 66);
        assertEquals(synchronizedArr.getY(5) , 66, DELTA);
    }

    @Test
    public void testIndexOfX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.indexOfX(1), 0, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfX(3), 2, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfX(6), 5, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.indexOfX(2), 1, DELTA);
        assertEquals(synchronizedArr.indexOfX(4), 3, DELTA);
        assertEquals(synchronizedArr.indexOfX(7), 6, DELTA);
    }

    @Test
    public void testIndexOfY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.indexOfY(10), 0, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfY(30), 2, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfY(60), 5, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.indexOfY(20), 1, DELTA);
        assertEquals(synchronizedArr.indexOfY(40), 3, DELTA);
        assertEquals(synchronizedArr.indexOfY(70), 6, DELTA);
    }

    @Test
    public void testLeftBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.leftBound(), 1, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.rightBound(), 7, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.rightBound(), 7, DELTA);
    }

    @Test
    public void testApply() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.apply(3), 30, DELTA);
        assertEquals(synchronizedTabulatedFunction.apply(-7), -70, DELTA);
        assertEquals(synchronizedTabulatedFunction.apply(0), 0, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.apply(0), 0, DELTA);
        assertEquals(synchronizedArr.apply(8), 80, DELTA);
        assertEquals(synchronizedArr.apply(-4), -40, DELTA);
    }
}
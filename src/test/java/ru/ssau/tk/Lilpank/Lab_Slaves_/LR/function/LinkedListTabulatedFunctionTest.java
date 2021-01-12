package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions.InterpolationException;
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
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new double[]{1}, new double[]{2}));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqrTestFunction, 10, 2, 10));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqrTestFunction, 300, 200, -5));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqrTestFunction, 2452, 5, 100000));
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
    public void testIteratorForEach() {
        AbstractTabulatedFunction tempArrayList = getListOfArray();
        int i = 0;
        for (Point point : tempArrayList) {
            assertEquals(point.x, tempArrayList.getX(i), DELTA);
            assertEquals(point.y, tempArrayList.getY(i++), DELTA);
        }
        assertEquals(tempArrayList.getCount(), i);
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
        assertEquals(getListOfMathFunction().getX(0), 5, DELTA); }

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

    @Test
    public void testInsert() {
        LinkedListTabulatedFunction testInsertLinkedList = (LinkedListTabulatedFunction) getListOfArray();

        testInsertLinkedList.insert(0, 0);
        testInsertLinkedList.insert(2.5, 25);
        testInsertLinkedList.insert(6, 60);

        for (int i = 0; i < 3; i++) {
            assertEquals(testInsertLinkedList.getX(i), i, DELTA);
            assertEquals(testInsertLinkedList.getY(i), 10 * i, DELTA);
        }
        assertEquals(testInsertLinkedList.getX(3), 2.5, DELTA);
        assertEquals(testInsertLinkedList.getY(3), 25, DELTA);
        for (int i = 4; i < 8; i++) {
            assertEquals(testInsertLinkedList.getX(i), i - 1, DELTA);
            assertEquals(testInsertLinkedList.getY(i), 10 * (i - 1), DELTA);
        }

        testInsertLinkedList.insert(4, 1);
        assertEquals(testInsertLinkedList.getX(5), 4, DELTA);
        assertEquals(testInsertLinkedList.getY(5), 1, DELTA);
        assertEquals(testInsertLinkedList.getCount(), 8, DELTA);

        LinkedListTabulatedFunction testInsertLinkedList2 = (LinkedListTabulatedFunction) getListOfMathFunction();

        testInsertLinkedList2.insert(0, 0);
        assertEquals(testInsertLinkedList2.getCount(), 21, DELTA);
        assertEquals(testInsertLinkedList2.getX(0), 0, DELTA);
        for (int i = 1; i < 21; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + i * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + i * 0.2631578947368424, 2), DELTA);
        }

        testInsertLinkedList2.insert(7, 49);
        assertEquals(testInsertLinkedList2.getCount(), 22, DELTA);
        assertEquals(testInsertLinkedList2.getX(0), 0, DELTA);
        for (int i = 1; i < 8; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + i * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + i * 0.2631578947368424, 2), DELTA);
        }
        assertEquals(testInsertLinkedList2.getX(9), 7, DELTA);
        assertEquals(testInsertLinkedList2.getY(9), 49, DELTA);
        for (int i = 10; i < 22; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + (i - 1) * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + (i - 1) * 0.2631578947368424, 2), DELTA);
        }

        testInsertLinkedList2.insert(10.2631578947368424, Math.pow(10.2631578947368424, 2));
        assertEquals(testInsertLinkedList2.getCount(), 23, DELTA);
        assertEquals(testInsertLinkedList2.getX(0), 0, DELTA);
        for (int i = 1; i < 9; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + i * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + i * 0.2631578947368424, 2), DELTA);
        }
        assertEquals(testInsertLinkedList2.getX(9), 7, DELTA);
        assertEquals(testInsertLinkedList2.getY(9), 49, DELTA);
        for (int i = 10; i < 23; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + (i - 1) * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + (i - 1) * 0.2631578947368424, 2), DELTA);
        }

        testInsertLinkedList2.insert(8.15789473684211, 0);
        assertEquals(testInsertLinkedList2.getCount(), 23, DELTA);
        assertEquals(testInsertLinkedList2.getX(0), 0, DELTA);
        for (int i = 1; i < 9; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + i * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + i * 0.2631578947368424, 2), DELTA);
        }
        assertEquals(testInsertLinkedList2.getX(9), 7, DELTA);
        assertEquals(testInsertLinkedList2.getY(9), 49, DELTA);
        for (int i = 10; i < 14; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + (i - 1) * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + (i - 1) * 0.2631578947368424, 2), DELTA);
        }
        assertEquals(testInsertLinkedList2.getX(14), 8.15789473684211, DELTA);
        assertEquals(testInsertLinkedList2.getY(14), 0, DELTA);
        for (int i = 15; i < 23; i++) {
            assertEquals(testInsertLinkedList2.getX(i), 4.7368421052631579 + (i - 1) * 0.2631578947368424, DELTA);
            assertEquals(testInsertLinkedList2.getY(i), Math.pow(4.7368421052631579 + (i - 1) * 0.2631578947368424, 2), DELTA);
        }
    }

    @Test
    public void testRemove() {
        LinkedListTabulatedFunction testRemoveLinkedListFirst = (LinkedListTabulatedFunction) getListOfArray();

        testRemoveLinkedListFirst.remove(0);
        assertEquals(testRemoveLinkedListFirst.getCount(), 4, DELTA);
        for (int i = 0; i < 4; i++) {
            assertEquals(testRemoveLinkedListFirst.getX(i), i + 2, DELTA);
            assertEquals(testRemoveLinkedListFirst.getY(i), 10 * (i + 2), DELTA);
        }
        testRemoveLinkedListFirst.remove(1);
        assertEquals(testRemoveLinkedListFirst.getCount(), 3, DELTA);
        assertEquals(testRemoveLinkedListFirst.getX(0), 2, DELTA);
        assertEquals(testRemoveLinkedListFirst.getY(0), 20, DELTA);
        assertEquals(testRemoveLinkedListFirst.getX(1), 4, DELTA);
        assertEquals(testRemoveLinkedListFirst.getY(1), 40, DELTA);
        assertEquals(testRemoveLinkedListFirst.getX(2), 5, DELTA);
        assertEquals(testRemoveLinkedListFirst.getY(2), 50, DELTA);

        testRemoveLinkedListFirst.remove(2);
        assertEquals(testRemoveLinkedListFirst.getCount(), 2, DELTA);
        assertEquals(testRemoveLinkedListFirst.getX(0), 2, DELTA);
        assertEquals(testRemoveLinkedListFirst.getY(0), 20, DELTA);
        assertEquals(testRemoveLinkedListFirst.getX(1), 4, DELTA);
        assertEquals(testRemoveLinkedListFirst.getY(1), 40, DELTA);

        LinkedListTabulatedFunction testRemoveLinkedListSecond = (LinkedListTabulatedFunction) getListOfMathFunction();

        testRemoveLinkedListSecond.remove(0);
        assertEquals(testRemoveLinkedListSecond.getCount(), 19, DELTA);
        for (int i = 0; i < 19; i++) {
            assertEquals(testRemoveLinkedListSecond.getX(i), 5.2631578947368424 + i * 0.2631578947368424, DELTA);
            assertEquals(testRemoveLinkedListSecond.getY(i), Math.pow(5.2631578947368424 + i * 0.2631578947368424, 2), DELTA);
        }

        testRemoveLinkedListSecond.remove(18);
        assertEquals(testRemoveLinkedListSecond.getCount(), 18, DELTA);
        for (int i = 0; i < 18; i++) {
            assertEquals(testRemoveLinkedListSecond.getX(i), 5.2631578947368424 + i * 0.2631578947368424, DELTA);
            assertEquals(testRemoveLinkedListSecond.getY(i), Math.pow(5.2631578947368424 + i * 0.2631578947368424, 2), DELTA);
        }

        testRemoveLinkedListSecond.remove(10);
        assertEquals(testRemoveLinkedListSecond.getCount(), 17, DELTA);
        for (int i = 0; i < 10; i++) {
            assertEquals(testRemoveLinkedListSecond.getX(i), 5.2631578947368424 + i * 0.2631578947368424, DELTA);
            assertEquals(testRemoveLinkedListSecond.getY(i), Math.pow(5.2631578947368424 + i * 0.2631578947368424, 2), DELTA);
        }
        for (int i = 10; i < 17; i++) {
            assertEquals(testRemoveLinkedListSecond.getX(i), 8.15789473684211 + (i - 10) * 0.2631578947368424, DELTA);
            assertEquals(testRemoveLinkedListSecond.getY(i), Math.pow(8.15789473684211 + (i - 10) * 0.2631578947368424, 2), DELTA);
        }
    }


    @Test
    public void testTestApply() {
        LinkedListTabulatedFunction testApplyLinkedListFirst = (LinkedListTabulatedFunction) getListOfArray();

        assertEquals(testApplyLinkedListFirst.apply(-10), -100, DELTA);
        assertEquals(testApplyLinkedListFirst.apply(2.5), 25, DELTA);
        assertEquals(testApplyLinkedListFirst.apply(10), 100, DELTA);

        LinkedListTabulatedFunction testApplyLinkedListSecond = (LinkedListTabulatedFunction) getListOfMathFunction();

        assertEquals(testApplyLinkedListSecond.apply(0), -26.31578947368424, DELTA);
        assertEquals(testApplyLinkedListSecond.apply(3), 4.473684210526304, DELTA);
        assertEquals(testApplyLinkedListSecond.apply(7.5), 56.267313019390585, DELTA);
        assertEquals(testApplyLinkedListSecond.apply(15), 198.68421052631584, DELTA);
    }
}

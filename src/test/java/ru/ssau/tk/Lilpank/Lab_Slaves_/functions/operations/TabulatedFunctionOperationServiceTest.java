package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.operations;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.exceptions.InconsistentFunctionsException;
import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.Point;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.operations.TabulatedFunctionOperationService;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    private final static double DELTA = 0.000001;
    private final double[] xValues = new double[]{-3, -2, -1, 0, 1, 2, 3};
    private final double[] yValuesArray = new double[]{-9, -4, -1, 0, 1, 4, 9};
    private final double[] yValuesList = new double[]{1, 2, 3, 4, 5, 6, 7};
    TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();

    ArrayTabulatedFunction testArray() {
        return new ArrayTabulatedFunction(xValues, yValuesArray);
    }

    LinkedListTabulatedFunction testList() {
        return new LinkedListTabulatedFunction(xValues, yValuesList);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction testArrayFunction = testArray();
        Point[] Dots = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Dots) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), DELTA);
        }
        assertEquals(testArrayFunction.getCount(), i);

        TabulatedFunction testListFunction = testList();
        Dots = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Dots) {
            assertEquals(myPoint.x, testListFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testListFunction.getY(i++), DELTA);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testGetFactory() {
        assertTrue(operationService.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        operationService.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(operationService.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        TabulatedFunction testArrayFunction = testArray();
        TabulatedFunction testListFunction = testList();

        final double[] xValuesErr1 = new double[]{-3, -2, -1, 0, 1, 2};
        final double[] yValuesErr1 = new double[]{-9, -4, -1, 0, 1, 4};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(xValuesErr1, yValuesErr1);
        assertThrows(InconsistentFunctionsException.class, () -> operationService.sum(testListFunction, errorTest1));

        final double[] xValuesErr2 = new double[]{-4, -2, -1, 0, 1, 2, 3};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(xValuesErr2, yValuesArray);
        assertThrows(InconsistentFunctionsException.class, () -> operationService.sum(testListFunction, errorTest2));

        TabulatedFunction testSumOfArrays = operationService.sum(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] + yValuesArray[i++]);
        }

        TabulatedFunction testSumOfLists = operationService.sum(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] + yValuesList[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = operationService.sum(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] + yValuesList[i++]);
        }

    }

    @Test
    public void testSubtract() {
        TabulatedFunction testArrayFunction = testArray();
        TabulatedFunction testListFunction = testList();

        TabulatedFunction testSubtractOfArrays = operationService.subtract(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] - yValuesArray[i++]);
        }

        TabulatedFunction testSubtractOfLists = operationService.subtract(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] - yValuesList[i++]);
        }

        TabulatedFunction testSubtractOfArrayAndList = operationService.subtract(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] - yValuesList[i++]);
        }

    }

    @Test
    public void testMultiplication() {
        TabulatedFunction testArrayFunction = testArray();
        TabulatedFunction testListFunction = testList();

        TabulatedFunction testMultiplicationOfArrays = operationService.multiplication(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testMultiplicationOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] * yValuesArray[i++]);
        }

        TabulatedFunction testMultiplicationOfLists = operationService.multiplication(testListFunction, testListFunction);
        i = 0;
        for (Point point : testMultiplicationOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] * yValuesList[i++]);
        }

        TabulatedFunction testMultiplicationOfArrayAndList = operationService.multiplication(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testMultiplicationOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] * yValuesList[i++]);
        }

    }

    @Test
    public void testDivision() {
        TabulatedFunction testArrayFunction = testArray();
        TabulatedFunction testListFunction = testList();

        TabulatedFunction testDivisionOfArrays = operationService.division(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testDivisionOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] / yValuesArray[i++]);
        }

        TabulatedFunction testDivisionOfLists = operationService.division(testListFunction, testListFunction);
        i = 0;
        for (Point point : testDivisionOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] / yValuesList[i++]);
        }

        TabulatedFunction testDivisionOfArrayAndList = operationService.division(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testDivisionOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] / yValuesList[i++]);
        }

    }
}
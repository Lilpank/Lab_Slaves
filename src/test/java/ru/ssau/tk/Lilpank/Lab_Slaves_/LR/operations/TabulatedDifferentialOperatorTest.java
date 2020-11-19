package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {
    @Test
    public void testDerive() {
        TabulatedFunction tempList = new LinkedListTabulatedFunction(new double[]{5, 6, 7, 8, 9}, new double[]{25, 36, 49, 64, 81});
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        tempList = differentialListOperator.derive(tempList);
        assertTrue(tempList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < tempList.getCount(); i++) {
            assertEquals(tempList.getX(i), (5 + i));
        }

        assertEquals(tempList.getY(0), 11);
        assertEquals(tempList.getY(1), 13);
        assertEquals(tempList.getY(2), 15);
        assertEquals(tempList.getY(3), 17);
        assertEquals(tempList.getY(4), 17);


        TabulatedFunction tempArray = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5, 6}, new double[]{10, 50, 80, 160, 250, 360});
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        tempArray = differentialArrayOperator.derive(tempArray);
        assertTrue(tempArray instanceof ArrayTabulatedFunction);
        for (int i = 0; i < tempArray.getCount(); i++) {
            assertEquals(tempArray.getX(i), i + 1);
        }

        assertEquals(tempArray.getY(0), 40);
        assertEquals(tempArray.getY(1), 30);
        assertEquals(tempArray.getY(2), 80);
        assertEquals(tempArray.getY(3), 90);
        assertEquals(tempArray.getY(4), 110);
        assertEquals(tempArray.getY(5), 110);
    }

    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{10, 11, 12, 13, 14}, new double[]{100, 121, 144, 169, 196});
        TabulatedDifferentialOperator differentialOperatorList = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionList = differentialOperatorList.deriveSynchronously(linkedListTabulatedFunction);
        assertTrue(differentialFunctionList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < differentialFunctionList.getCount(); i++) {
            assertEquals(differentialFunctionList.getX(i), (10 + i));
        }

        assertEquals(differentialFunctionList.getY(0), 21);
        assertEquals(differentialFunctionList.getY(1), 23);
        assertEquals(differentialFunctionList.getY(2), 25);
        assertEquals(differentialFunctionList.getY(3), 27);
        assertEquals(differentialFunctionList.getY(4), 27);

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{7, 8, 9, 10, 11, 12}, new double[]{490, 640, 820, 1000, 1210, 1440});
        TabulatedDifferentialOperator differentialOperatorArray = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionArray = differentialOperatorArray.deriveSynchronously(arrayTabulatedFunction);
        assertTrue(differentialFunctionArray instanceof ArrayTabulatedFunction);

        for (int i = 0; i < differentialFunctionArray.getCount(); i++) {
            assertEquals(differentialFunctionArray.getX(i), (7 + i));
        }

        assertEquals(differentialFunctionArray.getY(0), 150);
        assertEquals(differentialFunctionArray.getY(1), 180);
        assertEquals(differentialFunctionArray.getY(2), 180);
        assertEquals(differentialFunctionArray.getY(3), 210);
        assertEquals(differentialFunctionArray.getY(4), 230);
        assertEquals(differentialFunctionArray.getY(5), 230);
    }
}

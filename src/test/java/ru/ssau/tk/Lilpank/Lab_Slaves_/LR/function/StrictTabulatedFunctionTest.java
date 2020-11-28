package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class StrictTabulatedFunctionTest {
    LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});

    @Test
    public void testGetCount() {
        assertEquals(new StrictTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{4, 5}, new double[]{4, 5})
                ).getCount(),
                2
        );
        assertEquals(new StrictTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4})
                ).getCount(),
                4
        );
        assertEquals(
                new StrictTabulatedFunction(
                        new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5})
                ).getCount(),
                5
        );
    }

    @Test
    public void testGetX() {
        assertEquals(new StrictTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5})
                ).getX(2),
                3
        );
    }

    @Test
    public void testGetY() {
        assertEquals(new StrictTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5})
                ).getY(2),
                3
        );
    }

    @Test
    public void testSetY() {
        StrictTabulatedFunction tempStrict = new StrictTabulatedFunction(arrayTabulatedFunction);
        tempStrict.setY(1,15);
        assertEquals(tempStrict.getY(1),15);
    }

    @Test
    public void testIndexOfX() {
        StrictTabulatedFunction tempStrict = new StrictTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(tempStrict.indexOfX(1),1);
    }

    @Test
    public void testIndexOfY() {
        StrictTabulatedFunction tempStrict = new StrictTabulatedFunction(arrayTabulatedFunction);
        assertEquals(tempStrict.indexOfY(1),1);
    }

    @Test
    public void testLeftBound() {
        StrictTabulatedFunction tempStrict = new StrictTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(tempStrict.leftBound(),1);
    }

    @Test
    public void testRightBound() {
        StrictTabulatedFunction tempStrict = new StrictTabulatedFunction(arrayTabulatedFunction);
        assertEquals(tempStrict.rightBound(),3);
    }

    @Test
    public void testApply() {
        StrictTabulatedFunction tempStrict = new StrictTabulatedFunction(linkedListTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> tempStrict.apply(5));
        assertThrows(UnsupportedOperationException.class, () -> tempStrict.apply(-1));
    }
}
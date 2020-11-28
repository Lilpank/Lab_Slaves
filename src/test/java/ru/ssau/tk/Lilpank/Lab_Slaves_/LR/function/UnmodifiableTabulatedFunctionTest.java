package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnmodifiableTabulatedFunctionTest {
    LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(
            new double[]{1, 2, 3},
            new double[]{1, 2, 3}
    );
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(
            new double[]{1, 2, 3},
            new double[]{1, 2, 3}
    );

    @Test
    public void testGetCount() {
        assertEquals(new UnmodifiableTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{4, 5}, new double[]{4, 5})
                ).getCount(),
                2
        );
        assertEquals(new UnmodifiableTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4})
                ).getCount(),
                4
        );
        assertEquals(
                new UnmodifiableTabulatedFunction(
                        new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5})
                ).getCount(),
                5
        );
    }

    @Test
    public void testGetX() {
        assertEquals(new UnmodifiableTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5})
                ).getX(2),
                3
        );
    }

    @Test
    public void testGetY() {
        assertEquals(new UnmodifiableTabulatedFunction(
                        new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5})
                ).getY(2),
                3
        );
    }

    @Test
    public void testSetY() {
        UnmodifiableTabulatedFunction tempStrict = new UnmodifiableTabulatedFunction(arrayTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> tempStrict.setY(1, 1));
    }

    @Test
    public void testIndexOfX() {
        UnmodifiableTabulatedFunction tempStrict = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(tempStrict.indexOfX(1), 0);
    }

    @Test
    public void testIndexOfY() {
        UnmodifiableTabulatedFunction tempStrict = new UnmodifiableTabulatedFunction(arrayTabulatedFunction);
        assertEquals(tempStrict.indexOfY(1), 0);
    }

    @Test
    public void testLeftBound() {
        UnmodifiableTabulatedFunction tempStrict = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(tempStrict.leftBound(), 1);
    }

    @Test
    public void testRightBound() {
        UnmodifiableTabulatedFunction tempStrict = new UnmodifiableTabulatedFunction(arrayTabulatedFunction);
        assertEquals(tempStrict.rightBound(), 3);
    }

    @Test
    public void testApply() {
        UnmodifiableTabulatedFunction tempStrict = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(tempStrict.apply(1), 1);
        assertEquals(tempStrict.apply(2), 2);
    }
}
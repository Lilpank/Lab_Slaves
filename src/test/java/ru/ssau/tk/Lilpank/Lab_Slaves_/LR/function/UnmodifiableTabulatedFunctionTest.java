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
        UnmodifiableTabulatedFunction unmodifiableTabulatedFunction = new UnmodifiableTabulatedFunction(arrayTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableTabulatedFunction.setY(1, 1));
    }

    @Test
    public void testIndexOfX() {
        UnmodifiableTabulatedFunction unmodifiableTabulatedFunction = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(unmodifiableTabulatedFunction.indexOfX(1), 0);
    }

    @Test
    public void testIndexOfY() {
        UnmodifiableTabulatedFunction unmodifiableTabulatedFunction = new UnmodifiableTabulatedFunction(arrayTabulatedFunction);
        assertEquals(unmodifiableTabulatedFunction.indexOfY(1), 0);
    }

    @Test
    public void testLeftBound() {
        UnmodifiableTabulatedFunction unmodifiableTabulatedFunction = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(unmodifiableTabulatedFunction.leftBound(), 1);
    }

    @Test
    public void testRightBound() {
        UnmodifiableTabulatedFunction unmodifiableTabulatedFunction = new UnmodifiableTabulatedFunction(arrayTabulatedFunction);
        assertEquals(unmodifiableTabulatedFunction.rightBound(), 3);
    }

    @Test
    public void testApply() {
        UnmodifiableTabulatedFunction unmodifiableTabulatedFunction = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);
        assertEquals(unmodifiableTabulatedFunction.apply(1), 1);
        assertEquals(unmodifiableTabulatedFunction.apply(2), 2);
    }

    @Test
    public void UnmodifiableTabulatedFunctionToWrapStrictTabulatedFunction() {
        UnmodifiableTabulatedFunction unmodifiableTabulatedFunction = new UnmodifiableTabulatedFunction(new StrictTabulatedFunction(linkedListTabulatedFunction));

        assertThrows(UnsupportedOperationException.class, () -> unmodifiableTabulatedFunction.setY(1, 1));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableTabulatedFunction.setY(2, 1));
    }
}
package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory;


import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.*;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        ArrayTabulatedFunctionFactory temp = new ArrayTabulatedFunctionFactory();
        assertTrue(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof ArrayTabulatedFunction);
        assertFalse(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof ArrayTabulatedFunctionFactory);
    }
    @Test
    public void testTabulatedFunctionFactoryCrateStrict() {
        ArrayTabulatedFunctionFactory tempFactory = new ArrayTabulatedFunctionFactory();
        assertEquals(tempFactory.createStrict(new double[]{1, 2, 3}, new double[]{10, 2, 3}).getCount(),3);
        assertTrue(tempFactory.createStrict(new double[]{1, 2, 3}, new double[]{10, 2, 3}) instanceof StrictTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> tempFactory.createStrict(new double[]{1, 2, 3}, new double[]{10, 2, 3}).apply(5));
    }

    @Test
    public void testTabulatedFunctionFactoryCreateUnmodifiable() {
        ArrayTabulatedFunctionFactory tempFactory = new ArrayTabulatedFunctionFactory();
        assertEquals(tempFactory.createUnmodifiable(new double[]{1, 2, 3}, new double[]{10, 2, 3}).getCount(), 3);
        assertTrue(tempFactory.createUnmodifiable(new double[]{1, 2, 3}, new double[]{10, 2, 3}) instanceof UnmodifiableTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> tempFactory.createUnmodifiable(new double[]{1, 2, 3}, new double[]{10, 2, 3}).setY(1, 5));
    }
}

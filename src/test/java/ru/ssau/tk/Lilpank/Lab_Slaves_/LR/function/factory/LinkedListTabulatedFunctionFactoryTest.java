package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.StrictTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.UnmodifiableTabulatedFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        LinkedListTabulatedFunctionFactory temp = new LinkedListTabulatedFunctionFactory();
        assertTrue(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof LinkedListTabulatedFunction);
        assertFalse(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testTabulatedFunctionFactoryCrateStrict() {
        LinkedListTabulatedFunctionFactory tempFactory = new LinkedListTabulatedFunctionFactory();
        assertEquals(tempFactory.createStrict(new double[]{1, 2, 3}, new double[]{10, 2, 3}).getCount(),3);
        assertTrue(tempFactory.createStrict(new double[]{1, 2, 3}, new double[]{10, 2, 3}) instanceof StrictTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> tempFactory.createStrict(new double[]{1, 2, 3}, new double[]{10, 2, 3}).apply(5));
    }

    @Test
    public void testTabulatedFunctionFactoryCreateUnmodifiable() {
        LinkedListTabulatedFunctionFactory tempFactory = new LinkedListTabulatedFunctionFactory();
        assertEquals(tempFactory.createUnmodifiable(new double[]{1, 2, 3}, new double[]{10, 2, 3}).getCount(), 3);
        assertTrue(tempFactory.createUnmodifiable(new double[]{1, 2, 3}, new double[]{10, 2, 3}) instanceof UnmodifiableTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> tempFactory.createUnmodifiable(new double[]{1, 2, 3}, new double[]{10, 2, 3}).setY(1, 5));
    }
}
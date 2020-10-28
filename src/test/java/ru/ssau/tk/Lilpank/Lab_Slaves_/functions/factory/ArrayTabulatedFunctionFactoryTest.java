package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        ArrayTabulatedFunctionFactory temp = new ArrayTabulatedFunctionFactory();
        assertTrue(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof ArrayTabulatedFunction);
        assertFalse(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof ArrayTabulatedFunctionFactory);
    }
}

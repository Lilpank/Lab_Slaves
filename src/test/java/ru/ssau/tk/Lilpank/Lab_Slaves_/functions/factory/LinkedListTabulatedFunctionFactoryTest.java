package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        LinkedListTabulatedFunctionFactory temp = new LinkedListTabulatedFunctionFactory();
        assertTrue(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof LinkedListTabulatedFunction);
        assertFalse(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof LinkedListTabulatedFunctionFactory);
    }
}
package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        LinkedListTabulatedFunctionFactory temp = new LinkedListTabulatedFunctionFactory();
        assertTrue(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof LinkedListTabulatedFunction);
        assertFalse(temp.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}) instanceof LinkedListTabulatedFunctionFactory);
    }
}
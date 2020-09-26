package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testGetNum() {
        assertEquals(new ConstantFunction(0).getNum(),0);
    }

    @Test
    public void testApply() {
        assertEquals(new ConstantFunction(1).apply(5),5);

    }
}
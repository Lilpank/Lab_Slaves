package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testGetNum() {
        assertEquals(new ConstantFunction(0).getNum(),0);
        assertEquals(new ConstantFunction(Double.NEGATIVE_INFINITY).getNum(),Double.NEGATIVE_INFINITY);
        assertEquals(new ConstantFunction(-1).getNum(),-1);
    }

    @Test
    public void testApply() {
        assertEquals(new ConstantFunction(1).apply(5),1);
        assertEquals(new ConstantFunction(Double.NaN).apply(0),Double.NaN);
        assertEquals(new ConstantFunction(20).apply(Double.NaN),20);

    }
}
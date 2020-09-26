package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new SqrFunction().apply(2),4);
    }
}
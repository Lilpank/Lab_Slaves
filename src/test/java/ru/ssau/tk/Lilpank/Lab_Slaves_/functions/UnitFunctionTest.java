package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    @Test
    public void testApply() {
        assertEquals(new UnitFunction().apply(10), 1);
        assertEquals(new UnitFunction().apply(50), 1);
        assertEquals(new UnitFunction().apply(67), 1);
    }

}
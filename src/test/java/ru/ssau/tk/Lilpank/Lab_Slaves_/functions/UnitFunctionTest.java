package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    @Test
    public void test() {
        assertEquals(new UnitFunction().apply(10), 1);
    }

}
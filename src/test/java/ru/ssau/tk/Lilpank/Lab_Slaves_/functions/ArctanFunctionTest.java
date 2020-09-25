package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArctanFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new ArctanFunction().apply(0), 0);
        assertEquals(new ArctanFunction().apply(1), Math.PI / 4);
    }
}
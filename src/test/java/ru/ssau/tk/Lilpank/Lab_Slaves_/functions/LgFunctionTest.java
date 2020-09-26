package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LgFunctionTest {

    @Test
    public void testApply() {
        assertEquals(new LgFunction().apply(1),0);
        assertEquals(new LgFunction().apply(100),2);
    }
}
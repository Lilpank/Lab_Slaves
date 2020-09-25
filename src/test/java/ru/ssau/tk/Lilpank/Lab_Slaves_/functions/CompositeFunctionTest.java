package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        MathFunction arc = new ArctanFunction();
        MathFunction self = new IdentityFunction();

        MathFunction selfArc = new CompositeFunction(self, arc);
        MathFunction doubleArc = new CompositeFunction(arc, arc);
        MathFunction arcSelf = new CompositeFunction(arc, self);


        assertEquals(selfArc.apply(1), Math.PI/4, 0.00001);
        assertEquals(doubleArc.apply(3), 0.90, 0.01);
        assertEquals(arcSelf.apply(0), 0, 0.00001);
    }
}
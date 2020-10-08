package ru.ssau.tk.Lilpank.Lab_Slaves_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        MathFunction arc = new ArctanFunction();
        MathFunction self = new IdentityFunction();
        MathFunction ln = new LgFunction();

        MathFunction selfArc = new CompositeFunction(self, arc);
        MathFunction doubleArc = new CompositeFunction(arc, arc);
        MathFunction arcSelf = new CompositeFunction(arc, self);
        MathFunction lnArc = new CompositeFunction(ln,arc);
        MathFunction doubleLn = new CompositeFunction(ln,ln);
        MathFunction lnSelf = new CompositeFunction(ln,self);


        assertEquals(selfArc.apply(1), Math.PI/4, 0.00001);
        assertEquals(doubleArc.apply(3), 0.90, 0.01);
        assertEquals(arcSelf.apply(0), 0, 0.00001);
        assertEquals(lnArc.apply(1),0);
        assertEquals(doubleLn.apply(10),0);
        assertEquals(lnSelf.apply(10),1);
        assertEquals(lnArc.apply(100),1.1,0.01);
    }
}
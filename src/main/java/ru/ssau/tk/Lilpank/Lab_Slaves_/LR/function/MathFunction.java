package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}

package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x - step))/(step * 2);
    }
}

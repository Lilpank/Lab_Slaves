package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}

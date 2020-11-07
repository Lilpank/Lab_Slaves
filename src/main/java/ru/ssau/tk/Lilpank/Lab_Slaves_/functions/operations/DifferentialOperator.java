package ru.ssau.tk.Lilpank.Lab_Slaves_.functions.operations;

import ru.ssau.tk.Lilpank.Lab_Slaves_.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}

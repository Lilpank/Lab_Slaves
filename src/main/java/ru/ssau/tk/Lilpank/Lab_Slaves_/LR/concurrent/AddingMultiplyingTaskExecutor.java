package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.concurrent;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ConstantFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;

public class AddingMultiplyingTaskExecutor{
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(2),1,100,100);
        Thread thread1 = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread2 = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread3 = new Thread(new AddingTask(tabulatedFunction));

        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(3000);
        System.out.println(tabulatedFunction.toString());
    }
}
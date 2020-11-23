package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.concurrent;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ZeroFunction;

import java.util.ArrayList;
import java.util.List;

public class ReadWriteTaskExecutor extends Thread {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> listThread = new ArrayList<>();

        for (int i = 0; i != 20; i++) {
            listThread.add(new Thread(new ReadWriteTask(tabulatedFunction)));
        }

        for (Thread thread : listThread) {
            thread.start();
        }

        Thread.sleep(3000);
        System.out.println(tabulatedFunction.toString());
    }
}

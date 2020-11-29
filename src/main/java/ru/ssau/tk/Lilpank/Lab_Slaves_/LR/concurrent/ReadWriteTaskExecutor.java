package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.concurrent;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ZeroFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ReadWriteTaskExecutor extends Thread {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> listThread = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i = 0; i != 20; i++) {
            listThread.add(new Thread(new ReadWriteTask(tabulatedFunction, countDownLatch::countDown)));
        }
        for (Thread thread : listThread) {
            thread.start();
        }
        countDownLatch.await();

        System.out.println(tabulatedFunction.toString());
    }
}

package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.concurrent;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ZeroFunction;

import java.util.ArrayList;

public class ReadWriteTaskExecutor extends Thread {
    public static void main(String[] args) throws InterruptedException {
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        ArrayList<Thread> listThread = new ArrayList<>();

        for (int i = 0; i != 20; i++) {
            listThread.add(new Thread(new ReadWriteTask(linkedListTabulatedFunction)));
        }
        for (Thread thread: listThread){
            thread.start();
        }
        Thread.sleep(20000);
        System.out.println(linkedListTabulatedFunction.toString());
    }
}

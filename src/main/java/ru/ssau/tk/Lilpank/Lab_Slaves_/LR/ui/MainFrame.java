package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    public static final int HEIGHT = 640;
    public static final int WIDTH = HEIGHT / 12 * 9;
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

    public MainFrame() {
        super("Главное окно");
        getContentPane().setLayout(new GridLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setJMenuBar(getCreatingWindowSettings());
        // Подключаем меню к интерфейсу приложения
        setLocationRelativeTo(null);
        setVisible(true);
    }


    //кнопки настройки - сменить тип фабрики - массивы - связный список
    private JMenuBar getCreatingWindowSettings() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu settings = new JMenu("Настройки");
        JMenu changeFactory = new JMenu("Сменить тип фабрики");
        JMenuItem arraysItem = new JRadioButtonMenuItem("Массивы");
        JMenuItem linkedListItem = new JRadioButtonMenuItem("Связный список");

        if (factory.getClass() == ArrayTabulatedFunctionFactory.class) {
            arraysItem.setSelected(true);
            linkedListItem.setSelected(false);
        } else {
            arraysItem.setSelected(false);
            linkedListItem.setSelected(true);
        }

        arraysItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new ArrayTabulatedFunctionFactory();
                }
                linkedListItem.setSelected(false);
            }
        });
        linkedListItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                arraysItem.setSelected(false);
            }
        });

        changeFactory.add(arraysItem);
        changeFactory.add(linkedListItem);
        settings.add(changeFactory);

        jMenuBar.add(settings);
        return jMenuBar;
    }


    public static void main(String[] args) {
        new MainFrame();
    }
}
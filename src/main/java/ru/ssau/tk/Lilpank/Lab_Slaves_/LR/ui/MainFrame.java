package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.TabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener {
    public static final int HEIGHT = 640;
    public static final int WIDTH = HEIGHT / 12 * 9;
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    public static TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
    private final JButton jButtonCalculator = new JButton("Калькулятор для двух функций");
    private final JButton jMenuDifCalculator = new JButton("Производная");

    public MainFrame() {
        super("Главное окно");
        //Масштаб окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        //рисует картинку
        backgroundImage();

        // Подключаем меню к интерфейсу приложения
        JMenuBar jMenuBar = new JMenuBar();
        JMenu settings = new JMenu("Настройки");
        settings.add(creatingMenuSettings());

        jMenuBar.add(settings);
        setJMenuBar(jMenuBar);

        //Компановка кнопок
        compose();

        jMenuDifCalculator.addActionListener(this);
        jButtonCalculator.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void backgroundImage() {
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("Icon.jpg"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 40, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //настройки в главном окне  - сменить тип фабрики - массивы - связный список
    private JMenu creatingMenuSettings() {
        JMenu fontChangeFactory = new JMenu("Сменить тип фабрики");
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
                    tabulatedFunctionOperationService.setFactory(factory);
                }
                linkedListItem.setSelected(false);
            }
        });

        linkedListItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new LinkedListTabulatedFunctionFactory();
                    tabulatedFunctionOperationService.setFactory(factory);
                }
                arraysItem.setSelected(false);
            }
        });

        fontChangeFactory.add(arraysItem);
        fontChangeFactory.add(linkedListItem);

        return fontChangeFactory;
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCalculator)
                        .addComponent(jMenuDifCalculator)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonCalculator)
                        .addComponent(jMenuDifCalculator)
                )
        );
    }

    public static TabulatedFunctionFactory getFactory() {
        return factory;
    }


    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButtonCalculator) {
            WindowTabulatedFunctionOperationService windowTabulatedFunctionOperationService = new WindowTabulatedFunctionOperationService();
            windowTabulatedFunctionOperationService.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            windowTabulatedFunctionOperationService.setVisible(true);
        }
        if (e.getSource() == jMenuDifCalculator) {
            new DifferentialWindow().setVisible(true);
        }
    }
}
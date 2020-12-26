package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.ArrayTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.LinkedListTabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.io.FunctionsIO;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class WindowTabulatedFunctionOperationService extends JDialog {
    private final TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
    private CreatingTabulatedFunctionOfFunction creatingTabulatedFunctionOfFunction;
    private static TabulatedFunction leftTabulatedFunction;
    private static TabulatedFunction rightTabulatedFunction;
    private static TabulatedFunction resultTabulatedFunction;

    private final AbstractTableModelForOperation leftTableModel = new AbstractTableModelForOperation(leftTabulatedFunction);
    private final JTable leftTableXY = new JTable(leftTableModel);
    private final AbstractTableModelForOperation tableModel2 = new AbstractTableModelForOperation(rightTabulatedFunction);
    private final AbstractTableModelForResult tableModelResult = new AbstractTableModelForResult();
    private final JTable rightTableXY = new JTable(tableModel2);
    private final JTable resultTable = new JTable(tableModelResult);

    private final JButton jButtonPlus = new JButton("+");
    private final JButton jButtonDifference = new JButton("-");
    private final JButton jButtonMultiply = new JButton("*");
    private final JButton jButtonDivision = new JButton("/");

    private final JButton jButtonDownland = new JButton("Загрузить");
    private final JButton jButtonSave = new JButton("Сохранить");
    private final JButton jButtonCreateOfArray = new JButton("Создать из массива");
    private final JButton jButtonCreateOfFunction = new JButton("Создать из функции");

    private final JButton jButtonDownland1 = new JButton("Загрузить");
    private final JButton jButtonSave1 = new JButton("Сохранить");
    private final JButton jButtonCreateOfArray1 = new JButton("Создать из массива");
    private final JButton jButtonCreateOfFunction1 = new JButton("Создать из функции");

    private final JButton jButtonSave2 = new JButton("Сохранить");

    private final JFileChooser jFileChooser = new JFileChooser();

    public WindowTabulatedFunctionOperationService() {
        super();
        tabulatedFunctionOperationService.setFactory(MainFrame.getFactory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        jFileChooser.setFileFilter(filter);

        //Размеры окна.
        setMaximumSize(new Dimension(1680, MainFrame.HEIGHT));
        setMinimumSize(new Dimension(1680, MainFrame.HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        leftTableXY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightTableXY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        getContentPane().setLayout(new GridLayout());
        compose();

        jButtonCreateOfArray.addActionListener(args -> {
            try {
                CreatingTabulatedFunctionOfArray temp = new CreatingTabulatedFunctionOfArray();

                leftTabulatedFunction = temp.getTabulatedFunction();
                if (leftTabulatedFunction != null) {
                    leftTableModel.setFunction(leftTabulatedFunction);
                    leftTableModel.fireTableDataChanged();
                }
            } catch (UnsupportedOperationException | NullPointerException e) {
                System.out.println();
            }
        });


        jButtonCreateOfFunction.addActionListener(args -> {
            try {
                creatingTabulatedFunctionOfFunction = new CreatingTabulatedFunctionOfFunction();

                leftTabulatedFunction = creatingTabulatedFunctionOfFunction.getTabulatedFunction();
                leftTableModel.setFunction(leftTabulatedFunction);
                leftTableModel.fireTableDataChanged();
            } catch (UnsupportedOperationException | NullPointerException e) {
                System.out.println();
            }
        });

        jButtonCreateOfFunction1.addActionListener(args -> {
            try {
                creatingTabulatedFunctionOfFunction = new CreatingTabulatedFunctionOfFunction();

                rightTabulatedFunction = creatingTabulatedFunctionOfFunction.getTabulatedFunction();

                tableModel2.setFunction(rightTabulatedFunction);
                tableModel2.fireTableDataChanged();
            } catch (UnsupportedOperationException | NullPointerException e) {
                System.out.println();
            }
        });

        jButtonDownland.addActionListener(args -> {
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            try (BufferedReader outArray = new BufferedReader(new FileReader(file.getPath()))) {
                leftTabulatedFunction = FunctionsIO.readTabulatedFunction(outArray, new ArrayTabulatedFunctionFactory());
            } catch (IOException | NullPointerException e) {
                System.out.println();
            }
            if (leftTabulatedFunction != null) {
                for (int i = 0; i < leftTabulatedFunction.getCount(); i++) {
                    leftTableModel.setFunction(leftTabulatedFunction);
                    leftTableModel.fireTableDataChanged();
                }
            }
        });

        jButtonDownland1.addActionListener(args -> {
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            try (BufferedReader outArray = new BufferedReader(new FileReader(file.getPath()))) {
                rightTabulatedFunction = FunctionsIO.readTabulatedFunction(outArray, new ArrayTabulatedFunctionFactory());
            } catch (IOException | NullPointerException e) {
                System.out.println();
            }
            if (rightTabulatedFunction != null) {
                for (int i = 0; i < rightTabulatedFunction.getCount(); i++) {
                    tableModel2.setFunction(rightTabulatedFunction);
                    tableModel2.fireTableDataChanged();
                }
            }
        });
        //кнопка для сохранения функции
        jButtonSave.addActionListener(args -> {
            if (leftTabulatedFunction == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JTextFileName jTextFileName = new JTextFileName();
                jTextFileName.setModalityType(ModalityType.APPLICATION_MODAL);
                jTextFileName.setVisible(true);
                System.out.println(jFileChooser.getName());
                if (!jTextFileName.getFileName().isEmpty()) {
                    jFileChooser.showSaveDialog(this);
                    File file = jFileChooser.getSelectedFile();
                    if (leftTabulatedFunction instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, leftTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    } else if (leftTabulatedFunction instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, leftTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    }
                }
            }
        });
        //кнопка для сохранения функции
        jButtonSave1.addActionListener(args -> {
            if (rightTabulatedFunction == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JTextFileName jTextFileName = new JTextFileName();
                jTextFileName.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                jTextFileName.setVisible(true);
                if (!jTextFileName.getFileName().isEmpty()) {
                    jFileChooser.showSaveDialog(this);
                    File file = jFileChooser.getSelectedFile();
                    if (rightTabulatedFunction instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, rightTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }

                    } else if (rightTabulatedFunction instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, rightTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    }
                }
            }
        });
        //кнопка для сохранения функции
        jButtonSave2.addActionListener(args -> {
            if (resultTabulatedFunction == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JTextFileName jTextFileName = new JTextFileName();
                jTextFileName.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                jTextFileName.setVisible(true);
                if (!jTextFileName.getFileName().isEmpty()) {
                    jFileChooser.showSaveDialog(this);
                    File file = jFileChooser.getSelectedFile();
                    if (resultTabulatedFunction instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, resultTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    } else if (resultTabulatedFunction instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, resultTabulatedFunction);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    }
                }
            }
        });
        //изменение значений в таблице напрямую
        leftTableXY.addPropertyChangeListener(evt -> {
            if (evt.getOldValue() != null) {
                try {
                    double value = (double) leftTableXY.getValueAt(leftTableXY.getSelectedRow(), leftTableXY.getSelectedColumn());
                    leftTabulatedFunction.setY(leftTableXY.getEditingRow(), value);
                } catch (NumberFormatException e) {
                    new WindowException(new ExceptionPanel(e));
                } catch (ArrayIndexOutOfBoundsException e) {
                    new WindowException(new ExceptionPanel(e));
                } catch (UnsupportedOperationException e) {
                    System.out.println();
                }
            }
        });

        rightTableXY.addPropertyChangeListener(evt -> {
            if (evt.getOldValue() != null) {
                try {
                    double value = (double) rightTableXY.getValueAt(rightTableXY.getEditingRow(), rightTableXY.getEditingColumn());
                    rightTabulatedFunction.setY(rightTableXY.getEditingRow(), value);
                } catch (NumberFormatException e) {
                    new WindowException(new ExceptionPanel(e));
                } catch (UnsupportedOperationException | ArrayIndexOutOfBoundsException e) {
                    System.out.println();
                }
            }
        });

        jButtonCreateOfArray1.addActionListener(args -> {
                    try {
                        CreatingTabulatedFunctionOfArray temp = new CreatingTabulatedFunctionOfArray();

                        rightTabulatedFunction = temp.getTabulatedFunction();
                        tableModel2.setFunction(rightTabulatedFunction);
                        tableModel2.fireTableDataChanged();
                    } catch (UnsupportedOperationException | NullPointerException e) {
                        System.out.println();
                    }
                }
        );

        jButtonPlus.addActionListener(args -> {
            if (leftTabulatedFunction == null || rightTabulatedFunction == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                try {
                    resultTabulatedFunction = tabulatedFunctionOperationService.sum(leftTabulatedFunction, rightTabulatedFunction);
                    for (int i = 0; i < resultTabulatedFunction.getCount(); i++) {
                        tableModelResult.setFunction(resultTabulatedFunction);
                        tableModelResult.fireTableDataChanged();
                    }
                } catch (InconsistentFunctionsException e) {
                    new WindowException(new ExceptionPanel(e));
                }
            }
        });

        jButtonDifference.addActionListener(args -> {
                    if (leftTabulatedFunction == null || rightTabulatedFunction == null) {
                        new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
                    } else {
                        try {
                            resultTabulatedFunction = tabulatedFunctionOperationService.subtract(leftTabulatedFunction, rightTabulatedFunction);
                            for (int i = 0; i < resultTabulatedFunction.getCount(); i++) {
                                tableModelResult.setFunction(resultTabulatedFunction);
                                tableModelResult.fireTableDataChanged();
                            }
                        } catch (InconsistentFunctionsException e) {
                            new WindowException(new ExceptionPanel(e));
                        }
                    }
                }
        );

        jButtonDivision.addActionListener(args -> {
                    if (leftTabulatedFunction == null || rightTabulatedFunction == null) {
                        new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
                    } else {
                        try {
                            resultTabulatedFunction = tabulatedFunctionOperationService.division(leftTabulatedFunction, rightTabulatedFunction);
                            for (int i = 0; i < resultTabulatedFunction.getCount(); i++) {
                                tableModelResult.setFunction(resultTabulatedFunction);
                                tableModelResult.fireTableDataChanged();
                            }
                        } catch (InconsistentFunctionsException e) {
                            new WindowException(new ExceptionPanel(e));
                        }
                    }
                }
        );

        jButtonMultiply.addActionListener(args -> {
            if (leftTabulatedFunction == null || rightTabulatedFunction == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                try {
                    resultTabulatedFunction = tabulatedFunctionOperationService.multiplication(leftTabulatedFunction, rightTabulatedFunction);
                    for (int i = 0; i < resultTabulatedFunction.getCount(); i++) {
                        tableModelResult.setFunction(resultTabulatedFunction);
                        tableModelResult.fireTableDataChanged();
                    }
                } catch (InconsistentFunctionsException e) {
                    new WindowException(new ExceptionPanel(e));
                }
            }
        });
        // окно по центру экрана
        setLocationRelativeTo(null);
        setVisible(false);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane1 = new JScrollPane(leftTableXY);
        JScrollPane tableScrollPane2 = new JScrollPane(rightTableXY);
        JScrollPane tableScrollPane3 = new JScrollPane(resultTable);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonCreateOfArray)
                                        .addComponent(jButtonCreateOfFunction)
                                        .addComponent(jButtonDownland)
                                        .addComponent(jButtonSave)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(tableScrollPane1)
                                )
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonPlus)
                                .addComponent(jButtonMultiply)
                                .addComponent(jButtonDifference)
                                .addComponent(jButtonDivision)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonCreateOfArray1)
                                        .addComponent(jButtonCreateOfFunction1)
                                        .addComponent(jButtonDownland1)
                                        .addComponent(jButtonSave1)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(tableScrollPane2))
                        ).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonSave2)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(tableScrollPane3))
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonCreateOfArray)
                                .addComponent(jButtonCreateOfFunction)
                                .addComponent(jButtonDownland)
                                .addComponent(jButtonSave)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jButtonCreateOfArray1)
                                        .addComponent(jButtonCreateOfFunction1)
                                        .addComponent(jButtonDownland1)
                                        .addComponent(jButtonSave1)
                                )
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jButtonSave2)
                                )
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tableScrollPane1)
                                .addComponent(tableScrollPane2)
                                .addComponent(tableScrollPane3)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonPlus)
                                        .addComponent(jButtonMultiply)
                                        .addComponent(jButtonDifference)
                                        .addComponent(jButtonDivision)
                                )
                        )
        );
    }
}

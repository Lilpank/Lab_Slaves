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
    private CreatingTableTabulatedFunctionOfArray creatingTableTabulatedFunctionOfArray;
    private static TabulatedFunction tabulatedFunction1;
    private static TabulatedFunction tabulatedFunction2;
    private static TabulatedFunction tabulatedFunction3;

    private final AbstractTableForOperation tableModel1 = new AbstractTableForOperation();
    private final AbstractTableForOperation tableModel2 = new AbstractTableForOperation();
    private final AbstractTableForOperation tableModel3 = new AbstractTableForOperation();
    private final JTable tableXY1 = new JTable(tableModel1);
    private final JTable tableXY2 = new JTable(tableModel2);
    private final JTable tableXY3 = new JTable(tableModel3);

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

    private final JButton jButtonRavno = new JButton(" = ");
    private final JFileChooser jFileChooser = new JFileChooser();

    public WindowTabulatedFunctionOperationService() {
        super();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        jFileChooser.setFileFilter(filter);

        //Размеры окна.
        setMaximumSize(new Dimension(MainFrame.WIDTH * 4, MainFrame.HEIGHT));
        setMinimumSize(new Dimension(MainFrame.WIDTH * 4, MainFrame.HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        tableXY1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableXY2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableXY3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        getContentPane().setLayout(new GridLayout());
        compose();

        jButtonDownland.addActionListener(args -> {
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            try (BufferedReader outArray = new BufferedReader(new FileReader(file.getPath()))) {
                tabulatedFunction1 = FunctionsIO.readTabulatedFunction(outArray, new ArrayTabulatedFunctionFactory());
            } catch (IOException | NullPointerException e) {
                System.out.println();
            }
            if (tabulatedFunction1 != null) {
                tableModel1.clear();
                for (int i = 0; i < tabulatedFunction1.getCount(); i++) {
                    tableModel1.addTableData(String.valueOf(tabulatedFunction1.getY(i)));
                    tableModel1.fireTableDataChanged();
                }
            }
        });

        jButtonDownland1.addActionListener(args -> {
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            try (BufferedReader outArray = new BufferedReader(new FileReader(file.getPath()))) {
                tabulatedFunction2 = FunctionsIO.readTabulatedFunction(outArray, new ArrayTabulatedFunctionFactory());
            } catch (IOException | NullPointerException e) {
                System.out.println();
            }
            if (tabulatedFunction2 != null) {
                tableModel2.clear();
                for (int i = 0; i < tabulatedFunction2.getCount(); i++) {
                    tableModel2.addTableData(String.valueOf(tabulatedFunction2.getY(i)));
                    tableModel2.fireTableDataChanged();
                }
            }
        });
        //кнопка для сохранения функции
        jButtonSave.addActionListener(args -> {
            if (tabulatedFunction1 == null) {
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
                    if (tabulatedFunction1 instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, tabulatedFunction1);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    } else if (tabulatedFunction1 instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, tabulatedFunction1);
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
            if (tabulatedFunction2 == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JTextFileName jTextFileName = new JTextFileName();
                jTextFileName.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                jTextFileName.setVisible(true);
                if (!jTextFileName.getFileName().isEmpty()) {
                    jFileChooser.showSaveDialog(this);
                    File file = jFileChooser.getSelectedFile();
                    if (tabulatedFunction2 instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, tabulatedFunction2);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }

                    } else if (tabulatedFunction2 instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, tabulatedFunction2);
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
            if (tabulatedFunction3 == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JTextFileName jTextFileName = new JTextFileName();
                jTextFileName.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                jTextFileName.setVisible(true);
                if (!jTextFileName.getFileName().isEmpty()) {
                    jFileChooser.showSaveDialog(this);
                    File file = jFileChooser.getSelectedFile();
                    if (tabulatedFunction3 instanceof ArrayTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outArray = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outArray, tabulatedFunction3);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    } else if (tabulatedFunction3 instanceof LinkedListTabulatedFunction) {
                        if (file != null) {
                            try (BufferedWriter outList = new BufferedWriter(new FileWriter(file + "\\" + jTextFileName.getFileName() + ".txt"))) {
                                FunctionsIO.writeTabulatedFunction(outList, tabulatedFunction3);
                            } catch (IOException err) {
                                System.out.println();
                            }
                        }
                    }
                }
            }
        });
        //изменение значений в таблице напрямую
        tableXY1.addPropertyChangeListener(evt -> {
            if (evt.getOldValue() != null) {
                try {
                    String value = (String) tableXY1.getValueAt(tableXY1.getSelectedRow(), tableXY1.getSelectedColumn());
                    tabulatedFunction1.setY(tableXY1.getEditingRow(), Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    new WindowException(new ExceptionPanel(e));
                } catch (UnsupportedOperationException | ArrayIndexOutOfBoundsException e) {
                    System.out.println();
                }
            }
        });

        tableXY2.addPropertyChangeListener(evt -> {
            if (evt.getOldValue() != null) {
                try {
                    String value = (String) tableXY2.getValueAt(tableXY2.getEditingRow(), tableXY2.getEditingColumn());
                    tabulatedFunction2.setY(tableXY2.getEditingRow(), Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    new WindowException(new ExceptionPanel(e));
                } catch (UnsupportedOperationException | ArrayIndexOutOfBoundsException e) {
                    System.out.println();
                }
            }
        });

        jButtonCreateOfArray.addActionListener(args -> {
                    try {
                        creatingTableTabulatedFunctionOfArray = new CreatingTableTabulatedFunctionOfArray();
                        creatingTableTabulatedFunctionOfArray.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                        creatingTableTabulatedFunctionOfArray.setVisible(true);
                        tabulatedFunction1 = creatingTableTabulatedFunctionOfArray.getTabulatedFunction();

                        tableModel1.clear();
                        for (int i = 0; i < tabulatedFunction1.getCount(); i++) {
                            tableModel1.addTableData(String.valueOf(tabulatedFunction1.getY(i)));
                            tableModel1.fireTableDataChanged();
                        }
                    } catch (UnsupportedOperationException | NullPointerException e) {
                        System.out.println();
                    }
                }
        );

        jButtonCreateOfArray1.addActionListener(args -> {
                    try {
                        creatingTableTabulatedFunctionOfArray = new CreatingTableTabulatedFunctionOfArray();

                        creatingTableTabulatedFunctionOfArray.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                        creatingTableTabulatedFunctionOfArray.setVisible(true);
                        tabulatedFunction2 = creatingTableTabulatedFunctionOfArray.getTabulatedFunction();

                        tableModel2.clear();
                        for (int i = 0; i < tabulatedFunction2.getCount(); i++) {
                            tableModel2.addTableData(String.valueOf(tabulatedFunction2.getY(i)));
                            tableModel2.fireTableDataChanged();
                        }
                    } catch (UnsupportedOperationException | NullPointerException exception) {
                        System.out.println();
                    }
                }
        );

        jButtonPlus.addActionListener(args -> {
            if (tabulatedFunction1 == null || tabulatedFunction2 == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                try {
                    tabulatedFunction3 = tabulatedFunctionOperationService.sum(tabulatedFunction1, tabulatedFunction2);
                    tableModel3.clear();
                    for (int i = 0; i < tabulatedFunction3.getCount(); i++) {
                        tableModel3.addTableData(String.valueOf(tabulatedFunction3.getY(i)));
                        tableModel3.fireTableDataChanged();
                    }
                } catch (InconsistentFunctionsException e) {
                    new WindowException(new ExceptionPanel(e));
                }
            }
        });

        jButtonDifference.addActionListener(args -> {
                    if (tabulatedFunction1 == null || tabulatedFunction2 == null) {
                        new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
                    } else {
                        try {
                            tabulatedFunction3 = tabulatedFunctionOperationService.subtract(tabulatedFunction1, tabulatedFunction2);
                            tableModel3.clear();
                            for (int i = 0; i < tabulatedFunction3.getCount(); i++) {
                                tableModel3.addTableData(String.valueOf(tabulatedFunction3.getY(i)));
                                tableModel3.fireTableDataChanged();
                            }
                        } catch (InconsistentFunctionsException e) {
                            new WindowException(new ExceptionPanel(e));
                        }
                    }
                }
        );

        jButtonDivision.addActionListener(args -> {
                    if (tabulatedFunction1 == null || tabulatedFunction2 == null) {
                        new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
                    } else {
                        try {
                            tabulatedFunction3 = tabulatedFunctionOperationService.division(tabulatedFunction1, tabulatedFunction2);
                            tableModel3.clear();
                            for (int i = 0; i < tabulatedFunction3.getCount(); i++) {
                                tableModel3.addTableData(String.valueOf(tabulatedFunction3.getY(i)));
                                tableModel3.fireTableDataChanged();
                            }
                        } catch (InconsistentFunctionsException e) {
                            new WindowException(new ExceptionPanel(e));
                        }
                    }
                }
        );

        jButtonMultiply.addActionListener(args -> {
            if (tabulatedFunction1 == null || tabulatedFunction2 == null) {
                new WindowException(new ExceptionPanel(new NullPointerException("Введите значения!")));
            } else {
                try {
                    tabulatedFunction3 = tabulatedFunctionOperationService.multiplication(tabulatedFunction1, tabulatedFunction2);
                    tableModel3.clear();
                    for (int i = 0; i < tabulatedFunction3.getCount(); i++) {
                        tableModel3.addTableData(String.valueOf(tabulatedFunction3.getY(i)));
                        tableModel3.fireTableDataChanged();
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
        JScrollPane tableScrollPane1 = new JScrollPane(tableXY1);
        JScrollPane tableScrollPane2 = new JScrollPane(tableXY2);
        JScrollPane tableScrollPane3 = new JScrollPane(tableXY3);

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
                        .addComponent(jButtonRavno)
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
                                        .addComponent(jButtonRavno)
                                )
                        )
        );
    }
}

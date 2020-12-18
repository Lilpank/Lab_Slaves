package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import java.awt.*;

public class WindowTabulatedFunctionOperationService extends JDialog {
    private final TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
    private CreatingTableTabulatedFunctionOfArray creatingTableTabulatedFunctionOfArray;
    private TabulatedFunction temp;
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

    private final JButton jButtonDownland2 = new JButton("Загрузить");
    private final JButton jButtonSave2 = new JButton("Сохранить");
    private final JButton jButtonCreateOfArray2 = new JButton("Создать из массива");
    private final JButton jButtonCreateOfFunction2 = new JButton("Создать из функции");

    private final JButton jButtonRavno = new JButton(" = ");

    public WindowTabulatedFunctionOperationService() {
        super();
        //Размеры окна.
        setMaximumSize(new Dimension(MainFrame.WIDTH * 4, MainFrame.HEIGHT));
        setMinimumSize(new Dimension(MainFrame.WIDTH * 4, MainFrame.HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        tableXY1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableXY2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableXY3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        getContentPane().setLayout(new GridLayout());
        compose();

        tableXY1.addPropertyChangeListener(evt -> {
            if (evt.getOldValue() != null) {
                try {
                    String value = (String) tableXY1.getValueAt(tableXY1.getSelectedRow(), tableXY1.getSelectedColumn());
                    tabulatedFunction1.setY(tableXY1.getEditingRow(), Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    new WindowException(new ExceptionPanel(e));
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
                }
            }
        });

        jButtonCreateOfArray.addActionListener(args -> {
                    creatingTableTabulatedFunctionOfArray = new CreatingTableTabulatedFunctionOfArray();
                    creatingTableTabulatedFunctionOfArray.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    creatingTableTabulatedFunctionOfArray.setVisible(true);
                    tabulatedFunction1 = creatingTableTabulatedFunctionOfArray.getTabulatedFunction();

                    tableModel1.clear();
                    for (int i = 0; i < tabulatedFunction1.getCount(); i++) {
                        tableModel1.addTableData(String.valueOf(tabulatedFunction1.getY(i)));
                        tableModel1.fireTableDataChanged();
                    }
                }
        );

        jButtonCreateOfArray1.addActionListener(args -> {
                    creatingTableTabulatedFunctionOfArray = new CreatingTableTabulatedFunctionOfArray();
                    creatingTableTabulatedFunctionOfArray.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    creatingTableTabulatedFunctionOfArray.setVisible(true);
                    tabulatedFunction2 = creatingTableTabulatedFunctionOfArray.getTabulatedFunction();

                    tableModel2.clear();
                    for (int i = 0; i < tabulatedFunction2.getCount(); i++) {
                        tableModel2.addTableData(String.valueOf(tabulatedFunction2.getY(i)));
                        tableModel2.fireTableDataChanged();
                    }
                }
        );

        jButtonCreateOfArray2.addActionListener(args -> {
                    creatingTableTabulatedFunctionOfArray = new CreatingTableTabulatedFunctionOfArray();
                    creatingTableTabulatedFunctionOfArray.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    creatingTableTabulatedFunctionOfArray.setVisible(true);
                    tabulatedFunction3 = creatingTableTabulatedFunctionOfArray.getTabulatedFunction();

                    tableModel3.clear();
                    for (int i = 0; i < tabulatedFunction3.getCount(); i++) {
                        tableModel3.addTableData(String.valueOf(tabulatedFunction3.getY(i)));
                        tableModel3.fireTableDataChanged();
                    }
                }
        );

        jButtonPlus.addActionListener(args -> {
            temp = tabulatedFunctionOperationService.sum(tabulatedFunction1, tabulatedFunction2);
            tableModel3.clear();
            for (int i = 0; i < temp.getCount(); i++) {
                tableModel3.addTableData(String.valueOf(temp.getY(i)));
                tableModel3.fireTableDataChanged();
            }
        });

        jButtonDifference.addActionListener(args -> {
                    temp = tabulatedFunctionOperationService.subtract(tabulatedFunction1, tabulatedFunction2);
                    tableModel3.clear();
                    for (int i = 0; i < temp.getCount(); i++) {
                        tableModel3.addTableData(String.valueOf(temp.getY(i)));
                        tableModel3.fireTableDataChanged();
                    }
                }
        );

        jButtonDivision.addActionListener(args -> {
                    temp = tabulatedFunctionOperationService.division(tabulatedFunction1, tabulatedFunction2);
                    tableModel3.clear();
                    for (int i = 0; i < temp.getCount(); i++) {
                        tableModel3.addTableData(String.valueOf(temp.getY(i)));
                        tableModel3.fireTableDataChanged();
                    }
                }
        );

        jButtonMultiply.addActionListener(args -> {
            temp = tabulatedFunctionOperationService.multiplication(tabulatedFunction1, tabulatedFunction2);
            tableModel3.clear();
            for (int i = 0; i < temp.getCount(); i++) {
                tableModel3.addTableData(String.valueOf(temp.getY(i)));
                tableModel3.fireTableDataChanged();
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
                                        .addComponent(jButtonCreateOfArray2)
                                        .addComponent(jButtonCreateOfFunction2)
                                        .addComponent(jButtonDownland2)
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
                                        .addComponent(jButtonCreateOfArray2)
                                        .addComponent(jButtonCreateOfFunction2)
                                        .addComponent(jButtonDownland2)
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

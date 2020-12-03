package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

// класс рисующий шаблон таблицы
class AbstractTableXY extends AbstractTableModel {
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int VALUE_COLUMN_NUMBER = 1;
    private static final long serialVersionUID = -2084927806333118951L;
    private final List<String> strings;

    public AbstractTableXY(List<String> strings) {
        this.strings = strings;
    }


    @Override
    public int getRowCount() {
        return strings.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return rowIndex;
            case VALUE_COLUMN_NUMBER:
                return strings.get(rowIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        strings.set(rowIndex, String.valueOf(aValue));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
            case VALUE_COLUMN_NUMBER:
                return true;
        }

        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case INDEX_COLUMN_NUMBER:
                return "X: ";
            case VALUE_COLUMN_NUMBER:
                return "Y: ";

        }
        return super.getColumnName(column);
    }
}


public class CreatingTable extends JFrame {
    private final List<String> strings = new ArrayList<>();
    private final AbstractTableModel tableModel = new AbstractTableXY(strings);
    private final JTable tableXY = new JTable(tableModel);
    private final JButton buttonCreateFunction = new JButton("Создать");
    private final JButton buttonCreateXY = new JButton("Создать point");
    private final JLabel textField = new JLabel("Введите количество точек: ");
    private final JTextField textFieldCount = new JTextField();

    public CreatingTable() {
        super("TabulatedFunction");
        //размеры окна, и Layout
        getContentPane().setLayout(new GridLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        setMaximumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        setMinimumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));

        //чтобы пользователь мог выбрать только 1 строку
        tableXY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        compose();

        //окно по середине выйдет
        setLocationRelativeTo(null);
        // парсер ввода числа точек
        buttonCreateXY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args) {
                addTableLine(Integer.parseInt(textFieldCount.getText()));
            }
        });

        buttonCreateFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args) {
                getTabulatedFunctionFactory();
                setVisible(false);
            }
        });
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(tableXY);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(textField)
                                .addComponent(textFieldCount)))
                .addComponent(buttonCreateXY)
                .addComponent(buttonCreateFunction)
                .addComponent(tableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField)
                        .addComponent(textFieldCount))
                .addComponent(buttonCreateXY)
                .addComponent(tableScrollPane)
                .addComponent(buttonCreateFunction)
        );
    }

    private void addTableLine(int count) {
        if (count < 1) {
            throw new UnsupportedOperationException();
        } else {
            strings.clear();
            for (int i = 0; i < count; i++) {
                strings.add(String.valueOf(0));
                tableModel.fireTableDataChanged();
            }
        }
    }

    private TabulatedFunction getTabulatedFunctionFactory() {
        double[] yValues = new double[strings.size()];
        double[] xValues = new double[strings.size()];

        for (int i = 0; i < strings.size(); i++) {
            yValues[i] = Double.parseDouble(strings.get(i));
            xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
        }
        return new LinkedListTabulatedFunctionFactory().create(xValues,yValues);
    }

    public static void main(String[] args) {
        new CreatingTable();
    }
}
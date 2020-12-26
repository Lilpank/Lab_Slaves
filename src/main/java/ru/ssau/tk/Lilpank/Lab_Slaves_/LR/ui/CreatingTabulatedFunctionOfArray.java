package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.TabulatedFunction;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.ArrayTabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreatingTabulatedFunctionOfArray extends JDialog {
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();

    private final AbstractTableModelXY tableModel = new AbstractTableModelXY(xValues, yValues);
    private final JTable tableXY = new JTable(tableModel);
    private final JButton buttonCreateFunction = new JButton("Создать");
    private final JButton buttonCreateXY = new JButton("Создать point");
    private final JLabel textField = new JLabel("Введите количество точек: ");
    private final JTextField textFieldCount = new JTextField();
    private TabulatedFunction tabulatedFunction;

    public CreatingTabulatedFunctionOfArray() {
        //размеры окна, и Layout
        getContentPane().setLayout(new GridLayout());
        setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        setMaximumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        setMinimumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //чтобы пользователь мог выбрать только 1 строку
        tableXY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        compose();

        //окно по середине выйдет
        setLocationRelativeTo(null);
        // парсер ввода числа точек
        buttonCreateXY.addActionListener(args -> {
            try {
                int temp = -1;
                temp = Integer.parseInt(textFieldCount.getText());
                if (temp < 0) {
                    throw new ExceptionPanel("Введите положительное число!");
                }
                if (temp == 1) {
                    throw new ExceptionPanel("Введите несколько точек");
                }
                addTableLine(temp);
            } catch (NumberFormatException exception) {
                new WindowException(new ExceptionPanel(exception));
            } catch (NullPointerException exception) {
                new WindowException(new ExceptionPanel(exception));
            } catch (IllegalArgumentException exception) {
                new WindowException(new ExceptionPanel(exception));
            } catch (ExceptionPanel exceptionPanel) {
                new WindowException(exceptionPanel);
            }
        });
        //закрывается окно с таблицей
        buttonCreateFunction.addActionListener(args -> {
            try {
                setTabulatedFunctionFactory();
                this.dispose();
            } catch (IllegalArgumentException exception) {
                new WindowException(new ExceptionPanel(exception));
            } catch (ArrayIsNotSortedException exceptionPanel) {
                new WindowException(new ExceptionPanel(exceptionPanel));
            }
        });
        setModal(true);
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
        tableModel.clear();
        tableModel.fireTableDataChanged();
        for (int i = 0; i < count; i++) {
            xValues.add((double) i);
            yValues.add(0.);
            tableModel.fireTableDataChanged();
        }
    }

    public void setTabulatedFunctionFactory() {
        double[] y = new double[xValues.size()];
        double[] x = new double[xValues.size()];

        for (int i = 0; i < x.length; i++) {
            y[i] = yValues.get(i);
            x[i] = xValues.get(i);
        }
        tabulatedFunction = new ArrayTabulatedFunctionFactory().create(x, y);
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }

    public static void main(String[] args) {
        new CreatingTabulatedFunctionOfArray();
    }
}

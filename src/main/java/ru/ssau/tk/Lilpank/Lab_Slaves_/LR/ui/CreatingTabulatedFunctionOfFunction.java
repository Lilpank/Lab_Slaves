package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.*;
import ru.ssau.tk.Lilpank.Lab_Slaves_.LR.function.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreatingTabulatedFunctionOfFunction extends JDialog {
    private final JLabel fromLabel = new JLabel("От: ");
    private final JLabel toLabel = new JLabel("До: ");
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JLabel countLabel = new JLabel("Укажите количество точек разбиения: ");
    private final JTextField fromField = new JTextField();
    private final JButton jButtonCreate = new JButton("Создать");
    private final JTextField toField = new JTextField();
    private final JTextField countField = new JTextField();
    private final Map<String, MathFunction> nameMathFunctionMap = new HashMap<>();
    private final TabulatedFunctionFactory factory = MainFrame.factory;
    private TabulatedFunction function;

    public CreatingTabulatedFunctionOfFunction() {
        jButtonCreate.addActionListener(args -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameMathFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = factory.create(selectedFunction, from, to, count);
                this.dispose();
            } catch (Exception exception) {
                new WindowException(new ExceptionPanel(exception));
            }
        });
        setModal(true);

        stuffMap();
        compose();
        setMaximumSize(new Dimension(500, 150));
        setMinimumSize(new Dimension(500, 150));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(jButtonCreate)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(jButtonCreate)
        );
    }

    public void stuffMap() {
        nameMathFunctionMap.put("Единичная функция", new UnitFunction());
        nameMathFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameMathFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameMathFunctionMap.put("Логарифмическая функция", new LgFunction());
        nameMathFunctionMap.put("Тождественная функция", new IdentityFunction());
        String[] functions = new String[5];
        int i = 0;
        for (String string : nameMathFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }
    public TabulatedFunction getTabulatedFunction(){
        return function;
    }
}

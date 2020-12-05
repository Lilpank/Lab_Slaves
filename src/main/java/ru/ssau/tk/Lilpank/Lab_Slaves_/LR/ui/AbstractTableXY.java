package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// класс рисующий шаблон таблицы
public class AbstractTableXY extends AbstractTableModel {
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


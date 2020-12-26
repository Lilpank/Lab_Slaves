package ru.ssau.tk.Lilpank.Lab_Slaves_.LR.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AbstractTableModelXY extends AbstractTableModel {
    private static final int X_COLUMN = 0;
    private static final int Y_COLUMN = 1;
    private static final long serialVersionUID = -8441733366375892140L;
    private final List<Double> xValues;
    private final List<Double> yValues;

    public AbstractTableModelXY(List<Double> xValues, List<Double> yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public void clear() {
        xValues.clear();
        yValues.clear();
    }

    public void setXAndY(double xValues, double yValues) {
        this.xValues.add(xValues);
        this.yValues.add(yValues);
    }

    @Override
    public int getRowCount() {
        return xValues.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN:
                return xValues.get(rowIndex);
            case Y_COLUMN:
                return yValues.get(rowIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) throws NumberFormatException {
        if (columnIndex == X_COLUMN) {
            try {
                xValues.set(rowIndex, Double.valueOf(aValue.toString()));
            } catch (Exception e) {
                xValues.set(rowIndex, 0.0);
            }
        } else if (columnIndex == Y_COLUMN) {
            try {
                yValues.set(rowIndex, Double.valueOf(aValue.toString()));
            } catch (Exception e) {
                yValues.set(rowIndex, 0.0);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN:
            case Y_COLUMN:
                return true;
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case X_COLUMN:
                return "X";
            case Y_COLUMN:
                return "Y";
        }
        return super.getColumnName(column);
    }
}

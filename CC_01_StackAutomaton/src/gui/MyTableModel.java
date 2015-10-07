package gui;

import javax.swing.table.AbstractTableModel;

import util.TokenizedLines;

public class MyTableModel extends AbstractTableModel{
    
    TokenizedLines dataMatrix = null;
    
    MyTableModel(TokenizedLines tl){
        this.dataMatrix = tl;
    }
    
    @Override
    public int getRowCount() {
        return dataMatrix.size();
    }

    @Override
    public int getColumnCount() {
        // TODO: We assume that all rows have same number of elements, without checking.
        if (getRowCount() > 0)
            return dataMatrix.get(0).size();
        else
            return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataMatrix.get(rowIndex).get(columnIndex);
    }

}

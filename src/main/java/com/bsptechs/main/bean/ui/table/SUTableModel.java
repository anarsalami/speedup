/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import com.bsptechs.main.bean.SUArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class SUTableModel extends DefaultTableModel {

    private SUArrayList<SUTableColumn> columnNames = new SUArrayList<>();
    private SUArrayList<SUTableRow> rows = new SUArrayList<>();

    public SUTableModel() {

    }

    public SUTableModel(
            SUArrayList<SUTableRow> rows,
            SUArrayList<SUTableColumn> columns
    ) {
//        super(new Vector(rows), new Vector(columns));
        setColumnIdentifiers(new Vector(columns));
        this.columnNames = columns;
        this.rows = rows;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames != null ? columnNames.get(column).getName() : null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnNames.get(columnIndex).getClass();
    }

    @Override
    public int getColumnCount() {
        return columnNames != null ? columnNames.size() : 0;
    }

    @Override
    public int getRowCount() {
        return rows != null ? rows.size() : 0;
    }
//
//    @Override
//    public boolean isCellEditable(int row, int column) {
//        System.out.println("model is cell editable");
//        return true;
//    }
//

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        System.out.println("rowindex=" + rowIndex);
        System.out.println("columnIndex=" + columnIndex);
        SUTableCell cell = rows != null ? rows.get(rowIndex).get(columnIndex) : null;
        return cell;
    }

    public SUTableRow addEmptyRow() {
        SUTableRow newRow = new SUTableRow();

        for (int i = 0; i < columnNames.size(); i++) {
            newRow.add(new SUTableCell(columnNames.get(i), ""));
        }
        this.addRow(newRow);

        return newRow;
    }

    public void addRow(SUTableRow row) {
        rows.add(row);
        fireTableDataChanged();
//        super.addRow(dataVector);
    }

    public SUTableRow removeRow(SUTableRow row) {
        rows.remove(row);
        fireTableDataChanged();
//        super.addRow(dataVector);
        return row;
    }

    public SUTableRow getTableRow(int index) {
        return rows.get(index);
    }

    public SUTableRow removeLastRow() {
        SUTableRow row = rows.getLast();
        return removeRow(row);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {
        //don't remove this method. Method must be empty and override super method
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import com.bsptechs.main.bean.SUArrayList;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sarkhanrasullu
 */
//@Data
public class SUTableModel extends DefaultTableModel {

    private final SUArrayList<SUTableColumn> columns;
    private final SUArrayList<SUTableRow> rows;
    private SUTableListener<SUTableRow> onRowAdd;
    private SUTableListener<SUTableCell> onChange;

    public SUTableModel() {
        columns = new SUArrayList<>();
        rows = new SUArrayList<>();
        setColumnIdentifiers(new Vector(columns));
    }

    public SUTableModel(
            SUArrayList<SUTableRow> rows,
            SUArrayList<SUTableColumn> columns
    ) {
        setColumnIdentifiers(new Vector(columns));
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public String getColumnName(int index) {
        return columns != null ? columns.get(index).getName() : null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getClass();
    }

    @Override
    public int getColumnCount() {
        return columns != null ? columns.size() : 0;
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
//        System.out.println("rowindex=" + rowIndex);
//        System.out.println("columnIndex=" + columnIndex);
        SUTableCell cell = rows != null ? rows.get(rowIndex).get(columnIndex) : null;
        return cell;
    }

    public SUTableRow addEmptyRow() {
        SUTableRow newRow = new SUTableRow();
 
        for (int i = 0; i < columns.size(); i++) {
            newRow.add(new SUTableCell(columns.get(i), ""));
        }

        this.addRow(newRow);
        if (onRowAdd != null) {
            onRowAdd.action(newRow);
        }
        return newRow;
    }

//    public void refreshData(SUArrayList<SUTableColumn> columns, SUArrayList<SUTableRow> rows) {
//        System.out.println("columns="+columns);
//        this.rows.addAll(rows);
//        this.columns.addAll(columns);
//        fireTableStructureChanged();
//        fireTableDataChanged();
//    }
    public void addRow(SUTableRow row) {
        rows.add(row);
        for (SUTableCell cell : row) {
            cell.setOnChange(onChange);
        }

        fireTableDataChanged();
    }

    public void setOnRowAdd(SUTableListener<SUTableRow> listener) {
        onRowAdd = listener;
    }

    public void setOnCellEditing(SUTableListener<SUTableCell> listener) {
        this.onChange = listener;
        rows.forEach((row) -> {
            row.forEach((cell) -> {
                cell.setOnChange(listener);
            });
        });
    }

    public SUTableRow removeRow(SUTableRow row) {
        rows.remove(row);
        fireTableDataChanged();
        return row;
    }

    public SUTableRow getTableRow(int index) {
        return rows.get(index);
    }

    public SUTableRow removeLastRow() {
        SUTableRow row = rows.getLast();
        return removeRow(row);
    }

    public SUTableRow getEditingRow() {
        for(SUTableRow row: rows){
            if(row.isEditing()){
                return row;
            }
        }
//        SUArrayList<SUTableRow> editingRows = rows
//                        .stream()
//                        .filter(row -> row.isEditing())
//                        .collect(Collectors.toCollection(SUArrayList::new));
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {
        //don't remove this method. Method must be empty and override super method
    }
}

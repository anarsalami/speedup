/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class CustomTableModel extends DefaultTableModel {
    
    private String databaseName;
    private String tableName;
    
    public CustomTableModel() {
    }
    
    public CustomTableModel(String databaseName, String tableName) {
        this.databaseName = databaseName;
        this.tableName = tableName;
    }
    
    public CustomTableModel(List<TableRow> rows, List<String> columns, String databaseName, String tableName) {
        super(new Vector(rows), new Vector(columns));
        this.tableName = tableName;
        this.databaseName = databaseName;
    }
    
    public void refreshRows(List<TableRow> customTableRows) {
        for (TableRow row : customTableRows) {
            addRow(row);
        }
    }
    
    public TableRow addEmptyRow() {
        TableRow newRow = new TableRow(databaseName, tableName);
        
        for (int i = 0; i < columnIdentifiers.size(); i++) {
            newRow.add(new TableCell(columnIdentifiers.get(i).toString(), "", databaseName, tableName, false));
        }
        this.addRow(newRow);
        
        return newRow;
    }
    
    @Override
    public void addRow(Vector row) {
        super.addRow(row);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        TableRow roww = (TableRow) dataVector.get(row);
        TableCell cell = roww.get(col);
        cell.setColumnValue(value);
        roww.setUpdateMode(true);
        cell.setUpdateMode(true);
        super.setValueAt(cell, row, col);
    }
}

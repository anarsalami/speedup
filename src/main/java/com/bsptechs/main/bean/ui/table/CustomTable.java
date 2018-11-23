/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import com.bsptechs.main.bean.ui.tree.database.node.ConnectionTreeNode;
import com.bsptechs.main.bean.ui.tree.database.node.DatabaseTreeNode;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class CustomTable extends JTable {

    private ConnectionTreeNode connection;
    private DatabaseTreeNode database;
    private String query;

    public CustomTable() {
        setModel(new CustomTableModel());
    }

    public void refreshRows(List<TableRow> rows) {
        CustomTableModel model = (CustomTableModel) getModel();
        model.refreshRows(rows);
    }

    public TableRow getSelectedTableRow() {
        int selectedRowIndex = this.getSelectedRow();
        if (selectedRowIndex < 0) {
            return null;
        }
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        TableRow row = (TableRow) model.getDataVector().get(selectedRowIndex);
        return row;
    }

    public TableRow getTableRow(int rowIndex) {
        CustomTableModel model = (CustomTableModel) this.getModel();

        return (TableRow) model.getDataVector().get(rowIndex);
    }

    public TableCell getSelectedTableCell() {
        TableRow row = getSelectedTableRow();
        if (row == null) {
            return null;
        }
        return row.get(getSelectedColumn());
    }

    public TableCell getTableCell(int rowIndex, int columnIndex) {
        TableRow row = getTableRow(rowIndex);
        if (row == null) {
            return null;
        }
        return row.get(columnIndex);
    }

    public List<TableRow> getSelectedTableRows() {
        int[] selectedRowIndexes = this.getSelectedRows();
        if (selectedRowIndexes.length <= 0) {
            return null;
        }
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        List<TableRow> rows = new ArrayList<>();
        for (int selectedRowIndex : selectedRowIndexes) {
            TableRow row = (TableRow) model.getDataVector().get(selectedRowIndex);
            rows.add(row);
        }
        return rows;
    }

    public TableRow addEmptyRow() {
        CustomTableModel model = (CustomTableModel) getModel();
        TableRow row = model.addEmptyRow();
//        firePropertyChange("model", model, model);
        return row;
    }

    public void addRow(TableRow row) {
        CustomTableModel model = (CustomTableModel) getModel();
        model.addRow(row);
//        firePropertyChange("model", model, model);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int column) {
        System.out.println("isCellEditable");
        return getTableCell(rowIndex, column).isEditable();
    }

}

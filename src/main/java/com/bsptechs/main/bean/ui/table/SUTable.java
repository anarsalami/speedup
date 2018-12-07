/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.bean.SUQueryBean;
import com.bsptechs.main.bean.SUQueryResult;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
class MyTableColumnModel extends DefaultTableColumnModel {

    private SUTableCellEditor cellEditor;

    public MyTableColumnModel(SUTableCellEditor cellEditor) {
        this.cellEditor = cellEditor;
    }

    @Override
    public void addColumn(TableColumn column) {
        column.setCellEditor(cellEditor);
        super.addColumn(column);
    }
}

@Data
public class SUTable extends JTable {

    public SUTable() {
        super(new SUTableModel());
        setDefaultRenderer(SUTableColumn.class, new SUTableCellRenderer());
        setColumnModel(new MyTableColumnModel(new SUTableCellEditor()));

        this.setRowHeight(25);
        setShowVerticalLines(true);
        setShowHorizontalLines(true);
        setGridColor(new Color(239, 239, 239));

        this.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = this.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                SUTableCell selectedData = null;

                int[] selectedRow = getSelectedRows();
                int[] selectedColumns = getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = (SUTableCell) getValueAt(selectedRow[i], selectedColumns[j]);
                    }
                }
                System.out.println("Selected: " + selectedData);
            }

        });
    }

//    @Override
//    public void setModel(TableModel m) {
//        super.setModel(m);
////        throw new RuntimeException("can not use setModel");
//    }
    public SUTableModel getTableModel() {
        return (SUTableModel) super.getModel();
    }

    public SUTableRow getSelectedTableRow() {
        int selectedRowIndex = this.getSelectedRow();

        return (SUTableRow) getTableModel().getTableRow(selectedRowIndex);
    }

    public SUTableRow getTableRow(int rowIndex) {
        SUTableModel model = (SUTableModel) this.getModel();

        return (SUTableRow) getTableModel().getTableRow(rowIndex);
    }

    public SUTableCell getSelectedTableCell() {
        SUTableRow row = getSelectedTableRow();
        if (row == null) {
            return null;
        }
        return row.get(getSelectedColumn());
    }

    public SUTableCell getTableCell(int rowIndex, int columnIndex) {
        SUTableRow row = getTableRow(rowIndex);
        if (row == null) {
            return null;
        }
        return row.get(columnIndex);
    }

    public SUArrayList<SUTableRow> getSelectedTableRows() {
        int[] selectedRowIndexes = this.getSelectedRows();
        if (selectedRowIndexes.length <= 0) {
            return null;
        }
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        SUArrayList<SUTableRow> rows = new SUArrayList<>();
        for (int selectedRowIndex : selectedRowIndexes) {
            SUTableRow row = (SUTableRow) model.getDataVector().get(selectedRowIndex);
            rows.add(row);
        }
        return rows;
    }

    private SUQueryResult rs;

    public void refreshData(SUQueryResult rs) {
        this.rs = rs;
        SUArrayList<SUTableColumn> columns = rs.getColumns();
        SUArrayList<SUTableRow> rows = rs.getRows();
        SUTableModel model = new SUTableModel(rows, columns);
        setModel(model);
    }
    private static final DatabaseDAOInter db = new DatabaseDAOImpl();

    public void saveEditingRow() {
        SUTableRow row = getTableModel().getEditingRow();
        if (row == null) {
            return;
        }
        SUQueryBean query = rs.getQuery();
        System.out.println("editing row=" + row);
        db.saveRow(query.getConnection(), row);
        row.discardChanges();
        getTableModel().fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        System.out.println("Table is cell editable");
        return true;
    }

}

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
import javax.swing.DefaultListSelectionModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import lombok.Data;
import com.bsptechs.main.util.LogUtil;

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
        DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            int prev = 0;
            int i = 0;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                LogUtil.log("prev=" + prev);
                if (getSelectedRow() < 0) {
                    return;
                }
                i++;

                SUTableRow selectedRow = getTableModel().getTableRows().get(prev);
                prev = getSelectedRow();

                LogUtil.log("Selected: " + selectedRow);
                saveRow(selectedRow);
            }

        });
        this.setSelectionModel(selectionModel);
    }

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
        saveRow(row);
    }

    public void saveRow(SUTableRow row) {
        if (row == null) {
            return;
        }
        SUQueryBean query = rs.getQuery();
        LogUtil.log("editing row=" + row);
        db.saveRow(query.getConnection(), row);
        row.discardEditing();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        LogUtil.log("Table is cell editable");
        return true;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.bean.SUQueryBean;
import com.bsptechs.main.bean.SUQueryResult;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
//class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
//
//    JComponent component = new JTextField();
//
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
//            int rowIndex, int vColIndex) {
//
//        ((JTextField) component).setText((String) value);
//
//        return component;
//    }
//
//    public Object getCellEditorValue() {
//        return ((JTextField) component).getText();
//    }
//}
class MyTableColumnModel extends DefaultTableColumnModel {

    public MyTableColumnModel(SUArrayList<SUTableColumn> columns) {
//        super.tableColumns = new Vector(columns);
//        for (int i = 0; i < columns.size(); i++) {
//            super.addColumn(columns.get(i));
//        }
    }

    public void addColumn(TableColumn aColumn) {
        aColumn.setCellEditor(new SUTableCellEditor());
        super.addColumn(aColumn);

        System.out.println("aColumn.getModelIndex()=" + aColumn.getModelIndex());
        System.out.println("addColumn=" + aColumn);
        System.out.println("identifier=" + aColumn.getIdentifier());
        System.out.println("identifier class =" + aColumn.getIdentifier().getClass().getName());
    }
}

@Data
public class SUTable extends JTable {

    private SUQueryBean query;

    public SUTable() {
        super(new SUTableModel());
        this.setRowHeight(25);
    }  

    @Override
    public void setModel(TableModel m) {
        SUTableModel model = (SUTableModel) m;

        MyTableColumnModel cM = new MyTableColumnModel(model.getColumnNames());
        setColumnModel(cM);
        super.setModel(model);
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

    public List<SUTableRow> getSelectedTableRows() {
        int[] selectedRowIndexes = this.getSelectedRows();
        if (selectedRowIndexes.length <= 0) {
            return null;
        }
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        List<SUTableRow> rows = new ArrayList<>();
        for (int selectedRowIndex : selectedRowIndexes) {
            SUTableRow row = (SUTableRow) model.getDataVector().get(selectedRowIndex);
            rows.add(row);
        }
        return rows;
    }

    public void refreshData(SUQueryResult rs) {
        SUArrayList<SUTableColumn> columns = rs.getColumns();
        SUArrayList<SUTableRow> rows = rs.getRows();
        setModel(new SUTableModel(rows, columns));
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        System.out.println("Table is cell editable");
        return true;
    }

}

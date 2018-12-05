package com.bsptechs.main.bean.ui.table;

import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.bean.SUQueryBean;
import com.bsptechs.main.bean.SUQueryResult;
import com.bsptechs.main.bean.ui.tree.database.bean.SUTableBean;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import java.awt.Component;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author sarkhanrasullu
 */
class SUTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private DatabaseDAOImpl dao = new DatabaseDAOImpl();

    private JComponent component = new JTextField();
    private SUTableCell cell = null;

    @Override
    public Component getTableCellEditorComponent(
            JTable table,
            Object value,
            boolean isSelected,
            int rowIndex,
            int vColIndex
    ) {
        System.out.println("getTableCellEditorComponent=" + value);

        cell = (SUTableCell) value;

        SUTableColumn column = cell.getColumn();
        SUTableBean tableBean = column.getTable();
        if (column.getReferencedColumn() != null) {
            System.out.println("column.getReferencedColumn()=" + column.getReferencedColumn());

            SUQueryResult rs = dao.runQuery(new SUQueryBean(
                    tableBean.getDatabase().getConnection(), tableBean.getDatabase(),
                    "select * from " + column.getReferencedColumn().getTable().getName()));

            SUArrayList<SUTableRow> rows = rs.getRows();
            Vector<SUTableCell> cells = new Vector<>();
            for (SUTableRow row : rows) {
                SUTableCell cell = row.getByColumnName(column.getReferencedColumn().getName());
                cells.add(cell);
            }
            component = new JComboBox<SUTableCell>(cells);
        } else {
            JTextField textField = (JTextField) component;
            textField.setText(cell.getValue() + "");
        }

        return component;
    }

    private String getValue() {
        String str = null;
        if (component instanceof JTextField) {
            JTextField txt = (JTextField) component;
            str = txt.getText();
        } else if (component instanceof JComboBox) {
            JComboBox cb = (JComboBox) component;
            str = cb.getModel().getSelectedItem() + "";
        }
        return str;
    }

    @Override
    public Object getCellEditorValue() {

        cell.setEditing(false);
        System.out.println("class name="+cell.getValue().getClass().getName());
        if (!cell.getValue().toString().equals(getValue())) {
            System.out.println("cell old getValue()=" + cell.getValue());
            System.out.println("cell new getValue()=" + getValue());
            System.out.println("cell is editing mode");
            cell.setEditing(true);
        }
        cell.setValue(getValue());
//        cell.setEditing(false);
//        stopCellEditing();
        return cell;
    }
}

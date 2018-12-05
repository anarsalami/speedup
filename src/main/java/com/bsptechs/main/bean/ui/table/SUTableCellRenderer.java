/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author sarkhanrasullu
 */
public class SUTableCellRenderer extends DefaultTableCellRenderer {

    private int row, col;
    private boolean isSelected;
    private SUTableCell cell;
    private boolean hasFocus = false;
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        // Save row and column information for use in setValue().
        this.cell = (SUTableCell) value;
        this.row = row;
        this.col = column;
        this.isSelected = isSelected;
        this.hasFocus = hasFocus;

        // Allow superclass to return rendering component.
        Component cmp = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus,
                row, column);

        return cmp;
    }

    @Override
    public Color getBackground() {
        if (cell == null) {
            return super.getBackground();
        }
        if (cell.isEditing()) {
            return new Color(255, 221, 238);
        }

        if (hasFocus) {
            return super.getBackground();
        }

        return Color.WHITE;
    }

    @Override
    public Color getForeground() {
        return Color.BLACK;
    }

}

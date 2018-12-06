/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
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

    public SUTableCellRenderer() {
        Border b = BorderFactory.createLineBorder(Color.GREEN);
        setBorder(b);
    }

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
        JComponent cmp = (JComponent) super.getTableCellRendererComponent(table, value,
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
            return new Color(255, 242, 251);
        }

        if (hasFocus) {
            return new Color(66,134,244);
        }

        return Color.WHITE;
    }

    @Override
    public Color getForeground() {
        return Color.BLACK;
    }

}

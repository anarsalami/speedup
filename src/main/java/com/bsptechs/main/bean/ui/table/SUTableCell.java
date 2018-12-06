/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class SUTableCell {

    private SUTableColumn column;
    private String value;
    private boolean editing = false;
    private SUTableListener<SUTableCell> onCellEditing;

    public SUTableCell() {
    }

    public void setOnCellEditing(SUTableListener<SUTableCell> listener) {
        this.onCellEditing = listener;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
        if (onCellEditing != null) {
            this.onCellEditing.action(this);
        }
    }

    public SUTableCell(SUTableColumn column, String value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}

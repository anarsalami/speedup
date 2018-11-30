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
    private Object value;
    private boolean primaryKey;
    private boolean editable = true;
    private boolean updateMode;

    public SUTableCell() {
    }

    public SUTableCell(SUTableColumn column, Object value) {
        this.column = column;
        this.value = value;
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}

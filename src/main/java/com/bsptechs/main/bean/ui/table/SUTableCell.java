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
    private String oldValue;
    private boolean editing = false;
    private SUTableListener<SUTableCell> onChange = e -> {
    };

    
    public SUTableCell(SUTableColumn column, String value) {
        this.column = column;
        this.value = value;
    }

    public void setOnChange(SUTableListener<SUTableCell> listener) {
        this.onChange = listener;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
        this.onChange.action(this);
    }

    public void setValue(String value) {
        if (value == null) {
            return;
        }

        if (oldValue == null) {
            oldValue = value;
        }

        if (value.equals(oldValue)) {
            setEditing(false);
        } else if (!this.value.equals(value)) {
            setEditing(true);
        }
        
        this.value = value;
        this.onChange.action(this);
    }

    

    @Override
    public String toString() {
        return value;
    }

}

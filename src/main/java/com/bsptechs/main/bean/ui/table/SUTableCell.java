/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import lombok.Data;
import com.bsptechs.main.util.LogUtil;
/**
 *
 * @author sarkhanrasullu
 */
@Data
public class SUTableCell {

    private SUTableColumn column;
    private String value;
    private String originalValue;
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

        LogUtil.log("old value=" + originalValue);
        LogUtil.log("new value=" + value);

        if (value.equals(originalValue)) {
            setEditing(false);
        } else if (!this.value.equals(value)) {
            setEditing(true);
        }

        if (originalValue == null) {
            originalValue = value;
        }
        
        this.value = value;
        this.onChange.action(this);
    }

    @Override
    public String toString() {
        return value;
    }

}

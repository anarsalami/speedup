/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import java.util.List;

/**
 *
 * @author sarkhanrasullu
 */
public class TableCell {

    private String columnName;
    private Object columnValue;

    public TableCell() {
    }

    public TableCell(String columnName, Object columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(Object columnValue) {
        this.columnValue = columnValue;
    }

    @Override
    public String toString() {
        if(columnValue==null) return null;
        return columnValue.toString();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import java.util.List;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class TableCell {

    private String table;
    private String databaseName;
    private String columnName;
    private Object columnValue;
    private boolean primaryKey;
    private boolean editable = true;
    private boolean updateMode;
    
    public TableCell() {
    }

    public TableCell(String columnName, Object columnValue,String databaseName, String table,  boolean primaryKey) {
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.primaryKey = primaryKey;
        this.table = table;
        this.databaseName = databaseName;
    } 
    
    @Override
    public String toString() {
        if(columnValue==null) return null;
        return columnValue.toString();
    }
    
    
}

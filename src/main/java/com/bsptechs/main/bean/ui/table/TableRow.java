/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import java.util.List;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class TableRow extends Vector<TableCell> {

    private String databaseName;
    private String tableName;
    private boolean updateMode;
    
    public TableRow(String databaseName, String tableName) {
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public List<TableCell> getAllPrimaryCell() {
        if (isEmpty()) {
            return null;
        }

        List<TableCell> result = new ArrayList<>();
        for (TableCell cell : this) {
            if (cell.isPrimaryKey()) {
                result.add(cell);
            }
        }
        return result;
    }

}

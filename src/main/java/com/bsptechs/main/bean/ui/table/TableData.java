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
public class TableData {

    private List<TableRow> rows;
    private List<String> columns;
    
    public TableData() {
    }

    public TableData(List<TableRow> rows) {
        this.rows = rows;
    }

    public TableData(List<TableRow> rows, List<String> columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
    
    
    

    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "TableData{" + "rows=" + rows + '}';
    }

}

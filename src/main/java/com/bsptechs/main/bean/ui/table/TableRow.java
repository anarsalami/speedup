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
public class TableRow {

    private List<TableCell> cells;

    public TableRow(List<TableCell> cells) {
        this.cells = cells;
    }

    public List<TableCell> getCells() {
        return cells;
    }

    public void setCells(List<TableCell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "TableRow{" + "columns=" + cells + '}';
    }

}

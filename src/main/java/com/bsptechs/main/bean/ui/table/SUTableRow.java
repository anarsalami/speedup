/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.table;

import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.bean.ui.tree.database.bean.SUTableBean;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class SUTableRow extends SUArrayList<SUTableCell> {

    private SUTableBean table;

    public List<SUTableCell> getAllPrimaryCell() {
        if (isEmpty()) {
            return null;
        }

        List<SUTableCell> result = new ArrayList<>();
        for (SUTableCell cell : this) {
            if (cell.isPrimaryKey()) {
                result.add(cell);
            }
        }
        return result;
    }
    
    public SUTableCell getByColumnName(String columnName){
        for(SUTableCell cell: this){
            if(cell.getColumn().getName().equals(columnName)){
                return cell;
            }
        }
        return null;
    }

    @Override
    public boolean add(SUTableCell cell) {
        table = cell.getColumn().getTable();
        return super.add(cell);
    }
    
    @Override
    public String toString(){
        String str = "";
        for(int i=0;i<size();i++){
            str+=get(i).getValue()+" ";
        } 
        return str;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree.database.bean;

import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class SUTableBean {

    private String name;
    private SUDatabaseBean database;
    
    
    public SUTableBean(String name, SUDatabaseBean database){
        this.name = name;
        this.database = database;
    }
}

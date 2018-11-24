/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean;

import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class TableBean {

    private String name;
    private DatabaseBean database;
    
    
    public TableBean(String name, DatabaseBean database){
        this.name = name;
        this.database = database;
    }
}

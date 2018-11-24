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
public class DatabaseBean {

    private String name;
    private ConnectionBean connection;

    @Override
    public String toString() {
        return name;
    }

    public DatabaseBean(String name, ConnectionBean connection) {
        this.name = name;
        this.connection = connection;
    }
    
    

}

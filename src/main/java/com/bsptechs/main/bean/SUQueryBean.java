/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean;

import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUDatabaseBean;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class SUQueryBean {

    private SUConnectionBean connection;
    private SUDatabaseBean database;
    private String query;

    public SUQueryBean(SUConnectionBean connection, SUDatabaseBean database, String query) {
        this.connection = connection;
        this.database = database;
        this.query = query;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.panel.queryresult;

import java.io.Serializable;

/**
 *
 * @author Goshgar
 */
public class SavedQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    private String connectionName;
    private String databaseName;
    private String body;

    public SavedQuery() {
    }

    public SavedQuery(String connectionName, String databaseName, String body) {
        this.connectionName = connectionName;
        this.databaseName = databaseName;
        this.body = body;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}

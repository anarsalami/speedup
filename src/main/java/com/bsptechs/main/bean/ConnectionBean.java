/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.ui.tree.database.node.ConnectionTreeNode;
import com.bsptechs.main.bean.ui.tree.database.node.DatabaseTreeNode;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author sarkhanrasullu
 */
@Data
public class ConnectionBean implements Serializable{

    private String name;
    private String ipAdr;
    private String port;
    private String userName;
    private String password;
    private transient Connection parentConnection;
    private transient List<DatabaseTreeNode> databases;

    public ConnectionBean() {
    }

    public ConnectionBean(String name, String ipAdr, String port, String userName, String password) {
        this.name = name;
        this.ipAdr = ipAdr;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }
     
 

    public void reset() {
        try {
            if (parentConnection != null && !parentConnection.isClosed()) {
                this.parentConnection.close();
                this.parentConnection = null;
            }
            this.databases = null;
            Main.instance().refreshNewQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionTreeNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
}

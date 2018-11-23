package com.bsptechs.main.bean.ui.tree.database.node;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ConnectionBean;
import com.bsptechs.main.bean.ui.popup.UiPopupConnection;
import com.bsptechs.main.bean.ui.tree.node.CustomTreeNode;
import com.bsptechs.main.bean.ui.tree.node.CustomTreeNode;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import javax.swing.JPopupMenu;
import lombok.Data;

@Data
public class ConnectionTreeNode extends CustomTreeNode implements Serializable {

    private ConnectionBean connection;

    public ConnectionTreeNode() {
    }

    public ConnectionTreeNode(String name, String ipAdr, String port, String userName, String password) {
        this.connection = new ConnectionBean(name, ipAdr, port, userName, password);
    }
    
     public ConnectionTreeNode(ConnectionBean connection) {
        this.connection = connection;
    }

    public void connect() {
        if (connection.getDatabases() == null) {
            System.out.println("connection connect");
            connection.setDatabases(database.getAllDatabases(this));
            Main.instance().getConnectionTree().setCurrentConnectionNode(this);
            addChildren(connection.getDatabases());
            Main.instance().refreshNewQuery();
            nodeStructureChanged();
            expand();
        }
    }
    
    public void reset(){
        this.connection.reset();
    }

    @Override
    public void onClick() {
    }

    @Override
    public void onDoubleClick() {
        connect();
    }

    @Override
    public JPopupMenu getPopup() {
        return new UiPopupConnection();
    }

    @Override
    public List<DatabaseTreeNode> getSubList() {
        return connection.getDatabases();
    }

    @Override
    public String getIcon() {
        return "connection.png";
    }

    @Override
    public String toString() {
        return connection.getName();
    }

    public void setName(String name){
        this.connection.setName(name);
    }
    
    public String getName() {
        return connection.getName();
    }

    public ConnectionBean getConnection() {
        return connection;
    }

    public void setConnection(ConnectionBean connection) {
        this.connection = connection;
    }

    public String getIpAdr() {
        return connection.getIpAdr();
    }

    public void setIpAdr(String ipAdr) {
        this.connection.setIpAdr(ipAdr);
    }

    public String getPort() {
        return connection.getPort();
    }

    public void setPort(String port) {
        this.connection.setPort(port);
    }

    public String getUserName() {
        return connection.getUserName();
    }

    public void setUserName(String userName) {
        this.connection.setUserName(userName);
    }

    public String getPassword() {
        return connection.getPassword();
    }

    public void setPassword(String password) {
        this.connection.setPassword(password);
    }

    public Connection getParentConnection() {
        return connection.getParentConnection();
    }

    public void setParentConnection(Connection parentConnection) {
        this.connection.setParentConnection(parentConnection);
    }

    public List<DatabaseTreeNode> getDatabases() {
        return connection.getDatabases();
    }

    public void setDatabases(List<DatabaseTreeNode> databases) {
        this.connection.setDatabases(databases);
    }

}

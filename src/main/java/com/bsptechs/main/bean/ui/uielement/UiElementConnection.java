package com.bsptechs.main.bean.ui.uielement;

import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.popup.UiPopupConnection;
import com.bsptechs.main.bean.ui.uielement.UiElement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPopupMenu;

public class UiElementConnection extends UiElement implements Serializable {

    private String name;
    private String ipAdr;
    private String port;
    private String userName;
    private String password;
    private transient Connection parentConnection;
    private transient List<UiElementDatabase> databases;

    public UiElementConnection() {
    }

    public UiElementConnection(String name, String ipAdr, String port, String userName, String password) {
        this.name = name;
        this.ipAdr = ipAdr;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public List<UiElementDatabase> getDatabases() {
        return databases;
    }

    public void setDatabases(List<UiElementDatabase> databases) {
        this.databases = databases;
    }

    public String getIpAdr() {
        return ipAdr;
    }

    public void setIpAdr(String ipAdr) {
        this.ipAdr = ipAdr;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getParentConnection() {
        return parentConnection;
    }

    public void setParentConnection(Connection parentConnection) {
        this.parentConnection = parentConnection;
    }

    public void reset() {
        try {
            if (parentConnection != null && !parentConnection.isClosed()) {
                this.parentConnection.close();
                this.parentConnection = null;
            }
            this.databases = null;
            Config.getMain().refreshNewQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UiElementConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        if (databases == null) {
            databases = database.getAllDatabases(this);
            Config.setCurrentConnection(this);
            addChildren(databases);
            Config.getMain().refreshNewQuery();
            nodeStructureChanged();
            expand();
        }
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
    public List<UiElementDatabase> getSubList() {
        return getDatabases();
    }

    @Override
    public String getIcon() {
        return "connection.png";
    }

    @Override
    public String toString() {
        return name;
    }
}

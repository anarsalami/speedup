package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.bean.ui.popup.UiPopupConnection;
import com.bsptechs.main.bean.ui.tree.CustomTreeNode;
import com.bsptechs.main.bean.ui.tree.database.bean.ConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.DatabaseBean;
import com.bsptechs.main.bean.CustomList;
import javax.swing.JPopupMenu;
import com.bsptechs.main.Main;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import lombok.Data;

@Data
public class ConnectionTreeNode extends CustomTreeNode implements Serializable {

    private final ConnectionBean connection;
    private final DatabaseJTree tree;

    public ConnectionTreeNode(DatabaseJTree tree, ConnectionBean connection) {
        this.connection = connection;
        this.tree = tree;
    }

    public void connect() {
        if (connection.getDatabases() == null) {
            System.out.println("connection connect");
            connection.setDatabases(database.getAllDatabases(connection));
            tree.setCurrentConnectionNode(this);
            addDatabases(connection.getDatabases());
            Main.instance().refreshNewQuery();
            nodeStructureChanged();
            expand();
        }
    }

    public void addDatabases(List<DatabaseBean> databases) {
        CustomList<DatabaseTreeNode> dbNodes = new CustomList<>();
        for (DatabaseBean db : databases) {
            dbNodes.add(new DatabaseTreeNode(tree, db));
        }
        super.addChildren(dbNodes);
    }

    public CustomList<DatabaseBean> getAllDatabaseBeans() {
        CustomList<DatabaseBean> list = new CustomList<>();
        List<DatabaseTreeNode> l = getChildren(DatabaseTreeNode.class);
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i).getDatabase());
        }
        return list;
    }

    public void reset() {
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
    public String getIcon() {
        return "connection.png";
    }

    @Override
    public String toString() {
        return connection.getName();
    }

//    public void setName(String name) {
//        this.connection.setName(name);
//    }
//
//    public String getName() {
//        return connection.getName();
//    }
//
//    public ConnectionBean getConnection() {
//        return connection;
//    }
//
//    public String getIpAdr() {
//        return connection.getIpAdr();
//    }
//
//    public void setIpAdr(String ipAdr) {
//        this.connection.setIpAdr(ipAdr);
//    }
//
//    public String getPort() {
//        return connection.getPort();
//    }
//
//    public void setPort(String port) {
//        this.connection.setPort(port);
//    }
//
//    public String getUserName() {
//        return connection.getUserName();
//    }
//
//    public void setUserName(String userName) {
//        this.connection.setUserName(userName);
//    }
//
//    public String getPassword() {
//        return connection.getPassword();
//    }
//
//    public void setPassword(String password) {
//        this.connection.setPassword(password);
//    }
//
//    public Connection getParentConnection() {
//        return connection.getParentConnection();
//    }
//
//    public void setParentConnection(Connection parentConnection) {
//        this.connection.setParentConnection(parentConnection);
//    }

}

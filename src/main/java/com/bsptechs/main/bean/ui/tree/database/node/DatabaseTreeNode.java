package com.bsptechs.main.bean.ui.tree.database.node;

import com.bsptechs.main.bean.ui.tree.database.node.ConnectionTreeNode;
import com.bsptechs.main.Main;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.popup.UiPopupDatabase;
import com.bsptechs.main.bean.ui.tree.node.CustomTreeNode;
import com.bsptechs.main.bean.ui.tree.node.CustomTreeNode;
import com.bsptechs.main.bean.ui.tree.database.node.TableTreeNode;
import java.util.List;
import javax.swing.JPopupMenu;

public class DatabaseTreeNode extends CustomTreeNode {

    private String name;
    private ConnectionTreeNode connection;
    private List<TableTreeNode> tables;

    public DatabaseTreeNode() {
    }

    public DatabaseTreeNode(String name, ConnectionTreeNode connection) {
        this.name = name;
        this.connection = connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConnectionTreeNode getConnection() {
        return connection;
    }

    public void setConnection(ConnectionTreeNode connection) {
        this.connection = connection;
    }

    public List<TableTreeNode> getTables() {
        return tables;
    }

    public void setTables(List<TableTreeNode> tables) {
        this.tables = tables;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        Main.instance().getConnectionTree().setCurrentDatabaseNode(this);
        if (tables == null) { 
            tables = database.getAllTables(this);
            addChildren(tables);
            expand();
        }
    }

    @Override
    public JPopupMenu getPopup() {
        return new UiPopupDatabase();
    }

    @Override
    public List<TableTreeNode> getSubList() {
        return getTables();
    }

    @Override
    public String getIcon() {
        return "database.png";
    }
    
     @Override
    public String toString(){
        return name;
    }

}

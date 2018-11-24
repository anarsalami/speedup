package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.CustomList;
import com.bsptechs.main.bean.ui.tree.database.bean.DatabaseBean;
import com.bsptechs.main.bean.ui.tree.database.bean.TableBean;
import com.bsptechs.main.bean.ui.popup.UiPopupDatabase;
import com.bsptechs.main.bean.ui.tree.CustomTreeNode;
import java.util.List;
import javax.swing.JPopupMenu;

public class DatabaseTreeNode extends CustomTreeNode {

    private final DatabaseBean db;
    private final DatabaseJTree tree;

    public DatabaseTreeNode(DatabaseJTree tree, DatabaseBean database) {
        this.db = database;
        this.tree = tree;
    }

    public DatabaseBean getDatabase() {
        return db;
    }

    public CustomList<TableBean> getTableBeans() {
        CustomList<TableBean> list = new CustomList<>();
        List<TableTreeNode> l = getChildren(TableTreeNode.class);
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i).getTable());
        }
        return list;
    }

    public void addTables(List<TableBean> tables) {
        CustomList<TableTreeNode> nodes = new CustomList<>();
        for (TableBean table : tables) {
            nodes.add(new TableTreeNode(tree, table));
        }
        super.addChildren(nodes);
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        Main.instance().getConnectionTree().setCurrentDatabaseNode(this);
        List<TableBean> tables = getTableBeans();
        if (tables.isEmpty()) {
            tables = database.getAllTables(this.db);
            addTables(tables);
            expand();
        }
    }

    @Override
    public JPopupMenu getPopup() {
        return new UiPopupDatabase();
    }

    @Override
    public String getIcon() {
        return "database.png";
    }

    @Override
    public String toString() {
        return db.getName();
    }

}

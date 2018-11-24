package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.CustomList;
import com.bsptechs.main.bean.ui.tree.database.bean.DatabaseBean;
import com.bsptechs.main.bean.ui.tree.database.bean.TableBean;
import com.bsptechs.main.bean.ui.popup.UiPopupDatabase;
import java.util.List;
import javax.swing.JPopupMenu;

public class SUDatabaseTreeNode extends SUAbstractTreeNode {

    private final DatabaseBean database;

    public SUDatabaseTreeNode(SUDatabaseTree tree, DatabaseBean database) {
        super(tree);
        this.database = database;
    }

    public DatabaseBean getDatabase() {
        return database;
    }

    public CustomList<TableBean> getTableBeans() {
        CustomList<TableBean> list = new CustomList<>();
        List<SUTableTreeNode> l = getChildren(SUTableTreeNode.class);
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i).getTable());
        }
        return list;
    }

    public void addTables(List<TableBean> tables) {
        CustomList<SUTableTreeNode> nodes = new CustomList<>();
        for (TableBean table : tables) {
            nodes.add(new SUTableTreeNode(getTree(), table));
        }
        super.addChildren(nodes);
    } 
    
    public SUDatabaseTree getTree(){
        return (SUDatabaseTree) tree;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        Main.instance().getConnectionTree().setCurrentDatabaseNode(this);
        List<TableBean> tables = getTableBeans();
        if (tables.isEmpty()) {
            tables = dao.getAllTables(this.database);
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
        return database.getName();
    }

}

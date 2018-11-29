package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.bean.ui.tree.database.bean.SUDatabaseBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUTableBean;
import com.bsptechs.main.bean.ui.popup.UiPopupDatabase;
import java.util.List;
import javax.swing.JPopupMenu;

public class SUDatabaseTreeNode extends SUAbstractTreeNode {

    private final SUDatabaseBean database;

    public SUDatabaseTreeNode(SUDatabaseTree tree, SUDatabaseBean database) {
        super(tree, database);
        this.database = database;
    }

    public SUDatabaseBean getDatabase() {
        return database;
    }

    public SUArrayList<SUTableBean> getTableBeans() {
        SUArrayList<SUTableBean> list = new SUArrayList<>();
        List<SUTableTreeNode> l = getChildren(SUTableTreeNode.class);
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i).getTable());
        }
        return list;
    }

    public void addTables(List<SUTableBean> tables) {
        SUArrayList<SUTableTreeNode> nodes = new SUArrayList<>();
        for (SUTableBean table : tables) {
            nodes.add(new SUTableTreeNode(getTree(), table));
        }
        super.addChildren(nodes);
    }

    public SUDatabaseTree getTree() {
        return (SUDatabaseTree) tree;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        Main.instance().getConnectionTree().setCurrentDatabaseNode(this);
        List<SUTableBean> tables = getTableBeans();
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

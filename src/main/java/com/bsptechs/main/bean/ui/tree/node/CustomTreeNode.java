package com.bsptechs.main.bean.ui.tree.node;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.CustomList;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public abstract class CustomTreeNode extends DefaultMutableTreeNode {

    private static final long serialVersionUID = 1L;
    public DatabaseDAOInter database = new DatabaseDAOImpl();

    public CustomTreeNode() {
    }

    public abstract void onClick();

    public abstract void onDoubleClick();

    public abstract JPopupMenu getPopup();

//    public abstract List<? extends CustomTreeNode> getSubList();
    public abstract String getIcon();

    public void addChildren(List<? extends CustomTreeNode> listData) {
        if (listData == null) {
            return;
        }

        for (CustomTreeNode t : listData) {
            add(t);
        }

        nodeStructureChanged();

    }

    public <T> List<T> getChildren(Class<T> clazz) {
        Enumeration en = this.children();
        CustomList<T> list = new CustomList<>();
        while (en.hasMoreElements()) {
            list.add((T)en.nextElement());
        }
        return list;
    } 

    @Override
    public void removeAllChildren() {
        super.removeAllChildren();
        nodeStructureChanged();
    }

    public void nodeChanged() {
        Main.instance().getConnectionTree().getTreeModel().nodeChanged(this);
    }

    public void nodeStructureChanged() {
        Main.instance().getConnectionTree().getTreeModel().nodeStructureChanged(this);
    }

    public void expand() {
        Main.instance().getConnectionTree().expandPath(new TreePath(this.getPath()));
    }

}

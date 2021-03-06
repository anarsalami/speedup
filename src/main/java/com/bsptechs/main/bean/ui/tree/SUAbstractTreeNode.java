package com.bsptechs.main.bean.ui.tree;

import com.bsptechs.main.bean.SUArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public abstract class SUAbstractTreeNode extends DefaultMutableTreeNode {

    private static final long serialVersionUID = 1L;
    
    protected final SUAbstractTree tree;
    
    public SUAbstractTreeNode(SUAbstractTree tree) {
        this.tree = tree;
    }

    public abstract void onClick();

    public abstract void onDoubleClick();

    public abstract JPopupMenu getPopup();

    public abstract String getIcon();

    public void addChildren(List<? extends SUAbstractTreeNode> listData) {
        if (listData == null) {
            return;
        }

        for (SUAbstractTreeNode t : listData) {
            add(t);
        }

        nodeStructureChanged();

    }

    public <T> List<T> getChildren(Class<T> clazz) {
        Enumeration en = this.children();
        SUArrayList<T> list = new SUArrayList<>();
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
        tree.getTreeModel().nodeChanged(this);
    }

    public void nodeStructureChanged() {
        tree.getTreeModel().nodeStructureChanged(this);
    }

    public void expand() {
        tree.expandPath(new TreePath(this.getPath()));
    }

}

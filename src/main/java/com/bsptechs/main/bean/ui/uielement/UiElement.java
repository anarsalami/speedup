package com.bsptechs.main.bean.ui.uielement;

import com.bsptechs.main.bean.Config;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import java.util.List;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public abstract class UiElement extends DefaultMutableTreeNode {

    public static DatabaseDAOInter database = new DatabaseDAOImpl();

    public UiElement() {
    }

    public abstract void onClick();

    public abstract void onDoubleClick();

    public abstract JPopupMenu getPopup();

    public abstract List<? extends UiElement> getSubList();

    public abstract String getIcon();

    public void addChildren(List<? extends UiElement> listData) {
        if (listData == null) {
            return;
        }

        for (UiElement t : listData) {
            add(t);
        }
        
        nodeStructureChanged();

    }

    @Override
    public void removeAllChildren() {
        super.removeAllChildren();
        nodeStructureChanged();
    }

    public void nodeChanged() {
        Config.getMain().getListTable().getTreeModel().nodeChanged(this);
    }

    public void nodeStructureChanged() {
        Config.getMain().getListTable().getTreeModel().nodeStructureChanged(this);
    }
    
    
    public void expand(){
       Config.getMain().getListTable().expandPath(new TreePath(this.getPath()));
    }
}

package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUDatabaseBean;
import com.bsptechs.main.bean.ui.popup.UiPopupConnection;
import com.bsptechs.main.bean.SUArrayList;
import javax.swing.JPopupMenu;
import com.bsptechs.main.Main;
import java.util.List;
import lombok.Data;

@Data
public class SUConnectionTreeNode extends SUAbstractTreeNode<SUConnectionBean>{

    private final SUConnectionBean connection = dataBean;
 
    public SUConnectionTreeNode(SUDatabaseTree tree, SUConnectionBean connection){
        super(tree,connection);
    }
    public void connect() { 
        if (connection.getDatabases() == null) {
            System.out.println("connection connect");
            connection.setDatabases(dao.getAllDatabases(connection));
            getTree().setCurrentConnectionNode(this);
            addDatabases(connection.getDatabases());
            Main.instance().refreshNewQuery();
            nodeStructureChanged();
            expand();
        }
    }

    public void addDatabases(List<SUDatabaseBean> databases) {
        SUArrayList<SUDatabaseTreeNode> dbNodes = new SUArrayList<>();
        for (SUDatabaseBean db : databases) {
            dbNodes.add(new SUDatabaseTreeNode(getTree(), db));
        }
        super.addChildren(dbNodes);
    }

    public SUArrayList<SUDatabaseBean> getAllDatabaseBeans() {
        SUArrayList<SUDatabaseBean> list = new SUArrayList<>();
        List<SUDatabaseTreeNode> l = getChildren(SUDatabaseTreeNode.class);
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i).getDatabase());
        }
        return list;
    }

    public SUDatabaseTree getTree(){
        return (SUDatabaseTree) tree;
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

}

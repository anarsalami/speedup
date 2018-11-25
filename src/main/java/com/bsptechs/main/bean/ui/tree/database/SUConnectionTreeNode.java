package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.bean.ui.tree.database.bean.ConnectionBean;
import com.bsptechs.main.bean.ui.tree.database.bean.DatabaseBean;
import com.bsptechs.main.bean.ui.popup.UiPopupConnection;
import com.bsptechs.main.bean.CustomList;
import javax.swing.JPopupMenu;
import com.bsptechs.main.Main;
import java.util.List;
import lombok.Data;

@Data
public class SUConnectionTreeNode extends SUAbstractTreeNode<ConnectionBean>{

    private final ConnectionBean connection = dataBean;
 
    public SUConnectionTreeNode(SUDatabaseTree tree, ConnectionBean connection){
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

    public void addDatabases(List<DatabaseBean> databases) {
        CustomList<SUDatabaseTreeNode> dbNodes = new CustomList<>();
        for (DatabaseBean db : databases) {
            dbNodes.add(new SUDatabaseTreeNode(getTree(), db));
        }
        super.addChildren(dbNodes);
    }

    public CustomList<DatabaseBean> getAllDatabaseBeans() {
        CustomList<DatabaseBean> list = new CustomList<>();
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

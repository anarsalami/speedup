/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.ui.tree.database.bean.ConnectionBean;
import com.bsptechs.main.bean.CustomList;
import com.bsptechs.main.bean.ui.panel.PanelUiElementInformation;
import com.bsptechs.main.bean.ui.tree.AbstractCustomTree;
import com.bsptechs.main.bean.ui.tree.CustomTreeNode;
import com.bsptechs.main.util.MouseUtil;
import java.awt.event.MouseAdapter;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author sarkhanrasullu
 */
public class DatabaseJTree extends AbstractCustomTree {

    private ConnectionTreeNode currentConnectionNode = null;
    private DatabaseTreeNode currentDatabaseNode = null;

    public void addConnectionNode(ConnectionBean connection) {
        System.out.println(connection);
        this.addCustomTreeNodeToRoot(new ConnectionTreeNode(this, connection));
    }

    public CustomList<ConnectionBean> getConnectionBeans() {
        CustomList<ConnectionTreeNode> connections = getConnectionNodes();
        CustomList<ConnectionBean> connectionBeans = new CustomList<ConnectionBean>();
        for (ConnectionTreeNode conn : connections) {
            connectionBeans.add(conn.getConnection());
        }
        return connectionBeans;
    }

    public DatabaseTreeNode getSelectedDatabaseNode() {
        return getSelectedCustomTreeNodeGeneric(DatabaseTreeNode.class);
    }

    public TableTreeNode getSelectedTableNode() {
        return getSelectedCustomTreeNodeGeneric(TableTreeNode.class);
    }

    public ConnectionTreeNode getSelectedConnectionNode() {
        return getSelectedCustomTreeNodeGeneric(ConnectionTreeNode.class);
    }

    public CustomList<ConnectionTreeNode> getConnectionNodes() {
        DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treeModel.getRoot();
        Enumeration en = parentNode.children();

        CustomList<ConnectionTreeNode> list = new CustomList<>();
        while (en.hasMoreElements()) {
            list.add((ConnectionTreeNode) en.nextElement());
        }
        return list;
    }

    public void addConnectionNodes(List<ConnectionBean> connections) {
        for (ConnectionBean cnb : connections) {
            this.addCustomTreeNodeToRoot(new ConnectionTreeNode(this, cnb));
        }
    }

    public void setCurrentConnectionNode(ConnectionTreeNode connection) {
        currentConnectionNode = connection;
    }

    public ConnectionTreeNode getCurrentConnectionNode() {
        return currentConnectionNode;
    }

    public DatabaseTreeNode getCurrentDatabaseNode() {
        return currentDatabaseNode;
    }

    public void setCurrentDatabaseNode(DatabaseTreeNode currentDatabaseNode) {
        this.currentDatabaseNode = currentDatabaseNode;
    }

    public boolean hasAnyActiveConnection() {
        List<ConnectionBean> l = this.getConnectionBeans();
        boolean found = false;
        for (int i = 0; i < l.size(); i++) {
            ConnectionBean cn = l.get(i);
            if (cn.getParentConnection() != null) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    protected MouseAdapter getAdapter() {

        MouseAdapter m = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                CustomTreeNode element = getSelectedCustomTreeNode();
                if (element == null) {
                    return;
                }
                if (MouseUtil.isRightClicked(e)) {
                    element.getPopup().show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultMutableTreeNode selectedUiElement = getSelectedNode();
                if (selectedUiElement == null || !(selectedUiElement instanceof CustomTreeNode)) {
                    return;
                }
                CustomTreeNode element = (CustomTreeNode) selectedUiElement;

                if (MouseUtil.isLeftDoubleClicked(evt)) {
                    element.onDoubleClick();
                }

                if (MouseUtil.isLeftClicked(evt)) {
                    System.out.println("left clicked");
                    PanelUiElementInformation pnlInfor = Main.instance().getInformationPanel();
                    pnlInfor.preparePanel(element);
                }
            }
        };

        return m;
    }

}

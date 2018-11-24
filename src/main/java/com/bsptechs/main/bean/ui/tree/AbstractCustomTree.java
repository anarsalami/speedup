/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author sarkhanrasullu
 */
public abstract class AbstractCustomTree extends JTree {

    public AbstractCustomTree() {
        this.addMouseListener(getAdapter());
        this.setCellRenderer(new CustomTreeCellRenderer());
        this.setRootVisible(false);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root node, should be invisible");
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootNode);
        this.setModel(defaultTreeModel);
    }

    public DefaultMutableTreeNode getSelectedNode() {
        TreePath selectionPath = this.getSelectionPath();
        if (selectionPath != null) {
            DefaultMutableTreeNode obj = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
            return obj;
        }
        return null;
    }

    protected <T> T getSelectedCustomTreeNodeGeneric(Class<T> clazz) {
        DefaultMutableTreeNode obj = getSelectedNode();
        if (obj != null && clazz.isInstance(obj)) {
            return (T) obj;
        }
        return null;
    }

    public CustomTreeNode getSelectedCustomTreeNode() {
        DefaultMutableTreeNode obj = getSelectedNode();
        if (obj != null && obj instanceof CustomTreeNode) {
            return (CustomTreeNode) obj;
        }
        return null;
    }

    public void removeCustomTreeNode(CustomTreeNode element) {
        getTreeModel().removeNodeFromParent(element);
    }

    public DefaultTreeModel getTreeModel() {
        return (DefaultTreeModel) this.getModel();
    }

    public DefaultMutableTreeNode addCustomTreeNodeToRoot(CustomTreeNode node) {
        DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treeModel.getRoot();
        return addCustomTreeNode(treeModel, parentNode, node);
    }

    private DefaultMutableTreeNode addCustomTreeNode(DefaultMutableTreeNode parentNode, CustomTreeNode node) {
        DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
        return addCustomTreeNode(treeModel, parentNode, node);
    }

    private DefaultMutableTreeNode addCustomTreeNode(DefaultTreeModel treeModel, DefaultMutableTreeNode parentNode, DefaultMutableTreeNode node) {
        treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());
        if (parentNode == treeModel.getRoot()) {
            treeModel.nodeStructureChanged((TreeNode) treeModel.getRoot());
        }
        return node;
    }

    public void fillTreeAsRoot(List<? extends CustomTreeNode> listData) {

        if (listData == null) {
            return;
        }

        for (CustomTreeNode node : listData) {
            DefaultMutableTreeNode addEl = addCustomTreeNodeToRoot(node);
//            if (node.getSubList() != null) {
//                fillTree(node.getSubList(), addEl);
//            }
        }
    }

    protected abstract MouseAdapter getAdapter();

}

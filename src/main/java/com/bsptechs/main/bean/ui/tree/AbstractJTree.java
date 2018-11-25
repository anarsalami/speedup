/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree;

import com.bsptechs.main.bean.ui.uielement.UiElement;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author sarkhanrasullu
 */
public abstract class AbstractJTree extends JTree {

    private static DefaultMutableTreeNode root = null;

    public AbstractJTree() {
        this.addMouseListener(getAdapter());
        this.setCellRenderer(new CustomTreeCellRenderer());
        root = getSelectedRoot();
        System.out.println("root="+root);
    }

    public DefaultMutableTreeNode getSelectedNode() {
        TreePath selectionPath = this.getSelectionPath();
        if (selectionPath != null) {
            DefaultMutableTreeNode obj = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
            return obj;
        }
        return null;
    }

    public DefaultMutableTreeNode getRoot() {
        return root;
    }
    
    public void setRoot(DefaultMutableTreeNode root){
        this.root = root;
    }

    
    public DefaultMutableTreeNode getSelectedRoot() {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        return root;
    }

//    public DefaultMutableTreeNode findNode(String nodeTitle) {
//        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
//        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//        Enumeration e = root.children();
//        while (e.hasMoreElements()) {
//            UiElement uiElement = (UiElement) e.nextElement();
//            System.out.println(uiElement);
//            if (uiElement.toString().equals(nodeTitle)) {
//                return uiElement;
//            }
//        }
//        return null;
//    }

    public UiElement getSelectedUiElement() {
        DefaultMutableTreeNode obj = getSelectedNode();
        if (obj != null && obj instanceof UiElement) {
            return (UiElement) obj;
        }
        return null;
    }


    public DefaultMutableTreeNode addUiElement(UiElement element) {
        DefaultMutableTreeNode root = getRoot();
        return addUiElement(element, root);
    }
    
    public void removeUiElement(UiElement element){
       getTreeModel().removeNodeFromParent(element);
    }

       public DefaultTreeModel getTreeModel(){
        return (DefaultTreeModel) this.getModel();
    }
    
   
    
    public DefaultMutableTreeNode addUiElement(UiElement element, DefaultMutableTreeNode root) {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        model.insertNodeInto(element, root, root.getChildCount());
        return element;
    }

//    public DefaultMutableTreeNode addUiElement(UiElement element, String rootName) {
//        DefaultMutableTreeNode root = findNode(rootName);
//        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
//        model.insertNodeInto(element, root, root.getChildCount());
//        return element;
//    }

    public void fillTree(List<? extends UiElement> listData, DefaultMutableTreeNode root) {
        if (listData == null) {
            return;
        }

        for (UiElement t : listData) {
            DefaultMutableTreeNode addEl = addUiElement(t, root);
            System.out.println("connection=" + addEl);
            if (t.getSubList() != null) {
                fillTree(t.getSubList(), addEl);
            }
        }
    }

    public void fillTree(List<? extends UiElement> listData) {
        fillTree(listData, getRoot());
    }
 
    protected abstract MouseAdapter getAdapter();
}

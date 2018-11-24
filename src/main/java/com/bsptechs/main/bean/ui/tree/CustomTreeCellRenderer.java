/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree;

import com.bsptechs.main.bean.ui.tree.node.CustomTreeNode;
import com.bsptechs.main.util.ImageUtil;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author sarkhanrasullu
 */
public class CustomTreeCellRenderer implements TreeCellRenderer {

    private JLabel label;

    CustomTreeCellRenderer() {
        label = new JLabel();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        if(value instanceof CustomTreeNode){
            CustomTreeNode el = (CustomTreeNode) value;
            label.setIcon(ImageUtil.getIcon(el.getIcon()));
            label.setText(el.toString());
        }

        return label;
    }
}

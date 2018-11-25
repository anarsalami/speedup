/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree;

import com.bsptechs.main.bean.ui.uielement.UiElement;
import com.bsptechs.main.util.MouseUtil;
import java.awt.event.MouseAdapter;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author sarkhanrasullu
 */
public class CustomJTree extends AbstractJTree {

    @Override
    protected MouseAdapter getAdapter() {

        MouseAdapter m = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                UiElement element = getSelectedUiElement();
                if (element == null) {
                    return;
                }
                if (MouseUtil.isRightClicked(e)) {
                    element.getPopup().show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (MouseUtil.isLeftDoubleClicked(evt)) {
                    DefaultMutableTreeNode selectedUiElement = getSelectedNode();
                    if (selectedUiElement == null || !(selectedUiElement instanceof UiElement)) {
                        return;
                    }
                    UiElement element = (UiElement) selectedUiElement;

                    element.onDoubleClick();
                }
            }
        };

        return m;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree.database.node;

import com.bsptechs.main.bean.ui.panel.PanelQuery;
import com.bsptechs.main.bean.ui.popup.UiPopupTable;
import com.bsptechs.main.bean.ui.tree.node.CustomTreeNode;
import java.util.List;
import javax.swing.JPopupMenu;
import lombok.Data;

/**
 *
 * @author Goshgar
 */
@Data
public class TableTreeNode extends CustomTreeNode {

    private String name;
    private DatabaseTreeNode database;

    public TableTreeNode(String name, DatabaseTreeNode database) {
        this.name = name;
        this.database = database;
    }
 
    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        PanelQuery.runQuery("select * from " + this.getName());
    }

    @Override
    public JPopupMenu getPopup() {
        return new UiPopupTable();
    }

    @Override
    public List<? extends CustomTreeNode> getSubList() {
        return null;
    }

    @Override
    public String getIcon() {
        return "table.png";
    }

    @Override
    public String toString() {
        return name;
    }
}

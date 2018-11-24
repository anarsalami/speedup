/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.tree.database;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.ui.tree.database.bean.TableBean;
import com.bsptechs.main.bean.ui.popup.UiPopupTable;
import javax.swing.JPopupMenu;
import lombok.Data;

/**
 *
 * @author Goshgar
 */
@Data
public class SUTableTreeNode extends SUAbstractTreeNode {

    private final TableBean table;

    public SUTableTreeNode(SUDatabaseTree tree, TableBean table) {
        super(tree);
        this.table = table;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        Main.instance().prepareNewQuery("select * from " + this.table.getName(), true);
    }

    @Override
    public JPopupMenu getPopup() {
        return new UiPopupTable();
    }

    @Override
    public String getIcon() {
        return "table.png";
    }

    @Override
    public String toString() {
        return table.getName();
    }
}

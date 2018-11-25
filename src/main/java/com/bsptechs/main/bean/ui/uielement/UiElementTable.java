/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.uielement;

import com.bsptechs.main.bean.ui.panel.PanelQuery;
import com.bsptechs.main.bean.ui.popup.UiPopupTable;
import com.bsptechs.main.bean.ui.uielement.UiElement;
import java.util.List;
import javax.swing.JPopupMenu;

/**
 *
 * @author Goshgar
 */
public class UiElementTable extends UiElement {

    private String tableName;
    private UiElementDatabase databaseName;

    public UiElementTable(String tableName, UiElementDatabase databaseName) {
        this.tableName = tableName;
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UiElementDatabase getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(UiElementDatabase databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        PanelQuery.runQuery("select * from " + this.getTableName());
    }

    @Override
    public JPopupMenu getPopup() {
        return new UiPopupTable();
    }

    @Override
    public List<? extends UiElement> getSubList() {
        return null;
    }

    @Override
    public String getIcon() {
        return "table.png";
    }

    @Override
    public String toString() {
        return tableName;
    }
}

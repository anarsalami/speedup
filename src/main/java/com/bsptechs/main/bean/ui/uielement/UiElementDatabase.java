package com.bsptechs.main.bean.ui.uielement;

import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.popup.UiPopupDatabase;
import com.bsptechs.main.bean.ui.uielement.UiElement;
import java.util.List;
import javax.swing.JPopupMenu;

public class UiElementDatabase extends UiElement {

    private String name;
    private UiElementConnection connection;
    private List<UiElementTable> tables;

    public UiElementDatabase() {
    }

    public UiElementDatabase(String name, UiElementConnection connection) {
        this.name = name;
        this.connection = connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UiElementConnection getConnection() {
        return connection;
    }

    public void setConnection(UiElementConnection connection) {
        this.connection = connection;
    }

    public List<UiElementTable> getTables() {
        return tables;
    }

    public void setTables(List<UiElementTable> tables) {
        this.tables = tables;
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onDoubleClick() {
        Config.setCurrentDatabaseName(this);
        if (tables == null) {
            tables = database.getAllTables(this);
            addChildren(tables);
            expand();
        }
    }

    @Override
    public JPopupMenu getPopup() {
        return new UiPopupDatabase();
    }

    @Override
    public List<UiElementTable> getSubList() {
        return getTables();
    }

    @Override
    public String getIcon() {
        return "database.png";
    }
    
     @Override
    public String toString(){
        return name;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.popup;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.ui.panel.PanelQuery;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.uielement.UiElementTable;
import com.bsptechs.main.bean.ui.uielement.UiElement;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sarkhanrasullu
 */
public class UiPopupTable extends UiPopupAbstract {

    private static DatabaseDAOInter database = new DatabaseDAOImpl();

    public UiPopupTable() {

        addMenuItem("Delete Table", () -> {
            delete();
        });
        addMenuItem("Table Properties", () -> {
            properties();
        });

        addMenuItem("New Query", () -> {
            newQuery();
        });

        addMenuItem("View Table", () -> {
            viewTable();
        });
        addMenuItem("Rename", () -> {
            renameTable();
        });
        addMenuItem("Refresh", () -> {
            refreshDB();
        });
        addMenuItem("Empty Table", () -> {
            emptyTable();
        });
        addMenuItem("Truncate Table", () -> {
            truncateTeable();
        });
        addMenuItem("Copy", () -> {
            copyTable();
        });
        addMenuItem("Paste", () -> {
            pasteTable();
        });
        addMenuItem("Dublicate Table", () -> {
            dublicateTable();
        });
        addMenuItem("Dump Sql file", () -> {
            dumpSqlFile();
        });
        addMenuItem("Object Information", () -> {
            objectInformation();
        });

    }

    public void delete() {
        System.out.println("table delete");
        //Tebriz burani dolduracaq
    }

    public void properties() {
        System.out.println("table properties");
        //Tebriz burani dolduracaq
    }

    public void newQuery() {
        System.out.println("new query");
        //Tebriz burani dolduracaq
    }

    public void viewTable() {
        Main m = Config.getMain();

        UiElement element = (UiElement) m.getListTable().getSelectionPath().getLastPathComponent();

        if (element instanceof UiElementTable) {
            UiElementTable tb = (UiElementTable) element;
            PanelQuery.runQuery("select * from " + tb.getTableName());
        }
    }

    public UiElementTable getSelectedTable() {
        return (UiElementTable) getSelectedElement();
    }

    public void renameTable() {
        UiElementTable tb = getSelectedTable();
        String newTblName = (String) JOptionPane.showInputDialog(
                null,
                "Enter new name:",
                "Rename Table",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                tb.getTableName()
        );
        database.renameTable(tb, newTblName);
       tb.nodeChanged();
    }

    private void refreshDB() {
        UiElementTable tb = getSelectedTable();
        List<UiElementTable> tbNames = database.getAllTables(tb.getDatabaseName());

        Main m = Config.getMain();
    }

    private void emptyTable() {
        UiElementTable tb = getSelectedTable();
        database.emptyTable(tb.getDatabaseName(), tb.getTableName());
    }

    private void truncateTeable() {
        UiElementTable tb = getSelectedTable();
        database.truncateTable(tb.getDatabaseName(), tb.getTableName());
    }

    private UiElementTable selectedElementForCopy;

    private void copyTable() {
        UiElementTable tb = getSelectedTable();
        this.selectedElementForCopy = tb;
    }

    private void pasteTable() {
        UiElementTable tb = getSelectedTable();

        String newTblName = (String) JOptionPane.showInputDialog(
                null,
                "Enter name:",
                "Paste Table",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                tb.getTableName()
        );

        database.pasteTable(
                selectedElementForCopy.getDatabaseName() + "." + selectedElementForCopy.getTableName(),
                tb.getDatabaseName(),
                newTblName
        );
        refreshDB();
    }

    private void dublicateTable() {
        UiElementTable tb = getSelectedTable();
        database.dublicateTable(tb.getDatabaseName(), tb.getTableName());
        refreshDB();
    }

    private void dumpSqlFile() {
    }

    private void objectInformation() {
    }
}

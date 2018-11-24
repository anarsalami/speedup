/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.popup;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.ui.panel.queryresult.PanelQuery;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.tree.database.node.TableTreeNode;
import com.bsptechs.main.bean.ui.tree.node.CustomTreeNode;
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

        CustomTreeNode element = (CustomTreeNode) Main.instance().getConnectionTree().getSelectionPath().getLastPathComponent();

        if (element instanceof TableTreeNode) {
            TableTreeNode tb = (TableTreeNode) element;
            Main.instance().prepareNewQuery("select * from "+tb.getName(), true);
        }
    }

    public TableTreeNode getSelectedTable() {
        return (TableTreeNode) getSelectedElement();
    }

    public void renameTable() {
        TableTreeNode tb = getSelectedTable();
        String newTblName = (String) JOptionPane.showInputDialog(
                null,
                "Enter new name:",
                "Rename Table",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                tb.getName()
        );
        database.renameTable(tb, newTblName);
       tb.nodeChanged();
    }

    private void refreshDB() {
        TableTreeNode tb = getSelectedTable();
        List<TableTreeNode> tbNames = database.getAllTables(tb.getDatabase());
    }

    private void emptyTable() {
        TableTreeNode tb = getSelectedTable();
        database.emptyTable(tb.getDatabase(), tb.getName());
    }

    private void truncateTeable() {
        TableTreeNode tb = getSelectedTable();
        database.truncateTable(tb.getDatabase(), tb.getName());
    }

    private TableTreeNode selectedElementForCopy;

    private void copyTable() {
        TableTreeNode tb = getSelectedTable();
        this.selectedElementForCopy = tb;
    }

    private void pasteTable() {
        TableTreeNode tb = getSelectedTable();

        String newTblName = (String) JOptionPane.showInputDialog(
                null,
                "Enter name:",
                "Paste Table",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                tb.getName()
        );

        database.pasteTable(
                selectedElementForCopy.getDatabase() + "." + selectedElementForCopy.getName(),
                tb.getDatabase(),
                newTblName
        );
        refreshDB();
    }

    private void dublicateTable() {
        TableTreeNode tb = getSelectedTable();
        database.dublicateTable(tb.getDatabase(), tb.getName());
        refreshDB();
    }

    private void dumpSqlFile() {
    }

    private void objectInformation() {
    }
}

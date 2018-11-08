/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.popup;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.TableName;
import com.bsptechs.main.bean.UiElement;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import com.bsptechs.main.popup.file.FileUtility;
import com.bsptechs.main.util.ui.MainFrameUtility;
import java.awt.Frame;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sarkhanrasullu
 */
public class UiPopupTable extends UiPopupAbstract {

    private static DatabaseDAOInter database = new DatabaseDAOImpl();
    private JList list;
    private JPanel pane;
    private JFrame frame;

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

    private UiPopupTable(Frame frame, JList list, JPanel pane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
 UiElement element = (UiElement) m.getListTable().getSelectedValue();

        if (element.getData() instanceof TableName) {
            TableName tb = (TableName) element.getData();
            MainFrameUtility.runQuery("select * from " + tb.getTableName());
        }
    }

    public void renameTable() {
        TableName tb = MainFrameUtility.getSelectedTableFromList();
        String newTblName = (String) JOptionPane.showInputDialog(null, "Enter new name:", "Rename Table",
                JOptionPane.QUESTION_MESSAGE, null, null, tb.getTableName());
        database.renameTable(tb, newTblName);
        List<TableName> tbNames = database.getAllTables(tb.getDatabaseName());
        MainFrameUtility.fillList(tbNames, frame, new UiPopupTable(frame, list, pane), "table", list);
    }

    private void refreshDB() {
        int selectedIndex = list.getSelectedIndex();
        UiElement selectedElement = (UiElement) list.getModel().getElementAt(selectedIndex);
        TableName tb = (TableName) selectedElement.getData();
        List<TableName> tbNames = database.getAllTables(tb.getDatabaseName());
        Main m = Config.getMain();
        MainFrameUtility.fillList(tbNames, m, new UiPopupTable(), "table", m.getListTable());
    }

    private void emptyTable() {
        TableName tb = MainFrameUtility.getSelectedTableFromList();
        database.emptyTable(tb.getDatabaseName().getName(), tb.getTableName());
    }

    private void truncateTeable() {
        TableName tb = MainFrameUtility.getSelectedTableFromList();
        database.truncateTable(tb.getDatabaseName().getName(), tb.getTableName());
    }

    private void copyTable() {
        TableName tb = MainFrameUtility.getSelectedTableFromList();
        FileUtility.writeDBAndTblNameFile(tb.getDatabaseName().getName(), tb.getTableName());
    }

    private void pasteTable() {
        TableName tb = MainFrameUtility.getSelectedTableFromList();

        String newTblName = (String) JOptionPane.showInputDialog(null, "Enter name:", "Paste Table",
                JOptionPane.QUESTION_MESSAGE, null, null, tb.getTableName());
        database.pasteTable(FileUtility.readDBAndTblName(), tb.getDatabaseName().getName(), newTblName);
        refreshDB();
    }

    private void dublicateTable() {
        TableName tb = MainFrameUtility.getSelectedTableFromList();
        database.dublicateTable(tb.getDatabaseName().getName(), tb.getTableName());
        refreshDB();
    }

    private void dumpSqlFile() {
    }

    private void objectInformation() {
    }
}

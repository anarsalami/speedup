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
import com.bsptechs.main.util.ui.MainFrameUtility;
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
            MainFrameUtility.runQuery("select * from "+tb.getTableName());
        }
        //Tebriz burani dolduracaq
    }

    public void renameTable() {
        TableName tb = MainFrameUtility.getSelectedTableFromList();
        String newTblName = (String) JOptionPane.showInputDialog(null, "Enter new name:", "Rename Table",
                JOptionPane.QUESTION_MESSAGE, null, null, tb.getTableName());
        database.renameTable(tb, newTblName);

        List<TableName> tbNames = database.getAllTables(tb.getDatabaseName());
        
        Main m = Config.getMain();
        MainFrameUtility.fillList(tbNames, m, new UiPopupTable(), "table", m.getListTable());
    }
}

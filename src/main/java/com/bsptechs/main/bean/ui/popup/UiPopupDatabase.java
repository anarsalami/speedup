/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.popup;

import com.bsptechs.main.Main;
import com.bsptechs.main.bean.ui.panel.PanelQuery;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import javax.swing.JList;
import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.uielement.UiElement;
import com.bsptechs.main.bean.ui.uielement.UiElementDatabase;
import com.bsptechs.main.util.FileUtility;
import com.bsptechs.main.util.Util;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author sarkhanrasullu
 */
public class UiPopupDatabase extends UiPopupAbstract {

    DatabaseDAOImpl database = new DatabaseDAOImpl();

    JList list;

    public UiPopupDatabase() {
        addMenuItem("Database Properties", () -> {
            properties();
        });
        addMenuItem("Delete Database", () -> {
            delete();
        });
        addMenuItem("New Query", () -> {
            try {
                newQuery();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UiPopupDatabase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UiPopupDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        addMenuItem("Dump SQL file", () -> {
            try {
                dumpSQLFile();
            } catch (IOException ex) {
                Logger.getLogger(UiPopupDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void delete() {
        System.out.println("delete database");
        //Tebriz burani dolduracaq
    }

    public void properties() {
        System.out.println("properites database");
        //Tebriz burani dolduracaq
    }

    public void newQuery() throws ClassNotFoundException, SQLException {
        System.out.println("new query");
        UiElementDatabase db = Config.getCurrentDatabaseName();
        Util.addPanelToTab(Config.getMain().getTabPaneTable(), new PanelQuery(null, db), "Query");
    }

    private void dumpSQLFile() throws IOException {
        Config n = (Config) FileUtility.readFileDeserialize("mySql.txt");
        Main m = Config.getMain();
        UiElementDatabase element = (UiElementDatabase) m.getListTable().getSelectedUiElement();
        String dbName = element.getName();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/Users/Goshgar/Documents/" + dbName));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                String source = chooser.getCurrentDirectory() + "//" + chooser.getSelectedFile().getName();
                String executeCmd = "";
                executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -u " + Config.getCurrentConnection().getUserName() + " -p" + Config.getCurrentConnection().getPassword() + " " + dbName + " -r " + source + ".sql";
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(executeCmd, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

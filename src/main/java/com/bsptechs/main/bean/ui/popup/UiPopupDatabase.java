/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.popup;

import com.bsptechs.main.Main;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import javax.swing.JList;
import com.bsptechs.main.bean.ui.tree.database.SUConnectionTreeNode;
import java.io.IOException;

/**
 *
 * @author sarkhanrasullu
 */
public class UiPopupDatabase extends UiPopupAbstract {

    DatabaseDAOImpl database = new DatabaseDAOImpl();

    JList list;
      SUConnectionTreeNode connection=(SUConnectionTreeNode) Main.instance().getConnectionTree().getSelectedNode();;

    public UiPopupDatabase() {
        addMenuItem("Database Properties", () -> {
            properties();
        });
        addMenuItem("Delete Database", () -> {
            delete();
        });
        addMenuItem("New Query", () -> {
            newQuery();
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

    public void newQuery() {
        System.out.println("new query");
        Main.instance().prepareNewQuery(null, false);
    }
  private void dumpSQLFile() throws IOException {
      

//        
//        SUDatabaseBean  element=;
//        String dbName = element.getName();
//        JFileChooser chooser = new JFileChooser();
//        chooser.setCurrentDirectory(new File("/Users/Goshgar/Documents/" + dbName));
//        int retrival = chooser.showSaveDialog(null);
//        if (retrival == JFileChooser.APPROVE_OPTION) {
//            try {
//                String source = chooser.getCurrentDirectory() + "//" + chooser.getSelectedFile().getName();
//                String executeCmd = "";
//                executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -u " + connection. + " -p" + Config.getCurrentConnection().getPassword() + " " + dbName + " -r " + source + ".sql";
//                Runtime runtime = Runtime.getRuntime();
//                runtime.exec(executeCmd, null);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
    }
  
}

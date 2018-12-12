/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.panel;

import com.bsptechs.main.Config;
import com.bsptechs.main.Main;
import com.bsptechs.main.bean.SUArrayList;
import com.bsptechs.main.bean.SUQueryBean;
import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import javax.swing.DefaultListModel;

/**
 *
 * @author Goshgar
 */
public class PanelSavedQuery extends javax.swing.JPanel {

    /**
     * Creates new form PanelSavedQuery
     */
    SUConnectionBean selectedConnection = Main.instance().getConnectionTree().getCurrentConnectionNode().getConnection();
   // SUDatabaseBean selectedDatabase = Main.instance().getConnectionTree().getCurrentDatabaseNode().getDatabase();

    public PanelSavedQuery() {
        initComponents();
        fillQueryList();
    }

    private void fillQueryList() {
        Config.initialize();
        SUArrayList<SUQueryBean> savedQueries = Config.getQueryBeans();
        DefaultListModel model = new DefaultListModel();
        for (SUQueryBean queries : savedQueries) {
            model.addElement(queries.getName());
        }
        savedQueriesJList.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        savedQueriesJList = new javax.swing.JList<>();

        savedQueriesJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                savedQueriesJListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(savedQueriesJList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void savedQueriesJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_savedQueriesJListValueChanged

//        Main.instance().prepareNewQuery(queryBean.getQuery(), true);
    }//GEN-LAST:event_savedQueriesJListValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<SUQueryBean> savedQueriesJList;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main;

import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.NConnection;
import com.bsptechs.main.bean.table.TableData;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import com.bsptechs.main.util.ui.MainFrameUtility;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sarkhanrasullu
 */
public class PanelQuery extends javax.swing.JPanel {

    public static Connection conn;

    private static DatabaseDAOInter database = new DatabaseDAOImpl();

    public PanelQuery() throws ClassNotFoundException, SQLException {

        initComponents();
        List<NConnection> list = Config.instance().getConnections();
        jComboBoxconnections.addItem("");
        for (int i = 0; i < list.size(); i++) {
            NConnection c = list.get(i);
            jComboBoxconnections.addItem(c.getName());
        }
        jComboBoxtables.addItem("");
        List<String> listdatabase = database.getAllDatabases();
        for (String text : listdatabase) {
            jComboBoxtables.addItem(text);
        }
        pnlTable.setVisible(false);
    }

    public void refreshMyTable(List<String> f) {
        DefaultTableModel dtm = new DefaultTableModel();
        Vector<String> columns = new Vector<>();
        for (int i = 0; i < MainFrameUtility.columname.size(); i++) {
            columns.add(MainFrameUtility.columname.get(i));
        }
        Vector<Vector<String>> data = new Vector<>();
        for (int i = 0; i < f.size(); i++) {
            // FilesAndFolders ff = f.get(i);
            Vector<String> sVector = new Vector<>();
            //sVector.add(ff.getPath());
            //List<User> usr = alluserforstatus(1);
            // Integer row = cmbboxUsersList.getSelectedIndex();
            // String username = usr.get(row).getUsername();
            // sVector.add(username);
            data.add(sVector);
        }
        dtm.setDataVector(data, columns);
        tblQueryResult.setModel(dtm);

    }

    public void btnenter(JButton btn) {
        btn.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.white));
    }

    public void btnexit(JButton btn) {
        btn.setBorder(null);
    }

    /**
     * Creates new form PanelQuery
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRun = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        jComboBoxconnections = new javax.swing.JComboBox<>();
        jComboBoxtables = new javax.swing.JComboBox<>();
        btnstop = new javax.swing.JButton();
        btnexplain = new javax.swing.JButton();
        pnlTable = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblQueryResult = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnQueryBuilder = new javax.swing.JButton();
        btnBeautfySQL = new javax.swing.JButton();
        btnCodeSnipped = new javax.swing.JButton();
        btnText = new javax.swing.JButton();
        btnExportResult = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        btnRun.setText("Run");
        btnRun.setBorder(null);
        btnRun.setContentAreaFilled(false);
        btnRun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRunMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRunMouseEntered(evt);
            }
        });
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        txtQuery.setColumns(20);
        txtQuery.setRows(5);
        jScrollPane1.setViewportView(txtQuery);

        jComboBoxconnections.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxconnections.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxconnectionsActionPerformed(evt);
            }
        });

        jComboBoxtables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxtablesActionPerformed(evt);
            }
        });

        btnstop.setText("Stop");
        btnstop.setBorder(null);
        btnstop.setContentAreaFilled(false);
        btnstop.setPreferredSize(new java.awt.Dimension(51, 23));
        btnstop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnstopMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnstopMouseExited(evt);
            }
        });

        btnexplain.setText("Explain");
        btnexplain.setBorder(null);
        btnexplain.setContentAreaFilled(false);
        btnexplain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnexplainMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnexplainMouseExited(evt);
            }
        });

        tblQueryResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblQueryResult);

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );

        btnQueryBuilder.setText("Query bulder");
        btnQueryBuilder.setBorder(null);
        btnQueryBuilder.setContentAreaFilled(false);
        btnQueryBuilder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQueryBuilderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQueryBuilderMouseExited(evt);
            }
        });

        btnBeautfySQL.setText("Beautfy SQL");
        btnBeautfySQL.setBorder(null);
        btnBeautfySQL.setContentAreaFilled(false);
        btnBeautfySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBeautfySQLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBeautfySQLMouseExited(evt);
            }
        });

        btnCodeSnipped.setText("Code Snipped");
        btnCodeSnipped.setBorder(null);
        btnCodeSnipped.setContentAreaFilled(false);
        btnCodeSnipped.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCodeSnippedMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCodeSnippedMouseExited(evt);
            }
        });

        btnText.setText("Text");
        btnText.setBorder(null);
        btnText.setContentAreaFilled(false);
        btnText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTextMouseExited(evt);
            }
        });
        btnText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTextActionPerformed(evt);
            }
        });

        btnExportResult.setText("Export Result");
        btnExportResult.setBorder(null);
        btnExportResult.setContentAreaFilled(false);
        btnExportResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportResultMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportResultMouseExited(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setBorder(null);
        btnSave.setContentAreaFilled(false);
        btnSave.setMaximumSize(new java.awt.Dimension(39, 17));
        btnSave.setMinimumSize(new java.awt.Dimension(39, 17));
        btnSave.setPreferredSize(new java.awt.Dimension(44, 17));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnQueryBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnBeautfySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCodeSnipped, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnExportResult, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQueryBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBeautfySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCodeSnipped, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxconnections, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxtables, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnexplain, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxconnections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxtables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexplain, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setQuery(String txt) {
        txtQuery.setText(txt);
    }

    public void runQuery() {
        pnlTable.setVisible(true);
        try {
            TableData data = database.runQuery(txtQuery.getText());
            DefaultTableModel model = MainFrameUtility.buildTableModel(data);
            tblQueryResult.setModel(model);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PanelQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PanelQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        runQuery();
    }//GEN-LAST:event_btnRunActionPerformed

    private void jComboBoxtablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxtablesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxtablesActionPerformed

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        btnenter(btnSave);
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        btnexit(btnSave);
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnQueryBuilderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQueryBuilderMouseEntered
        btnenter(btnQueryBuilder);
    }//GEN-LAST:event_btnQueryBuilderMouseEntered

    private void btnQueryBuilderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQueryBuilderMouseExited
        btnexit(btnQueryBuilder);
    }//GEN-LAST:event_btnQueryBuilderMouseExited

    private void btnBeautfySQLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeautfySQLMouseEntered
        btnenter(btnBeautfySQL);
    }//GEN-LAST:event_btnBeautfySQLMouseEntered

    private void btnBeautfySQLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeautfySQLMouseExited
        btnexit(btnBeautfySQL);
    }//GEN-LAST:event_btnBeautfySQLMouseExited

    private void btnCodeSnippedMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCodeSnippedMouseEntered
        btnenter(btnCodeSnipped);
    }//GEN-LAST:event_btnCodeSnippedMouseEntered

    private void btnCodeSnippedMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCodeSnippedMouseExited
        btnexit(btnCodeSnipped);
    }//GEN-LAST:event_btnCodeSnippedMouseExited

    private void btnTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTextActionPerformed

    private void btnTextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTextMouseEntered
        btnenter(btnText);
    }//GEN-LAST:event_btnTextMouseEntered

    private void btnTextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTextMouseExited
        btnexit(btnText);
    }//GEN-LAST:event_btnTextMouseExited

    private void btnExportResultMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportResultMouseEntered
        btnenter(btnExportResult);
    }//GEN-LAST:event_btnExportResultMouseEntered

    private void btnExportResultMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportResultMouseExited
        btnexit(btnExportResult);
    }//GEN-LAST:event_btnExportResultMouseExited

    private void btnRunMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRunMouseEntered
        btnenter(btnRun);
    }//GEN-LAST:event_btnRunMouseEntered

    private void btnRunMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRunMouseExited
        btnexit(btnRun);
    }//GEN-LAST:event_btnRunMouseExited

    private void btnstopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstopMouseEntered
        btnenter(btnstop);
    }//GEN-LAST:event_btnstopMouseEntered

    private void btnstopMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstopMouseExited
        btnexit(btnstop);
    }//GEN-LAST:event_btnstopMouseExited

    private void btnexplainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnexplainMouseEntered
        btnenter(btnexplain);
    }//GEN-LAST:event_btnexplainMouseEntered

    private void btnexplainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnexplainMouseExited
        btnexit(btnexplain);
    }//GEN-LAST:event_btnexplainMouseExited

    private void jComboBoxconnectionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxconnectionsActionPerformed

    }//GEN-LAST:event_jComboBoxconnectionsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBeautfySQL;
    private javax.swing.JButton btnCodeSnipped;
    private javax.swing.JButton btnExportResult;
    private javax.swing.JButton btnQueryBuilder;
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnText;
    private javax.swing.JButton btnexplain;
    private javax.swing.JButton btnstop;
    private javax.swing.JComboBox<String> jComboBoxconnections;
    private javax.swing.JComboBox<String> jComboBoxtables;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTable tblQueryResult;
    private javax.swing.JTextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}

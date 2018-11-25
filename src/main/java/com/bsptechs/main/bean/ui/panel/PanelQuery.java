/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.panel;

import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.uielement.UiElementDatabase;
import com.bsptechs.main.bean.ui.uielement.UiElementConnection;
import com.bsptechs.main.bean.ui.table.TableData;
import com.bsptechs.main.bean.ui.table.TableRow;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import com.bsptechs.main.util.ImageUtil;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sarkhanrasullu
 */
public class PanelQuery extends javax.swing.JPanel {

    private static final DatabaseDAOInter db = new DatabaseDAOImpl();

    public PanelQuery(UiElementConnection connection, UiElementDatabase database) throws ClassNotFoundException, SQLException {
        initComponents();
        preparePanel(connection, database);
        setIcon();
    }

    public void setIcon(){
    btnSave.setIcon(ImageUtil.getIconforQueryPanel("querypanel/save.png"));
    btnQueryBuilder.setIcon(ImageUtil.getIconforQueryPanel("querypanel/querybuilder.png"));
    btnBeautfySQL.setIcon(ImageUtil.getIconforQueryPanel("querypanel/beauty.png"));
    btnCodeSnipped.setIcon(ImageUtil.getIconforQueryPanel("querypanel/snippet.png"));
    btnText.setIcon(ImageUtil.getIconforQueryPanel("querypanel/text-document.png"));
    btnExportResult.setIcon(ImageUtil.getIconforQueryPanel("querypanel/export-file.png"));
    btnRun.setIcon(ImageUtil.getIconforQueryPanel("querypanel/play-arrow.png"));
    btnstop.setIcon(ImageUtil.getIconforQueryPanel("querypanel/stop.png"));
    btnexplain.setIcon(ImageUtil.getIconforQueryPanel("querypanel/explain-.png"));
    }
    
    public final void preparePanel(UiElementConnection connection, UiElementDatabase database) {
        prepareConnectionCombobox(connection);
        prepareDatabasesCombobox(connection, database);
        pnlTable.setVisible(false);
    }

    public void prepareConnectionCombobox(UiElementConnection connection) {
        System.out.println("prepareConnectionCombobox=" + connection);
        cbConnections.removeAllItems();
        List<UiElementConnection> list = Config.instance().getConnections();
        for (int i = 0; i < list.size(); i++) {
            cbConnections.addItem(list.get(i));
        }
        System.out.println("Config.getCurrentConnection()=" + connection);
        cbConnections.setSelectedItem(connection);
    }

    public void prepareDatabasesCombobox(UiElementConnection connection, UiElementDatabase database) {
        System.out.println("prepareDatabasesCombobox=" + database);
        cbDatabases.removeAllItems();
        List<UiElementDatabase> databases = connection.getDatabases();
        if (databases == null) {
            databases = db.getAllDatabases(connection);
        }
        for (int i = 0; i < databases.size(); i++) {
            cbDatabases.addItem(databases.get(i));
        }
        cbDatabases.setSelectedItem(database);
    }

    public void btnenter(JButton btn) {
        btn.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.white));
    }

    public void btnexit(JButton btn) {
        btn.setBorder(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTable = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblQueryResult = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        pnlButtons = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnQueryBuilder = new javax.swing.JButton();
        btnBeautfySQL = new javax.swing.JButton();
        btnCodeSnipped = new javax.swing.JButton();
        btnText = new javax.swing.JButton();
        btnExportResult = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbConnections = new javax.swing.JComboBox<>();
        cbDatabases = new javax.swing.JComboBox<>();
        btnRun = new javax.swing.JButton();
        btnstop = new javax.swing.JButton();
        btnexplain = new javax.swing.JButton();

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

        txtQuery.setColumns(20);
        txtQuery.setRows(5);
        jScrollPane1.setViewportView(txtQuery);

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane1)
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSave.setText("Save");
        btnSave.setBorder(null);
        btnSave.setContentAreaFilled(false);
        btnSave.setMaximumSize(new java.awt.Dimension(39, 17));
        btnSave.setMinimumSize(new java.awt.Dimension(39, 17));
        btnSave.setPreferredSize(new java.awt.Dimension(44, 17));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
        });

        btnQueryBuilder.setText("Query bulder");
        btnQueryBuilder.setBorder(null);
        btnQueryBuilder.setContentAreaFilled(false);
        btnQueryBuilder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQueryBuilderMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQueryBuilderMouseEntered(evt);
            }
        });

        btnBeautfySQL.setText("Beautfy SQL");
        btnBeautfySQL.setBorder(null);
        btnBeautfySQL.setContentAreaFilled(false);
        btnBeautfySQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBeautfySQLMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBeautfySQLMouseEntered(evt);
            }
        });
        btnBeautfySQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeautfySQLActionPerformed(evt);
            }
        });

        btnCodeSnipped.setText("Code Snipped");
        btnCodeSnipped.setBorder(null);
        btnCodeSnipped.setContentAreaFilled(false);
        btnCodeSnipped.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCodeSnippedMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCodeSnippedMouseEntered(evt);
            }
        });

        btnText.setText("Text");
        btnText.setBorder(null);
        btnText.setContentAreaFilled(false);
        btnText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTextMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTextMouseEntered(evt);
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
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportResultMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportResultMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnQueryBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnBeautfySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCodeSnipped, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnExportResult, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQueryBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBeautfySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCodeSnipped, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonsLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cbConnections.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbConnections.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbConnectionsItemStateChanged(evt);
            }
        });
        cbConnections.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbConnectionsActionPerformed(evt);
            }
        });

        cbDatabases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDatabasesActionPerformed(evt);
            }
        });

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

        btnstop.setText("Stop");
        btnstop.setBorder(null);
        btnstop.setContentAreaFilled(false);
        btnstop.setPreferredSize(new java.awt.Dimension(51, 23));
        btnstop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnstopMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnstopMouseEntered(evt);
            }
        });

        btnexplain.setText("Explain");
        btnexplain.setBorder(null);
        btnexplain.setContentAreaFilled(false);
        btnexplain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnexplainMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnexplainMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(cbConnections, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbDatabases, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnexplain, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbConnections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDatabases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexplain, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(473, 473, 473))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setQuery(String txt) {
        txtQuery.setText(txt);
    }

    public void runQuery() {
        pnlTable.setVisible(true);
        try {
            TableData data = db.runQuery(txtQuery.getText(), getSelectedConnection(), getSelectedDatabase());
            DefaultTableModel model = PanelQuery.buildTableModel(data);
            tblQueryResult.setModel(model);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PanelQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PanelQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UiElementDatabase getSelectedDatabase() {
        Object obj = cbDatabases.getSelectedItem();

        return (UiElementDatabase) obj;
    }
    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        runQuery();
    }//GEN-LAST:event_btnRunActionPerformed

    private void cbDatabasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDatabasesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDatabasesActionPerformed

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

    private void cbConnectionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbConnectionsActionPerformed

    }//GEN-LAST:event_cbConnectionsActionPerformed

    public UiElementConnection getSelectedConnection() {
        return (UiElementConnection) cbConnections.getSelectedItem();
    }
    private void btnBeautfySQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeautfySQLActionPerformed
        String s[] = txtQuery.getText().split("\\r?\\n");
        ArrayList<String> arrList = new ArrayList<>(Arrays.asList(s));
        System.out.println(arrList.size());
    }//GEN-LAST:event_btnBeautfySQLActionPerformed

    private void cbConnectionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbConnectionsItemStateChanged
        prepareDatabasesCombobox(getSelectedConnection(), null);
    }//GEN-LAST:event_cbConnectionsItemStateChanged

    public static void runQuery(String txt) {
        Config.getMain().prepareNewQuery();
        Config.getMain().getPanelQuery().setQuery(txt);
        Config.getMain().getPanelQuery().runQuery();
    }

    public static DefaultTableModel buildTableModel(TableData tableData) {
        Vector<String> columnNames = new Vector<String>(tableData.getColumns());

        Vector<Vector<Object>> rowsVector = new Vector<Vector<Object>>();
        List<TableRow> rows = tableData.getRows();
        for (TableRow row : rows) {
            Vector<Object> vector = new Vector<Object>(row.getCells());
            rowsVector.add(vector);
        }

        DefaultTableModel dtm = new DefaultTableModel(rowsVector, columnNames);
        return dtm;
    }

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
    private javax.swing.JComboBox<UiElementConnection> cbConnections;
    private javax.swing.JComboBox<UiElementDatabase> cbDatabases;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTable tblQueryResult;
    private javax.swing.JTextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}

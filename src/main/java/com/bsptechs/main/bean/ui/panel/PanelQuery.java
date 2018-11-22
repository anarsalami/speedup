/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.main.bean.ui.panel;

import com.bsptechs.main.bean.Config;
import com.bsptechs.main.bean.ui.table.CustomTable;
import com.bsptechs.main.bean.ui.uielement.UiElementDatabase;
import com.bsptechs.main.bean.ui.uielement.UiElementConnection;
import com.bsptechs.main.bean.ui.table.CustomTableModel;
import com.bsptechs.main.bean.ui.table.TableRow;
import com.bsptechs.main.bean.ui.uielement.UiElementTable;
import com.bsptechs.main.dao.impl.DatabaseDAOImpl;
import com.bsptechs.main.dao.inter.DatabaseDAOInter;
import com.bsptechs.main.util.ImageUtil;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import lombok.SneakyThrows;

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
        CustomTable tbl = getTable();
        tbl.setConnection(getSelectedConnection());
    }

    public void setIcon() {
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
//        pnlResult.setVisible(false);
        prepareConnectionCombobox(connection);
        prepareDatabasesCombobox(connection, database);
    }

    public void prepareConnectionCombobox(UiElementConnection connection) {
        cbConnections.removeAllItems();
        List<UiElementConnection> list = Config.instance().getConnections();
        if (list.size() == 0) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            cbConnections.addItem(list.get(i));
        }

        if (connection == null) {
            connection = list.get(0);
        }
        cbConnections.setSelectedItem(connection);

        CustomTable tbl = getTable();
        tbl.setConnection(getSelectedConnection());
    }

    public void prepareDatabasesCombobox(UiElementConnection connection, UiElementDatabase database) {
        if (connection == null) {
            return;
        }
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

        splitPane = new javax.swing.JSplitPane();
        pnlQuery = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        pnlButtons = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnQueryBuilder = new javax.swing.JButton();
        btnBeautfySQL = new javax.swing.JButton();
        btnCodeSnipped = new javax.swing.JButton();
        btnText = new javax.swing.JButton();
        btnExportResult = new javax.swing.JButton();
        pnlControllBtns = new javax.swing.JPanel();
        cbConnections = new javax.swing.JComboBox<>();
        cbDatabases = new javax.swing.JComboBox<>();
        btnRun = new javax.swing.JButton();
        btnstop = new javax.swing.JButton();
        btnexplain = new javax.swing.JButton();
        pnlResult = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblQueryResult = new CustomTable();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnSaveChangesForTable = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        pnlQuery.setMinimumSize(new java.awt.Dimension(300, 300));
        pnlQuery.setPreferredSize(new java.awt.Dimension(300, 300));

        txtQuery.setColumns(20);
        txtQuery.setRows(5);
        txtQuery.setMinimumSize(new java.awt.Dimension(0, 160));
        txtQuery.setPreferredSize(new java.awt.Dimension(240, 200));
        jScrollPane1.setViewportView(txtQuery);

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

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
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
                .addGap(9, 9, 9))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQueryBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBeautfySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCodeSnipped, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout pnlControllBtnsLayout = new javax.swing.GroupLayout(pnlControllBtns);
        pnlControllBtns.setLayout(pnlControllBtnsLayout);
        pnlControllBtnsLayout.setHorizontalGroup(
            pnlControllBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControllBtnsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        pnlControllBtnsLayout.setVerticalGroup(
            pnlControllBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControllBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControllBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbConnections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDatabases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexplain, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlQueryLayout = new javax.swing.GroupLayout(pnlQuery);
        pnlQuery.setLayout(pnlQueryLayout);
        pnlQueryLayout.setHorizontalGroup(
            pnlQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQueryLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlControllBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        pnlQueryLayout.setVerticalGroup(
            pnlQueryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQueryLayout.createSequentialGroup()
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(pnlControllBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
        );

        splitPane.setLeftComponent(pnlQuery);

        tblQueryResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {

            }
        ));
        tblQueryResult.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblQueryResultFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblQueryResultFocusLost(evt);
            }
        });
        tblQueryResult.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblQueryResultPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(tblQueryResult);

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSaveChangesForTable.setText("S");
        btnSaveChangesForTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangesForTableActionPerformed(evt);
            }
        });

        btnDelete.setText("-");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCancel.setText("X");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveChangesForTable, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnSaveChangesForTable)
                    .addComponent(btnDelete)
                    .addComponent(btnCancel)))
        );

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1372, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
        );

        splitPane.setRightComponent(pnlResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setQuery(String txt) {
        txtQuery.setText(txt);
        CustomTable tbl = getTable();
        tbl.setQuery(txt);
    }

    private CustomTable getTable() {
        return (CustomTable) tblQueryResult;
    }

    @SneakyThrows
    public void runQuery() {
        CustomTable tbl = getTable();
        CustomTableModel model = db.runQuery(txtQuery.getText(), getSelectedConnection(), getSelectedDatabase());
        tbl.setModel(model);
        tbl.setConnection(getSelectedConnection());
        tbl.setDatabase(getSelectedDatabase());
    }
 
    
    @SneakyThrows
    public void runQuery(String query, UiElementConnection conn, UiElementDatabase database) {
        CustomTable tbl = getTable();
        CustomTableModel model = db.runQuery(query, conn, database);
        tbl.setModel(model);
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
        UiElementConnection conn = getSelectedConnection();
        System.out.println("selected connnection=" + conn);
        prepareDatabasesCombobox(conn, null);
    }//GEN-LAST:event_cbConnectionsItemStateChanged

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        CustomTable tbl = getTable();
        db.deleteRow(getSelectedConnection(), tbl.getSelectedTableRow());
        runQuery();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        CustomTable tbl = getTable();
        TableRow addedRow = tbl.addEmptyRow();
        System.out.println("row added");
        btnAdd.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblQueryResultPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblQueryResultPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tblQueryResultPropertyChange

    private void tblQueryResultFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblQueryResultFocusGained
        CustomTable table = getTable();
        TableRow row = table.getSelectedTableRow();
        System.out.println("tblQueryResultFocusGained=" + row);
    }//GEN-LAST:event_tblQueryResultFocusGained

    private void tblQueryResultFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblQueryResultFocusLost
        CustomTable table = getTable();
        TableRow row = table.getSelectedTableRow();
        System.out.println("tblQueryResultFocusLost=" + row);
    }//GEN-LAST:event_tblQueryResultFocusLost

    private void btnSaveChangesForTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChangesForTableActionPerformed
        CustomTable table = getTable();
        TableRow row = table.getSelectedTableRow();
        db.saveRow(table.getConnection(), row);
        runQuery(table.getQuery(), table.getConnection(), table.getDatabase());
    }//GEN-LAST:event_btnSaveChangesForTableActionPerformed

    public static void viewTable(UiElementTable table) {
        runQuery("select * from " + table.getTableName());
    }

    public static void runQuery(String txt) {
        Config.getMain().prepareNewQuery();
        Config.getMain().getPanelQuery().setQuery(txt);
        Config.getMain().getPanelQuery().runQuery();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBeautfySQL;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCodeSnipped;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportResult;
    private javax.swing.JButton btnQueryBuilder;
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveChangesForTable;
    private javax.swing.JButton btnText;
    private javax.swing.JButton btnexplain;
    private javax.swing.JButton btnstop;
    private javax.swing.JComboBox<UiElementConnection> cbConnections;
    private javax.swing.JComboBox<UiElementDatabase> cbDatabases;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlControllBtns;
    private javax.swing.JPanel pnlQuery;
    private javax.swing.JPanel pnlResult;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTable tblQueryResult;
    private javax.swing.JTextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}

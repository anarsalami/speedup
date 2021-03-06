package com.bsptechs.main.bean.ui.frame;

import com.bsptechs.main.Main;
import com.bsptechs.main.Config;
import com.bsptechs.main.bean.ui.tree.database.bean.SUConnectionBean;
import com.bsptechs.main.util.Util;
import org.apache.commons.lang3.StringUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Penthos
 */
public class ConnectionFrame extends javax.swing.JFrame {

    /**
     * Creates new form MySqlConnection
     */
    public ConnectionFrame() {
//        MainFrameUtility.centralizeJFrame(this);
        initComponents();
        clearErrMsgs();
    }

    private boolean updateMode = false;
    private SUConnectionBean connection = null;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tabbedPane = new javax.swing.JTabbedPane();
        panelGeneral = new javax.swing.JPanel();
        lblPort = new javax.swing.JLabel();
        lblConnectionNameErrMsg = new javax.swing.JLabel();
        lblHostName = new javax.swing.JLabel();
        lblPortErrMsg = new javax.swing.JLabel();
        lblPasswordErrMsg = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtHostNameIpAdr = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtConnectionName = new javax.swing.JTextField();
        lblUserNameErrMsg = new javax.swing.JLabel();
        lblHostNameErrMsg = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtPort = new javax.swing.JTextField();
        panelAdvanced = new javax.swing.JPanel();
        panelSSL = new javax.swing.JPanel();
        panelSSH = new javax.swing.JPanel();
        panelHTTP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelGeneral.setLayout(new java.awt.GridBagLayout());

        lblPort.setText("Port:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 54;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 28, 0, 0);
        panelGeneral.add(lblPort, gridBagConstraints);

        lblConnectionNameErrMsg.setForeground(new java.awt.Color(255, 51, 51));
        lblConnectionNameErrMsg.setText("Connection Name Error Message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 22);
        panelGeneral.add(lblConnectionNameErrMsg, gridBagConstraints);

        lblHostName.setText("Host Name/IP Adress:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 28, 0, 0);
        panelGeneral.add(lblHostName, gridBagConstraints);

        lblPortErrMsg.setForeground(new java.awt.Color(255, 51, 51));
        lblPortErrMsg.setText("Port Error Message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 87;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 22);
        panelGeneral.add(lblPortErrMsg, gridBagConstraints);

        lblPasswordErrMsg.setForeground(new java.awt.Color(255, 51, 51));
        lblPasswordErrMsg.setText("Password Error Message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 53;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 22);
        panelGeneral.add(lblPasswordErrMsg, gridBagConstraints);

        jLabel3.setText("User Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 28, 0, 0);
        panelGeneral.add(jLabel3, gridBagConstraints);

        txtPassword.setMaximumSize(new java.awt.Dimension(200, 200));
        txtPassword.setMinimumSize(new java.awt.Dimension(200, 26));
        txtPassword.setPreferredSize(new java.awt.Dimension(200, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 22);
        panelGeneral.add(txtPassword, gridBagConstraints);

        jLabel2.setText("Connection Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(37, 28, 0, 0);
        panelGeneral.add(jLabel2, gridBagConstraints);

        txtHostNameIpAdr.setMaximumSize(new java.awt.Dimension(200, 200));
        txtHostNameIpAdr.setMinimumSize(new java.awt.Dimension(200, 26));
        txtHostNameIpAdr.setPreferredSize(new java.awt.Dimension(200, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 6, 0, 22);
        panelGeneral.add(txtHostNameIpAdr, gridBagConstraints);

        txtUserName.setMaximumSize(new java.awt.Dimension(200, 200));
        txtUserName.setMinimumSize(new java.awt.Dimension(200, 26));
        txtUserName.setPreferredSize(new java.awt.Dimension(200, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 22);
        panelGeneral.add(txtUserName, gridBagConstraints);

        txtConnectionName.setMaximumSize(new java.awt.Dimension(200, 200));
        txtConnectionName.setMinimumSize(new java.awt.Dimension(200, 26));
        txtConnectionName.setPreferredSize(new java.awt.Dimension(200, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(36, 6, 0, 22);
        panelGeneral.add(txtConnectionName, gridBagConstraints);

        lblUserNameErrMsg.setForeground(new java.awt.Color(255, 51, 51));
        lblUserNameErrMsg.setText("Username Error Message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 0, 22);
        panelGeneral.add(lblUserNameErrMsg, gridBagConstraints);

        lblHostNameErrMsg.setForeground(new java.awt.Color(255, 51, 51));
        lblHostNameErrMsg.setText("Host Error Message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 82;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 0, 22);
        panelGeneral.add(lblHostNameErrMsg, gridBagConstraints);

        jLabel5.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 24;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 28, 0, 0);
        panelGeneral.add(jLabel5, gridBagConstraints);

        btnOk.setText("OK");
        btnOk.setPreferredSize(new java.awt.Dimension(80, 29));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = -5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 49, 12, 0);
        panelGeneral.add(btnOk, gridBagConstraints);

        btnCancel.setText("Cancel");
        btnCancel.setPreferredSize(new java.awt.Dimension(80, 29));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 12, 22);
        panelGeneral.add(btnCancel, gridBagConstraints);

        txtPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPortKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPortKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPortKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 84;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        panelGeneral.add(txtPort, gridBagConstraints);

        tabbedPane.addTab("General", panelGeneral);

        javax.swing.GroupLayout panelAdvancedLayout = new javax.swing.GroupLayout(panelAdvanced);
        panelAdvanced.setLayout(panelAdvancedLayout);
        panelAdvancedLayout.setHorizontalGroup(
            panelAdvancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelAdvancedLayout.setVerticalGroup(
            panelAdvancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Advanced", panelAdvanced);

        javax.swing.GroupLayout panelSSLLayout = new javax.swing.GroupLayout(panelSSL);
        panelSSL.setLayout(panelSSLLayout);
        panelSSLLayout.setHorizontalGroup(
            panelSSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelSSLLayout.setVerticalGroup(
            panelSSLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        tabbedPane.addTab("SSL", panelSSL);

        javax.swing.GroupLayout panelSSHLayout = new javax.swing.GroupLayout(panelSSH);
        panelSSH.setLayout(panelSSHLayout);
        panelSSHLayout.setHorizontalGroup(
            panelSSHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelSSHLayout.setVerticalGroup(
            panelSSHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        tabbedPane.addTab("SSH", panelSSH);

        javax.swing.GroupLayout panelHTTPLayout = new javax.swing.GroupLayout(panelHTTP);
        panelHTTP.setLayout(panelHTTPLayout);
        panelHTTPLayout.setHorizontalGroup(
            panelHTTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelHTTPLayout.setVerticalGroup(
            panelHTTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        tabbedPane.addTab("HTTP", panelHTTP);

        getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);
        tabbedPane.getAccessibleContext().setAccessibleName("general");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        SUConnectionBean filledConnection = getAllInformFromUser();
        if (!validateFields()) {
            return;
        }
        if (updateMode) {
            updateConnection(filledConnection);
        } else {
            Main.instance().getConnectionTree().addConnectionNode(filledConnection);
        }
        Config.instance().saveConfig();
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    public void updateConnection(SUConnectionBean newConnection) {
        connection.setName(newConnection.getName());
        connection.setIpAdr(newConnection.getIpAdr());
        connection.setPort(newConnection.getPort());
        connection.setUserName(newConnection.getUserName());
        connection.setPassword(newConnection.getPassword());
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtPortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPortKeyPressed

    }//GEN-LAST:event_txtPortKeyPressed

    private void txtPortKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPortKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPortKeyReleased

    private void txtPortKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPortKeyTyped
        boolean isValid = evt.getKeyChar() >= 48 && evt.getKeyChar() <= 57;
        if (!isValid) {
            evt.consume();
            return;
        }
        String txt = txtPort.getText() + evt.getKeyChar();
        if (Integer.parseInt(txt) > 65536) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPortKeyTyped

    private void clearErrMsgs() {
        lblConnectionNameErrMsg.setText("");
        lblHostNameErrMsg.setText("");
        lblPasswordErrMsg.setText("");
        lblPortErrMsg.setText("");
        lblUserNameErrMsg.setText("");
    }

    private boolean validateFields() {
        clearErrMsgs();
        boolean res = true;
        SUConnectionBean conn = getAllInformFromUser();

        SUConnectionBean c = Main.instance().getConnectionTree().getConnectionBeans().getByName(conn.getName());
        if (c!=null && c != connection) {
            lblConnectionNameErrMsg.setText("connection name already exists");
            res = false;
        }

        if (StringUtils.isEmpty(conn.getName())) {
            lblConnectionNameErrMsg.setText("fill connection name");
            res = false;
        }

        if (StringUtils.isEmpty(conn.getIpAdr())) {
            lblHostNameErrMsg.setText("fill hostname/ip adress");
            res = false;
        }

        if (StringUtils.isEmpty(conn.getPort())) {
            lblPortErrMsg.setText("fill port");
            res = false;
        }

        if (StringUtils.isEmpty(conn.getUserName())) {
            lblUserNameErrMsg.setText("fill username");
            res = false;
        }

        if (!Util.checkIp(conn.getIpAdr())) {
            lblHostNameErrMsg.setText("invalid ip address");
            res = false;
        }

        if (!Util.checkPort(conn.getPort())) {
            lblPortErrMsg.setText("invalid port number");
            res = false;
        }

        return res;
    }

    public SUConnectionBean getAllInformFromUser() {
        String name = txtConnectionName.getText();
        String ipAdr = txtHostNameIpAdr.getText().toLowerCase();
        String port = txtPort.getText();
        String username = txtUserName.getText();
        String password = new String(txtPassword.getPassword());

        SUConnectionBean c = new SUConnectionBean(name, ipAdr, port, username, password);
        return c;
    }

    public static void showAsRegister() {
        ConnectionFrame f = new ConnectionFrame();
        f.setVisible(true);
    }

    private void prepareUpdate(SUConnectionBean c) {
        connection = c;
        updateMode = true;
        txtConnectionName.setText(c.getName());
        txtHostNameIpAdr.setText(c.getIpAdr());
        txtPort.setText(c.getPort());
        txtUserName.setText(c.getUserName());
        txtPassword.setText(c.getPassword());
        this.setVisible(true);
    }

    public static void showAsUpdate(SUConnectionBean c) {
        ConnectionFrame m = new ConnectionFrame();
        m.prepareUpdate(c);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblConnectionNameErrMsg;
    private javax.swing.JLabel lblHostName;
    private javax.swing.JLabel lblHostNameErrMsg;
    private javax.swing.JLabel lblPasswordErrMsg;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblPortErrMsg;
    private javax.swing.JLabel lblUserNameErrMsg;
    private javax.swing.JPanel panelAdvanced;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelHTTP;
    private javax.swing.JPanel panelSSH;
    private javax.swing.JPanel panelSSL;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField txtConnectionName;
    private javax.swing.JTextField txtHostNameIpAdr;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}

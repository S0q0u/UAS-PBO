/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Aplikasi;

import static Koneksi.Database.koneksi;
import com.mysql.jdbc.Statement;
import static Koneksi.Database.statement;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Harlin
 */

public class Login extends javax.swing.JFrame {
    public static ResultSet resultSet;
    
    public Login() {
        initComponents();
        
        // Agar form muncul di tengah saat dijalankan
        setLocationRelativeTo(null);
        
        // Focus saat program baru berjalan
        txtUsername.requestFocus();
        
        // Mengatur ukuran form dan button
        setSize(1023,562);
        btnLogin.setPreferredSize(new Dimension(90,33));
        btnClear.setPreferredSize(new Dimension(90,33));
    }
    
    // Fungsi untuk cek kelengkapan inputan
    private boolean cek(){
        if (txtUsername.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Masukkan Username!", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return false;
        } else if (txtPassword.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Masukkan Password!", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }
    
    // Menghapus input pada komponen
    private void kosong(){
        txtUsername.setText("");        
        txtPassword.setText("");
        txtUsername.requestFocus();
    }
    
    // Method untuk identifikasi role
    private void showMenu(String role) {
        if (role.equals("admin")) {
            // Tampilkan menu admin
            new Staff().setVisible(true);
            dispose();
        } else if (role.equals("user")) {
            // Tampilkan menu user
            new Student().setVisible(true);
            dispose();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1023, 542));
        setSize(new java.awt.Dimension(1023, 542));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 320, 30));

        txtPassword.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 290, 320, 30));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel1.setText("Username");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 30));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, 30));

        btnLogin.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(122, 169, 202));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, -1, -1));

        btnClear.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 0, 0));
        btnClear.setText("Cancel");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 380, -1, -1));

        btnExit.setBackground(new java.awt.Color(204, 0, 0));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("X");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(955, 22, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Aplikasi/TEKNOLOGI.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (cek() == true){
            
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            try {
                koneksi();
                statement = (Statement) koneksi.createStatement();
                String query = "SELECT * FROM tbAkun WHERE Username = '" + username + "' AND Password = '" + password + "'";
                resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    String role = resultSet.getString("Role");
                    showMenu(role);
                } else {
                    JOptionPane.showMessageDialog(this, "Username Atau Password Anda Salah!", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
                }

                resultSet.close();
                statement.close();
                koneksi.close();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        kosong();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Apakah Anda yakin akan keluar?","Keluar",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

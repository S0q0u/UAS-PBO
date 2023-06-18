/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Aplikasi;

import static Koneksi.Database.Pstatement;
import static Koneksi.Database.koneksi;
import static Koneksi.Database.result;
import static Koneksi.Database.statement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Harlin
 */
public class Staff extends javax.swing.JFrame {
    public static String namaLengkap, jenisKelamin, jurusan, NISN;
    public static int agama, pkn, bina, bing, mtk, ipa, ips, skor;
    public static DefaultTableModel tabMode;
    
    public Staff() {
        initComponents();
        
        Object[] row = {"NISN", "Nama Lengkap", "Jurusan", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR"};
        tabMode = new DefaultTableModel(null,row);
        tabeldata.setModel(tabMode);
        
        // Agar form muncul di tengah saat dijalankan
        setLocationRelativeTo(null);
        
        // Method untuk menampilkan data
        tampil();
        
        // Focus saat program baru berjalan
        txtNama.requestFocus();
        
        // Buttongroup untuk radiobutton
        ButtonGroup group = new ButtonGroup();
        group.add(RB1);
        group.add(RB2);
        
        // Mengatur ukuran form, kolom, dan button
        setSize(1150, 680);
        resize();
        btnAdd.setPreferredSize(new Dimension(76,27));
        btnDel.setPreferredSize(new Dimension(76,27));
        btnCancel.setPreferredSize(new Dimension(76,27));
        btnUpdate.setPreferredSize(new Dimension(76,27));
        btnLogout.setPreferredSize(new Dimension(76,27));
        
        // Mendapatkan header tabel
        JTableHeader header = tabeldata.getTableHeader();

        // Mengatur render header kolom agar di tengah
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }
    
    // Mengatur ukuran kolom
    private void resize() {
        TableColumnModel columnModel = tabeldata.getColumnModel();
        for (int column = 0; column < tabeldata.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();
            
            for (int row = 0; row < tabeldata.getRowCount(); row++) {
                TableCellRenderer renderer = tabeldata.getCellRenderer(row, column);
                Component component = tabeldata.prepareRenderer(renderer, row, column);
                int width = component.getPreferredSize().width + tabeldata.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);
                
                // Jika melebihi batas lebar maksimum, atur ke lebar maksimum
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        for (int column = 0; column < tabeldata.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            tableColumn.setCellRenderer(centerRenderer);
        }
    }
    
    // Menghapus data pada tabel
    public static void hapus(){
        int row = tabMode.getRowCount();
        for(int i=0;i<row;i++){
            tabMode.removeRow(0);
        }
    }
    
    // Menampilkan data
    public static void tampil(){
        hapus();
        koneksi();
        try{
            statement = (Statement) koneksi.createStatement();
            result = statement.executeQuery("Select * from tbsiswa order by NISN");
            while(result.next()){
                NISN = result.getString("NISN");
                namaLengkap = result.getString("Nama Lengkap");
                jurusan = result.getString("Jurusan");
                jenisKelamin = result.getString("Jenis Kelamin");
                agama = result.getInt("Agama");
                pkn = result.getInt("Pendidikan Kewarganegaraan");
                bina = result.getInt("Bahasa Indonesia");
                bing = result.getInt("Bahasa Inggris");
                mtk = result.getInt("Matematika");
                ipa = result.getInt("IPA");
                ips = result.getInt("IPS");
                skor = result.getInt("SKOR AKHIR");
                tabMode.addRow(new Object[]{NISN, namaLengkap, jurusan, jenisKelamin, agama, pkn, bina, bing, mtk, ipa, ips, skor});
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }   
    }
        
    // Menghapus input pada komponen
    public void clear(){
        txtNama.setText("");
        txtNisn.setText("");
        txtAgama.setText("");
        txtPkn.setText("");
        txtBahasa.setText("");
        txtBasing.setText("");
        txtMtk.setText("");
        txtIpa.setText("");
        txtIps.setText("");
        txtCari.setText("");
        txtNama.requestFocus();
    }
    
    // Method untuk simpan data
    public void simpan() throws SQLException{
        NISN = txtNisn.getText();
        namaLengkap = txtNama.getText();
        jurusan = cbJurusan.getSelectedItem().toString();
        if(RB1.isSelected()){
            jenisKelamin = RB1.getText();
        }
        else{
            jenisKelamin = RB2.getText();
        }
        String ag = txtAgama.getText();
        agama = Integer.parseInt(ag);
        String pk = txtPkn.getText();
        pkn = Integer.parseInt(pk);
        String bin = txtBahasa.getText();
        bina = Integer.parseInt(bin);
        String en = txtBasing.getText();
        bing = Integer.parseInt(en);
        String mt = txtMtk.getText();
        mtk = Integer.parseInt(mt);
        String pa = txtIpa.getText();
        ipa = Integer.parseInt(pa);
        String ps = txtIps.getText();
        ips = Integer.parseInt(ps);
        skor = agama + pkn + bina + bing + mtk + ipa + ips;
        
        koneksi();
        
        String checkQuery = "SELECT * FROM tbsiswa WHERE NISN = '" + NISN + "'";
        Pstatement = (com.mysql.jdbc.PreparedStatement) koneksi.prepareStatement(checkQuery);
        ResultSet resultSet = Pstatement.executeQuery();
        
        if (resultSet.next()) {
            JOptionPane.showMessageDialog(this, "Data dengan NISN yang sama sudah ada!", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
            return; // Hentikan proses pembaruan data
        }
        else{
            try{
                String query = "INSERT INTO tbSiswa (NISN, `Nama Lengkap`, Jurusan, `Jenis Kelamin`, Agama, `Pendidikan Kewarganegaraan`, `Bahasa Indonesia`, `Bahasa Inggris`, Matematika, IPA, IPS, `SKOR AKHIR`) VALUES ('"
                        + NISN + "', "
                        + "'" + namaLengkap + "', "
                        + "'" + jurusan + "', "
                        + "'" + jenisKelamin + "', "
                        + "'" + agama + "', "
                        + "'" + pkn + "', "
                        + "'" + bina + "', "
                        + "'" + bing + "', "
                        + "'" + mtk + "', "
                        + "'" + ipa + "', "
                        + "'" + ips + "', "
                        + "'" + skor + "')";
                Pstatement = (com.mysql.jdbc.PreparedStatement) koneksi.prepareStatement(query);
                Pstatement.executeUpdate();
                
                String query2 = "INSERT INTO tbAkun (Username, Password, Role) VALUES ('" + NISN + "', '" + jurusan + "', 'user')";
                Pstatement = (com.mysql.jdbc.PreparedStatement) koneksi.prepareStatement(query2);
                Pstatement.executeUpdate();
                
                Pstatement.close();
                koneksi.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        
    }
    
    // Method untuk update data
    public void ubah(){
        koneksi();
        NISN = txtNisn.getText();
        namaLengkap = txtNama.getText();
        jurusan = cbJurusan.getSelectedItem().toString();
        if(RB1.isSelected()){
            jenisKelamin = RB1.getText();
        }
        else{
            jenisKelamin = RB2.getText();
        }
        String ag = txtAgama.getText();
        agama = Integer.parseInt(ag);
        String pk = txtPkn.getText();
        pkn = Integer.parseInt(pk);
        String bin = txtBahasa.getText();
        bina = Integer.parseInt(bin);
        String en = txtBasing.getText();
        bing = Integer.parseInt(en);
        String mt = txtMtk.getText();
        mtk = Integer.parseInt(mt);
        String pa = txtIpa.getText();
        ipa = Integer.parseInt(pa);
        String ps = txtIps.getText();
        ips = Integer.parseInt(ps);
        skor = agama + pkn + bina + bing + mtk + ipa + ips;
        try{
            String query = "UPDATE tbSiswa SET NISN=?, `Nama Lengkap`=?, Jurusan=?, `Jenis Kelamin`=?, Agama=?, `Pendidikan Kewarganegaraan`=?, `Bahasa Indonesia`=?, `Bahasa Inggris`=?, Matematika=?, IPA=?, IPS=?, `SKOR AKHIR`=? WHERE NISN=?";
            Pstatement = (com.mysql.jdbc.PreparedStatement) koneksi.prepareStatement(query);
            
            Pstatement.setString(1, NISN);
            Pstatement.setString(2, namaLengkap);
            Pstatement.setString(3, jurusan);
            Pstatement.setString(4, jenisKelamin);
            Pstatement.setInt(5, agama);
            Pstatement.setInt(6, pkn);
            Pstatement.setInt(7, bina);
            Pstatement.setInt(8, bing);
            Pstatement.setInt(9, mtk);
            Pstatement.setInt(10, ipa);
            Pstatement.setInt(11, ips);
            Pstatement.setInt(12, skor);
            Pstatement.setString(13, NISN);
            Pstatement.executeUpdate();
            
            String query2 = "UPDATE tbAkun SET Username=?, Password=?";
            Pstatement = (com.mysql.jdbc.PreparedStatement) koneksi.prepareStatement(query2);
            Pstatement.setString(1, NISN);
            Pstatement.setString(2, jurusan);
            Pstatement.executeUpdate();
                
            Pstatement.close();
            koneksi.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        } 
    }
    
    // Fungsi untuk cek kelengkapan inputan
    public boolean cek(){
        if (txtNama.getText().equals("")||cbJurusan.getSelectedItem().equals("")||txtNisn.getText().equals("")||txtAgama.getText().equals("")||
                txtPkn.getText().equals("")||txtBahasa.getText().equals("")||txtBasing.getText().equals("")||txtMtk.getText().equals("")||
                txtIpa.getText().equals("")||txtIps.getText().equals("")){
            JOptionPane.showMessageDialog(this, "DATA TIDAK LENGKAP!", "PERHATIAN", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    // Menghapus data
    public void delete(){
        koneksi();
        NISN = txtNisn.getText();
        
        try{
            String query = "DELETE FROM tbSiswa where NISN = '" + NISN + "'";
            Pstatement = (PreparedStatement) koneksi.prepareStatement(query);
            Pstatement.executeUpdate();
            
            String query2 = "DELETE FROM tbSiswa where Username = '" + NISN + "'";
            Pstatement = (PreparedStatement) koneksi.prepareStatement(query2);
            Pstatement.executeUpdate();
            Pstatement.close();
            koneksi.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // Method untuk cari data
    public static void search(String cari){
        hapus();
        koneksi();
        try{
            statement = (Statement) koneksi.createStatement();
            result = statement.executeQuery("Select * from tbSiswa where NISN like '%"+ cari +"%' order by `SKOR AKHIR` DESC");
            while(result.next()){
                NISN = result.getString("NISN");
                namaLengkap = result.getString("Nama Lengkap");
                jurusan = result.getString("Jurusan");
                jenisKelamin = result.getString("Jenis Kelamin");
                agama = result.getInt("Agama");
                pkn = result.getInt("Pendidikan Kewarganegaraan");
                bina = result.getInt("Bahasa Indonesia");
                bing = result.getInt("Bahasa Inggris");
                mtk = result.getInt("Matematika");
                ipa = result.getInt("IPA");
                ips = result.getInt("IPS");
                skor = result.getInt("SKOR AKHIR");
                tabMode.addRow(new Object[]{NISN, namaLengkap, jurusan, jenisKelamin, agama, pkn, bina, bing, mtk, ipa, ips, skor});
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RB1 = new javax.swing.JRadioButton();
        RB2 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbJurusan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        txtNisn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtBahasa = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtIps = new javax.swing.JTextField();
        txtIpa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAgama = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMtk = new javax.swing.JTextField();
        txtPkn = new javax.swing.JTextField();
        txtBasing = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeldata = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RB1.setText("Laki-Laki");
        getContentPane().add(RB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, -1, -1));

        RB2.setText("Perempuan");
        getContentPane().add(RB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, -1));

        jLabel6.setText("Nama Lengkap");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel7.setText("NISN");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel8.setText("Jurusan");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        cbJurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IPA", "IPS", "BAHASA" }));
        getContentPane().add(cbJurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        jLabel9.setText("Jenis Kelamin");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        getContentPane().add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 960, -1));
        getContentPane().add(txtNisn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 180, -1));

        jLabel11.setText("IPS");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, -1, -1));
        getContentPane().add(txtBahasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 95, -1));
        getContentPane().add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 180, -1));
        getContentPane().add(txtIps, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, 95, -1));
        getContentPane().add(txtIpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 95, -1));

        jLabel3.setText("Bahasa Indonesia");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        jLabel1.setText("Agama");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, -1, -1));

        jLabel2.setText("Pendidikan Kewarganegaraan");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, -1, -1));

        jLabel4.setText("Bahasa Inggris");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));
        getContentPane().add(txtAgama, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 95, -1));

        jLabel10.setText("IPA");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, -1, -1));

        jLabel5.setText("Matematika");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, -1, -1));
        getContentPane().add(txtMtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 95, -1));
        getContentPane().add(txtPkn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 95, -1));
        getContentPane().add(txtBasing, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 95, -1));

        tabeldata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NISN", "Nama Lengkap", "Jurusan", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR"
            }
        ));
        tabeldata.setShowGrid(true);
        tabeldata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeldata);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 1060, 180));

        btnAdd.setText("ADD");
        btnAdd.setPreferredSize(new java.awt.Dimension(75, 23));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 90, -1));

        btnDel.setText("DELETE");
        btnDel.setPreferredSize(new java.awt.Dimension(75, 23));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });
        getContentPane().add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, 90, -1));

        btnLogout.setForeground(new java.awt.Color(255, 0, 0));
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 94, -1));

        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 260, 90, -1));

        jLabel12.setText("NISN");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, 20));

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 90, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Aplikasi/admin.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(cek()){
            try {
                simpan();
            } catch (SQLException ex) {
                Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
            }
            tampil();
            clear();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if(cek()){
            ubah();
            tampil();
            clear();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        delete();
        clear();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clear();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tabeldataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldataMouseClicked
        int selectedRow = tabeldata.getSelectedRow();
        
        // Mengambil nilai dari table ke dalam komponen
        txtNisn.setText(tabeldata.getValueAt(selectedRow, 0).toString());
        txtNama.setText(tabeldata.getValueAt(selectedRow, 1).toString());
        if(tabeldata.getValueAt(selectedRow, 2).toString().equals("IPA")){
            cbJurusan.setSelectedItem("IPA");
        }
        else if(tabeldata.getValueAt(selectedRow, 2).toString().equals("IPS")){
            cbJurusan.setSelectedItem("IPS");
        }
        else{
            cbJurusan.setSelectedItem("BAHASA");
        }
        if(tabeldata.getValueAt(selectedRow, 3).toString().equals("Laki-Laki")){
            RB1.setSelected(true);
        }
        else{
            RB2.setSelected(true);
        }
        txtAgama.setText(tabeldata.getValueAt(selectedRow, 4).toString());
        txtPkn.setText(tabeldata.getValueAt(selectedRow, 5).toString());
        txtBahasa.setText(tabeldata.getValueAt(selectedRow, 6).toString());
        txtBasing.setText(tabeldata.getValueAt(selectedRow, 7).toString());
        txtMtk.setText(tabeldata.getValueAt(selectedRow, 8).toString());
        txtIpa.setText(tabeldata.getValueAt(selectedRow, 9).toString());
        txtIps.setText(tabeldata.getValueAt(selectedRow, 10).toString());
    }//GEN-LAST:event_tabeldataMouseClicked

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        search(txtCari.getText());
    }//GEN-LAST:event_txtCariKeyReleased

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

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
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RB1;
    private javax.swing.JRadioButton RB2;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbJurusan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabeldata;
    private javax.swing.JTextField txtAgama;
    private javax.swing.JTextField txtBahasa;
    private javax.swing.JTextField txtBasing;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIpa;
    private javax.swing.JTextField txtIps;
    private javax.swing.JTextField txtMtk;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNisn;
    private javax.swing.JTextField txtPkn;
    // End of variables declaration//GEN-END:variables
}

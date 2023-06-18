/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Aplikasi;

/**
 *
 * @author Harlin
 */
import static Aplikasi.Staff.NISN;
import static Aplikasi.Staff.agama;
import static Aplikasi.Staff.bina;
import static Aplikasi.Staff.bing;
import static Aplikasi.Staff.hapus;
import static Aplikasi.Staff.ipa;
import static Aplikasi.Staff.ips;
import static Aplikasi.Staff.jenisKelamin;
import static Aplikasi.Staff.jurusan;
import static Aplikasi.Staff.mtk;
import static Aplikasi.Staff.namaLengkap;
import static Aplikasi.Staff.pkn;
import static Aplikasi.Staff.skor;
import static Aplikasi.Staff.tabMode;
import static Koneksi.Database.koneksi;
import static Koneksi.Database.result;
import static Koneksi.Database.statement;
import com.mysql.jdbc.Statement;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Student extends javax.swing.JFrame {
    
    public Student() {
        initComponents();
        
        // Agar form muncul di tengah saat dijalankan
        setLocationRelativeTo(null);
        
        // Mengatur ukuran form dan button
        resize(1047, 583);
        btnALL.setPreferredSize(new Dimension(76,27));
        btnIPA.setPreferredSize(new Dimension(76,27));
        btnIPS.setPreferredSize(new Dimension(76,27));
        btnBahasa.setPreferredSize(new Dimension(76,27));
        btnHome.setPreferredSize(new Dimension(76,27));
        
        // Agar panel welcome yang tampil di form saat pertama kali program berjalan
        ALL.setVisible(false);
        IPS.setVisible(false);
        IPA.setVisible(false);
        BAHASA.setVisible(false);
        welcome.setVisible(true);
        
        // Mendapatkan header tabel
        JTableHeader header = tabeldata.getTableHeader();
        JTableHeader headeripa = tabelipa.getTableHeader();
        JTableHeader headerips = tabelips.getTableHeader();
        JTableHeader headerbhs = tabelbhs.getTableHeader();

        // Mengatur render header kolom agar di tengah
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer rendereripa = (DefaultTableCellRenderer) headeripa.getDefaultRenderer();
        rendereripa.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer rendererips = (DefaultTableCellRenderer) headerips.getDefaultRenderer();
        rendererips.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer rendererbhs = (DefaultTableCellRenderer) headerbhs.getDefaultRenderer();
        rendererbhs.setHorizontalAlignment(JLabel.CENTER);
    }
    
    // Tampil data pada panel ALL
    public static void lihat(){
        Object[] row = {"RANK", "NISN", "Nama Lengkap", "Jurusan", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR"};
        tabMode = new DefaultTableModel(null,row);
        hapus();
        koneksi();
        try{
            statement = (Statement) koneksi.createStatement();
            result = statement.executeQuery("SELECT*FROM tbSiswa ORDER BY `SKOR AKHIR` DESC");
            int rank = 1;
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
                tabMode.addRow(new Object[]{rank, NISN, namaLengkap, jurusan, jenisKelamin, agama, pkn, bina, bing, mtk, ipa, ips, skor});
                rank++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }   
    }

    // Tampil data pada panel IPA
    public static void lihatIPA(){
        Object[] row = {"RANK", "NISN", "Nama Lengkap", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR", "STATUS"};
        tabMode = new DefaultTableModel (null,row);
        hapus();
        koneksi();
        try{
            statement = (Statement) koneksi.createStatement();
            result = statement.executeQuery("SELECT * FROM tbSiswa where Jurusan = 'IPA' ORDER BY `SKOR AKHIR` DESC");
            int rank = 1;
            while(result.next()){
                String status = rank <= 100? "LOLOS" : "TIDAK LOLOS";
                NISN = result.getString("NISN");
                namaLengkap = result.getString("Nama Lengkap");
                jenisKelamin = result.getString("Jenis Kelamin");
                agama = result.getInt("Agama");
                pkn = result.getInt("Pendidikan Kewarganegaraan");
                bina = result.getInt("Bahasa Indonesia");
                bing = result.getInt("Bahasa Inggris");
                mtk = result.getInt("Matematika");
                ipa = result.getInt("IPA");
                ips = result.getInt("IPS");
                skor = result.getInt("SKOR AKHIR");
                tabMode.addRow(new Object[]{rank, NISN, namaLengkap, jenisKelamin, agama, pkn, bina, bing, mtk, ipa, ips, skor, status});
                rank++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    // Tampil data pada panel IPS
    public static void lihatIPS(){
        Object[] row = {"RANK", "NISN", "Nama Lengkap", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR", "STATUS"};
        tabMode = new DefaultTableModel(null,row);
        hapus();
        koneksi();
        try{
            statement = (Statement) koneksi.createStatement();
            result = statement.executeQuery("Select * from tbsiswa where Jurusan = 'IPS' order by `SKOR AKHIR` DESC");
            int rank = 1;
            while(result.next()){
                String status = rank <= 100? "LOLOS" : "TIDAK LOLOS";
                NISN = result.getString("NISN");
                namaLengkap = result.getString("Nama Lengkap");
                jenisKelamin = result.getString("Jenis Kelamin");
                agama = result.getInt("Agama");
                pkn = result.getInt("Pendidikan Kewarganegaraan");
                bina = result.getInt("Bahasa Indonesia");
                bing = result.getInt("Bahasa Inggris");
                mtk = result.getInt("Matematika");
                ipa = result.getInt("IPA");
                ips = result.getInt("IPS");
                skor = result.getInt("SKOR AKHIR");
                tabMode.addRow(new Object[]{rank, NISN, namaLengkap, jenisKelamin, agama, pkn, bina, bing, mtk, ipa, ips, skor, status});
                rank++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    // Tampil data pada panel BAHASA
    public static void lihatBahasa(){
        Object[] row = {"RANK", "NISN", "Nama Lengkap", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR", "STATUS"};
        tabMode = new DefaultTableModel(null,row);
        hapus();
        koneksi();
        try{
            statement = (Statement) koneksi.createStatement();
            result = statement.executeQuery("Select * from tbsiswa where Jurusan = 'BAHASA' order by `SKOR AKHIR` DESC");
            int rank = 1;
            while(result.next()){
                String status = rank <= 100? "LOLOS" : "TIDAK LOLOS";
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
                tabMode.addRow(new Object[]{rank, NISN, namaLengkap, jenisKelamin, agama, pkn, bina, bing, mtk, ipa, ips, skor, status});
                rank++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    // Mengatur ukuran tabel agar seukuran kolom
    private void resize(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();
            
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component component = table.prepareRenderer(renderer, row, column);
                int width = component.getPreferredSize().width + table.getIntercellSpacing().width;
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
        
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            tableColumn.setCellRenderer(centerRenderer);
        }
        table.setDefaultEditor(Object.class, null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIPA = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        welcome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnIPS = new javax.swing.JButton();
        btnBahasa = new javax.swing.JButton();
        btnALL = new javax.swing.JButton();
        IPA = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelipa = new javax.swing.JTable();
        ALL = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabeldata = new javax.swing.JTable();
        IPS = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelips = new javax.swing.JTable();
        BAHASA = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelbhs = new javax.swing.JTable();
        btnLogout = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIPA.setText("IPA");
        btnIPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIPAActionPerformed(evt);
            }
        });
        getContentPane().add(btnIPA, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, -1, -1));

        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, -1, -1));

        welcome.setBackground(new java.awt.Color(22, 29, 75));
        welcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ARE YOU READY");
        welcome.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TO BE");
        welcome.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(122, 169, 202));
        jLabel3.setText("THE NEXT NEO GENERATION??");
        welcome.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("p.s. baca doa dulu");
        welcome.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, -1, -1));

        getContentPane().add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 960, 360));

        btnIPS.setText("IPS");
        btnIPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIPSActionPerformed(evt);
            }
        });
        getContentPane().add(btnIPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 460, -1, -1));

        btnBahasa.setText("BAHASA");
        btnBahasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBahasaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBahasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, -1, -1));

        btnALL.setText("ALL");
        btnALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnALLActionPerformed(evt);
            }
        });
        getContentPane().add(btnALL, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, -1, -1));

        IPA.setBackground(new java.awt.Color(255, 255, 255));
        IPA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelipa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RANK", "Nama Lengkap", "NISN", "Jurusan", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelipa.setShowGrid(true);
        jScrollPane8.setViewportView(tabelipa);

        IPA.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 960, 362));

        getContentPane().add(IPA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 450));

        ALL.setBackground(new java.awt.Color(255, 255, 255));
        ALL.setPreferredSize(new java.awt.Dimension(1840, 388));

        tabeldata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RANK", "Nama Lengkap", "Jurusan", "NISN", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabeldata.setShowGrid(true);
        jScrollPane7.setViewportView(tabeldata);

        javax.swing.GroupLayout ALLLayout = new javax.swing.GroupLayout(ALL);
        ALL.setLayout(ALLLayout);
        ALLLayout.setHorizontalGroup(
            ALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ALLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        ALLLayout.setVerticalGroup(
            ALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ALLLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        getContentPane().add(ALL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 990, 400));

        IPS.setBackground(new java.awt.Color(255, 255, 255));
        IPS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBackground(new java.awt.Color(153, 188, 229));

        tabelips.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RANK", "Nama Lengkap", "NISN", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelips.setShowGrid(true);
        jScrollPane5.setViewportView(tabelips);

        IPS.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 960, 362));

        getContentPane().add(IPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 970, 400));

        BAHASA.setBackground(new java.awt.Color(255, 255, 255));
        BAHASA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelbhs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RANK", "Nama Lengkap", "NISN", "Jurusan", "Jenis Kelamin", "Agama", "PKN", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "IPA", "IPS", "SKOR AKHIR", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelbhs.setShowGrid(true);
        jScrollPane6.setViewportView(tabelbhs);

        BAHASA.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 960, 362));

        getContentPane().add(BAHASA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 970, 400));

        btnLogout.setBackground(new java.awt.Color(255, 0, 0));
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Aplikasi/white.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIPAActionPerformed
        // Menyembunyikan panel selain panel IPA
        welcome.setVisible(false);
        ALL.setVisible(false);
        IPS.setVisible(false);
        BAHASA.setVisible(false);
        IPA.setVisible(true);
        
        // Menampilkan data 
        lihatIPA();
        tabelipa.setModel(tabMode);
        tabelipa.setDefaultEditor(Object.class, null);
        
        // Mengatur lebar kolom secara otomatis sesuai dengan konten
        resize(tabelipa);
    }//GEN-LAST:event_btnIPAActionPerformed

    private void btnIPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIPSActionPerformed
        // Menyembunyikan panel selain panel IPS
        welcome.setVisible(false);
        ALL.setVisible(false);
        BAHASA.setVisible(false);
        IPA.setVisible(false);
        IPS.setVisible(true);
        
        // Menampilkan data
        lihatIPS();
        tabelips.setModel(tabMode);
        tabelips.setDefaultEditor(Object.class, null);
        
        // Mengatur lebar kolom secara otomatis sesuai dengan konten
        resize(tabelips);
    }//GEN-LAST:event_btnIPSActionPerformed

    private void btnBahasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBahasaActionPerformed
        // Menyembunyikan panel selain panel BAHASA
        welcome.setVisible(false);
        ALL.setVisible(false);
        IPS.setVisible(false);
        IPA.setVisible(false);
        BAHASA.setVisible(true);
        
        // Menampilkan data
        lihatBahasa();
        tabelbhs.setModel(tabMode);
        tabelbhs.setDefaultEditor(Object.class, null);
        
        // Mengatur lebar kolom secara otomatis sesuai dengan konten
        resize(tabelbhs);
    }//GEN-LAST:event_btnBahasaActionPerformed

    private void btnALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnALLActionPerformed
        // Menyembunyikan panel selain panel ALL
        welcome.setVisible(false);
        ALL.setVisible(true);
        IPS.setVisible(false);
        BAHASA.setVisible(false);
        IPA.setVisible(false);
        
        // Menampilkan data
        lihat();
        tabeldata.setModel(tabMode);
        tabeldata.setDefaultEditor(Object.class, null);
        
        // Mengatur lebar kolom secara otomatis sesuai dengan konten
        resize(tabeldata);
    }//GEN-LAST:event_btnALLActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // Menyembunyikan panel selain panel welcome
        welcome.setVisible(true);
        ALL.setVisible(false);
        IPS.setVisible(false);
        BAHASA.setVisible(false);
        IPA.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

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
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ALL;
    private javax.swing.JPanel BAHASA;
    private javax.swing.JPanel IPA;
    private javax.swing.JPanel IPS;
    private javax.swing.JButton btnALL;
    private javax.swing.JButton btnBahasa;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnIPA;
    private javax.swing.JButton btnIPS;
    private javax.swing.JToggleButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable tabelbhs;
    private javax.swing.JTable tabeldata;
    private javax.swing.JTable tabelipa;
    private javax.swing.JTable tabelips;
    private javax.swing.JPanel welcome;
    // End of variables declaration//GEN-END:variables
}

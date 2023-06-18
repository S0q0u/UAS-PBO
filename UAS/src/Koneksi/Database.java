/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;

/**
 *
 * @author Harlin
 */

import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
    public static Connection koneksi;
    public static com.mysql.jdbc.Statement statement;
    public static ResultSet result;
    public static com.mysql.jdbc.PreparedStatement Pstatement;
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB = "jdbc:mysql://localhost:3306/dbSeleksi";
    
    public static void koneksi(){
        try{
            Class.forName(JDBC_DRIVER);
            koneksi = DriverManager.getConnection(DB,"root","");
        }
        catch (Exception e){
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if(koneksi==null){
//            new Driver();
            koneksi = DriverManager.getConnection(DB,"root","");
        }
        return koneksi;
    }
    
    public static void main(String[] args){
        try{
            getConnection();
            JOptionPane.showMessageDialog(null,"Koneksi BERHASIL","report koneksi",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException ex){
            System.err.println("Koneksi GAGAL");
        }
    }
}

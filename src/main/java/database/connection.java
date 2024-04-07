/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ivwi3
 */
public class connection {
    private static Connection con;
    private static String url = "jdbc:mysql://localhost:3306/demo2";
    private static String user = "root";
    private static String password = "";
    
    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.err.println("Lỗi kết nối đến database");
        }
        return con;
    }
}

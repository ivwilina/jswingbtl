/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import database.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author ivwi3
 */
public class Model_login {
    private String id,username,password,phanquyen;
    private Connection con = null;
    
    public Model_login() {
    }

    public Model_login(String id, String username, String password, String phanquyen) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phanquyen = phanquyen;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhanquyen(String phanquyen) {
        this.phanquyen = phanquyen;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhanquyen() {
        return phanquyen;
    }
    
    public String dangNhap() {
        String phanquyen = "";
        try {
            con = connection.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                if(rs.getString("username").isEmpty()) {
                    phanquyen = "khongtontai";
                }else {
                    phanquyen = rs.getString("phanquyen");
                    this.id = rs.getString("id");
                }
            }
            return phanquyen;
        } catch (Exception e) {
        }
        return phanquyen;
    }
    
}

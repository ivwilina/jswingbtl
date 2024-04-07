/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author ivwi3
 */
public class Model_dethi {
    private String made, monhoc;
    private int socau, thoiluong;
    private Connection con;
    
    public Model_dethi() {
    }

    public Model_dethi(String made, String monhoc, int socau, int thoiluong) {
        this.made = made;
        this.monhoc = monhoc;
        this.socau = socau;
        this.thoiluong = thoiluong;
    }

    public String getMade() {
        return made;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public int getSocau() {
        return socau;
    }

    public int getThoiluong() {
        return thoiluong;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    public void setSocau(int socau) {
        this.socau = socau;
    }

    public void setThoiluong(int thoiluong) {
        this.thoiluong = thoiluong;
    }
    
    public ResultSet getAllDethi() {
        ResultSet rs = null;
        
        try {
            con = connection.getConnection();
            String sql = "select * from dethi";
            PreparedStatement st = con.prepareStatement(sql);
            rs = st.executeQuery();
            return rs;
        } catch (Exception e) {
        }
        
        return rs;
    }
    
    public ResultSet getDethi() {
        ResultSet rs = null;
        return rs;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.connection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author ivwi3
 */
public class Model_hocsinh {
    private String id,hoten,sdt,quequan;
    private Connection con;
    
    public Model_hocsinh() {
    }

    public Model_hocsinh(String id, String hoten, String sdt, String quequan) {
        this.id = id;
        this.hoten = hoten;
        this.sdt = sdt;
        this.quequan = quequan;
    }

    public String getId() {
        return id;
    }

    public String getHoten() {
        return hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }
    
    public void getThongtinhs() {
        ResultSet rs;
        try {
            con = connection.getConnection();
            String sql = "select * from user where id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,id);
            rs = st.executeQuery();
            while(rs.next()) {
                this.hoten = rs.getNString("hoten");
                this.sdt = rs.getNString("sdt");
                this.quequan = rs.getNString("quequan");
            }
        } catch (Exception e) {
        }
    }
    public void SuaThongTinHS() {
    try {
       con = connection.getConnection();
    String Sql = "Update  user  Set hoten = ?,sdt = ?,quequan = ? where id = ?";
   
    PreparedStatement st = con.prepareStatement(Sql);
    st.setString(1, hoten);
    st.setString(2, sdt);
    st.setString(3,quequan);
    st.setString(4,id);
    st.executeUpdate();
    }
    catch(Exception e ){}
    }
}

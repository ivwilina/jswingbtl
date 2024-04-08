/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import database.connection;
import java.sql.Connection;
import model.Model_hocsinh;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;

/**
 *
 * @author ivwi3
 */
public class View_giaovien extends javax.swing.JFrame {
    private Connection con = null;
    Map<String,String> mapMonhoc,mapMonhoc2;
    DefaultTableModel hienthi, andanh;
    int macau, tempIndex;
    String debai,da1,da2,da3,da4,mamonz;
    Model_hocsinh modelHocsinh;
    public String id ;
     Map<String, String> map_mamon = new HashMap<>();
    Map<String, String> map_mamon2 = new HashMap<>();
    public View_giaovien(String id) {
        initComponents();
        init2();
        this.id=id;
        load_info();
        load_table();
        load_cbMamon();
        txtid.setEnabled(false);
    }
    
    public void init2() {
        mapMonhoc = new HashMap<>();
        mapMonhoc2 = new HashMap<>();
        load_Cauhoi("");
        load_cbmonhoc();
        String [] rowHeadz1 = {"Mã Câu","Đề Bài"};
        hienthi = new DefaultTableModel(rowHeadz1, 0);
        String [] rowHeadz2 = {"Mã câu","Môn","Đề bài","ĐA1","ĐA2","ĐA3","ĐA4"};
        andanh = new DefaultTableModel(rowHeadz2, 0);
        }
    
    public void load_Cauhoi(String mamon) {
        try {
            con = connection.getConnection();
            ResultSet rs = null;
            String sql = "select * from cauhoi where mamon like ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,"%"+mamon+"%");
            rs = st.executeQuery();
            tableCauhoi.removeAll();
            String [] rowHead = {"Mã câu","Môn","Đề bài","ĐA1","ĐA2","ĐA3","ĐA4"};
            DefaultTableModel dtm = new DefaultTableModel(rowHead,0);
            while(rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("idcauhoi"));
                v.add(rs.getString("mamon"));
                v.add(rs.getString("debai"));
                v.add(rs.getString("dapan1"));
                v.add(rs.getString("dapan2"));
                v.add(rs.getString("dapan3"));
                v.add(rs.getString("dapan4"));
                dtm.addRow(v);
            }
            tableCauhoi.setModel(dtm);
            st.close();
            con.close();
        } catch (Exception e) {
        }
    }
    public void load_info() {
        try {
            String a,b,c ;
             ResultSet rs;
    con = connection.getConnection();
    String sql = "select hoten,sdt,quequan from user where id = ? ";
     PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,id);
            rs = st.executeQuery();
            while(rs.next()) {
                a = rs.getNString("hoten");
                b=rs.getNString("sdt");
                c=rs.getNString("quequan");
                txt_hoten_gv.setText(a);
                txt_que_gv.setText(c);
                txt_sdt_gv.setText(b);
                
            }
            txt_hoten_gv.setEnabled(false);
            txt_que_gv.setEnabled(false);
            txt_sdt_gv.setEnabled(false);
        }
        catch (Exception e ) {
        }
    }
    
    public void load_cbmonhoc() {
        try {
            con = connection.getConnection();
            ResultSet rs = null;
            String sql = "select * from monhoc";
            PreparedStatement st = con.prepareStatement(sql);
            rs = st.executeQuery();
            tableCauhoi.removeAll();
            while(rs.next()) {
                cbChonmon.addItem(rs.getString("tenmon"));
                mapMonhoc.put(rs.getString("tenmon"), rs.getString("mamon"));
                mapMonhoc2.put(rs.getString("mamon"), rs.getString("tenmon"));
            }
            st.close();
            con.close();
        } catch (Exception e) {
        }
    }
    private void load_table(){
        try{
            con= connection.getConnection();
            String sqlQueryGetSach="Select * from cauhoi";
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery(sqlQueryGetSach);
            
            tbcauhoi.removeAll();
            String[] rowhead={"ID Câu Hỏi","Môn Học","Đề Bài","Câu 1","Câu 2","Câu 3","Câu 4"};
            DefaultTableModel tb = new DefaultTableModel(rowhead,0);
            while(rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                tb.addRow(v);
            }
            tbcauhoi.setModel(tb);
            st.close();
            con.close();
        }catch(Exception e){  
        }
    }
    private void load_cbMamon(){
         try {
           con= connection.getConnection();
            String sqlQueryGetLoaiSach="Select * from monhoc";
            Statement st=con.createStatement();
     
            ResultSet rs=st.executeQuery(sqlQueryGetLoaiSach);
            
            while(rs.next()) {
                cbmamon.addItem(rs.getString("tenmon"));
                map_mamon.put(rs.getString("mamon"),rs.getString("tenmon"));
                map_mamon2.put(rs.getString("tenmon"),rs.getString("mamon"));
            }
            
            st.close();
            con.close();
            
        } catch (Exception e) {
            System.out.println("qlytacgia.jKT01.load_cbMaloai()");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCauhoi = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTempDethi = new javax.swing.JTable();
        txtDebai = new javax.swing.JLabel();
        txtDapan1 = new javax.swing.JLabel();
        txtDapan2 = new javax.swing.JLabel();
        txtDapan3 = new javax.swing.JLabel();
        txtDapan4 = new javax.swing.JLabel();
        cbChonmon = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        spinThoiluong = new javax.swing.JSpinner();
        btnTaode = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txt_hoten_gv = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_sdt_gv = new javax.swing.JTextField();
        txt_que_gv = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtcau4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbcauhoi = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtcau1 = new javax.swing.JTextField();
        cbmamon = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btntimkiem = new javax.swing.JButton();
        txtdebai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        txtcau2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnsua = new javax.swing.JButton();
        txtcau3 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnxoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        tableCauhoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableCauhoi.setGridColor(new java.awt.Color(255, 255, 255));
        tableCauhoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCauhoiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCauhoi);

        tableTempDethi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã câu", "Câu hỏi"
            }
        ));
        tableTempDethi.setGridColor(new java.awt.Color(255, 255, 255));
        tableTempDethi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTempDethiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTempDethi);

        txtDebai.setBackground(new java.awt.Color(255, 255, 255));
        txtDebai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDebai.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txtDebai.setOpaque(true);

        txtDapan1.setBackground(new java.awt.Color(255, 255, 255));
        txtDapan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDapan1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txtDapan1.setOpaque(true);

        txtDapan2.setBackground(new java.awt.Color(255, 255, 255));
        txtDapan2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDapan2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txtDapan2.setOpaque(true);

        txtDapan3.setBackground(new java.awt.Color(255, 255, 255));
        txtDapan3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDapan3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txtDapan3.setOpaque(true);

        txtDapan4.setBackground(new java.awt.Color(255, 255, 255));
        txtDapan4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDapan4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txtDapan4.setOpaque(true);

        cbChonmon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbChonmon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn môn học" }));
        cbChonmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChonmonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Chọn môn");

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm vào đề");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoa.setText("Xóa khỏi đề");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Thời lượng (phút)");

        spinThoiluong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnTaode.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTaode.setText("Tạo đề");
        btnTaode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDapan4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                            .addComponent(txtDapan3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDapan2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDapan1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDebai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addContainerGap(138, Short.MAX_VALUE))
                            .addComponent(cbChonmon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(spinThoiluong)
                            .addComponent(btnTaode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtDebai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDapan1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDapan2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDapan3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDapan4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbChonmon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinThoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaode, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đề Thi", jPanel2);

        jPanel3.setOpaque(false);

        jButton1.setText("Đăng xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Họ tên");

        jLabel2.setText("Số điện thoại");

        jLabel3.setText("Quê quán");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_hoten_gv)
                            .addComponent(txt_sdt_gv)
                            .addComponent(txt_que_gv, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(379, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hoten_gv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_sdt_gv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_que_gv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(303, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin", jPanel3);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtcau4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcau4ActionPerformed(evt);
            }
        });

        jLabel9.setText("Câu 4");

        tbcauhoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Câu Hỏi", "Môn Học", "Đề Bài", "Câu 1", "Câu 2", "Câu 3", "Câu 4"
            }
        ));
        tbcauhoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcauhoiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbcauhoi);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Câu Hỏi Đề Thi");

        cbmamon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Môn Học", " " }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Đáp án");

        btntimkiem.setText("Tìm Kiếm");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        jLabel5.setText("Câu 1");

        jLabel6.setText("ID Câu hỏi");

        btnthem.setText("Thêm Mới");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jLabel11.setText("Câu 2");

        jLabel12.setText("Mã Môn Học");

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        txtcau3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcau3ActionPerformed(evt);
            }
        });

        jLabel13.setText("Câu 3");

        jLabel14.setText("Đề Bài");

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtid)
                            .addComponent(cbmamon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdebai, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcau1)
                                    .addComponent(txtcau2)
                                    .addComponent(txtcau3)
                                    .addComponent(txtcau4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnthem)
                .addGap(57, 57, 57)
                .addComponent(btnsua)
                .addGap(45, 45, 45)
                .addComponent(btnxoa)
                .addGap(61, 61, 61)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btntimkiem)
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cbmamon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtdebai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtcau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtcau3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtcau4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnxoa)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Câu hỏi", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbChonmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChonmonActionPerformed
        String monhoc = cbChonmon.getSelectedItem().toString();
        String mamon;
        if("Chọn môn học".equals(monhoc)) {
            mamon = "";
        }else {
            mamon = mapMonhoc.get(monhoc);
        }
        load_Cauhoi(mamon);
    }//GEN-LAST:event_cbChonmonActionPerformed

    private void tableCauhoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCauhoiMouseClicked
            int index = tableCauhoi.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel) tableCauhoi.getModel();
            macau = Integer.parseInt(dtm.getValueAt(index, 0).toString());
            mamonz = dtm.getValueAt(index, 1).toString();
            debai = dtm.getValueAt(index, 2).toString();
            da1 = dtm.getValueAt(index, 3).toString();
            da2 = dtm.getValueAt(index, 4).toString();
            da3 = dtm.getValueAt(index, 5).toString();
            da4 = dtm.getValueAt(index, 6).toString();
            txtDebai.setText(dtm.getValueAt(index, 2).toString());
            txtDapan1.setText(dtm.getValueAt(index, 3).toString());
            txtDapan2.setText(dtm.getValueAt(index, 4).toString());
            txtDapan3.setText(dtm.getValueAt(index, 5).toString());
            txtDapan4.setText(dtm.getValueAt(index, 6).toString());
    }//GEN-LAST:event_tableCauhoiMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        cbChonmon.setSelectedItem(mapMonhoc2.get(mamonz));
        load_Cauhoi(mamonz);
        cbChonmon.setEnabled(false);
        Vector v = new Vector();
        v.add(macau);
        v.add(debai);
        v.add(da1);
        v.add(da2);
        v.add(da3);
        v.add(da4);
        andanh.addRow(v);
        Vector v2 = new Vector();
        v2.add(macau);
        v2.add(debai);
        hienthi.addRow(v2);
        tableTempDethi.setModel(hienthi);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        hienthi.removeRow(tempIndex);
        andanh.removeRow(tempIndex);
        tableTempDethi.setModel(hienthi);
        txtDebai.setText("");
        txtDapan1.setText("");
        txtDapan2.setText("");
        txtDapan3.setText("");
        txtDapan4.setText("");
        if(hienthi.getRowCount()==0) {
            cbChonmon.setEnabled(true);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableTempDethiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTempDethiMouseClicked
        int index = tableTempDethi.getSelectedRow();
        tempIndex = index;
        txtDebai.setText(andanh.getValueAt(index, 1).toString());
        txtDapan1.setText(andanh.getValueAt(index, 2).toString());
        txtDapan2.setText(andanh.getValueAt(index, 3).toString());
        txtDapan3.setText(andanh.getValueAt(index, 4).toString());
        txtDapan4.setText(andanh.getValueAt(index, 5).toString());
    }//GEN-LAST:event_tableTempDethiMouseClicked

    private void btnTaodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaodeActionPerformed
        ArrayList<Integer> list =  new ArrayList<>();
        for(int i=0;i<hienthi.getRowCount();i++) {
            list.add((int)hienthi.getValueAt(i, 0));
        }
        String dscauhoi = list.toString();
        String monhoc = mapMonhoc2.get(mamonz);
        int socau = hienthi.getRowCount();
        int thoiluong = (int) spinThoiluong.getValue();
        try {
            con = connection.getConnection();
            String sql = "INSERT INTO `dethi`(`monhoc`, `socau`, `thoiluong`, `dscauhoi`) VALUES ('"+monhoc+"','"+socau+"','"+thoiluong+"','"+dscauhoi+"')";
            Statement st=con.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Tạo đề thành công");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTaodeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       new View_login().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
       
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void txtcau4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcau4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcau4ActionPerformed

    private void tbcauhoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcauhoiMouseClicked
        // TODO add your handling code here:
        try {
            //
            int index=tbcauhoi.getSelectedRow();
            DefaultTableModel dtm=(DefaultTableModel) tbcauhoi.getModel();
            txtid.setText(dtm.getValueAt(index, 0).toString());
            cbmamon.setSelectedItem(map_mamon.get(dtm.getValueAt(index, 1).toString()));
            txtdebai.setText(dtm.getValueAt(index, 2).toString());
            txtcau1.setText(dtm.getValueAt(index, 3).toString());
            txtcau2.setText(dtm.getValueAt(index, 4).toString());
            txtcau3.setText(dtm.getValueAt(index, 5).toString());
            txtcau4.setText(dtm.getValueAt(index, 6).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tbcauhoiMouseClicked

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
        String id, mm = "";
        id = txttimkiem.getText();
        if("Chọn Môn Học".equals(cbmamon.getSelectedItem().toString())){
            mm="";
        }else{
            mm=map_mamon2.get(cbmamon.getSelectedItem().toString());
        }
        try {
            con=connection.getConnection();
            String sql = "SELECT * from cauhoi WHERE idcauhoi like '%"+id+"%' AND mamon like '%"+mm+"%' ";
            System.out.println(sql);
            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery(sql);

            tbcauhoi.removeAll();
            String[] rowhead={"ID Câu Hỏi","Môn Học","Đề Bài","Câu 1","Câu 2","Câu 3","Câu 4"};
            DefaultTableModel tb = new DefaultTableModel(rowhead,0);
            while(rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                tb.addRow(v);
            }

            tbcauhoi.setModel(tb);
            st.close();
            con.close();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        String mm,db,c1,c2,c3,c4;
        mm=map_mamon2.get(cbmamon.getSelectedItem().toString());
        db=txtdebai.getText();
        c1=txtcau1.getText();
        c2=txtcau2.getText();
        c3=txtcau3.getText();
        c4=txtcau4.getText();
        if(mm.isEmpty() || db.isEmpty() || c1.isEmpty() || c2.isEmpty() || c3.isEmpty() || c4.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điền trường còn thiếu");
        }
        try {
            con = connection.getConnection();
            String sql = "INSERT INTO cauhoi(mamon,debai,dapan1,dapan2,dapan3,dapan4) VAlUES ('"+mm+"','"+db+"','"+c1+"','"+c2+"','"+c3+"','"+c4+"')";
            System.out.println(sql);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Thêm thành công");

            load_table();
            con.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        String id, mm,db,c1,c2,c3,c4;
        id = txtid.getText();
        mm=map_mamon2.get(cbmamon.getSelectedItem().toString());
        db=txtdebai.getText();
        c1=txtcau1.getText();
        c2=txtcau2.getText();
        c3=txtcau3.getText();
        c4=txtcau4.getText();
        try {
            con=connection.getConnection();
            String sql = "UPDATE cauhoi SET mamon=N'"+mm+"',debai=N'"+db+"',dapan1=N'"+c1+"',dapan2=N'"+c2+"',dapan3=N'"+c3+"',dapan4="+c4+" WHERE idcauhoi=N'"+id+"'";

            Statement st = con.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            load_table();

            con.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void txtcau3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcau3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcau3ActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        String id;
        id=txtid.getText();

        try {
            con=connection.getConnection();
            String sql = "DELETE from cauhoi WHERE idcauhoi = '"+id+"'";
            System.out.println(sql);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Xoá thành công");
            load_table();

            con.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaode;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbChonmon;
    private javax.swing.JComboBox<String> cbmamon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner spinThoiluong;
    private javax.swing.JTable tableCauhoi;
    private javax.swing.JTable tableTempDethi;
    private javax.swing.JTable tbcauhoi;
    private javax.swing.JLabel txtDapan1;
    private javax.swing.JLabel txtDapan2;
    private javax.swing.JLabel txtDapan3;
    private javax.swing.JLabel txtDapan4;
    private javax.swing.JLabel txtDebai;
    private javax.swing.JTextField txt_hoten_gv;
    private javax.swing.JTextField txt_que_gv;
    private javax.swing.JTextField txt_sdt_gv;
    private javax.swing.JTextField txtcau1;
    private javax.swing.JTextField txtcau2;
    private javax.swing.JTextField txtcau3;
    private javax.swing.JTextField txtcau4;
    private javax.swing.JTextField txtdebai;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}

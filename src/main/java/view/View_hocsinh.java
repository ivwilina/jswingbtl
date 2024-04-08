/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.Model_dethi;
import model.Model_hocsinh;
import controller.Controller_hocsinh;
import database.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author ivwi3
 */
public class View_hocsinh extends javax.swing.JFrame {
        Model_dethi modelDethi;
        Model_hocsinh modelHocsinh;
        private String user;
        private Connection con = null;
    /**
     * Creates new form View_hocsinh
     */
    public View_hocsinh(String id) {
        initComponents();
        modelDethi = new Model_dethi();
        modelHocsinh = new Model_hocsinh();
        modelHocsinh.setId(id);
        modelHocsinh.getThongtinhs();
        lblNavTen.setText(modelHocsinh.getHoten());
        txt_hoten.setText(modelHocsinh.getHoten());
        txt_quequan.setText(modelHocsinh.getQuequan());
        txt_sdt.setText(modelHocsinh.getSdt());
        txt_hoten.setEnabled(false);
        txt_quequan.setEnabled(false);
        txt_sdt.setEnabled(false);
        btn_luu.setEnabled(false);

        txt_hoten.setText(modelHocsinh.getHoten());
        txt_quequan.setText(modelHocsinh.getQuequan());
        txt_sdt.setText(modelHocsinh.getSdt());
        txt_hoten.setEnabled(false);
        txt_quequan.setEnabled(false);
        txt_sdt.setEnabled(false);
        btn_luu.setEnabled(false);

        
        
        
        
        
        
        
        user = id;
        load_alldethi();
        load_ketqua();
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
        tableAllDethi = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblMade = new javax.swing.JLabel();
        lblMonhoc = new javax.swing.JLabel();
        lblSocau = new javax.swing.JLabel();
        lblThoiluong = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ketqua = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_hoten = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        txt_quequan = new javax.swing.JTextField();
        btn_sua = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        btn_doi_mk = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblNavTen = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTabbedPane1.setName(""); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách đề thi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tableAllDethi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableAllDethi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableAllDethi.setGridColor(new java.awt.Color(255, 255, 255));
        tableAllDethi.getTableHeader().setReorderingAllowed(false);
        tableAllDethi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAllDethiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAllDethi);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mã Đề :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Môn Học :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Số Câu :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Thời Lượng :");

        lblMade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblMonhoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblSocau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblThoiluong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setText("Làm Bài");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSocau, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMade, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblMade))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblMonhoc))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblSocau))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblThoiluong)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đề thi", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        ketqua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(ketqua);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Kết quả", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Họ và tên");

        jLabel7.setText("Số điện thoại");

        jLabel8.setText("Quê quán");

        txt_quequan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quequanActionPerformed(evt);
            }
        });

        btn_sua.setText("Sửa thông tin cá nhân");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_luu.setText("Cập nhật");
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        btn_doi_mk.setText("Đổi mật khẩu");
        btn_doi_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doi_mkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(77, 77, 77)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_hoten, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txt_sdt)
                    .addComponent(txt_quequan))
                .addGap(97, 97, 97)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_doi_mk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(188, Short.MAX_VALUE))
            .addGap(0, 827, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sua))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_luu))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_quequan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_doi_mk))
                .addContainerGap(406, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài khoản", jPanel4);

        jPanel1.setBackground(new java.awt.Color(87, 211, 228));

        lblNavTen.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNavTen.setForeground(new java.awt.Color(255, 255, 255));
        lblNavTen.setText("Họ thị văn và tên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Xin Chào");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNavTen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNavTen)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void load_ketqua() {
        try {
            con = connection.getConnection();
            ResultSet rs = null;
            String sql = "select * from ketqua where id ='"+user+"'";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            ketqua.removeAll();
            String [] rowHead = {"Mã lượt thi","Mã đề","Điểm"};
            DefaultTableModel dtm2 = new DefaultTableModel(rowHead,0);
            while(rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("idlanthi"));
                v.add(rs.getInt("made"));
                v.add(rs.getInt("diem"));
                dtm2.addRow(v);
            }
            ketqua.setModel(dtm2);
            st.close();
            con.close();
        } catch (Exception e) {
        }
    }
    
    private void tableAllDethiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAllDethiMouseClicked
        int index = tableAllDethi.getSelectedRow();
        DefaultTableModel dtm  = (DefaultTableModel) tableAllDethi.getModel();
        lblMade.setText(dtm.getValueAt(index, 0).toString());
        lblMonhoc.setText(dtm.getValueAt(index, 1).toString());
        lblSocau.setText(dtm.getValueAt(index, 2).toString());
        lblThoiluong.setText(dtm.getValueAt(index, 3).toString() + " phút");
    }//GEN-LAST:event_tableAllDethiMouseClicked


    private void txt_quequanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quequanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quequanActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
 txt_hoten.setEnabled(true);
        txt_quequan.setEnabled(true);
        txt_sdt.setEnabled(true);  
        btn_luu.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        String hoten = txt_hoten.getText().trim();
        String quequan = txt_quequan.getText().trim();
        String sdt = txt_sdt.getText().trim();
        // assign value 
        modelHocsinh.setHoten(hoten);
        modelHocsinh.setQuequan(quequan);
        modelHocsinh.setSdt(sdt);
        modelHocsinh.setId(modelHocsinh.getId());
        modelHocsinh.SuaThongTinHS();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công <3 ");
        // load lai info
        txt_hoten.setText(modelHocsinh.getHoten());
        txt_quequan.setText(modelHocsinh.getQuequan());
        txt_sdt.setText(modelHocsinh.getSdt());
        //vísible 
        btn_luu.setEnabled(false);
         txt_hoten.setEnabled(false);
        txt_quequan.setEnabled(false);
        txt_sdt.setEnabled(false);  
    }//GEN-LAST:event_btn_luuActionPerformed

    private void btn_doi_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doi_mkActionPerformed
       String a = modelHocsinh.getId();
        new Doi_pass(a).setVisible(true);
    }//GEN-LAST:event_btn_doi_mkActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String made = lblMade.getText();
        if(made.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đề");
            return;
        }
        new View_lambai(made,user).setVisible(true);
        this.toBack();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void load_alldethi() {
        try {
            ResultSet allDethi = modelDethi.getAllDethi();
            tableAllDethi.removeAll();
            String [] rowHead = {"Mã đề","Môn học","Số câu","Thời lượng (phút)"};
                DefaultTableModel dtm = new DefaultTableModel(rowHead,0);
                while(allDethi.next()) {
                    Vector v = new Vector();
                    v.add(allDethi.getString("made"));
                    v.add(allDethi.getString("monhoc"));
                    v.add(allDethi.getString("socau"));
                    v.add(allDethi.getString("thoiluong"));
                    dtm.addRow(v);
                }
                tableAllDethi.setModel(dtm);   
        } catch (Exception e) {
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_doi_mk;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable ketqua;
    private javax.swing.JLabel lblMade;
    private javax.swing.JLabel lblMonhoc;
    private javax.swing.JLabel lblNavTen;
    private javax.swing.JLabel lblSocau;
    private javax.swing.JLabel lblThoiluong;
    private javax.swing.JTable tableAllDethi;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_quequan;
    private javax.swing.JTextField txt_sdt;
    // End of variables declaration//GEN-END:variables
}

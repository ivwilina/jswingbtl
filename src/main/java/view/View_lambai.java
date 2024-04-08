/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import database.connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author ivwi3
 */
public class View_lambai extends javax.swing.JFrame {
    private Connection con;
    DefaultTableModel hienthi, andanh, dapan;
    int stt;
    int time,time2,seconds;
    Timer timer;
    int upMade,upDiem;
    String upUser;
    /**
     * Creates new form View_lambai
     */
    public View_lambai(String made, String user) {
        initComponents();
        init2();
        settingUp(made);
        onStartUp();
        countDown();
        timer.start();
        upUser = user;
        upMade = Integer.parseInt(made);
    }
    
    public void shuffleDance() {
        ArrayList<Integer> list =  new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Collections.shuffle(list);
        System.out.println(list.toString());
    }
    
    public void init2() {
        stt = 0;
        String [] rowHead1 = {"Câu","Đáp án chọn"};
        String [] rowHead2 = {"Câu","Đề Bài","d1","d2","d3","d4"};
        String [] rowHead3 = {"Câu","Đáp án"};
        hienthi = new DefaultTableModel(rowHead1, 0);
        andanh = new DefaultTableModel(rowHead2, 0);
        dapan = new DefaultTableModel(rowHead3, 0);

    }
    
    public void countDown() {
        timebar.setMaximum((int) time2);
        long temp = time2;
        seconds = 0;
        timebar.setMinimum(0);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timebar.getValue() < temp) {

                    int h = (int) time2 / 3600;
                    int m = (int) (time2-h*3600) / 60;
                    int s = (int) time2-h*3600-m*60;
                    if (h > 0) {

                        if (m > 0) {
                            timebar.setString(h + " : " + m + " : " + s);
                        }

                    } else {
                        if (m > 0) {
                            timebar.setString(m + " : " + s);

                        } else {
                            timebar.setString("00 : " + s);
                        }
                    }
                    lbTime.setText(h+"h: "+m+"p: "+s+"s");
                    time2--;
                    seconds++;
                    timebar.setValue(seconds);
                } else {
                    timer.stop();

                    int show = JOptionPane.showConfirmDialog(null, "Đã hết thời gian làm bài!", "Time up", 2);

                    if (show == 0) {
                        chamDiem();
                    }

                }
            }
        });
    }
    
    public void chamDiem() {
        int quesCount = 0;
        for(int i=0;i<hienthi.getRowCount();i++) {
            String selection;
            if((hienthi.getValueAt(i, 1).toString()).equals(dapan.getValueAt(i, 1).toString())) {
                quesCount++;
            }
        }
        upDiem = (quesCount/hienthi.getRowCount()*10);
        System.out.println(upDiem);
        try {
            con = connection.getConnection();
            String sql = "INSERT INTO `ketqua`(`made`, `id`, `diem`) VALUES ('"+upMade+"','"+upUser+"','"+upDiem+"')";
            Statement st=con.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
        }
    }
    
    public void settingUp(String Made) {
        String dscauhoi="";
        try {
            int made = Integer.parseInt(Made);
            con = connection.getConnection();
            ResultSet rs;
            String sql = "select * from dethi where made="+made+"";
            Statement st=con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {   
                lblMade.setText(rs.getString("made"));
                lblSocau.setText(rs.getString("socau"));
                lblMonhoc.setText(rs.getString("monhoc"));
                dscauhoi = rs.getString("dscauhoi");
                time = (int) rs.getInt("thoiluong");
                time2 = time*60;
                lbTime.setText(""+time+" phút");
            }
            
            
            String dscauhoi2 = dscauhoi.replace("[", "").replace("]", "").replace(" ","");
            String [] s = dscauhoi2.split(",");
            
            ArrayList<String> daoCau = new ArrayList<>();
            for(String a : s) {
                daoCau.add(a);
            }
            Collections.shuffle(daoCau);
            int size = 0;
            for(String a : daoCau) {
                size+=1;
                ResultSet rs2;
                String sql2 = "select * from cauhoi where idcauhoi="+a+"";
                Statement st2 = con.createStatement();
                rs2 = st2.executeQuery(sql2);
                while(rs2.next()) {
                    Vector v1 = new Vector();
                    Vector v2 = new Vector();
                    Vector v3 = new Vector();
                        v1.add(size);
                        v1.add("");
                        v2.add(rs2.getString("debai"));
                        v3.add(size);
                    String temp = rs2.getString("dapan1");
                    ArrayList<String> tempList = new ArrayList();
                    tempList.add(rs2.getString("dapan1"));
                    tempList.add(rs2.getString("dapan2"));
                    tempList.add(rs2.getString("dapan3"));
                    tempList.add(rs2.getString("dapan4"));
                    Collections.shuffle(tempList);
                    int tempCount = 1;
                    for(String m : tempList) {
                        v2.add(m);
                        if(m.equals(temp)) {
                            if(tempCount == 1) {
                                v3.add("A");
                            }else if(tempCount == 2) {
                                v3.add("B");
                            }else if(tempCount == 3) {
                                v3.add("C");
                            }else if(tempCount == 4) {
                                v3.add("D");
                            }
                        }
                        tempCount+=1;
                    }
                    hienthi.addRow(v1);
                    andanh.addRow(v2);
                    dapan.addRow(v3);
                }
                tableDscauhoi.setModel(hienthi);
                st2.close();
            }
        } catch (Exception e) {
        }
    }
    
    public void onStartUp() {
        if(stt == 0) {
            btnCautruoc.setEnabled(false);
            btnCautiep.setEnabled(true);
        }else if(stt == hienthi.getRowCount()) {
            btnCautiep.setEnabled(false);
            btnCautruoc.setEnabled(false);
        }else {
            btnCautruoc.setEnabled(true);
            btnCautiep.setEnabled(true);
        }
        lblCau.setText("Câu " +(stt+1) );
        lblDebai.setText(andanh.getValueAt(stt, 0).toString());
        radDapan1.setText(andanh.getValueAt(stt, 1).toString());
        radDapan2.setText(andanh.getValueAt(stt, 2).toString());
        radDapan3.setText(andanh.getValueAt(stt, 3).toString());
        radDapan4.setText(andanh.getValueAt(stt, 4).toString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chonDapan = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDscauhoi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMade = new javax.swing.JLabel();
        lblMonhoc = new javax.swing.JLabel();
        lblSocau = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnNopbai = new javax.swing.JButton();
        timebar = new javax.swing.JProgressBar();
        lbTime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCau = new javax.swing.JLabel();
        lblDebai = new javax.swing.JLabel();
        radDapan1 = new javax.swing.JRadioButton();
        radDapan2 = new javax.swing.JRadioButton();
        radDapan3 = new javax.swing.JRadioButton();
        radDapan4 = new javax.swing.JRadioButton();
        btnCautiep = new javax.swing.JButton();
        btnCautruoc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableDscauhoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tableDscauhoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDscauhoiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDscauhoi);

        jPanel1.setBackground(new java.awt.Color(87, 211, 228));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Môn học :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Số câu :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mã đề :");

        lblMade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMade.setText("lblMade");

        lblMonhoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMonhoc.setText("lblMonhoc");

        lblSocau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblSocau.setText("lblSocau");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Thời gian :");

        btnNopbai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNopbai.setText("Nộp Bài");
        btnNopbai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNopbaiActionPerformed(evt);
            }
        });

        timebar.setBackground(new java.awt.Color(242, 242, 242));
        timebar.setForeground(new java.awt.Color(0, 255, 102));

        lbTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(40, 40, 40)
                        .addComponent(lblMade, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(btnNopbai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSocau, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lblMonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timebar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTime)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblMade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblMonhoc)
                    .addComponent(jLabel4)
                    .addComponent(lbTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblSocau)
                    .addComponent(btnNopbai)
                    .addComponent(timebar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblCau.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCau.setText("Câu ");

        lblDebai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDebai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDebai.setText("Đề bài");
        lblDebai.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        chonDapan.add(radDapan1);
        radDapan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radDapan1.setText("jRadioButton1");
        radDapan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radDapan1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        radDapan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDapan1ActionPerformed(evt);
            }
        });

        chonDapan.add(radDapan2);
        radDapan2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radDapan2.setText("jRadioButton1");
        radDapan2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radDapan2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        radDapan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDapan2ActionPerformed(evt);
            }
        });

        chonDapan.add(radDapan3);
        radDapan3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radDapan3.setText("jRadioButton1");
        radDapan3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radDapan3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        radDapan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDapan3ActionPerformed(evt);
            }
        });

        chonDapan.add(radDapan4);
        radDapan4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radDapan4.setText("jRadioButton1");
        radDapan4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        radDapan4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        radDapan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDapan4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDebai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radDapan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radDapan2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radDapan4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radDapan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCau)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDebai, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radDapan1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radDapan2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radDapan3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radDapan4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCautiep.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCautiep.setText("Câu tiếp >");
        btnCautiep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCautiepActionPerformed(evt);
            }
        });

        btnCautruoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCautruoc.setText("< Câu trước");
        btnCautruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCautruocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCautruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCautiep, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCautiep, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(btnCautruoc, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableDscauhoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDscauhoiMouseClicked
        stt = tableDscauhoi.getSelectedRow();
        if(stt == 0) {
            btnCautruoc.setEnabled(false);
            btnCautiep.setEnabled(true);
        }else if(stt == hienthi.getRowCount()-1) {
            btnCautiep.setEnabled(false);
            btnCautruoc.setEnabled(true);
        }else {
            btnCautruoc.setEnabled(true);
            btnCautiep.setEnabled(true);
        }
        
        if("A".equals(hienthi.getValueAt(stt, 1).toString())){
            radDapan1.setSelected(true);
        }else if("B".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan2.setSelected(true);
        }else if("C".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan3.setSelected(true);
        }else if("D".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan4.setSelected(true);
        }else {
            chonDapan.clearSelection();
        }
        
        lblCau.setText("Câu " +(stt+1) );
        lblDebai.setText(andanh.getValueAt(stt, 0).toString());
        radDapan1.setText(andanh.getValueAt(stt, 1).toString());
        radDapan2.setText(andanh.getValueAt(stt, 2).toString());
        radDapan3.setText(andanh.getValueAt(stt, 3).toString());
        radDapan4.setText(andanh.getValueAt(stt, 4).toString());
    }//GEN-LAST:event_tableDscauhoiMouseClicked

    private void btnCautruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCautruocActionPerformed
        stt--;
        if(stt == 0) {
            btnCautruoc.setEnabled(false);
            btnCautiep.setEnabled(true);
        }else if(stt == hienthi.getRowCount()-1) {
            btnCautiep.setEnabled(false);
            btnCautruoc.setEnabled(true);
        }else {
            btnCautruoc.setEnabled(true);
            btnCautiep.setEnabled(true);
        }
        
        if("A".equals(hienthi.getValueAt(stt, 1).toString())){
            radDapan1.setSelected(true);
        }else if("B".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan2.setSelected(true);
        }else if("C".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan3.setSelected(true);
        }else if("D".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan4.setSelected(true);
        }else {
            chonDapan.clearSelection();
        }
        
        lblCau.setText("Câu " +(stt+1) );
        lblDebai.setText(andanh.getValueAt(stt, 0).toString());
        radDapan1.setText(andanh.getValueAt(stt, 1).toString());
        radDapan2.setText(andanh.getValueAt(stt, 2).toString());
        radDapan3.setText(andanh.getValueAt(stt, 3).toString());
        radDapan4.setText(andanh.getValueAt(stt, 4).toString());
    }//GEN-LAST:event_btnCautruocActionPerformed

    private void btnCautiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCautiepActionPerformed
        stt++;
        if(stt == 0) {
            btnCautruoc.setEnabled(false);
            btnCautiep.setEnabled(true);
        }else if(stt == hienthi.getRowCount()-1) {
            btnCautiep.setEnabled(false);
            btnCautruoc.setEnabled(true);
        }else {
            btnCautruoc.setEnabled(true);
            btnCautiep.setEnabled(true);
        }
        
        if("A".equals(hienthi.getValueAt(stt, 1).toString())){
            radDapan1.setSelected(true);
        }else if("B".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan2.setSelected(true);
        }else if("C".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan3.setSelected(true);
        }else if("D".equals(hienthi.getValueAt(stt, 1).toString())) {
            radDapan4.setSelected(true);
        }else {
            chonDapan.clearSelection();
        }
        
        lblCau.setText("Câu " +(stt+1) );
        lblDebai.setText(andanh.getValueAt(stt, 0).toString());
        radDapan1.setText(andanh.getValueAt(stt, 1).toString());
        radDapan2.setText(andanh.getValueAt(stt, 2).toString());
        radDapan3.setText(andanh.getValueAt(stt, 3).toString());
        radDapan4.setText(andanh.getValueAt(stt, 4).toString());
    }//GEN-LAST:event_btnCautiepActionPerformed

    private void radDapan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDapan1ActionPerformed
        hienthi.setValueAt("A", stt, 1);
    }//GEN-LAST:event_radDapan1ActionPerformed

    private void radDapan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDapan2ActionPerformed
        hienthi.setValueAt("B", stt, 1);
    }//GEN-LAST:event_radDapan2ActionPerformed

    private void radDapan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDapan3ActionPerformed
        hienthi.setValueAt("C", stt, 1);
    }//GEN-LAST:event_radDapan3ActionPerformed

    private void radDapan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDapan4ActionPerformed
        hienthi.setValueAt("D", stt, 1);
    }//GEN-LAST:event_radDapan4ActionPerformed

    private void btnNopbaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNopbaiActionPerformed
        chamDiem();
        this.dispose();
    }//GEN-LAST:event_btnNopbaiActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCautiep;
    private javax.swing.JButton btnCautruoc;
    private javax.swing.JButton btnNopbai;
    private javax.swing.ButtonGroup chonDapan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lblCau;
    private javax.swing.JLabel lblDebai;
    private javax.swing.JLabel lblMade;
    private javax.swing.JLabel lblMonhoc;
    private javax.swing.JLabel lblSocau;
    private javax.swing.JRadioButton radDapan1;
    private javax.swing.JRadioButton radDapan2;
    private javax.swing.JRadioButton radDapan3;
    private javax.swing.JRadioButton radDapan4;
    private javax.swing.JTable tableDscauhoi;
    private javax.swing.JProgressBar timebar;
    // End of variables declaration//GEN-END:variables
}

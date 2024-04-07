/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import view.View_login;


/**
 *
 * @author ivwi3
 */
public class Controller_login implements ActionListener{

    private View_login view;

    public Controller_login(View_login view) {
        this.view = view;
    }

    public Controller_login() {
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonSource = (JButton)e.getSource();
        String action = buttonSource.getText();
        if("Đăng Nhập".equals(action)) {
            view.dangNhap();
        }
    }
    
}

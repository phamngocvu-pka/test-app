/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydiemthidaihoc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mycompany.quanlydiemthidaihoc.action.CheckLogin;
import com.mycompany.quanlydiemthidaihoc.entity.User;
import com.mycompany.quanlydiemthidaihoc.view.LoginView;
import com.mycompany.quanlydiemthidaihoc.view.ResidentView;


/**
 *
 * @author PC
 */
public class LoginController 
{
    private CheckLogin checkLogin;
    private LoginView loginView;
    
    public LoginController(LoginView view) 
    {
        this.loginView = view;
        this.checkLogin = new CheckLogin();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() 
    {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            User user = loginView.getUser();
            if (checkLogin.checkUser(user)) 
            {
                StudentController studentController = new StudentController(new ResidentView());
                studentController.showManagerView();
                loginView.setVisible(false);
            } 
            else 
            {
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }
}

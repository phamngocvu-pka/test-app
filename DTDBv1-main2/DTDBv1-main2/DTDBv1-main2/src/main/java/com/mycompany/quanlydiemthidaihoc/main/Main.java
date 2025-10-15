/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlydiemthidaihoc.main;

import java.awt.EventQueue;

import com.mycompany.quanlydiemthidaihoc.controller.LoginController;
import com.mycompany.quanlydiemthidaihoc.view.LoginView;

/**
 *
 * @author PC
 */
public class Main 
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginController controller = new LoginController(new LoginView());
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}

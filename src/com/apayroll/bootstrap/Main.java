/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.bootstrap;

import com.apayroll.views.main.MainFrame;
import com.apayroll.views.splashscreen.SplashScreenFrame;
import java.sql.SQLException;

/**
 *
 * @author sly
 */
public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException{
        MainFrame main = new MainFrame();
        SplashScreenFrame sc = new SplashScreenFrame();
        
        sc.setVisible(true);
        for(int i = 0; i < 100; i++) {
            Thread.sleep(40);
        }
        sc.setVisible(false);
        main.setVisible(true);
        sc.dispose();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.bootstrap;

import com.apayroll.views.main.MainFrame;
import java.sql.SQLException;

/**
 *
 * @author sly
 */
public class Main {
    
    
    public static void main(String[] args) throws SQLException{
        MainFrame main = new MainFrame();
        main.setVisible(true);
    }
}

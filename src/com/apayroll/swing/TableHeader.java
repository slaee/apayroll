package com.apayroll.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sly
 */
public class TableHeader extends JLabel{
    public TableHeader(String text){
        super(text);
        setOpaque(false);
        setBackground(new Color(255, 255, 255));
        setFont(new Font("freesans", 1, 13));
        setForeground(new Color(102,102,102));
        setBorder(new EmptyBorder(10,5,10,5));
    }
    
    @Override
    protected void paintComponent(Graphics gr){
        super.paintComponent(gr);
        gr.setColor(new Color(255, 255, 255));
        gr.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.swing;

import com.apayroll.models.components.ButtonType;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author sly
 */
public class TableButton extends JButton{

    /**
     * @return the buttonType
     */
    public ButtonType getButtonType() {
        return buttonType;
    }

    /**
     * @param buttonType the buttonType to set
     */
    public void setButtonType(ButtonType buttonType) {
        this.buttonType = buttonType;
        repaint();
    }
    
    private ButtonType buttonType;
    
    public TableButton(ButtonType buttonType){
        this.buttonType = buttonType;
        if(null == buttonType){
            setText("");
        } else switch (buttonType) {
            case DELETE:
                setText("Delete");
                break;
            case EDIT:
                setText("Edit");
                break;
            default:
                setText("View");
                break;
        }
    }
    
    public TableButton(){
        
    }
   
    
    @Override
    protected void paintComponent(Graphics gr){
        Graphics2D gr2D = (Graphics2D) gr;
        gr2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp;
        if(null == buttonType){
            gp = new GradientPaint(0, 0, Color.BLUE, 0, getHeight(), Color.BLUE);
        } else switch (buttonType) {
            case DELETE:
                gp = new GradientPaint(0, 0, Color.RED, 0, getHeight(), Color.RED);
                break;
            case EDIT:
                gp = new GradientPaint(0, 0, Color.GREEN, 0, getHeight(), Color.GREEN);
                break;
            default:
                gp = new GradientPaint(0, 0, Color.BLUE, 0, getHeight(), Color.BLUE);
                break;
        }
        gr2D.setPaint(gp);
        gr2D.fillRoundRect(0,0, getWidth(), getHeight(), 0, 0);
        super.paintComponent(gr);
        
    }
}

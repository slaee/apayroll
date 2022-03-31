
package com.apayroll.components;

import com.apayroll.swing.renderers.ShadowRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Card extends javax.swing.JPanel {

    /**
     * @return the borderRadius
     */
    public int getBorderRadius() {
        return borderRadius;
    }

    /**
     * @param borderRadius the borderRadius to set
     */
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    /**
     * @return the shadowSize
     */
    public int getShadowSize() {
        return shadowSize;
    }

    /**
     * @param shadowSize the shadowSize to set
     */
    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    /**
     * @return the shadowOpacity
     */
    public float getShadowOpacity() {
        return shadowOpacity;
    }

    /**
     * @param shadowOpacity the shadowOpacity to set
     */
    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    /**
     * @return the shadowColor
     */
    public Color getShadowColor() {
        return shadowColor;
    }

    /**
     * @param shadowColor the shadowColor to set
     */
    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    /**
     * @return the shadowType
     */
    public ShadowType getShadowType() {
        return shadowType;
    }

    /**
     * @param shadowType the shadowType to set
     */
    public void setShadowType(ShadowType shadowType) {
        this.shadowType = shadowType;
    }
    
    private int shadowSize = 6;
    private float shadowOpacity = 0.5f;
    private Color shadowColor = Color.BLACK;
    private ShadowType shadowType;
    private int borderRadius = 10;
    
    public static enum ShadowType{
        CENTER, TOP_RIGHT, TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT, BOTTOM, TOP
    }

    public Card() {
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics gr){
        Graphics2D gr2D = (Graphics2D) gr;
        int size = getShadowSize()*2;
        int x;
        int y;
        int width = getWidth() - size;
        int height = getHeight() - size;
        
        if(null == shadowType){
            x = 0;
            y = 0;
        } else switch (shadowType) {
            case TOP:
                x = shadowSize;
                y = size;
                break;
            case BOTTOM:
                x = shadowSize;
                y = 0;
                break;
            case TOP_LEFT:
                x = size;
                y = size;
                break;
            case TOP_RIGHT:
                x = 0;
                y = size;
                break;
            case BOTTOM_LEFT:
                x = size;
                y = 0;
                break;
            case BOTTOM_RIGHT:
                y = 0;
                x = 0;
                break;
            default:
                x = shadowSize;
                y = shadowSize;
                break;
        }
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D gr2 = img.createGraphics();
        gr2.setBackground(getBackground());
        gr2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        gr2.setColor(getBackground());
        gr2.fillRoundRect(0,0, width, height, getBorderRadius(), getBorderRadius());     
        
        ShadowRenderer render = new ShadowRenderer(getShadowSize(), getShadowOpacity(), getShadowColor());
        gr2D.drawImage(render.createShadow(img), 0, 0, null);
        gr2D.drawImage(img, x, y, null);
        super.paintComponent(gr);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

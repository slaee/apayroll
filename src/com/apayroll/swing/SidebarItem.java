
package com.apayroll.swing;

import com.apayroll.models.components.SidebarModel;
import com.apayroll.swing.renderers.ShadowRenderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class SidebarItem extends javax.swing.JPanel {
    public boolean selected;
    
    public SidebarItem(SidebarModel data) {
        initComponents();
        setOpaque(false);
        sidebarItemName.setForeground(Color.decode("#67748E"));
        if(null == data.getType()){
            sidebarItemName.setText(" ");
        } else switch (data.getType()) {
            case SIDEBAR:
                sidebarItemIcon.setIcon(data.toIcon());
                sidebarItemName.setText(data.getName());
                sidebarItemName.setFont(new Font("freesans", 0, 15));
                break;
            case TITLE:
                sidebarItemIcon.setText(data.getName());
                sidebarItemIcon.setFont(new Font("freesans", 1, 16));
                sidebarItemName.setVisible(false);
                break;
            default:
                sidebarItemName.setText(" ");
                break;
        }
    }
    
    public void setSelected(boolean selected){
        this.selected = selected;
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new com.apayroll.components.Card();
        sidebarItemIcon = new javax.swing.JLabel();
        sidebarItemName = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(276, 43));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        card1.setBackground(new java.awt.Color(255, 255, 255));
        card1.setPreferredSize(new java.awt.Dimension(50, 50));
        card1.setShadowColor(new java.awt.Color(204, 204, 204));
        card1.setShadowOpacity(0.2F);
        card1.setShadowSize(3);
        card1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card1.add(sidebarItemIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 20, 20));

        add(card1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 8, 40, 40));

        sidebarItemName.setBackground(new java.awt.Color(103, 116, 142));
        sidebarItemName.setFont(new java.awt.Font("FreeSans", 0, 14)); // NOI18N
        sidebarItemName.setText("Sidebar item name");
        add(sidebarItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 150, 50));
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void paintComponent(Graphics gr){
        int shadowSize = 5;
        float shadowOpacity = 0.2f;
        if(selected){
            Graphics2D gr2D = (Graphics2D) gr;
            int size = shadowSize*2;
            int x = shadowSize;
            int y = 0;
            int width = getWidth() - size;
            int height = getHeight() - size;
            
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
            Graphics2D gr2 = img.createGraphics();
            gr2.setBackground(getBackground());
            gr2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
            gr2.setColor(Color.WHITE);
            gr2.fillRoundRect(0,0, width, height, 15, 15);     
        
            ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, new Color(204,204,204));
            gr2D.drawImage(render.createShadow(img), 0, 0, null);
            gr2D.drawImage(img, x, y, null);
            card1.setBackground(new Color(56,182,255));
            sidebarItemName.setFont(new Font("freesans", 1, 15));
            sidebarItemName.setForeground(Color.decode("#344767"));
        }
       
        super.paintComponent(gr);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.apayroll.components.Card card1;
    private javax.swing.JLabel sidebarItemIcon;
    private javax.swing.JLabel sidebarItemName;
    // End of variables declaration//GEN-END:variables
}

package com.apayroll.components;

import com.apayroll.models.components.SidebarModel;
import com.apayroll.views.main.MainFrame;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sidebar extends javax.swing.JPanel {
    public Sidebar() {
        initComponents();
        setOpaque(false);
        listSidebarItem2.setOpaque(false);
        init();
    }
    
    private void init(){
        listSidebarItem2.addItem(new SidebarModel("dashboard", "Dashboard", SidebarModel.SidebarType.SIDEBAR));
        listSidebarItem2.addItem(new SidebarModel("dashboard", "Employees", SidebarModel.SidebarType.SIDEBAR));
        listSidebarItem2.addItem(new SidebarModel("dashboard", "Payroll", SidebarModel.SidebarType.SIDEBAR));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        listSidebarItem2 = new com.apayroll.swing.ListSidebarItem<>();
        graphicalCard1 = new com.apayroll.components.GraphicalCard();

        setPreferredSize(new java.awt.Dimension(276, 768));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMoving.setFont(new java.awt.Font("FreeSans", 0, 24)); // NOI18N
        panelMoving.setText("PAYROLL");
        add(panelMoving, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 0, 173, 108));

        jLabel2.setFont(new java.awt.Font("FreeSans", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(56, 182, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("A");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 45, 108));

        add(listSidebarItem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 114, 190, 180));
        add(graphicalCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 320, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics gr){
        Graphics2D gr2D = (Graphics2D) gr;
        gr2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#F8F9FA"), 0, getHeight(), Color.decode("#F8F9FA"));
        gr2D.setPaint(gp);
        gr2D.fillRoundRect(0,0, getWidth(), getHeight(), 0, 0);
        super.paintChildren(gr);
    }
    
    private int x;
    private int y;
    
    public void initPressed(JFrame frame){
        listSidebarItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me){
                if(SwingUtilities.isLeftMouseButton(me)){
                    MainFrame.setTabbedPane(listSidebarItem2.getSelectedIndex());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.apayroll.components.GraphicalCard graphicalCard1;
    private javax.swing.JLabel jLabel2;
    private com.apayroll.swing.ListSidebarItem<String> listSidebarItem2;
    private javax.swing.JLabel panelMoving;
    // End of variables declaration//GEN-END:variables
}

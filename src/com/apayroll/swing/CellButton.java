
package com.apayroll.swing;

import com.apayroll.models.components.ButtonType;

public class CellButton extends javax.swing.JPanel {

    public CellButton(ButtonType buttonType) {
        initComponents();
        tableButton1.setButtonType(buttonType);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableButton1 = new com.apayroll.swing.TableButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableButton1.setBackground(new java.awt.Color(255, 51, 51));
        tableButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tableButton1.setForeground(new java.awt.Color(255, 255, 255));
        tableButton1.setText("button");
        tableButton1.setFont(new java.awt.Font("FreeSans", 1, 14)); // NOI18N
        add(tableButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3, 80, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.apayroll.swing.TableButton tableButton1;
    // End of variables declaration//GEN-END:variables
}

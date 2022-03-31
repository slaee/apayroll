
package com.apayroll.swing;

import com.apayroll.models.components.ButtonType;

public class CellButton extends javax.swing.JPanel {

    public CellButton(ButtonType buttonType) {
        initComponents();
        button.setButtonType(buttonType);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new com.apayroll.swing.TableButton();

        button.setText("button");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.apayroll.swing.TableButton button;
    // End of variables declaration//GEN-END:variables
}

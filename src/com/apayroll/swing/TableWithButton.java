/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.swing;

import com.apayroll.models.components.ButtonType;
//import com.apayroll.swing.renderers.ButtonEditor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sly
 */
public class TableWithButton extends JTable{
    public TableWithButton() {
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        setGridColor(new Color(230, 230, 230));
//        setOpaque(false);
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int index, int index1){
                TableHeader header = new TableHeader(o + "");
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int row, int col){
                if(col != 5){
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, row, col);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if(selected){
                        com.setForeground(new Color(15, 89, 140));
                    } else {
                        com.setForeground(new Color(102, 102, 102));
                    }
                    return com;
                } else {
                    ButtonType type;
                    
                    if(o instanceof String){
                        type = (ButtonType) ButtonType.DELETE;
                    } else {
                        type = (ButtonType) o;
                    }
                    
                    CellButton cell = new CellButton(type);
                    return cell;
                }
            }
        });
    }
    
    
    public void setCellNotEditorColumn(String columnName){
//        getColumn(columnName).setCellEditor(new ButtonEditor(new JTextField()));
        getColumn(columnName).setCellEditor(new DefaultCellEditor(new JTextField()) {
            protected TableButton btn = new TableButton();
            private Boolean clicked;
            private String selectedData;

            {
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clicked = true;
                        selectedData = (String) getCellEditorValue();
                        stopCellEditing();
                        ((DefaultTableModel) getModel()).removeRow(getSelectedRow());
                    }
                });
            }
            
            @Override
            public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
                selectedData = (String) table.getValueAt(row, 0);
                btn.setButtonType(ButtonType.DELETE);
                clicked = true;
                return btn;
            }
            
            @Override
            public Object getCellEditorValue() {
                if(clicked) {
                    JOptionPane.showMessageDialog(btn, "Deleted "+ selectedData);
                }
                clicked = false;
                return selectedData;
            }
    
            @Override
            public boolean stopCellEditing() {
            //SET CLICKED TO FALSE FIRST
                clicked = false;
                return super.stopCellEditing();
            }

            @Override
            protected void fireEditingStopped() {
                // TODO Auto-generated method stub
                super.fireEditingStopped();
            }
        });
    }
    
    public void addRow(Object[] row){
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
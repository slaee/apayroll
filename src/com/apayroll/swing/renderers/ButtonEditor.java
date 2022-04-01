package com.apayroll.swing.renderers;

import com.apayroll.models.components.ButtonType;
import com.apayroll.swing.TableButton;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ButtonEditor extends DefaultCellEditor{
    protected TableButton btn;
    private Boolean clicked;
    private static String selectedData;

    public ButtonEditor(JTextField txt) {
        super(txt);
        if("DELETE".equals(txt.getText())){
            btn= new TableButton();
        } else {
            btn = new TableButton();
        }
        
        btn.setOpaque(true);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
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
    
    public static String getSelectedData(){
        return selectedData;
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
}

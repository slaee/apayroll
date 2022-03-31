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
    private String lbl;
    private Boolean clicked;
    private static String selectedData;

    public ButtonEditor(JTextField txt) {
        super(txt);
        if("DELETE".equals(txt.getText())){
            btn= new TableButton(ButtonType.DELETE);
        } else {
            btn = new TableButton(ButtonType.EDIT);
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
        lbl = (obj==null) ? "" : obj.toString();
        btn.setText(lbl);
        selectedData = (String) table.getValueAt(row, 0);
        clicked = true;
        return btn;
    }
    
    public static String getSelectedData(){
        return selectedData;
    }
    
    @Override
    public Object getCellEditorValue() {
        if(clicked)
        {
            JOptionPane.showMessageDialog(btn, lbl+" Clicked");
        }
        clicked = false;
        return new String(lbl);
    }
    
    @Override
    public boolean stopCellEditing() {

         //SET CLICKED TO FALSE FIRST
        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        // TODO Auto-generated method stub
        super.fireEditingStopped();
    }
}

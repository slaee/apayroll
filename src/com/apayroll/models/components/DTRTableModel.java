/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.models.components;

import com.apayroll.models.Employee;
import com.apayroll.models.EmployeeDTR;
import com.apayroll.models.EmployeeRoster;
import com.apayroll.models.EmployeeType;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sly
 */
public class DTRTableModel extends AbstractTableModel{
    private final String columnNames[] = { "Employee ID",  "Name", "Time in", "Time out"};
    private ArrayList<EmployeeDTR> dtrList;
    
    public DTRTableModel(ArrayList<EmployeeDTR> dtrList){
        this.dtrList = dtrList;
    }
    
    @Override
    public int getRowCount() {
        return dtrList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object data = null;
        switch(col){
            case 0:
                data = dtrList.get(row).getId();
                break;
            case 1:
                data = dtrList.get(row).getFullName();
                break;
            case 2:
                data = dtrList.get(row).getTimeIn();
                break;
            case 3:
                data = dtrList.get(row).getTimeOut();
                break;
        }
        return data;
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return !(col == 0 || col == 4);
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Class getColumnClass(int col){
        switch(col){
            case 0:
                return Long.class;
            case 1:
            case 2:
            case 3:
                return String.class;
            case 4:
                return EmployeeType.class;
            case 5:
                return ButtonType.class;
        }
        return null;
    }
    
    // Update Employee
    @Override
    public void setValueAt(Object value, int row, int col){
        EmployeeDTR e = dtrList.get(row);
        switch(col){
            case 1:
                e.setFirstName((String) value);
                break;
              
            case 2:
                e.setMiddleName((String) value);
                break;
            case 3:
                e.setLastName((String) value);
                break;
        }
        dtrList.set(row, e);
        EmployeeRoster er = new EmployeeRoster();
        er.updateEmployee(e);
        fireTableCellUpdated(row, col);
    }
    
    // Add Employee
    public boolean addRow(EmployeeDTR employee){
        EmployeeRoster er = new EmployeeRoster();
        if(er.addEmployee(employee)){
            employee.setId(er.getLastInsertId());
            dtrList.add(employee);
            fireTableDataChanged();
            return true;
        }
        return false;
    }
}

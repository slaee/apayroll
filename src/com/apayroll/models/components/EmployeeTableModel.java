/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.models.components;

import com.apayroll.models.Employee;
import com.apayroll.models.EmployeeRoster;
import com.apayroll.models.EmployeeType;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sly
 */
public class EmployeeTableModel extends AbstractTableModel{
    private String columnNames[] = {"Employee ID", "First Name", "Middle Name", "Last Name", "Type", "Action"};
    private ArrayList<Employee> employeeList;
    
    public EmployeeTableModel(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }
    
    @Override
    public int getRowCount() {
        return employeeList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object data = null;
        switch(columnIndex){
            case 0:
                data = employeeList.get(rowIndex).getId();
                break;
            case 1:
                data = employeeList.get(rowIndex).getFirstName();
                break;
            case 2:
                data = employeeList.get(rowIndex).getMiddleName();
                break;
            case 3:
                data = employeeList.get(rowIndex).getLastName();
                break;
            case 4:
                data = employeeList.get(rowIndex).getType();
                break;
            case 5:
                data = ButtonType.DELETE;
                break;
        }
        return data;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
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

    public boolean removeRow(long id, int row) {
        EmployeeRoster re = new EmployeeRoster();
        fireTableRowsDeleted(row, row);
        return re.removeEmployee(id);
    }
}

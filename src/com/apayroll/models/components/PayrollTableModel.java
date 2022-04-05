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
public class PayrollTableModel extends AbstractTableModel{
    private final String columnNames[]= { "Employee ID", "Name", "Gross Pay", "Net Pay" };
    private ArrayList<Employee> employeeList;
            
    public PayrollTableModel(ArrayList<Employee> employeeList){
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
    public Object getValueAt(int row, int col) {
        Object data = null;
        switch(col){
            case 0:
                data = employeeList.get(row).getId();
                break;
            case 1:
                data = employeeList.get(row).getFullName();
                break;
            case 2:
                data = employeeList.get(row).getFirstName();
                break;
            case 3:
                data = employeeList.get(row).getMiddleName();
                break;
            case 4:
                data = employeeList.get(row).getLastName();
                break;
            case 5:
                data = employeeList.get(row).getType();
                break;
            case 6:
                data = ButtonType.DELETE;
                break;
        }
        return data;
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return !(col == 0 || col == 5);
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
            case 4:
                return String.class;
            case 5:
                return EmployeeType.class;
            case 6:
                return ButtonType.class;
        }
        return null;
    }
    
    // Update Employee
    @Override
    public void setValueAt(Object value, int row, int col){
        Employee e = employeeList.get(row);
        switch(col){
            case 1:
                e.setRfid((String) value);
                break;
            case 2:
                e.setFirstName((String) value);
                break;
            case 3:
                e.setMiddleName((String) value);
                break;
            case 4:
                e.setLastName((String) value);
                break;
        }
        employeeList.set(row, e);
        EmployeeRoster er = new EmployeeRoster();
        er.updateEmployee(e);
        fireTableCellUpdated(row, col);
    }
    
    // Add Employee
    public boolean addRow(Employee employee){
        EmployeeRoster er = new EmployeeRoster();
        if(er.addEmployee(employee)){
            employee.setId(er.getLastInsertId());
            employeeList.add(employee);
            fireTableDataChanged();
            return true;
        }
        return false;
    }
    
    // Remove Employee
    public boolean removeRow(long id, int index) {
        EmployeeRoster er = new EmployeeRoster();
        boolean isRemoved = false;
        if(er.removeEmployee(id))
            isRemoved = true;
        
        employeeList.remove(index);
        fireTableRowsDeleted(index, index);
        return isRemoved;
    }
}

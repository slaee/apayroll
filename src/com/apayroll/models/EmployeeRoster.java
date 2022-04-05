/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.models;

import com.apayroll.libcore.Database;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sly
 */
public class EmployeeRoster {
    Database db;
    static ArrayList<Employee> employeeList;
    static ArrayList<Employee> DTR_Record;
    
    public EmployeeRoster(){
        db = new Database();
    }
    
    public boolean addEmployee(Employee emp){
        try {
            db.query("INSERT INTO dbb_employee (firstName, middleName, lastName, employeeType) VALUES (?, ? ,?, ?)", true);
            db.bind(1, emp.getFirstName());
            db.bind(2, emp.getMiddleName());
            db.bind(3, emp.getLastName());
            db.bind(4, emp.getType().toString());
            db.execute();
            if(!doInsertRfid(emp.getRfid())){
                db.rollBack();
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean timeIn(EmployeeDTR dtr){
        try {
            db.query("INSERT INTO dbb_dtr_record (employeeId, timeIn) VALUES (?, ?)", true);
            db.bind(1, dtr.getId());
            db.bind(2, dtr.getTimeIn());
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean timeOut(EmployeeDTR dtr){
        try {
            db.query("INSERT INTO dbb_dtr_record (employeeId, timeOut) VALUES (?, ?)", true);
            db.bind(1, dtr.getId());
            db.bind(2, dtr.getTimeOut());
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public Long getEmployeeIdByRFID(String rfid){
        try{
            db.query("SElECT user_id FROM dbb_rfid WHERE = ?");
            db.bind(1, rfid);
            db.execute();
            ResultSet res = db.getResultSet();
            if(res.next()){
                return res.getLong(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1L;
    }
    
    private boolean doInsertRfid(String rfid){
        try {
            db.query("INSERT INTO dbb_rfid (user_id, user_rfid_number) VALUES (?, ?)", true);
            System.out.println(getLastInsertId());
            db.bind(1, getLastInsertId());
            db.bind(2, rfid);
            db.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRoster.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public long getLastInsertId(){
        return db.getLastInsertId();
    }

    public boolean removeEmployee(long id){
        try {
            db.query("DELETE FROM dbb_employee WHERE id = ?", true);
            db.bind(1, id);
            db.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmployee(Employee emp){
        try {
            db.query("UPDATE dbb_employee SET firstName = ?, middleName = ?, lastName = ?, employeeType = ? WHERE id = ?", true);
            db.bind(1, emp.getFirstName());
            db.bind(2, emp.getMiddleName());
            db.bind(3, emp.getLastName());
            db.bind(4, emp.getType().toString());
            db.bind(5, emp.getId());
            if(db.execute()){
                db.query("UPDATE dbb_rfid SET user_rfid_number = ? WHERE user_id = ?", true);
                db.bind(1, emp.getRfid());
                db.bind(2, emp.getId());
                db.execute();
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void updateList(){
        employeeList = new ArrayList();
        try {
            db.query("SELECT e.*, r.* FROM dbb_employee e LEFT JOIN dbb_rfid r ON r.user_id = e.id");
            db.execute();
            ResultSet res = db.getResultSet();
            while(res.next()){
                if(EmployeeType.valueOf(res.getString("employeeType")) == EmployeeType.HOURLY_EMPLOYEE){
                    HourlyEmployee he = new HourlyEmployee();
                    he.setId(res.getLong("e.id"));
                    he.setRfid(res.getString("r.user_rfid_number"));
                    he.setFirstName(res.getString("firstName"));
                    he.setMiddleName(res.getString("middleName"));
                    he.setLastName(res.getString("lastName"));
                    he.setType(EmployeeType.HOURLY_EMPLOYEE);
                    employeeList.add(he);
                } else {
                    RegularEmployee re = new RegularEmployee();
                    re.setId(res.getLong("e.id"));
                    re.setRfid(res.getString("r.user_rfid_number"));
                    re.setFirstName(res.getString("firstName"));
                    re.setMiddleName(res.getString("middleName"));
                    re.setLastName(res.getString("lastName"));
                    re.setType(EmployeeType.REGULAR_EMPLOYEE);
                    employeeList.add(re);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateDTR_Record(){
        DTR_Record = new ArrayList();
        try{
            db.query("Select employee.*, dtr.* FROM dbb_employee employee LEFT JOIN dbb_dtr_record dtr ON dtr.employeeId = employee.id");
            db.execute();
            ResultSet res = db.getResultSet();
            while(res.next()){
                EmployeeDTR edtr = new EmployeeDTR();
                edtr.setId(res.getLong("employee.dtr"));
                edtr.setFirstName(res.getString("firstName"));
                edtr.setMiddleName(res.getString("middleName"));
                edtr.setLastName(res.getString("lastName"));
                edtr.setTimeIn(res.getTimestamp("timeIn"));
                edtr.setTimeOut(res.getTimestamp("timeout"));
                DTR_Record.add(edtr);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public int size(){
        return employeeList.size();
    }
    
    public ArrayList<Employee> getEmployeeList(){
        return employeeList;
    }
}
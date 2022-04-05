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
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sly
 */
public class EmployeeRoster {
    Database db;
    static ArrayList<Employee> employeeList;
    static ArrayList<EmployeeDTR> DTR_Record;
    
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
    
    public boolean work(String rfid){
        long id = getEmployeeIdByRFID(rfid);
        // Select the dtr record in the database
        try {
            db.query("SELECT * FROM dbb_dtr_record WHERE employeeId = ? AND timeOut IS NULL", true);
            db.bind(1, id);
            db.execute();
            ResultSet rs = db.getResultSet();
            if(rs.next()){
                // Update the dtr record
                db.query("UPDATE dbb_dtr_record SET timeOut = ? WHERE employeeId = ?", true);
                db.bind(1, new Timestamp(new Date().getTime()));
                db.bind(2, id);
                db.execute();
            } else {
                // Insert the dtr record
                db.query("INSERT INTO dbb_dtr_record (employeeId, timeIn) VALUES (?, ?)", true);
                db.bind(1, id);
                db.bind(2, new Timestamp(new Date().getTime()));
                db.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Long getEmployeeIdByRFID(String rfid){
        try{
            db.query("SELECT user_id FROM dbb_rfid WHERE user_rfid_number= ?");
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
    
    // get employee by id
    public EmployeeDTR getEmployeeDTRByRFID(String rfid){
        long id = getEmployeeIdByRFID(rfid);
        try {
            db.query("SELECT * FROM dbb_dtr_record dtr JOIN dbb_employee e ON dtr.employeeId = e.id WHERE dtr.employeeId = ? ORDER BY dtr.id DESC LIMIT 0, 1", true);
            db.bind(1, id);
            db.execute();
            ResultSet rs = db.getResultSet();
            if(rs.next()){
                EmployeeDTR emp = new EmployeeDTR();
                emp.setId(rs.getLong("e.id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setMiddleName(rs.getString("middleName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setTimeIn(rs.getTimestamp("timeIn"));
                emp.setTimeOut(rs.getTimestamp("timeOut"));
                return (EmployeeDTR) emp;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
            db.query("Select employee.*, dtr.* FROM dbb_employee employee JOIN dbb_dtr_record dtr ON dtr.employeeId = employee.id ORDER BY employee.id");
            db.execute();
            ResultSet res = db.getResultSet();
            while(res.next()){
                EmployeeDTR edtr = new EmployeeDTR();
                edtr.setId(res.getLong("employee.id"));
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
    
    public ArrayList<EmployeeDTR> getDtrRecordList(){
        return DTR_Record;
    }
}
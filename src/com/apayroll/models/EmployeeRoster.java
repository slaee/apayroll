/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.models;

import com.apayroll.libcore.Database;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sly
 */
public class EmployeeRoster {
    Database db;
    Vector<Employee> employee;
    
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
            if(!doInsertRfid(emp.getId())){
                db.rollBack();
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
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

    public boolean removeEmployee(Employee emp){
        try {
            db.query("DELETE FROM dbb_employee WHERE id = ?", true);
            db.bind(1, emp.getId());
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
            db.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
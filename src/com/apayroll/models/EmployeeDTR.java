/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.models;

import java.sql.Timestamp;

/**
 *
 * @author sly
 */
public class EmployeeDTR extends Employee{

    /**
     * @return the timeIn
     */
    public Timestamp getTimeIn() {
        return timeIn;
    }

    /**
     * @param timeIn the timeIn to set
     */
    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    /**
     * @return the timeOut
     */
    public Timestamp getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut the timeOut to set
     */
    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }
    
    private Timestamp timeIn;
    private Timestamp timeOut;
    
    public EmployeeDTR(String id, String firstName, String middleName, String lastName, EmployeeType type, Timestamp timeIn, Timestamp timeOut){
        super(id, firstName, middleName, lastName, type);
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }
    
    public EmployeeDTR(){
        super();
    }
    
    @Override
    public void computeSalary(){}
}

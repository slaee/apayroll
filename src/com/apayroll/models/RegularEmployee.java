/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apayroll.models;

/**
 *
 * @author sly
 */
public class RegularEmployee extends Employee{

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @return the regularSalary
     */
    public double getRegularSalary() {
        return regularSalary;
    }

    /**
     * @param regularSalary the regularSalary to set
     */
    public void setRegularSalary(double regularSalary) {
        this.regularSalary = regularSalary;
    }

    /**
     * @return the absence
     */
    public int getAbsence() {
        return absence;
    }

    /**
     * @param absence the absence to set
     */
    public void setAbsence(int absence) {
        this.absence = absence;
    }
    private double salary;
    private double regularSalary;
    private int absence;
    
    public RegularEmployee(Long id, String rfid, String firstName, String middleName, String lastName, EmployeeType type, double salary, double regularSalary, int absence){
        super(id, rfid, firstName, middleName, lastName, type);
        this.salary = salary;
        this.regularSalary = regularSalary;
        this.absence = absence;
    }
    
    public RegularEmployee(Long id, String rfid, String firstName, String middleName, String lastName, EmployeeType type){
        super(id, rfid, firstName, middleName, lastName, type);
        regularSalary = 0;
        salary = 0;
        absence = 0;
    }
    
    public RegularEmployee(){
        super();
    }
  
    @Override
    public void computeSalary() {
        
    }
}


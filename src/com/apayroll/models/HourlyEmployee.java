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
public class HourlyEmployee extends Employee{

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
     * @return the workedHours
     */
    public float getWorkedHours() {
        return workedHours;
    }

    /**
     * @param workedHours the workedHours to set
     */
    public void setWorkedHours(float workedHours) {
        this.workedHours = workedHours;
    }

    /**
     * @return the dailyRate
     */
    public double getDailyRate() {
        return dailyRate;
    }

    /**
     * @param dailyRate the dailyRate to set
     */
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }
    
    private double salary;
    private float workedHours;
    private double dailyRate;
    
    private final int REGULAR_WORKING_HOURS = 40;
    private final float OT_PAY_RATE = 1.25f;
    
    public HourlyEmployee(Long id, String rfid, String firstName, String middleName, 
        String lastName, EmployeeType type, double salary, double dailyRate, float workedHours){
        super(id, rfid, firstName, middleName, lastName, type);
        this.salary = salary;
        this.dailyRate = dailyRate;
        this.workedHours = workedHours;
    }
    
    public HourlyEmployee(Long id, String rfid, String firstName, 
        String middleName, String lastName, EmployeeType type){
        super(id, rfid, firstName, middleName, lastName, type);
        dailyRate = 0;
        workedHours = 0;
        salary = 0;
    }
    
    public HourlyEmployee(){
        super();
    }
  
    @Override
    public void computeSalary() {
        
    }
}

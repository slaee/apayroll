package com.apayroll.models;

public abstract class Employee {

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the type
     */
    public EmployeeType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EmployeeType type) {
        this.type = type;
    }

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private EmployeeType type;
    
    public Employee(String id, String firstName, String middleName, String lastName, EmployeeType type){
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.type = type;
    }
    
    public Employee(){
        
    }
    
    public abstract void computeSalary();
}

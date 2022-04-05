package com.apayroll.models;

public abstract class Employee {

    /**
     * @return the rfid
     */
    public String getRfid() {
        return rfid;
    }

    /**
     * @param rfid the rfid to set
     */
    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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
    
    public String getFullName(){
        return firstName+" "+middleName+" "+lastName;
    }
    
    @Override
    public String toString(){
        return "ID: "+ id
                +"\nRFID: " +rfid
                +"\nFirst Name: "+firstName
               +"\nMiddle Name: "+middleName
               +"\nLast Name: "+lastName;
    }

    private Long id;
    private String rfid;
    private String firstName;
    private String middleName;
    private String lastName;
    private EmployeeType type;
    
    public Employee(Long id, String rfid, String firstName, String middleName, String lastName, EmployeeType type){
        this.id = id;
        this.rfid = rfid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.type = type;
    }
    
    public Employee(){
        
    }
    
    public abstract void computeSalary();
    
}

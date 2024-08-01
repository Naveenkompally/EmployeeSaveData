package com.jtpl.employeedata;


public class Employee {
    private int id;
    private String name;
    private String address;
    private String department;
    private String gender;
    private boolean isFresher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isFresher() {
        return isFresher;
    }

    public void setFresher(boolean fresher) {
        isFresher = fresher;
    }
}
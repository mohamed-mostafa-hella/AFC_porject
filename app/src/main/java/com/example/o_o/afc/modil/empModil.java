package com.example.o_o.afc.modil;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class empModil implements Serializable {
    private String name,nationalId,address,salary,jop,ID;
    private int gender;
    private Date date;

    public empModil() {
    }

    public empModil(String name, String nationalId, String address, int gender, String jop, String salary, Date date,String ID) {
        this.name = name;
        this.nationalId = nationalId;
        this.address = address;
        this.gender = gender;
        this.jop = jop;
        this.salary = salary;
        this.date = date;
        this.ID=ID;
    }

    public empModil(String name, String nationalId, String address, int gender, String jop, String salary, Date date) {
        this.name = name;
        this.nationalId = nationalId;
        this.address = address;
        this.gender = gender;
        this.jop = jop;
        this.salary = salary;
        this.date = date;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getJop() {
        return jop;
    }

    public void setJop(String jop) {
        this.jop = jop;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

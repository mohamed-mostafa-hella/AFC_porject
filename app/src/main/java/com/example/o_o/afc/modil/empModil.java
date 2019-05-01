package com.example.o_o.afc.modil;

import java.util.Date;

public class empModil {
    private String name,nationalId,address;
    private int gender,jop,salary;
    private Date date;

    public empModil() {
    }

    public empModil(String name, String nationalId, String address, int gender, int jop, int salary, Date date) {
        this.name = name;
        this.nationalId = nationalId;
        this.address = address;
        this.gender = gender;
        this.jop = jop;
        this.salary = salary;
        this.date = date;
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

    public int getJop() {
        return jop;
    }

    public void setJop(int jop) {
        this.jop = jop;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

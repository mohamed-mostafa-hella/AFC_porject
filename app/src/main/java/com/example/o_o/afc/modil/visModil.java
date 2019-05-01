package com.example.o_o.afc.modil;

import java.util.Date;

public class visModil {
    private String name;
    private int age,gender,number;
    private Date date;

    public visModil() {
    }

    public visModil(String name, int age, int gender, int number, Date date) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.number = number;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

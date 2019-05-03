package com.example.o_o.afc.modil;

import java.util.Date;

public class memModel {
    private String name,natonalId,jop,address;
    private int gender,ID;
    private Date date;

    public memModel() {
    }

    public memModel(String name, String natonalId, String jop, String address, int gender, Date date) {
        this.name = name;
        this.natonalId = natonalId;
        this.jop = jop;
        this.address = address;
        this.gender = gender;
        this.date = date;
    }

    public memModel(String name, String natonalId, String jop, String address, int gender, int ID, Date date) {
        this.name = name;
        this.natonalId = natonalId;
        this.jop = jop;
        this.address = address;
        this.gender = gender;
        this.ID = ID;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNatonalId() {
        return natonalId;
    }

    public void setNatonalId(String natonalId) {
        this.natonalId = natonalId;
    }

    public String getJop() {
        return jop;
    }

    public void setJop(String jop) {
        this.jop = jop;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

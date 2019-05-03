package com.example.o_o.afc.modil;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class memModel implements Serializable {
    private String name,natonalId,jop,address,ID;
    private int gender;
    private Date date,exdata;

    public memModel() {
    }

    public memModel(String name, String natonalId, String jop, String address, String ID, int gender, Date date, Date exdata) {
        this.name = name;
        this.natonalId = natonalId;
        this.jop = jop;
        this.address = address;
        this.ID = ID;
        this.gender = gender;
        this.date = date;
        this.exdata = exdata;
    }



    public Date getExdata() {
        return exdata;
    }

    public void setExdata(Date exdata) {
        this.exdata = exdata;
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

package com.example.contact_gagandeepkaur_768688_android;

import java.io.Serializable;

public class PersonModel implements Serializable {
    private int id;
    private String fName, lName,Email, address, phone;

    public PersonModel(int id, String fName, String lName,String Email, String phone, String address) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.Email = Email;
        this.phone = phone;
        this.address = address;

    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail(){return Email;}

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}

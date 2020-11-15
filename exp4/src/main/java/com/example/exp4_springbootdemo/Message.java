package com.example.exp4_springbootdemo;


public class Message {
    private int id;
    private String name;
    private String phoneNumber;
    private String mail;
    private String address;
    private String qnumber;

    public Message(int id, String name, String phoneNumber, String mail, String address, String qnumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.address = address;
        this.qnumber = qnumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public String getQnumber() {
        return qnumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setQnumber(String qnumber) {
        this.qnumber = qnumber;
    }
}

package com.example.exp4_springbootdemo;


public class Message {
    private final int id;
    private final String name;
    private final int phoneNumber;
    private final String mail;
    private final String address;
    private final int qnumber;

    public Message(int id, String name, int phoneNumber, String mail, String address, int qnumber) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public int getQnumber() {
        return qnumber;
    }


}

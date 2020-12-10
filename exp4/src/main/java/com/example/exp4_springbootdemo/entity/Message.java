package com.example.exp4_springbootdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Message() {

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

    public void copyMessage(Message src){
        this.setName(src.name);
        this.setPhoneNumber(src.name);
        this.setAddress(src.address);
        this.setMail(src.mail);
        this.setQnumber(src.qnumber);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", qnumber='" + qnumber + '\'' +
                '}';
    }
}

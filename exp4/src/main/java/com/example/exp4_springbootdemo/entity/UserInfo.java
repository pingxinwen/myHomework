package com.example.exp4_springbootdemo.entity;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private String username;
    private List<Message> messages;
    private int number;

    public UserInfo(String username){
        this.username = username;
        this.messages = new ArrayList<>();
        messages.add(new Message(1,"平信文","13000000000",
                "a@a.com","翻斗花园","1796632871"));
        this.number = 1;
    }


    public void addMessage(Message message){
        this.number += 1;
        message.setId(this.number);
        this.messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void deleteMessage(int id) {
        for (int i=0;i<this.messages.size();i++){
            if (this.messages.get(i).getId() == id){
                this.messages.remove(i);
                break;
            }
        }
    }

    public Message findMessage(int id){
        for (int i=0;i<this.messages.size();i++){
            if (this.messages.get(i).getId() == id){
                return this.messages.get(i);
            }
        }
        return null;
    }

}

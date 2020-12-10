package com.example.exp4_springbootdemo.controller;

import com.example.exp4_springbootdemo.dao.MessageRepository;
import com.example.exp4_springbootdemo.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class UserController {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    /*用户主界面*/
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("message",messageRepository.findAll());
        return "user";
    }

    /*添加记录界面*/
    @GetMapping("/create")
    public String create() {
        return "create";
    }

    /*编辑记录界面*/
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        Message message = messageRepository.findById(id).get(0);
        if (message != null){
            model.addAttribute("mes",message);
            return "edit";
        }
        else {
            return "NotFound";
        }
    }
}

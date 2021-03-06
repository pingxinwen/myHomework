package com.example.exp4_springbootdemo.controller;

import com.example.exp4_springbootdemo.dao.MessageRepository;
import com.example.exp4_springbootdemo.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class DataController {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    /*添加数据*/
    @ResponseBody
    @PostMapping("/add")
    public void addMessage(HttpServletRequest request, @RequestBody Message message){
            System.out.println(message);
            this.messageRepository.save(message);
    }

    /*修改数据*/
    @ResponseBody
    @PutMapping("/change")
    public void changeMessage(@RequestBody Message message){
            messageRepository.save(message);
    }


    /*删除数据*/
    @ResponseBody
    @GetMapping("/delete/{id}")
    public boolean deleteMessage(HttpServletResponse response, @PathVariable("id") String id_string)throws IOException {
        int id = Integer.parseInt(id_string);
        if (messageRepository.existsById(id)){
            messageRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    /*查询获取数据*/
    @ResponseBody
    @GetMapping("/find/{id}")
    public List<Message> findMessage(@PathVariable("id") int id) {
        return messageRepository.findById(id);
    }

    /*查询是否存在电话号*/
    @ResponseBody
    @GetMapping("/phoneNumber/{phoneNumber}")
    public boolean findByPhone(@PathVariable String phoneNumber){
        return messageRepository.existsByPhoneNumber(phoneNumber);
    }
}

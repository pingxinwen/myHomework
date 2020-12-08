package com.example.exp4_springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class DataController {

    /*添加数据*/
    @ResponseBody
    @PostMapping("/add")
    public void addMessage(HttpServletRequest request, @RequestBody Message message){
        HttpSession session = request.getSession();
        try {
            UserInfo info = (UserInfo) session.getAttribute("record");
            System.out.println(message);
            info.addMessage(message);
        }
        catch (NullPointerException npe){
            System.out.println("/add:  取得会话为空");
        }
    }

    /*修改数据*/
    @ResponseBody
    @PutMapping("/change")
    public void changeMessage(HttpServletRequest request,@RequestBody Message message){
        HttpSession session = request.getSession();
        try {
            UserInfo info = (UserInfo) session.getAttribute("record");
            Message srcMessage = info.findMessage(message.getId());
            srcMessage.setName(message.getName());
            srcMessage.setPhoneNumber(message.getPhoneNumber());
            srcMessage.setMail(message.getMail());
            srcMessage.setAddress(message.getAddress());
            srcMessage.setQnumber(message.getQnumber());
        }
        catch (NullPointerException npe){
            System.out.println("/add:  取得会话为空");
        }
    }


    /*删除数据*/
    @GetMapping("/delete/{id}")
    public String deleteMessage(HttpServletRequest request, @PathVariable String id){
        HttpSession session =request.getSession();
        UserInfo info = (UserInfo) session.getAttribute("record");
        info.deleteMessage(Integer.parseInt(id));
        return "redirect:/user";
    }
}

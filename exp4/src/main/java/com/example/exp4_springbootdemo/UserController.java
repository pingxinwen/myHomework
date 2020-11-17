package com.example.exp4_springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    /*用户主界面*/
    @GetMapping("/user")
    public String user (HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        UserInfo info = (UserInfo)session.getAttribute("record");
        model.addAttribute("message",info.getMessages());
        return "user";
    }

    /*添加记录界面*/
    @GetMapping("/create")
    public String create(Model model) {
        return "create";
    }

    /*编辑记录界面*/
    @GetMapping("/edit/{id}")
    public String edit(Model model,HttpServletRequest request, @PathVariable int id){
        HttpSession session = request.getSession();
        UserInfo info = (UserInfo)session.getAttribute("record");
        Message message = info.findMessage(id);
        if (message != null){
            model.addAttribute("mes",message);
            return "edit";
        }
        else {
            return "NotFound";
        }
    }
}

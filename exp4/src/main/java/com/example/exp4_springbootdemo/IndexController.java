package com.example.exp4_springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index (){
        return "index";
    }

    @RequestMapping("/login")
    public  String login(HttpServletRequest request,@RequestParam String username, @RequestParam String password){
        HttpSession session = request.getSession();
        System.out.println("login!");
        if(username.equals("user") && password.equals("123456")){
            session.setAttribute("login",true);
            return "user";
        }
        else{
            session.setAttribute("login",false);
            return "index";
        }
    }
}

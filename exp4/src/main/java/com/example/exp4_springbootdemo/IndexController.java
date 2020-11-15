package com.example.exp4_springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String login(Model model,HttpServletRequest request, @RequestParam String username, @RequestParam String password){
        HttpSession session = request.getSession();
        if(username.equals("user") && password.equals("123456")){
            session.setAttribute("login",true);
            session.setAttribute("record",new UserInfo(username));
            return "redirect:/user";
        }
        else{
            session.setAttribute("login",false);
            model.addAttribute("alert","用户名或密码错误");
            return "index";
        }
    }
}

package com.example.exp4_springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user")
    public String user (Model model)throws Exception{
        return "user";
    }

    @GetMapping("/create")
    public String create(Model model)throws Exception{
        return "create";
    }
}

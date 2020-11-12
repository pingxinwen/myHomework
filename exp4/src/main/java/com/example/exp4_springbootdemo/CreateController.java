package com.example.exp4_springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateController {
    @RequestMapping("/create")
    public String create(Model model)throws Exception{
        model.addAttribute("mav","create");
        return "create";
    }
}

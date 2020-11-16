package com.example.exp4_springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping({"/","/index"})
    public String index (Model model, @ModelAttribute(value = "alert",binding = false) String alert){
        if (alert!=null){
            model.addAttribute("alert",alert);
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(RedirectAttributes attributes,HttpServletRequest request, @RequestParam String username, @RequestParam String password){
        HttpSession session = request.getSession();
        if(username.equals("user") && password.equals("123456")){
            session.setAttribute("login",true);
            session.setAttribute("record",new UserInfo(username));
            return "redirect:/user";
        }
        else{
            session.setAttribute("login",false);
            attributes.addFlashAttribute("alert","用户名或密码错误");
            return "redirect:/";
        }
    }
    @RequestMapping("/logout")
    public String logout(RedirectAttributes attributes, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("login")==null||session.getAttribute("login").equals(false)){
            attributes.addFlashAttribute("alert","请登录");
        }
        else{
            session.setAttribute("login",false);
            attributes.addFlashAttribute("alert","退出登录成功");
        }

        return "redirect:/";
    }
}

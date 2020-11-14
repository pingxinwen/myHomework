package com.example.exp4_springbootdemo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DataController {

    @RequestMapping("/add")
    public void addMessage(@RequestBody String message){
        System.out.println(message);
    }
}

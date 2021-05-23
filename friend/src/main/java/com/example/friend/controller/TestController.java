package com.example.friend.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Tag(name = "Test")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }

    @GetMapping("/")
    public String test(@Parameter(name = "arg",schema = @Schema(description = "v1",type = "string",allowableValues = {"1","2"})) String arg) {
        return "测试的是："+arg;
    }

    @GetMapping("/test")
    public String timeTest(@Parameter(name="type",schema = @Schema(allowableValues = {"relay","test"}))String type){
        try{
            if(type.equals("relay")){
                Thread.sleep(10000);
                System.out.println("delay!");
                return "relay completed";
            }
        } catch (InterruptedException e){
            System.out.println(e.toString());
        }
        System.out.println(type);
        return type;
    }
}

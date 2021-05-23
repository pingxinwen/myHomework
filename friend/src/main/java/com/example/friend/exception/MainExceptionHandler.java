package com.example.friend.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<JSONObject> userNotFoundHandler(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","没有此用户");
        return new ResponseEntity<>(jsonObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserHasExistedException.class)
    public ResponseEntity<JSONObject> userHasExistedHandler(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","用户已存在");
        return  new ResponseEntity<>(jsonObject,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotLoginException.class)
    public  ResponseEntity<JSONObject> userNotLoginHandler(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","用户未登录");
        return  new ResponseEntity<>(jsonObject,HttpStatus.UNAUTHORIZED);
    }
}

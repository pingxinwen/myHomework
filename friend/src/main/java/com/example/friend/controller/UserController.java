package com.example.friend.controller;

import com.example.friend.dao.UserRepo;
import com.example.friend.entity.User;
import com.example.friend.entity.View;
import com.example.friend.exception.UserHasExistedException;
import com.example.friend.exception.UserNotFoundException;
import com.example.friend.exception.UserNotLoginException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Tag(name = "User")
public class UserController {
    private UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.publicView.class)
    public User register(@RequestBody User user_new){
        System.out.println(user_new.getUid());
        if (userRepo.findByUsername(user_new.getUsername())!=null){
            throw new UserHasExistedException();
        }
        return userRepo.save(user_new);
    }

    @GetMapping("/register")
    @Operation(summary = "查询用户名是否存在")
    public Boolean checkRegister(@Parameter(required = true,description = "要查询的用户名")String username){
        return userRepo.findByUsername(username)!=null;
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @JsonView(View.publicView.class)
    public User login(HttpServletRequest request, @RequestBody User user_attempt){
        User user = userRepo.findByUsername(user_attempt.getUsername());
        if (user.getPassword().equals(user_attempt.getPassword())){
            request.getSession().setAttribute("uid",user.getUid());
            return user;
        }else {
            return null;
        }
    }

    @PostMapping("/follow")
    @Operation(summary = "添加某个用户好友")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.publicView.class)
    public User followById(HttpServletRequest request,@RequestBody long uid){
        User target = userRepo.findByUid(uid);
        if (target==null){
            throw new UserNotFoundException();
        }
        else {
            //添加进当前用户的关注列表
            User user = userRepo.findByUid((long) request.getSession().getAttribute("uid"));
            if (user == null){
                throw new UserNotLoginException();
            }
            user.getFollows().add(target);
            userRepo.save(user);
            return target;
        }
    }

    @DeleteMapping("/follow")
    @Operation(summary = "删除某个好友")
    @JsonView(View.publicView.class)
    public User deleteById(HttpServletRequest request,@RequestBody long uid){
        User target = userRepo.findByUid(uid);
        if (target==null){
            throw new UserNotFoundException();
        }
        else {
            //添加进当前用户的关注列表
            User user = userRepo.findByUid((long) request.getSession().getAttribute("uid"));
            if (user == null){
                throw new UserNotLoginException();
            }
            user.getFollows().remove(target);
            userRepo.save(user);
            return target;
        }
    }

    @GetMapping("/follow")
    @Operation(summary = "获取某个用户关注列表")
    @JsonView(View.publicView.class)
    public List<User> getFollow(@Parameter(description = "查询用户id")long uid) throws UserNotFoundException{
        return userRepo.findByUid(uid).getFollows();
    }

    @GetMapping("/")
    @Operation(summary = "获取用户信息")
    @JsonView(View.publicView.class)
    public User getUser(@Parameter(description = "用户id")long uid) throws UserNotFoundException{
        return userRepo.findByUid(uid);
    }

    @PutMapping("/")
    @Operation(summary = "修改用户信息")
    @JsonView(View.detailView.class)
    public User postUser(HttpServletRequest request,@RequestBody User user){
        if (request.getSession().getAttribute("uid")==null){
            throw  new UserNotLoginException();
        }
        long uid = (long) request.getSession().getAttribute("uid");
        if(uid == user.getUid()){
            return userRepo.save(user);
        }
        else throw new UserNotFoundException();
    }
}

package com.example.friend.controller;

import com.example.friend.dao.ShareRepo;
import com.example.friend.dao.UserRepo;
import com.example.friend.entity.Share;
import com.example.friend.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "Share")
@RequestMapping("/share")
public class ShareController {
    private UserRepo userRepo;
    private ShareRepo shareRepo;

    @Autowired
    public ShareController(UserRepo userRepo, ShareRepo shareRepo) {
        this.userRepo = userRepo;
        this.shareRepo = shareRepo;
    }

    @GetMapping("/")
    @Operation(description = "返回所有动态")
    public List<Share> getShares(){
        return shareRepo.findAll();
    }

    @GetMapping("/id:")
    public List<Share> getSharesOfUser(@PathVariable long uid) throws UserNotFoundException{
        return userRepo.findByUid(uid).getShares();
    }

    @PostMapping("/")
    @Operation(description = "发布动态")
    public String postShare(HttpServletRequest request, @RequestBody(description = "动态内容") String content) throws UserNotFoundException {
        //在当前用户下保存
        Share share = new Share();
        share.setContent(content);
        share.setUser(userRepo.findByUid((Long) request.getSession().getAttribute("uid")));
        share.setTimestamp(new Timestamp(new Date().getTime()));
        shareRepo.save(share);
        return "Succeed";
    }

}

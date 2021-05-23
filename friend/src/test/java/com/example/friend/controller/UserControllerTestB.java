package com.example.friend.controller;

import com.example.friend.dao.UserRepo;
import com.example.friend.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTestB {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepo userRepo;

    @BeforeEach
    void setUserRepo(){
        System.out.println("Before Each......");
        BDDMockito.given(this.userRepo.findByUid(1))
                .willReturn(new User(1L,"zhang","123456","hello,world!"));
    }

    @Test
    void saveUser() throws Exception{
        this.mockMvc.perform(get("/user")
                .accept(MediaType.APPLICATION_JSON)
                .param("uid","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("zhang"))
                .andDo(MockMvcResultHandlers.print());
    }
}

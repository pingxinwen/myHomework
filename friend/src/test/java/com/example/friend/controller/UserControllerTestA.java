package com.example.friend.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestA {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach......");
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach......");
    }

    @Test
    void getUserInfo() throws Exception {
        this.mockMvc.perform(get("/user/register")
                .accept(MediaType.APPLICATION_JSON)
                .param("username", "zhang"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}

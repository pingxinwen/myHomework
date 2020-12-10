package com.example.exp4_springbootdemo.dao;

import com.example.exp4_springbootdemo.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Integer> {
    List<Message> findById(int id);
    boolean existsByPhoneNumber(String phoneNumber);
}

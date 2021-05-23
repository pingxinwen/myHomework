package com.example.friend.dao;


import com.example.friend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUid(long uid);
}

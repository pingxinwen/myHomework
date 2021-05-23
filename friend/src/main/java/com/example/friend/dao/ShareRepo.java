package com.example.friend.dao;

import com.example.friend.entity.Share;
import com.example.friend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareRepo extends JpaRepository<Share,Long> {
    List<Share> findByUser(User user);
}
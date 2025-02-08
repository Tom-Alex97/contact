package com.example.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contact.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByIsPrivateFalse();  // 仅查询公开留言
}
package com.example.contact.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contact.model.Message;
import com.example.contact.repository.MessageRepository;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*") // 允许跨域请求
public class MessageController {
    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    // ✅ 保存留言
    @PostMapping("/submit")
    public String submitMessage(@RequestBody Message message) {
        System.out.println("📥 接收留言: " + message); // 打印完整留言内容
        System.out.println("🔍 isPrivate 值: " + message.isPrivate());
        repository.save(message);
        return message.isPrivate() ? "私密メッセージが保存されました！" : "公開メッセージが保存されました！";
    }
    // ✅ 获取 **公开留言**（显示在 contact.html）
    @GetMapping("/public")
    public List<Message> getPublicMessages() {
        return repository.findByIsPrivateFalse();
    }

    // ✅ 获取 **所有留言**（仅管理员查看，包含私密留言）
    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return repository.findAll();
    }
}
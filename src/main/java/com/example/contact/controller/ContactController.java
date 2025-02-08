package com.example.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContactPage() {
        System.out.println("✅ 渲染 contact 页面...");
        return "contact"; // ✅ 确保返回的是 "contact"
    }
}
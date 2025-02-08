package com.example.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.contact.repository.MessageRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MessageRepository repository;

    public AdminController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("messages", repository.findAll());
        return "admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/admin";
    }
}
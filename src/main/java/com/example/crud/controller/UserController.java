package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import com.example.crud.service.KafkaProducerService;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private KafkaProducerService kafkaProducerService;
    
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }
    
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        User savedUser = userRepository.save(user);
        kafkaProducerService.sendUserEvent("CREATE", savedUser);
        return "redirect:/users";
    }

    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElseThrow());
        return "users/form";
    }
    
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        User updatedUser = userRepository.save(user);
        kafkaProducerService.sendUserEvent("UPDATE", updatedUser);
        return "redirect:/users";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        kafkaProducerService.sendUserEvent("DELETE", user);
        return "redirect:/users";
    }
}
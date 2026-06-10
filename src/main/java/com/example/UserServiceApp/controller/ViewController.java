package com.example.UserServiceApp.controller;

import com.example.UserServiceApp.DTO.RegistrationDTO;
import com.example.UserServiceApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

//    private final UserService userService;
    /**
     * Displays the Registration Page
     * URL: http://localhost:8081/register
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // We add an empty DTO to the model so Thymeleaf can bind form data to it
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "register"; // Points to src/main/resources/templates/register.html
    }
}

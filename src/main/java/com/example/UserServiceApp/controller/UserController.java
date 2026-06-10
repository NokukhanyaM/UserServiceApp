package com.example.UserServiceApp.controller;

import com.example.UserServiceApp.DTO.RegistrationDTO;
import com.example.UserServiceApp.model.User;
import com.example.UserServiceApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    // We inject the UserService to access the business logic (the register method).
    private final UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegistrationDTO registerRequest) {
//        try {
//            userService.register(registerRequest);
//            // Send a success status back to Angular
//            return ResponseEntity.ok().body("{\"message\": \"Registration successful\"}");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("{\"error\": \"Email already exists!\"}");
//        }
//    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegistrationDTO registerRequest, RedirectAttributes redirectAttributes) {

        try {
            userService.register(registerRequest);

            return "redirect:http://localhost:8081/api/auth/login";

        } catch (Exception e) {
            // Use addFlashAttribute so it survives the redirect back to the register page
            redirectAttributes.addFlashAttribute("error", "Email already exists or invalid data!");
            return "redirect:/api/users/register";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Matches your register.html filename
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok) // Returns 200 OK with the user if found
                .orElse(ResponseEntity.notFound().build()); // Returns 404 if not found
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok) // Returns 200 OK with the user if found
                .orElse(ResponseEntity.notFound().build()); // Returns 404 if not found
    }


}

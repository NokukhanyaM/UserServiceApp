package com.example.UserServiceApp.service;


import com.example.UserServiceApp.DTO.RegistrationDTO;
import com.example.UserServiceApp.model.User;
import com.example.UserServiceApp.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//Tells spring that this class holds BL of our app
@Service
//Performs Constructor Injection to give use the UserRepo
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    /**
     * This method takes the raw data from the registration request,
     * transforms it into a database entity, and saves it.
     */

    public User register(RegistrationDTO registerRequest){

        //Create an Instance of a User
        User user = new User();

        //Map data from the DTO to the Entity
        user.setEmail(registerRequest.getEmail());

        user.setUsername(registerRequest.getUsername());

        // HASH the password before setting it on the entity
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);

        user.setRole(registerRequest.getRole());

        //Save to database
        return userRepo.save(user);
    }


    public Optional<User> findByEmail(String email) {
        // This calls the repo method we created
        return userRepo.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        // This calls the repo method we created
        return userRepo.findByUsername(username);
    }

    // Method to delete a user
    public void deleteUser(long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            // Optional: You can throw a custom exception here
            throw new RuntimeException("User with ID " + id + " not found");
        }
    }

    //Method to update user
    public User updateUser(User user) {
        return userRepo.findById(user.getId())
                .map(existing -> {
                    existing.setUsername(user.getUsername());
                    existing.setEmail(user.getEmail());
                    existing.setRole(user.getRole());
                    // do NOT overwrite password unless explicitly provided
                    return userRepo.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    //Method to get all users
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        System.out.println("Found users: " + users.size());
        return users;

    }

}

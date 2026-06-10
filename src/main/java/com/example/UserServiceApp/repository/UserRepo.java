package com.example.UserServiceApp.repository;


import com.example.UserServiceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

    Optional<User> findById(Long id);//JpaRepository already provides this

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);



}

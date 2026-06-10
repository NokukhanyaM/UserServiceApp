package com.example.UserServiceApp.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    private String email;
    private String password;
    private String role;
    private String username;


}

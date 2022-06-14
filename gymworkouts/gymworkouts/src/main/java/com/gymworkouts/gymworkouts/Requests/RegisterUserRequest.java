package com.gymworkouts.gymworkouts.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {
    private String userName;
    private String password;
    private String repeatPassword;
    private String firstName;
    private String lastName;
    private String email;
}

package com.gymworkouts.gymworkouts.Responses;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private boolean success;
    private String message;
    private UserEntity user;
    private Long userId;
}

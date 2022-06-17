package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    UserRepository userRepository;
}

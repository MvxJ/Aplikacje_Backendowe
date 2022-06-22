package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import com.gymworkouts.gymworkouts.Repository.UserRepository;
import com.gymworkouts.gymworkouts.Requests.RegisterUserRequest;
import com.gymworkouts.gymworkouts.Requests.UserLoginRequest;
import com.gymworkouts.gymworkouts.Responses.AuthenticationResponse;
import com.gymworkouts.gymworkouts.Responses.UserActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public UserActionResponse registerUser(HttpServletRequest request, RegisterUserRequest registerUserRequest) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            return new UserActionResponse(false, "User is already logged in!");
        }

        if (!Objects.equals(registerUserRequest.getPassword(), registerUserRequest.getRepeatPassword())) {
            return new UserActionResponse(false, "Passwords doesn't mach!");
        }

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(registerUserRequest.getFirstName());
            userEntity.setLastName(registerUserRequest.getLastName());
            userEntity.setPassword(registerUserRequest.getPassword());
            userEntity.setUsername(registerUserRequest.getUserName());
            userEntity.setEmail(registerUserRequest.getEmail());

            userRepository.save(userEntity);

            return new UserActionResponse(true, "Successfully registered user!");
        } catch (Exception exception) {
            return new UserActionResponse(false, exception.getMessage());
        }
    }

    public AuthenticationResponse loginUser(HttpServletRequest request, UserLoginRequest userLoginRequest) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            return new AuthenticationResponse(false, "User already logged in!", null);
        }

        Optional<UserEntity> userOptional = this.userRepository.findByUsername(userLoginRequest.getUserName());

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();

            if (Objects.equals(userLoginRequest.getPassword(), userOptional.get().getPassword())) {
                session.setAttribute("LOGGED_USER_ID", userEntity.getId());

                return new AuthenticationResponse(true, "Successfully logged in!", userOptional.get());
            } else {
                return new AuthenticationResponse(false, "Wrong creditentials!", null);
            }
        }

        return new AuthenticationResponse(false, "User not found!", null);
    }

    public UserActionResponse logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            session.removeAttribute("LOGGED_USER_ID");

            return new UserActionResponse(true, "Successfully logged out!");
        }

        return new UserActionResponse(false, "User wasn't logged!");
    }

    public Long getLoggedUserId(HttpSession session) {
        return (Long) session.getAttribute("LOGGED_USER_ID");
    }

    public  boolean isUserLogged(HttpSession session) {
        Long loggedUserId = this.getLoggedUserId(session);
        return loggedUserId != null;
    }
}

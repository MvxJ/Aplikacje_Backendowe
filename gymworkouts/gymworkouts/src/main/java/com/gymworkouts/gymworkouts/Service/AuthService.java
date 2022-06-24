package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import com.gymworkouts.gymworkouts.Repository.UserRepository;
import com.gymworkouts.gymworkouts.Requests.RegisterUserRequest;
import com.gymworkouts.gymworkouts.Requests.UserLoginRequest;
import com.gymworkouts.gymworkouts.Responses.AuthenticationResponse;
import com.gymworkouts.gymworkouts.Responses.UserActionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    private RabbitTemplate rabitTemplate;

    public ResponseEntity<UserActionResponse> registerUser(HttpServletRequest request, RegisterUserRequest registerUserRequest) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UserActionResponse(false, "User is already logged in!"));
        }

        if (!Objects.equals(registerUserRequest.getPassword(), registerUserRequest.getRepeatPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UserActionResponse(false, "Passwords doesn't mach!"));
        }

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(registerUserRequest.getFirstName());
            userEntity.setLastName(registerUserRequest.getLastName());
            userEntity.setPassword(registerUserRequest.getPassword());
            userEntity.setUsername(registerUserRequest.getUserName());
            userEntity.setEmail(registerUserRequest.getEmail());

            userRepository.save(userEntity);

            rabitTemplate.convertAndSend("","user-registration", userEntity);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new UserActionResponse(true, "Successfully registered user!"));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserActionResponse(false, exception.getMessage()));
        }
    }

    public ResponseEntity<AuthenticationResponse> loginUser(HttpServletRequest request, UserLoginRequest userLoginRequest) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new AuthenticationResponse(false, "User already logged in!", null));
        }

        Optional<UserEntity> userOptional = this.userRepository.findByUsername(userLoginRequest.getUserName());

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();

            if (Objects.equals(userLoginRequest.getPassword(), userOptional.get().getPassword())) {
                session.setAttribute("LOGGED_USER_ID", userEntity.getId());

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(
                                new AuthenticationResponse(
                                        true,
                                        "Successfully logged in!",
                                        userOptional.get()
                                )
                        );
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new AuthenticationResponse(false, "Wrong creditentials!", null));
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new AuthenticationResponse(false, "User not found!", null));
    }

    public ResponseEntity<UserActionResponse> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            session.removeAttribute("LOGGED_USER_ID");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new UserActionResponse(true, "Successfully logged out!"));
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new UserActionResponse(false, "User wasn't logged!"));
    }

    public Long getLoggedUserId(HttpSession session) {
        return (Long) session.getAttribute("LOGGED_USER_ID");
    }

    public  boolean isUserLogged(HttpSession session) {
        Long loggedUserId = this.getLoggedUserId(session);
        return loggedUserId != null;
    }
}

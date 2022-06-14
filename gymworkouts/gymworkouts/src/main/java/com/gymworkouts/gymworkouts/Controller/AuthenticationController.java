package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import com.gymworkouts.gymworkouts.Repository.UserRepository;
import com.gymworkouts.gymworkouts.Requests.RegisterUserRequest;
import com.gymworkouts.gymworkouts.Requests.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(
            value = "/user/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity loginUser(
            HttpServletRequest request,
            @RequestBody UserLoginRequest userLoginRequest
    ) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false\"}");
        }

        Optional<UserEntity> userOptional = this.userRepository.findByUsername(userLoginRequest.getUserName());

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();

            if (Objects.equals(userLoginRequest.getPassword(), userOptional.get().getPassword())) {
                session.setAttribute("LOGGED_USER_ID", userEntity.getId());

                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"result\":\"true\"}");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"result\":\"false\"}");
            }

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"result\":\"false\"}");
    }

    @RequestMapping(
            value = "/user/logout",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity logout(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            session.removeAttribute("LOGGED_USER_ID");

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"result\":\"false\"}");
    }

    @RequestMapping(
            value = "/user/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity register(
            HttpServletRequest request,
            @RequestBody RegisterUserRequest registerUserRequest
    ) {
        HttpSession session = request.getSession();

        if (this.isUserLogged(session)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false user logged in already\"}");
        }

        if (!Objects.equals(registerUserRequest.getPassword(), registerUserRequest.getRepeatPassword())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false not same passwords\"}");
        }

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(registerUserRequest.getFirstName());
            userEntity.setLastName(registerUserRequest.getLastName());
            userEntity.setPassword(registerUserRequest.getPassword());
            userEntity.setUsername(registerUserRequest.getUserName());
            userEntity.setEmail(registerUserRequest.getEmail());
            userEntity.setWeight(0.0);
            userEntity.setAge(0);
            userEntity.setHeight(0.0);

            userRepository.save(userEntity);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\" "+ exception.getMessage() + "\"}");
        }
    }

    @RequestMapping(
            value = "/user/check",
            method = RequestMethod.POST
    )
    public ResponseEntity checkUser(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Long loggedUserId = this.getLoggedUserId(session);

        if (loggedUserId == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false\"}");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"result\":\"true\"}");
    }

    public boolean isUserLogged(HttpSession session) {
        Long loggedUserId = this.getLoggedUserId(session);
        return loggedUserId != null;
    }

    public Long getLoggedUserId(HttpSession session) {
        return (Long) session.getAttribute("LOGGED_USER_ID");
    }
}

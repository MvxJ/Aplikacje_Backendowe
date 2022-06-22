package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Requests.RegisterUserRequest;
import com.gymworkouts.gymworkouts.Requests.UserLoginRequest;
import com.gymworkouts.gymworkouts.Responses.AuthenticationResponse;
import com.gymworkouts.gymworkouts.Responses.CheckAccessResponse;
import com.gymworkouts.gymworkouts.Responses.UserActionResponse;
import com.gymworkouts.gymworkouts.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @RequestMapping(
            value = "/user/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AuthenticationResponse loginUser(
            HttpServletRequest request,
            @RequestBody UserLoginRequest userLoginRequest
    ) {
        return this.authService.loginUser(request, userLoginRequest);
    }

    @RequestMapping(
            value = "/user/logout",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserActionResponse logout(
            HttpServletRequest request
    ) {
        return this.authService.logoutUser(request);
    }

    @RequestMapping(
            value = "/user/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserActionResponse register(
            HttpServletRequest request,
            @RequestBody RegisterUserRequest registerUserRequest
    ) {
        return this.authService.registerUser(request, registerUserRequest);
    }

    @RequestMapping(
            value = "/user/check",
            method = RequestMethod.POST
    )
    public CheckAccessResponse checkUser(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Long loggedUserId = this.authService.getLoggedUserId(session);

        if (loggedUserId == null) {
            return new CheckAccessResponse(true, loggedUserId);
        }

        return new CheckAccessResponse(false, null);
    }
}

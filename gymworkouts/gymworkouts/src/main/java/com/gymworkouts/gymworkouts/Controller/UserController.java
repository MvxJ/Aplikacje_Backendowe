package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import com.gymworkouts.gymworkouts.Repository.UserRepository;
import com.gymworkouts.gymworkouts.Requests.UpdateUserRequest;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Responses.UserResponse;
import com.gymworkouts.gymworkouts.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserEntity> getUser(
            @PathVariable long id
    ) {
        return ResponseEntity.of(this.userRepository.findById(id));
    }

    @RequestMapping(
            value = "/user/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<UserEntity>> getUsers() {
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @RequestMapping(
            value = "/user/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeleteResponse> deleteUser(
        @PathVariable long id
    ) {
        try {
            return this.userService.deleteUser(id);
        } catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DeleteResponse(false, exception.getMessage()));
        }
    }

    @RequestMapping(
            value = "/user/update/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UpdateResponse updateUser(
            @PathVariable long id,
            @RequestBody UpdateUserRequest userRequest
    ) {
        try {
            return this.userService.updateUser(id, userRequest);
        } catch (Exception exception) {
            return new UpdateResponse(false, exception.getMessage());
        }
    }

    @RequestMapping(
            value = "/user/profile",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserResponse getUserProfile(
            HttpServletRequest request
    ) {
        try {
            return this.userService.getProfile(request);
        } catch (Exception exception) {
            return new UserResponse(false, exception.getMessage(), null, null);
        }
    }
}

package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import com.gymworkouts.gymworkouts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

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
            value = "/user/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createUser(
            @RequestBody UserEntity user
    ) {
        try {
            this.userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false\"}");
        }
    }

    @RequestMapping(
            value = "/user/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteUser(
        @PathVariable long id
    ) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);

        if (userEntity.isPresent()) {
            try {
                this.userRepository.deleteById(id);

                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"result\":\"true\"}");
            } catch (Exception exception) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"" + exception.getMessage() + "\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Entity not found\"}");
    }

    @RequestMapping(
            value = "/user/update/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateUser(
            @PathVariable long id,
            @RequestBody UserEntity user
    ) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);

        if (userEntity.isPresent()) {
            try {
                UserEntity updatedUserEntity = userEntity.get();
                updatedUserEntity.setAge(user.getAge());
                updatedUserEntity.setEmail(user.getEmail());
                updatedUserEntity.setFirstName(user.getFirstName());
                updatedUserEntity.setLastName(user.getLastName());
                updatedUserEntity.setAge(user.getAge());
                updatedUserEntity.setHeight(user.getHeight());
                updatedUserEntity.setWeight(user.getWeight());
                updatedUserEntity.setPassword(user.getPassword());
                this.userRepository.save(updatedUserEntity);

                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"result\":\"true\"}");
            } catch (Exception exception){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"" + exception.getMessage() + "\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Entity not found\"}");
    }
}

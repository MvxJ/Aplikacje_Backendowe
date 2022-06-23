package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import com.gymworkouts.gymworkouts.Repository.UserRepository;
import com.gymworkouts.gymworkouts.Requests.UpdateUserRequest;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public ResponseEntity<UpdateResponse> updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        Optional<UserEntity> userEntity = this.userRepository.findById(userId);

        if (userEntity.isPresent()) {
            UserEntity updatedUserEntity = userEntity.get();
            updatedUserEntity.setAge(updateUserRequest.getAge());
            updatedUserEntity.setEmail(updateUserRequest.getEmail());
            updatedUserEntity.setFirstName(updateUserRequest.getFirstName());
            updatedUserEntity.setLastName(updateUserRequest.getLastName());
            updatedUserEntity.setAge(updateUserRequest.getAge());
            updatedUserEntity.setHeight(updateUserRequest.getHeight());
            updatedUserEntity.setWeight(updateUserRequest.getWeight());

            this.userRepository.save(updatedUserEntity);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new UpdateResponse(true, "Successfully updated user profile!"));
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UpdateResponse(false, "Entity not found!"));
    }

    public ResponseEntity<DeleteResponse> deleteUser(Long userId) {
        Optional<UserEntity> userEntity = this.userRepository.findById(userId);

        if (userEntity.isPresent()) {
            this.userRepository.deleteById(userId);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new DeleteResponse(true, "Successfully deleted user profile!"));
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DeleteResponse(false, "Entity not found!"));
    }

    public ResponseEntity<UserResponse> getProfile(HttpServletRequest request) {
        if (this.authService.isUserLogged(request.getSession())) {
            Long userId = this.authService.getLoggedUserId(request.getSession());

            Optional<UserEntity> user = this.userRepository.findById(userId);

            if (user.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(
                                new UserResponse(
                                        true,
                                        "Successfully get user profile!",
                                        user.get(),
                                        userId
                                )
                        );
            }

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new UserResponse(false, "Entity not found!", null, userId));
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new UserResponse(false, "Required logged in user!", null, null));
    }
}

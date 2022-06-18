package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Repository.UserRepository;
import com.gymworkouts.gymworkouts.Responses.AuthenticationResponse;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public UpdateResponse updateUser() {
        return new UpdateResponse(false, "Entity not found");
    }

    public AuthenticationResponse loginUser() {
        return new AuthenticationResponse(false, "Entity not found", null);
    }

    public CreateResponse createUser() {
        return new CreateResponse(false, "There was an error", null);
    }

    public DeleteResponse deleteUser() {
        return new DeleteResponse(false, "Entity not found!");
    }
}

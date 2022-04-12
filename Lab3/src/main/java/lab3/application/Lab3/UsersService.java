package lab3.application.Lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class UsersService {
    public Collection getUsers()
    {
        return null;
    }

    public static UserEntity createUser(String userData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserEntity user = objectMapper.readValue(userData, UserEntity.class);

        return user;
    }

    public static String getUser(HashMap<Integer, UserEntity> users, Integer id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(users.get(id));
    }
}

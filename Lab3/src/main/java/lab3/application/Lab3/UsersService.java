package lab3.application.Lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
public class UsersService {
    private static HashMap users = new HashMap<Integer, UserEntity>();

    public static UserEntity createUser(String userData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserEntity user = objectMapper.readValue(userData, UserEntity.class);

        return user;
    }

    public static ResponseEntity getUser(Integer id) throws JsonProcessingException {
        if (users.get(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(users.get(id));
        }
    }

    public static UserEntity createUser(UserEntity user) {
        user.setId(users.size() + 1);
        users.put(user.getId(), user);

        return user;
    }

    public static UsersPage getUsers(int pageNumber, int pageSize) {
        UsersPage userPage = new UsersPage();
        userPage.setUsers(users.values());
        userPage.setPageNumber(pageNumber);
        userPage.setTotalCount(users.size());
        userPage.setPageSize(pageSize);

        return userPage;
    }

    public static ResponseEntity deleteUsers(Integer id) {
        if (users.get(id) != null) {
            users.remove(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public static ResponseEntity updateUser(Integer id, UserEntity user) throws JsonProcessingException {
        user.setId(id);

        if (users.get(id) != null) {
            users.put(id, user);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public static void writeUsers() throws IOException {
        FileOutputStream file = new FileOutputStream("users.json");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(users));
        writer.flush();
    }

    public static void readUsers() throws IOException {
        try (FileInputStream inputStream = new FileInputStream("users.json")) {
            File file = new File("users.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            ObjectMapper objectMapper = new ObjectMapper();
            users = objectMapper.readValue(reader, HashMap.class);
        }
    }
}

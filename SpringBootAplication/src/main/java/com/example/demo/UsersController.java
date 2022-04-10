package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UsersController {
    private Map<Long, UserEntity> users = new HashMap<Long, UserEntity>() {{
        put(1L, new UserEntity(1L, "Max", 22));
        put(2L, new UserEntity(2L, "Klaudia", 21));
        put(3L, new UserEntity(3L, "Szymon", 18));
        put(4L, new UserEntity(4L, "Szymon", 22));
    }};

    @RequestMapping("/users/{id}/get")
    @ResponseBody
    public UserEntity user(
            @PathVariable Long id
    ) {
        return users.get(id);
    }

    @RequestMapping("/users/add")
    @ResponseBody
    public UserEntity user(
            @RequestParam String name,
            @RequestParam Integer age
    ) {
        Long id = (long)users.size() + 1L;
        UserEntity user = new UserEntity(id, name, age);
        users.put(id, user);

        return user;
    }

    @RequestMapping("/users")
    @ResponseBody
    public Map<Long, UserEntity> user() {
        return users;
    }

    @RequestMapping("/users/{id}/remove")
    @ResponseBody
    public String remove(
        @PathVariable Long id
    ) {
        try {
            users.remove(id);
        } catch (Exception exception) {
            return "Error while deleting users: " + exception.getMessage();
        }

        return "Successfully deleted user " + id;
    }
}

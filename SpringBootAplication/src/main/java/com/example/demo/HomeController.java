package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("/example")
    public String test(
            @RequestParam Integer variant
    ) {
        if (variant == 2 ) {
            return "example2";
        }

        return "example";
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public UserEntity user(
        @PathVariable Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer age
    ) {
        UserEntity user = new UserEntity(id, name, age);
        return user;
    }
}

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
    public String example() {
        return "example";
    }

    @RequestMapping("/exampleTest")
    public String exampleTest(@RequestParam(defaultValue = "1") Integer variant) {
        if (variant == 2 ) {
            return "example2";
        }

        return "example";
    }
}

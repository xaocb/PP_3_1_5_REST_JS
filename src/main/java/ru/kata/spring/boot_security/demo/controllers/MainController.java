package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/user")
    public String user() {
        return "pages/user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "pages/admin";
    }
}

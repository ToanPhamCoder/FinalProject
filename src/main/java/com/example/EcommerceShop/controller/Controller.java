package com.example.EcommerceShop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {
    @GetMapping("/hello222")
    public String hello() {
        return "Hello, World!";
    }
}

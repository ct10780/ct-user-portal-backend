package com.citiustech.userportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingController {

    @GetMapping("/welcome")
    public String testMethod(){
        return "Welcome to the User Portal";
    }

    // need t create one customer controller which load all the customer
}

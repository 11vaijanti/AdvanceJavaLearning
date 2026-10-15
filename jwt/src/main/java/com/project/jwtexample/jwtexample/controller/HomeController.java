package com.project.jwtexample.jwtexample.controller;

import com.project.jwtexample.jwtexample.model.User;
import com.project.jwtexample.jwtexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("getting Users");
        return this.userService.getUsers();
    }


}

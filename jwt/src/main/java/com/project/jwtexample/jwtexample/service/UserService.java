package com.project.jwtexample.jwtexample.service;

import com.project.jwtexample.jwtexample.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store = new ArrayList<>();
    public UserService(){
        store.add(new User(UUID.randomUUID().toString(), "maya", "maya@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "vaijanti", "vaijanti@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "yash", "yash@gmail.com"));
    }
    public List<User> getUser(){
        return this.store;
    }

    public List<User> getUsers() {
        return this.store;
    }
}

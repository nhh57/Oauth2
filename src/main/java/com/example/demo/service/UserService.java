package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService  {
    User saveUser(User user);

    void addRoleToUser(String username,String roleName);

    User getUser(String username);

    User getUserByEmail(String email);

    List<User> getUsers();


}

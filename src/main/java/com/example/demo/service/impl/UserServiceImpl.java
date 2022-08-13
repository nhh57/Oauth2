package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}

package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.repo.RoleRepo;
import com.example.demo.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Role getRole(String name) {
        return roleRepo.findByName(name);
    }
}

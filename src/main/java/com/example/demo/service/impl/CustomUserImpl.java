package com.example.demo.service.impl;

import com.example.demo.common.Utils;
import com.example.demo.jwt.CustomUser;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if (user == null) {
            log.error("User not found: " + username);
        } else {
            log.info("User found in the database: {}", username);
        }
        String roles = user.getRoles().stream().map(x -> x.getName()).collect(Collectors.toList()).toString();
        log.info("Roles: " + roles);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        log.info("SimpleGrantedAuthority: " + authorities.stream().map(x -> x.getAuthority()).collect(Collectors.toList()).toString());
        return new CustomUser(user, authorities);
    }

    public void LoginFromOauth2(OAuth2AuthenticationToken oauth2){
        User user = new User();
        user.setName(oauth2.getPrincipal().getAttribute("name"));
        user.setPassword("");
        user.setUsername(oauth2.getPrincipal().getAttribute("email"));
        user.setEmail(oauth2.getPrincipal().getAttribute("email"));
        Role role =  roleService.getRole("CUSTOMER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(Utils.convertListObjectToJsonArray(roles));
    }

    
}

package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> index() {
        BaseResponse response = new BaseResponse();
        List<User> userList = userService.getUsers();
        response.setData(userList);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> info(OAuth2AuthenticationToken oauth2) throws Exception {
        BaseResponse response = new BaseResponse();
        User user = new User();
        user.setName(oauth2.getPrincipal().getAttribute("name"));
        user.setPassword("");
        user.setUsername(oauth2.getPrincipal().getAttribute("email"));
        user.setEmail(oauth2.getPrincipal().getAttribute("email"));
        Role role =  roleService.getRole("CUSTOMER");
        System.out.println("hehehe");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(Utils.convertListObjectToJsonArray(roles));
        response.setData(user);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-user-login-oauth", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> createUserLoginOauth2(OAuth2AuthenticationToken oauth2) throws Exception {
        BaseResponse response = new BaseResponse();
        User user = new User();
        user.setName(oauth2.getPrincipal().getAttribute("name"));
        user.setPassword("");
        user.setUsername(oauth2.getPrincipal().getAttribute("email"));
        user.setEmail(oauth2.getPrincipal().getAttribute("email"));
        Role role =  roleService.getRole("CUSTOMER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(Utils.convertListObjectToJsonArray(roles));
        User user2 = userService.getUserByEmail(user.getEmail());
        if(user2 == null){
             user2 = userService.saveUser(user);
        }
        response.setData(user2);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }

    @RequestMapping("/index")
    public String hehe(){
        return "hhihii";
    }
}

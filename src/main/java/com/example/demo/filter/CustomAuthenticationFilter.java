package com.example.demo.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.MultiValueMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManagerBean) {
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.info("username is: {}"+ username);
        log.info("password is: {}"+ password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        if(authenticationToken == null){
            System.out.println("Rỗng: "+ authenticationToken);
        }else{
            System.out.println("Không rỗng: "+ authenticationToken);
        }
        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {


    }


}

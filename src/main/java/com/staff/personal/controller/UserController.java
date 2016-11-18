package com.staff.personal.controller;

import com.staff.personal.domain.Role;
import com.staff.personal.domain.User;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.dto.UserRegistrationDTO;
import com.staff.personal.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.xml.ws.Response;
import java.util.Date;

/**
 * Created by mtustanovskyy on 10/29/16.
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController  {


    @Autowired
    UserService userService;

    @RequestMapping("/{username}")
    public UserDTO getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
            throws ServletException {
        User user = userService.getUserByUsernameAndPassword(login.username, login.password);

        if (user == null) {
            throw new ServletException("Invalid login");

        }
        return new LoginResponse(Jwts.builder().setSubject(user.getEmail()).setSubject(user.getPassword())
                .claim("role", user.getRole()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public LoginResponse registration(@RequestBody final UserRegistrationDTO userRegistrationDTO){
        userService.createUser(userRegistrationDTO);
        return new LoginResponse(Jwts.builder().setSubject(userRegistrationDTO.getEmail()).setSubject(userRegistrationDTO.getPassword())
                .claim("role", Role.ROLE_OPERATOR).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }

    private static class UserLogin {
        public String username;
        public String password;
    }

    private static class LoginResponse {
        public String access_token;

        public LoginResponse(final String token) {
            this.access_token = token;
        }
    }







}

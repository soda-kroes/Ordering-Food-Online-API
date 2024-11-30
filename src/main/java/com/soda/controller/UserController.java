package com.soda.controller;

import com.soda.model.User;
import com.soda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping ("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String token) {
        User user = userService.findUserByJwtToken(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

package com.example.book_your_show.controller;

import com.example.book_your_show.requestDTO.UserRequest;
import com.example.book_your_show.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    //Method 1:
    @PostMapping("/addDetails")
    public ResponseEntity addDetails(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userServiceImpl.addDetails(userRequest), HttpStatus.CREATED);
    }

}

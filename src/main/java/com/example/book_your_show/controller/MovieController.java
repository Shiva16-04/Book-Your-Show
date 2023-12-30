package com.example.book_your_show.controller;

import com.example.book_your_show.requestDTO.MovieRequest;
import com.example.book_your_show.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieServiceImpl movieServiceImpl;

    @PostMapping("/addDetails")
    public ResponseEntity addDetails(@RequestBody MovieRequest movieRequest){
        return new ResponseEntity<>(movieServiceImpl.addDetails(movieRequest), HttpStatus.CREATED);
    }
}

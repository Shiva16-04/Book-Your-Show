package com.example.book_your_show.controller;

import com.example.book_your_show.requestDTO.TheatreRequest;
import com.example.book_your_show.service.impl.TheatreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theatre")
public class TheatreController {
    @Autowired
    TheatreServiceImpl theatreServiceImpl;

    @PostMapping("/addDetails")
    public ResponseEntity addDetails (@RequestBody TheatreRequest theatreRequest){
        try {
            return new ResponseEntity<>(theatreServiceImpl.addDetails(theatreRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

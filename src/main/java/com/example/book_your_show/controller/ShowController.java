package com.example.book_your_show.controller;

import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.service.impl.ShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowServiceImpl showServiceImpl;

    public ResponseEntity addDetails(@RequestBody ShowRequest showRequest){
        return new ResponseEntity<>(showServiceImpl.addDetails(showRequest), HttpStatus.CREATED);
    }
}

package com.example.book_your_show.controller;

import com.example.book_your_show.requestDTO.ScreenRequest;
import com.example.book_your_show.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("screen")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @PostMapping("/add-screens")
    public ResponseEntity addScreens(@RequestParam String theatreCode, @RequestBody List<ScreenRequest> screenRequestList){
        try {
            return new ResponseEntity<>(screenService.addScreens(theatreCode, screenRequestList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

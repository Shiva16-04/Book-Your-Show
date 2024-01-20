package com.example.book_your_show.controller;

import com.example.book_your_show.requestDTO.ScreenSeatRequest;
import com.example.book_your_show.requestDTO.ScreenSeatRequestAddl;
import com.example.book_your_show.service.ScreenSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("screen-seat")
public class ScreenSeatController {
    @Autowired
    private ScreenSeatService screenSeatService;
    @PostMapping("/add-screen-seats")
    public ResponseEntity addScreenSeats(@RequestParam String theatreCode,
                                         @RequestParam String screenNumber,
                                         @RequestBody List<ScreenSeatRequestAddl> screenSeatRequestList){
        try {
            return new ResponseEntity<>(screenSeatService.addScreenSeats(theatreCode, screenNumber, screenSeatRequestList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-screen-seat")
    public ResponseEntity deleteScreenSeat(@RequestParam String theatreCode,
                                           @RequestParam String screenNumber,
                                           @RequestParam String screenSeatNumber){
        try {
            return new ResponseEntity<>(screenSeatService.deleteScreenSeat(theatreCode, screenNumber, screenSeatNumber), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

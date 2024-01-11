package com.example.book_your_show.controller;

import com.example.book_your_show.requestDTO.TicketRequest;
import com.example.book_your_show.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/book-ticket")
    public ResponseEntity bookATicket(@RequestBody TicketRequest ticketRequest){
        try {
            return new ResponseEntity<>(ticketService.bookTicket(ticketRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/cancel-a-ticket")
    public ResponseEntity cancelATicket(@RequestBody String ticketCode){
        try {
            return new ResponseEntity(ticketService.cancelTicket(ticketCode),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("/movie-booking-revenue")
    public ResponseEntity getMovieBookingRevenue(@RequestParam String movieCode, @RequestParam LocalDate startDateOfRange,
                                                 @RequestParam LocalDate endDateOfRange){
        return new ResponseEntity(ticketService.getMovieBookingRevenueByMovieCodeAndDateRange(movieCode, startDateOfRange,
                endDateOfRange),HttpStatus.OK);
    }
}

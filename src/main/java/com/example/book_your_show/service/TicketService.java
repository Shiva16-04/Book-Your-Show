package com.example.book_your_show.service;

import com.example.book_your_show.requestDTO.TicketRequest;
import com.example.book_your_show.responseDTO.TicketResponse;

import java.time.LocalDate;

public interface TicketService {
    public TicketResponse bookTicket(TicketRequest ticketRequest)throws Exception;
    public String cancelTicket(String ticketCode)throws Exception;
    public long getMovieBookingRevenueByMovieCodeAndDateRange(String movieCode, LocalDate startDateOfRange, LocalDate endDateOfRange);

}

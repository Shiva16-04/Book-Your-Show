package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Ticket;
import com.example.book_your_show.repository.TicketRepository;
import com.example.book_your_show.requestDTO.TicketRequest;
import com.example.book_your_show.transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl {
    @Autowired
    private TicketRepository ticketRepository;

    //Method 1:
    public int bookTicket(TicketRequest ticketRequest){
        Ticket ticket= TicketTransformer.ticketRequestToTicket(ticketRequest);
        return 1;
    }
}

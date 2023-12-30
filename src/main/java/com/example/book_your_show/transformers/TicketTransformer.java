package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Ticket;
import com.example.book_your_show.requestDTO.TicketRequest;

public class TicketTransformer {
    public static Ticket ticketRequestToTicket(TicketRequest ticketRequest){
        return Ticket.builder()
//                .name(ticketRequest.getMovieName())
//                .showDate(ticketRequest.getShowDate())
//                .locality(ticketRequest.getLocality())
//                .locationUrl(ticketRequest.getLocationUrl())
                .build();
    }
}

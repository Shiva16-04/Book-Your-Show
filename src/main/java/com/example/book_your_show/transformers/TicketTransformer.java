package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Movie;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.entities.Ticket;
import com.example.book_your_show.responseDTO.TicketResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketTransformer {
    public static Ticket ticketRequestToTicket(String code){
        return Ticket.builder()
                .code(code)
                .bookedSeats(new ArrayList<>())
                .build();
    }
    public static TicketResponse ticketToTicketResponse(Show show, Map<String,List<String>>bookedSeatInfo, Ticket ticket){
        Movie movie=show.getMovie();
        Theatre theatre=show.getScreen().getTheatre();
        return TicketResponse.builder()
                .movieName(movie.getName())
                .filmCertificationCategory(movie.getFilmCertificationCategory().getDisplayName())
                .format(show.getFormatEnum().getDisplayName())
                .language(show.getLanguageEnum().getDisplayName())
                .theatreName(theatre.getName())
                .locality(theatre.getAddress().getLocality())
                .screenNumber(show.getScreen().getScreenNumber())
                .locationUrl(theatre.getAddress().getLocationUrl())
                .seatInfo(bookedSeatInfo)
                .showDate(show.getDate())
                .showTime(show.getStartTime().toLocalTime())
                .totalCost(ticket.getTotalPrice())
                .build();
    }
}

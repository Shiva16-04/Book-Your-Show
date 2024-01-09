package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Movie;
import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.requestDTO.ShowRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShowTransformer {
    public static Show ShowRequestToShow(ShowRequest showRequest, LocalDateTime endTime){

        return Show.builder()
                .date(showRequest.getStartTime().toLocalDate())
                .startTime(showRequest.getStartTime())
                .ticketCancellationTimeLimit(showRequest.getTicketCancellationTimeLimit())
                .formatEnum(showRequest.getFormatEnum())
                .languageEnum(showRequest.getLanguage())
                .showSeatList(new ArrayList<>())
                .ticketList(new ArrayList<>())
                .endTime(endTime)
                .build();
    }
}

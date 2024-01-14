package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Show;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.responseDTO.ShowResponseTheatre;
import com.example.book_your_show.responseDTO.ShowSeatResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public static ShowResponseTheatre showToShowResponseTheatre(String movieName, String screenNumber, Show show, List<ShowSeatResponse> showSeatResponseList){

        return ShowResponseTheatre.builder()
                .movieName(movieName)
                .screenNumber(screenNumber)
                .showDate(show.getDate())
                .showTime(show.getStartTime().toLocalTime())
                .language(show.getLanguageEnum().toString())
                .format(show.getFormatEnum().toString())
                .showSeatResponseList(showSeatResponseList)
                .build();
    }
}

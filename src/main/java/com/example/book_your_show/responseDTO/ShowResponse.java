package com.example.book_your_show.responseDTO;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

public class ShowResponse {
    String movieName;
    String language;
    String format;
    String screenNumber;
    String theatreName;
    URL location;
    LocalDate date;
    LocalTime time;
    LocalTime ticketCancellationTimeLimit;
}

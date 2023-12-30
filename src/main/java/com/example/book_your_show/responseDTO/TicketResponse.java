package com.example.book_your_show.responseDTO;

import jakarta.persistence.Column;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TicketResponse {
    String movieName;
    String filmCertificationCategory;
    String format;
    String theatreName;
    LocalDate showDate;
    LocalTime showTime;
    List<String>seatInfo;
    int totalCost;
    String City;
    URL locationUrl;
}

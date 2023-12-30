package com.example.book_your_show.requestDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowRequest {
    String movieCode;
    LocalDate Date;
    LocalTime time;
    String theatreCode;
    String screenNumber;
    int priceOfSilverSeats;
    int priceOfGoldSeats;
    int priceOfPlatinumSeats;
    int priceOfLoungers;
    int priceOfSemiRecliners;
    int priceOfRecliners;
}

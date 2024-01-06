package com.example.book_your_show.requestDTO;

import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.LanguageEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowRequest {
    String movieCode;
    LocalDateTime startTime;
    LanguageEnum language;
    FormatEnum formatEnum;
    String theatreCode;
    String screenNumber;
    int priceOfSilverSeats;
    int priceOfGoldSeats;
    int priceOfPlatinumSeats;
    int priceOfLoungers;
    int priceOfSemiRecliners;
    int priceOfRecliners;
}

package com.example.book_your_show.requestDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScreenRequest {
    String screenNumber;
    int totalNumberOfSeats;
    List<ScreenSeatRequest> screenSeatRequestList;
    List<String>seatListForPD;
    int noOfSilverSeats;
    int noOfGoldSeats;
    int noOfPlatinumSeats;
    int noOfLoungers;
    int noOfRecliners;
    int noOfSeatsPerRow;

}

package com.example.book_your_show.requestDTO;

import com.example.book_your_show.enums.SeatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScreenSeatRequest {
    String seatPrefixName;
    SeatType seatType;
    int numberOfSeats;

}

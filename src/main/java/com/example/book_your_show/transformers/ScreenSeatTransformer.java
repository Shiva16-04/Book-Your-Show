package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.enums.SeatType;

public class ScreenSeatTransformer {
    public static ScreenSeat toTheatreSeat(String seatNo, SeatType seatType, Screen screen){
        return ScreenSeat.builder()
                .screenSeatNumber(seatNo)
                .screenSeatType(seatType)
                .screen(screen)
                .build();
    }
}

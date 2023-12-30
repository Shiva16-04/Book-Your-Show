package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.enums.SeatType;

public class ShowSeatTransformer {
    public static ShowSeat showSeatRequestToShowSeat(String screenSeatNo, SeatType screenSeatType, int cost, boolean isAvailable, boolean isFoodAttached){
        return ShowSeat.builder()
                .showSeatNo(screenSeatNo)
                .showSeatType(screenSeatType)
                .cost(cost)
                .isAvailable(isAvailable)
                .isFoodAttached(isFoodAttached)
                .build();
    }
}

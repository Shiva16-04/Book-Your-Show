package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.enums.SeatType;
import com.example.book_your_show.responseDTO.ShowSeatResponse;

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
    public static ShowSeatResponse showSeatToShowSeatResponse(ShowSeat showSeat){
        return ShowSeatResponse.builder()
                .showSeatNo(showSeat.getShowSeatNo())
                .showSeatType(showSeat.getShowSeatType().getDisplayName())
                .cost(showSeat.getCost())
                .isAvailable(showSeat.isAvailable())
                .isFoodAttached(showSeat.isFoodAttached())
                .build();
    }
}

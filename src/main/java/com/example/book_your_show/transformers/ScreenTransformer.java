package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.requestDTO.ScreenRequest;

import java.util.ArrayList;

public class ScreenTransformer {
    public static Screen screenRequesToScreen(ScreenRequest screenRequest){
        return Screen.builder()
                .screenNumber(screenRequest.getScreenNumber())
                .noOfSeats(screenRequest.getTotalNumberOfSeats())
                .screenSeatList(new ArrayList<>())
                .showList(new ArrayList<>())
                .build();
    }
}

package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.enums.SeatType;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.service.ShowSeatService;
import com.example.book_your_show.transformers.ShowSeatTransformer;

import java.util.ArrayList;
import java.util.List;

public class ShowSeatServiceImpl implements ShowSeatService {


    public List<ShowSeat> createShowSeats(Show show, Screen screen, ShowRequest showRequest){

        List<ScreenSeat> screenSeatList =screen.getScreenSeatList();
        List<ShowSeat>showSeatList=new ArrayList<>();

        for(ScreenSeat screenSeat : screenSeatList){
            int cost=0;
            boolean isFoodAttached=false;
            SeatType seatType=screenSeat.getScreenSeatType();
            switch (seatType){
                case SILVER -> {
                    cost = showRequest.getPriceOfSilverSeats();
                    isFoodAttached = false;
                }
                case GOLD ->{
                    cost=showRequest.getPriceOfGoldSeats();
                    isFoodAttached=false;
                }
                case PLATINUM -> {
                    cost = showRequest.getPriceOfPlatinumSeats();
                    isFoodAttached=true;
                }
                case LOUNGERS -> {
                    cost = showRequest.getPriceOfLoungers();
                    isFoodAttached=true;
                }
                case SEMI_RECLINERS -> {
                    cost= showRequest.getPriceOfSemiRecliners();
                    isFoodAttached=true;
                }
                case RECLINERS -> {
                    cost = showRequest.getPriceOfRecliners();
                    isFoodAttached=true;
                }
            }

            ShowSeat showSeat= ShowSeatTransformer.showSeatRequestToShowSeat(screenSeat.getScreenSeatNumber(),screenSeat.getScreenSeatType(),
                    cost, true, isFoodAttached);

            //setting the foreign keys
            showSeat.setShow(show);
            showSeat.setScreenSeat(screenSeat);

            //mapping foreign keys bidirectionally
            show.getShowSeatList().add(showSeat);
            screenSeat.getShowSeatList().add(showSeat);

            //adding it tot the showSeat list
            showSeatList.add(showSeat);
        }
        return showSeatList;
    }
}

package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.enums.SeatType;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.requestDTO.ShowSeatRequest;
import com.example.book_your_show.service.ScreenService;
import com.example.book_your_show.service.ShowSeatService;
import com.example.book_your_show.service.ShowService;
import com.example.book_your_show.transformers.ShowSeatTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    private ShowService showService;
    @Autowired
    private ScreenService screenService;

    @Transactional
    public String addShowSeats(String showCode, String screenNumber, String theatreCode,  ShowSeatRequest showSeatRequest)throws Exception{

        Show show=showService.getShowByShowCode(showCode);
        Screen screen=screenService.getScreenByTheatreCodeAndScreenNumber(theatreCode, screenNumber);
        List<ScreenSeat>screenSeatList=screen.getScreenSeatList();
        List<ShowSeat>showSeatList=new ArrayList<>();

        for(ScreenSeat screenSeat : screenSeatList){
            int cost=0;
            boolean isFoodAttached=false;
            SeatType seatType=screenSeat.getScreenSeatType();
            switch (seatType){
                case SILVER -> {
                    cost = showSeatRequest.getPriceOfSilverSeats();
                    isFoodAttached = false;
                }
                case GOLD ->{
                    cost=showSeatRequest.getPriceOfGoldSeats();
                    isFoodAttached=false;
                }
                case PLATINUM -> {
                    cost = showSeatRequest.getPriceOfPlatinumSeats();
                    isFoodAttached=true;
                }
                case LOUNGERS -> {
                    cost = showSeatRequest.getPriceOfLoungers();
                    isFoodAttached=true;
                }
                case SEMI_RECLINERS -> {
                    cost= showSeatRequest.getPriceOfSemiRecliners();
                    isFoodAttached=true;
                }
                case RECLINERS -> {
                    cost = showSeatRequest.getPriceOfRecliners();
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

            //adding it to the showSeat list
            showSeatList.add(showSeat);
        }
        return "Show seat list"+ showSeatList+" have been added successfully";
    }
}

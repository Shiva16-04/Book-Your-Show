package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.repository.ScreenRepository;
import com.example.book_your_show.requestDTO.ScreenRequest;
import com.example.book_your_show.requestDTO.TheatreRequest;
import com.example.book_your_show.service.ScreenSeatService;
import com.example.book_your_show.service.ScreenService;
import com.example.book_your_show.transformers.ScreenTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ScreenSeatService screenSeatService;

    public List<Screen> createScreens(Theatre theatre, TheatreRequest theatreRequest){
        List<ScreenRequest>screenRequestList=theatreRequest.getScreenRequestList();
        List<Screen>screenList=new ArrayList<>();
        for(ScreenRequest screenRequest: screenRequestList) {
            Screen screen= ScreenTransformer.screenRequesToScreen(screenRequest);
            screen.setTheatre(theatre);

            List<ScreenSeat> screenSeatList =screenSeatService.createTheatreSeats(screen, screenRequest.getScreenSeatRequestList(), screenRequest.getSeatListForPD());

            screen.setScreenSeatList(screenSeatList);
            screenList.add(screen);
        }
        return screenList;
    }
}

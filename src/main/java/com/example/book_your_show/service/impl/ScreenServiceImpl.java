package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.exceptions.ScreenNotFoundException;
import com.example.book_your_show.repository.ScreenRepository;
import com.example.book_your_show.requestDTO.ScreenRequest;
import com.example.book_your_show.requestDTO.TheatreRequest;
import com.example.book_your_show.service.ScreenSeatService;
import com.example.book_your_show.service.ScreenService;
import com.example.book_your_show.service.TheatreService;
import com.example.book_your_show.transformers.ScreenTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ScreenSeatService screenSeatService;
    @Autowired
    private TheatreService theatreService;
    @Transactional
    public String addScreens(String theatreCode, List<ScreenRequest>screenRequestList)throws Exception{
        Theatre theatre=theatreService.getTheatreByTheatreCode(theatreCode);
        List<Screen>screenList=new ArrayList<>();
        for(ScreenRequest screenRequest: screenRequestList) {
            Screen screen= ScreenTransformer.screenRequesToScreen(screenRequest);
            screen.setTheatre(theatre);
            screenRepository.save(screen);

            //calling screen seat service to add screen seats
            screenSeatService.addScreenSeats(theatre.getCode(), screen.getScreenNumber(), screenRequest.getScreenSeatRequestList(), screenRequest.getSeatListForPD());

            theatre.getScreens().add(screen);
            screenRepository.save(screen);
        }
        return "Screen(s) "+screenRequestList+" have been added successfully to the theatre with theatre code: "+theatreCode;
    }
    public Screen getScreenByTheatreCodeAndScreenNumber(String theatreCode, String screenNumber)throws Exception{
        Optional<Screen> optionalScreen=screenRepository.findByTheatreCodeAndScreenNumber(theatreCode, screenNumber);
        if(optionalScreen.isEmpty()){
            throw new ScreenNotFoundException("Screen not found with the particular screen number and theatre code combination. Try again with correct information");
        }
        return optionalScreen.get();
    }

}

package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.enums.SeatType;
import com.example.book_your_show.repository.ScreenSeatRepository;
import com.example.book_your_show.requestDTO.ScreenSeatRequest;
import com.example.book_your_show.service.ScreenSeatService;
import com.example.book_your_show.service.ScreenService;
import com.example.book_your_show.transformers.ScreenSeatTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenSeatServiceImpl implements ScreenSeatService {

    @Autowired
    private ScreenService screenService;
    @Autowired
    private ScreenSeatRepository screenSeatRepository;
    @Transactional(rollbackFor = Exception.class)
    public String addScreenSeats(String theatreCode, String screenNumber, List<ScreenSeatRequest>screenSeatRequestList, List<String>pdSeatList)throws Exception{
        Screen screen=screenService.getScreenByTheatreCodeAndScreenNumber(theatreCode, screenNumber);
        List<ScreenSeat> screenSeatList =new ArrayList<>();
        for(ScreenSeatRequest screenSeatRequest: screenSeatRequestList){
            int seatCount=screenSeatRequest.getNumberOfSeats();
            String seatNumberPrefix=screenSeatRequest.getSeatPrefixName();
            for(int i=1; i<=seatCount; i++){
                String seatNo=seatNumberPrefix+""+i;
                SeatType seatType=pdSeatList.contains(seatNo) ? SeatType.PHYSICALLY_HANDICAPPED : screenSeatRequest.getSeatType();
                ScreenSeat screenSeat = ScreenSeatTransformer.toTheatreSeat(seatNo, seatType, screen);
                screen.getScreenSeatList().add(screenSeat);
                screenSeatRepository.save(screenSeat);
            }
        }
        return "Screen seats "+screenSeatRequestList+"have been added successfully to the screen: "+screenNumber+" of theatre with code: "+theatreCode;
    }
}

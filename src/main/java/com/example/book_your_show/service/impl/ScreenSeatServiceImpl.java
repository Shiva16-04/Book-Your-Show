package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.requestDTO.ScreenSeatRequest;
import com.example.book_your_show.service.ScreenSeatService;
import com.example.book_your_show.transformers.ScreenSeatTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenSeatServiceImpl implements ScreenSeatService {
    public List<ScreenSeat> createTheatreSeats(Screen screen, List<ScreenSeatRequest>screenSeatRequestList, List<String>pdSeatList){

        List<ScreenSeat> screenSeatList =new ArrayList<>();
        for(ScreenSeatRequest screenSeatRequest: screenSeatRequestList){
            int seatCount=screenSeatRequest.getNumberOfSeats();
            String seatNumberPrefix=screenSeatRequest.getSeatPrefixName();
            for(int i=1; i<=seatCount; i++){
                String seatNo=seatNumberPrefix+""+i;
                ScreenSeat screenSeat = ScreenSeatTransformer.toTheatreSeat(seatNo, screenSeatRequest.getSeatType(), screen);
                screenSeatList.add(screenSeat);
            }
        }
        return screenSeatList;
    }
}

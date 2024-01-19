package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.entities.Ticket;
import com.example.book_your_show.enums.SeatType;
import com.example.book_your_show.exceptions.InvalidCodeException;
import com.example.book_your_show.repository.ScreenSeatRepository;
import com.example.book_your_show.requestDTO.ScreenSeatRequest;
import com.example.book_your_show.service.ScreenSeatService;
import com.example.book_your_show.service.ScreenService;
import com.example.book_your_show.service.ShowSeatService;
import com.example.book_your_show.service.TicketService;
import com.example.book_your_show.transformers.ScreenSeatTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ScreenSeatServiceImpl implements ScreenSeatService {

    @Autowired
    private ScreenService screenService;
    @Autowired
    private ShowSeatService showSeatService;
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
    @Transactional(rollbackFor = Exception.class)
    public String deleteScreenSeat(String theatreCode, String screenNumber, String screenSeatNumber)throws Exception{
        List<ScreenSeat> screenSeatList=screenSeatRepository.findFilteredTheatreShowResponseList(theatreCode, screenNumber, screenSeatNumber);
        if(screenSeatList.size()==0){
            throw new InvalidCodeException("Invalid theatreCode or screenNumber or screenSeat number! try passing the details again");
        }
        LocalDateTime presentDateTime=LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        ScreenSeat screenSeat=screenSeatList.get(0);

        // finding the shows seats that need to be cancelled due to the deletion of screen seat
        List<ShowSeat>showSeatList=screenSeat.getShowSeatList();
        List<ShowSeat>toBeDeletedShowSeatList=new ArrayList<>();
        Iterator<ShowSeat>showSeatIterator= showSeatList.iterator();
        while (showSeatIterator.hasNext()){
            ShowSeat showSeat=showSeatIterator.next();
            LocalDateTime showStartTime = showSeat.getShow().getStartTime();
            if(showStartTime.equals(presentDateTime) || showStartTime.isAfter(presentDateTime)){
                toBeDeletedShowSeatList.add(showSeat);
            }
        }
        showSeatService.deleteShowSeats(toBeDeletedShowSeatList);
        screenSeatRepository.deleteById(screenSeat.getId());

        return "screen seat "+ screenSeatNumber+" from screen "+screenNumber+" of theatre "+screenSeat.getScreen().getTheatre().getName()+" removed successfully";
    }
}

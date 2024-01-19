package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.enums.SeatType;
import com.example.book_your_show.repository.ShowSeatRepository;
import com.example.book_your_show.requestDTO.ShowSeatRequest;
import com.example.book_your_show.service.ScreenService;
import com.example.book_your_show.service.ShowSeatService;
import com.example.book_your_show.service.ShowService;
import com.example.book_your_show.service.TicketService;
import com.example.book_your_show.transformers.ShowSeatTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    private ShowService showService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Transactional(rollbackFor = Exception.class)
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
                case PHYSICALLY_HANDICAPPED -> {
                    cost=showSeatRequest.getPriceOfPhysicallyHandicappedSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForPHS();
                }
                case SILVER -> {
                    cost = showSeatRequest.getPriceOfSilverSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForSS();
                }
                case GOLD -> {
                    cost=showSeatRequest.getPriceOfGoldSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForGS();
                }
                case PLATINUM -> {
                    cost = showSeatRequest.getPriceOfPlatinumSeats();
                    isFoodAttached = showSeatRequest.isFoodAttachedForPS();
                }
                case LOUNGERS -> {
                    cost = showSeatRequest.getPriceOfLoungers();
                    isFoodAttached = showSeatRequest.isFoodAttachedForLS();
                }
                case SEMI_RECLINERS -> {
                    cost= showSeatRequest.getPriceOfSemiRecliners();
                    isFoodAttached = showSeatRequest.isFoodAttachedForSRS();
                }
                case RECLINERS -> {
                    cost = showSeatRequest.getPriceOfRecliners();
                    isFoodAttached = showSeatRequest.isFoodAttachedForRS();
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
    public String deleteShowSeats(String showCode, List<String>showSeatNumberList) throws Exception{
        List<ShowSeat>showSeatList=findShowSeatsByShowCodeAndShowSeatNoList(showCode, showSeatNumberList);
        processShowSeatDeletion(showSeatList);
        return showSeatList+" has been removed successfully";
    }
    public String deleteShowSeats(List<ShowSeat>showSeatList)throws Exception{
        processShowSeatDeletion(showSeatList);
        return showSeatList+" has been removed successfully";
    }
    @Transactional(rollbackFor = Exception.class)
    private void processShowSeatDeletion(List<ShowSeat>showSeatList)throws Exception{
        Iterator<ShowSeat> showSeatIterator= showSeatList.iterator();
        while (showSeatIterator.hasNext()){
            ShowSeat showSeat=showSeatIterator.next();
            Show show=showSeat.getShow();
            ScreenSeat screenSeat=showSeat.getScreenSeat();
            //setting show seat attributes to null
            showSeat.setShow(null);
            showSeat.setScreenSeat(null);
            //bi-directionally removing
            show.getShowSeatList().remove(showSeat);
            screenSeat.getShowSeatList().remove(showSeat);

            //Handling null pointer exception as ticket cancellation
            // can lead to removal of ticket mapping from other show seats
            if(showSeat.getTicket()==null)
                ticketService.cancelTicket(showSeat.getTicket().getCode());

            //finally deleting show seat after removing all the mappings
            showSeatRepository.deleteById(showSeat.getId());
        }
    }
    public List<ShowSeat> findShowSeatsByShowCodeAndShowSeatNoList(String showCode, List<String>showSeatNumberList){
        List<ShowSeat>showSeatList=showSeatRepository.findByShowCodeAndShowSeatNoIn(showCode, showSeatNumberList);
        return showSeatList;
    }
}

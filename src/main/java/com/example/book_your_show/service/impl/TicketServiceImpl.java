package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.entities.Ticket;
import com.example.book_your_show.entities.User;
import com.example.book_your_show.exceptions.InvalidTicketCodeException;
import com.example.book_your_show.exceptions.ShowSeatNotAvailableException;
import com.example.book_your_show.exceptions.TicketCannotBeBookedException;
import com.example.book_your_show.exceptions.TicketCannotBeCancelledException;
import com.example.book_your_show.generators.EmailGenerator;
import com.example.book_your_show.generators.TicketCodeGenerator;
import com.example.book_your_show.repository.TicketRepository;
import com.example.book_your_show.requestDTO.TicketRequest;
import com.example.book_your_show.responseDTO.TicketResponse;
import com.example.book_your_show.service.*;
import com.example.book_your_show.transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.example.book_your_show.service.impl.MailConfigurationServiceImpl.SENDER_EMAIL;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowSeatService showSeatService;
    @Autowired
    private TicketCodeGenerator ticketCodeGenerator;
    @Autowired
    private AuthenticationDetailsService authenticationDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShowService showService;
    @Autowired
    private EmailGenerator emailGenerator;
    @Autowired
    private MailConfigurationService mailConfigurationService;

    //Method 1:
    @Transactional(rollbackFor = Exception.class)
    public TicketResponse bookTicket(TicketRequest ticketRequest)throws Exception{
        //Assuming that requests to book seats are for available seats
        //as in front end there will be no option to book un available seats

        //Even though we can assume that show code we get are valid as front end will display
        //only valid shows but still handling exceptions to prevent sever down due to malware attacks

        String showCode=ticketRequest.getShowCode();
        List<String>showSeatNumberList=ticketRequest.getBookedSeats();

        Show show=showService.getShowByShowCode(showCode);
        List<ShowSeat>showSeatList=showSeatService.findShowSeatsByShowCodeAndShowSeatNoList(showCode, showSeatNumberList);

        LocalDateTime currentTime=LocalDateTime.now();
        LocalDateTime showTime=show.getEndTime();

        if(currentTime.isAfter(showTime)){
            throw new TicketCannotBeBookedException("Ticket cannot be booked as show with show code "+showCode+" is already ended on "+show.getEndTime());
        }

        String code=ticketCodeGenerator.generate("TKT");
        Ticket ticket= TicketTransformer.ticketRequestToTicket(code);


        int ticketCost=0;
        Map<String, List<String>>bookedSeatInfo=new HashMap<>();

        for(ShowSeat showSeat:showSeatList){
            if(!showSeat.isAvailable()){
                throw new ShowSeatNotAvailableException("Seat "+showSeat.getShowSeatNo()+" you are trying to access is not available");
            }
            ticketCost+=showSeat.getCost();
            String showSeatTypeName=showSeat.getShowSeatType().getDisplayName();
            List<String>bookedSeatInfoList=bookedSeatInfo.getOrDefault(showSeatTypeName, new ArrayList<String>());
            bookedSeatInfoList.add(showSeat.getShowSeatNo());
            bookedSeatInfo.put(showSeatTypeName, bookedSeatInfoList);

            //setting the attributes
            showSeat.setAvailable(false);
            showSeat.setFoodAttached(true);

            //mapping ticket and show seat and setting the foreign keys
            showSeat.setTicket(ticket);
            //mapping bidirectionally
            ticket.getBookedSeats().add(showSeat);
        }

        ticket.setTotalPrice(ticketCost);

        //mapping ticket and show
        ticket.setShow(show);
        show.getTicketList().add(ticket);

        //Getting the user details from login information and mapping ticket and user
        String emailId=authenticationDetailsService.getAuthenticationDetails();
        User user=userService.getUserByEmailId(emailId);

        //setting the foreign key
        ticket.setUser(user);
        //bi directionally mapping the keys
        user.getTicketList().add(ticket);

        ticketRepository.save(ticket);

        TicketResponse ticketResponse=TicketTransformer.ticketToTicketResponse(show, bookedSeatInfo, ticket);

        String emailBody=emailGenerator.ticketBookingConfirmationEmailGenerator(user.getName(), ticketResponse);
        mailConfigurationService.mailSender(SENDER_EMAIL, user.getEmailId(),emailBody, "Booking Confirmation");

        return ticketResponse;
    }
    @Transactional(rollbackFor = Exception.class)
    public String cancelTicket(String ticketCode)throws Exception{
        //Getting the user details from login information and mapping ticket and user
        String emailId=authenticationDetailsService.getAuthenticationDetails();
        User user=userService.getUserByEmailId(emailId);

        List<Ticket>userTicketList=user.getTicketList();
        Ticket ticket=getTicketByTicketCode(ticketCode);
        if(!userTicketList.contains(ticket)){
            throw new InvalidTicketCodeException("Ticket is invalid / not belongs to the user "+user.getName());
        }

        Show show=ticket.getShow();
        int ticketCost=ticket.getTotalPrice();

        LocalDateTime cancellationRequestTime= LocalDateTime.now();
        LocalDateTime showTime=show.getStartTime();
        LocalTime cancellationTime=show.getTicketCancellationTimeLimit();

        long totalMinutes = Duration.between(cancellationRequestTime, showTime).toMinutes();

        // Calculate hours and remaining minutes
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        // Create a new LocalTime using the calculated hours and minutes
        LocalTime difference = LocalTime.of((int) hours, (int) minutes);

        if(difference.isBefore(cancellationTime)){
            throw new TicketCannotBeCancelledException("Ticket cannot be cancelled now");
        }

        List<ShowSeat>bookedSeatList=ticket.getBookedSeats();

        for(ShowSeat showSeat: bookedSeatList){
            //setting the attributes
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);

            //de-mapping ticket and show seat and setting the foreign keys
            showSeat.setTicket(null);
        }
        //de-mapping ticket and show seat bidirectionally
        ticket.setBookedSeats(new ArrayList<>());

        //de-mapping ticket and show
        ticket.setShow(null);
        show.getTicketList().remove(ticket);

        //de-mapping ticket and user
        ticket.setUser(null);
        //bi directionally de-mapping the keys
        user.getTicketList().remove(ticket);

        ticketRepository.deleteById(ticket.getId());

        String message="Ticket has been cancelled. Refund amount of Rs "+ticketCost+" has been initiated. " +
                "Amount will be reflected in your account with in 5-7 days";

        String emailBody=emailGenerator.ticketCancellationConfirmationEmailGenerator(user.getName(), message);
        mailConfigurationService.mailSender(SENDER_EMAIL, user.getEmailId(),emailBody, "Booking Cancellation Confirmation");

        return message;
    }
    public long getMovieBookingRevenueByMovieCodeAndDateRange(String movieCode, LocalDate startDateOfRange, LocalDate endDateOfRange){
        Long totalRevenue=ticketRepository.getMovieBookingRevenueByMovieCode(movieCode, startDateOfRange, endDateOfRange);
        return totalRevenue==null?0:totalRevenue;

    }
    public Ticket getTicketByTicketCode(String ticketCode)throws Exception{
        Optional<Ticket>optionalTicket=ticketRepository.findByCode(ticketCode);
        if(!optionalTicket.isPresent()){
            throw new InvalidTicketCodeException("Ticket code is invalid!!!");
        }
        return optionalTicket.get();
    }
}

package com.example.book_your_show.generators;

import com.example.book_your_show.responseDTO.TicketResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class EmailGenerator {

    public String userEmailValidationCodeGenerator(){
        UUID uuid= UUID.randomUUID();
        return ""+uuid;
    }
    public String userSuccessfulRegistrationMessageEmailGenerator(String name){

        String body= "Dear "+name+" !!!\n\n\n" +
                "Welcome to the Book My Show. You have been successfully registered with us.\n\n\n" +
                "Regards,\n\n" +
                "Team Burning Beatles";
        return body;
    }
    public String ticketBookingConfirmationEmailGenerator(String name, TicketResponse ticketResponse){
        String body="Dear "+name+"!!!\n\n\n" +
                "Your ticket booking has been confirmed. Show the below e-ticket at the entry of respective theatre/screen.\n" +
                ticketResponse.getMovieName()+"("+ticketResponse.getFilmCertificationCategory()+")\n" +
                ticketResponse.getLanguage()+", "+ticketResponse.getFormat()+"\n" +
                ticketResponse.getTheatreName()+": "+ticketResponse.getLocality()+" (Screen Number: "+ticketResponse.getScreenNumber()+")\n" +
                "Location URL: "+ticketResponse.getLocationUrl()+"\n" +
                ticketResponse.getSeatInfo()+"\n" +
                "Show Date: "+ticketResponse.getShowDate()+"\n" +
                "Show Time: "+ticketResponse.getShowTime()+"\n\n\n" +
                "Regards,\n\n" +
                "Team Burning Beatles";
        return body;
    }
    public String ticketCancellationConfirmationEmailGenerator(String name, String message){
        return "Dear "+name+"!!!\n\n\n" +
                message+"\n\n\n" +
                "Regards,\n\n" +
                "Team Burning Beatles";

    }



}

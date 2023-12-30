package com.example.book_your_show.generators;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;
@Component
public class EmailGenerator {

    public String userEmailValidationCodeGenerator(){
        UUID uuid= UUID.randomUUID();
        return ""+uuid;
    }
    public String userSuccessfulRegistrationMessageEmailGenerator(String name){

        String body= "Dear "+name+" !!!\nWelcome to the English Readers' Club.\nYou have been successfully registered with us.";
        return body;
    }
    public String bookIssueEmailGenerator(String userName, String bookName, LocalDate returnDate){
        String body= "Dear "+userName+" !!!\n\nYour Book "+bookName+" has been issued" +
                "\nWe are expecting you to return the book by "+returnDate;
        return body;
    }
    public String bookReturnEmailGenerator(String userName, String bookName, LocalDate returnDate){
        String body= "Dear "+userName+" !!!\n\nYour book "+bookName+" has been returned to ERC successfully"+
                "\nTry exploring other books. Have a nice day!!!";
        return body;
    }
    public String dueAmountOnBookEmailGenerator(String name, String bookName, int amount){

        String body= "Dear "+name+" !!!\nDeadline to return the book "+bookName+" that is issued to is crossed. Fine amount to be paid till date is "+amount+"" +
                "\n Return the book by tomorrow";

        return body;
    }

}

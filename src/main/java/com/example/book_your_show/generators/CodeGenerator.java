package com.example.book_your_show.generators;


import com.example.book_your_show.exceptions.MaxLimitReachedToAddException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CodeGenerator {
    private static final String Prefix=null;
    private static final String FORMAT_PATTERN="yyyy";
    private static final Long MAX_SEQUENCE_NUMBER=Long.MAX_VALUE;



    public String generate(String prefix) throws Exception{
        LocalDate currentDate=LocalDate.now();
        String year= currentDate.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN));

        //fetching the latest sequence number that persisted previously
        long sequenceNumber = getLatestSequenceNumber(year);
        sequenceNumber+=1;
        if(sequenceNumber>MAX_SEQUENCE_NUMBER){
            throw new MaxLimitReachedToAddException("Maximum limit reached for this particular year to add to the database");
        }
        return year+prefix+String.format("%04d", sequenceNumber);
    }
    public Long getLatestSequenceNumber(String year){
        return Long.MAX_VALUE;
    }
}

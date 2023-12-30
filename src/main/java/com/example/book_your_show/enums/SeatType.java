package com.example.book_your_show.enums;

public enum SeatType {
    SILVER("Silver"),
    GOLD("Gold"),
    PLATINUM("Platinum"),
    LOUNGERS("Loungers"),
    SEMI_RECLINERS("Semi Recliners"),
    RECLINERS("Recliners");
    private final String displayName;
    SeatType(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }
}

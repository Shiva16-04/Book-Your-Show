package com.example.book_your_show.enums;

public enum City {
    AHMEDABAD("AHM"),
    BENGALURU("BGL"),
    CHANDIGARH("CDR"),
    CHENNAI("CHN"),
    DELHI("DEL"),
    HYDERABAD("HYD"),
    KOCHI("KCH"),
    KOLKATA("KLK"),
    NOIDA("ND"),
    PUNE("PUN");
    private final String cityCode;
    City(String cityCode){
        this.cityCode=cityCode;
    }
    public String getCityCode(){
        return cityCode;
    }

}

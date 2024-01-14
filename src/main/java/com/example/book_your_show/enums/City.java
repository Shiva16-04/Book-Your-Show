package com.example.book_your_show.enums;

public enum City {
    AHMEDABAD("Ahmedabad","AHM"),
    BENGALURU("Bengaluru","BGL"),
    CHANDIGARH("Chandigarh","CDR"),
    CHENNAI("Chennai","CHN"),
    DELHI("Delhi","DEL"),
    HYDERABAD("Hyderabad","HYD"),
    KOCHI("Kochi","KCH"),
    KOLKATA("Kolkata","KLK"),
    NOIDA("Noida","ND"),
    PUNE("Pune","PUN");
    public final String displayName;
    private final String cityCode;
    City(String displayName, String cityCode){
        this.displayName=displayName;
        this.cityCode=cityCode;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getCityCode(){
        return cityCode;
    }


}
